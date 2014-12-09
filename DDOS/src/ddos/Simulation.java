/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddos;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Juancho
 */
public class Simulation {
    ArrayList<Node> nodes;
    ArrayList<Client> clients;
    
    Server server;
    Node node;
    Client client;
    int mode;
    static Random rnd = new Random();
    
    private int numNodes, numClients, numSuspects, numAttacks, numPackets, numPacketsReceived, numAttacksDetected, numPacketsDropped, numAttacksReceived;
    private int cycles;
    private int attackTime;
    private int alarmThreshold;
    private int attackCounter;
    private int maxConnections;
    private double attackers;
    private double attackF;
    private boolean mutation;
    
    public Simulation(int nodes, int numClients, int mode, int cycles, double attackF, double attackers, int alarmThreshold, int attackCounter, boolean mutation, int maxConnections){
        this.nodes = new ArrayList();
        this.clients = new ArrayList();
        this.mode = mode;
        this.cycles = cycles;
        this.attackF = attackF;
        this.attackers = attackers;
        this.alarmThreshold = alarmThreshold;
        this.attackCounter = attackCounter;
        this.numNodes = nodes;
        this.numAttacks = 0;
        this.numSuspects = 0;
        this.numPackets = 0; 
        this.numPacketsReceived = 0; 
        this.numPacketsDropped = 0;
        this.numAttacksReceived = 0;
        this.numClients = numClients;
        this.mutation = mutation;
        this.maxConnections = maxConnections;
    }

    public int getNumSuspects() {
        return numSuspects;
    }
    
    public int getNumAttacks() {
        return numAttacks;
    }

    public int getNumPackets() {
        return numPackets;
    }

    public int getNumAttacksDetected() {
        return numAttacksDetected;
    }

    public int getNumPacketsDropped() {
        return numPacketsDropped;
    }

    public int getNumPacketsReceived() {
        return numPacketsReceived;
    }

    public int getNumAttacksReceived() {
        return numAttacksReceived;
    }
     
    public void initalize() throws Exception{
        int nodeId, clientId, nodePos, conections;
        ArrayList<Integer> generatedIds = new ArrayList();

        //Server creation. It always will be id 1
        
        server = new Server(1);
        
        //Generate nodes
        for(int i=0; i<numNodes; i++){
            //nodeId = rnd.nextInt(999) + 1000;
            nodeId = rnd.nextInt(numNodes*2)+1;
            if(generatedIds.indexOf(nodeId) == -1){
                node = new Node(nodeId);
                nodes.add(node);
                generatedIds.add(nodeId);
            }            
        }
        
        //Generate clients
        for(int i=0; i<numClients; i++){
            while(true){
                clientId = rnd.nextInt(numClients*2)+1;
                if(generatedIds.indexOf(clientId) == -1){
                    if(mode == 0){
                        client = new Client(clientId, false);
                    }
                    if(mode == 1){
                        if(rnd.nextDouble()< attackers ){
                            client = new Client(clientId, true);
                        }
                        else{
                            client = new Client(clientId, false);
                        }
                    }
                    clients.add(client);
                    generatedIds.add(clientId);
                    break;
                }
            }   
        }
        
        //Generate connections with clients, maximum 4  per client, minimum 2
        for(int i=0; i<clients.size(); i++){
            //conections = 5;    
            if(numNodes<maxConnections){
                conections = rnd.nextInt(numNodes) + 1;
            }
            else{
                conections = rnd.nextInt(maxConnections) + 1; 
            }
            
            for(int j=0; j<conections; j++){
                while(true){
                    nodePos = getNode(nodes.get(rnd.nextInt(nodes.size())).getId());
                   
                    if(clients.get(i).checkConnection(nodes.get(nodePos).getId()) == -1){
                       clients.get(i).addConnection(nodes.get(nodePos).getId());
                       nodes.get(nodePos).addClient(clients.get(i).getId());
                       break;
                    }
                }
            }
        }
    }
    
    public void simulate() throws Exception{
        
        int alarmCounter, attacker, attackWave;
        double auxPacket, signal;
        ArrayList<Packet> packets = new ArrayList();
        ArrayList<Node> alertedNodes = new ArrayList();
        
        attackWave = 0;
        for(int i=0; i<cycles; i++){
            
            //Send packets
            signal = rnd.nextFloat();
            for(int j=0; j<clients.size(); j++){
                auxPacket = rnd.nextFloat();
                //Generate an attack by client j
                //Asynchronize mode
                if(mode == 0){
                    if(auxPacket<attackF){
                        packets.add(clients.get(j).sendMessage(true, false));
                        //System.out.println(packets.get(j).getMessage());
                    }
                    else{
                        packets.add(clients.get(j).sendMessage(false, false));
                    }
                }
                //Synchronize mode
                if(mode == 1){
                    if(attackWave > 0){
                        packets.add(clients.get(j).sendMessage(false, true)); 
                        attackWave--;
                    }
                    else{
                        if(signal<attackF){
                            packets.add(clients.get(j).sendMessage(false, true));
                            attackWave = attackCounter;
                        }
                        else{
                            packets.add(clients.get(j).sendMessage(false, false));
                        }
                    }
                }
            }
            
            //Send packets to nodes
            alarmCounter = 0;
            for(int k=0; k<packets.size(); k++){
                //Check message 
                
                //Verify is not blocked
                if(nodes.get(getNode(packets.get(k).getReceiver())).checkBlocked(i)){
                    //Suspect detected
                    if(!nodes.get(getNode(packets.get(k).getReceiver())).checkMessage(packets.get(k).getSender(), packets.get(k).getMessage())){
                        numSuspects++;
                        nodes.get(getNode(packets.get(k).getReceiver())).addSuspect(packets.get(k).getSender());
                        alertedNodes.add(nodes.get(getNode(packets.get(k).getReceiver())));
                        alarmCounter++;
                    }
                    //Send packet to server
                    server.receivePacket(packets.get(k));
                }
                else{
                    //Packet dropped
                    //Mutate Id
                    if(getClient(packets.get(k).getSender()) != -1 && mutation){
                        clients.get(getClient(packets.get(k).getSender())).notifyBlock();
                    }
                    
                    numAttacks++;
                    numPacketsDropped++;
                }
                
                //Attack detected
                if(alarmCounter == alarmThreshold){
                    //System.out.println("ATTACK" + " " + k);
                    attacker = detectAttacker(alertedNodes);
                    for(Node nodeAlerted: alertedNodes){
                        nodeAlerted.blockConnection(attacker);
                    }
                    alertedNodes = new ArrayList();
                    alarmCounter = 0;
                    numAttacks++;
                    numAttacksDetected++;
                }
                numPackets++;
            }
        }
        numPacketsReceived = server.getRequestCounter();
        numAttacksReceived = server.getAttackCounter();
    }
    
    private int getNode(int nodeId) throws Exception{
        for(int i=0; i<nodes.size(); i++){
            if(nodes.get(i).getId() == nodeId){
                return i;
            }
        }
        
        throw new Exception("Node not found");
    }
    
    private int getClient(int clientId){
        for(int i=0; i<clients.size(); i++){
            if(clients.get(i).getId() == clientId){
                return i;
            }
        }
        //Client already mutate
        return -1;
    }
    
    private static int detectAttacker(ArrayList<Node> alertedNodes) throws Exception{
        int keyLen, key, a, max, rndAux, groupSize;
        boolean keysLock;
        //System.out.println("alerts "+ alertedNodes.size());
        ArrayList<String> truthTable = new ArrayList();
        ArrayList<Expression> expressions = new ArrayList();
        ArrayList<Integer> keys = new ArrayList();
        ArrayList<Binary> binaryAuxList;
        ArrayList<Ant> agents = new ArrayList();
        ArrayList<List> suspectListArray = new ArrayList();
        ArrayList<Integer> suspect = new ArrayList();
        
        String groupKey;
        Binary binaryAux;
        List suspectList;
        
        //Number of bits for representation
        keyLen = (int) Math.ceil(Math.log(alertedNodes.size())/Math.log(2));
        
        //Generate binary values for each node AKA Truth table
        for(int i=0; i<alertedNodes.size(); i++){
            truthTable.add(toBinaryStringOfLength(i, keyLen));
        }
        
        //Generate Array of variables. ej; S1, S2, S3
        
        for(int i=0; i<keyLen; i++){
            //Normal and negated (¬)  value
            binaryAuxList = new ArrayList();
            binaryAuxList.add(new Binary(i, 0));
            expressions.add(new Expression(binaryAuxList));
            binaryAuxList = new ArrayList();
            binaryAuxList.add(new Binary(i, 1));
            expressions.add(new Expression(binaryAuxList));
            //System.out.println(expressions.size()+ " expS");
        }
        
        //Combine variables
        //KeyLen minimum 2
        for(int i=0; i<keyLen*2; i++){
            //System.out.println("ssst");
            binaryAux = expressions.get(i).getIndexList().get(0);
            for(int j=i+1; j<keyLen*2; j++){
                //Obviate same variable combination (S1 * ¬S1) 
                if(j == i+1 && i%2 == 0){
                    continue;
                }                    
                binaryAuxList = new ArrayList();
                binaryAuxList.add(binaryAux);
                binaryAuxList.add(expressions.get(j).getIndexList().get(0));
                //System.out.println(binaryAuxList + " Binary list");
                expressions.add(new Expression(binaryAuxList));
                //System.out.println("Expressions size " + expressions.size() );
            }
        }
        //Solve key problem
        //Generate ants
        for(int i=0; i<alertedNodes.size(); i++){
            agents.add(new Ant(expressions, truthTable, generateTrail(expressions.size()-1), alertedNodes.size()));
        }
        //System.out.println("Houweee");
        //Calculate minimun key
        keysLock = false;
        while(!keysLock){
            for(int i=0; i<agents.size(); i++){
                //Minimum value found
                if(agents.get(i).updateTrail() == alertedNodes.size()){
                    keys = agents.get(i).getTrail();
                    keysLock = true;
                    /*System.out.println("Keys" + keys);
                    System.out.println("Keys size " + keys.size());*/
                    break;
                }
            }
            //System.out.println("Houweee");
        }
        
        //Distribute groupKey
        int groupKeySize = 0;
      
        for(int i=0; i<keys.size(); i++){
            if(groupKeySize < expressions.get(keys.get(i)).getIndexList().size()){
                groupKeySize = expressions.get(keys.get(i)).getIndexList().size();
            }
        }
        
        //System.out.println("Group key size "+ groupKeySize);
        a = (int)(Math.pow(2,groupKeySize-1));
        max = (int)(Math.pow(2,groupKeySize));
        //System.out.println("max " + max +" a "+ a);
        groupKey = toBinaryStringOfLength(rnd.nextInt(max-a)+a+1,0);
        //System.out.println("GroupKey "+ groupKey);
        for(int i=0; i<alertedNodes.size(); i++){
            alertedNodes.get(i).setGroupKey(groupKey);
        }

        //Distribute auxiliary keys
//        System.out.println("alerted nodes: " + alertedNodes.size());
//        System.out.println("keys size "+keys.size());
        //int auxIs = keys.size()-alertedNodes.size() ; 
        
        int keysSize = keys.size();
        for(int i=0; i<keysSize; i++){
            if(keys.size()>alertedNodes.size()){
                keys.remove(keys.size()-1);
            }
        }
        
        //System.out.println("keys: " + keys.size());
        //System.out.println("nodes size: " + alertedNodes.size());
        groupSize = (int)(Math.floor(alertedNodes.size()/keys.size()))-1;
        key = 0;
        //max = (int)(Math.pow(2,expressions.get(keys.get(key)).getIndexList().size()))-1;
        //System.out.println("New Group");
        //ArrayList<String> keysP = new ArrayList();
        rndAux = rnd.nextInt((int)(Math.pow(2,expressions.get(keys.get(key)).getIndexList().size()))-1);
        for(int i=0; i<alertedNodes.size(); i++){            
            //max = (int)(Math.pow(2,expressions.get(keys.get(key)).getIndexList().size()))-1;
            //rndAux = rnd.nextInt(max);
            //System.out.println("Random 1 "+ rndAux);
            //System.out.println("Size "+ expressions.get(keys.get(key)).getIndexList().size
            if(i%groupSize == 0 && i != 0){
                key++;
                rndAux = rnd.nextInt(max);
                //System.out.println("New Group");
            }
            if(key == keys.size()){
                key = 0;
                rndAux = rnd.nextInt(max);
                //System.out.println("Star over");
            }
            //System.out.println("Random 2 "+ rndAux);
            //System.out.println("Alerted " + alertedNodes.size());
            //System.out.println("Expressions " + expressions.size());
            //System.out.println("max " + max);
            //System.out.println("Group size " + groupSize);
            //System.out.println(keys + " Keys");
            //System.out.println("Aux keys last size " + expressions.get(expressions.size()-1).getIndexList().size());
            //System.out.println("Aux keys " + expressions.get(keys.get(key)).evaluateString(toBinaryStringOfLength(rndAux,0)));
            //System.out.println("group "+groupSize);
            //System.out.println("Random aux " + rndAux);
            //System.out.println("Key " + keys.get(key) + " i: " +i);
            //System.out.println("Real value " + expressions.get(keys.get(key)).evaluateString(toBinaryStringOfLength(rndAux,0)));
            //keysP.add(expressions.get(keys.get(key)).evaluateString(toBinaryStringOfLength(rndAux,0)));
            alertedNodes.get(i).setAuxKey(expressions.get(keys.get(key)).evaluateString(toBinaryStringOfLength(rndAux,0)));
            //System.out.println("Aux keys " + alertedNodes.get(i).getAuxKey() + " i: " + i);
            //System.out.println("\n\n");
            /*System.out.println("Aux keys check ");
            for(int k=0; k<alertedNodes.size(); k++){
                System.out.println(alertedNodes.get(k).getAuxKey() + " k: " + k);
            }
            System.out.println("\n\n");*/
        }
        
//        System.out.println("Aux keys check ");
//        for(int i=0; i<alertedNodes.size(); i++){
//            System.out.println(alertedNodes.get(i).getAuxKey() + " i: " + i);
//        }
//        int u=0;
//        for(Node nodeAlerted: alertedNodes){
//            nodeAlerted.setAuxKey(keysP.get(u));
//            u
//        }
//        
        //Send suspect list
        for(int i=0; i<alertedNodes.size(); i+=groupSize){
            suspectList = new List(alertedNodes.get(i).getSuspect(),alertedNodes.get(i).getSign());
            //System.out.println("\n\n\n");
            //System.out.println("Aux key "+alertedNodes.get(i).getAuxKey()+ " i: "+ i);
            for(int j=i+1; j<groupSize+i; j++){
                //Check suspect list and add own
                //System.out.println("alerted "+alertedNodes.size());
                //System.out.println("group "+groupSize);
                if(j<alertedNodes.size()){
                    /*System.out.println("\n \n \n ");
                    System.out.println("j: "+ j);
                    System.out.println("Aux key "+alertedNodes.get(j).getAuxKey());*/
                    suspectList.addSuspect(alertedNodes.get(j).getSuspect());
                    suspectList.setSign(alertedNodes.get(j).getSign());
                    /*if(alertedNodes.get(j).checkSign(suspectList.getSign())){
                        suspectList.addSuspect(alertedNodes.get(j).getSuspect());
                        suspectList.setSign(alertedNodes.get(j).getSign());
                    }
                    else{
                        //System.out.println("j: "+ j);
                        //System.out.println("Hallo hallo");
                        throw new Exception("Invalid suspect List!!!");
                    }*/
                }
            }
            suspectListArray.add(suspectList);
        }
        
        //Check lists and deteck attack
        //Add all list in one
        suspectList = new List();
        for(int i=0; i<suspectListArray.size(); i++){
            suspectList.addSuspect(suspectListArray.get(i).getSuspects());
        }
        
        return suspectList.getAttacker();
    }
    
    private static ArrayList<Integer> generateTrail(int max){
        ArrayList<Integer> trail = new ArrayList();
        int a, b;
        //System.out.println("Maxi " + max);
        a = rnd.nextInt(max);
        b = rnd.nextInt(max-a) + a + 1;
        
        trail.add(a);
        trail.add(b);
        
        return trail;
    }
    
    //Transform int into binary String
    private static String toBinaryStringOfLength(int value, int length) {
        String binaryString = Integer.toBinaryString(value); 
        StringBuilder leadingZeroes = new StringBuilder();
        for(int index = 0; index < length - binaryString.length(); index++) {
            leadingZeroes = leadingZeroes.append("0");
        }

        return leadingZeroes + binaryString;
    }
}
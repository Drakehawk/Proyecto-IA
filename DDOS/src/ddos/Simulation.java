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
    
    private int numNodes, numClients, numSuspects, numAttacks, numPackets, numPacketsReceived, numAttacksDetected, numPacketsDropped, numSuspectsReceived;
    private int cycles;
    //private int attackTime;
    private int alarmThreshold;
    private int waves;
    private int maxConnections;
    private double attackers;
    private double attackF;
    private boolean mutation;
    
    public Simulation(){
        this.nodes = new ArrayList();
        this.clients = new ArrayList();
        this.mode = 0;
        this.cycles = 0;
        this.attackF = 0;
        this.attackers = 0;
        this.alarmThreshold = 0;
        this.waves = 0;
        this.numNodes = 0;
        this.numAttacks = 0;
        this.numSuspects = 0;
        this.numPackets = 0; 
        this.numPacketsReceived = 0; 
        this.numPacketsDropped = 0;
        this.numSuspectsReceived = 0;
        this.numClients = 0;
        this.mutation = false;
    }
    
    public Simulation(int nodes, int numClients, int mode, int cycles, double attackF, double attackers, int alarmThreshold, int waves, boolean mutation){
        this.nodes = new ArrayList();
        this.clients = new ArrayList();
        this.mode = mode;
        this.cycles = cycles;
        this.attackF = attackF;
        this.attackers = attackers;
        this.alarmThreshold = alarmThreshold;
        this.waves = waves;
        this.numNodes = nodes;
        this.numAttacks = 0;
        this.numSuspects = 0;
        this.numPackets = 0; 
        this.numPacketsReceived = 0; 
        this.numPacketsDropped = 0;
        this.numSuspectsReceived = 0;
        this.numClients = numClients;
        this.mutation = mutation;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setNumNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    public void setNumClients(int numClients) {
        this.numClients = numClients;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public void setAlarmThreshold(int alarmThreshold) {
        this.alarmThreshold = alarmThreshold;
    }

    public void setWaves(int waves) {
        this.waves = waves;
    }

    public void setAttackers(double attackers) {
        this.attackers = attackers;
    }

    public void setAttackF(double attackF) {
        this.attackF = attackF;
    }

    public void setMutation(boolean mutation) {
        this.mutation = mutation;
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

    public int getNumSuspectsReceived() {
        return numSuspectsReceived;
    }
     
    public void initalize() throws Exception{
        int nodeId, clientId, nodePos, connections;
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
            if(numNodes<10){
                connections = 3;
            }
            else{
                connections = 5;
            }
            
            for(int j=0; j<connections; j++){
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
                            attackWave = waves;
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
                    attacker = detectAttacker(alertedNodes);
                    for(Node nodeAux: nodes){
                        nodeAux.blockConnection(attacker);
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
        numSuspectsReceived = server.getSuspectCounter();
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
        }
        
        //Combine variables
        //KeyLen minimum 2
        for(int i=0; i<keyLen*2; i++){
            
            binaryAux = expressions.get(i).getIndexList().get(0);
            for(int j=i+1; j<keyLen*2; j++){
                //Obviate same variable combination (S1 * ¬S1) 
                if(j == i+1 && i%2 == 0){
                    continue;
                }                    
                binaryAuxList = new ArrayList();
                binaryAuxList.add(binaryAux);
                binaryAuxList.add(expressions.get(j).getIndexList().get(0));
                expressions.add(new Expression(binaryAuxList));
            }
        }
        //Solve key problem
        //Generate ants
        for(int i=0; i<alertedNodes.size(); i++){
            agents.add(new Ant(expressions, truthTable, generateTrail(expressions.size()-1), alertedNodes.size()));
        }
        
        //Calculate minimun key
        keysLock = false;
        while(!keysLock){
            for(int i=0; i<agents.size(); i++){
                //Minimum value found
                if(agents.get(i).updateTrail() == alertedNodes.size()){
                    keys = agents.get(i).getTrail();
                    keysLock = true;
                    break;
                }
            }
        }
        
        //Distribute groupKey
        int groupKeySize = 0;
      
        for(int i=0; i<keys.size(); i++){
            if(groupKeySize < expressions.get(keys.get(i)).getIndexList().size()){
                groupKeySize = expressions.get(keys.get(i)).getIndexList().size();
            }
        }
        
        a = (int)(Math.pow(2,groupKeySize-1));
        max = (int)(Math.pow(2,groupKeySize));
        groupKey = toBinaryStringOfLength(rnd.nextInt(max-a)+a+1,0);
        for(int i=0; i<alertedNodes.size(); i++){
            alertedNodes.get(i).setGroupKey(groupKey);
        }

        //Distribute auxiliary keys
        int keysSize = keys.size();
        for(int i=0; i<keysSize; i++){
            if(keys.size()>alertedNodes.size()){
                keys.remove(keys.size()-1);
            }
        }
        
        groupSize = (int)(Math.floor(alertedNodes.size()/keys.size()))-1;
        key = 0;
        rndAux = rnd.nextInt((int)(Math.pow(2,expressions.get(keys.get(key)).getIndexList().size()))-1);
        for(int i=0; i<alertedNodes.size(); i++){            
            if(i%groupSize == 0 && i != 0){
                key++;
                rndAux = rnd.nextInt(max);
            }
            if(key == keys.size()){
                key = 0;
                rndAux = rnd.nextInt(max);
            }
            alertedNodes.get(i).setAuxKey(expressions.get(keys.get(key)).evaluateString(toBinaryStringOfLength(rndAux,0)));
        }
       
        //Send suspect list
        for(int i=0; i<alertedNodes.size(); i+=groupSize){
            suspectList = new List(alertedNodes.get(i).getSuspect(),alertedNodes.get(i).getSign());
            
            for(int j=i+1; j<groupSize+i; j++){
                //Check suspect list and add own
                if(j<alertedNodes.size()){
                    suspectList.addSuspect(alertedNodes.get(j).getSuspect());
                    suspectList.setSign(alertedNodes.get(j).getSign());
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
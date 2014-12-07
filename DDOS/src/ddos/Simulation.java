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
    static ArrayList<Node> nodes;
    static ArrayList<Client> clients;
    
    Server server;
    Node node;
    Client client;
    static Random rnd = new Random();
    
    private int numNodes, antNumbers, numClients, numAttacks, numPackets;
    
    public Simulation(int nodes, int antNumbers, int numClients){
        Simulation.nodes = new ArrayList();
        Simulation.clients = new ArrayList();
        this.numNodes = nodes;
        this.antNumbers = antNumbers;
        this.numClients = numClients;
    }

    public int getNumAttacks() {
        return numAttacks;
    }

    public int getNumPackets() {
        return numPackets;
    }
    
    public void initalize() throws Exception{
        int nodePos, conections;
        ArrayList<Integer> nodeIds = new ArrayList();
        
        numAttacks = 0;
        numPackets = 0; 
        //Server creation. It alwas will be id 1
        //nodeId = rnd.nextInt(999) + 1000;
        //nodeId = rnd.nextInt(999) + 1000;
        server = new Server(1);
        //nodeIds.add(nodeId);
        
        //Generate nodes
        for(int i=0; i<numNodes; i++){
            //nodeId = rnd.nextInt(999) + 1000;
            nodePos = rnd.nextInt(numNodes*2)+1;
            if(nodeIds.indexOf(nodePos) == -1){
                node = new Node(nodePos, antNumbers);
                nodes.add(node);
                nodeIds.add(nodePos);
            }            
        }
        
        //Generate clients
        for(int i=0; i<numClients; i++){
            while(true){
                nodePos = rnd.nextInt(numClients*2)+1;
                //nodeId = rnd.nextInt(999) + 1000;
                if(nodeIds.indexOf(nodePos) == -1){
                    client = new Client(nodePos);
                    clients.add(client);
                    nodeIds.add(nodePos);
                    break;
                }
            }   
        }
        
        //Generate connections with clients, maximum 4  per client, minimum 2
        for(int i=0; i<clients.size(); i++){
            conections = rnd.nextInt(3) + 2;
            for(int j=0; j<conections; j++){
                while(true){
                    nodePos = getNode(nodes.get(rnd.nextInt(nodes.size())).getId());
                    //if(nodeId>nodes.size()){
                    //    System.out.println("WHAT??");
                    //System.out.println(nodePos);
                    if(clients.get(i).checkConnection(nodes.get(nodePos).getId()) == -1){
                       clients.get(i).addConnection(nodes.get(nodePos).getId());
                       nodes.get(nodePos).addClient(clients.get(i).getId());
                       break;
                    }
                    //}
                }
            }
        }
    }
    
    public void simulate(int cycles, double attackF, int alarmThreshold) throws Exception{
        
        int sender, receipt, alarmCounter, attacker;
        double auxPacket;
        ArrayList<Packet> packets = new ArrayList();
        ArrayList<Node> alertedNodes = new ArrayList();
        //Node auxNode;
        
        for(int i=0; i<cycles; i++){
            
            //Send packets
            for(int j=0; j<clients.size(); j++){
                auxPacket = rnd.nextFloat();
                //Generate an attack by client j
                if(attackF<auxPacket){
                    packets.add(clients.get(j).sendMessage(true));
                }
            }
            
            //Send packets to nodes
            alarmCounter = 0;
            //attacker = 0;
            for(int k=0; k<packets.size(); k++){
                //Check message 
                sender = packets.get(i).getSender();
                receipt = packets.get(i).getReceiver();
                //if(){}
                //Suspect detected
                if(!nodes.get(getNode(receipt)).checkMessage(sender, packets.get(i).getMessage())){
                    nodes.get(receipt).addSuspect(sender);
                    alertedNodes.add(nodes.get(receipt));
                    numAttacks++;
                    alarmCounter++;
                    //Attack detected
                    if(alarmCounter == alarmThreshold){
                        attacker = detectAttacker(alertedNodes);
                        alertedNodes = new ArrayList();
                    }
                }
                numPackets++;
            }
        }
    }
    
    private static int getNode(int nodeId) throws Exception{
        for(int i=0; i<nodes.size(); i++){
            if(nodes.get(i).getId() == nodeId){
                return i;
            }
        }
        
        throw new Exception("Node not found");
    }
    
    private static int detectAttacker(ArrayList<Node> alertedNodes) throws Exception{
        int keyLen, key, a, max, groupSize;
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
        
        //Random rnd = new Random();
        
        //Number of bits for representation
        keyLen = (int) Math.ceil(Math.log(alertedNodes.size())/Math.log(2));
        
        //Generate binary values for each node AKA Truth table
        for(int i=0; i<alertedNodes.size(); i++){
            truthTable.add(toBinaryStringOfLength(i, keyLen));
        }
        
        //Generate Array of variables. ej; S1, S2, S3
        binaryAuxList = new ArrayList();
        for(int i=0; i<keyLen*2; i++){
            binaryAuxList.add(new Binary(i, i%2));
            //expressionAux = new Expression(new ArrayList<>)
            expressions.add(new Expression(binaryAuxList));
        }
        
        //Combine variables
        for(int i=2; i<keyLen; i++){
            for(int j=0; j<keyLen*2; j++){
                binaryAux = expressions.get(j).getIndexList().get(0);
                for(int k=j+1; k<keyLen*2; k++){
                    //Obviate same variable combination (S1 * ¬S1) 
                    if(j%2 == 0 && k == j+1){
                        continue;
                    }                    
                    binaryAuxList = new ArrayList();
                    binaryAuxList.add(binaryAux);
                    binaryAuxList.add(expressions.get(k).getIndexList().get(0));
                    expressions.add(new Expression(binaryAuxList));
                }
            }
        }
        
        //Solve key problem
        //Generate ants
        for(int i=0; i<alertedNodes.size(); i++){
            agents.add(new Ant(expressions, truthTable, generateTrail(expressions.size()-1)));
        }
        
        //Calculate minimun key
        keysLock = false;
        while(keysLock){
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
        max = (int)(Math.pow(2,groupKeySize))-1;
        groupKey = toBinaryStringOfLength(rnd.nextInt(max-a)+a+1,0);
        for(int i=0; i<alertedNodes.size(); i++){
            alertedNodes.get(i).setGroupKey(groupKey);
        }

        //Distribute auxiliary keys
        groupSize = (int)(Math.floor(alertedNodes.size()/keys.size()))-1;
        key = 0;
        for(int i=0; i<alertedNodes.size(); i++){            
            max = (int)(Math.pow(2,expressions.get(keys.get(key)).getIndexList().size()))-1;
            if(i%groupSize == 0){
                key++;
            }
            if(key == keys.size()){
                key = 0;
            }
            alertedNodes.get(i).setAuxKey(expressions.get(keys.get(key)).evaluateString(toBinaryStringOfLength(rnd.nextInt(max),0)));
        }
        
        //Send suspect list
        for(int i=0; i<alertedNodes.size(); i+=groupSize){
            suspectList = new List(alertedNodes.get(i).getSuspect(),alertedNodes.get(i).getSign());
            for(int j=i+1; j<groupSize+i; j++){
                //Check suspect list and add own
                if(alertedNodes.get(j).checkSign(suspectList.getSign())){
                    suspectList.addSuspect(alertedNodes.get(j).getSuspect());
                    suspectList.setSign(alertedNodes.get(j).getSign());
                }
                else{
                    throw new Exception("Invalid suspect List!!!");
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
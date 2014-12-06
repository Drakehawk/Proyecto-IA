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
public class System {
    ArrayList<Node> nodes;
    ArrayList<Node> alertedNodes;
    ArrayList<Client> clients;
    Server server;
    Node node;
    Client client;
    Random rnd = new Random();
    int numNodes, antNumbers, numClients;
    
    
    public System(int nodes){
        this.numNodes = nodes;
    }
    
    public void initalize(){
        int aux, cons;
        ArrayList<Integer> auxArray = new ArrayList(); //Array of generated ids
        Node auxNode;
        
//      layer = (int) Math.floor(numNodes/3);
//      auxLayer = layer%3;
        aux = rnd.nextInt(999) + 1000;
        server = new Server(aux);
        auxArray.add(aux);
        
        //Generate nodes
        for(int i=0; i<numNodes; i++){
            aux = rnd.nextInt(999) + 1000;
            if(auxArray.indexOf(aux) == -1){
                node = new Node(aux, antNumbers);
                nodes.add(node);
                auxArray.add(aux);
            }            
        }
        
        //Generate clients
        for(int i=0; i<numClients; i++){
            while(true){
                aux = rnd.nextInt(999) + 1000;
                if(auxArray.indexOf(aux) == -1){
                    client = new Client(aux);
                    clients.add(client);
                    auxArray.add(aux);
                    break;
                }
            }   
        }
        
        
        //Generate connections with clients, maximum 4  per client, minimum 2
        for(int i=0; i<numClients; i++){
            cons = rnd.nextInt(3) + 2;
            for(int j=0; j<cons; j++){
                while(true){
                    aux = rnd.nextInt(numNodes);
                    if(clients.get(i).checkConnection(nodes.get(aux).getCode()) == -1){
                       clients.get(i).addConnection(nodes.get(aux).getCode());
                       break;
                    }
                }
            }
        }
        
        //Generate random node connections in 3 layers
//        for(int i=0; i<numNodes; i++){
//            //First layer
//            if(i<layer){
//                for(int j=0; j<2; j++){
//                    while(true){
//                        aux = rnd.nextInt(layer);
//                        if(j == 1){
//                            aux = aux + layer - 1;
//                        }
//                        if(aux != nodes.get(i).getCode() && nodes.get(i).checkConnection(aux) == -1){
//                            nodes.get(i).addConnection(aux);
//                            break;
//                        }
//                    }
//                }
//            }
//            //Second layer
//            if(i>=layer && i<numNodes-layer){
//                for(int j=0; j<2; j++){
//                    while(true){
//                        aux = rnd.nextInt(layer) + layer;
//                        if(j == 1){
//                            aux = rnd.nextInt(layer) + layer*2+auxLayer;
//                        }
//                        if(aux != nodes.get(i).getCode() && nodes.get(i).checkConnection(aux) == -1){
//                            nodes.get(i).addConnection(aux);
//                            break;
//                        }
//                    }
//                }
//            }
//            //Last layer
//            else{   
//                for(int j=0; j<2; j++){
//                    while(true){
//                        aux = rnd.nextInt(layer) + layer*2+auxLayer; 
//                        if(j == 1){
//                            aux = server.getId();
//                        }
//                        if(aux != nodes.get(i).getCode() && nodes.get(i).checkConnection(aux) == -1){
//                            nodes.get(i).addConnection(aux);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
    }
    
    public void simulate(int cycles, int attackF){
        
        int aux;
        
        for(int i=0; i<cycles; i++){
            
            //Send packets
            for(int j=0; j<clients.size(); j++){
                aux = rnd.nextInt();
                //Generate an attack by client j
                if(attackF<aux){
                    clients.get(j).sendMessage(true);
                }
            }
        }
    }
    
    //Detect Intruder
    
    //Generate keys
    public void generateKeys(int alertedNodes){
        int keyLen;
        boolean auxBool;
        ArrayList<String> truthTable = new ArrayList();
        ArrayList<Expression> expression = new ArrayList();
        ArrayList<Binary> binaryAuxList;
        //Expression expressionAux;
        Binary binaryAux;
        Ant antAgent;
        ArrayList<Ant> agents = new ArrayList();
        
        //Number of bits for representation
        keyLen = (int) Math.ceil(Math.log(alertedNodes)/Math.log(2));
        
        //Generate binary values for each node AKA Truth table
        for(int i=0; i<alertedNodes; i++){
            truthTable.add(toBinaryStringOfLength(i, keyLen));
        }
        
        //Generate Array of variables. ej; S1, S2, S3
        binaryAuxList = new ArrayList();
        for(int i=0; i<keyLen*2; i++){
            binaryAuxList.add(new Binary(i, i%2));
            //expressionAux = new Expression(new ArrayList<>)
            expression.add(new Expression(binaryAuxList));
        }
        
        //Combine variables
        for(int i=2; i<keyLen; i++){
            for(int j=0; j<keyLen*2; j++){
                binaryAux = expression.get(j).getIndexList().get(0);
                for(int k=j+1; k<keyLen*2; k++){
                    //Obviate same variable combination (S1 * ¬S1) 
                    if(j%2 == 0 && k == j+1){
                        continue;
                    }                    
                    binaryAuxList = new ArrayList();
                    binaryAuxList.add(binaryAux);
                    binaryAuxList.add(expression.get(k).getIndexList().get(0));
                    expression.add(new Expression(binaryAuxList));
                }
            }
        }
        
        //Solve key problem
        //Generate ants
        for(int i=0; i<alertedNodes; i++){
            
        }
        
        
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

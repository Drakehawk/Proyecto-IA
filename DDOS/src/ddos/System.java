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
    Server server;
    Node node;
    Random rnd = new Random();
    int numNodes;
    int antNumbers;
    
    public System(int nodes){
        this.numNodes = nodes;
    }
    
    public void initalize(){
        int aux, layer, auxLayer;
        ArrayList<Integer> auxArray = new ArrayList();
        Node auxNode;
        
        layer = (int) Math.floor(numNodes/3);
        auxLayer = layer%3;
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
        
        //Generate random node connections in 3 layers
        for(int i=0; i<numNodes; i++){
            //First layer
            if(i<layer){
                for(int j=0; j<2; j++){
                    while(true){
                        aux = rnd.nextInt(layer);
                        if(j == 1){
                            aux = aux + layer - 1;
                        }
                        if(aux != nodes.get(i).getCode() && nodes.get(i).checkConnection(aux) == -1){
                            nodes.get(i).addConnection(aux);
                            break;
                        }
                    }
                }
            }
            //Second layer
            if(i>=layer && i<numNodes-layer){
                for(int j=0; j<2; j++){
                    while(true){
                        aux = rnd.nextInt(layer) + layer;
                        if(j == 1){
                            aux = rnd.nextInt(layer) + layer*2+auxLayer;
                        }
                        if(aux != nodes.get(i).getCode() && nodes.get(i).checkConnection(aux) == -1){
                            nodes.get(i).addConnection(aux);
                            break;
                        }
                    }
                }
            }
            //Last layer
            else{   
                for(int j=0; j<2; j++){
                    while(true){
                        aux = rnd.nextInt(layer) + layer*2+auxLayer; 
                        if(j == 1){
                            aux = server.getId();
                        }
                        if(aux != nodes.get(i).getCode() && nodes.get(i).checkConnection(aux) == -1){
                            nodes.get(i).addConnection(aux);
                            break;
                        }
                    }
                }
            }
        }
        
        //Generate random client connections
        
        //To be implemented
        
        
        //Detect Intruder
        
        
        
        
    }
}

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
public class Client {
    
    private int id;
    private boolean mutate;
    private ArrayList<Integer> connections;
    
    public Client(int id){
        this.id = id;
        this.mutate = false;
        this.connections = new ArrayList();
    }

    public void addConnection(int node) {
        this.connections.add(node);
    }
    
    public int checkConnection(int node){
        return this.connections.indexOf(node);
    }
    
    public int getId() {
        return id;
    }
    
//    public boolean isMutate() {
//        return mutate;
//    }
    
    public void notifyBlock(){
        Random rnd = new Random();
        id = rnd.nextInt() + 4;
        //mutate = true;
    }

    public ArrayList<Integer> getConnections() {
        return connections;
    }
    
    public Packet sendMessage(boolean attack){
        //String messageText = "";
        Packet message;
        Random rnd = new Random();
        
        int receiver;
        
        receiver = connections.get(rnd.nextInt(connections.size()));
        
        if(attack){
            message = new Packet("Bad", id, receiver);
        }
        else{
            message = new Packet("Ok", id, receiver);
        }
        return message;
    }
}

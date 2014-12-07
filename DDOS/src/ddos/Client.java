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
    
    private final int id;
    private ArrayList<Integer> connections;
    
    public Client(int id){
        this.id = id;
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
    
    public Packet sendMessage(boolean attack){
        //String messageText = "";
        Packet message;
        Random rand = new Random();
        int receiver;
        
        receiver = connections.get(rand.nextInt(connections.size()));
        
        if(attack){
            message = new Packet("Bad", id, receiver);
            //messageText = "Bad";
        }
        else{
            message = new Packet("Ok", id, receiver);
        }
        return message;
    }
}

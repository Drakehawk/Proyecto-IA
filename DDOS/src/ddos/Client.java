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
    private boolean synchronize;
    private ArrayList<Integer> connections;
    
    public Client(int id, boolean synchronize){
        this.id = id;
        this.mutate = false;
        this.connections = new ArrayList();
        this.synchronize = synchronize;
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
        id = rnd.nextInt() + 3;
        //mutate = true;
    }

    public ArrayList<Integer> getConnections() {
        return connections;
    }
    
    public Packet sendMessage(boolean attack, boolean signal){
        //String messageText = "";
        Packet message;
        Random rnd = new Random();
        
        //int receiver;
        //receiver = connections.get(rnd.nextInt(connections.size()));
        //Synchronize mode
        if(synchronize){
            if(signal){
                message = new Packet("Bad", id, connections.get(rnd.nextInt(connections.size())));
            }else{
                message = new Packet("Ok", id, connections.get(rnd.nextInt(connections.size())));
            }
            return message;
        }
        //Asynchronize mode
        else{
            if(attack){
                message = new Packet("Bad", id, connections.get(rnd.nextInt(connections.size())));
            }
            else{
                message = new Packet("Ok", id, connections.get(rnd.nextInt(connections.size())));
            }
            return message;
        }
    }
}

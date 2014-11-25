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
public class Node {
    
    private final int code;
    //private int energy;
    private final ArrayList<Integer> connections;
    private final ArrayList<Integer> clients;
    
    public Node(int code, ArrayList connections, ArrayList clients){
        this.code = code;
        //this.energy = 0;
        this.connections = connections;  
        this.clients = clients; 
    }
    
    public boolean verifyMessage(int sender, String message){
        if(!checkMessage(sender, message)){
            return true;
        }
        return false;
    }
    
    public boolean checkMessage(int sender, String message){
        int aux;
        Random rand = new Random();
        
        aux = rand.nextInt(clients.size());
        
        //Check sender Id in ruleset
        if(clients.get(aux) == sender){
            //Check message is valid
            return "Ok".equals(message);
        }
        else{
            if(clients.get(aux) < sender){
                return binarySearch(sender, 0, clients.indexOf(aux));
            }
            else{
                return binarySearch(sender, clients.indexOf(aux), clients.size()-1);
            }
        }   
    } 
    
    public boolean binarySearch(int value, int left, int right){
        int middle;
        while(left <= right){
            middle = (int) (Math.floor((right-left)/2)+left);
            if(clients.get(middle) ==  value){
                return true;
            }
            if(value < clients.get(middle)){
                right = middle - 1;
            }
            else{
                left = middle + 1 ;
            }
        }        
        return false;
    }
}

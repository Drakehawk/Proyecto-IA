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
    
    private final int id;
    private ArrayList<Integer> clients;
    private ArrayList<Integer> suspectList;
    private ArrayList<Integer> blockedConnections;
    private String groupKey;
    private String auxKey;
    
    public Node(int id){
        this.id = id;
        this.groupKey = "";
        this.auxKey = "";
        this.clients = new ArrayList();
        this.suspectList = new ArrayList();
        this.blockedConnections = new ArrayList();
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public void setAuxKey(String auxKey) {
        this.auxKey = auxKey;
    }
    
    public void addClient(int client){
        
        boolean insert = false;
        if(clients.isEmpty()){
            clients.add(client);
        }
        else{
            for(int i=0; i<clients.size(); i++){
                if(clients.get(i)>client){
                    clients.add(i, client);
                    insert = true;
                    break;
                }
            }
            if(!insert){
                clients.add(client);
            }
        }
    }
    
    public void addSuspect(int suspect){
        suspectList.add(suspect);
    }
    
    public void blockConnection(int attacker){
        blockedConnections.add(attacker);
    }    
    
    public boolean checkBlocked(int id){
        return blockedConnections.indexOf(id) == -1;
    }
    
    public ArrayList<Integer> getSuspect(){
        return suspectList;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getClients() {
        return clients;
    }

    public String getAuxKey() {
        return auxKey;
    }
    
    public boolean checkMessage(int sender, String message){
        int aux, antNumbers;
        int positive, negative;
        positive = 0;
        negative = clients.size()-1;
        ArrayList<Ant> ants = new ArrayList();
        ArrayList<Integer> tempAnts = new ArrayList();
        Ant auxAnt;
        Random rnd = new Random();
        
        antNumbers = rnd.nextInt((int)(Math.ceil(clients.size()/3))+1)+1;
        
        //Generate random ants
        while(ants.size()<antNumbers){
            aux = rnd.nextInt(clients.size());
            auxAnt = new Ant();
            if(tempAnts.indexOf(aux) == -1){
                auxAnt.setPheromone(clients.get(aux));
                auxAnt.calculateEnergySuspect(sender);
                ants.add(auxAnt);
                tempAnts.add(aux);
            }
        }
        
        //Check sender Id in ruleset
        for(int i=0; i<ants.size(); i++){
            if(ants.get(i).getEnergy() == 0){
                return "Ok".equals(message);
            }
            else if(ants.get(i).getEnergy() == 1){
                if(positive < clients.indexOf(ants.get(i).getPheromone())){
                    positive = clients.indexOf(ants.get(i).getPheromone());
                }
            }
            else if(ants.get(i).getEnergy() == -1){
                if(negative > clients.indexOf(ants.get(i).getPheromone())){
                    negative = clients.indexOf(ants.get(i).getPheromone());
                }
            }
        }
        if(binarySearch(sender, positive, negative)){
            return "Ok".equals(message);
        }
        return false; 
    } 
    
    public boolean binarySearch(int value, int left, int right){
        int middle;
        if(left > right){
            return false;
        }
        middle = (int) (Math.floor((left+right)/2));
        
        if(clients.get(middle) > value){
            return binarySearch(value, left, middle-1);
        }
        if(clients.get(middle) < value){
            return binarySearch(value, middle+1, right);
        }
       
        return true;
    }  
  
    public boolean checkSign(String sign) throws Exception{
        return cypher(sign, auxKey).equals(groupKey);
    }
    
    public String getSign() throws Exception{
        return cypher(groupKey,auxKey);
    }
    
    public static String cypher(String message, String key) throws Exception{
        String cypherMessage = "";
        String aux = "";
        
        if(message.length()>=key.length()){
            
            for(int i=0; i<message.length(); i++){
                if(message.length()>key.length()){
                    aux = "0";
                    key = aux.concat(key);
                }
                else{
                    break;
                }
            }
            
            for(int i=0; i<message.length(); i++){   

                if(i<key.length()){
                    if(message.charAt(i) == key.charAt(i)){
                        cypherMessage = cypherMessage.concat("0");
                    }
                    else{
                        cypherMessage = cypherMessage.concat("1");
                    }
                }
                else{
                    cypherMessage = cypherMessage.concat("1");
                }
            }
        }
        else{
            throw new Exception("Cypher error: Key is bigger than message");
        }
 
        return cypherMessage;
    }
    
}

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
    private final int antNumbers;
    //private int positive, negative;
    //private ArrayList<Integer> connections;
    private static ArrayList<Integer> clients;
    private static ArrayList<Integer> suspectList;
    //private Expression groupKey;
    private String groupKey;
    private String auxKey;
    //private Expression auxKey;
    
    public Node(int id, int antNumbers){
        this.id = id;
        //this.positive = 0;
        //this.negative = 0;
        this.antNumbers = antNumbers;
        //this.connections = new ArrayList();  
        clients = new ArrayList();
        suspectList = new ArrayList();
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public void setAuxKey(String auxKey) {
        this.auxKey = auxKey;
    }
    
    public void addClient(int client){
        if(clients.isEmpty()){
            clients.add(client);
        }
        for(int i=0; i<clients.size(); i++){
            if(clients.get(i)>client){
                clients.add(i, client);
            }
        }
    }
    
    public void addSuspect(int suspect){
        suspectList.add(suspect);
    }
    
    public ArrayList<Integer> getSuspect(){
        return suspectList;
    }

    public int getId() {
        return id;
    }
    
    public boolean checkMessage(int sender, String message){
        int aux;
        int positive, negative;
        positive = 0;
        negative = 0;
        ArrayList<Ant> ants = new ArrayList();
        ArrayList<Integer> tempAnts = new ArrayList();
        Ant auxAnt;
        Random rand = new Random();
        
        //Generate random ants
        while(ants.size()<antNumbers){
            aux = rand.nextInt(clients.size());
            auxAnt = new Ant();
            if(tempAnts.indexOf(aux) == -1){
                auxAnt.setPheromone(aux);
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
                if(positive < ants.get(i).getPheromone()){
                    positive = ants.get(i).getPheromone();
                }
            }
            else if(ants.get(i).getEnergy() == -1){
                if(negative > ants.get(i).getPheromone()){
                    negative = ants.get(i).getPheromone();
                }
            }
        }
        
        return binarySearch(sender, positive, negative); 
    } 
    
    public static boolean binarySearch(int value, int left, int right){
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
    
    public boolean checkSign(String sign) throws Exception{
        return cypher(sign, auxKey).equals(groupKey);
    }
    
    public String getSign() throws Exception{
        return cypher(groupKey,auxKey);
    }
    
    public static String cypher(String message, String key) throws Exception{
        String cypherMessage = "";
        int counter = 0;
        if(message.length()>=key.length()){
            for(int i=0; i<message.length(); i++){   
                if(i>key.length()){
                    counter = 0;
                }
                if(message.charAt(i) == key.charAt(counter)){
                    cypherMessage = cypherMessage.concat("0");
                }
                else{
                    cypherMessage = cypherMessage.concat("1");
                }
                counter++;
            }
        }
        else{
            throw new Exception("Cypher error: Key is bigger than message");
        }
        return cypherMessage; 
    }
    
}

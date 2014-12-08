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
    //private final int antNumbers;
    //private int positive, negative;
    //private ArrayList<Integer> connections;
    private ArrayList<Integer> clients;
    private static ArrayList<Integer> suspectList;
    //private Expression groupKey;
    private static String groupKey;
    private String auxKey;
    //private Expression auxKey;
    
    public Node(int id){
        this.id = id;
        //this.positive = 0;
        //this.negative = 0;
        //this.antNumbers = 0;
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
        //System.out.println("client " + client + " "+ this.id);
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
    
    public ArrayList<Integer> getSuspect(){
        return suspectList;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getClients() {
        return clients;
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
        
        //int auxP = (int)(Math.ceil(clients.size()/3))+1;
        //System.out.println("AUX "+ auxP);
        //antNumbers = rnd.nextInt(auxP);
        antNumbers = rnd.nextInt((int)(Math.ceil(clients.size()/3))+1)+1;
        //System.out.println("Ants "+ antNumbers);
        //Generate random ants
        while(ants.size()<antNumbers){
            //System.out.println("Aux: "+aux);
            aux = rnd.nextInt(clients.size());
            auxAnt = new Ant();
            if(tempAnts.indexOf(aux) == -1){
                //System.out.println("NOPE");
                auxAnt.setPheromone(clients.get(aux));
                auxAnt.calculateEnergySuspect(sender);
                ants.add(auxAnt);
                tempAnts.add(aux);
            }
        }
        
        //Check sender Id in ruleset
        for(int i=0; i<ants.size(); i++){
            /*System.out.println("Ants: " + ants.size());
            System.out.println("Pheromone: " + ants.get(i).getPheromone());*/
            if(ants.get(i).getEnergy() == 0){
                return "Ok".equals(message);
            }
            else if(ants.get(i).getEnergy() == 1){
                if(positive < clients.indexOf(ants.get(i).getPheromone())){
                    //System.out.println("Why");
                    positive = clients.indexOf(ants.get(i).getPheromone());
                }
            }
            else if(ants.get(i).getEnergy() == -1){
                if(negative > clients.indexOf(ants.get(i).getPheromone())){
                    //System.out.println("Why");
                    negative = clients.indexOf(ants.get(i).getPheromone());
                }
            }
        }
        //System.out.println("Why");
        //System.out.println(positive + " " + negative);
        if(binarySearch(sender, positive, negative)){
        //System.out.println("But it's ok?");
            return "Ok".equals(message);
        }
        return false; 
    } 
    
    public boolean binarySearch(int value, int left, int right){
        int middle;
        if(left > right){
            //System.out.println("False");
            //System.out.println("Value: " + value);
            //System.out.println(clients);
            return false;
        }
        middle = (int) (Math.floor((left+right)/2));
        /*System.out.println("ValueS: " + value);
        System.out.println("Left " + left);
        System.out.println("Right " + right);
        System.out.println("Middle " + middle);
        System.out.println(clients);*/
        if(clients.get(middle) > value){
            //System.out.println(clients.get(middle));
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
        int counter = 0;
        //System.out.println("Aux key: " + key);
        if(message.length()>=key.length()){
            for(int i=0; i<message.length(); i++){   
                if(i>=key.length()){
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
//        System.out.println("Cypher: " + cypherMessage);
//        System.out.println("Groupkey: " + groupKey);
        return groupKey; 
    }
    
}

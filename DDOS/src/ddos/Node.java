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
    private final int antNumbers;
    private int positive, negative;
    //private ArrayList<Integer> connections;
    private ArrayList<Integer> clients;
    private ArrayList<Integer> suspectList;
    
    public Node(int code, int antNumbers){
        this.code = code;
        this.positive = 0;
        this.negative = 0;
        this.antNumbers = antNumbers;
        //this.connections = new ArrayList();  
        this.clients = new ArrayList();
        this.suspectList = new ArrayList();
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }
    
//    public void addConnection(int node){
//        this.connections.add(node);
//    }
    
    public void addSuspect(int suspect){
        this.suspectList.add(suspect);
    }
    
    public int getSuspect(int index){
        return this.suspectList.get(index);
    }

    public int getCode() {
        return code;
    }
    
//    public int checkConnection(int node){
//        return this.connections.indexOf(node);
//    }
    
    public boolean checkMessage(int sender, String message){
        int aux;
        ArrayList<Ant> ants = new ArrayList();
        ArrayList<Integer> tempAnts = new ArrayList();
        //ArrayList<Integer> pheromone = new ArrayList();
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
                    this.positive = ants.get(i).getPheromone();
                }
            }
            else if(ants.get(i).getEnergy() == -1){
                if(negative > ants.get(i).getPheromone()){
                    this.negative = ants.get(i).getPheromone();
                }
            }
        }
        
        return binarySearch(sender, positive, negative); 
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

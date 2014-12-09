/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddos;

import java.util.ArrayList;

/**
 *
 * @author Juancho
 */
public class List {
    ArrayList<Integer> suspects;
    ArrayList<Integer> counter;
    String sign;
    
    public List(){
        this.suspects = new ArrayList();
        this.counter = new ArrayList();
    }
    
    public List(ArrayList<Integer> suspects, String sign){
        this.suspects = suspects;
        this.counter = new ArrayList();
        this.sign = sign;
        
        for(int i=0; i<this.suspects.size(); i++){
            this.counter.add(1);
        }
    }
    
    public void setSign(String sign){
        this.sign = sign;
    }
    
    public String getSign() {
        return sign;
    }
    
    public void addSuspect(ArrayList<Integer> suspects){
        for(int i=0; i<suspects.size(); i++){
            //Add counter to suspect already in the list
            if(this.suspects.indexOf(suspects.get(i)) != -1){
                this.counter.set(this.suspects.indexOf(suspects.get(i)), this.counter.get(this.suspects.indexOf(suspects.get(i)))+1);
            }
            //Add new suspect to the list
            else{
                this.suspects.add(suspects.get(i));
                this.counter.add(1);
            }
        }
    }
    
    public ArrayList<Integer> getSuspects() {
        return suspects;
    }
    
    public int getAttacker(){
        int attacker, countAtt;
        attacker = suspects.get(0);
        countAtt = counter.get(0);
        for(int i=1; i<suspects.size(); i++){
            if(counter.get(i) > countAtt){
                countAtt = counter.get(i);
                attacker = suspects.get(i);
            }
        }
        
        return attacker;
    } 
}

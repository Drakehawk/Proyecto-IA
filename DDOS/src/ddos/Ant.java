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
public class Ant {
    
    //private int energy, positive, negative;
    private int energy;
    private ArrayList<Expression> expressions;
    private ArrayList<Integer> pheromone;
    private ArrayList<String> truthTable;
    
    public Ant(){
        this.energy = 0;
        this.pheromone = new ArrayList();
    }
    
    public Ant(ArrayList<Expression> expressions, ArrayList<String> truthTable){
        this.energy = 0;
        this.expressions = expressions;
        this.truthTable = truthTable;
    }
    
//    public Ant(int maxValues){
//        this.energy = 0;
//        this.positive = 0;
//        this.negative = maxValues;
//        this.pheromone = new ArrayList();
//    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

//    public void setPositive(int positive) {
//        this.positive = positive;
//    }
//
//    public void setNegative(int negative) {
//        this.negative = negative;
//    }

    public void addPheromone(int pheromone) {
        this.pheromone.add(pheromone);
    }

    public int getEnergy() {
        return energy;
    }

    public ArrayList<Integer> getPheromone() {
        return pheromone;
    }
    
    //Temporal solution method for package detection
    public void calculateEnergySuspect (int suspect){
        if(this.pheromone.get(0) == suspect) this.energy = 0;
        
        else if(this.pheromone.get(0) < suspect) this.energy = 1; 
        
        else this.energy = -1;
    }
    
    //Temporal solution method for boolean optimization
    public int calculateEnergyBoolean(ArrayList<Integer> trail){
        ArrayList<Expression> auxExpression = new ArrayList();
        String nodeValue;
        //int counter = 0;
        
        //Get expressions for trail 
        for(int i=0; i<trail.size(); i++){
            auxExpression.add(this.expressions.get(trail.get(i)));
        }
        
        //Evaluate each input in truth table
        for(int i=0; i<this.truthTable.size(); i++){
            nodeValue = this.truthTable.get(i);
            if(evaluate(nodeValue, auxExpression)){
                this.energy++;
            }
        }
   
        return this.energy;
    }
    
    public boolean evaluate(String value, ArrayList<Expression> expressions){
        
        //Expression auxExp;
        Boolean result = false;
        
        for(int i=0; i<expressions.size(); i++){
            //auxExp = expressions.get(i);
            //Calculate OR for expressions. ej: S1S2 + S2S3
            result = result || expressions.get(i).evaluate(value);            
        }
        
        return result;
    }
}

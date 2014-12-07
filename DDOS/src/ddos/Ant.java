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
    private int energy, pheromone;
    private ArrayList<Expression> expressions;
    
    //private ArrayList<Integer> pheromone;
    private ArrayList<Integer> trail;
    private ArrayList<String> truthTable;
    
    public Ant(){
        this.energy = 0;
        this.pheromone = 0;
    }
    
    public Ant(ArrayList<Expression> expressions, ArrayList<String> truthTable, ArrayList<Integer> trail){
        this.energy = 0;
        this.expressions = expressions;
        this.truthTable = truthTable;
        this.trail = trail;
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

    public void setPheromone(int pheromone) {
        this.pheromone = pheromone;
    }
    
//    public void addPheromone(int pheromone) {
//        this.pheromone.add(pheromone);
//    }

    public int getEnergy() {
        return energy;
    }

    public int getPheromone() {
        return pheromone;
    }

//    public ArrayList<Integer> getPheromone() {
//        return pheromone;
//    }

    public ArrayList<Integer> getTrail() {
        return trail;
    }
    
    private boolean checkTrail(Expression expression){
        for(int i=0; i<this.trail.size(); i++){
            if(expression == this.expressions.get(trail.get(i))){
                return false;
            }
        }
        return true;
    }
    
    //Calculate energy and update trail if solution not found
    public int updateTrail(){
        int energyAux = 0;
        int counterTrail = 0;
        int start;
        int end;
        Expression auxExp;
        ArrayList<Integer> auxTrail;
       
        //First evaluation
        if(this.energy == 0){
            this.energy = calculateEnergyBoolean(this.trail);
        }
        //Update trail
        else{
            while(this.energy > energyAux){
                if(counterTrail < this.trail.size()){
                    if(counterTrail == 0){
                        start = 0;
                        end = this.trail.get(1);
                    }
                    if(counterTrail == this.trail.size()-1){
                        start = this.trail.get(this.trail.indexOf(counterTrail) - 1);
                        end = this.expressions.size();
                    }
                    else{
                        start = this.trail.get(this.trail.indexOf(counterTrail) - 1);
                        end = this.trail.get(this.trail.indexOf(counterTrail) + 1);
                    }
                    auxTrail = this.trail;
                    for(int i=start; i<end; i++){
                        //if(auxExp != this.expressions.get(i)){
                        auxExp = this.expressions.get(i);
                        if(checkTrail(auxExp)){
                            auxTrail.set(counterTrail, this.expressions.indexOf(auxExp));
                            energyAux = calculateEnergyBoolean(auxTrail);
                        }
                        if(energyAux > this.energy){
                            this.trail = auxTrail;
                            break;
                        }
                    }
                    counterTrail++;
                }
                //Add new expression
                else{
                    for(int i=0; i<this.trail.size(); i++){
                       if(this.trail.get(i) != i){
                           this.trail.add(i,i);
                           counterTrail = 0;
                           energyAux = calculateEnergyBoolean(this.trail);
                       }
                    }
                }
            }
            this.energy = energyAux;
        }
        return this.energy;
    }
    
    //Temporal solution method for package detection
    public void calculateEnergySuspect (int suspect){
        if(this.pheromone == suspect) this.energy = 0;
        
        else if(this.pheromone < suspect) this.energy = 1; 
        
        else this.energy = -1;
    }
    
    //Temporal solution method for boolean optimization
    public int calculateEnergyBoolean(ArrayList<Integer> trail){
        ArrayList<Expression> auxExpression = new ArrayList();
        String nodeValue;
        int counter = 0;
        
        //Get expressions for trail 
        for(int i=0; i<trail.size(); i++){
            auxExpression.add(this.expressions.get(trail.get(i)));
        }
        
        //Evaluate each input in truth table
        for(int i=0; i<this.truthTable.size(); i++){
            nodeValue = this.truthTable.get(i);
            if(evaluate(nodeValue, auxExpression)){
                counter++;
            }
        }
   
        return counter;
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

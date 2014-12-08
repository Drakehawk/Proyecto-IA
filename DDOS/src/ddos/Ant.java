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
    private int energy, pheromone, nodes;
    private static ArrayList<Expression> expressions;
    
    //private ArrayList<Integer> pheromone;
    private ArrayList<Integer> trail;
    private static ArrayList<String> truthTable;
    
    public Ant(){
        this.energy = 0;
        this.pheromone = 0;
    }
    
    public Ant(ArrayList<Expression> expressions, ArrayList<String> truthTable, ArrayList<Integer> trail, int nodes){
        this.energy = 0;
        this.expressions = expressions;
        this.truthTable = truthTable;
        this.trail = trail;
        this.nodes = nodes;
    }
    
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setPheromone(int pheromone) {
        this.pheromone = pheromone;
    }
    
    public int getEnergy() {
        return energy;
    }

    public int getPheromone() {
        return pheromone;
    }

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
    
    //Temporal solution method for package detection
    public void calculateEnergySuspect (int suspect){
        if(pheromone == suspect) energy = 0;
        
        else if(pheromone < suspect) energy = 1; 
        
        else energy = -1;
    }
    
    //Calculate energy and update trail if solution not found
    public int updateTrail(){
        int energyAux = 0;
        int counterTrail = 0;
        int start, end;
        
        Expression auxExp;
        ArrayList<Integer> auxTrail;
       
        //First evaluation
        if(energy == 0){
            energy = calculateEnergyBoolean(trail);
        }
        //Update trail
        else{
            while(energy > energyAux){
                //System.out.println("TSTS");
                if(counterTrail < trail.size()){
                    //Select trail index to replace in current trail. ej {2,5}
                    //Replaces 2 with 0 or 1 and replaces 5 with 6 or more
                    //First value
                    if(counterTrail == 0){
                        //System.out.println("counter 000 " + counterTrail);
                        start = 0;
                        end = trail.get(1);
                    }
                    else{
                        //Last value
                        if(counterTrail == trail.size()-1){
                            //System.out.println("counter " + counterTrail);
                            //System.out.println("Trail " + trail.size());
                            start = trail.get(counterTrail - 1);
                            end = expressions.size();
                        }
                        //Intermediate value
                        else{
                            //System.out.println("counter " + counterTrail);
                            start = trail.get(counterTrail - 1);
                            end = trail.get(counterTrail + 1);
                        }
                    }
                    auxTrail = trail;
                    for(int i=start; i<end; i++){
                        auxExp = expressions.get(i);
                        if(checkTrail(auxExp)){
                            auxTrail.set(counterTrail, expressions.indexOf(auxExp));
                            energyAux = calculateEnergyBoolean(auxTrail);
                        }
                        if(energyAux > energy){
                            trail = auxTrail;
                            break;
                        }
                    }
                    counterTrail++;
                }
                //Add new expression
                else{
                    energyAux = nodes;
                    /*for(int i=0; i<trail.size(); i++){
                       if(trail.get(i) != i){
                           trail.add(i,i);
                           counterTrail = 0;
                           energyAux = calculateEnergyBoolean(trail);
                       }
                    }*/
                }
            }
            energy = energyAux;
        }
        return energy;
    }
    
    //Temporal solution method for boolean optimization
    public static int calculateEnergyBoolean(ArrayList<Integer> trail){
        ArrayList<Expression> auxExpression = new ArrayList();
        String nodeValue;
        int counter = 0;
        
        //Get expressions for trail 
        for(int i=0; i<trail.size(); i++){
            auxExpression.add(expressions.get(trail.get(i)));
        }
        
        //Evaluate each input in truth table
        for(int i=0; i<truthTable.size(); i++){
            nodeValue = truthTable.get(i);
            if(evaluate(nodeValue, auxExpression)){
                counter++;
            }
        }
   
        return counter;
    }
    
    public static boolean evaluate(String value, ArrayList<Expression> expressions){
        
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

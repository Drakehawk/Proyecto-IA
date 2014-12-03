/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddos;

/**
 *
 * @author Juancho
 */
public class Ant {
    
    private int energy, positive, negative, pheromone;
    
    public Ant(){
        this.energy = 0;
    }
    
    public Ant(int maxValues, int pheromone){
        this.energy = 0;
        this.positive = 0;
        this.negative = maxValues;
        this.pheromone = pheromone;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public void setNegative(int negative) {
        this.negative = negative;
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
    
    //Temporal solution method for package detection
    public void calculateEnergy (int suspect){
        if(this.pheromone == suspect) this.energy = 0;
        
        else if(this.pheromone < suspect) this.energy = 1; 
        
        else this.energy = -1;
    }
}

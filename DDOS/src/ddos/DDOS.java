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
public class DDOS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        int numNodes = 10;
        int numClients = 10;
        int antNumbers = 5;
        int cycles = 100;
        int alarmThreshold = 5;
        double attackF = 0.3;
        
        
        Simulation simulation;
        simulation = new Simulation(numNodes, antNumbers, numClients);
        
        simulation.initalize();
        simulation.simulate(cycles, attackF, alarmThreshold);
        
        
        System.out.println("Packets send: " + simulation.getNumPackets());
        System.out.println("Attacks detected: " +  simulation.getNumAttacks());
        
    }
    
}

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
        //Ants cant be greater than client number
        int numNodes = 100;
        int numClients = 100;
        //int antNumbers = 3;
        int cycles = 15;
        //Minimum 4 alerted nodes
        int alarmThreshold = 4;
        double attackF = 0.2;
        
        
        Simulation simulation;
        simulation = new Simulation(numNodes, numClients);
        
        simulation.initalize();
        simulation.simulate(cycles, attackF, alarmThreshold);
        
        
        System.out.println("Packets send: " + simulation.getNumPackets());
        System.out.println("Attacks send: " +  simulation.getNumAttacks());
        System.out.println("Attacks detected: " +  simulation.getNumAttacksDetected());
        
    }
    
}

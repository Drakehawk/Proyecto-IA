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
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        int numNodes = 100;
        int numClients = 100;
        int cycles = 50;
        int waves = 3;
        
        //Minimum 4 alerted nodes
        int alarmThreshold = 30;
        int mode = 0;
        double attackF = 0.2;
        double attackers = 0.4;
        boolean mutation = false;
        
        Simulation simulation;
        simulation = new Simulation(numNodes, numClients, mode, cycles, attackF, attackers, alarmThreshold, waves, mutation);
        simulation.initalize();
        simulation.simulate();
        
        System.out.println("Mode: " + mode + "\n\n\n ");
        System.out.println("Packets send: " + simulation.getNumPackets());
        System.out.println("Packets received: " + simulation.getNumPacketsReceived());
        System.out.println("Suspect packets send: " +  simulation.getNumSuspects());
        System.out.println("Attacks send: " +  simulation.getNumAttacks());
        System.out.println("Attacks detected: " +  simulation.getNumAttacksDetected());
        System.out.println("Suspects packets received: " +  simulation.getNumSuspectsReceived());
        System.out.println("Attacks dropped: " +  simulation.getNumPacketsDropped());     
        System.out.println("Attacks received: " +  (simulation.getNumAttacks()-simulation.getNumPacketsDropped()));
    }
    
}

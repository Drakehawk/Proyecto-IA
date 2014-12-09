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
public class Server {
    
    private final int id;
    private int suspectCounter;
    private int requestCounter;
    
    public Server(int id){
        this.id = id;
        this.suspectCounter = 0;
        this.requestCounter = 0;
    }

    public int getId() {
        return id;
    }

    public int getSuspectCounter() {
        return suspectCounter;
    }

    public int getRequestCounter() {
        return requestCounter;
    }
    
    public void receivePacket(Packet packet){
        if(packet.getMessage().equals("Bad")){
            suspectCounter++;
        }
        requestCounter++;
    }
}

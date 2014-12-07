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
public class Packet {
    
    private final String message;
    private int sender, receiver;
    
    public Packet(String message, int sender, int receiver){
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public int getSender() {
        return sender;
    }

    public int getReceiver() {
        return receiver;
    }
    
}

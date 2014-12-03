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
    
    private final int id, maxCounter;
    private int attackCounter;
    private int requestCounter;
    
    public Server(int id){
        this.id = id;
        this.maxCounter = 1000;
        this.attackCounter = 0;
        this.requestCounter = 0;
    }

    public int getId() {
        return id;
    }
    
    public void countMessages(String message){
        this.requestCounter++;
        if(message.equals("Bad")){
            this.attackCounter++;
        }
        if(attackCounter == maxCounter){
           //Server dies 
        }
    }
}

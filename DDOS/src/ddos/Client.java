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
public class Client {
    
    private final int id;
    
    
    
    public Client(int id){
        this.id = id;
    }
    
    public String sendMessage(int attack){
        String message = "";
        if(attack == 0){
            message = "Ok";
        }
        else{
            message = "Bad";
        }
        return message;
    }
}

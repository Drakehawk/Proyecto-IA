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
public class Binary {
    
    private final int index, not;
    
    public Binary(int index, int not){
        this.index = index;
        this.not = not;
    }

    public int getIndex() {
        return index;
    }

//    public int getNot() {
//        return not;
//    }
//      
    //1 Equals is negated (¬) 0 otherwise
    public boolean getValue(char value){
        if(this.not == 1){
            if(value == '0'){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(value == '0'){
                return false;
            }
            else{
                return true;
            }
        }
    }
}

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
public class Expression {
    
    private ArrayList<Binary> indexList;
    
    public Expression(ArrayList indexList){
        this.indexList = new ArrayList();
    }

    public ArrayList<Binary> getIndexList() {
        return indexList;
    }
    
    public void addExpression(Binary expression){
        this.indexList.add(expression);
    }
    
//    public Binary getExpression(int index){
//        return this.indexList.get(index);
//    }
    
    
    public boolean evaluate(String value){
        boolean expValue;
        expValue = true;
        
        //AND every value in expression. ej: S1*S2*S3
        for(int i=0; i<this.indexList.size(); i++){
            expValue = this.indexList.get(i).getValue(value.charAt(i)) && expValue;
        }
        
        return expValue;
    }
    
}

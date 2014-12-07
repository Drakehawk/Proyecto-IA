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
    
    public boolean evaluate(String value){
        boolean expValue;
        expValue = true;
        
        //AND every value in expression. ej: S1*S2*S3
        for(int i=0; i<indexList.size(); i++){
            expValue = indexList.get(i).getValue(value.charAt(i)) && expValue;
        }
        
        return expValue;
    }
    
    public String evaluateString(String value){
        String expValue;
        expValue = "";
        
        //Parse expression to binary String with value. ej S1S2S3 => 001
        for(int i=indexList.size()-1; i>=0; i--){
            if(indexList.get(i).getValue(value.charAt(i))){
                expValue = expValue.concat("1");
            }
            else{
                expValue = expValue.concat("0");
            }
        }
        return expValue;
    }    
}

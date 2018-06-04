/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;


/**
 *
 * @author ASUS
 */
public class CellInfo {
    
    
    private int state;
    private ArrayList<Result> arrayList;
    
    public CellInfo(String labelCell, String fisrtIndex, String secondIndex, int state) {
        arrayList = new ArrayList<>();
        this.state = state;
    }

    public ArrayList<Result> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Result> arrayList) {
        this.arrayList = arrayList;
    }

   

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public CellInfo() {
    }

    

    @Override
    public String toString() {
        if(!arrayList.isEmpty()){
        String base =  "<html><br>";// + fisrtIndex + " " + plus + "<br>" + secondIndex +"</html>";
        for(Result re : arrayList){
            base += re.toString() + "<br>";
        }
        return base + "</html>";
        }
        return "";
    }

   
    
    
    
}

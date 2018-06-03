/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author ASUS
 */
public class CellInfo {
    private String labelCell;
    private String fisrtIndex;
    private String secondIndex;
    private String plus;
    private int state;
    
    public CellInfo(String labelCell, String fisrtIndex,String plus, String secondIndex, int state) {
        this.labelCell = labelCell;
        this.fisrtIndex = fisrtIndex;
        this.plus = plus;
        this.secondIndex = secondIndex;
        this.state = state;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public CellInfo() {
    }

    public String getLabelCell() {
        return labelCell;
    }

    public void setLabelCell(String labelCell) {
        this.labelCell = labelCell;
    }

    public String getFisrtIndex() {
        return fisrtIndex;
    }

    public void setFisrtIndex(String fisrtIndex) {
        this.fisrtIndex = fisrtIndex;
    }

    public String getSecondIndex() {
        return secondIndex;
    }

    public void setSecondIndex(String secondIndex) {
        this.secondIndex = secondIndex;
    }

    @Override
    public String toString() {
        return "<html><b>"+ labelCell + "</b><br>" + fisrtIndex + " " + plus + "<br>" + secondIndex +"</html>";
                //"CellInfo{" + "labelCell=" + labelCell + ", fisrtIndex=" + fisrtIndex + ", secondIndex=" + secondIndex + '}';
    }

   
    
    
    
}

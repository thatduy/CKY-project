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
public class Result {
    private String labelCell;
    private String fisrtIndex;
    private String secondIndex;

    public Result(String labelCell, String fisrtIndex, String secondIndex) {
        this.labelCell = labelCell;
        this.fisrtIndex = fisrtIndex;
        this.secondIndex = secondIndex;
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
        if(labelCell.isEmpty() || fisrtIndex.isEmpty()){
            return "<b>" + labelCell + "</b>";
        }
        return "<b>" + labelCell + "</b>"+ "( " + fisrtIndex + " + " + secondIndex + " )" ;
    }
    
    
}

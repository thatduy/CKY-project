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
public class NodeCNF {
    private String ownerName;
    private String firstValue;
    private String secondValue;
    private boolean isNonTerminal;
    private boolean isUsed;

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
    

    public boolean isIsNonTerminal() {
        return isNonTerminal;
    }

    public void setIsNonTerminal(boolean isNonTerminal) {
        this.isNonTerminal = isNonTerminal;
    }
    
    
    public NodeCNF() {
    }

    public NodeCNF(String ownerName, String firstValue, String secondValue, boolean isNonTerminal) {
        this.ownerName = ownerName;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.isNonTerminal = isNonTerminal;
        isUsed = false;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    @Override
    public String toString() {
        return "NodeCNF{" + "ownerName=" + ownerName + ", firstValue=" + firstValue + ", secondValue=" + secondValue + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class TableModel extends AbstractTableModel {
    private ArrayList<String> colunmName;
    private ArrayList<ArrayList<CellInfo>> table;

    public ArrayList<String> getColunmName() {
        return colunmName;
    }

    public void setColunmName(ArrayList<String> colunmName) {
        this.colunmName = colunmName;
    }

    public ArrayList<ArrayList<CellInfo>> getTable() {
        return table;
    }

    public void setTable(ArrayList<ArrayList<CellInfo>> table) {
        this.table = table;
    }

    public TableModel() {
    }
    
    public TableModel(ArrayList<String> colunmName,  ArrayList<ArrayList<CellInfo>> table) {
        this.colunmName = colunmName;
        this.table = table;
        
    }
    
     @Override
    public int getRowCount() {
        return colunmName.size();
    }

    @Override
    public int getColumnCount() {
        return colunmName.size();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue.toString(), rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return table.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return "<html><b>"+ (column + 1) + "</b><br>"+colunmName.get(column)+"</html>";//To change body of generated methods, choose Tools | Templates.
    }
    
    
}

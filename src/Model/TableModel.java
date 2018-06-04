/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class TableModel extends AbstractTableModel {
    private ArrayList<String> colunmName;
    private ArrayList<ArrayList<CellInfo>> table;
   
    public int getState(int row, int col) {
            return table.get(row).get(col).getState();
        }

        public void setState(int row,int col, int state) {
            table.get(row).get(col).setState(state);
        }
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
        //table.get(rowIndex).set(columnIndex, (CellInfo) aValue);
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return table.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return "<html><b>"+ (column + 1) + "</b><br>"+colunmName.get(column)+"</html>";//To change body of generated methods, choose Tools | Templates.
    }
    @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }
    
}

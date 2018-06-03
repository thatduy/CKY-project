/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author ASUS
 */
public class MyEditor extends DefaultCellEditor {

        private JTable table;
        private TableModel model;

        public MyEditor(JTable table) {
            super(new JTextField());
            this.table = table;
            this.model =  (TableModel) table.getModel();
        }

        @Override
        public boolean stopCellEditing() {
            model.setState(table.getEditingRow(),table.getEditingColumn(), -1);
            return super.stopCellEditing();
        }
    }

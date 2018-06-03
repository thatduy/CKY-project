/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ASUS
 */
public class MyRenderer extends DefaultTableCellRenderer {

        Color backgroundColor = getBackground();

    public MyRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }

        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
            TableModel model = (TableModel) table.getModel();
            int state = model.getState(row, column);
            if (state == 0) {
                c.setBackground(Color.green.brighter());
            } else if(state == 1){
                c.setBackground(Color.red.brighter());
            } else if (state == 2){
                 c.setBackground(Color.yellow.brighter());
            }
            else {
                c.setBackground(backgroundColor);
            }
            return c;
        }
    }

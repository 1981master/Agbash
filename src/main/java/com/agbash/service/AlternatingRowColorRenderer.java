
package com.agbash.service;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class AlternatingRowColorRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!isSelected) {
            if (row % 2 == 0) { 
                cell.setBackground(new Color(194, 242, 236)); 
            } else { 
                cell.setBackground(Color.WHITE); 
            }
        } else {
            cell.setBackground(table.getSelectionBackground());
        }
        return cell;
    }
}


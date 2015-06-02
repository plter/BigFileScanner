package com.plter.bfs

import javax.swing.*
import javax.swing.table.TableCellEditor
import javax.swing.table.TableCellRenderer
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * Created by plter on 6/2/15.
 */

class OptCellRender extends AbstractCellEditor implements TableCellRenderer,TableCellEditor {

    public OptCellRender(OnOptCellRenderButtonClickListener l){

        onOptCellRenderButtonClickedListener = l;

        btn = new JButton("打开目录")
        btn.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                if (currentRow>-1&&onOptCellRenderButtonClickedListener){
                    onOptCellRenderButtonClickedListener.onClick(currentRow)
                }
            }
        })
    }


    @Override
    Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return btn
    }


    private JButton btn;

    @Override
    Object getCellEditorValue() {
        return null
    }

    @Override
    Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentRow = row
        return btn
    }

    private def currentRow = -1
    private OnOptCellRenderButtonClickListener onOptCellRenderButtonClickedListener = null;


    public static interface OnOptCellRenderButtonClickListener {
        void onClick(int row);
    }
}

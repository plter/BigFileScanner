package com.plter.bfs

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by plter on 6/1/15.
 */
public class MainFormListModel extends AbstractTableModel {


    @Override
    public int getRowCount() {
        return source.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        File f = source.get(rowIndex);

        switch (columnIndex){
            case 0:
                return f.getAbsolutePath();
            case 1:
                return Math.floor(((double) f.length())/1024/1024*100)/100;
            case 2:
                return "打开目录"
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "文件路径";
            case 1:
                return "大小";
            case 2:
                return "操作"
        }

        return "";
    }

    @Override
    boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex==2
    }

    public void insertFile(File data){
        source.add(0,data);
        fireTableDataChanged();
    }

    public File getFile(int row){
        return source.get(row)
    }

    public void clear(){
        source.clear()
        fireTableDataChanged()
    }

    private List<File> source = new ArrayList<>();
}

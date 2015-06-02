package com.plter.bfs;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.io.File;
import java.io.IOException;

/**
 * Created by plter on 6/1/15.
 */
public class MainForm {
    public JPanel rootContainer;
    private JTable dataTable;
    private JLabel currentFileLabel;
    private JButton btnBrowseFile;
    private JTextField tfDirField;
    private JButton btnClear;

    public MainForm() {

        //init table
        dataTable.setModel(dataModel);
        TableColumn fileSizeColumn = dataTable.getColumnModel().getColumn(1);
        fileSizeColumn.setMaxWidth(100);
        fileSizeColumn.setMinWidth(100);

        TableColumn optColumn = dataTable.getColumnModel().getColumn(2);
        optColumn.setMaxWidth(100);
        optColumn.setMinWidth(100);
        optColumn.setCellRenderer(new OptCellRender(null));
        optColumn.setCellEditor(new OptCellRender(row -> openDir(row)));

        btnClear.addActionListener(e -> clearTable());
        btnBrowseFile.addActionListener(e -> chooseADir());
    }

    private void clearTable() {
        dataModel.clear();
    }

    private void chooseADir(){
        JFileChooser dirC = new JFileChooser();
        dirC.setDialogTitle("选择一个目录");
        dirC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dirC.showOpenDialog(rootContainer);

        if ((rootDir=dirC.getSelectedFile())!=null){
            tfDirField.setText(rootDir.getAbsolutePath());
            startScan();
        }
    }

    private void startScan(){
        if (rootDir!=null){
            new ScanFileThread(rootDir,new ScanFileThread.OnScanFileThreadListener() {
                @Override
                public void foundFle(File f) {
                    currentFileLabel.setText("正在扫描："+f.getName());
                }

                @Override
                public void foundBigFile(File f) {
                    dataModel.insertFile(f);
                }

                @Override
                public void completed() {
                    JOptionPane.showMessageDialog(rootContainer,"扫描完成");
                }
            }).start();
        }else {
            JOptionPane.showMessageDialog(rootContainer,"请先选择目录");
            chooseADir();
        }
    }

    private void openDir(int row){

        try {
            String osName = System.getProperty("os.name");
            String parentFilePath = dataModel.getFile(row).getParentFile().getAbsolutePath();

            if (osName.startsWith("Mac")){
                Runtime.getRuntime().exec("open "+parentFilePath);
            }else if (osName.startsWith("Windows")){
                Runtime.getRuntime().exec("explorer "+parentFilePath);
            }else if (osName.startsWith("Linux")){
                Runtime.getRuntime().exec("nautilus "+parentFilePath);
            }else {
                JOptionPane.showMessageDialog(rootContainer,"您的系统不支持该操作");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootContainer,"您的系统不支持该操作");
            e.printStackTrace();
        }
    }

    private final MainFormListModel dataModel = new MainFormListModel();
    private File rootDir = null;

}

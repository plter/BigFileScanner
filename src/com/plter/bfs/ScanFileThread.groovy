package com.plter.bfs

/**
 * Created by plter on 6/1/15.
 */
class ScanFileThread extends Thread{

    public ScanFileThread(File rootDir,OnScanFileThreadListener l){
        _onScanFileThreadListener = l;
        this.rootDir = rootDir
    }

    @Override
    public void run() {
        super.run()

        findInDir(rootDir)

        if (_onScanFileThreadListener){
            _onScanFileThreadListener.completed();
        }
    }

    public void findInDir(File dir){

        for (File f:dir.listFiles()){
            if (f.isFile()){
                String path = f.getAbsolutePath();


                if (_onScanFileThreadListener){
                    _onScanFileThreadListener.foundFle(f)

                    if (f.length()>1024*1024*10) {
                        _onScanFileThreadListener.foundBigFile(f);
                    }
                }
            }else if (f.isDirectory()){
                findInDir(f)
            }
        }
    }

    private File rootDir = null;

    private OnScanFileThreadListener _onScanFileThreadListener = null;

    public static interface OnScanFileThreadListener {

        void foundFle(File f);
        void foundBigFile(File f);
        void completed();
    }
}

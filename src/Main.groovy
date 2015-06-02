import com.plter.bfs.MainForm

import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.UnsupportedLookAndFeelException
import javax.swing.plaf.metal.MetalLookAndFeel

/**
 * Created by plter on 6/1/15.
 */
public class Main {

    public static void main(def args){
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        def win = new JFrame("大文件扫描工具");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        win.setSize(800,500)
        win.setContentPane(new MainForm().rootContainer)
        win.setVisible(true)
    }

}

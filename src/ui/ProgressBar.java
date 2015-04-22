package ui;

import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * 加载进度条
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class ProgressBar extends JDialog{
    private JPanel pane;
    private JProgressBar progressBar;
    private JLabel label;
    //------------------------------------------
    public ProgressBar() {
    	this.setModal(true);
    	pane = new JPanel();
    	progressBar = new JProgressBar();
    	label = new JLabel("正在加载数据,请稍候……");
    	progressBar.setIndeterminate(true);
    	pane.add(progressBar);
    	pane.add(label);
    	pane.addComponentListener(new ComponentAdapter() {
    		public void componentResized(ComponentEvent e) {
            	progressBar.setBounds(20, 20, 350, 15);
                label.setBounds(20, 50, 350, 25);
            }
        });
    	this.getContentPane().add(pane);
    	this.setUndecorated(true);
        this.setSize(390, 100);
        this.setLocationRelativeTo((Frame)null);
    	this.setVisible(true);
    }
}

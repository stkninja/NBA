package ui;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Point;
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
	private Thread thread;
    private JPanel pane;
    private JProgressBar progressBar;
    private JLabel label;
	//拖动
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
    //------------------------------------------
    public ProgressBar(Thread thread, String msg) {
    	this.thread = thread;
    	this.setModal(true);
    	pane = new JPanel();
    	progressBar = new JProgressBar();
    	label = new JLabel(msg);
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
        this.setDragable();
        this.setAlwaysOnTop(true);
        this.startThread();
    	this.setVisible(true);
    }
    /**
     * 启动线程
     */
    private void startThread() {
    	new Thread() {
    		public void run() {
    			try {
        			thread.start();
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					ProgressBar.this.dispose();
				}
    		}
    	}.start();
    }
    /**
	 * 设置界面可拖动
	 */
	private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               ProgressBar.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               ProgressBar.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(ProgressBar.this.getLocation().x + e.getX() - tmp.x,
                		   ProgressBar.this.getLocation().y + e.getY() - tmp.y);
                   ProgressBar.this.setLocation(loc);
               }
            }
        });
	}
}

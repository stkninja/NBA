package ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 进入界面
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class Login extends JFrame {
	private JPanel contentPane;//总panel
	private ImageIcon background;//背景图片
	//退出、最小化按钮
	private JPanel top;
	private JButton exit;
	private JButton mini;
	//内容panel
	private JPanel pane;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	//拖动
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	//----------------------------------------------------------------------------
	public Login() {
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height / 2;
		int frameWidth = frameHeight;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//背景
		background = new ImageIcon("data/pic/background.jpg");
		contentPane = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		//退出，最小化按钮
		top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		top.setOpaque(false);
		
		exit = new JButton();
        exit.setSize(new Dimension(25, 25));
        exit.setPreferredSize(new Dimension(25, 25));
		this.setIcon(exit, "data/pic/exit1.png", "data/pic/exit2.png");
		mini = new JButton();
		mini.setSize(new Dimension(25, 25));
		mini.setPreferredSize(new Dimension(25, 25));
		this.setIcon(mini, "data/pic/mini1.png", "data/pic/mini2.png");
		
		top.add(mini);
		top.add(exit);
		contentPane.add(top, BorderLayout.NORTH);
		//内容panel
		pane = new JPanel(new GridLayout(2, 2, 10, 10));
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
		button1 = new JButton();
		button1.setSize(150, 150);
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		this.setIcon(button1, "data/pic/NotFound.jpg", "data/pic/NotFound.jpg");
//		this.setIcon(button2, "data/pic/NotFound.jpg", "data/pic/NotFound.jpg");
//		this.setIcon(button3, "data/pic/NotFound.jpg", "data/pic/NotFound.jpg");
//		this.setIcon(button4, "data/pic/NotFound.jpg", "data/pic/NotFound.jpg");
		pane.add(button1);
		pane.add(button2);
		pane.add(button3);
		pane.add(button4);
		contentPane.add(pane);
		//监听
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		//----------------------------------------------------
		this.setDragable();
		this.setUndecorated(true);
		this.setVisible(true);
	}
	/**
	 * 设置图标
	 * @param button 图标
	 * @param file1 默认图标路径
	 * @param file2 翻转图标路径
	 */
	public void setIcon(JButton button, String file1, String file2) {  
        Image icon1 = (new ImageIcon(file1)).getImage();
        int scale = icon1.getWidth(null) / icon1.getHeight(null);
		Image temp1 = icon1.getScaledInstance(button.getHeight() * scale, button.getHeight(), Image.SCALE_DEFAULT);
		ImageIcon icon2 = new ImageIcon(file2);
		Image temp2 = icon2.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(temp1));
		button.setRolloverIcon(new ImageIcon(temp2));
		button.setPressedIcon(new ImageIcon(temp2));
		button.setFocusPainted(false);//无选择效果
        button.setOpaque(false);//透明
		button.setContentAreaFilled(false);//填充
		button.setBorderPainted(false);//无边框
		button.setMargin(new Insets(0, 0, 0, 0));//无边距
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//指针变手
	}
	/**
	 * 设置界面可拖动
	 */
	private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               Login.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               Login.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(Login.this.getLocation().x + e.getX() - tmp.x,
                		   Login.this.getLocation().y + e.getY() - tmp.y);
                   Login.this.setLocation(loc);
               }
            }
        });
	}
	//------------------------------------------------------------
	public static void main(String[] args) {
		new Login();
	}
}

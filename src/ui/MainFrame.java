package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import data.rwArrangedFiles.WritePOs;

/**
 * 
 * @author stk
 * NBA查询平台主界面
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	private JPanel contentPane;//总panel
	private JPanel pane;//内容panel
	private ImageIcon background;//背景图片
	//退出、最大最小化按钮
	private JPanel top;
	private JButton exit;
	private JButton mini;
	//导航
	private JPanel navigation;
	private JButton team;
	private JButton player;
	//拖动
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	//--------------------------------------------------------
	public MainFrame() {
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 3 / 4;
		int frameWidth = frameHeight * 9 / 5;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//背景
		background = new ImageIcon("data/pic/TeamFrame_background.jpg");
		contentPane = new JPanel(new BorderLayout()) {
//			public void paint(Graphics g) {
//				super.paint(g);
//		        RoundRectangle2D rect = new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),70,70);
//		        g.setClip(rect);
//		        if(background!=null)
//		            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
//		        revalidate();
//			}
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = background.getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), background.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		//导航
		navigation = new JPanel();
		navigation.setOpaque(false);
		navigation.setLayout(new BoxLayout(navigation, BoxLayout.Y_AXIS));
		navigation.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 10));
		
		team = new JButton("球队");
		player = new JButton("球员");
		navigation.add(team);
		navigation.add(Box.createVerticalStrut(20));
		navigation.add(player);
		contentPane.add(navigation, BorderLayout.WEST);
		//按钮
		top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		top.setOpaque(false);
		
		exit = new JButton();
		exit.setSize(new Dimension(25, 25));
		exit.setPreferredSize(new Dimension(25, 25));
		this.setIcon("data/pic/exit.jpg", exit);
		exit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		exit.addMouseListener(new MouseAdapter() {  
		    public void mouseEntered(MouseEvent e) {  
		    	exit.setBorder(new Border() {
					public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
						g.setColor(Color.gray);  
		                g.drawRect(x, y, width - 1, height - 1);
					}
					public boolean isBorderOpaque() {
						return true;
					}
					public Insets getBorderInsets(Component c) {
						return new Insets(1, 1, 1, 1);
					}
				});
		    }
		    public void mouseExited(MouseEvent e) {
		    	exit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		    }  
		});
		
		mini = new JButton();
		mini.setSize(new Dimension(25, 25));
		mini.setPreferredSize(new Dimension(25, 25));
		this.setIcon("data/pic/mini.jpg", mini);
		mini.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		mini.addMouseListener(new MouseAdapter() {  
		    public void mouseEntered(MouseEvent e) {  
		    	mini.setBorder(new Border() {
					public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
						g.setColor(Color.gray);  
		                g.drawRect(x, y, width - 1, height - 1);
					}
					public boolean isBorderOpaque() {
						return true;
					}
					public Insets getBorderInsets(Component c) {
						return new Insets(1, 1, 1, 1);
					}
				});
		    }
		    public void mouseExited(MouseEvent e) {
		    	mini.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		    }  
		});
		
		top.add(mini);
		top.add(exit);
		contentPane.add(top, BorderLayout.NORTH);
		//内容panel
		pane = new TeamPane();
		contentPane.add(pane, BorderLayout.CENTER);
		//监听
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		team.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.remove(pane);
				pane = new TeamPane();
				MainFrame.this.getContentPane().add(pane);
				revalidate();
			}
		});
		player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.remove(pane);
				pane = new PlayerPane();
				MainFrame.this.getContentPane().add(pane);
				revalidate();
			}
		});
		//------------------------------------------------------------------
		this.setTitle("NBA查询平台");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDragable();
		this.setUndecorated(true);
		this.setVisible(true);
	}
	/**
	 * 设置图标背景
	 * @param file 图标路径
	 * @param iconButton 按钮
	 */
	public void setIcon(String file, JButton iconButton) {  
        ImageIcon icon = new ImageIcon(file);  
        @SuppressWarnings("static-access")
		Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(), iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);  
        icon = new ImageIcon(temp);  
        iconButton.setIcon(icon);  
	}
	/**
	 * 设置界面可拖动
	 */
	private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               MainFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               MainFrame.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(MainFrame.this.getLocation().x + e.getX() - tmp.x,
                		   MainFrame.this.getLocation().y + e.getY() - tmp.y);
                   MainFrame.this.setLocation(loc);
               }
            }
        });
	}
	//------------------------------------------------------------
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		WritePOs.writePOs();
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new MainFrame();
	}
}

package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.apache.batik.transcoder.TranscoderException;

import vo.TeamBasicInfoVO;

public class TeamFrame extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571378058437970468L;
	JPanel panel;   //背景panel
	JPanel panel1;  //图片panel
	JPanel panel2;  //信息panel
	JPanel panel3;  //按钮panel
	JButton exit;  //关闭按钮
	JLabel fullName;  //球队
	JLabel abbName;  //球队缩写
	JLabel location;  //所在地
	JLabel competionArea;  //赛区
	JLabel subArea;  //分区
	JLabel homeGround;  //主场
	JLabel setupTime;  //建立时间
	JLabel getfullName;  //球队
	JLabel getabbName;  //球队缩写
	JLabel getlocation;  //所在地
	JLabel getcompetionArea;  //赛区
	JLabel getsubArea;  //分区
	JLabel gethomeGround;  //主场
	JLabel getsetupTime;  //建立时间
	ImageIcon bg;  //背景图
    JLabel lab;  //背景
    Image logo;  //队标
    ImageIcon logoicon;
	File logofile;
	
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	public TeamFrame (TeamBasicInfoVO vo) throws IOException, TranscoderException{
		//定义界面大小
		
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 2 / 3;
		int frameWidth = frameHeight * 5 / 4;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		
		//背景图片
		bg = new ImageIcon("data/pic/Yellow.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		//肖像照panel
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setOpaque(false);
		logofile = new File("logofile");
		logofile.createNewFile();
		SvgUtil.convertSvgFile2Png(vo.teamLogo, logofile);
		logo = ImageIO.read(logofile);
		logoicon = new ImageIcon(logo);
		logoicon.setImage(logoicon.getImage().getScaledInstance(280,280,Image.SCALE_DEFAULT));
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(logoicon);
		panel1.add(Pic1,BorderLayout.NORTH);
		
		//文字信息panel
		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		panel2.setLayout(new GridLayout(7,2,0,20));
		panel2.setOpaque(false);
		fullName = new JLabel("球队名(fullName):",JLabel.RIGHT);
		abbName = new JLabel("队名缩写(abbName):",JLabel.RIGHT);
		location = new JLabel("所在地(location):",JLabel.RIGHT);
		competionArea = new JLabel("赛区(competionArea):",JLabel.RIGHT);
		subArea = new JLabel("分区(subArea):",JLabel.RIGHT);
		homeGround = new JLabel("主场馆(homeGround):",JLabel.RIGHT);
		setupTime = new JLabel("建队时间(setupTime):",JLabel.RIGHT);
		Font f1 = new Font("宋体",Font.BOLD,14);
		fullName.setFont(f1);
		abbName.setFont(f1);
		location.setFont(f1);
		competionArea.setFont(f1);
		subArea.setFont(f1);
		homeGround.setFont(f1);
		setupTime.setFont(f1);
		
		Font f2 = new Font("宋体",Font.BOLD,16);
		getfullName = new JLabel(vo.fullName,JLabel.CENTER);
		getabbName = new JLabel(vo.abbName,JLabel.CENTER);
		getlocation = new JLabel(vo.location,JLabel.CENTER);
		getcompetionArea = new JLabel(vo.competionArea,JLabel.CENTER);
		getsubArea = new JLabel(vo.subArea,JLabel.CENTER);
		gethomeGround = new JLabel(vo.homeGround,JLabel.CENTER);
		getsetupTime = new JLabel(vo.setupTime,JLabel.CENTER);
		getfullName.setFont(f2);
		getabbName.setFont(f2);
		getlocation.setFont(f2);
		getcompetionArea.setFont(f2);
		getsubArea.setFont(f2);
		gethomeGround.setFont(f2);
		getsetupTime.setFont(f2);
		panel2.add(fullName);
		panel2.add(getfullName);
		panel2.add(abbName);
		panel2.add(getabbName);
		panel2.add(location);
		panel2.add(getlocation);
		panel2.add(competionArea);
		panel2.add(getcompetionArea);
		panel2.add(subArea);
		panel2.add(getsubArea);
		panel2.add(homeGround);
		panel2.add(gethomeGround);
		panel2.add(setupTime);
		panel2.add(getsetupTime);
		//---------------------------------------
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel3.setOpaque(false);
		exit = new JButton();
		exit.setSize(new Dimension(25, 25));
		exit.setOpaque(false);
		exit.setPreferredSize(new Dimension(25, 25));
		this.setIcon("data/pic/exit.png", exit);
		exit.setFocusPainted(false);
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
		panel3.add(exit);
		exit.addActionListener(new ExitListener());
		//---------------------------------
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panel2,BorderLayout.CENTER);
		panel.add(panel1,BorderLayout.WEST);
		panel.add(panel3,BorderLayout.NORTH);
		this.setContentPane(panel);
		panel.setOpaque(false);
		this.setDragable();
		this.setUndecorated(true);
		this.setVisible(true);
	}
	public void setIcon(String file, JButton iconButton) {  
        ImageIcon icon = new ImageIcon(file);  
        @SuppressWarnings("static-access")
		Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(), iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);  
        icon = new ImageIcon(temp);  
        iconButton.setIcon(icon);  
	}
	private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               TeamFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               TeamFrame.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(TeamFrame.this.getLocation().x + e.getX() - tmp.x,
                		   TeamFrame.this.getLocation().y + e.getY() - tmp.y);
                   TeamFrame.this.setLocation(loc);
               }
            }
        });
	}
//------------------------------------------------------------
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			 dispose();
		}
	}


}

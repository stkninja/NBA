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
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import vo.PlayerBasicInfoVO;

public class PlayerFrame extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;   //背景panel
	JPanel panel1;  //图片panel
	JPanel panel2;  //信息panel
	JPanel panel3;  //按钮panel
	JPanel subpanel;
	JButton exit;  //关闭按钮
	JLabel name;  
	JLabel getName;
	JLabel age;
	JLabel getAge;
	JLabel position;
	JLabel getPosition;
	JLabel height;
	JLabel getHeight;
	JLabel weight;
	JLabel getWeight;
    JLabel number;
    JLabel getNumber;
    JLabel exp;
    JLabel getExp;
    JLabel school;
    JLabel getSchool;
    JLabel team;
    JLabel getTeam;
    JLabel actionlabel;
    JLabel birth;
    JLabel getbirth;
    ImageIcon bg;  //背景图
    JLabel lab;  //背景
    Image portrait; //肖像图
    ImageIcon portraiticon;
	Image action;  //动作图
	ImageIcon actionicon;
	ImageIcon actionicon1;
	JFrame playeraction;
	
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
    
	public PlayerFrame (PlayerBasicInfoVO vo) throws IOException{
		
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 2 / 3;
		int frameWidth = frameHeight * 1;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//背景图片
		bg = new ImageIcon("data/pic/Yellow.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		//肖像照panel-----------------------------------------------------------
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,0,20));
		panel1.setOpaque(false);
		try{
			portrait=ImageIO.read(vo.portrait);
		}
		catch (Exception ex){
			
		}
		
		portraiticon=new ImageIcon(portrait);
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(portraiticon);
		panel1.add(Pic1);
		try{
			action = ImageIO.read(vo.action);
			actionicon = new ImageIcon(action);
			actionicon1 = new ImageIcon(action);	   
		}
        catch (Exception ex){
			actionicon = new ImageIcon("data/pic/error.jpg");
			actionicon1 = new ImageIcon("data/pic/error.jpg");
		}
		actionicon.setImage(actionicon.getImage().getScaledInstance(100,200,Image.SCALE_DEFAULT));
		JLabel Pic2 = new JLabel();
		Pic2.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				playeraction = new PlayerPicture(actionicon1);
			}
			public void mouseExited(MouseEvent e){
				playeraction.dispose();
			}
		});
		Pic2.setIcon(actionicon);
		subpanel = new JPanel();
		Font f = new Font("宋体",Font.BOLD,14);
		actionlabel = new JLabel("动作图(Action):",JLabel.RIGHT);
		actionlabel.setFont(f);
		subpanel.setLayout(new BorderLayout());
		subpanel.add(actionlabel, BorderLayout.CENTER);
		subpanel.add(Pic2, BorderLayout.EAST);
		subpanel.setOpaque(false);
		panel1.add(subpanel);
		
		//文字信息panel----------------------------------------
		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		panel2.setLayout(new GridLayout(9,2,0,0));
		panel2.setOpaque(false);
		Font f1 = new Font("宋体",Font.BOLD,14);
		name = new JLabel("姓名(Name):", JLabel.RIGHT);
		age = new JLabel("年龄(Age):", JLabel.RIGHT);
		position = new JLabel("位置(Position):", JLabel.RIGHT);
		height = new JLabel("身高(Height):", JLabel.RIGHT);
		weight = new JLabel("体重(Weight):", JLabel.RIGHT);
		number = new JLabel("号码(Number):", JLabel.RIGHT);
		exp = new JLabel("经验(Exp):", JLabel.RIGHT);
		school = new JLabel("毕业学校(School):", JLabel.RIGHT);
		birth = new JLabel("出生日期(Birth):", JLabel.RIGHT);
		
		birth.setFont(f1);
		name.setFont(f1);
		age.setFont(f1);
		position.setFont(f1);
		height.setFont(f1);
		weight.setFont(f1);
		number.setFont(f1);
		exp.setFont(f1);
		school.setFont(f1);
		Font f2 = new Font("宋体",Font.BOLD,12);

		getName = new JLabel(vo.name, JLabel.CENTER);
		getAge = new JLabel(vo.age, JLabel.CENTER);
		getPosition = new JLabel(vo.position, JLabel.CENTER);
		getHeight = new JLabel(vo.height, JLabel.CENTER);
		getWeight = new JLabel(vo.weight, JLabel.CENTER);
		getNumber = new JLabel(vo.number, JLabel.CENTER);
		getExp = new JLabel(vo.exp, JLabel.CENTER);
		getSchool = new JLabel(vo.school, JLabel.CENTER);
		getbirth = new JLabel(vo.birth, JLabel.CENTER);
		getName.setFont(f2);
		getAge.setFont(f2);
		getPosition.setFont(f2);
		getHeight.setFont(f2);
		getWeight.setFont(f2);
		getNumber.setFont(f2);
		getExp.setFont(f2);
		getSchool.setFont(f2);
		getbirth.setFont(f2);
		panel2.add(name);
		panel2.add(getName);
		panel2.add(number);
		panel2.add(getNumber);
		panel2.add(position);
		panel2.add(getPosition);
		panel2.add(height);
		panel2.add(getHeight);
		panel2.add(weight);
		panel2.add(getWeight);
		panel2.add(age);
		panel2.add(getAge);
		panel2.add(birth);
		panel2.add(getbirth);
		panel2.add(exp);
        panel2.add(getExp);
		panel2.add(school);
		panel2.add(getSchool);
		//----------------------------------------
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel3.setOpaque(false);
		exit = new JButton();
		exit.setSize(new Dimension(25, 25));
		exit.setOpaque(false);
		exit.setFocusPainted(false);
		exit.setPreferredSize(new Dimension(25, 25));
		this.setIcon("data/pic/exit.png", exit);
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
		//-----------------------------------------
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panel1,BorderLayout.WEST);
		panel.add(panel2,BorderLayout.CENTER);
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
               PlayerFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               PlayerFrame.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(PlayerFrame.this.getLocation().x + e.getX() - tmp.x,
                		   PlayerFrame.this.getLocation().y + e.getY() - tmp.y);
                   PlayerFrame.this.setLocation(loc);
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


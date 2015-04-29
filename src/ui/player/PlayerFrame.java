package ui.player;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.batik.transcoder.TranscoderException;

import ui.MainFrame;
import ui.PositionEnum;
import ui.SvgUtil;
import ui.TeamEnum;
import ui.match.MatchFrame;
import ui.team.TeamFrame;
import vo.MatchVO;
import vo.PlayerBasicInfoVO;
import vo.PlayerVO;
import businesslogic.MatchBL;
import businesslogic.PlayerBL;
import businesslogic.TeamBL;
import businesslogicservice.MatchBLService;
import businesslogicservice.PlayerBLService;
import businesslogicservice.TeamBLService;

@SuppressWarnings("serial")
public class PlayerFrame extends JFrame{
	PlayerBLService bl;
	MatchBLService mbl;
	TeamBLService tbl;
	JPanel panel;   //背景panel
	JPanel panelA;  //按钮panel
	JPanel panelA1;
	JPanel panelA2;
	
	JPanel panelB;  //数据panel
	JPanel panelB1;
	
	JPanel panel1;  //基本数据panel
	JPanel subpanel1;
	JPanel subpanel2;
	JPanel subpanel22;
	JPanel subpanel21;
	
	JPanel panel2;  //赛季战绩panel
	JPanel subpanel4;
	JPanel subpanel41;
	JPanel subpanel5;
	
	JPanel container; //容器
	CardLayout card;
	
	
	JPanel panel3;  //生涯统计
	JPanel subpanel3;
	JPanel subpanel31;
	JPanel subpanel6;
	
	JPanel panel4; //标签
	
	JButton exit;  //关闭按钮
	JButton button1;
	JButton button2;
	
	JLabel name;  
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
    JLabel actionlabel;
    JLabel birth;
    JLabel getbirth;
    JLabel recentTitle;
    JLabel historyTitle;
    JLabel type;
    
    JLabel point;
    JLabel getpoint;
    JLabel assist;
    JLabel getassist;
    JLabel rebound;
    JLabel getrebound;
    
    JLabel promotion;
    JLabel scorep;
    JLabel assistp;
    JLabel reboundp;
    
    JComboBox<String> mode;
    
    JTable table;
    JScrollPane sp;
    JTable historytable;
    JScrollPane hsp;
    
    ImageIcon bg;  //背景图
    JLabel lab;  //背景
    Image portrait; //肖像图
    ImageIcon portraiticon;
	Image action;  //动作图
	ImageIcon actionicon;
	ImageIcon actionicon1;
	JFrame playeraction;
	
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	
	CareerData careerdata;
	RecentMatch recentmatch;
	
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	
	Object[][] data;
	
	Image logo;  //队标
	ImageIcon logoicon;
    File logofile;
	
    String abbname;
	public PlayerFrame(PlayerBasicInfoVO vo,MainFrame mf) throws IOException, TranscoderException{
		bl = new PlayerBL();
		mbl = new MatchBL();
		tbl = new TeamBL();
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 12 / 16;
		int frameWidth = frameHeight * 21 / 16;
		this.setBounds((screenSize.width - frameWidth)* 2 / 3, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//背景图片
		bg = new ImageIcon("data/pic/playerframe.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		//panel1基本数据----------
		panel1(vo, mf);	
		
		//panel2近五场比赛panel-----------------------------------------------
		panel2(vo, mf);
		
	    //panel3历史数据-------------------------------------------
		panel3(vo, mf);
		
		//container-----------------------------------------------
		card = new CardLayout();
		container = new JPanel(card);
		container.setOpaque(false);
		container.add(panel2,"1");
		container.add(panel3,"2");
		
		
		//数据panel--------------------------------------------------
		panelB = new JPanel();
		panelB.setLayout(new BorderLayout());
		panelB.setOpaque(false);
		
		panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4,BoxLayout.X_AXIS));
		panel4.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		panel4.setOpaque(false);
		button1 = new JButton("近五场比赛");
		button2 = new JButton("历史数据");
		panel4.add(button1);
		panel4.add(button2);
		
		panelB1 = new JPanel();
		panelB1.setLayout(new BorderLayout());
		panelB1.setOpaque(false);
		panelB1.add(panel4,BorderLayout.NORTH);
		panelB1.add(container,BorderLayout.CENTER);
		
		panelB.add(panel1,BorderLayout.NORTH);
		panelB.add(panelB1,BorderLayout.CENTER);
		
	
		
		//退出按钮panel----------------------------------------
		panelA = new JPanel();
		panelA.setLayout(new GridLayout(1,2));
		
		panelA1 = new JPanel();
		panelA1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelA1.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		panelA2 = new JPanel();
		panelA2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelA2.setOpaque(false);
		panelA1.setOpaque(false);
		panelA.setOpaque(false);
		panelA.add(panelA1);
		panelA.add(panelA2);
		Font f = new Font("宋体",Font.BOLD,22);
		name = new JLabel(vo.name);
		name.setFont(f);
		panelA1.add(name);
		exit = new JButton();
		exit.setFocusPainted(false);
		exit.setSize(new Dimension(25, 25));
	    exit.setPreferredSize(new Dimension(25, 25));
	    this.setIcon(exit, "data/pic/exit1.png", "data/pic/exit2.png");
		panelA2.add(exit);
		exit.addActionListener(new ExitListener());
		
		//标签监听-----------------------------------
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				card.show(container, "1");
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				card.show(container, "2");
			}
		});
		
		
		//-----------------------------------------
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panelB,BorderLayout.CENTER);
		panel.add(panelA,BorderLayout.NORTH);
		
		this.setContentPane(panel);
		panel.setOpaque(false);
		this.setDragable();
		this.setUndecorated(true);
		this.setVisible(true);
	}

	/**
	 * @param vo
	 * @param pp
	 * @throws IOException
	 */
	private void panel3(PlayerBasicInfoVO vo, MainFrame mf) throws IOException {
		subpanel31 = new JPanel();
		subpanel31.setLayout(new FlowLayout(FlowLayout.LEFT));
		subpanel31.setOpaque(false);
		mode = new JComboBox<String>(new String[]{"场均","总数" });
		mode.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setHistoryData(vo.name);
				}
				});
		type = new JLabel("         数据类型: ");
		historyTitle = new JLabel("生涯统计:");
		historyTitle.setFont(new Font("宋体",Font.BOLD,15));
        subpanel31.add(historyTitle);
		subpanel31.add(type);
		subpanel31.add(mode);
		
		historytable = new JTable();
		hsp = new JScrollPane();
		hsp.setViewportView(historytable);
		this.setHistoryData(vo.name);
		
		//表格监听
		historytable.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		String season = ((String)historytable.getValueAt(historytable.getSelectedRow(), 0));
	    		if(season.equals("生涯总计")){
	    			mf.getPlayerMatch("All", vo.name);
	    		}
	    		else{
	    			mf.getPlayerMatch(season, vo.name);
	    		}
	    		
	    		mf.toFront();
	    		
	    	}
	    });
		historytable.addMouseMotionListener(new MouseAdapter() {
	    	public void mouseMoved(MouseEvent e) {
	    		historytable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变手
		    }
	    });
		subpanel3 = new JPanel();
		subpanel3.setLayout(new BorderLayout());
		subpanel3.setOpaque(false);
		subpanel3.add(subpanel31,BorderLayout.NORTH);
		subpanel3.add(hsp, BorderLayout.CENTER);
		
		subpanel6 = new JPanel();
		subpanel6.setLayout(new GridLayout(1,2));
		subpanel6.setOpaque(false);
		careerdata = new CareerData(vo.name);
		label3 = new JLabel();
		label4 = new JLabel();
		label3.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/8.jpg"))));
		label4.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/9.jpg"))));
		subpanel6.add(label3);
		subpanel6.add(label4);
		
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(2,1));
		panel3.setOpaque(false);
        panel3.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		
		panel3.add(subpanel3);
		panel3.add(subpanel6);
	}

	/**
	 * @param vo
	 * @param pp
	 * @throws IOException
	 */
	private void panel2(PlayerBasicInfoVO vo, MainFrame mf) throws IOException {
		panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setLayout(new GridLayout(2,1));
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		subpanel41 = new JPanel();
		subpanel41.setOpaque(false);
		subpanel41.setLayout(new FlowLayout(FlowLayout.LEFT));
		recentTitle = new JLabel("最近5场比赛统计:");
		recentTitle.setFont(new Font("宋体",Font.BOLD,15));
		promotion = new JLabel("       提升率->   ");
		promotion.setFont(new Font("宋体",Font.BOLD,12));
		scorep = new JLabel(bl.getPlayerVO(vo.name).pointpromotion+"(场均得分)； ");
		assistp = new JLabel(bl.getPlayerVO(vo.name).assistpromotion+"(场均助攻)；");
		reboundp = new JLabel(bl.getPlayerVO(vo.name).reboundpromotion+"(场均篮板)");
		scorep.setForeground(Color.RED);
		assistp.setForeground(Color.RED);
		reboundp.setForeground(Color.RED);
		subpanel41.add(recentTitle);
		subpanel41.add(promotion);
		subpanel41.add(scorep);
		subpanel41.add(assistp);
		subpanel41.add(reboundp);
		
		table = new JTable();
		sp = new JScrollPane(table);
		sp.setOpaque(false);
		this.setData(vo.name);
		 //表格监听
	    table.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		String season = ((String)table.getValueAt(table.getSelectedRow(), 0)).substring(0, 5);
	    		String date = ((String)table.getValueAt(table.getSelectedRow(), 0)).substring(6);
	    		String team = ((String)table.getValueAt(table.getSelectedRow(), 1)).substring(0, 3);
	    		ArrayList<MatchVO> list = mbl.getMatchesAboutTeamSeasonDatePlayer(team, season, date, "All");
	    		SwingUtilities.invokeLater(new Runnable() {
					@SuppressWarnings("restriction")
					public void run() {
						try {
							JFrame.setDefaultLookAndFeelDecorated(true);
							MatchFrame frame = new MatchFrame(list.get(0),mf);
							com.sun.awt.AWTUtilities.setWindowOpacity(frame, 0.9f);//设置透明度
							com.sun.awt.AWTUtilities.setWindowShape(frame, new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));//设置圆角
						} catch (IOException | TranscoderException e) {
							e.printStackTrace();
						}
					}
			    });
	    	}
	    });
	    table.addMouseMotionListener(new MouseAdapter() {
	    	public void mouseMoved(MouseEvent e) {
	    		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变手
		    }
	    });
	    subpanel4 = new JPanel();
	    subpanel4.setLayout(new BorderLayout());
	    subpanel4.setOpaque(false);
	    subpanel4.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
	    subpanel4.add(subpanel41, BorderLayout.NORTH);
	    subpanel4.add(sp,BorderLayout.CENTER);
	    
	    subpanel5 = new JPanel();
	    subpanel5.setLayout(new GridLayout(1,2));
	    subpanel5.setOpaque(false);
	    recentmatch = new RecentMatch(data);
		label1 = new JLabel();
		label2 = new JLabel();
		label1.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/10.jpg"))));
		label2.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/11.jpg"))));
		subpanel5.add(label1);
		subpanel5.add(label2);
	    
		panel2.add(subpanel4);
		panel2.add(subpanel5);
	}

	/**
	 * @param vo
	 * @throws IOException
	 * @throws TranscoderException
	 */
	private void panel1(PlayerBasicInfoVO vo, MainFrame mf) throws IOException,
			TranscoderException {
		//基本数据panel-----------------------------------------------------------
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,3,0,20));
		panel1.setOpaque(false);
		
		
		JLabel Pic = new JLabel();
		Pic.setSize(new Dimension(180, 180));
		Pic.setPreferredSize(new Dimension(180, 180));
		try{
			portrait=ImageIO.read(vo.portrait);
			double scale = (double)portrait.getWidth(null) / (double)portrait.getHeight(null);
	        Image temp = portrait.getScaledInstance((int)(Pic.getHeight() * scale), Pic.getHeight(), Image.SCALE_DEFAULT);
			portraiticon=new ImageIcon(temp);
			Pic.setIcon(portraiticon);
		}
		catch (Exception ex){
			Image icon = (new ImageIcon("data/pic/NotFound.png")).getImage();
	        double scale = (double)icon.getWidth(null) / (double)icon.getHeight(null);
	        Image temp = icon.getScaledInstance((int)(Pic.getHeight() * scale), Pic.getHeight(), Image.SCALE_DEFAULT);
	        Pic.setIcon(new ImageIcon(temp));
		}
		Pic.setBorder(BorderFactory.createEmptyBorder(0, 20, 5, 0));
		
		panel1.add(Pic);
		try{
			action = ImageIO.read(vo.action);
			actionicon = new ImageIcon(action);	   
		}
        catch (Exception ex){
			actionicon = new ImageIcon("data/pic/error.jpg");
		}
		Pic.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						playeraction = new PlayerPicture(actionicon,PlayerFrame.this);
					}
			    });
				
			}
			public void mouseExited(MouseEvent e){
				if(playeraction!=null){
					playeraction.dispose();
				}			
			}
		});
		
		
		//基本数据panel----------------------------------------
	
		subpanel1 = new JPanel();
		subpanel1.setLayout(new GridLayout(6,2,0,0));
		subpanel1.setOpaque(false);
		
		subpanel2 = new JPanel();
		subpanel2.setLayout(new GridLayout(2,1,0,0));
		subpanel2.setOpaque(false);
		
		subpanel21 = new JPanel();
		subpanel21.setLayout(new GridLayout(1,2));
		subpanel21.setOpaque(false);
		
		subpanel22 = new JPanel();
		subpanel22.setLayout(new GridLayout(2,3));
		subpanel22.setOpaque(false);
		
		Font f1 = new Font("宋体",Font.BOLD,11);
		
		birth = new JLabel("出生日期:", JLabel.RIGHT);
		age = new JLabel("年龄:", JLabel.RIGHT);
		exp = new JLabel("经验(y):", JLabel.RIGHT);
		school = new JLabel("毕业学校:", JLabel.RIGHT);
		
		birth.setFont(f1);		
		age.setFont(f1);
		exp.setFont(f1);
		school.setFont(f1);
		
		Font f2 = new Font("宋体",Font.BOLD,12);

		getAge = new JLabel(vo.age, JLabel.CENTER);
		getPosition = new JLabel(PositionEnum.valueToEnum(vo.position).name_Ch(), JLabel.CENTER);
		getHeight = new JLabel(vo.height+"英尺", JLabel.RIGHT);
		getWeight = new JLabel(vo.weight+"磅", JLabel.CENTER);
		getNumber = new JLabel(vo.number+"号", JLabel.RIGHT);
		getExp = new JLabel(vo.exp, JLabel.CENTER);
		
		if(vo.school.length() > 13){
			getSchool = new JLabel("<html>"+vo.school.substring(0, 13)+"<br>"+vo.school.substring(13, vo.school.length())+"<html>", JLabel.CENTER);
		}
		else{
			getSchool = new JLabel(vo.school, JLabel.CENTER);
		}
		getbirth = new JLabel(vo.birth, JLabel.CENTER);

		getAge.setFont(f2);
		getPosition.setFont(f2);
		getHeight.setFont(f2);
		getWeight.setFont(f2);
		getNumber.setFont(f2);
		getExp.setFont(f2);
		getSchool.setFont(f2);
		getbirth.setFont(f2);
		
		subpanel1.add(getNumber);
		subpanel1.add(getPosition);
		subpanel1.add(getHeight);
		subpanel1.add(getWeight);
		subpanel1.add(age);
		subpanel1.add(getAge);
		subpanel1.add(birth);
		subpanel1.add(getbirth);
		subpanel1.add(exp);
		subpanel1.add(getExp);
		subpanel1.add(school);
		subpanel1.add(getSchool);
		
		ArrayList<MatchVO> list = bl.getLastFiveMatches(vo.name);
		if(list.size()==0){
			logoicon = null;
			team = new JLabel("");
		}
		else{
			for(int m = 0;m<list.get(list.size()-1).team1.teamPlayers.size();m++){
		    	 if(list.get(list.size()-1).team1.teamPlayers.get(m).name.equals(vo.name)){
		    		 logofile = new File("logofile");
		 		     logofile.createNewFile();
		 		     SvgUtil.convertSvgFile2Png(tbl.getOneTeam(list.get(list.size()-1).team1.abbName).teamLogo, logofile);
		 		     logo = ImageIO.read(logofile);
		 		     logoicon = new ImageIcon(logo);
		 		     logoicon.setImage(logoicon.getImage().getScaledInstance(130,100,Image.SCALE_DEFAULT));
		 		     team = new JLabel(TeamEnum.valueToEnum(list.get(list.size()-1).team1.abbName).name_abbCh(),JLabel.CENTER);
		 		     abbname = list.get(list.size()-1).team1.abbName;
		    	 }
			}
			for(int m = 0;m<list.get(list.size()-1).team2.teamPlayers.size();m++){
		    	 if(list.get(list.size()-1).team2.teamPlayers.get(m).name.equals(vo.name)){
		    		 logofile = new File("logofile");
		 		     logofile.createNewFile();
		 		     SvgUtil.convertSvgFile2Png(tbl.getOneTeam(list.get(list.size()-1).team2.abbName).teamLogo, logofile);
		 		     logo = ImageIO.read(logofile);
		 		     logoicon = new ImageIcon(logo);
		 		     logoicon.setImage(logoicon.getImage().getScaledInstance(130,100,Image.SCALE_DEFAULT));
		 		     team = new JLabel(TeamEnum.valueToEnum(list.get(list.size()-1).team2.abbName).name_abbCh(),JLabel.CENTER);
		 		     abbname = list.get(list.size()-1).team2.abbName;
		    	 }
			}
		}
		
		
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(logoicon);
		if(logoicon != null){
			Pic1.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					try {
						JFrame.setDefaultLookAndFeelDecorated(true);
						TeamFrame frame = new TeamFrame(tbl.getOneTeam(abbname), mf);
						frame.setOpacity(0.9f);
						frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
					} catch (IOException | TranscoderException e1) {
						e1.printStackTrace();
					}
				}
			});
			Pic1.addMouseMotionListener(new MouseAdapter() {
				public void mouseMoved(MouseEvent e) {
					Pic1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变手
				}
			});
		}
		team.setFont(new Font("黑体",Font.BOLD,20));
		team.setForeground(Color.RED);
		subpanel21.add(Pic1);
		subpanel21.add(team);
		
		Font f3 = new Font("黑体",Font.BOLD,13);
		point = new JLabel("场均得分",JLabel.CENTER);
		assist = new JLabel("场均助攻",JLabel.CENTER);
		rebound = new JLabel("场均篮板",JLabel.CENTER);
		point.setFont(f3);
		assist.setFont(f3);
		rebound.setFont(f3);
		point.setForeground(Color.GRAY);
		assist.setForeground(Color.GRAY);
		rebound.setForeground(Color.GRAY);
		
		getpoint = new JLabel(String.valueOf(bl.getPlayerPast(vo.name).point),JLabel.CENTER);
		getassist = new JLabel(String.valueOf(bl.getPlayerPast(vo.name).assist),JLabel.CENTER);
		getrebound = new JLabel(String.valueOf(bl.getPlayerPast(vo.name).rebound),JLabel.CENTER);
		getpoint.setFont(new Font("黑体",Font.BOLD,20));
		getassist.setFont(new Font("黑体",Font.BOLD,20));
		getrebound.setFont(new Font("黑体",Font.BOLD,20));
		getpoint.setForeground(Color.BLACK);
		getassist.setForeground(Color.BLACK);
		getrebound.setForeground(Color.BLACK);
		
		subpanel22.add(point);
		subpanel22.add(assist);
		subpanel22.add(rebound);
		subpanel22.add(getpoint);
		subpanel22.add(getassist);
		subpanel22.add(getrebound);
		
		subpanel2.add(subpanel21);
		subpanel2.add(subpanel22);
		panel1.add(subpanel1);
		panel1.add(subpanel2);
		//基本信息panel结束--------------------------------------
	}
	
	//-------------------------------------------
		public void setIcon(JButton button, String file1, String file2) {  
	        ImageIcon icon1 = new ImageIcon(file1);
			Image temp1 = icon1.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT);
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
		}
		//可拖动-----------------------------------------------------
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
		
		private void setData(String name) {
			ArrayList<MatchVO> list = bl.getLastFiveMatches(name);
			data = new Object[list.size()][15];
			for (int i = 0; i < list.size(); i++) {
			     data[i][0] = list.get(i).season+"·"+list.get(i).date;
			     data[i][1] = TeamEnum.valueToEnum(list.get(i).team1.abbName).name_abbCh()+"-"+TeamEnum.valueToEnum(list.get(i).team2.abbName).name_abbCh();
			     data[i][2] = "常规赛";
			     for(int m = 0;m<list.get(i).team1.teamPlayers.size();m++){
			    	 if(list.get(i).team1.teamPlayers.get(m).name.equals(name)){
			    		
			    		 if(list.get(i).team1.teamPlayers.get(m).gameStart==1){
			    			 data[i][3] = "是";
			    		 }
			    		 else{
			    			 data[i][3] = "否";
			    		 }
					     data[i][4] = Math.ceil(list.get(i).team1.teamPlayers.get(m).minute * 100) / 100;
					     data[i][5] = (int)list.get(i).team1.teamPlayers.get(m).shootmade+"-"+(int)list.get(i).team1.teamPlayers.get(m).shoot;
					     data[i][6] = (int)list.get(i).team1.teamPlayers.get(m).threepointmade+"-"+(int)list.get(i).team1.teamPlayers.get(m).threepoint;
					     data[i][7] = (int)list.get(i).team1.teamPlayers.get(m).freethrowmade+"-"+(int)list.get(i).team1.teamPlayers.get(m).freethrow;
					     data[i][8] = (int)list.get(i).team1.teamPlayers.get(m).offensiveRebounds+"-"
					    		 +(int)list.get(i).team1.teamPlayers.get(m).defensiveRebounds+"-"
					    		 +((int)list.get(i).team1.teamPlayers.get(m).defensiveRebounds+(int)list.get(i).team1.teamPlayers.get(m).offensiveRebounds);
					     data[i][9] =(int)list.get(i).team1.teamPlayers.get(m).assist;
					     data[i][10] = (int)list.get(i).team1.teamPlayers.get(m).steal;
					     data[i][11] = (int)list.get(i).team1.teamPlayers.get(m).block;
					     data[i][12] = (int)list.get(i).team1.teamPlayers.get(m).error;
					     data[i][13] = (int)list.get(i).team1.teamPlayers.get(m).foul;
					     data[i][14] = (int)list.get(i).team1.teamPlayers.get(m).point;
			    	 }
			     }
			     for(int m = 0;m<list.get(i).team2.teamPlayers.size();m++){
			    	 if(list.get(i).team2.teamPlayers.get(m).name.equals(name)){
			    		 if(list.get(i).team2.teamPlayers.get(m).gameStart==1){
			    			 data[i][3] = "是";
			    		 }
			    		 else{
			    			 data[i][3] = "否";
			    		 }
					     data[i][4] = Math.ceil(list.get(i).team2.teamPlayers.get(m).minute * 100) / 100;
					     data[i][5] = (int)list.get(i).team2.teamPlayers.get(m).shootmade+"-"+(int)list.get(i).team2.teamPlayers.get(m).shoot;
					     data[i][6] = (int)list.get(i).team2.teamPlayers.get(m).threepointmade+"-"+(int)list.get(i).team2.teamPlayers.get(m).threepoint;
					     data[i][7] = (int)list.get(i).team2.teamPlayers.get(m).freethrowmade+"-"+(int)list.get(i).team2.teamPlayers.get(m).freethrow;
					     data[i][8] = (int)list.get(i).team2.teamPlayers.get(m).offensiveRebounds+"-"
					    		 +(int)list.get(i).team2.teamPlayers.get(m).defensiveRebounds+"-"
					    		 +((int)list.get(i).team2.teamPlayers.get(m).defensiveRebounds+(int)list.get(i).team2.teamPlayers.get(m).offensiveRebounds);
					     data[i][9] = (int)list.get(i).team2.teamPlayers.get(m).assist;
					     data[i][10] = (int)list.get(i).team2.teamPlayers.get(m).steal;
					     data[i][11] = (int)list.get(i).team2.teamPlayers.get(m).block;
					     data[i][12] = (int)list.get(i).team2.teamPlayers.get(m).error;
					     data[i][13] = (int)list.get(i).team2.teamPlayers.get(m).foul;
					     data[i][14] = (int)list.get(i).team2.teamPlayers.get(m).point;
			    	 }
			     }
			}
			this.showTable(data);
		}
		
		private void setHistoryData(String name) {
			ArrayList<PlayerVO> list = bl.getAllSeasonPlayer(name);
			PlayerVO playervo = bl.getPlayerPast(name);
			Object[][] data = new Object[list.size()+1][15];
			if(mode.getSelectedItem() == "总数"){
				for (int i = 0; i < list.size()+1; i++) {
					if(i == list.size()){
						 data[i][0] = "生涯总计";
					     data[i][1] = "";
					     data[i][2] = (int)playervo.gameplay;
					     data[i][3] = (int)playervo.gamestart;
					     data[i][4] = (int)playervo.allminute;
					     data[i][5] = (int)(playervo.allshootefficiency*100) + "%";
					     data[i][6] = (int)(playervo.allthreepointpercent*100) + "%";
					     data[i][7] = (int)(playervo.allfreethrowpercent*100) + "%";
					     data[i][8] = (int)playervo.alloffensiverebound+"-"+(int)playervo.alldefensiverebound+"-"+(int)playervo.allrebound;
					     data[i][9] = (int)playervo.allassist;
					     data[i][10] = (int)playervo.allsteal;
					     data[i][11] = (int)playervo.allblock;
					     data[i][12] = (int)playervo.allerror;
					     data[i][13] = (int)playervo.allfoul;
					     data[i][14] = (int)playervo.allpoint;
					}
					else{
						 data[i][0] = list.get(i).season;
					     data[i][1] = TeamEnum.valueToEnum(list.get(i).team).name_abbCh();
					     data[i][2] = list.get(i).gameplay;
					     data[i][3] = list.get(i).gamestart;
					     data[i][4] = list.get(i).allminute;
					     data[i][5] = (int)(list.get(i).allshootefficiency*100) + "%";
					     data[i][6] = (int)(list.get(i).allthreepointpercent*100) + "%";
					     data[i][7] = (int)(list.get(i).allfreethrowpercent*100) + "%";
					     data[i][8] = (int)list.get(i).alloffensiverebound+"-"+(int)list.get(i).alldefensiverebound+"-"+(int)list.get(i).allrebound;
					     data[i][9] = (int)list.get(i).allassist;
					     data[i][10] = (int)list.get(i).allsteal;
					     data[i][11] = (int)list.get(i).allblock;
					     data[i][12] = (int)list.get(i).allerror;
					     data[i][13] = (int)list.get(i).allfoul;
					     data[i][14] = (int)list.get(i).allpoint;
					}
			}
			}
			else{
				for (int i = 0; i < list.size()+1; i++) {
					if(i == list.size()){
						 data[i][0] = "生涯总计";
					     data[i][1] = "";
					     data[i][2] = playervo.gameplay;
					     data[i][3] = playervo.gamestart;
					     data[i][4] = playervo.minute;
					     data[i][5] = (int)(playervo.shootefficiency*100) + "%";
					     data[i][6] = (int)(playervo.threepointpercent*100) + "%";
					     data[i][7] = (int)(playervo.freethrowpercent*100) + "%";
					     data[i][8] = playervo.offensiverebound+"-"+playervo.defensiverebound+"-"+playervo.rebound;
					     data[i][9] = playervo.assist;
					     data[i][10] = playervo.steal;
					     data[i][11] = playervo.block;
					     data[i][12] = playervo.error;
					     data[i][13] = playervo.foul;
					     data[i][14] = playervo.point;
					}
					else{
						 data[i][0] = list.get(i).season;
					     data[i][1] = TeamEnum.valueToEnum(list.get(i).team).name_abbCh();
					     data[i][2] = list.get(i).gameplay;
					     data[i][3] = list.get(i).gamestart;
					     data[i][4] = list.get(i).minute;
					     data[i][5] = (int)(list.get(i).shootefficiency*100)+"%";
					     data[i][6] = (int)(list.get(i).threepointpercent*100)+"%";
					     data[i][7] = (int)(list.get(i).freethrowpercent*100)+"%";
					     data[i][8] = list.get(i).offensiverebound+"-"+list.get(i).defensiverebound+"-"+list.get(i).rebound;
					     data[i][9] = list.get(i).assist;
					     data[i][10] = list.get(i).steal;
					     data[i][11] = list.get(i).block;
					     data[i][12] = list.get(i).error;
					     data[i][13] = list.get(i).foul;
					     data[i][14] = list.get(i).point;		
					}
				}
			}
			
			this.showHistoryTable(data);
		}
		
		private void showTable(Object[][] data) {
			this.remove(table);
			//近比赛表格
			String[] subTitle = {"赛季·日期", "比赛", "类型", "首发","时间",//0-4
						"投篮","三分","罚球","篮板(前后总)","助攻","抢断","盖帽",//5-11
						"失误","犯规","得分"//12-14
						};
			//------------------------------------------------------
			DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			table = new JTable(dm);
			table.getTableHeader().setFont(new Font("宋体",Font.BOLD,12));
			table.setFont(new Font("宋体",0,12));
			table.setDefaultRenderer(Object.class,   r);//居中显示
			((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
			FitTableColumns(table);
	        table.getTableHeader().setReorderingAllowed(false); 
	        table.getTableHeader().setResizingAllowed(false);
				
			sp.setViewportView(table);
			revalidate();
		}
		
		private void showHistoryTable(Object[][] data) {
			this.remove(historytable);
			//生涯比赛表格
			String[] subTitle = {"赛季", "球队", "出场数", "首发场数","时间(M)",//0-4
						"投篮","三分","罚球","篮板(前-后-总)","助攻","抢断","盖帽",//5-11
						"失误","犯规","得分"//12-14
						};
			//------------------------------------------------------
			DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			historytable = new JTable(dm);
			historytable.getTableHeader().setFont(new Font("宋体",Font.BOLD,12));
			historytable.setDefaultRenderer(Object.class,   r);//居中显示
			((DefaultTableCellRenderer)historytable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
			historytable.setFont(new Font("宋体",0,12));
			FitTableColumns(historytable);
			historytable.getTableHeader().setReorderingAllowed(false); 
			historytable.getTableHeader().setResizingAllowed(false);
				
			hsp.setViewportView(historytable);
			revalidate();
		}
		
	//------------------------------------------------------------
		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		}
		
	    public void FitTableColumns(JTable myTable){
	        JTableHeader header = myTable.getTableHeader();
	        int rowCount = myTable.getRowCount();
	        Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();
	        while(columns.hasMoreElements()){
	        TableColumn column = (TableColumn)columns.nextElement();
	        int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
	        int width = (int)myTable.getTableHeader().getDefaultRenderer()
	                 .getTableCellRendererComponent(myTable, column.getIdentifier()
	                         , false, false, -1, col).getPreferredSize().getWidth();
	        for(int row = 0; row<rowCount; row++){
	             int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
	               myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
	             width = Math.max(width, preferedWidth);
	         }
	         header.setResizingColumn(column); 
	         column.setWidth(width+myTable.getIntercellSpacing().width);
	     }
	    }

}

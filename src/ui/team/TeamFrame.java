package ui.team;

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

import businesslogic.MatchBL;
import businesslogic.PlayerBL;
import businesslogic.TeamBL;
import businesslogicservice.MatchBLService;
import businesslogicservice.PlayerBLService;
import businesslogicservice.TeamBLService;
import ui.MainFrame;
import ui.RegionEnum;
import ui.SvgUtil;
import ui.TeamEnum;
import ui.match.MatchFrame;
import ui.player.PlayerFrame;
import vo.MatchVO;
import vo.PlayerVO;
import vo.TeamBasicInfoVO;
import vo.TeamVO;

public class TeamFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571378058437970468L;
	JPanel panel;   //背景panel
	JPanel panelA;  //按钮panel
	JPanel panelA1;
	JPanel panelA2;
	
	JPanel panelB;  //数据panel
	JPanel panelB1;
	
	JPanel panel1;  //基本数据panel
	JPanel subpanel1;
	JPanel subpanel2;
	JPanel subpanel21;
	JPanel subpanel22;
	
	JPanel panel2;  //赛季战绩panel
	JPanel subpanel4;
	JPanel subpanel41;
	JPanel subpanel5;
	
	JPanel panel3;  //生涯统计
	JPanel subpanel3;
	JPanel subpanel31;
	JPanel subpanel6;
	
    JPanel panel4; //球员统计
    JPanel subpanel7;
    JPanel subpanel71;
    JPanel subpanel8;
    
    JPanel panel5; //标签
	
	JButton exit;  //关闭按钮
	JLabel fullName;  //球队
	JLabel abbName;  //球队缩写
	JLabel location;  //所在地
	JLabel competionArea;  //赛区
	JLabel subArea;  //分区
	JLabel homeGround;  //主场
	JLabel setupTime;  //建立时间
	JLabel getabbName;  //球队缩写
	JLabel getlocation;  //所在地
	JLabel getcompetionArea;  //赛区
	JLabel getsubArea;  //分区
	JLabel gethomeGround;  //主场
	JLabel getsetupTime;  //建立时间
	JLabel recentTitle;
	JLabel historyTitle;
	JLabel playerTitle;
	JLabel type;
	
	JLabel win;
	JLabel lost;
	JLabel winrate;
	JLabel getwin;
	JLabel getlost;
	JLabel getwinrate;
	
	JLabel point;
	JLabel getpoint;
	JLabel rate;
	JLabel getrate;
	JLabel offense;
	JLabel getoffense;
	
	ImageIcon bg;  //背景图
    JLabel lab;  //背景
    Image logo;  //队标
    ImageIcon logoicon;
	File logofile;
	
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	
	JComboBox<String> mode;
	
	JTable table;
    JScrollPane sp;
    JTable historytable;
    JScrollPane hsp;
    JTable playertable;
    JScrollPane psp;
	
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	
	TeamBLService bl;
	MatchBLService mbl;
	PlayerBLService pbl;
	
	HistoryData historydata;
	PlayerData playerdata;
	RecentData recentdata;
	
	JButton button1;
	JButton button2;
	JButton button3;
	
	JPanel container; //容器
	CardLayout card;
	public TeamFrame (TeamBasicInfoVO vo,MainFrame mf) throws IOException, TranscoderException{	
		bl = new TeamBL();
		mbl = new MatchBL();
		pbl = new PlayerBL();
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 35 / 48;
		int frameWidth = frameHeight * 39 / 32;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		
		//背景图片
		bg = new ImageIcon("data/pic/teamframe.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		//基本数据panel
		panel1(vo);
		
		//panel2近五场---------------------------------
		panel2(vo, mf);
		
		//panel3---------------------------------------
		panel3(vo, mf);
		
		//panel4--------------------------------------
		panel4(vo, mf);
		
		//container-----------------------------------------------
		card = new CardLayout();
		container = new JPanel(card);
		container.setOpaque(false);
		container.add(panel2,"1");
		container.add(panel3,"2");
		container.add(panel4,"3");
		
		//数据panel---------------------------------------------
		panelB = new JPanel();
		panelB.setLayout(new BorderLayout());
		panelB.setOpaque(false);
		
		panel5 = new JPanel();
		panel5.setLayout(new BoxLayout(panel5,BoxLayout.X_AXIS));
		panel5.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		panel5.setOpaque(false);
		button1 = new JButton("近五场比赛");
		button2 = new JButton("历史数据");
		button3 = new JButton("球队阵容");
		panel5.add(button1);
		panel5.add(button2);
		panel5.add(button3);
		
		panelB1 = new JPanel();
		panelB1.setLayout(new BorderLayout());
		panelB1.setOpaque(false);
		panelB1.add(panel5,BorderLayout.NORTH);
		panelB1.add(container,BorderLayout.CENTER);
		
		panelB.add(panel1,BorderLayout.NORTH);
		panelB.add(panelB1,BorderLayout.CENTER);

		//------------------
		panelA = new JPanel();
		panelA.setLayout(new GridLayout(1,2));
		
		panelA1 = new JPanel();
		panelA1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelA1.setBorder(BorderFactory.createEmptyBorder(0, 65, 0, 0));
		panelA2 = new JPanel();
		panelA2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelA2.setOpaque(false);
		panelA1.setOpaque(false);
		panelA.setOpaque(false);
		panelA.add(panelA1);
		panelA.add(panelA2);
		Font f = new Font("宋体",Font.BOLD,22);
		fullName = new JLabel(TeamEnum.valueToEnum(vo.abbName).name_Ch());
		fullName.setFont(f);
		fullName.setForeground(Color.ORANGE);
		panelA1.add(fullName);
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
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				card.show(container, "3");
			}
		});
		//---------------------------------
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
	 * @throws IOException
	 */
	private void panel4(TeamBasicInfoVO vo,MainFrame mf) throws IOException {
		panel4 = new JPanel();
		panel4.setLayout(new GridLayout(2,1));
		panel4.setOpaque(false);
		panel4.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		
		playerTitle = new JLabel("球队阵容：");
		playerTitle.setFont(new Font("黑体",Font.BOLD,15));
		playerTitle.setForeground(Color.ORANGE);
		subpanel71 = new JPanel();
		subpanel71.setLayout(new FlowLayout(FlowLayout.LEFT));
		subpanel71.setOpaque(false);
		subpanel71.add(playerTitle);
		
		playertable = new JTable();
		psp = new JScrollPane(playertable);
		this.setPlayerData(vo.abbName);
		//表格监听
		playertable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (playertable.getSelectedColumn() == 0) {
					String str = (String)playertable.getValueAt(playertable.getSelectedRow(), 0);
					try {
						JFrame.setDefaultLookAndFeelDecorated(true);
						PlayerFrame frame = new PlayerFrame(pbl.getOnePlayer(str),mf);
						frame.setOpacity(0.9f);
						frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
					} catch (IOException | TranscoderException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		playertable.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {  
		        int col = playertable.columnAtPoint(e.getPoint());  
		        if (col == 0)
		        	playertable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变手
		        else
		        	playertable.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));//鼠标默认
		    }  
		});
		
		
		subpanel7 = new JPanel();
		subpanel7.setLayout(new BorderLayout());
		subpanel7.setOpaque(false);
		subpanel7.add(subpanel71,BorderLayout.NORTH);
		subpanel7.add(psp, BorderLayout.CENTER);
		
		subpanel8 = new JPanel();
		subpanel8.setLayout(new GridLayout(1,3));
		subpanel8.setOpaque(false);
		playerdata = new PlayerData(vo.abbName);
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label5.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/5.jpg"))));
		label6.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/6.jpg"))));
		label7.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/7.jpg"))));
		subpanel8.add(label5);
		subpanel8.add(label6);
		subpanel8.add(label7);
		panel4.add(subpanel7);
		panel4.add(subpanel8);
	}

	/**
	 * @param vo
	 * @param tp
	 * @throws IOException 
	 */
	private void panel3(TeamBasicInfoVO vo, MainFrame mf) throws IOException {
	
		subpanel31 = new JPanel();
		subpanel31.setLayout(new FlowLayout(FlowLayout.LEFT));
		subpanel31.setOpaque(false);
		mode = new JComboBox<String>(new String[]{"场均", "总数"});
		mode.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setHistoryData(vo.abbName);
				}
				});
		type = new JLabel("    数据类型:");
		type.setForeground(Color.BLACK);;
        historyTitle = new JLabel("历史统计:");
		historyTitle.setFont(new Font("宋体",Font.BOLD,15));
		historyTitle.setForeground(Color.ORANGE);
		subpanel31.add(historyTitle);
		subpanel31.add(type);
		subpanel31.add(mode);
		
		historytable = new JTable();
		hsp = new JScrollPane(historytable);
		this.setHistoryData(vo.abbName);
		//表格监听
		historytable.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		String season = ((String)historytable.getValueAt(historytable.getSelectedRow(), 0));
	    	    mf.getTeamMatch(season, vo.abbName);
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
		subpanel3.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		subpanel3.add(subpanel31,BorderLayout.NORTH);
		subpanel3.add(hsp, BorderLayout.CENTER);
		
		subpanel6 = new JPanel();
		subpanel6.setLayout(new GridLayout(1,2));
		subpanel6.setOpaque(false);
		historydata = new HistoryData(vo.abbName);
		label3 = new JLabel();
		label4 = new JLabel();
		label3.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/3.jpg"))));
		label4.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/4.jpg"))));
		subpanel6.add(label3);
		subpanel6.add(label4);
		
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(2,1));
		panel3.setOpaque(false);
        panel3.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
		
		panel3.add(subpanel3);
		panel3.add(subpanel6);
	}

	/**
	 * @param vo
	 * @param tp
	 * @throws IOException 
	 */
	private void panel2(TeamBasicInfoVO vo, MainFrame mf) throws IOException {
		panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setLayout(new GridLayout(2,1));
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
		subpanel41 = new JPanel();
		subpanel41.setOpaque(false);
		subpanel41.setLayout(new FlowLayout(FlowLayout.LEFT));
		recentTitle = new JLabel("最近5场比赛统计:");
		recentTitle.setFont(new Font("宋体",Font.BOLD,15));
		recentTitle.setForeground(Color.ORANGE);
		subpanel41.add(recentTitle);
		table = new JTable();
		sp = new JScrollPane(table);
		this.setData(vo.abbName);
		 //表格监听
	    table.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		String season = (String)table.getValueAt(table.getSelectedRow(), 0);
	    		String date = (String)table.getValueAt(table.getSelectedRow(), 1);
	    		String team = ((String)table.getValueAt(table.getSelectedRow(), 3));
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
	    subpanel4.setBorder(BorderFactory.createEmptyBorder(5, 0, 12, 0));
	    subpanel4.add(subpanel41, BorderLayout.NORTH);
	    subpanel4.add(sp,BorderLayout.CENTER);
	    
	    subpanel5 = new JPanel();
	    subpanel5.setLayout(new GridLayout(1,2));
	    subpanel5.setOpaque(false);
	    recentdata = new RecentData(vo.abbName);
	    
		label1 = new JLabel();
		label2 = new JLabel();
		label1.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/1.jpg"))));
		label2.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/2.jpg"))));
		subpanel5.add(label1);
		subpanel5.add(label2);
		
		panel2.add(subpanel4);
		panel2.add(subpanel5);
	}

	/**
	 * @param vo
	 */
	private void panel1(TeamBasicInfoVO vo) {
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,3,0,20));
		panel1.setOpaque(false);
		try{
			logofile = new File("logofile");
		    logofile.createNewFile();
		    SvgUtil.convertSvgFile2Png(vo.teamLogo, logofile);
		    logo = ImageIO.read(logofile);
		    logoicon = new ImageIcon(logo);
		}
		catch(Exception ex){
			logoicon = new ImageIcon("data/pic/NotFound.jpg");
		}
		
		logoicon.setImage(logoicon.getImage().getScaledInstance(220,180,Image.SCALE_DEFAULT));
		
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(logoicon);
		
		panel1.add(Pic1);
		
		//文字信息panel
		subpanel1 = new JPanel();
//		subpanel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		subpanel1.setLayout(new GridLayout(6,2,0,0));
		subpanel1.setOpaque(false);
		
		subpanel2 = new JPanel();
//		subpanel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		subpanel2.setLayout(new GridLayout(2,1,0,0));
		subpanel2.setOpaque(false);
		
		abbName = new JLabel("队名缩写:",JLabel.CENTER);
		location = new JLabel("所在地:",JLabel.CENTER);
		competionArea = new JLabel("赛区:",JLabel.CENTER);
		subArea = new JLabel("分区:",JLabel.CENTER);
		homeGround = new JLabel("主场馆:",JLabel.CENTER);
		setupTime = new JLabel("建队时间:",JLabel.CENTER);
		
		Font f1 = new Font("宋体",Font.BOLD,12);

		abbName.setFont(f1);
		location.setFont(f1);
		competionArea.setFont(f1);
		subArea.setFont(f1);
		homeGround.setFont(f1);
		setupTime.setFont(f1);
		abbName.setForeground(Color.PINK);
		location.setForeground(Color.MAGENTA);
		competionArea.setForeground(Color.PINK);
		subArea.setForeground(Color.MAGENTA);
		homeGround.setForeground(Color.PINK);
		setupTime.setForeground(Color.MAGENTA);
		
		Font f2 = new Font("宋体",Font.BOLD,11);

		getabbName = new JLabel(vo.abbName,JLabel.LEFT);
		getlocation = new JLabel(vo.location,JLabel.LEFT);
		getcompetionArea = new JLabel(RegionEnum.valueToEnum(vo.competionArea).name_Ch(),JLabel.LEFT);
		getsubArea = new JLabel(RegionEnum.valueToEnum(vo.subArea).name_Ch(),JLabel.LEFT);
		if(vo.homeGround.length() > 15){
			gethomeGround = new JLabel("<html>"+vo.homeGround.substring(0, 15)+"-"+"<br>"+vo.homeGround.substring(15, vo.homeGround.length())+"<html>", JLabel.CENTER);
		}
		else{
			gethomeGround = new JLabel(vo.homeGround, JLabel.LEFT);
		}
		getsetupTime = new JLabel(vo.setupTime,JLabel.LEFT);

		getabbName.setFont(f2);
		getlocation.setFont(f2);
		getcompetionArea.setFont(f2);
		getsubArea.setFont(f2);
		gethomeGround.setFont(f2);
		getsetupTime.setFont(f2);
		
		getabbName.setForeground(Color.WHITE);
		getlocation.setForeground(Color.WHITE);
		getcompetionArea.setForeground(Color.WHITE);
		getsubArea.setForeground(Color.WHITE);
		gethomeGround.setForeground(Color.WHITE);
		getsetupTime.setForeground(Color.WHITE);
		
		subpanel1.add(abbName);
		subpanel1.add(getabbName);
		subpanel1.add(location);
		subpanel1.add(getlocation);
		subpanel1.add(competionArea);
		subpanel1.add(getcompetionArea);
		subpanel1.add(subArea);
		subpanel1.add(getsubArea);
		subpanel1.add(homeGround);
		subpanel1.add(gethomeGround);
		subpanel1.add(setupTime);
		subpanel1.add(getsetupTime);
		
		subpanel21 = new JPanel();
		subpanel21.setLayout(new GridLayout(2,3));
		subpanel21.setOpaque(false);
		win = new JLabel("胜场",JLabel.CENTER);
		lost = new JLabel("负场",JLabel.CENTER);
		winrate = new JLabel("胜率",JLabel.CENTER);
		getwin = new JLabel(String.valueOf((int)bl.getSeasonTeams(mbl.getAllSeasons().get(0), vo.abbName).winsNum),JLabel.CENTER);
		getlost = new JLabel(String.valueOf((int)bl.getSeasonTeams(mbl.getAllSeasons().get(0), vo.abbName).gamesNum-(int)bl.getSeasonTeams(mbl.getAllSeasons().get(0), vo.abbName).winsNum),JLabel.CENTER);
		getwinrate = new JLabel(String.valueOf(bl.getSeasonTeams(mbl.getAllSeasons().get(0), vo.abbName).winsRate),JLabel.CENTER);
		Font f3 = new Font("黑体", Font.BOLD ,13);
		win.setFont(f3);
		lost.setFont(f3);
		winrate.setFont(f3);
		win.setForeground(Color.CYAN);
		lost.setForeground(Color.CYAN);
		winrate.setForeground(Color.CYAN);
		
		Font f4 = new Font("黑体", Font.BOLD ,17);
		getwin.setFont(f4);
		getlost.setFont(f4);
		getwinrate.setFont(f4);
		getwin.setForeground(Color.BLACK);
		getlost.setForeground(Color.black);
		getwinrate.setForeground(Color.BLACK);
		
		subpanel21.add(win);
		subpanel21.add(lost);
		subpanel21.add(winrate);
		subpanel21.add(getwin);
		subpanel21.add(getlost);
		subpanel21.add(getwinrate);
		
		subpanel22 = new JPanel();
		subpanel22.setLayout(new GridLayout(2,3));
		subpanel22.setOpaque(false);
		
		point = new JLabel("场均得分",JLabel.CENTER);
		getpoint = new JLabel(String.valueOf(bl.getSeasonTeams(mbl.getAllSeasons().get(0), vo.abbName).scores),JLabel.CENTER);
		rate = new JLabel("命中率",JLabel.CENTER);
		getrate = new JLabel(String.valueOf(bl.getSeasonTeams(mbl.getAllSeasons().get(0), vo.abbName).shootingHitRate),JLabel.CENTER);
		offense = new JLabel("场均进攻数",JLabel.CENTER);
		getoffense = new JLabel(String.valueOf(bl.getSeasonTeams(mbl.getAllSeasons().get(0), vo.abbName).attackRound),JLabel.CENTER);
		
		point.setFont(f3);
		rate.setFont(f3);
		offense.setFont(f3);
		point.setForeground(Color.CYAN);
		rate.setForeground(Color.CYAN);
		offense.setForeground(Color.CYAN);
		getpoint.setFont(f4);
		getrate.setFont(f4);
		getoffense.setFont(f4);
		getpoint.setForeground(Color.black);
		getrate.setForeground(Color.black);
		getoffense.setForeground(Color.black);
		
		subpanel22.add(point);
		subpanel22.add(rate);
		subpanel22.add(offense);
		subpanel22.add(getpoint);
		subpanel22.add(getrate);
		subpanel22.add(getoffense);
		
		subpanel2.add(subpanel21);
		subpanel2.add(subpanel22);
		
		panel1.add(subpanel1);
		panel1.add(subpanel2);
	}

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
	
	private void setData(String name) {
		ArrayList<MatchVO> list = bl.getLastFiveMatchesSpecific(name);
		
		Object[][] data = new Object[list.size()][10];
		for (int i = 0; i < list.size(); i++) {
		     data[i][0] = list.get(i).season;
		     data[i][1] = list.get(i).date;
		     if(list.get(i).team1.abbName.equals(name)){
		    	 data[i][2] = "主场";
		    	 data[i][3] = TeamEnum.valueToEnum(list.get(i).team2.abbName).name_abbCh();
		    	 data[i][4] = (int)list.get(i).team1.qt1Scores+"-"+(int)list.get(i).team2.qt1Scores;
			     data[i][5] = (int)list.get(i).team1.qt2Scores+"-"+(int)list.get(i).team2.qt2Scores;
			     data[i][6] = (int)list.get(i).team1.qt3Scores+"-"+(int)list.get(i).team2.qt3Scores;
			     data[i][7] = (int)list.get(i).team1.qt4Scores+"-"+(int)list.get(i).team2.qt4Scores;
			     data[i][9] = (int)list.get(i).team1.scores+"-"+(int)list.get(i).team2.scores;
			     if(list.get(i).team1.qtPlusScores.isEmpty()){
			    	 data[i][8] = "未加时";
			     }
			     else{
			    	 data[i][8] = "";
				     for(int m = 0;m < list.get(i).team1.qtPlusScores.size()-1;m++){
				    	  data[i][8] = data[i][8]+((int)(double)list.get(i).team1.qtPlusScores.get(m)+"-"+(int)(double)list.get(i).team2.qtPlusScores.get(m)+",");
				     }
				     data[i][8] = data[i][8] + ((int)(double)list.get(i).team1.qtPlusScores.get(list.get(i).team1.qtPlusScores.size()-1)+"-"+(int)(double)list.get(i).team2.qtPlusScores.get(list.get(i).team1.qtPlusScores.size()-1));
			     }		    
		     }
		     else{
		    	 data[i][2] = "客场";
		    	 data[i][3] = TeamEnum.valueToEnum(list.get(i).team1.abbName).name_abbCh();
		    	 data[i][4] = (int)list.get(i).team2.qt1Scores+"-"+(int)list.get(i).team1.qt1Scores;
			     data[i][5] = (int)list.get(i).team2.qt2Scores+"-"+(int)list.get(i).team1.qt2Scores;
			     data[i][6] = (int)list.get(i).team2.qt3Scores+"-"+(int)list.get(i).team1.qt3Scores;
			     data[i][7] = (int)list.get(i).team2.qt4Scores+"-"+(int)list.get(i).team1.qt4Scores;
			     data[i][9] = (int)list.get(i).team2.scores+"-"+(int)list.get(i).team1.scores;
			     if(list.get(i).team1.qtPlusScores.isEmpty()){
			    	 data[i][8] = "未加时";
			     }
			     else{
			    	 data[i][8] = "";
				     for(int m = 0;m < list.get(i).team1.qtPlusScores.size()-1;m++){
				    	  data[i][8] = data[i][8]+((int)(double)list.get(i).team2.qtPlusScores.get(m)+"-"+(int)(double)list.get(i).team1.qtPlusScores.get(m)+",");
				     }
				     data[i][8] = data[i][8] + ((int)(double)list.get(i).team2.qtPlusScores.get(list.get(i).team1.qtPlusScores.size()-1)+"-"+(int)(double)list.get(i).team1.qtPlusScores.get(list.get(i).team1.qtPlusScores.size()-1));
			     }		    
		     }
		}
		this.showTable(data);
	}
	
	
	private void setHistoryData(String name) {
		ArrayList<TeamVO> list = bl.getAllSeasonTeam(name);
		Object[][] data = new Object[list.size()][14];
		if(mode.getSelectedItem() == "总数"){
			for (int i = 0; i < list.size(); i++) {
		     data[i][0] = list.get(i).season;
		     data[i][4] = list.get(i).allshootingHitRate*100+"%";
		     data[i][5] = list.get(i).allthreePointHitRate*100+"%";
		     data[i][6] = list.get(i).allfreeThrowHitRate*100+"%";
		     data[i][7] = (int)list.get(i).alloffensiveRebounds+"-"+(int)list.get(i).alldefensiveRebounds+"-"+(int)list.get(i).allrebounds;
		     data[i][8] = (int)list.get(i).allassists;
		     data[i][9] = (int)list.get(i).allsteal;
		     data[i][10] = (int)list.get(i).allcaps;
		     data[i][11] = (int)list.get(i).allturnovers;
		     data[i][12] = (int)list.get(i).allfouls;
		     data[i][13] = (int)list.get(i).allscores;
		     data[i][1] = (int)list.get(i).winsNum;
		     data[i][2] = (int)list.get(i).gamesNum;
		     data[i][3] = (int)(list.get(i).winsRate*100)+"%";
		}
		}
		else{
			for (int i = 0; i < list.size(); i++) {
			     data[i][0] = list.get(i).season;
			     data[i][4] = list.get(i).shootingHitRate*100+"%";
			     data[i][5] = list.get(i).threePointHitRate*100+"%";
			     data[i][6] = list.get(i).freeThrowHitRate*100+"%";
			     data[i][7] = list.get(i).offensiveRebounds+"-"+list.get(i).defensiveRebounds+"-"+list.get(i).rebounds;
			     data[i][8] = list.get(i).assists;
			     data[i][9] = list.get(i).steal;
			     data[i][10] = list.get(i).caps;
			     data[i][11] = list.get(i).turnovers;
			     data[i][12] = list.get(i).fouls;
			     data[i][13] = list.get(i).scores;
			     data[i][1] = (int)list.get(i).winsNum;
			     data[i][2] = (int)list.get(i).gamesNum;
			     data[i][3] = (int)(list.get(i).winsRate*100)+"%";
			}
		}
		this.showHistoryTable(data);
	}
	
	private void setPlayerData(String name) {
		ArrayList<PlayerVO> list = pbl.getPlayers(mbl.getAllSeasons().get(0), "All", "All", name);
		Object[][] data = new Object[list.size()][14];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).name;
			data[i][1] = list.get(i).gameplay;
		    data[i][2] = list.get(i).gamestart;
			data[i][3] = list.get(i).minute;
			data[i][4] = list.get(i).shootefficiency;
			data[i][5] = list.get(i).threepointpercent;
			data[i][6] = list.get(i).freethrowpercent;
			data[i][7] = list.get(i).rebound;
			data[i][8] = list.get(i).assist;
			data[i][9] = list.get(i).steal;
			data[i][10] = list.get(i).block;
			data[i][11] = list.get(i).error;
			data[i][12] = list.get(i).foul;
			data[i][13] = list.get(i).point;
		}
		this.showPlayerTable(data);
	}
	
	private void showTable(Object[][] data) {
		this.remove(table);
		//近比赛表格
		String[] subTitle = {"赛季", "日期","主客场","对手",//0-3
					"第一节","第二节","第三节","第四节","加时",//4-8
					"比分"//8
					};
		//------------------------------------------------------
		@SuppressWarnings("serial")
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dm);
		table.getTableHeader().setFont(new Font("宋体",Font.BOLD,12));
		table.setFont(new Font("宋体",0,12));
//		FitTableColumns(table);
        table.getTableHeader().setReorderingAllowed(false); //表头不能移动
		
		sp.setViewportView(table);
		revalidate();
	}
	
	private void showHistoryTable(Object[][] data) {
		this.remove(historytable);
		//生涯比赛表格
		String[] subTitle = {"赛季","胜场","比赛场数","胜率","投篮","三分","罚球","篮板(前-后-总)","助攻","抢断","盖帽",//0-7
					"失误","犯规","得分"//8-10
					};
		//------------------------------------------------------
		@SuppressWarnings("serial")
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		historytable = new JTable(dm);
		historytable = new JTable(dm);
		historytable.getTableHeader().setFont(new Font("宋体",Font.BOLD,12));
		historytable.setDefaultRenderer(Object.class,   r);//居中显示
		historytable.setFont(new Font("宋体",0,12));
		FitTableColumns(historytable);
		historytable.getTableHeader().setReorderingAllowed(false); 
		historytable.getTableHeader().setResizingAllowed(false);
			
		hsp.setViewportView(historytable);
		revalidate();
	}
	
	private void showPlayerTable(Object[][] data) {
		this.remove(playertable);
		//生涯比赛表格
		String[] subTitle = {"姓名","场数","先发","分钟","投篮","三分","罚球","篮板","助攻","抢断","盖帽",//0-10
					"失误","犯规","得分"//11-13
					};
		//------------------------------------------------------
		@SuppressWarnings("serial")
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		playertable = new JTable(dm);
		playertable = new JTable(dm);
		playertable.getTableHeader().setFont(new Font("宋体",Font.BOLD,12));
		playertable.setDefaultRenderer(Object.class,   r);//居中显示
		((DefaultTableCellRenderer)playertable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		playertable.setFont(new Font("宋体",0,12));
		FitTableColumns(playertable);
		playertable.getTableHeader().setReorderingAllowed(false); 
		playertable.getTableHeader().setResizingAllowed(false);
			
		psp.setViewportView(playertable);
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

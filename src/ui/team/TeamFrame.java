package ui.team;

import java.awt.BorderLayout;
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
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.batik.transcoder.TranscoderException;

import ui.SvgUtil;
import vo.TeamBasicInfoVO;

public class TeamFrame extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571378058437970468L;
	JPanel panel;   //背景panel
	JPanel panelA;  //按钮panel
	JPanel panelA1;
	JPanel panelA2;
	
	JPanel panelB;  //数据panel
	
	JPanel panel1;  //基本数据panel
	JPanel subpanel1;
	JPanel subpanel2;
	JPanel panel2;  //赛季战绩panel
	JPanel subpanel4;
	JPanel panel3;  //生涯统计
	JPanel subpanel3;
	
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
	JLabel type;
	
	ImageIcon bg;  //背景图
    JLabel lab;  //背景
    Image logo;  //队标
    ImageIcon logoicon;
	File logofile;
	
	JComboBox<String> mode;
	
	JTable table;
    JScrollPane sp;
    JTable historytable;
    JScrollPane hsp;
	
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	public TeamFrame (TeamBasicInfoVO vo) throws IOException, TranscoderException{	
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 11 / 16;
		int frameWidth = frameHeight * 21 / 16;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		
		//背景图片
		bg = new ImageIcon("data/pic/Yellow.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		//基本数据panel
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,3,0,20));
		panel1.setOpaque(false);
		logofile = new File("logofile");
		logofile.createNewFile();
		SvgUtil.convertSvgFile2Png(vo.teamLogo, logofile);
		logo = ImageIO.read(logofile);
		logoicon = new ImageIcon(logo);
		logoicon.setImage(logoicon.getImage().getScaledInstance(220,180,Image.SCALE_DEFAULT));
		
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(logoicon);
		
		panel1.add(Pic1);
		
		//文字信息panel
		subpanel1 = new JPanel();
//		subpanel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		subpanel1.setLayout(new GridLayout(3,2,0,0));
		subpanel1.setOpaque(false);
		
		subpanel2 = new JPanel();
//		subpanel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		subpanel2.setLayout(new GridLayout(3,2,0,0));
		subpanel2.setOpaque(false);
		
		
		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		panel2.setLayout(new GridLayout(7,2,0,20));
		panel2.setOpaque(false);
		abbName = new JLabel("队名缩写:",JLabel.CENTER);
		location = new JLabel("所在地:",JLabel.CENTER);
		competionArea = new JLabel("赛区:",JLabel.CENTER);
		subArea = new JLabel("分区:",JLabel.CENTER);
		homeGround = new JLabel("主场馆:",JLabel.CENTER);
		setupTime = new JLabel("建队时间:",JLabel.CENTER);
		Font f1 = new Font("宋体",Font.BOLD,16);
		abbName.setFont(f1);
		location.setFont(f1);
		competionArea.setFont(f1);
		subArea.setFont(f1);
		homeGround.setFont(f1);
		setupTime.setFont(f1);
		
		Font f2 = new Font("宋体",Font.BOLD,14);

		getabbName = new JLabel(vo.abbName,JLabel.LEFT);
		getlocation = new JLabel(vo.location,JLabel.LEFT);
		getcompetionArea = new JLabel(vo.competionArea,JLabel.LEFT);
		getsubArea = new JLabel(vo.subArea,JLabel.LEFT);
		gethomeGround = new JLabel(vo.homeGround,JLabel.LEFT);
		getsetupTime = new JLabel(vo.setupTime,JLabel.LEFT);

		getabbName.setFont(f2);
		getlocation.setFont(f2);
		getcompetionArea.setFont(f2);
		getsubArea.setFont(f2);
		gethomeGround.setFont(f2);
		getsetupTime.setFont(f2);
		
		subpanel1.add(abbName);
		subpanel1.add(getabbName);
		subpanel1.add(location);
		subpanel1.add(getlocation);
		subpanel1.add(competionArea);
		subpanel1.add(getcompetionArea);
		subpanel2.add(subArea);
		subpanel2.add(getsubArea);
		subpanel2.add(homeGround);
		subpanel2.add(gethomeGround);
		subpanel2.add(setupTime);
		subpanel2.add(getsetupTime);
		
		panel1.add(subpanel1);
		panel1.add(subpanel2);
		
		//panel2---------------------------------
		panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setLayout(new BorderLayout());
		subpanel4 = new JPanel();
		subpanel4.setOpaque(false);
		subpanel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		recentTitle = new JLabel("最近5场比赛统计:");
		recentTitle.setFont(new Font("宋体",Font.BOLD,15));
		subpanel4.add(recentTitle);
		table = new JTable();
		sp = new JScrollPane(table);
		sp.setOpaque(false);
		this.setData();
		table.setBackground(Color.YELLOW);

		panel2.add(sp ,BorderLayout.CENTER);
		panel2.add(subpanel4,BorderLayout.NORTH);
		//panel3---------------------------------------
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.setOpaque(false);
		subpanel3 = new JPanel();
		subpanel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		subpanel3.setOpaque(false);
		mode = new JComboBox<String>(new String[]{"总数", "场均"});
		type = new JLabel("数据类型:");
		historytable = new JTable();
		hsp = new JScrollPane(historytable);
		hsp.setOpaque(false);
		this.setHistoryData();
		historytable.setBackground(Color.YELLOW);
		
		historyTitle = new JLabel("生涯统计:");
		historyTitle.setFont(new Font("宋体",Font.BOLD,15));
		subpanel3.add(historyTitle);
		subpanel3.add(type);
		subpanel3.add(mode);
		
		panel3.add(subpanel3,BorderLayout.NORTH);
		panel3.add(hsp,BorderLayout.CENTER);
		//数据panel---------------------------------------------
		panelB = new JPanel();
		panelB.setLayout(new GridLayout(3,1));
		panelB.setOpaque(false);
		panelB.add(panel1);
		panelB.add(panel2);
		panelB.add(panel3);
		//------------------
		panelA = new JPanel();
		panelA.setLayout(new GridLayout(1,2));
		
		panelA1 = new JPanel();
		panelA1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelA2 = new JPanel();
		panelA2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelA2.setOpaque(false);
		panelA1.setOpaque(false);
		panelA.setOpaque(false);
		panelA.add(panelA1);
		panelA.add(panelA2);
		Font f = new Font("宋体",Font.BOLD,22);
		fullName = new JLabel(vo.fullName);
		fullName.setFont(f);
		panelA1.add(fullName);
		exit = new JButton();
		exit.setFocusPainted(false);
		exit.setSize(new Dimension(25, 25));
	    exit.setPreferredSize(new Dimension(25, 25));
	    this.setIcon(exit, "data/pic/exit1.png", "data/pic/exit2.png");
		panelA2.add(exit);
		exit.addActionListener(new ExitListener());
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
	
	private void setData() {
		Object[][] data = new Object[5][15];
		for (int i = 0; i < 5; i++) {
		     data[i][0] = "01-01";
		     data[i][1] = "DAL-WAS";
		     data[i][2] = "F";
		     data[i][3] = "是";
		     data[i][4] = "30:09";
		     data[i][5] = "18-18";
		     data[i][6] = "18-18";
		     data[i][7] = "10-10";
		     data[i][8] = "20-15-45";
		     data[i][9] = "10";
		     data[i][10] = "10";
		     data[i][11] = "15";
		     data[i][12] = "6";
		     data[i][13] = "5";
		     data[i][14] = "30";
		}
		this.showTable(data);
	}
	
	private void setHistoryData() {
		Object[][] data = new Object[20][15];
		for (int i = 0; i < 5; i++) {
		     data[i][0] = "14-15";
		     data[i][1] = "DAL";
		     data[i][2] = "82";
		     data[i][3] = "82";
		     data[i][4] = "30:09";
		     data[i][5] = "50%";
		     data[i][6] = "30%";
		     data[i][7] = "85%";
		     data[i][8] = "20-15-45";
		     data[i][9] = "8.6";
		     data[i][10] = "2";
		     data[i][11] = "0.2";
		     data[i][12] = "4";
		     data[i][13] = "2.7";
		     data[i][14] = "27";
		}
		this.showHistoryTable(data);
	}
	
	private void showTable(Object[][] data) {
		this.remove(table);
		//近比赛表格
		String[] subTitle = {"日期", "比赛",//0-4
					"投篮","三分","罚球","篮板(前-后-总)","助攻","抢断","盖帽",//5-11
					"失误","犯规","得分"//12-14
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
		FitTableColumns(table);
        table.getTableHeader().setReorderingAllowed(false); 
        table.getTableHeader().setResizingAllowed(false);
			
		sp.setViewportView(table);
		sp.setBorder(BorderFactory.createEmptyBorder(5, 20, 25, 20));
		revalidate();
	}
	
	private void showHistoryTable(Object[][] data) {
		this.remove(historytable);
		//生涯比赛表格
		String[] subTitle = {"赛季", "球队", "出场", "首发","时间",//0-4
					"投篮","三分","罚球","篮板(前-后-总)","助攻","抢断","盖帽",//5-11
					"失误","犯规","得分"//12-14
					};
		//------------------------------------------------------
		@SuppressWarnings("serial")
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		historytable = new JTable(dm);
		historytable.getTableHeader().setFont(new Font("宋体",Font.BOLD,12));
		historytable.setFont(new Font("宋体",0,12));
		FitTableColumns(historytable);
		historytable.getTableHeader().setReorderingAllowed(false); 
		historytable.getTableHeader().setResizingAllowed(false);
			
		hsp.setViewportView(historytable);
		hsp.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
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

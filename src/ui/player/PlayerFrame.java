package ui.player;

import java.awt.BorderLayout;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;
import vo.MatchVO;
import vo.PlayerBasicInfoVO;

public class PlayerFrame extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PlayerBLService bl;
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
    JLabel getTeam;
    JLabel actionlabel;
    JLabel birth;
    JLabel getbirth;
    JLabel recentTitle;
    JLabel historyTitle;
    JLabel type;
    
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
	
	
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
    
	public PlayerFrame (PlayerBasicInfoVO vo) throws IOException{
		bl = new PlayerBL();
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 12 / 16;
		int frameWidth = frameHeight * 21 / 16;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//背景图片
		bg = new ImageIcon("data/pic/Yellow.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		//基本数据panel-----------------------------------------------------------
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,3,0,20));
		panel1.setOpaque(false);
		try{
			portrait=ImageIO.read(vo.portrait);
		}
		catch (Exception ex){
			
		}
		
		portraiticon=new ImageIcon(portrait);
		JLabel Pic = new JLabel();
		Pic.setIcon(portraiticon);
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
					@SuppressWarnings("restriction")
					public void run() {
						playeraction = new PlayerPicture(actionicon,PlayerFrame.this);
						com.sun.awt.AWTUtilities.setWindowOpacity(playeraction, 0.9f);//设置透明度
						com.sun.awt.AWTUtilities.setWindowShape(playeraction, new RoundRectangle2D.Double(0.0D, 0.0D, playeraction.getWidth(), playeraction.getHeight(), 26.0D, 26.0D));//设置圆角
					}
			    });
				
			}
			public void mouseExited(MouseEvent e){
				playeraction.dispose();
			}
		});
		
		
		//基本数据panel----------------------------------------
	
		subpanel1 = new JPanel();
		subpanel1.setLayout(new GridLayout(4,2,0,0));
		subpanel1.setOpaque(false);
		
		subpanel2 = new JPanel();
		subpanel2.setLayout(new GridLayout(4,2,0,0));
		subpanel2.setOpaque(false);
		
		Font f1 = new Font("宋体",Font.BOLD,14);
		
		birth = new JLabel("出生日期:", JLabel.RIGHT);
		age = new JLabel("年龄:", JLabel.RIGHT);
		position = new JLabel("位置:", JLabel.RIGHT);
		height = new JLabel("身高(ft):", JLabel.RIGHT);
		weight = new JLabel("体重(lb):", JLabel.RIGHT);
		number = new JLabel("号码:", JLabel.RIGHT);
		exp = new JLabel("经验(y):", JLabel.RIGHT);
		school = new JLabel("毕业学校:", JLabel.RIGHT);
		
		birth.setFont(f1);		
		age.setFont(f1);
		position.setFont(f1);
		height.setFont(f1);
		weight.setFont(f1);
		number.setFont(f1);
		exp.setFont(f1);
		school.setFont(f1);
		
		Font f2 = new Font("宋体",Font.BOLD,12);

		getAge = new JLabel(vo.age, JLabel.CENTER);
		getPosition = new JLabel(vo.position, JLabel.CENTER);
		getHeight = new JLabel(vo.height, JLabel.CENTER);
		getWeight = new JLabel(vo.weight, JLabel.CENTER);
		getNumber = new JLabel(vo.number, JLabel.CENTER);
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
		
		subpanel1.add(number);
		subpanel1.add(getNumber);
		subpanel1.add(position);
		subpanel1.add(getPosition);
		subpanel1.add(height);
		subpanel1.add(getHeight);
		subpanel1.add(weight);
		subpanel1.add(getWeight);
		subpanel2.add(age);
		subpanel2.add(getAge);
		subpanel2.add(birth);
		subpanel2.add(getbirth);
		subpanel2.add(exp);
		subpanel2.add(getExp);
		subpanel2.add(school);
		subpanel2.add(getSchool);
		
		panel1.add(subpanel1);
		panel1.add(subpanel2);
		
		//panel2
		panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setLayout(new BorderLayout());
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		subpanel4 = new JPanel();
		subpanel4.setOpaque(false);
		subpanel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		recentTitle = new JLabel("最近5场比赛统计:");
		recentTitle.setFont(new Font("宋体",Font.BOLD,15));
		subpanel4.add(recentTitle);
		table = new JTable();
		sp = new JScrollPane(table);
		sp.setOpaque(false);
		this.setData(vo.name);
		
		subpanel3 = new JPanel();
		subpanel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		subpanel3.setOpaque(false);
		mode = new JComboBox<String>(new String[]{"总数", "场均"});
		type = new JLabel("数据类型:");
		historyTitle = new JLabel("生涯统计:");
		historyTitle.setFont(new Font("宋体",Font.BOLD,15));
        subpanel3.add(historyTitle);
		subpanel3.add(type);
		subpanel3.add(mode);
		
		panel2.add(sp ,BorderLayout.CENTER);
		panel2.add(subpanel4,BorderLayout.NORTH);
		panel2.add(subpanel3,BorderLayout.SOUTH);
		//panel3
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.setOpaque(false);
        panel3.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		
		historytable = new JTable();
		hsp = new JScrollPane();
		hsp.setViewportView(historytable);
		this.setHistoryData();
		
		panel3.add(hsp,BorderLayout.CENTER);
		
		//数据panel--------------------------------------------------
		panelB = new JPanel();
		panelB.setLayout(new GridLayout(3,1));
		panelB.setOpaque(false);
		panelB.add(panel1);
		panelB.add(panel2);
		panelB.add(panel3);
	
		
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
		Object[][] data = new Object[5][15];
		for (int i = 0; i < 5; i++) {
		     data[i][0] = list.get(i).season+"·"+list.get(i).date;
		     data[i][1] = list.get(i).team1.abbName+"-"+list.get(i).team2.abbName;
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
		String[] subTitle = {"赛季·日期", "比赛", "类型", "首发","时间(M)",//0-4
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
		revalidate();
	}
	
	private void showHistoryTable(Object[][] data) {
		this.remove(historytable);
		//生涯比赛表格
		String[] subTitle = {"赛季", "球队", "出场数", "首发场数","时间",//0-4
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

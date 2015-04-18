package ui.match;

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
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.batik.transcoder.TranscoderException;

import ui.SvgUtil;
import vo.MatchVO;

@SuppressWarnings("serial")
public class MatchFrame extends JFrame{
	JPanel panel;  //背景panel	
	
	JPanel panel1;  //标题panel
	JPanel panel1A;
	JPanel panel1B;
	
	JPanel panel2;  //数据panel
	
	JPanel panelA;  //比分panel
	JPanel subpanelA1;
	JPanel subpanelA2;
	JPanel subpanelA3;
	
	JPanel panel22;
	
	JPanel panelB;  //主队panel
	JPanel subpanelB1;
	JPanel subpanelB2;
	
	JPanel panelC;  //客队panel
	JPanel subpanelC1;
	JPanel subpanelC2;
	
	JButton exit;  //关闭按钮
	JLabel season;
	JLabel date;
	JLabel score1;
	JLabel score2;
	JLabel team1;
	JLabel team2;
	
	JLabel first;
	JLabel second;
	JLabel third;
	JLabel forth;
	JLabel plus;
	JLabel h1;
	JLabel h2;
	JLabel h3;
	JLabel h4;
	JLabel hp;
	JLabel c1;
	JLabel c2;
	JLabel c3;
	JLabel c4;
	JLabel cp;
	
	
	ImageIcon bg;  //背景图
    JLabel lab;  //背景
    Image logo1;  //主队队标
    ImageIcon logoicon1;
	File logofile1;
	Image logo2;  //客队队标
    ImageIcon logoicon2;
	File logofile2;
	File temp;
	
	JTable table1;  //主队数据表格
    JScrollPane sp1;
    Object[][] data1;
    
    JTable table2;  //客队数据表格
    JScrollPane sp2;
    Object[][] data2;
	
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	
	public MatchFrame(MatchVO vo) throws IOException, TranscoderException{
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 11 / 16;
		int frameWidth = frameHeight * 13 / 10;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		
		//背景图片
		bg = new ImageIcon("data/pic/Yellow.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));

		//标题panel1---------------------------------
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,2));
		
		panel1A = new JPanel();
		panel1A.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel1A.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		panel1B = new JPanel();
		panel1B.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel1B.setOpaque(false);
		panel1A.setOpaque(false);
		panel1.setOpaque(false);
		panel1.add(panel1A);
		panel1.add(panel1B);
		Font f = new Font("宋体",Font.BOLD,20);
		season = new JLabel("13-14赛季");
		season.setFont(f);
		date = new JLabel("   01-01");
		date.setFont(f);
		panel1A.add(season);
		panel1A.add(date);
		exit = new JButton();
		exit.setFocusPainted(false);
		exit.setSize(new Dimension(25, 25));
	    exit.setPreferredSize(new Dimension(25, 25));
	    this.setIcon(exit, "data/pic/exit1.png", "data/pic/exit2.png");
		panel1B.add(exit);
		exit.addActionListener(new ExitListener());
		
		//比分panelA------------------------------
		panelA = new JPanel();
		panelA.setLayout(new BorderLayout());
		panelA.setOpaque(false);
		
		logofile1 = new File("logofile");
		logofile1.createNewFile();
		temp = new File("HOU.svg");
		SvgUtil.convertSvgFile2Png(temp, logofile1);
		logo1 = ImageIO.read(logofile1);
		logoicon1 = new ImageIcon(logo1);
		logoicon1.setImage(logoicon1.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));		
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(logoicon1);
		score1 = new JLabel("91");
		score1.setFont(new Font("宋体",Font.BOLD,30));		
		subpanelA1 = new JPanel();
		subpanelA1.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		subpanelA1.setOpaque(false);
		subpanelA1.add(Pic1);
		subpanelA1.add(score1);
		
		subpanelA3 = new JPanel();
		subpanelA3.setOpaque(false);
		subpanelA3.setLayout(new GridLayout(3,5));
		subpanelA3.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 5));
		Font f1 = new Font("宋体",Font.BOLD,15);
		first = new JLabel("第一节",JLabel.CENTER);
		second = new JLabel("第二节",JLabel.CENTER);
		third = new JLabel("第三节",JLabel.CENTER);
		forth = new JLabel("第四节",JLabel.CENTER);
		plus = new JLabel("加时",JLabel.CENTER);
		first.setFont(f1);
		second.setFont(f1);
		third.setFont(f1);
		forth.setFont(f1);
		plus.setFont(f1);
		Font f2 = new Font("宋体",Font.BOLD,12);
		h1 = new JLabel("25",JLabel.CENTER);
		h2 = new JLabel("25",JLabel.CENTER);
		h3 = new JLabel("25",JLabel.CENTER);
		h4 = new JLabel("25",JLabel.CENTER);
		hp = new JLabel("25,25,25",JLabel.CENTER);
		c1 = new JLabel("25",JLabel.CENTER);
		c2 = new JLabel("25",JLabel.CENTER);
		c3 = new JLabel("25",JLabel.CENTER);
		c4 = new JLabel("25",JLabel.CENTER);
		cp = new JLabel("25,25,25",JLabel.CENTER);
		h1.setFont(f2);
		h2.setFont(f2);
		h3.setFont(f2);
		h4.setFont(f2);
		hp.setFont(f2);
		c1.setFont(f2);
		c2.setFont(f2);
		c3.setFont(f2);
		c4.setFont(f2);
		cp.setFont(f2);
		subpanelA3.add(first);
		subpanelA3.add(second);
		subpanelA3.add(third);
		subpanelA3.add(forth);
		subpanelA3.add(plus);
		subpanelA3.add(h1);
		subpanelA3.add(h2);
		subpanelA3.add(h3);
		subpanelA3.add(h4);
		subpanelA3.add(hp);
		subpanelA3.add(c1);
		subpanelA3.add(c2);
		subpanelA3.add(c3);
		subpanelA3.add(c4);
		subpanelA3.add(cp);
		
		
		logofile2 = new File("logofile");
		logofile2.createNewFile();
		temp = new File("HOU.svg");
		SvgUtil.convertSvgFile2Png(temp, logofile1);
		logo2 = ImageIO.read(logofile2);
		logoicon2 = new ImageIcon(logo2);
		logoicon2.setImage(logoicon2.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));		
		JLabel Pic2 = new JLabel();
		Pic2.setIcon(logoicon1);
		score2 = new JLabel("92");
		score2.setFont(new Font("宋体",Font.BOLD,30));
		subpanelA2 = new JPanel();
		subpanelA2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));;
		subpanelA2.setOpaque(false);
		subpanelA2.add(score2);
		subpanelA2.add(Pic2);
		
		
		panelA.add(subpanelA1,BorderLayout.WEST);
		panelA.add(subpanelA3,BorderLayout.CENTER);
		panelA.add(subpanelA2,BorderLayout.EAST);
		
		
		
        //主队数据panelB
		panelB = new JPanel();
		panelB.setLayout(new BorderLayout());
		panelB.setOpaque(false);
		panelB.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
		subpanelB1 = new JPanel();
		subpanelB1.setLayout(new FlowLayout(FlowLayout.LEFT));
		team1 = new JLabel("HOU"+"技术统计:");
		team1.setFont(new Font("宋体",Font.BOLD,15));
		subpanelB1.add(team1);
		

		table1 = new JTable();
		sp1 = new JScrollPane(table1);
		data1 = new Object[12][14];
		for(int n = 0; n < 12;n++){
			data1[n][0] = "LLLLLLL詹姆斯";
			data1[n][1] = "是";
			data1[n][2] = "F";
			data1[n][3] = "35";
			data1[n][4] = "10-10";
			data1[n][5] = "2-3";
			data1[n][6] = "5-5";
			data1[n][7] = "5-6-11";
			data1[n][8] = "5";//assist
			data1[n][9] = "2";//steal
			data1[n][10] = "1";//block
			data1[n][11] = "6";//error
			data1[n][12] = "3";//f
			data1[n][13] = "40";
		}
		this.showTable1(data1);
		panelB.add(team1,BorderLayout.NORTH);
		panelB.add(sp1,BorderLayout.CENTER);
		
		//客队panelC---------------------------------
		panelC = new JPanel();
		panelC.setLayout(new BorderLayout());
		panelC.setOpaque(false);
		panelC.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		subpanelC1 = new JPanel();
		subpanelC1.setLayout(new FlowLayout(FlowLayout.LEFT));
		team2 = new JLabel("HOU"+"技术统计:");
		team2.setFont(new Font("宋体",Font.BOLD,15));
		subpanelC1.add(team2);
		

		table2 = new JTable();
		sp2 = new JScrollPane(table2);
		data2 = new Object[12][14];
		for(int n = 0; n < 12;n++){
			data2[n][0] = "LLLLLLL詹姆斯";
			data2[n][1] = "是";
			data2[n][2] = "F";
			data2[n][3] = "35";
			data2[n][4] = "10-10";
			data2[n][5] = "2-3";
			data2[n][6] = "5-5";
			data2[n][7] = "5-6-11";
			data2[n][8] = "5";//assist
			data2[n][9] = "2";//steal
			data2[n][10] = "1";//block
			data2[n][11] = "6";//error
			data2[n][12] = "3";//f
			data2[n][13] = "40";
		}
		this.showTable2(data2);
		panelC.add(team2,BorderLayout.NORTH);
		panelC.add(sp2,BorderLayout.CENTER);
		
		//panel22----------------------
		panel22 = new JPanel();
		panel22.setOpaque(false);
		panel22.setLayout(new GridLayout(2,1));
		panel22.add(panelB);
		panel22.add(panelC);
		//数据panel2-----------------------------------
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setOpaque(false);
		panel2.add(panelA,BorderLayout.NORTH);
		panel2.add(panel22,BorderLayout.CENTER);
		//---------------------------------
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panel2,BorderLayout.CENTER);
		panel.add(panel1,BorderLayout.NORTH);
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
               MatchFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               MatchFrame.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(MatchFrame.this.getLocation().x + e.getX() - tmp.x,
                		   MatchFrame.this.getLocation().y + e.getY() - tmp.y);
                   MatchFrame.this.setLocation(loc);
               }
            }
        });
	}
	private void showTable1(Object[][] data) {
		this.remove(table1);
		String[] subTitle = {"球员","首发","位置","时间(M)","投篮","三分","罚球",
				"篮板(前-后-总)","助攻","抢断","盖帽","失误","犯规","得分"};
		//------------------------------------------------------
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table1 = new JTable(dm);
		table1.setDefaultRenderer(Object.class,   r);
		table1.getTableHeader().setFont(new Font("宋体",Font.BOLD,12));
		table1.setFont(new Font("宋体",0,12));
		this.FitTableColumns(table1);
		table1.getTableHeader().setReorderingAllowed(false); 
		table1.getTableHeader().setResizingAllowed(false);
			
		sp1.setViewportView(table1);
		revalidate();
	}
	
	private void showTable2(Object[][] data) {
		this.remove(table2);
		String[] subTitle = {"球员","首发","位置","时间(M)","投篮","三分","罚球",
				"篮板(前-后-总)","助攻","抢断","盖帽","失误","犯规","得分"};
		//------------------------------------------------------
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableCellRenderer r =  new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table2 = new JTable(dm);
		table2.setDefaultRenderer(Object.class,   r);
		table2.getTableHeader().setFont(new Font("宋体",Font.BOLD,12));
		table2.setFont(new Font("宋体",0,12));
		this.FitTableColumns(table2);
		table2.getTableHeader().setReorderingAllowed(false); 
		table2.getTableHeader().setResizingAllowed(false);
			
		sp2.setViewportView(table2);
		revalidate();
	}
	
	public static void main(String[] args) throws IOException, TranscoderException{
		new MatchFrame(new MatchVO());
	}
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

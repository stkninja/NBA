package ui.match;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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

@SuppressWarnings("serial")
public class LivePane extends JPanel implements Runnable{
    JPanel panel1;  //标题panel
	
    JPanel panel2;  //数据panel
	
	JPanel panelA;  //比分panel
	JPanel subpanelA1;
	JPanel subpanelA2;
	JPanel subpanelA3;
	
	JPanel panel22;
	
	JPanel panelB;  //文字panel
	JPanel subpanelB1;
	
	JLabel season;
	JLabel type;
	JLabel date;
	JLabel score1;
	JLabel score2;
	JLabel team1;
	JLabel team2;
	JLabel subtitle;
	
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
	
	Image logo1;  //主队队标
	ImageIcon logoicon1;
	File logofile1;
	Image logo2;  //客队队标
	ImageIcon logoicon2;
	File logofile2;
	File temp;
	
	JButton refresh;
	
	
	JTable table;
	JScrollPane sp;
	Object[][] data;
	public LivePane() throws IOException, TranscoderException {
		//标题panel
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel1.setOpaque(false);
		Font f = new Font("宋体",Font.BOLD,20);
		season = new JLabel("14-15赛季");
		season.setFont(f);
		season.setForeground(Color.BLACK);
		type = new JLabel("总决赛");
		type.setFont(f);
		type.setForeground(Color.BLACK);
		date = new JLabel("   "+"6-15"+"日      ");
		date.setFont(f);
		date.setForeground(Color.BLACK);
		team1 = new JLabel("勇士vs");
		team1.setFont(f);
		team1.setForeground(Color.BLACK);
		team2 = new JLabel("骑士");
		team2.setFont(f);
		team2.setForeground(Color.BLACK);
		panel1.add(season);
		panel1.add(type);
		panel1.add(date);
		panel1.add(team1);
		panel1.add(team2);
		
		//比分panelA------------------------------
		panelA = new JPanel();
		panelA.setLayout(new BorderLayout());
		panelA.setOpaque(false);
		
		logofile1 = new File("logofile");
		logofile1.createNewFile();
		
		SvgUtil.convertSvgFile2Png(new File("data/teams/GSW.svg"), logofile1);
		logo1 = ImageIO.read(logofile1);
		logoicon1 = new ImageIcon(logo1);
		logoicon1.setImage(logoicon1.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));		
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(logoicon1);
		score1 = new JLabel("102");
		score1.setFont(new Font("宋体",Font.BOLD,29));	
		score1.setForeground(Color.RED);
		subpanelA1 = new JPanel();
		subpanelA1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		subpanelA1.setOpaque(false);
		subpanelA1.add(Pic1);
		subpanelA1.add(score1);
		
		subpanelA3 = new JPanel();
		subpanelA3.setOpaque(false);
		subpanelA3.setLayout(new GridLayout(3,5));
		subpanelA3.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
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
		first.setForeground(Color.BLACK);
		second.setForeground(Color.BLACK);
		third.setForeground(Color.BLACK);
		forth.setForeground(Color.BLACK);
		plus.setForeground(Color.BLACK);
		Font f2 = new Font("宋体",Font.BOLD,12);
		h1 = new JLabel("24",JLabel.CENTER);
		h2 = new JLabel("18",JLabel.CENTER);
		h3 = new JLabel("28",JLabel.CENTER);
		h4 = new JLabel("12",JLabel.CENTER);
		hp = new JLabel("0",JLabel.CENTER);
		
		c1 = new JLabel("31",JLabel.CENTER);
		c2 = new JLabel("23",JLabel.CENTER);
		c3 = new JLabel("22",JLabel.CENTER);
		c4 = new JLabel("27",JLabel.CENTER);
		cp = new JLabel("0",JLabel.CENTER);
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
		h1.setForeground(Color.MAGENTA);
		h2.setForeground(Color.MAGENTA);
		h3.setForeground(Color.MAGENTA);
		h4.setForeground(Color.MAGENTA);
		hp.setForeground(Color.MAGENTA);
		c1.setForeground(Color.MAGENTA);
		c2.setForeground(Color.MAGENTA);
		c3.setForeground(Color.MAGENTA);
		c4.setForeground(Color.MAGENTA);
		cp.setForeground(Color.MAGENTA);
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
		
		SvgUtil.convertSvgFile2Png(new File("data/teams/CLE.svg"), logofile2);
		logo2 = ImageIO.read(logofile2);
		logoicon2 = new ImageIcon(logo2);
		logoicon2.setImage(logoicon2.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));		
		JLabel Pic2 = new JLabel();
		Pic2.setIcon(logoicon2);
		score2 = new JLabel("103");
		score2.setFont(new Font("宋体",Font.BOLD,29));
		score2.setForeground(Color.RED);
		subpanelA2 = new JPanel();
		subpanelA2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));;
		subpanelA2.setOpaque(false);
		subpanelA2.add(score2);
		subpanelA2.add(Pic2);
		
		
		panelA.add(subpanelA1,BorderLayout.WEST);
		panelA.add(subpanelA3,BorderLayout.CENTER);
		panelA.add(subpanelA2,BorderLayout.EAST);
		
		//文字直播panel
		panelB = new JPanel();
		panelB.setLayout(new BorderLayout());
		panelB.setOpaque(false);
		panelB.setBorder(BorderFactory.createEmptyBorder(0, 12, 10, 12));
		subpanelB1 = new JPanel();
		subpanelB1.setLayout(new FlowLayout(FlowLayout.LEFT));
		subtitle = new JLabel("直播实况         ");
		subtitle.setFont(new Font("楷体",Font.BOLD,20));
		refresh = new JButton("刷新");
		subpanelB1.add(subtitle);
		subpanelB1.add(refresh);
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setData(); 
			}
		});
		table = new JTable();
		sp = new JScrollPane(table);
		this.setData(); 
		
		panelB.add(subpanelB1,BorderLayout.NORTH);
		panelB.add(sp,BorderLayout.CENTER);
		
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setOpaque(false);
		panel2.add(panelA,BorderLayout.NORTH);
		panel2.add(panelB,BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(panel1, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.CENTER);
	}
	
	private void setData(){
		data = new Object[50][4];
		for(int n = 0; n < 50;n++){
			data[n][0] = "12:00";
			data[n][1] = "骑士";
			data[n][2] = "莫兹戈夫和德雷蒙德-格林跳球，特里斯坦-汤普森得到篮球";
			data[n][3] = "0-0";
		}
		this.showTable(data);
	}
	
	private void showTable(Object[][] data) {
		this.remove(table);
		String[] subTitle = {"时间","球队","事件","比分（勇士-骑士）"};
		//------------------------------------------------------
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableCellRenderer r = new DefaultTableCellRenderer(); 
		r.setHorizontalAlignment(JLabel.CENTER);   
		table = new JTable(dm);
		table.setFont(new Font("楷体", 0, 16));
		table.setDefaultRenderer(Object.class,r);//居中显示
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.getTableHeader().setFont(new Font("宋体",Font.BOLD,12));
		table.setFont(new Font("宋体",0,12));
		this.FitTableColumns(table);
		table.getTableHeader().setReorderingAllowed(false); 
		table.getTableHeader().setResizingAllowed(false);
			
		sp.setViewportView(table);
		revalidate();
	}
	public void run() {
		while(true){
			this.setData();
			try {
				Thread.sleep(5000);
				} catch(InterruptedException e) {
				return;
				}
		}
	}
		
		
	
	public void FitTableColumns(JTable myTable){
        JTableHeader header = myTable.getTableHeader();
        header.setFont(new Font("黑体",Font.BOLD,15));
        myTable.setFont(new Font("黑体",Font.PLAIN,12));
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
	public static void main(String[] args) throws IOException, TranscoderException{
		JFrame frame = new JFrame();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 11 / 16;
		int frameWidth = frameHeight * 13 / 10;
		frame.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		LivePane lp = new LivePane();
		Thread t = new Thread(lp,"A");
		frame.add(lp);
		t.start();
		frame.setVisible(true);
	}

}

package ui.match;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.batik.transcoder.TranscoderException;

import ui.SvgUtil;
import vo.LiveInfoVO;
import businesslogic.LiveBL;
import businesslogicservice.LiveBLService;

@SuppressWarnings("serial")
public class LivePane extends JPanel implements Runnable{
	LiveBLService lbl;
	JComboBox<String> mode;
	JPanel panel;
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
	public void init(){
		lbl = new LiveBL();
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		LiveInfoVO vo = lbl.getLiveInfo();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);
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
		
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setOpaque(false);
		
		//比分panelA------------------------------
		panelA = new JPanel();
		panelA(vo);
		
		//文字直播panel
		panelB = new JPanel();
		panelB.setLayout(new BorderLayout());
		panelB.setOpaque(false);
		panelB.setBorder(BorderFactory.createEmptyBorder(0, 12, 10, 12));
		subpanelB1 = new JPanel();
		subpanelB1.setOpaque(false);
		subpanelB1.setLayout(new FlowLayout(FlowLayout.LEFT));
		subtitle = new JLabel("直播实况 ");
		subtitle.setFont(new Font("楷体",Font.BOLD,20));
		refresh = new JButton("刷新");
//		mode = new JComboBox<String>(new String[]{"自动刷新","手动刷新" });
//		mode.addActionListener(
//				new ActionListener(){
//				public void actionPerformed(ActionEvent e) {
//					thread();
//				}
//				});
		subpanelB1.add(subtitle);
//		subpanelB1.add(mode);
		subpanelB1.add(refresh);
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				refresh(); 
			}
		});
		table = new JTable();
		sp = new JScrollPane(table);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		sp.setBorder(new EmptyBorder(0,0,0,0));
		this.setData(vo); 
		
		panelB.add(subpanelB1,BorderLayout.NORTH);
		panelB.add(sp,BorderLayout.CENTER);
		
		panel2.add(panelA,BorderLayout.NORTH);
		panel2.add(panelB,BorderLayout.CENTER);
		
		panel.add(panel1, BorderLayout.NORTH);
		panel.add(panel2, BorderLayout.CENTER);
		this.setLayout(new BorderLayout());
		this.add(panel,BorderLayout.CENTER);
		
	}
	/**
	 * @param vo
	 */
	private void panelA(LiveInfoVO vo) {
		panel2.remove(panelA);
		panelA = new JPanel();
		panelA.setLayout(new BorderLayout());
		panelA.setOpaque(false);
		
		logofile1 = new File("logofile");
		try {
			logofile1.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			SvgUtil.convertSvgFile2Png(new File("data/teams/GSW.svg"), logofile1);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (TranscoderException e1) {
			e1.printStackTrace();
		}
		try {
			logo1 = ImageIO.read(logofile1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		logoicon1 = new ImageIcon(logo1);
		logoicon1.setImage(logoicon1.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));		
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(logoicon1);
		score1 = new JLabel(String.valueOf(vo.homeScore));
		score1.setFont(new Font("宋体",Font.BOLD,29));	
		score1.setForeground(Color.RED);
		subpanelA1 = new JPanel();
		subpanelA1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		subpanelA1.setOpaque(false);
		subpanelA1.add(Pic1);
		subpanelA1.add(score1);
		
		subpanelA3 = new JPanel();
		subpanelA3.setOpaque(false);
		subpanelA3.setLayout(new GridLayout(3,4));
		subpanelA3.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		Font f1 = new Font("宋体",Font.BOLD,15);
		first = new JLabel("第一节",JLabel.CENTER);
		second = new JLabel("第二节",JLabel.CENTER);
		third = new JLabel("第三节",JLabel.CENTER);
		forth = new JLabel("第四节",JLabel.CENTER);
//		plus = new JLabel("加时",JLabel.CENTER);
		first.setFont(f1);
		second.setFont(f1);
		third.setFont(f1);
		forth.setFont(f1);
//		plus.setFont(f1);
		first.setForeground(Color.BLACK);
		second.setForeground(Color.BLACK);
		third.setForeground(Color.BLACK);
		forth.setForeground(Color.BLACK);
//		plus.setForeground(Color.BLACK);
		Font f2 = new Font("宋体",Font.BOLD,12);
		h1 = new JLabel(String.valueOf(LiveInfoVO.matchrecord[0]),JLabel.CENTER);
		h2 = new JLabel(String.valueOf(LiveInfoVO.matchrecord[2]),JLabel.CENTER);
		h3 = new JLabel(String.valueOf(LiveInfoVO.matchrecord[4]),JLabel.CENTER);
		h4 = new JLabel(String.valueOf(LiveInfoVO.matchrecord[6]),JLabel.CENTER);
//		hp = new JLabel("0",JLabel.CENTER);
		
		c1 = new JLabel(String.valueOf(LiveInfoVO.matchrecord[1]),JLabel.CENTER);
		c2 = new JLabel(String.valueOf(LiveInfoVO.matchrecord[3]),JLabel.CENTER);
		c3 = new JLabel(String.valueOf(LiveInfoVO.matchrecord[5]),JLabel.CENTER);
		c4 = new JLabel(String.valueOf(LiveInfoVO.matchrecord[7]),JLabel.CENTER);
//		cp = new JLabel("0",JLabel.CENTER);
		h1.setFont(f2);
		h2.setFont(f2);
		h3.setFont(f2);
		h4.setFont(f2);
//		hp.setFont(f2);
		c1.setFont(f2);
		c2.setFont(f2);
		c3.setFont(f2);
		c4.setFont(f2);
//		cp.setFont(f2);
		h1.setForeground(Color.MAGENTA);
		h2.setForeground(Color.MAGENTA);
		h3.setForeground(Color.MAGENTA);
		h4.setForeground(Color.MAGENTA);
//		hp.setForeground(Color.MAGENTA);
		c1.setForeground(Color.MAGENTA);
		c2.setForeground(Color.MAGENTA);
		c3.setForeground(Color.MAGENTA);
		c4.setForeground(Color.MAGENTA);
//		cp.setForeground(Color.MAGENTA);
		subpanelA3.add(first);
		subpanelA3.add(second);
		subpanelA3.add(third);
		subpanelA3.add(forth);
//		subpanelA3.add(plus);
		subpanelA3.add(h1);
		subpanelA3.add(h2);
		subpanelA3.add(h3);
		subpanelA3.add(h4);
//		subpanelA3.add(hp);
		subpanelA3.add(c1);
		subpanelA3.add(c2);
		subpanelA3.add(c3);
		subpanelA3.add(c4);
//		subpanelA3.add(cp);
		
		
		logofile2 = new File("logofile");
		try {
			logofile2.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			SvgUtil.convertSvgFile2Png(new File("data/teams/CLE.svg"), logofile2);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (TranscoderException e1) {
			e1.printStackTrace();
		}
		try {
			logo2 = ImageIO.read(logofile2);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		logoicon2 = new ImageIcon(logo2);
		logoicon2.setImage(logoicon2.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));		
		JLabel Pic2 = new JLabel();
		Pic2.setIcon(logoicon2);
		score2 = new JLabel(String.valueOf(vo.visitScore));
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
		panel2.add(panelA, BorderLayout.NORTH);
		revalidate();
	}
	public LivePane(){
		init();
		new Thread(){
			public void run() {
				while(LivePane.this.isShowing()){
					refresh();
					System.out.println("on");
					try {
						Thread.sleep(5000);
						} catch(InterruptedException e) {
						return;
						}
				}
			}
		}.start();
		
	}
	public void run() {
		while(true){
			refresh();
			System.out.println("0");
			try {
				Thread.sleep(5000);
				} catch(InterruptedException e) {
				return;
				}
		}
	}
//	private void thread(){
//		if(mode.getSelectedItem().equals("手动刷新")){
//			t.interrupt();
//		}
//		else{
//			t.interrupt();
//			t =new Thread(){
//				public void run() {
//					while(isShow){
//						
//						refresh();
//						System.out.println(isShow);
//						try {
//							Thread.sleep(5000);
//							} catch(InterruptedException e) {
//							return;
//							}
//					}
//				}
//			};
//			t.start();
//		}
//		
//	}
	
	private void setData(LiveInfoVO vo){
		data = vo.live;
		this.showTable(data);
	}
	
	private void refresh(){
		LiveInfoVO vo = lbl.getLiveInfo();
		setData(vo);
		h1.setText(String.valueOf(LiveInfoVO.matchrecord[0]));
		h2.setText(String.valueOf(LiveInfoVO.matchrecord[2]));
		h3.setText(String.valueOf(LiveInfoVO.matchrecord[4]));
		h4.setText(String.valueOf(LiveInfoVO.matchrecord[6]));
		
		c1.setText(String.valueOf(LiveInfoVO.matchrecord[1]));
		c2.setText(String.valueOf(LiveInfoVO.matchrecord[3]));
		c3.setText(String.valueOf(LiveInfoVO.matchrecord[5]));
		c4.setText(String.valueOf(LiveInfoVO.matchrecord[7]));
		
		score2.setText(String.valueOf(vo.visitScore));
		score1.setText(String.valueOf(vo.homeScore));
//		panelA(vo);
		revalidate();
	};
	
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
		table.setRowHeight(30);
		table.setShowGrid(false);
		table.getTableHeader().setReorderingAllowed(false); 
		table.getTableHeader().setResizingAllowed(false);
			
		sp.setViewportView(table);
		revalidate();
	}
	
		
		
	
	public void FitTableColumns(JTable myTable){
        JTableHeader header = myTable.getTableHeader();
        header.setFont(new Font("黑体",Font.BOLD,16));
        myTable.setFont(new Font("黑体",Font.PLAIN,14));
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
         column.setWidth(width+myTable.getIntercellSpacing().width+120);
     }
    }

}

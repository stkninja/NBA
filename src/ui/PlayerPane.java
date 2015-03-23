package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ui.tableheader.ColumnGroup;
import ui.tableheader.GroupableTableColumnModel;
import ui.tableheader.GroupableTableHeader;
import vo.PlayerVO;
import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;

/**
 * 
 * @author stk
 * 球员面板
 *
 */
@SuppressWarnings("serial")
public class PlayerPane extends JPanel implements ActionListener {
	private PlayerBLService bl;
	private JTable table;
	private JScrollPane sp;
	//搜索界面
	private JPanel pane;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JComboBox<String> mode;
	private JComboBox<String> region;
	private JComboBox<String> team;
	private JComboBox<String> position;
	//--------------------------------------------------------------
	public PlayerPane() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(0, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		//搜索界面
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		pane.setOpaque(false);
		
		label1 = new JLabel("数据类型：");
		mode = new JComboBox<String>(new String[]{"总数", "场均"});
		label2 = new JLabel("地区：");
		region = new JComboBox<String>(Region.getRegion());
		label3 = new JLabel("球队：");
		team = new JComboBox<String>();
		team.addItem("All");
		team.setEnabled(false);
		label4 = new JLabel("位置：");
		String[] positionList = {"All", "G", "F", "C"};
		position = new JComboBox<String>(positionList);
		
		pane.add(label1);
		pane.add(mode);
		pane.add(label2);
		pane.add(region);
		pane.add(label3);
		pane.add(team);
		pane.add(label4);
		pane.add(position);
		this.add(pane, BorderLayout.NORTH);
		//表格
		table = new JTable();
		sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		bl = new PlayerBL();
		this.setData(bl.getAllPlayers());
		//监听
		mode.addActionListener(this);
		region.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (region.getSelectedItem().equals("All")) {
					team.removeAllItems();
					team.addItem("All");
					team.setEnabled(false);
				} else {
					team.removeAllItems();
					String[] list = Region.valueOf((String)region.getSelectedItem()).getTeam();
					team.addItem("All");
					for (int i = 0; i < list.length; i++) {
						team.addItem(list[i]);
					}
					team.setEnabled(true);
				}
			}
		});
		team.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					PlayerPane.this.setData(bl.getPlayers((String)region.getSelectedItem(), (String)position.getSelectedItem(), (String)team.getSelectedItem()));
			}
		});
		position.addActionListener(this);
	}
	/**
	 * 监听
	 */
	public void actionPerformed(ActionEvent e) {
		PlayerPane.this.setData(bl.getPlayers((String)region.getSelectedItem(), (String)position.getSelectedItem(), (String)team.getSelectedItem()));		
	}
	/**
	 * 设置表格数据
	 * @param list 队员VO列表
	 */
	private void setData(ArrayList<PlayerVO> list) {
		Object[][] data = new Object[list.size()][36];
		if (mode.getSelectedItem() == "总数") {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).name;
				data[i][2] = list.get(i).team;
				data[i][3] = list.get(i).position;
				data[i][4] = list.get(i).gameplay;
				data[i][5] = list.get(i).gamestart;
				data[i][6] = list.get(i).allminute;
				
				data[i][7] = list.get(i).allshootmade;
				data[i][8] = list.get(i).allshoot;
				data[i][9] = list.get(i).allfieldgoalpercent;
				data[i][10] = list.get(i).allrealshootpercent;
				
				data[i][11] = list.get(i).allthreepointmade;
				data[i][12] = list.get(i).allthreepoint;
				data[i][13] = list.get(i).allthreepointpercent;
				
				data[i][14] = list.get(i).allfreethrowmade;
				data[i][15] = list.get(i).allfreethrow;
				data[i][16] = list.get(i).allfreethrowpercent;
				
				data[i][17] = list.get(i).alloffensiverebound;
				data[i][18] = list.get(i).alldefensiverebound;
				data[i][19] = list.get(i).allrebound;
				
				data[i][20] = list.get(i).allassist;
				data[i][21] = list.get(i).allsteal;
				data[i][22] = list.get(i).allblock;
				data[i][23] = list.get(i).allerror;
				data[i][24] = list.get(i).allfoul;
				data[i][25] = list.get(i).allpoint;
				data[i][26] = list.get(i).doubledouble;
				
				data[i][27] = list.get(i).alloffensivereboundrate;
				data[i][28] = list.get(i).alldefensivereboundrate;
				data[i][29] = list.get(i).allstealrate;
				data[i][30] = list.get(i).allassistrate;
				data[i][31] = list.get(i).allblockrate;
				data[i][32] = list.get(i).allerrorrate;
				data[i][33] = list.get(i).allusage;
				data[i][34] = list.get(i).allgmsc;;
				data[i][35] = list.get(i).allefficiency;
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).name;
				data[i][2] = list.get(i).team;
				data[i][3] = list.get(i).position;
				data[i][4] = list.get(i).gameplay;
				data[i][5] = list.get(i).gamestart;
				data[i][6] = list.get(i).minute;
				
				data[i][7] = list.get(i).shootmade;
				data[i][8] = list.get(i).shoot;
				data[i][9] = list.get(i).fieldgoalpercent;
				data[i][10] = list.get(i).realshootpercent;
				
				data[i][11] = list.get(i).threepointmade;
				data[i][12] = list.get(i).threepoint;
				data[i][13] = list.get(i).threepointpercent;
				
				data[i][14] = list.get(i).freethrowmade;
				data[i][15] = list.get(i).freethrow;
				data[i][16] = list.get(i).freethrowpercent;
				
				data[i][17] = list.get(i).offensiverebound;
				data[i][18] = list.get(i).defensiverebound;
				data[i][19] = list.get(i).rebound;
				
				data[i][20] = list.get(i).assist;
				data[i][21] = list.get(i).steal;
				data[i][22] = list.get(i).block;
				data[i][23] = list.get(i).error;
				data[i][24] = list.get(i).foul;
				data[i][25] = list.get(i).point;
				data[i][26] = list.get(i).doubledouble;
				
				data[i][27] = list.get(i).offensivereboundrate;
				data[i][28] = list.get(i).defensivereboundrate;
				data[i][29] = list.get(i).stealrate;
				data[i][30] = list.get(i).assistrate;
				data[i][31] = list.get(i).blockrate;
				data[i][32] = list.get(i).errorrate;
				data[i][33] = list.get(i).usage;
				data[i][34] = list.get(i).gmsc;;
				data[i][35] = list.get(i).efficiency;
			}
		}
		this.showTable(data);
	}
	/**
	 * 显示表格
	 * @param data 表格数据
	 */
	private void showTable(Object[][] data) {
		this.remove(table);
		String[] subTitle = {"编号", "球员名称", "所属球队", "位置",//0-6
				 "参赛场数", "先发场数","在场时间",
				 //投篮7-10
				 "命中数","出手数","命中率","真实命中率",
				 //三分11-13
				 "命中数","出手数","命中率",
				 //罚球14-16
				 "命中数","出手数","命中率",
				 //篮板17-19
				 "进攻篮板数", "防守篮板数", "篮板数",
				 //20-26
				 "助攻数", "抢断数", "盖帽数", "失误数", "犯规数","得分","两双",
				 //效率27-35
				 "进攻篮板", "防守篮板", "抢断", "助攻","盖帽","失误","使用","GmSc","效率"
				 };
		
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			 public Class<?> getColumnClass(int column) {  
			        Class<?> returnValue;  
			        if ((column >= 0) && (column < getColumnCount())) {  
			            returnValue = getValueAt(0,column).getClass();  
			        } else {  
			            returnValue = Object.class;  
			        }  
			        return returnValue;  
			    }  
		};
		
		dm.setDataVector(data, subTitle);
		table = new JTable();
		table.setColumnModel(new GroupableTableColumnModel());
        table.setTableHeader(new GroupableTableHeader((GroupableTableColumnModel)table.getColumnModel()));
        table.setModel(dm);
        
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dm);  
        table.setRowSorter(sorter); 
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//---------------------------------------------------------------------------
        GroupableTableColumnModel cm = (GroupableTableColumnModel)table.getColumnModel();
        
        ColumnGroup group1 = new ColumnGroup("基本信息");
        group1.add(cm.getColumn(1));
        group1.add(cm.getColumn(2));
        group1.add(cm.getColumn(3));
        group1.add(cm.getColumn(4));
        group1.add(cm.getColumn(5));
        group1.add(cm.getColumn(6));
        
        
        ColumnGroup group2 = new ColumnGroup("投篮");
        group2.add(cm.getColumn(7));
        group2.add(cm.getColumn(8));
        group2.add(cm.getColumn(9));
        group2.add(cm.getColumn(10));
        
        ColumnGroup group3 = new ColumnGroup("三分");
        group3.add(cm.getColumn(11));
        group3.add(cm.getColumn(12));
        group3.add(cm.getColumn(13));
        
        ColumnGroup group4 = new ColumnGroup("罚球");
        group4.add(cm.getColumn(14));
        group4.add(cm.getColumn(15));
        group4.add(cm.getColumn(16));
        
        ColumnGroup group5 = new ColumnGroup("篮板");
        group5.add(cm.getColumn(17));
        group5.add(cm.getColumn(18));
        group5.add(cm.getColumn(19));
        
        ColumnGroup group6 = new ColumnGroup("效率");
        group6.add(cm.getColumn(27));
        group6.add(cm.getColumn(28));
        group6.add(cm.getColumn(29));
        group6.add(cm.getColumn(30));
        group6.add(cm.getColumn(31));
        group6.add(cm.getColumn(32));
        group6.add(cm.getColumn(33));
        group6.add(cm.getColumn(34));
        group6.add(cm.getColumn(35));
        
        @SuppressWarnings("unused")
		GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
		cm.addColumnGroup(group1);
		cm.addColumnGroup(group2);
		cm.addColumnGroup(group3);
		cm.addColumnGroup(group4);
		cm.addColumnGroup(group5);
		cm.addColumnGroup(group6);
		//列色-------------------------------------------------------
		try
        {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer()
            {
              public Component getTableCellRendererComponent(JTable table,
                  Object value, boolean isSelected, boolean hasFocus,
                  int row, int column)
              {
                if(column%2 == 0)
                  setBackground(Color.white); //设置奇数行底色
                else if(column%2 == 1)
                  setBackground(new Color(206,231,255));  //设置偶数行底色
                return super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column); }
            };
                for(int i = 0; i < table.getColumnCount(); i++) {
                  cm.getColumn(i).setCellRenderer(tcr);
                  
          }
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }
		//表格监听
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedColumn() == 1) {
					String str = (String)table.getValueAt(table.getSelectedRow(), 1);
					try {
						new PlayerFrame(bl.getOnePlayer(str));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		table.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {  
		        int row = table.rowAtPoint(e.getPoint());  
		        int col = table.columnAtPoint(e.getPoint());  
		        if (row > -1 && col > -1) {  
		            Object value = table.getValueAt(row, col);  
		            if (value != null && !value.equals(""))  
		                table.setToolTipText(value.toString());//悬浮显示单元格内容  
		            else  
		                table.setToolTipText(null);//关闭提示  
		        }  
		        if (col == 1)
		        	table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变手
		        else
		        	table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));//鼠标默认
		    }  
		});
        //------------------------------------------------------------
		sp.setViewportView(table);
		revalidate();
	}
}

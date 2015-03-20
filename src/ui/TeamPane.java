package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import vo.TeamVO;
import businesslogic.TeamBL;
import businesslogicservice.TeamBLService;

/**
 * 
 * @date 2015年3月20日
 * @author stk
 *
 */

/*
 * 球队面板
 */
@SuppressWarnings("serial")
public class TeamPane extends JPanel{
	private TeamBLService bl;
	private JTable table;
	private JScrollPane sp;
	private JPanel pane;
	private JLabel label1;
	private JLabel label2;
	private JComboBox<String> mode;
	private JComboBox<String> region;
	private JButton search;
	//--------------------------------------------------------------
	public TeamPane() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(0, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		//---------------------------------------
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		pane.setOpaque(false);
		
		label1 = new JLabel("数据类型：");
		mode = new JComboBox<String>(new String[]{"总数", "场均"});
		label2 = new JLabel("地区：");
		region = new JComboBox<String>(Region.valueOf("ATLANTIC").getRegion());
		search = new JButton("搜索");
		
		pane.add(label1);
		pane.add(mode);
		pane.add(label2);
		pane.add(region);
		pane.add(search);
		
		this.add(pane, BorderLayout.NORTH);
		//----------------------------------------
		table = new JTable();
		sp = new JScrollPane(table);
		sp.setOpaque(false);
		this.add(sp, BorderLayout.CENTER);
		bl = new TeamBL();
		this.setData(bl.getTeams((String)region.getSelectedItem()));
		//监听
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamPane.this.setData(bl.getTeams((String)region.getSelectedItem()));
			}
		});
		mode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mode) {
					TeamPane.this.setData(bl.getTeams((String)region.getSelectedItem()));
				}
			}
		});
	}
	//------------------------------------------------------------------------
	private void setData(ArrayList<TeamVO> list) {
		Object[][] data = new Object[list.size()][31];
		if (mode.getSelectedItem() == "总数") {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).fullName;
				data[i][2] = list.get(i).abbName;
				data[i][3] = list.get(i).winsNum;
				data[i][4] = list.get(i).gamesNum;
				data[i][5] = list.get(i).winsRate;
				
				data[i][6] = list.get(i).allshootingHit;
				data[i][7] = list.get(i).allshooting;
				data[i][8] = list.get(i).allshootingHitRate;
				
				data[i][9] = list.get(i).allthreePointHits;
				data[i][10] = list.get(i).allthreePoint;
				data[i][11] = list.get(i).allthreePointHitRate;
				
				data[i][12] = list.get(i).allfreeThrowHit;
				data[i][13] = list.get(i).allfreeThrow;
				data[i][14] = list.get(i).allthreePointHitRate;
				
				data[i][15] = list.get(i).alloffensiveRebounds;
				data[i][16] = list.get(i).alldefensiveRebounds;
				data[i][17] = list.get(i).allrebounds;
				
				data[i][18] = list.get(i).allassists;
				data[i][19] = list.get(i).allsteal;
				data[i][20] = list.get(i).allcaps;
				data[i][21] = list.get(i).allturnovers;
				data[i][22] = list.get(i).allfouls;
				data[i][23] = list.get(i).allscores;
				data[i][24] = list.get(i).allattackRound;
				
				data[i][25] = list.get(i).allattackEfficiency;
				data[i][26] = list.get(i).alldefenceEfficiency;
				data[i][27] = list.get(i).alloffensivereboundsEfficiency;
				data[i][28] = list.get(i).alldefensivereboundsEfficiency;
				data[i][29] = list.get(i).allstealEfficiency;
				data[i][30] = list.get(i).allassistEfficiency;
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).fullName;
				data[i][2] = list.get(i).abbName;
				data[i][3] = list.get(i).winsNum;
				data[i][4] = list.get(i).gamesNum;
				data[i][5] = list.get(i).winsRate;
				
				data[i][6] = list.get(i).shootingHit;
				data[i][7] = list.get(i).shooting;
				data[i][8] = list.get(i).shootingHitRate;
				
				data[i][9] = list.get(i).threePointHits;
				data[i][10] = list.get(i).threePoint;
				data[i][11] = list.get(i).threePointHitRate;
				
				data[i][12] = list.get(i).freeThrowHit;
				data[i][13] = list.get(i).freeThrow;
				data[i][14] = list.get(i).threePointHitRate;
				
				data[i][15] = list.get(i).offensiveRebounds;
				data[i][16] = list.get(i).defensiveRebounds;
				data[i][17] = list.get(i).rebounds;
				
				data[i][18] = list.get(i).assists;
				data[i][19] = list.get(i).steal;
				data[i][20] = list.get(i).caps;
				data[i][21] = list.get(i).turnovers;
				data[i][22] = list.get(i).fouls;
				data[i][23] = list.get(i).scores;
				data[i][24] = list.get(i).attackRound;
				
				data[i][25] = list.get(i).attackEfficiency;
				data[i][26] = list.get(i).defenceEfficiency;
				data[i][27] = list.get(i).offensivereboundsEfficiency;
				data[i][28] = list.get(i).defensivereboundsEfficiency;
				data[i][29] = list.get(i).stealEfficiency;
				data[i][30] = list.get(i).assistEfficiency;
			}
			
		}
		this.showTable(data);
	}
	
	private void showTable(Object[][] data) {
		this.remove(table);
		
		DefaultTableModel dm = new DefaultTableModel() {
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
		String[] subTitle = {"编号", "球队名称", "队名缩写",
							 "胜利场数", "比赛场数", "胜率",
							 //投篮 6
							 "命中数", "出手数", "命中率",
							 //三分 9
							 "命中数", "出手数", "命中率",
							 //罚球 12
							 "命中数", "出手数", "命中率",
							 //篮板 15
							 "进攻篮板数", "防守篮板数", "篮板数",
							 //18
							 "助攻数", "抢断数", "盖帽数", "失误数", "犯规数", "比赛得分", "进攻回合",
							 //效率 25
							 "进攻", "防守", "进攻篮板", "防守篮板", "抢断", "助攻"
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
		
		ColumnGroup group1 = new ColumnGroup("胜率");
		group1.add(cm.getColumn(3));
		group1.add(cm.getColumn(4));
		group1.add(cm.getColumn(5));
		
		ColumnGroup group2 = new ColumnGroup("投篮");
		group2.add(cm.getColumn(6));
		group2.add(cm.getColumn(7));
		group2.add(cm.getColumn(8));
		
		ColumnGroup group3 = new ColumnGroup("三分");
		group3.add(cm.getColumn(9));
		group3.add(cm.getColumn(10));
		group3.add(cm.getColumn(11));
		
		ColumnGroup group4 = new ColumnGroup("罚球");
		group4.add(cm.getColumn(12));
		group4.add(cm.getColumn(13));
		group4.add(cm.getColumn(14));
		
		ColumnGroup group5 = new ColumnGroup("篮板");
		group5.add(cm.getColumn(15));
		group5.add(cm.getColumn(16));
		group5.add(cm.getColumn(17));
		
		ColumnGroup group6 = new ColumnGroup("效率 ");
		group6.add(cm.getColumn(25));
		group6.add(cm.getColumn(26));
		group6.add(cm.getColumn(27));
		group6.add(cm.getColumn(28));
		group6.add(cm.getColumn(29));
		group6.add(cm.getColumn(30));
		
		@SuppressWarnings("unused")
		GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
		cm.addColumnGroup(group1);
		cm.addColumnGroup(group2);
		cm.addColumnGroup(group3);
		cm.addColumnGroup(group4);
		cm.addColumnGroup(group5);
		cm.addColumnGroup(group6);
		//列色------------------------------------------------------------
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
				if (e.getClickCount() == 2) {
					if (table.getSelectedColumn() == 1) {
						String str = (String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
						new TeamFrame(bl.getOneTeam(str));
					}
				}
			}
		});
		//---------------------------------------------------
		sp.setViewportView(table);
		revalidate();
	}
}

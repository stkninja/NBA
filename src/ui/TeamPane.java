package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ui.tableheader.ColumnGroup;
import ui.tableheader.GroupableTableColumnModel;
import ui.tableheader.GroupableTableHeader;
import businesslogic.TeamBL;
import businesslogicservice.TeamBLService;

/**
 * 
 * @date 2015年3月16日
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
	private JComboBox<String> mode;
	private JComboBox<String> region;
	private JButton search;
	//--------------------------------------------------------------
	public TeamPane() {
		this.setLayout(new BorderLayout(0, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		//---------------------------------------
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		
		mode = new JComboBox<String>(new String[]{"总数", "场均"});
		region = new JComboBox<String>(Region.valueOf("ATLANTIC").getRegion());
//		region.setPreferredSize(new Dimension(170,30));
		search = new JButton("搜索");
		pane.add(mode);
		pane.add(region);
		pane.add(search);
		
		this.add(pane, BorderLayout.NORTH);
		//----------------------------------------
		showTable();
		//监听
		search.addActionListener(new SearchListener());
	}
	//------------------------------------------------------------------------
	private void showTable() {
		DefaultTableModel dm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
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
		
		dm.setDataVector(new Object[][]{{"111", "2222", "3"}}, subTitle);
		table = new JTable();
		table.setColumnModel(new GroupableTableColumnModel());
        table.setTableHeader(new GroupableTableHeader((GroupableTableColumnModel)table.getColumnModel()));
        table.setModel(dm);
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
		//------------------------------------------------------------
		sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
	}
	//-------------------------------------------------------------------------
	private class SearchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bl = new TeamBL();
		}
	}
}

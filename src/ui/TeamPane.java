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
							 //投篮
							 "命中数", "出手数", "命中率",
							 //三分
							 "命中数", "出手数", "命中率",
							 //罚球
							 "命中数", "出手数", "命中率",
							 //篮板
							 "进攻篮板数", "防守篮板数", "篮板数",
							 
							 "助攻数",
							 "抢断数",
							 //盖帽34-35
							 "盖帽数",
							 //失误36-37
							 "总数",
							 //犯规38-39
							 "总数",
							 //比赛得分40-41
							 "总数",
							 //进攻回合42-43
							 "总数", "场均",
							 //效率44-55
							 "进攻", "防守", "进攻篮板", "防守篮板", "抢断", "助攻",//总数
							 "进攻", "防守", "进攻篮板", "防守篮板", "抢断", "助攻"//场均
							 };
		
		dm.setDataVector(new Object[][]{{"111", "2222", "3"}}, subTitle);
		table = new JTable();
		table.setColumnModel(new GroupableTableColumnModel());
        table.setTableHeader(new GroupableTableHeader((GroupableTableColumnModel)table.getColumnModel()));
        table.setModel(dm);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//---------------------------------------------------------------------------
		GroupableTableColumnModel cm = (GroupableTableColumnModel)table.getColumnModel();
		
		ColumnGroup group0 = new ColumnGroup("胜率");
		group0.add(cm.getColumn(3));
		group0.add(cm.getColumn(4));
		group0.add(cm.getColumn(5));
		
		ColumnGroup group1 = new ColumnGroup("总数");
		group1.add(cm.getColumn(6));
		group1.add(cm.getColumn(7));
		group1.add(cm.getColumn(8));
		ColumnGroup group2 = new ColumnGroup("场均");
		group2.add(cm.getColumn(9));
		group2.add(cm.getColumn(10));
		group2.add(cm.getColumn(11));
		ColumnGroup groupA = new ColumnGroup("投篮");
		groupA.add(group1);
		groupA.add(group2);
		
		ColumnGroup group3 = new ColumnGroup("总数");
		group3.add(cm.getColumn(12));
		group3.add(cm.getColumn(13));
		group3.add(cm.getColumn(14));
		ColumnGroup group4 = new ColumnGroup("场均");
		group4.add(cm.getColumn(15));
		group4.add(cm.getColumn(16));
		group4.add(cm.getColumn(17));
		ColumnGroup groupB = new ColumnGroup("三分");
		groupB.add(group3);
		groupB.add(group4);
		
		ColumnGroup group5 = new ColumnGroup("总数");
		group5.add(cm.getColumn(18));
		group5.add(cm.getColumn(19));
		group5.add(cm.getColumn(20));
		ColumnGroup group6 = new ColumnGroup("场均");
		group6.add(cm.getColumn(21));
		group6.add(cm.getColumn(22));
		group6.add(cm.getColumn(23));
		ColumnGroup groupC = new ColumnGroup("罚球");
		groupC.add(group5);
		groupC.add(group6);
		
		ColumnGroup group7 = new ColumnGroup("总数");
		group7.add(cm.getColumn(24));
		group7.add(cm.getColumn(25));
		group7.add(cm.getColumn(26));
		ColumnGroup group8 = new ColumnGroup("场均");
		group8.add(cm.getColumn(27));
		group8.add(cm.getColumn(28));
		group8.add(cm.getColumn(29));
		ColumnGroup groupD = new ColumnGroup("篮板");
		groupD.add(group7);
		groupD.add(group8);
		
		ColumnGroup groupE = new ColumnGroup("助攻");
		groupE.add(cm.getColumn(30));
		groupE.add(cm.getColumn(31));
		
		ColumnGroup groupF = new ColumnGroup("抢断");
		groupF.add(cm.getColumn(32));
		groupF.add(cm.getColumn(33));
		
		ColumnGroup groupG = new ColumnGroup("盖帽");
		groupG.add(cm.getColumn(34));
		groupG.add(cm.getColumn(35));
		
		ColumnGroup groupH = new ColumnGroup("失误");
		groupH.add(cm.getColumn(36));
		groupH.add(cm.getColumn(37));
		
		ColumnGroup groupI = new ColumnGroup("犯规");
		groupI.add(cm.getColumn(38));
		groupI.add(cm.getColumn(39));
		
		ColumnGroup groupJ = new ColumnGroup("比赛得分");
		groupJ.add(cm.getColumn(40));
		groupJ.add(cm.getColumn(41));
		
		ColumnGroup groupK = new ColumnGroup("进攻回合");
		groupK.add(cm.getColumn(42));
		groupK.add(cm.getColumn(43));
		
		ColumnGroup group9 = new ColumnGroup("总数");
		group9.add(cm.getColumn(44));
		group9.add(cm.getColumn(45));
		group9.add(cm.getColumn(46));
		group9.add(cm.getColumn(47));
		group9.add(cm.getColumn(48));
		group9.add(cm.getColumn(49));
		ColumnGroup group10 = new ColumnGroup("场均");
		group10.add(cm.getColumn(50));
		group10.add(cm.getColumn(51));
		group10.add(cm.getColumn(52));
		group10.add(cm.getColumn(53));
		group10.add(cm.getColumn(54));
		group10.add(cm.getColumn(55));
		ColumnGroup groupL = new ColumnGroup("效率");
		groupL.add(group9);
		groupL.add(group10);
		
		@SuppressWarnings("unused")
		GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
		cm.addColumnGroup(group0);
		cm.addColumnGroup(groupA);
		cm.addColumnGroup(groupB);
		cm.addColumnGroup(groupC);
		cm.addColumnGroup(groupD);
		cm.addColumnGroup(groupE);
		cm.addColumnGroup(groupF);
		cm.addColumnGroup(groupG);
		cm.addColumnGroup(groupH);
		cm.addColumnGroup(groupI);
		cm.addColumnGroup(groupJ);
		cm.addColumnGroup(groupK);
		cm.addColumnGroup(groupL);
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

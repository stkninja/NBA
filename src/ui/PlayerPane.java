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

/**
 * 
 * @date 2015年3月11日
 * @author stk
 *
 */

/*
 * 球员面板
 */
@SuppressWarnings("serial")
public class PlayerPane extends JPanel{
	private JTable table;
	private JScrollPane sp;
	private JPanel pane;
	private JComboBox<String> region;
	private JComboBox<String> team;
	private JComboBox<String> position;
	private JButton search;
	//--------------------------------------------------------------
	public PlayerPane() {
		this.setLayout(new BorderLayout(0, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		//------------------------------------------------------------
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		
		region = new JComboBox<String>(Region.valueOf("ATLANTIC").getRegion());
		region.setPreferredSize(new Dimension(170,30));
		team = new JComboBox<String>();
		team.setPreferredSize(new Dimension(170,30));
		team.setEnabled(false);
		String[] positionList = {"控球后卫(PG)", "得分后卫(SP)", "小前锋(SF)", "大前锋(PF)", "中锋(C)"};
		position = new JComboBox<String>(positionList);
		position.setPreferredSize(new Dimension(170,30));
		search = new JButton("搜索");
		
		pane.add(region);
		pane.add(team);
		pane.add(position);
		pane.add(search);
		
		this.add(pane, BorderLayout.NORTH);
		//----------------------------------------
		showTable();
		//监听
		search.addActionListener(new SearchListener());
		region.addActionListener(new ComboBoxListener());
	}
	//-----------------------------------------------------------------
	private void showTable() {
		DefaultTableModel dm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] subTitle = {"编号", "球员名称", "所属球队", "位置",//0-6
							 "分区", "参赛场数", "先发场数",
							 //篮板7-12
							 "进攻篮板数", "防守篮板数", "篮板数",//总数
							 "进攻篮板数", "防守篮板数", "篮板数",//场均
							 //助攻13-14
							 "总数", "场均",
							 //在场时间15-16
							 "总数", "场均",
							 //进攻17-18
							 "总数", "场均",
							 //防守19-20
							 "总数", "场均",
							 //抢断21-22
							 "总数", "场均",
							 //盖帽23-24
							 "总数", "场均",
							 //失误25-26
							 "总数", "场均",
							 //犯规
							 "总数", "场均",
							 //得分
							 "总数", "场均",
							 //投篮出手
							 "总数", "场均",
							 //
							 "总数", "场均",
							 //
							 "总数", "场均",
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
        
        
        
        
        
        
        
        //------------------------------------------------------------
      	sp = new JScrollPane(table);
      	this.add(sp, BorderLayout.CENTER);
	}
	//-----------------------------------------------------------------
	private class SearchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	private class ComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == region) {
				if (region.getSelectedItem().equals("ALL")) {
					team.removeAllItems();;
					team.setEnabled(false);
				} else {
					team.removeAllItems();
					String[] list = Region.valueOf((String)region.getSelectedItem()).getTeam();
					for (int i = 0; i < list.length; i++) {
						team.addItem(list[i]);
					}
					team.setEnabled(true);
				}
			}
		}
	}
}

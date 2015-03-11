package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
		this.setLayout(new BorderLayout());
		//------------------------------------------------------------
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		String[] regionList= {"东部", "西部"};
		region = new JComboBox<String>(regionList);
		String[] teamList = {"doubi", "hehe"};
		team = new JComboBox<String>(teamList);
		String[] positionList = {"控球后卫(PG)", "得分后卫(SP)", "小前锋(SF)", "大前锋(PF)", "中锋(C)"};
		position = new JComboBox<String>(positionList);
		search = new JButton("搜索");
		
		pane.add(region);
		pane.add(team);
		pane.add(position);
		pane.add(search);
		
		this.add(pane, BorderLayout.NORTH);
		//----------------------------------------
		String[] title = {"1", "2", "3"};
		String[][] ob = {{"$$", "fdg", "12"}};
		table = new JTable(ob, title);
		sp = new JScrollPane(table);
		
		this.add(sp, BorderLayout.CENTER);
	}
}

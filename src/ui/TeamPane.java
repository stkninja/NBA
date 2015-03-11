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
 * @date 2015��3��11��
 * @author stk
 *
 */

/*
 * ������
 */
@SuppressWarnings("serial")
public class TeamPane extends JPanel{
	private JTable table;
	private JScrollPane sp;
	private JPanel pane;
	private JComboBox<String> region;
	private JButton search;
	//--------------------------------------------------------------
	public TeamPane() {
		this.setLayout(new BorderLayout());
		//---------------------------------------
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		String[] str = {"����", "����"};
		region = new JComboBox<String>(str);
		search = new JButton("����");
		pane.add(region);
		pane.add(search);
		
		this.add(pane, BorderLayout.NORTH);
		//----------------------------------------
		String[] title = {"a", "b", "c"};
		String[][] ob = {{"qwewqe", "dsfa", "xcv"}, {"433", "32a", "77"}};
		table = new JTable(ob, title);
		sp = new JScrollPane(table);
		
		this.add(sp, BorderLayout.CENTER);
	}
}

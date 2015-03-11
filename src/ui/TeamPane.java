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
		this.setLayout(new BorderLayout(0, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		//---------------------------------------
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		//FIXME
		//�����������
		String[] str = {"ȫ������", "����������������", "�в�����������", "��������������", "̫ƽ������������", "��������������", "��������������"};
		region = new JComboBox<String>(str);
		region.setPreferredSize(new Dimension(150,30));
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
		//����
		search.addActionListener(new SearchListener());
	}
	//------------------------------------------------------------------------
	private class SearchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}

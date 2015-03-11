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
 * ��Ա���
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
		//FIXME
		//�����������
		String[] regionList= {"ȫ������", "����������������", "�в�����������", "��������������", "̫ƽ������������", "��������������", "��������������"};
		region = new JComboBox<String>(regionList);
		region.setPreferredSize(new Dimension(150,30));
		team = new JComboBox<String>();
		team.setPreferredSize(new Dimension(150,30));
		team.setEnabled(false);
		String[] positionList = {"�������(PG)", "�÷ֺ���(SP)", "Сǰ��(SF)", "��ǰ��(PF)", "�з�(C)"};
		position = new JComboBox<String>(positionList);
		position.setPreferredSize(new Dimension(150,30));
		search = new JButton("����");
		
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
		//����
		search.addActionListener(new SearchListener());
		region.addActionListener(new ComboBoxListener());
	}
	//-----------------------------------------------------------------
	private class SearchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	private class ComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == region) {
				if (region.getSelectedItem().equals("ȫ������")) {
					team.setEnabled(false);
				} else {
					team.setEnabled(true);
				}
			}
		}
	}
}

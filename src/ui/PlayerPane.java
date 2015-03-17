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
		
		region = new JComboBox<String>(Region.valueOf("ATLANTIC").getRegion());
		region.setPreferredSize(new Dimension(170,30));
		team = new JComboBox<String>();
		team.setPreferredSize(new Dimension(170,30));
		team.setEnabled(false);
		String[] positionList = {"�������(PG)", "�÷ֺ���(SP)", "Сǰ��(SF)", "��ǰ��(PF)", "�з�(C)"};
		position = new JComboBox<String>(positionList);
		position.setPreferredSize(new Dimension(170,30));
		search = new JButton("����");
		
		pane.add(region);
		pane.add(team);
		pane.add(position);
		pane.add(search);
		
		this.add(pane, BorderLayout.NORTH);
		//----------------------------------------
		showTable();
		//����
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
		String[] subTitle = {"���", "��Ա����", "�������", "λ��",//0-6
							 "����", "��������", "�ȷ�����",
							 //����7-12
							 "����������", "����������", "������",//����
							 "����������", "����������", "������",//����
							 //����13-14
							 "����", "����",
							 //�ڳ�ʱ��15-16
							 "����", "����",
							 //����17-18
							 "����", "����",
							 //����19-20
							 "����", "����",
							 //����21-22
							 "����", "����",
							 //��ñ23-24
							 "����", "����",
							 //ʧ��25-26
							 "����", "����",
							 //����
							 "����", "����",
							 //�÷�
							 "����", "����",
							 //Ͷ������
							 "����", "����",
							 //
							 "����", "����",
							 //
							 "����", "����",
							 };
		
		dm.setDataVector(new Object[][]{{"111", "2222", "3"}}, subTitle);
		table = new JTable();
		table.setColumnModel(new GroupableTableColumnModel());
        table.setTableHeader(new GroupableTableHeader((GroupableTableColumnModel)table.getColumnModel()));
        table.setModel(dm);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//---------------------------------------------------------------------------
        GroupableTableColumnModel cm = (GroupableTableColumnModel)table.getColumnModel();
        
        ColumnGroup group0 = new ColumnGroup("ʤ��");
        
        
        
        
        
        
        
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

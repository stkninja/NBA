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

import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;
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
	private PlayerBLService bl;
	private JTable table;
	private JScrollPane sp;
	private JPanel pane;
	private JComboBox<String> mode;
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
		mode = new JComboBox<String>(new String[]{"����", "����"});
		
		region = new JComboBox<String>(Region.valueOf("ATLANTIC").getRegion());
		region.setPreferredSize(new Dimension(170,30));
		team = new JComboBox<String>();
		team.setPreferredSize(new Dimension(170,30));
		team.setEnabled(false);
		String[] positionList = {"�������(PG)", "�÷ֺ���(SP)", "Сǰ��(SF)", "��ǰ��(PF)", "�з�(C)"};
		position = new JComboBox<String>(positionList);
		position.setPreferredSize(new Dimension(170,30));
		search = new JButton("����");
		
		pane.add(mode);
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
							 "��������", "�ȷ�����","�ڳ�ʱ��",
							 //Ͷ��7-10
							 "������","������","������","��ʵ������",
							 //����11-13
							 "������","������","������",
							 //����14-16
							 "������","������","������",
							 //����17-19
							 "����������", "����������", "������",
							 //20-26
							 "������", "������", "��ñ��", "ʧ����", "������","�÷�","��˫",
							 //Ч��27-35
							 "��������", "��������", "����", "����","��ñ","ʧ��","ʹ��","GmSc","Ч��"
							 };
		
		dm.setDataVector(new Object[][]{{"111", "2222", "3"}}, subTitle);
		table = new JTable();
		table.setColumnModel(new GroupableTableColumnModel());
        table.setTableHeader(new GroupableTableHeader((GroupableTableColumnModel)table.getColumnModel()));
        table.setModel(dm);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//---------------------------------------------------------------------------
        GroupableTableColumnModel cm = (GroupableTableColumnModel)table.getColumnModel();
        
        ColumnGroup group1 = new ColumnGroup("������Ϣ");
        group1.add(cm.getColumn(1));
        group1.add(cm.getColumn(2));
        group1.add(cm.getColumn(3));
        group1.add(cm.getColumn(4));
        group1.add(cm.getColumn(5));
        group1.add(cm.getColumn(6));
        
        
        ColumnGroup group2 = new ColumnGroup("Ͷ��");
        group2.add(cm.getColumn(7));
        group2.add(cm.getColumn(8));
        group2.add(cm.getColumn(9));
        group2.add(cm.getColumn(10));
        
        ColumnGroup group3 = new ColumnGroup("����");
        group3.add(cm.getColumn(11));
        group3.add(cm.getColumn(12));
        group3.add(cm.getColumn(13));
        
        ColumnGroup group4 = new ColumnGroup("����");
        group4.add(cm.getColumn(14));
        group4.add(cm.getColumn(15));
        group4.add(cm.getColumn(16));
        
        ColumnGroup group5 = new ColumnGroup("����");
        group5.add(cm.getColumn(17));
        group5.add(cm.getColumn(18));
        group5.add(cm.getColumn(19));
        
        ColumnGroup group6 = new ColumnGroup("Ч��");
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
        //------------------------------------------------------------
      	sp = new JScrollPane(table);
      	this.add(sp, BorderLayout.CENTER);
	}
	//-----------------------------------------------------------------
	private class SearchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bl = new PlayerBL();
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

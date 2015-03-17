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
 * @date 2015��3��16��
 * @author stk
 *
 */

/*
 * ������
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
		
		mode = new JComboBox<String>(new String[]{"����", "����"});
		region = new JComboBox<String>(Region.valueOf("ATLANTIC").getRegion());
//		region.setPreferredSize(new Dimension(170,30));
		search = new JButton("����");
		pane.add(mode);
		pane.add(region);
		pane.add(search);
		
		this.add(pane, BorderLayout.NORTH);
		//----------------------------------------
		showTable();
		//����
		search.addActionListener(new SearchListener());
	}
	//------------------------------------------------------------------------
	private void showTable() {
		DefaultTableModel dm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] subTitle = {"���", "�������", "������д",
							 "ʤ������", "��������", "ʤ��",
							 //Ͷ��
							 "������", "������", "������",
							 //����
							 "������", "������", "������",
							 //����
							 "������", "������", "������",
							 //����
							 "����������", "����������", "������",
							 
							 "������",
							 "������",
							 //��ñ34-35
							 "��ñ��",
							 //ʧ��36-37
							 "����",
							 //����38-39
							 "����",
							 //�����÷�40-41
							 "����",
							 //�����غ�42-43
							 "����", "����",
							 //Ч��44-55
							 "����", "����", "��������", "��������", "����", "����",//����
							 "����", "����", "��������", "��������", "����", "����"//����
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
		group0.add(cm.getColumn(3));
		group0.add(cm.getColumn(4));
		group0.add(cm.getColumn(5));
		
		ColumnGroup group1 = new ColumnGroup("����");
		group1.add(cm.getColumn(6));
		group1.add(cm.getColumn(7));
		group1.add(cm.getColumn(8));
		ColumnGroup group2 = new ColumnGroup("����");
		group2.add(cm.getColumn(9));
		group2.add(cm.getColumn(10));
		group2.add(cm.getColumn(11));
		ColumnGroup groupA = new ColumnGroup("Ͷ��");
		groupA.add(group1);
		groupA.add(group2);
		
		ColumnGroup group3 = new ColumnGroup("����");
		group3.add(cm.getColumn(12));
		group3.add(cm.getColumn(13));
		group3.add(cm.getColumn(14));
		ColumnGroup group4 = new ColumnGroup("����");
		group4.add(cm.getColumn(15));
		group4.add(cm.getColumn(16));
		group4.add(cm.getColumn(17));
		ColumnGroup groupB = new ColumnGroup("����");
		groupB.add(group3);
		groupB.add(group4);
		
		ColumnGroup group5 = new ColumnGroup("����");
		group5.add(cm.getColumn(18));
		group5.add(cm.getColumn(19));
		group5.add(cm.getColumn(20));
		ColumnGroup group6 = new ColumnGroup("����");
		group6.add(cm.getColumn(21));
		group6.add(cm.getColumn(22));
		group6.add(cm.getColumn(23));
		ColumnGroup groupC = new ColumnGroup("����");
		groupC.add(group5);
		groupC.add(group6);
		
		ColumnGroup group7 = new ColumnGroup("����");
		group7.add(cm.getColumn(24));
		group7.add(cm.getColumn(25));
		group7.add(cm.getColumn(26));
		ColumnGroup group8 = new ColumnGroup("����");
		group8.add(cm.getColumn(27));
		group8.add(cm.getColumn(28));
		group8.add(cm.getColumn(29));
		ColumnGroup groupD = new ColumnGroup("����");
		groupD.add(group7);
		groupD.add(group8);
		
		ColumnGroup groupE = new ColumnGroup("����");
		groupE.add(cm.getColumn(30));
		groupE.add(cm.getColumn(31));
		
		ColumnGroup groupF = new ColumnGroup("����");
		groupF.add(cm.getColumn(32));
		groupF.add(cm.getColumn(33));
		
		ColumnGroup groupG = new ColumnGroup("��ñ");
		groupG.add(cm.getColumn(34));
		groupG.add(cm.getColumn(35));
		
		ColumnGroup groupH = new ColumnGroup("ʧ��");
		groupH.add(cm.getColumn(36));
		groupH.add(cm.getColumn(37));
		
		ColumnGroup groupI = new ColumnGroup("����");
		groupI.add(cm.getColumn(38));
		groupI.add(cm.getColumn(39));
		
		ColumnGroup groupJ = new ColumnGroup("�����÷�");
		groupJ.add(cm.getColumn(40));
		groupJ.add(cm.getColumn(41));
		
		ColumnGroup groupK = new ColumnGroup("�����غ�");
		groupK.add(cm.getColumn(42));
		groupK.add(cm.getColumn(43));
		
		ColumnGroup group9 = new ColumnGroup("����");
		group9.add(cm.getColumn(44));
		group9.add(cm.getColumn(45));
		group9.add(cm.getColumn(46));
		group9.add(cm.getColumn(47));
		group9.add(cm.getColumn(48));
		group9.add(cm.getColumn(49));
		ColumnGroup group10 = new ColumnGroup("����");
		group10.add(cm.getColumn(50));
		group10.add(cm.getColumn(51));
		group10.add(cm.getColumn(52));
		group10.add(cm.getColumn(53));
		group10.add(cm.getColumn(54));
		group10.add(cm.getColumn(55));
		ColumnGroup groupL = new ColumnGroup("Ч��");
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

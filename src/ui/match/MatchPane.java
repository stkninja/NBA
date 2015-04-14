package ui.match;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * ������ѯ����
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class MatchPane extends JPanel{
	private JTable table;
	private JScrollPane sp;
	//��������
	private JPanel pane;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JComboBox<String> comboBox1;
	private JComboBox<String> comboBox2;
	private JComboBox<String> comboBox3;
	private JTextField text1;
	private JTextField text2;
	private JButton search;
	//--------------------------------------------------
	public MatchPane() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(0, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		//��������
		pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		pane.setOpaque(false);
		label1 = new JLabel("ʱ�䣺");
		label2 = new JLabel("����");
		label3 = new JLabel("��");
		label4 = new JLabel("��");
		label5 = new JLabel("��Ա��");
		label6 = new JLabel("��ӣ�");
		comboBox1 = new JComboBox<String>(new String[]{"2010", "2011", "2012", "2013", "2014", "2015"});
		comboBox2 = new JComboBox<String>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
		comboBox3 = new JComboBox<String>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
													   "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
													   "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		text1 = new JTextField();
		text1.setPreferredSize(new Dimension(100, 20));
		text2 = new JTextField();
		text2.setPreferredSize(new Dimension(100, 20));
		search = new JButton("����");
		
		pane.add(label1);
		pane.add(comboBox1);
		pane.add(label2);
		pane.add(comboBox2);
		pane.add(label3);
		pane.add(comboBox3);
		pane.add(label4);
		pane.add(label5);
		pane.add(text1);
		pane.add(label6);
		pane.add(text2);
		
		pane.add(search);
		this.add(pane, BorderLayout.NORTH);
		//���
		table = new JTable();
		sp = new JScrollPane(table);
		this.setData();
		this.add(sp, BorderLayout.CENTER);
		//����
	}
	
	private void setData() {
		Object[][] data = new Object[][]{{0,0,0,0,0}, {1,1,1,1,1}};
		this.showTable(data);
		
	}
	/**
	 * ��ʾ���
	 * @param data �������
	 */
	private void showTable(Object[][] data) {
		this.remove(table);
		String[] subtitle = new String[]{"����", "����", "����", "�ȷ�", "�Ͷ�"};
		table = new JTable(new DefaultTableModel(data, subtitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    table.getTableHeader().setDefaultRenderer(tcr);//��ͷ����
	    table.setDefaultRenderer(Object.class, tcr);//���ݾ���
//	    table.setShowVerticalLines(false);
		sp.setViewportView(table);
		revalidate();
	}
}

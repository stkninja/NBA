package ui.match;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import vo.MatchVO;
import businesslogic.MatchBL;
import businesslogicservice.MatchBLService;

/**
 * ������ѯ����
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class MatchPane extends JPanel implements ActionListener{
	private MatchBLService bl;
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
	private JButton reset;
	//--------------------------------------------------
	public MatchPane() {
		bl = new MatchBL();
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
		label6 = new JLabel("�����д��");
		comboBox1 = new JComboBox<String>((String[])bl.getAllSeasons().toArray(new String[bl.getAllSeasons().size()]));
		comboBox2 = new JComboBox<String>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		comboBox3 = new JComboBox<String>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
													   "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
													   "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		text1 = new JTextField();
		text1.setPreferredSize(new Dimension(100, 20));
		text2 = new JTextField();
		text2.setPreferredSize(new Dimension(50, 20));
		search = new JButton("����");
		reset = new JButton("����");
		
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
		pane.add(reset);
		this.add(pane, BorderLayout.NORTH);
		//���
		table = new JTable();
		sp = new JScrollPane(table);
		this.setData(bl.getMatches((String)comboBox1.getSelectedItem()));
		this.add(sp, BorderLayout.CENTER);
		//����
		search.addActionListener(this);
		reset.addActionListener(this);
	}
	/**
	 * ����
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == search)
			this.setData(bl.getMatchesAboutTeamSeasonDatePlayer(text2.getText(), (String)comboBox1.getSelectedItem(), (String)comboBox2.getSelectedItem() +"-"+ (String)comboBox3.getSelectedItem(), text1.getText()));
		else
			this.setData(bl.getMatches((String)comboBox1.getSelectedItem()));
	}
	/**
	 * ��������
	 */
	private void setData(ArrayList<MatchVO> list) {
		Object[][] data = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).season;
			data[i][1] = list.get(i).date;
			data[i][2] = list.get(i).team1.abbName;
			data[i][3] = Math.round(list.get(i).team1.scores) +":"+ Math.round(list.get(i).team2.scores);
			data[i][4] = list.get(i).team2.abbName;
		}
		this.showTable(data);
	}
	/**
	 * ��ʾ���
	 * @param data �������
	 */
	private void showTable(Object[][] data) {
		this.remove(table);
		String[] subTitle = new String[]{"����", "����", "����", "�ȷ�", "�Ͷ�"};
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dm);
        table.setShowVerticalLines(false);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dm);
        table.setRowSorter(sorter);
		//��ɫ
	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
	    	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    		if(column%2 == 0)
	    			setBackground(Color.white); //���������е�ɫ
	    		else if(column%2 == 1)
	    			setBackground(new Color(206,231,255));  //����ż���е�ɫ 
	    		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    	}
	    };
	    for(int i = 0; i < table.getColumnCount(); i++) {
	    	table.getColumnModel().getColumn(i).setCellRenderer(tcr);
	    }
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(Object.class, tcr);//���ݾ���
		sp.setViewportView(table);
		revalidate();
	}
}

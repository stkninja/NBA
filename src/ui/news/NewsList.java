package ui.news;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import spider.htmlParser.HtmlParser;
import ui.MainFrame;

/**
 * �����б�
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class NewsList extends JPanel {
	public MainFrame main;
	private JScrollPane sp;
	private JTable table;
	private String[][] data;
	private JPanel pane;
	private CardLayout card;
	private JPanel list;
	private JPanel content;
	private JButton button1;
	private JButton button2;
	/**
	 * 
	 * @param main �����
	 */
	public NewsList(MainFrame main) {
		this.main = main;
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5, 50, 20, 0));
		this.initTop();
		this.setData();
		//�б�
		list = new JPanel(new BorderLayout());
		list.setOpaque(false);
		table = new JTable();
		sp = new JScrollPane(table);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		sp.setBorder(new EmptyBorder(0, 0, 0, 0));
		list.add(sp, BorderLayout.CENTER);
		this.showTable();
		//����
		content = new JPanel();
		
		card=new CardLayout();
		pane = new JPanel(card);
		pane.setOpaque(false);
		pane.add(list, "list");
		pane.add(content, "content");
		this.add(pane, BorderLayout.CENTER);
	}
	private void initTop() {
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		top.setOpaque(false);
		
		button1 = new JButton();
		this.setIcon(button1, "data/pic/back1.png", "data/pic/back2.png");
		button1.setEnabled(false);
		button2 = new JButton();
		this.setIcon(button2, "data/pic/refresh1.png", "data/pic/refresh2.png");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pane, "list");
				button1.setEnabled(false);
				button2.setEnabled(true);
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewsList.this.setData();
			}
		});
		
		JLabel label = new JLabel("NBA����");
		label.setFont(new Font("����", Font.BOLD, 30));
		label.setForeground(new Color(0, 0, 128));
		
		top.add(label);
		top.add(Box.createHorizontalGlue());
		top.add(button1);
		top.add(Box.createHorizontalStrut(10));
		top.add(button2);
		top.add(Box.createHorizontalStrut(10));
		this.add(top, BorderLayout.NORTH);
	}
	/**
	 * �������
	 */
	private void setData() {
		ArrayList<String> listData = new ArrayList<String>();
		String[] line = HtmlParser.getHtmlContent("http://voice.hupu.com/nba/newslist", "utf-8").split("\n");
		for (int i = 0; i < line.length; i++) {
			if (line[i].indexOf("html\"  target=\"_blank\">") != -1) {
				String temp = line[i].substring(line[i].indexOf("http"), line[i].indexOf("\"  target=\"_blank\"")) +";"+
							  line[i].substring(line[i].indexOf("html\"  target=\"_blank\">") + 23, line[i].indexOf("</a>"));
				listData.add(temp);
			}
		}
		data = new String[listData.size()][2];
		for (int i = 0; i < data.length; i++) {
			String[] temp = listData.get(i).split(";");
			data[i][0] = temp[1];
			data[i][1] = temp[0];
		}
	}
	/**
	 * ��ʾ���
	 */
	private void showTable() {
		sp.remove(table);
		DefaultTableModel dm = new DefaultTableModel(data, new String[]{"", ""}) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dm);
		table.setOpaque(false);
		table.setShowGrid(false);
		table.setFont(new Font("����", Font.PLAIN, 20));
		table.setRowHeight(40);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setFocusable(false);
		table.setSelectionForeground(Color.RED);
		table.getColumnModel().getColumn(1).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(1).setWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(0);
		
		DefaultTableCellRenderer headRender = new DefaultTableCellRenderer();
		headRender.setPreferredSize(new Dimension(0, 0));
		table.getTableHeader().setDefaultRenderer(headRender);
		
		DefaultTableCellRenderer tableRender = new DefaultTableCellRenderer();
		tableRender.setOpaque(false);
		table.setDefaultRenderer(Object.class, tableRender);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String str = (String)table.getValueAt(table.getSelectedRow(), 1);
				pane.remove(content);
				content = new News(str);
				pane.add(content, "content");
				card.show(pane, "content");
				button1.setEnabled(true);
				button2.setEnabled(false);
				revalidate();
			}
		});
		
		sp.setViewportView(table);
	}
	/**
	 * ����ͼ��
	 * @param button ͼ��
	 * @param file1 Ĭ��ͼ��·��
	 * @param file2 ��תͼ��·��
	 */
	private void setIcon(JButton button, String file1, String file2) {
		button.setPreferredSize(new Dimension(30, 30));
		button.setSize(new Dimension(30, 30));
        Image icon1 = (new ImageIcon(file1)).getImage();
        double scale1 = (double)icon1.getWidth(null) / (double)icon1.getHeight(null);
		Image temp1 = icon1.getScaledInstance((int)(button.getHeight() * scale1), button.getHeight(), Image.SCALE_DEFAULT);
		Image icon2 = (new ImageIcon(file2)).getImage();
		double scale2 = (double)icon2.getWidth(null) / (double)icon2.getHeight(null);
		Image temp2 = icon2.getScaledInstance((int)(button.getHeight() * scale2), button.getHeight(), Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(temp1));
		button.setRolloverIcon(new ImageIcon(temp2));
		button.setPressedIcon(new ImageIcon(temp2));
		button.setFocusPainted(false);//��ѡ��Ч��
        button.setOpaque(false);//͸��
		button.setContentAreaFilled(false);//���
		button.setBorderPainted(false);//�ޱ߿�
		button.setMargin(new Insets(0, 0, 0, 0));//�ޱ߾�
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//ָ�����
	}
}

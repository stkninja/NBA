package ui.News;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
 * 新闻列表
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
	 * @param main 主框架
	 */
	public NewsList(MainFrame main) {
		this.main = main;
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5, 50, 20, 0));
		this.initTop();
		this.setData();
		//列表
		list = new JPanel(new BorderLayout());
		list.setOpaque(false);
		table = new JTable();
		sp = new JScrollPane(table);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		sp.setBorder(new EmptyBorder(0, 0, 0, 0));
		list.add(sp, BorderLayout.CENTER);
		this.showTable();
		//内容
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
		
		button1 = new JButton("<");
		button1.setEnabled(false);
		button2 = new JButton("O");
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
		
		JLabel label = new JLabel("NBA新闻");
		label.setFont(new Font("黑体", Font.BOLD, 30));
		label.setForeground(new Color(0, 0, 128));
		
		top.add(label);
		top.add(Box.createHorizontalGlue());
		top.add(button1);
		top.add(button2);
		this.add(top, BorderLayout.NORTH);
	}
	/**
	 * 获得数据
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
	 * 显示表格
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
		table.setFont(new Font("楷体", Font.PLAIN, 20));
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
}

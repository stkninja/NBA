package ui.match;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.batik.transcoder.TranscoderException;

import vo.MatchVO;
import businesslogic.MatchBL;
import businesslogicservice.MatchBLService;

/**
 * 比赛查询界面
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class MatchPane extends JPanel implements ActionListener{
	private MatchBLService bl;
	private JTable table;
	private JScrollPane sp;
	//搜索界面
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
		//搜索界面
		pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
		pane.setOpaque(false);
		label1 = new JLabel("时间：");
		label1.setFont(new Font("黑体", Font.PLAIN, 14));
		label2 = new JLabel("赛季");
		label2.setFont(new Font("黑体", Font.PLAIN, 14));
		label3 = new JLabel("月");
		label3.setFont(new Font("黑体", Font.PLAIN, 14));
		label4 = new JLabel("日");
		label4.setFont(new Font("黑体", Font.PLAIN, 14));
		label5 = new JLabel("球员：");
		label5.setFont(new Font("黑体", Font.PLAIN, 14));
		label6 = new JLabel("球队缩写：");
		label6.setFont(new Font("黑体", Font.PLAIN, 14));
		comboBox1 = new JComboBox<String>((String[])bl.getAllSeasons().toArray(new String[bl.getAllSeasons().size()]));
		comboBox2 = new JComboBox<String>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		comboBox3 = new JComboBox<String>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
													   "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
													   "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		text1 = new JTextField();
		text1.setPreferredSize(new Dimension(100, 20));
		text2 = new JTextField();
		text2.setPreferredSize(new Dimension(50, 20));
		search = new JButton("搜索");
		search.setFont(new Font("楷体", Font.PLAIN, 14));
		reset = new JButton("重置");
		reset.setFont(new Font("楷体", Font.PLAIN, 14));
		
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
		//表格
		table = new JTable();
		sp = new JScrollPane(table);
		this.setData(bl.getMatches((String)comboBox1.getSelectedItem()));
		this.add(sp, BorderLayout.CENTER);
		//监听
		search.addActionListener(this);
		reset.addActionListener(this);
	}
	/**
	 * 监听
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == search)
			this.setData(bl.getMatchesAboutTeamSeasonDatePlayer(text2.getText(), (String)comboBox1.getSelectedItem(), (String)comboBox2.getSelectedItem() +"-"+ (String)comboBox3.getSelectedItem(), text1.getText()));
		else
			this.setData(bl.getMatches((String)comboBox1.getSelectedItem()));
	}
	/**
	 * 设置数据
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
	 * 显示表格
	 * @param data 表格数据
	 */
	private void showTable(Object[][] data) {
		this.remove(table);
		String[] subTitle = new String[]{"赛季", "日期", "主队", "比分", "客队"};
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dm);
        table.setShowVerticalLines(false);
        table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 14));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dm);
        table.setRowSorter(sorter);
		//列色
	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
	    	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    		if(column%2 == 0)
	    			setBackground(Color.white); //设置奇数行底色
	    		else if(column%2 == 1)
	    			setBackground(new Color(206,231,255));  //设置偶数行底色 
	    		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    	}
	    };
	    for(int i = 0; i < table.getColumnCount(); i++) {
	    	table.getColumnModel().getColumn(i).setCellRenderer(tcr);
	    }
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(Object.class, tcr);//内容居中
	    //表格监听
	    table.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		String season = (String)table.getValueAt(table.getSelectedRow(), 0);
	    		String date = (String)table.getValueAt(table.getSelectedRow(), 1);
	    		String team = (String)table.getValueAt(table.getSelectedRow(), 2);
	    		ArrayList<MatchVO> list = bl.getMatchesAboutTeamSeasonDatePlayer(team, season, date, "All");
	    		SwingUtilities.invokeLater(new Runnable() {
					@SuppressWarnings("restriction")
					public void run() {
						try {
							JFrame.setDefaultLookAndFeelDecorated(true);
							MatchFrame frame = new MatchFrame(list.get(0));
							com.sun.awt.AWTUtilities.setWindowOpacity(frame, 0.9f);//设置透明度
							com.sun.awt.AWTUtilities.setWindowShape(frame, new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));//设置圆角
						} catch (IOException | TranscoderException e) {
							e.printStackTrace();
						}
					}
			    });
	    	}
	    });
	    table.addMouseMotionListener(new MouseAdapter() {
	    	public void mouseMoved(MouseEvent e) {
	    		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变手
		    }
	    });
		sp.setViewportView(table);
		revalidate();
	}
}

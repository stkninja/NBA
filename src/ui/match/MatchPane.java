package ui.match;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.batik.transcoder.TranscoderException;

import ui.MainFrame;
import ui.TeamEnum;
import vo.MatchVO;
import businesslogic.MatchBL;
import businesslogicservice.MatchBLService;

/**
 * 比赛查询界面
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class MatchPane extends JPanel {
	public MainFrame main;
	private MatchBLService bl;
	private JTable table;
	private JScrollPane sp;
	//子窗口
	private MatchSearchPane searchPane;
	/**
	 * 
	 * @param main 主框架
	 */
	public MatchPane(MainFrame main) {
		this.main = main;
		bl = new MatchBL();
		this.setOpaque(false);
		this.setLayout(new BorderLayout(20, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		//初始搜索面板
		JTabbedPane tab = new JTabbedPane();
		searchPane= new MatchSearchPane(this);
		searchPane.setVisible(false);
		Image icon = (new ImageIcon("data/pic/search.png")).getImage();
        double scale = (double)icon.getWidth(null) / (double)icon.getHeight(null);
		Image temp1 = icon.getScaledInstance(20, (int)(20 / scale), Image.SCALE_DEFAULT);
		tab.addTab("", new ImageIcon(temp1), searchPane, "搜索");
		this.add(tab, BorderLayout.WEST);
		//表格
		table = new JTable();
		sp = new JScrollPane(table);
		searchPane.getAll();
		this.add(sp, BorderLayout.CENTER);
	}
	/**
	 * 显示表格
	 * @param data 表格数据
	 */
	public void showTable(Object[][] data) {
		this.remove(table);
		String[] subTitle = new String[]{"赛季", "日期", "主队", "比分", "客队"};
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dm);
		table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 14));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dm);
        table.setRowSorter(sorter);
		//列色
	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
	    	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    		if(row % 2 == 0)
	    			setBackground(Color.white); //设置奇数行底色
	    		else if(row % 2 == 1)
	    			setBackground(new Color(206,231,255));  //设置偶数行底色 
	    		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    	}
	    };
	    for(int i = 0; i < table.getColumnCount(); i++) {
	    	table.getColumnModel().getColumn(i).setCellRenderer(tcr);
	    }
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(Object.class, tcr);//内容居中
	    ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
	    //表格监听
	    table.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		String season = (String)table.getValueAt(table.getSelectedRow(), 0);
	    		String date = (String)table.getValueAt(table.getSelectedRow(), 1);
	    		String team = TeamEnum.valueToEnum((String)table.getValueAt(table.getSelectedRow(), 2)).abbr();
	    		ArrayList<MatchVO> list = bl.getMatchesAboutTeamSeasonDatePlayer(team, season, date, "All");
	    		try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					MatchFrame frame = new MatchFrame(list.get(0), main);
					frame.setOpacity(0.9f);
					frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
				} catch (IOException | TranscoderException e1) {
					e1.printStackTrace();
				}
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
	/**
	 * 获得搜索面板
	 * @return 搜索面板
	 */
	public MatchSearchPane getSearchPane() {
		return searchPane;
	}
	/**
	 * 刷新
	 */
	public void refresh() {
		searchPane.refresh();
	}
}

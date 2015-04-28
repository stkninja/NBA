package ui.match;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.batik.transcoder.TranscoderException;

import ui.MainFrame;
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
	public JDesktopPane dp;
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
		this.setLayout(new BorderLayout());
		//桌面
		dp = new JDesktopPane();
		dp.setOpaque(false);
		dp.setLayout(new BorderLayout(0, 20));
		dp.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		this.add(dp, BorderLayout.CENTER);
		
		this.init();
		//初始搜索面板
		searchPane= new MatchSearchPane(this);
		searchPane.setVisible(false);
		dp.add(searchPane);
		//表格
		table = new JTable();
		sp = new JScrollPane(table);
		searchPane.getAll();
		dp.add(sp, BorderLayout.CENTER);
	}
	/**
	 * 初始化
	 */
	private void init() {
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
		pane.setOpaque(false);
		JButton search = new JButton();
		search.setSize(new Dimension(25, 25));
		search.setSize(new Dimension(25, 25));
		this.setIcon(search, "data/pic/search1.png", "data/pic/search2.png");
		pane.add(search);
		dp.add(pane, BorderLayout.NORTH);
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (searchPane.isVisible())
					searchPane.setVisible(false);
				else
					searchPane.setVisible(true);
				searchPane.setPlace();
			}
		});
	}
	/**
	 * 显示表格
	 * @param data 表格数据
	 */
	public void showTable(Object[][] data) {
		dp.remove(table);
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
	    		String team = (String)table.getValueAt(table.getSelectedRow(), 2);
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
	 * 设置图标
	 * @param button 图标
	 * @param file1 默认图标路径
	 * @param file2 翻转图标路径
	 */
	private void setIcon(JButton button, String file1, String file2) {  
        Image icon1 = (new ImageIcon(file1)).getImage();
        double scale1 = (double)icon1.getWidth(null) / (double)icon1.getHeight(null);
		Image temp1 = icon1.getScaledInstance((int)(button.getHeight() * scale1), button.getHeight(), Image.SCALE_DEFAULT);
		Image icon2 = (new ImageIcon(file2)).getImage();
		double scale2 = (double)icon2.getWidth(null) / (double)icon2.getHeight(null);
		Image temp2 = icon2.getScaledInstance((int)(button.getHeight() * scale2), button.getHeight(), Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(temp1));
		button.setRolloverIcon(new ImageIcon(temp2));
		button.setPressedIcon(new ImageIcon(temp2));
		button.setFocusPainted(false);//无选择效果
        button.setOpaque(false);//透明
		button.setContentAreaFilled(false);//填充
		button.setBorderPainted(false);//无边框
		button.setMargin(new Insets(0, 0, 0, 0));//无边距
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//指针变手
	}
	/**
	 * 刷新
	 */
	public void refresh() {
		searchPane.refresh();
	}
}

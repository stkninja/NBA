package ui.player;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ui.MainFrame;
import ui.tableheader.ColumnGroup;
import ui.tableheader.GroupableTableColumnModel;
import ui.tableheader.GroupableTableHeader;
import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;

/**
 * 球员面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class PlayerPane extends JDesktopPane {
	public MainFrame main;
	private PlayerBLService playerBL;
	private JTable table;
	private JTable fixedTable;
	private JScrollPane sp;
	//搜索界面
	private JPanel pane;
	private JButton search;
	private JButton sort;
	private PlayerSearchPane searchPane;
	private PlayerSortPane sortPane;
	//--------------------------------------------------------------
	public PlayerPane(MainFrame main) {
		this.main = main;
		playerBL = new PlayerBL();
		this.setOpaque(false);
		this.setLayout(new BorderLayout(0, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		//搜索界面
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		pane.setOpaque(false);
		search = new JButton("搜索");
		search.setFont(new Font("楷体", Font.PLAIN, 14));
		sort = new JButton("排序");
		sort.setFont(new Font("楷体", Font.PLAIN, 14));
		pane.add(search);
		pane.add(sort);
		this.add(pane, BorderLayout.NORTH);
		//
		searchPane = new PlayerSearchPane(this);
		searchPane.setVisible(false);
		sortPane = new PlayerSortPane(this);
		sortPane.setVisible(false);
		this.add(searchPane);
		this.add(sortPane);
		//表格
		table = new JTable();
		sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		searchPane.getAll();
		//监听
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (searchPane.isVisible())
					searchPane.setVisible(false);
				else
					searchPane.setVisible(true);
				searchPane.setPlace();
			}
		});
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sortPane.isVisible())
					sortPane.setVisible(false);
				else
					sortPane.setVisible(true);
				sortPane.setPlace();
			}
		});
	}
	/**
	 * 显示表格
	 * @param data 表格数据
	 */
	public void showTable(Object[][] data) {
		this.remove(table);
		String[] subTitle = {"编号", "球员名称", "所属球队", "位置",//0-6
				 "参赛场数", "先发场数","在场时间",
				 //投篮7-10
				 "命中数","出手数","命中率","真实命中率",
				 //三分11-13
				 "命中数","出手数","命中率",
				 //罚球14-16
				 "命中数","出手数","命中率",
				 //篮板17-19
				 "进攻篮板数", "防守篮板数", "篮板数",
				 //20-29
				 "进攻数","防守数","助攻数", "抢断数", "盖帽数", "失误数", "犯规数","得分","两双","得分/篮板/助攻",
				 //效率30-40
				 "投篮","进攻篮板", "防守篮板","篮板", "抢断", "助攻","盖帽","失误","使用","GmSc","效率"
				 };
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			public int getColumnCount() {
                return subTitle.length-1;
            }
            public String getColumnName(int column) {
                return subTitle[column + 1];
            }
            public int getRowCount() {
                return data.length;
            }
            public Object getValueAt(int row, int column) {
                return data[row][column + 1];
            }
			public Class<?> getColumnClass(int column) {  
				Class<?> returnValue;  
				if ((column >= 0) && (column < getColumnCount())) {  
					returnValue = getValueAt(0,column).getClass();  
				} else {  
					returnValue = Object.class;  
				}  
				return returnValue;  
			}  
		};
		dm.setDataVector(data, subTitle);
		TableModel fixedColumnModel = new AbstractTableModel() {
            public int getColumnCount() {
                return 1;
            }
            public String getColumnName(int column) {
                return subTitle[column];
            }
            public int getRowCount() {
                return data.length;
            }
            public Object getValueAt(int row, int column) {
                return data[row][column];
            }
        };
        fixedTable = new JTable(fixedColumnModel);
        fixedTable.getColumnModel().getColumn(0).setMaxWidth(35);
        fixedTable.getTableHeader().setReorderingAllowed(false); 
        fixedTable.getTableHeader().setResizingAllowed(false);
        fixedTable.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 12));
        fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Dimension fixedSize = fixedTable.getPreferredSize();
        
		table = new JTable();
		table.setColumnModel(new GroupableTableColumnModel());
        table.setTableHeader(new GroupableTableHeader((GroupableTableColumnModel)table.getColumnModel()));
        table.setModel(dm);
        table.setShowVerticalLines(false);
        table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 14));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dm);  
        table.setRowSorter(sorter); 
		//---------------------------------------------------------------------------
        GroupableTableColumnModel cm = (GroupableTableColumnModel)table.getColumnModel();
        
        ColumnGroup group1 = new ColumnGroup("基本信息");
        group1.add(cm.getColumn(0));
        group1.add(cm.getColumn(1));
        group1.add(cm.getColumn(2));
        group1.add(cm.getColumn(3));
        group1.add(cm.getColumn(4));
        group1.add(cm.getColumn(5));
        
        
        ColumnGroup group2 = new ColumnGroup("投篮");
        group2.add(cm.getColumn(6));
        group2.add(cm.getColumn(7));
        group2.add(cm.getColumn(8));
        group2.add(cm.getColumn(9));
        
        ColumnGroup group3 = new ColumnGroup("三分");
        group3.add(cm.getColumn(10));
        group3.add(cm.getColumn(11));
        group3.add(cm.getColumn(12));
        
        ColumnGroup group4 = new ColumnGroup("罚球");
        group4.add(cm.getColumn(13));
        group4.add(cm.getColumn(14));
        group4.add(cm.getColumn(15));
        
        ColumnGroup group5 = new ColumnGroup("篮板");
        group5.add(cm.getColumn(16));
        group5.add(cm.getColumn(17));
        group5.add(cm.getColumn(18));
        
        ColumnGroup group6 = new ColumnGroup("效率");
        group6.add(cm.getColumn(29));
        group6.add(cm.getColumn(30));
        group6.add(cm.getColumn(31));
        group6.add(cm.getColumn(32));
        group6.add(cm.getColumn(33));
        group6.add(cm.getColumn(34));
        group6.add(cm.getColumn(35));
        group6.add(cm.getColumn(36));
        group6.add(cm.getColumn(37));
        group6.add(cm.getColumn(38));
        group6.add(cm.getColumn(39));
        
        @SuppressWarnings("unused")
		GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
		cm.addColumnGroup(group1);
		cm.addColumnGroup(group2);
		cm.addColumnGroup(group3);
		cm.addColumnGroup(group4);
		cm.addColumnGroup(group5);
		cm.addColumnGroup(group6);
		//列色-------------------------------------------------------
		try {
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
            	cm.getColumn(i).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
		fixedTable.setBackground(Color.PINK);
		//表格监听
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedColumn() == 0) {
					String str = (String)table.getValueAt(table.getSelectedRow(), 0);
					try {
						JFrame.setDefaultLookAndFeelDecorated(true);
						PlayerFrame frame = new PlayerFrame(playerBL.getOnePlayer(str));
						frame.setOpacity(0.9f);
						frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		table.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {  
		        int row = table.rowAtPoint(e.getPoint());  
		        int col = table.columnAtPoint(e.getPoint());  
		        if (row > -1 && col > -1) {  
		            Object value = table.getValueAt(row, col);  
		            if (value != null && !value.equals(""))  
		                table.setToolTipText(value.toString());//悬浮显示单元格内容  
		            else  
		                table.setToolTipText(null);//关闭提示  
		        }  
		        if (col == 0)
		        	table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变手
		        else
		        	table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));//鼠标默认
		    }  
		});
        //------------------------------------------------------------
		sp.setViewportView(table);
		JViewport viewport = new JViewport();
        viewport.setView(fixedTable);
        viewport.setPreferredSize(fixedSize);
        sp.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixedTable.getTableHeader());
        sp.setRowHeaderView(viewport);
		revalidate();
	}
	/**
	 * 获得搜索面板
	 * @return 搜索面板
	 */
	public PlayerSearchPane getSearchPane() {
		return searchPane;
	}
}

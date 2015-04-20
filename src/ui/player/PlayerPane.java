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
 * ��Ա���
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
	//��������
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
		//��������
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		pane.setOpaque(false);
		search = new JButton("����");
		search.setFont(new Font("����", Font.PLAIN, 14));
		sort = new JButton("����");
		sort.setFont(new Font("����", Font.PLAIN, 14));
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
		//���
		table = new JTable();
		sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		searchPane.getAll();
		//����
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
	 * ��ʾ���
	 * @param data �������
	 */
	public void showTable(Object[][] data) {
		this.remove(table);
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
				 //20-29
				 "������","������","������", "������", "��ñ��", "ʧ����", "������","�÷�","��˫","�÷�/����/����",
				 //Ч��30-40
				 "Ͷ��","��������", "��������","����", "����", "����","��ñ","ʧ��","ʹ��","GmSc","Ч��"
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
        fixedTable.getTableHeader().setFont(new Font("����", Font.PLAIN, 12));
        fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Dimension fixedSize = fixedTable.getPreferredSize();
        
		table = new JTable();
		table.setColumnModel(new GroupableTableColumnModel());
        table.setTableHeader(new GroupableTableHeader((GroupableTableColumnModel)table.getColumnModel()));
        table.setModel(dm);
        table.setShowVerticalLines(false);
        table.getTableHeader().setFont(new Font("����", Font.PLAIN, 14));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dm);  
        table.setRowSorter(sorter); 
		//---------------------------------------------------------------------------
        GroupableTableColumnModel cm = (GroupableTableColumnModel)table.getColumnModel();
        
        ColumnGroup group1 = new ColumnGroup("������Ϣ");
        group1.add(cm.getColumn(0));
        group1.add(cm.getColumn(1));
        group1.add(cm.getColumn(2));
        group1.add(cm.getColumn(3));
        group1.add(cm.getColumn(4));
        group1.add(cm.getColumn(5));
        
        
        ColumnGroup group2 = new ColumnGroup("Ͷ��");
        group2.add(cm.getColumn(6));
        group2.add(cm.getColumn(7));
        group2.add(cm.getColumn(8));
        group2.add(cm.getColumn(9));
        
        ColumnGroup group3 = new ColumnGroup("����");
        group3.add(cm.getColumn(10));
        group3.add(cm.getColumn(11));
        group3.add(cm.getColumn(12));
        
        ColumnGroup group4 = new ColumnGroup("����");
        group4.add(cm.getColumn(13));
        group4.add(cm.getColumn(14));
        group4.add(cm.getColumn(15));
        
        ColumnGroup group5 = new ColumnGroup("����");
        group5.add(cm.getColumn(16));
        group5.add(cm.getColumn(17));
        group5.add(cm.getColumn(18));
        
        ColumnGroup group6 = new ColumnGroup("Ч��");
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
		//��ɫ-------------------------------------------------------
		try {
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
            	cm.getColumn(i).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
		fixedTable.setBackground(Color.PINK);
		//������
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
		                table.setToolTipText(value.toString());//������ʾ��Ԫ������  
		            else  
		                table.setToolTipText(null);//�ر���ʾ  
		        }  
		        if (col == 0)
		        	table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//������
		        else
		        	table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));//���Ĭ��
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
	 * ����������
	 * @return �������
	 */
	public PlayerSearchPane getSearchPane() {
		return searchPane;
	}
}

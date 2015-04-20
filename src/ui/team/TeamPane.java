package ui.team;

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

import org.apache.batik.transcoder.TranscoderException;

import ui.MainFrame;
import ui.tableheader.ColumnGroup;
import ui.tableheader.GroupableTableColumnModel;
import ui.tableheader.GroupableTableHeader;
import businesslogicservice.TeamBLService;

/**
 * ������
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class TeamPane extends JDesktopPane {
	public MainFrame main;
	private TeamBLService teamBL;
	private JTable table;
	private JTable fixedTable;
	private JScrollPane sp;
	//��������
	private JPanel pane;
	private JButton search;
	private JButton sort;
	private TeamSearchPane searchPane;
	private TeamSortPane sortPane;
	//--------------------------------------------------------------
	public TeamPane(MainFrame main) {
		this.main = main;
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
		//��ʼ�����������
		searchPane = new TeamSearchPane(this);
		searchPane.setVisible(false);
		sortPane = new TeamSortPane(this);
		sortPane.setVisible(false);
		this.add(searchPane);
		this.add(sortPane);
		//���
		table = new JTable();
		sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		searchPane.getAll();;
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
		String[] subTitle = {"���", "�������", "������д",
							 "ʤ������", "��������", "ʤ��",
							 //Ͷ�� 6
							 "������", "������", "������",
							 //���� 9
							 "������", "������", "������",
							 //���� 12
							 "������", "������", "������",
							 //���� 15
							 "����������", "����������", "������",
							 //18
							 "������", "������", "��ñ��", "ʧ����", "������", "�����÷�", "�����غ�",
							 //Ч�� 25
							 "����", "����", "��������", "��������", "����", "����"
							 };
		DefaultTableModel dm = new DefaultTableModel() {
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
        fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        fixedTable.getColumnModel().getColumn(0).setMaxWidth(35);
        fixedTable.getTableHeader().setReorderingAllowed(false); 
        fixedTable.getTableHeader().setResizingAllowed(false);
        fixedTable.getTableHeader().setFont(new Font("����", Font.PLAIN, 12));
        Dimension fixedSize = fixedTable.getPreferredSize();
        
		table = new JTable();
		table.setColumnModel(new GroupableTableColumnModel());
        table.setTableHeader(new GroupableTableHeader((GroupableTableColumnModel)table.getColumnModel()));
        table.setModel(dm);
        table.setShowVerticalLines(false);
        table.setOpaque(false);
        table.getTableHeader().setFont(new Font("����", Font.PLAIN, 14));
        
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dm);  
        table.setRowSorter(sorter); 
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//---------------------------------------------------------------------------
		GroupableTableColumnModel cm = (GroupableTableColumnModel)table.getColumnModel();
		
		ColumnGroup group1 = new ColumnGroup("ʤ��");
		group1.add(cm.getColumn(2));
		group1.add(cm.getColumn(3));
		group1.add(cm.getColumn(4));
		
		ColumnGroup group2 = new ColumnGroup("Ͷ��");
		group2.add(cm.getColumn(5));
		group2.add(cm.getColumn(6));
		group2.add(cm.getColumn(7));
		
		ColumnGroup group3 = new ColumnGroup("����");
		group3.add(cm.getColumn(8));
		group3.add(cm.getColumn(9));
		group3.add(cm.getColumn(10));
		
		ColumnGroup group4 = new ColumnGroup("����");
		group4.add(cm.getColumn(11));
		group4.add(cm.getColumn(12));
		group4.add(cm.getColumn(13));
		
		ColumnGroup group5 = new ColumnGroup("����");
		group5.add(cm.getColumn(14));
		group5.add(cm.getColumn(15));
		group5.add(cm.getColumn(16));
		
		ColumnGroup group6 = new ColumnGroup("Ч�� ");
		group6.add(cm.getColumn(24));
		group6.add(cm.getColumn(25));
		group6.add(cm.getColumn(26));
		group6.add(cm.getColumn(27));
		group6.add(cm.getColumn(28));
		group6.add(cm.getColumn(29));
		
		@SuppressWarnings("unused")
		GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
		cm.addColumnGroup(group1);
		cm.addColumnGroup(group2);
		cm.addColumnGroup(group3);
		cm.addColumnGroup(group4);
		cm.addColumnGroup(group5);
		cm.addColumnGroup(group6);
		//��ɫ------------------------------------------------------------
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
					String str = (String)table.getValueAt(table.getSelectedRow(), 1);
					try {
						JFrame.setDefaultLookAndFeelDecorated(true);
						TeamFrame frame = new TeamFrame(teamBL.getOneTeam(str));
						frame.setOpacity(0.9f);
						frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
					} catch (IOException | TranscoderException e1) {
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
		//---------------------------------------------------
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
	public TeamSearchPane getSearchPane() {
		return searchPane;
	}
}

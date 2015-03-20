package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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

import ui.tableheader.ColumnGroup;
import ui.tableheader.GroupableTableColumnModel;
import ui.tableheader.GroupableTableHeader;
import vo.TeamVO;
import businesslogic.TeamBL;
import businesslogicservice.TeamBLService;

/**
 * 
 * @time 2015��3��20�� ����6:23:29
 * @author stk
 * ������
 *
 */
@SuppressWarnings("serial")
public class TeamPane extends JPanel implements ActionListener{
	private TeamBLService bl;
	private JTable table;
	private JTable fixedTable;
	private JScrollPane sp;
	//��������
	private JPanel pane;
	private JLabel label1;
	private JLabel label2;
	private JComboBox<String> mode;
	private JComboBox<String> region;
	//--------------------------------------------------------------
	public TeamPane() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(0, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		//��������
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		pane.setOpaque(false);
		
		label1 = new JLabel("�������ͣ�");
		mode = new JComboBox<String>(new String[]{"����", "����"});
		label2 = new JLabel("������");
		region = new JComboBox<String>(Region.valueOf("ATLANTIC").getRegion());
		
		pane.add(label1);
		pane.add(mode);
		pane.add(label2);
		pane.add(region);
		this.add(pane, BorderLayout.NORTH);
		//���
		table = new JTable();
		sp = new JScrollPane(table);
		sp.setOpaque(false);
		this.add(sp, BorderLayout.CENTER);
		
		bl = new TeamBL();
		this.setData(bl.getTeams((String)region.getSelectedItem()));
		//����
		mode.addActionListener(this);
		region.addActionListener(this);
	}
	/**
	 * ����
	 */
	public void actionPerformed(ActionEvent e) {
		this.setData(bl.getTeams((String)region.getSelectedItem()));
	}
	/**
	 * ���ñ������
	 * @param list ���VO�б�
	 */
	private void setData(ArrayList<TeamVO> list) {
		Object[][] data = new Object[list.size()][31];
		if (mode.getSelectedItem() == "����") {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).fullName;
				data[i][2] = list.get(i).abbName;
				data[i][3] = list.get(i).winsNum;
				data[i][4] = list.get(i).gamesNum;
				data[i][5] = list.get(i).winsRate;
				
				data[i][6] = list.get(i).allshootingHit;
				data[i][7] = list.get(i).allshooting;
				data[i][8] = list.get(i).allshootingHitRate;
				
				data[i][9] = list.get(i).allthreePointHits;
				data[i][10] = list.get(i).allthreePoint;
				data[i][11] = list.get(i).allthreePointHitRate;
				
				data[i][12] = list.get(i).allfreeThrowHit;
				data[i][13] = list.get(i).allfreeThrow;
				data[i][14] = list.get(i).allthreePointHitRate;
				
				data[i][15] = list.get(i).alloffensiveRebounds;
				data[i][16] = list.get(i).alldefensiveRebounds;
				data[i][17] = list.get(i).allrebounds;
				
				data[i][18] = list.get(i).allassists;
				data[i][19] = list.get(i).allsteal;
				data[i][20] = list.get(i).allcaps;
				data[i][21] = list.get(i).allturnovers;
				data[i][22] = list.get(i).allfouls;
				data[i][23] = list.get(i).allscores;
				data[i][24] = list.get(i).allattackRound;
				
				data[i][25] = list.get(i).allattackEfficiency;
				data[i][26] = list.get(i).alldefenceEfficiency;
				data[i][27] = list.get(i).alloffensivereboundsEfficiency;
				data[i][28] = list.get(i).alldefensivereboundsEfficiency;
				data[i][29] = list.get(i).allstealEfficiency;
				data[i][30] = list.get(i).allassistEfficiency;
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).fullName;
				data[i][2] = list.get(i).abbName;
				data[i][3] = list.get(i).winsNum;
				data[i][4] = list.get(i).gamesNum;
				data[i][5] = list.get(i).winsRate;
				
				data[i][6] = list.get(i).shootingHit;
				data[i][7] = list.get(i).shooting;
				data[i][8] = list.get(i).shootingHitRate;
				
				data[i][9] = list.get(i).threePointHits;
				data[i][10] = list.get(i).threePoint;
				data[i][11] = list.get(i).threePointHitRate;
				
				data[i][12] = list.get(i).freeThrowHit;
				data[i][13] = list.get(i).freeThrow;
				data[i][14] = list.get(i).threePointHitRate;
				
				data[i][15] = list.get(i).offensiveRebounds;
				data[i][16] = list.get(i).defensiveRebounds;
				data[i][17] = list.get(i).rebounds;
				
				data[i][18] = list.get(i).assists;
				data[i][19] = list.get(i).steal;
				data[i][20] = list.get(i).caps;
				data[i][21] = list.get(i).turnovers;
				data[i][22] = list.get(i).fouls;
				data[i][23] = list.get(i).scores;
				data[i][24] = list.get(i).attackRound;
				
				data[i][25] = list.get(i).attackEfficiency;
				data[i][26] = list.get(i).defenceEfficiency;
				data[i][27] = list.get(i).offensivereboundsEfficiency;
				data[i][28] = list.get(i).defensivereboundsEfficiency;
				data[i][29] = list.get(i).stealEfficiency;
				data[i][30] = list.get(i).assistEfficiency;
			}
			
		}
		this.showTable(data);
	}
	/**
	 * ��ʾ���
	 * @param data �������
	 */
	private void showTable(Object[][] data) {
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
			public int getColumnCount()
            {
                return subTitle.length-1;
            }
             
            public String getColumnName(int column)
            {
                return subTitle[column + 1];
            }
             
            public int getRowCount()
            {
                return data.length;
            }
             
            public Object getValueAt(int row, int column)
            {
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
		
		TableModel fixedColumnModel = new AbstractTableModel()
        {
            public int getColumnCount()
            {
                return 1;
            }
             
            public String getColumnName(int column)
            {
                return subTitle[column];
            }
             
            public int getRowCount()
            {
                return data.length;
            }
             
            public Object getValueAt(int row, int column)
            {
                return data[row][column];
            }
        };
        
        fixedTable = new JTable(fixedColumnModel);
        fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        fixedTable.getColumnModel().getColumn(0).setMaxWidth(35);
        fixedTable.getTableHeader().setReorderingAllowed(false); 
        fixedTable.getTableHeader().setResizingAllowed(false);
        Dimension fixedSize = fixedTable.getPreferredSize();
        
		table = new JTable();
		table.setColumnModel(new GroupableTableColumnModel());
        table.setTableHeader(new GroupableTableHeader((GroupableTableColumnModel)table.getColumnModel()));
        table.setModel(dm);
        
        
        
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
		try
        {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer()
            {
              public Component getTableCellRendererComponent(JTable table,
                  Object value, boolean isSelected, boolean hasFocus,
                  int row, int column)
              {
                if(column%2 == 0)
                  setBackground(Color.white); //���������е�ɫ
                else if(column%2 == 1)
                  setBackground(new Color(206,231,255));  //����ż���е�ɫ
                return super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column); }
            };
                for(int i = 0; i < table.getColumnCount(); i++) {
                  cm.getColumn(i).setCellRenderer(tcr);
                  
          }
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }
		
//		try
//        {
//            DefaultTableCellRenderer cr = new DefaultTableCellRenderer()
//            {
//              public Component getTableCellRendererComponent(JTable table,
//                  Object value, boolean isSelected, boolean hasFocus,
//                  int row, int column)
//              {
//                setBackground(Color.red);
//                return super.getTableCellRendererComponent(fixedTable, value,
//                isSelected, hasFocus, row, column); }
//            }; 
//            fixedTable.getColumn(0).setCellRenderer(cr);
//          }
//        catch (Exception ex)
//        {
//          ex.printStackTrace();
//        }
		fixedTable.setBackground(Color.PINK);
		//������
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedColumn() == 0) {
					String str = (String)table.getValueAt(table.getSelectedRow(), 1);
					try {
						new TeamFrame(bl.getOneTeam(str));
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
}

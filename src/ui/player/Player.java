package ui.player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.batik.transcoder.TranscoderException;

import ui.MainFrame;
import ui.PositionEnum;
import ui.TeamEnum;
import vo.PlayerBasicInfoVO;
import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;

/**
 * 球员面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class Player extends JPanel {
	public MainFrame main;
	private PlayerBLService playerBL;
	private JButton[] button;
	private String letter;
	private JComboBox<String> comboBox;
	private JTable table;
	private JScrollPane sp;
	/**
	 * 
	 * @param main 主框架
	 */
	public Player(MainFrame main) {
		this.main = main;
		playerBL = new PlayerBL();
		this.setOpaque(false);
		this.setLayout(new BorderLayout(20, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.initTop();
		table = new JTable();
		sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		letter = "A";
		this.setData();
	}
	/**
	 * 初始化搜索栏
	 */
	private void initTop() {
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		top.setOpaque(false);
		
		button = new JButton[26];
		for (int i = 0; i < 26; i++) {
			char temp = (char) ('A' + i);
			button[i] = new JButton(String.valueOf(temp));
			button[i].setContentAreaFilled(false);
			button[i].setBorderPainted(false);
			button[i].setMargin(new Insets(0, 0, 0, 0));
			button[i].setFont(new Font("黑体", Font.BOLD, 17));
			button[i].setForeground(Color.BLUE);
			button[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//指针变手
		}
		button[0].setForeground(Color.RED);
		String[] team = new String[31];
		team[0] = "根据球队查找";
		int count = 1;
		for (TeamEnum t : TeamEnum.values()) {
			if (count < 31) {
				team[count] = t.name_Ch();
				count++;
			} else
				break;
		}
		comboBox = new JComboBox<String>(team);
		comboBox.setFont(new Font("楷体", Font.PLAIN, 14));
		for (int i = 0; i < button.length; i++) {
			top.add(button[i]);
		}
		top.add(comboBox);
		this.add(top, BorderLayout.NORTH);
		
		for (int i = 0; i < button.length; i++)
			button[i].addActionListener(new ButtonListener());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player.this.setData();
			}
		});
	}
	/**
	 * 设置数据
	 */
	private void setData() {
		String temp;
		if (comboBox.getSelectedItem().equals("根据球队查找"))
			temp = "All";
		else
			temp = (TeamEnum.valueToEnum((String)comboBox.getSelectedItem())).abbr();
		ArrayList<PlayerBasicInfoVO> list = playerBL.getPlayersByFirst(letter, temp);
		Object[][] data = new Object[list.size()][8];
		for (int i = 0; i < data.length; i++) {
			if (new File("data/players/portrait/" + list.get(i).name + ".png").exists())
				data[i][0] = new ImageIcon("data/players/portrait/" + list.get(i).name + ".png");
			else
				data[i][0] = new ImageIcon("data/pic/NotFound.png");
			data[i][1] = list.get(i).name;
			data[i][2] = PositionEnum.valueToEnum(list.get(i).position).name_Ch();
			data[i][3] = list.get(i).height;
			data[i][4] = list.get(i).weight;
			data[i][5] = list.get(i).exp;
			data[i][6] = list.get(i).birth;
			data[i][7] = list.get(i).school;
		}
		this.showTable(data);
	}
	/**
	 * 显示表格
	 * @param data 数据
	 */
	private void showTable(Object[][] data) {
		this.remove(table);
		String[] subTitle = new String[]{"", "球员", "位置", "身高", "体重", "经验", "生日", "学校"};
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dm);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
        table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 14));
		//列色
	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
	    	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    		if (row % 2 == 0)
	    			setBackground(Color.white); //设置奇数行底色
	    		else if (row % 2 == 1)
	    			setBackground(new Color(206,231,255));  //设置偶数行底色 
	    		if (column == 0) {
	    			Image temp = ((ImageIcon)value).getImage();
	    			double scale = (double)temp.getWidth(null) / (double)temp.getHeight(null);
	    			return new JLabel(new ImageIcon(temp.getScaledInstance((int)(70 * scale), 70, Image.SCALE_DEFAULT)));
	    		} else
	    			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    	}
	    };
	    for(int i = 0; i < table.getColumnCount(); i++) {
	    	table.getColumnModel().getColumn(i).setCellRenderer(tcr);
	    }
	    table.setRowHeight(70);
	    table.setFont(new Font("黑体", Font.PLAIN, 14));
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(Object.class, tcr);//内容居中
	    ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
	    this.FitTableColumns(table);
		
	    table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedColumn() == 1) {
					String str = (String)table.getValueAt(table.getSelectedRow(), 1);
					try {
						JFrame.setDefaultLookAndFeelDecorated(true);
						PlayerFrame frame = new PlayerFrame(playerBL.getOnePlayer(str), main);
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
		                table.setToolTipText(value.toString());//悬浮显示单元格内容  
		            else  
		                table.setToolTipText(null);//关闭提示  
		        }
	    		if (col == 1)
		        	table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变手
		        else
		        	table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));//鼠标默认
		    }
	    });
	    
		sp.setViewportView(table);
		revalidate();
	}
	/**
	 * 按钮监听内部类
	 * @author stk
	 *
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < button.length; i++) {
				if (e.getSource() == button[i]) {
					letter = button[i].getText();
					for (int j = 0; j < button.length; j++)
						button[j].setForeground(Color.BLUE);
					button[i].setForeground(Color.RED);
					break;
				}
			}
			Player.this.setData();
		}
	}
	/**
	 * JTable自适应
	 * @param myTable JTable
	 */
	public void FitTableColumns(JTable myTable) {
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		@SuppressWarnings("rawtypes")
		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn)columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int)myTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column);
			column.setWidth(35+width+myTable.getIntercellSpacing().width);
		}
	}
	/**
	 * 刷新
	 */
	public void refresh() {
		this.setData();
	}
}

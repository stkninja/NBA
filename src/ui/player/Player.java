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
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ui.MainFrame;
import ui.TeamEnum;
import vo.PlayerBasicInfoVO;
import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;

/**
 * ��Ա���
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
	 * @param main �����
	 */
	public Player(MainFrame main) {
		this.main = main;
		playerBL = new PlayerBL();
		this.setOpaque(false);
		this.setLayout(new BorderLayout(0, 20));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		this.initTop();
		table = new JTable();
		sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		letter = "A";
		this.setData(letter);
	}
	/**
	 * ��ʼ��������
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
			button[i].setForeground(Color.BLUE);
			button[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//ָ�����
		}
		String[] team = new String[31];
		team[0] = "������Ӳ���";
		int count = 1;
		for (TeamEnum t : TeamEnum.values()) {
			team[count] = t.name_Ch();
			count++;
		}
		comboBox = new JComboBox<String>(team);
		for (int i = 0; i < button.length; i++) {
			top.add(button[i]);
		}
		top.add(comboBox);
		this.add(top, BorderLayout.NORTH);
		
		for (int i = 0; i < button.length; i++)
			button[i].addActionListener(new ButtonListener());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player.this.setData(letter);
			}
		});
	}
	/**
	 * ��������
	 */
	private void setData(String letter) {
		String temp;
		if (comboBox.getSelectedItem().equals("������Ӳ���"))
			temp = "All";
		else
			temp = (TeamEnum.valueToEnum((String)comboBox.getSelectedItem())).abbr();
		ArrayList<PlayerBasicInfoVO> list = playerBL.getPlayersByFirst(letter, temp);
		Object[][] data = new Object[list.size()][6];
		for (int i = 0; i < data.length; i++) {
			try {
				data[i][0] = new ImageIcon(ImageIO.read(list.get(i).portrait));
			} catch (IOException e) {
				data[i][0] = new ImageIcon("data/pic/NotFound.png");
			}
			data[i][1] = list.get(i).name;
			data[i][2] = list.get(i).position;
			data[i][3] = list.get(i).height;
			data[i][4] = list.get(i).weight;
			data[i][5] = list.get(i).exp;
		}
		this.showTable(data);
	}
	/**
	 * ��ʾ���
	 * @param data ����
	 */
	private void showTable(Object[][] data) {
		this.remove(table);
		String[] subTitle = new String[]{"", "��Ա", "λ��", "���", "����", "����"};//"���", 
		DefaultTableModel dm = new DefaultTableModel(data, subTitle) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dm);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
        table.getTableHeader().setFont(new Font("����", Font.PLAIN, 14));
		//��ɫ
	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
	    	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    		if (row % 2 == 0)
	    			setBackground(Color.white); //���������е�ɫ
	    		else if (row % 2 == 1)
	    			setBackground(new Color(206,231,255));  //����ż���е�ɫ 
	    		if (column == 0) {
	    			Image temp = ((ImageIcon)value).getImage();
	    			double scale = (double)temp.getWidth(null) / (double)temp.getHeight(null);
	    			return new JLabel(new ImageIcon(temp.getScaledInstance((int)(100 * scale), 100, Image.SCALE_DEFAULT)));
	    		} else
	    			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    	}
	    };
	    for(int i = 0; i < table.getColumnCount(); i++) {
	    	table.getColumnModel().getColumn(i).setCellRenderer(tcr);
	    }
	    table.setRowHeight(100);
	    table.setFont(new Font("Menu.font", Font.PLAIN, 17));
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(Object.class, tcr);//���ݾ���
	    ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
	    table.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		
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
	    		if (col == 1)
		        	table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//������
		        else
		        	table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));//���Ĭ��
		    }
	    });
	    
		sp.setViewportView(table);
		revalidate();
	}
	/**
	 * ��ť�����ڲ���
	 * @author stk
	 *
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < button.length; i++) {
				if (e.getSource() == button[i]) {
					letter = button[i].getText();
					break;
				}
			}
			Player.this.setData(letter);
		}
	}
}

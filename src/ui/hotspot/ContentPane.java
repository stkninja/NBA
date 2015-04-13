package ui.hotspot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import businesslogicservice.PlayerBLService;
import businesslogicservice.TeamBLService;

/**
 * �ȵ�����������
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class ContentPane extends JPanel {
	private TeamBLService teamBL;
	private PlayerBLService playerBL;
	private String type;
	private String filter;
	private JPanel top;
	private JLabel label1;
	private JPanel title;
	private JLabel label2;
	private JPanel pane;
	private JButton[] label;
	private JButton[] button;
	private JLabel label3;
	private JComboBox<String> comboBox;
	//---------------------------------------------------------
	/**
	 * 
	 * @param condition ɸѡ����
	 */
	public ContentPane(String[] condition) {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		type = condition[0];
		filter = condition[1];
		//ɸѡ����
		top = new JPanel(new FlowLayout());
		top.setOpaque(false);
		label1 = new JLabel("ɸѡ������");
		top.add(label1);
		button = new JButton[condition.length - 1];
		for (int i = 1; i < condition.length; i++) {
			button[i - 1] = new JButton(condition[i]);
			button[i - 1].setContentAreaFilled(false);
			button[i - 1].setBorderPainted(false);
			button[i - 1].setMargin(new Insets(0, 0, 0, 0));
			top.add(button[i - 1]);
		}
		if (type.equals("�����ȵ���Ա") || type.equals("�����ȵ����")) {
			this.setComboBox();
		}
		this.add(top, BorderLayout.NORTH);
		//���ñ���
		title = new JPanel();
		title.setOpaque(false);
		label2 = new JLabel(type, JLabel.CENTER);
		title.add(label2);
		this.add(title, BorderLayout.CENTER);
		//��Ա���
		pane = new JPanel();
		pane.setOpaque(false);
		label = new JButton[5];
		this.setData();
		
		this.add(pane, BorderLayout.SOUTH);
		//����
		for (int i = 0; i < button.length; i++) {
			button[i].addActionListener(new ButtonListener());
		}
		for (int i = 0; i < 5; i++) {
			label[i].addActionListener(new LabelListener());
		}
	}
	/**
	 * ��������
	 */
	private void setData() {
		switch(type) {
		case "�����ȵ���Ա" : {
//			playerBL = new PlayerBL();
//			ArrayList<PlayerVO> playerList = playerBL.getTodayTopFivePlayers(filter);
			for (int i = 0; i < 5; i++) {
//				try {
//					label[i] = new JButton(new ImageIcon(ImageIO.read(playerBL.getOnePlayer(playerList.get(i).name).portrait)));
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				label[i] = new JButton(i +"");
			}
			break;
		}
		case "�����ȵ���Ա" : {
//			playerBL = new PlayerBL();
//			ArrayList<PlayerVO> playerList = playerBL.getSeasonTopFivePlayers(season, filter)
			for (int i = 0; i < 5; i++) {
				label[i] = new JButton(i +"");
			}
			break;
		}
		case "�����ȵ����" : {
			for (int i = 0; i < 5; i++) {
				label[i] = new JButton(i +"");
			}
			break;
		}
		case "���������Ա" : {
			for (int i = 0; i < 5; i++) {
				label[i] = new JButton(i +"");
			}
			break;
		}
		}
		for (int i = 0; i < 5; i++) {
			pane.add(label[i]);
		}
	}
	
	private void setComboBox() {
		label3 = new JLabel("ѡ��������");
		comboBox = new JComboBox<String>(new String[]{"1", "2", "3"});
		top.add(label3);
		top.add(comboBox);
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
					filter = button[i].getText();
					break;
				}
			}
			ContentPane.this.setData();
		}
	}
	
	private class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 5; i++) {
				if (e.getSource() == label[i]) {
					//FIXME
					break;
				}
			}
		}
	}
}

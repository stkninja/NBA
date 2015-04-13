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
 * 热点分类内容面板
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
	 * @param condition 筛选条件
	 */
	public ContentPane(String[] condition) {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		type = condition[0];
		filter = condition[1];
		//筛选条件
		top = new JPanel(new FlowLayout());
		top.setOpaque(false);
		label1 = new JLabel("筛选条件：");
		top.add(label1);
		button = new JButton[condition.length - 1];
		for (int i = 1; i < condition.length; i++) {
			button[i - 1] = new JButton(condition[i]);
			button[i - 1].setContentAreaFilled(false);
			button[i - 1].setBorderPainted(false);
			button[i - 1].setMargin(new Insets(0, 0, 0, 0));
			top.add(button[i - 1]);
		}
		if (type.equals("赛季热点球员") || type.equals("赛季热点球队")) {
			this.setComboBox();
		}
		this.add(top, BorderLayout.NORTH);
		//设置标题
		title = new JPanel();
		title.setOpaque(false);
		label2 = new JLabel(type, JLabel.CENTER);
		title.add(label2);
		this.add(title, BorderLayout.CENTER);
		//球员面板
		pane = new JPanel();
		pane.setOpaque(false);
		label = new JButton[5];
		this.setData();
		
		this.add(pane, BorderLayout.SOUTH);
		//监听
		for (int i = 0; i < button.length; i++) {
			button[i].addActionListener(new ButtonListener());
		}
		for (int i = 0; i < 5; i++) {
			label[i].addActionListener(new LabelListener());
		}
	}
	/**
	 * 设置数据
	 */
	private void setData() {
		switch(type) {
		case "当天热点球员" : {
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
		case "赛季热点球员" : {
//			playerBL = new PlayerBL();
//			ArrayList<PlayerVO> playerList = playerBL.getSeasonTopFivePlayers(season, filter)
			for (int i = 0; i < 5; i++) {
				label[i] = new JButton(i +"");
			}
			break;
		}
		case "赛季热点球队" : {
			for (int i = 0; i < 5; i++) {
				label[i] = new JButton(i +"");
			}
			break;
		}
		case "进步最快球员" : {
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
		label3 = new JLabel("选择赛季：");
		comboBox = new JComboBox<String>(new String[]{"1", "2", "3"});
		top.add(label3);
		top.add(comboBox);
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

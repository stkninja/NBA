package ui.hotspot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.batik.transcoder.TranscoderException;

import ui.MainFrame;
import ui.SvgUtil;
import ui.player.PlayerFrame;
import ui.team.TeamFrame;
import vo.PlayerVO;
import vo.TeamVO;
import businesslogic.MatchBL;
import businesslogic.PlayerBL;
import businesslogic.TeamBL;
import businesslogicservice.MatchBLService;
import businesslogicservice.PlayerBLService;
import businesslogicservice.TeamBLService;

/**
 * 热点面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class Hotspot extends JPanel {
	public MainFrame main;
	private TeamBLService teamBL;
	private PlayerBLService playerBL;
	private MatchBLService matchBL;
	private JPanel pane;
	private String filter;
	private String[] condition;
	private JButton[] button;
	private JButton[] label;
	private JComboBox<String> comboBox;
	/**
	 * 
	 * @param main 主框架
	 * @param condition 筛选条件
	 */
	public Hotspot(MainFrame main, String[] condition) {
		this.main = main;
		this.condition = condition;
		this.filter = condition[1];
		teamBL = new TeamBL();
		playerBL = new PlayerBL();
		matchBL = new MatchBL();
		this.setOpaque(false);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(20, 150, 200, 150));
		this.initTop();
		pane = new JPanel();
		pane.setOpaque(false);
		label = new JButton[5];
		this.setData();
		this.add(pane);
	}
	/**
	 * 初始化标题和搜索
	 */
	private void initTop() {
		//标题
		JPanel title = new JPanel(new FlowLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon background = new ImageIcon("data/pic/" + condition[0] +".png");
				Image bg = background.getImage();
				double scale = (double)bg.getWidth(null) / (double)bg.getHeight(null);
				g.drawImage(bg, 0, 0, getWidth(), (int)(getWidth() / scale), background.getImageObserver());
			}
		};
		title.setOpaque(false);
		this.add(title);
		//搜索
		JPanel search = new JPanel(new FlowLayout());
		search.setOpaque(false);
		
		JLabel label1 = new JLabel("筛选条件：");
		label1.setFont(new Font("黑体", Font.BOLD, 15));
		search.add(label1);
		button = new JButton[condition.length - 1];
		for (int i = 0; i < condition.length - 1; i++) {
			button[i] = new JButton(condition[i + 1]);
			button[i].setContentAreaFilled(false);
			button[i].setBorderPainted(false);
			button[i].setMargin(new Insets(0, 0, 0, 0));
			button[i].setFont(new Font("楷体", Font.PLAIN, 15));
			button[i].setForeground(Color.BLUE);
			button[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//指针变手
			search.add(button[i]);
		}
		if (condition[0].equals("SeasonTopPlayer") || condition[0].equals("SeasonTopTeam"))
			this.setComboBox(search);
		this.add(search, BorderLayout.CENTER);
		
		for (int i = 0; i < button.length; i++) {
			button[i].addActionListener(new ButtonListener());
		}
		this.add(search);
	}
	/**
	 * 添加赛季选项
	 * @param search 搜索面板
	 */
	private void setComboBox(JPanel search) {
		JLabel label2 = new JLabel("选择赛季：");
		label2.setFont(new Font("黑体", Font.BOLD, 15));
		comboBox = new JComboBox<String>((String[])matchBL.getAllSeasons().toArray(new String[matchBL.getAllSeasons().size()]));
		search.add(label2);
		search.add(comboBox);
		
		comboBox.addActionListener(new ComboBoxListener());
	}
	/**
	 * 设置数据
	 */
	private void setData() {
		switch(condition[0]) {
		case "TodayTopPlayer" : {
			ArrayList<PlayerVO> playerList = playerBL.getTodayTopFivePlayers(filter);
			if (playerList.size() != 0) {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setText(playerList.get(i).name);
					label[i].setHorizontalTextPosition(JButton.CENTER);
					label[i].setVerticalTextPosition(JButton.BOTTOM);
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					if (new File("data/players/portrait/" + playerList.get(i).name + ".png").exists())
						this.setIcon(label[i], new ImageIcon("data/players/portrait/" + playerList.get(i).name + ".png").getImage(), "data/pic/No"+ (i+1) +".png");
					else
						this.setIcon(label[i], new ImageIcon("data/pic/NotFound.png").getImage(), "data/pic/No"+ (i+1) +".png");
				}
			} else {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					this.setNOTFOUND(label[i], "data/pic/NotFound.png");
				}
			}
			break;
		}
		case "SeasonTopPlayer" : {
			ArrayList<PlayerVO> playerList = playerBL.getSeasonTopFivePlayers((String)comboBox.getSelectedItem(), filter);
			if (playerList.size() != 0) {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setText(playerList.get(i).name);
					label[i].setHorizontalTextPosition(JButton.CENTER);
					label[i].setVerticalTextPosition(JButton.BOTTOM);
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					if (new File("data/players/portrait/" + playerList.get(i).name + ".png").exists())
						this.setIcon(label[i], new ImageIcon("data/players/portrait/" + playerList.get(i).name + ".png").getImage(), "data/pic/No"+ (i+1) +".png");
					else
						this.setIcon(label[i], new ImageIcon("data/pic/NotFound.png").getImage(), "data/pic/No"+ (i+1) +".png");
				}
			} else {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					this.setNOTFOUND(label[i], "data/pic/NotFound.png");
				}
			}
			break;
		}
		case "SeasonTopTeam" : {
			ArrayList<TeamVO> teamList = teamBL.getSeasonTopFiveTeams((String)comboBox.getSelectedItem(), filter);
			if (teamList.size() != 0) {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setText(teamList.get(i).abbName);
					label[i].setHorizontalTextPosition(JButton.CENTER);
					label[i].setVerticalTextPosition(JButton.BOTTOM);
					label[i].setSize(new Dimension(150, 170));
					label[i].setPreferredSize(new Dimension(150, 170));
					File logofile = new File("logofile");
					if (new File("data/teams/" + teamList.get(i).abbName + ".svg").exists()) {
						try {
							SvgUtil.convertSvgFile2Png(new File("data/teams/" + teamList.get(i).abbName + ".svg"), logofile);
							this.setIcon(label[i], ImageIO.read(logofile), "data/pic/No"+ (i+1) +".png");
						} catch (IOException | TranscoderException e) {
							e.printStackTrace();
						}
					} else
						this.setIcon(label[i], new ImageIcon("data/pic/NotFound.png").getImage(), "data/pic/No"+ (i+1) +".png");
				}
			} else {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					this.setNOTFOUND(label[i], "data/pic/NotFound.png");
				}
			}
			break;
		}
		case "PromotionPlayer" : {
			ArrayList<PlayerVO> playerList = playerBL.getPromotionPlayers(filter);
			if (playerList.size() != 0) {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setText(playerList.get(i).name);
					label[i].setHorizontalTextPosition(JButton.CENTER);
					label[i].setVerticalTextPosition(JButton.BOTTOM);
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					if (new File("data/players/portrait/" + playerList.get(i).name + ".png").exists())
						this.setIcon(label[i], new ImageIcon("data/players/portrait/" + playerList.get(i).name + ".png").getImage(), "data/pic/No"+ (i+1) +".png");
					else
						this.setIcon(label[i], new ImageIcon("data/pic/NotFound.png").getImage(), "data/pic/No"+ (i+1) +".png");
				}
			} else {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					this.setNOTFOUND(label[i], "data/pic/NotFound.png");
				}
			}
			break;
		}
		}
		revalidate();
	}
	/**
	 * 设置图片异常图标
	 * @param button 按钮
	 * @param file 图标路径
	 */
	private void setNOTFOUND(JButton button, String file) {
		button.setSize(new Dimension(150, 150));
		button.setPreferredSize(new Dimension(150, 150));
		Image icon = (new ImageIcon(file)).getImage();
		double scale = (double)icon.getWidth(null) / (double)icon.getHeight(null);
		Image temp = icon.getScaledInstance(button.getWidth(), (int)(button.getWidth() / scale), Image.SCALE_DEFAULT);
		button.setIcon(new ImageIcon(temp));
		button.setFocusPainted(false);//无选择效果
        button.setOpaque(false);//透明
		button.setContentAreaFilled(false);//填充
		button.setBorderPainted(false);//无边框
		button.setMargin(new Insets(0, 0, 0, 0));//无边距
		pane.add(button);
	}
	/**
	 * 设置图标
	 * @param button 按钮
	 * @param icon 默认图标路径
	 * @param file 翻转图标路径
	 */
	private void setIcon(JButton button, Image icon, String file) {
		double scale1 = (double)icon.getWidth(null) / (double)icon.getHeight(null);
		Image temp1 = icon.getScaledInstance(button.getWidth(), (int)(button.getWidth() / scale1), Image.SCALE_DEFAULT);
		Image icon2 = (new ImageIcon(file)).getImage();
		double scale2 = (double)icon2.getWidth(null) / (double)icon2.getHeight(null);
		Image temp2 = icon2.getScaledInstance(button.getWidth(), (int)(button.getWidth() / scale2), Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(temp1));
		button.setRolloverIcon(new ImageIcon(temp2));
		button.setPressedIcon(new ImageIcon(temp2));
		button.setFocusPainted(false);//无选择效果
        button.setOpaque(false);//透明
		button.setContentAreaFilled(false);//填充
		button.setBorderPainted(false);//无边框
		button.setMargin(new Insets(0, 0, 0, 0));//无边距
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//指针变手
		pane.add(button);
		button.addActionListener(new LabelListener());
	}
	/**
	 * 多选框监听内部类
	 * @author stk
	 *
	 */
	private class ComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 5; i++) {
				pane.remove(label[i]);
			}
			Hotspot.this.setData();
		}
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
			for (int i = 0; i < 5; i++) {
				pane.remove(label[i]);
			}
			Hotspot.this.setData();
		}
	}
	/**
	 * 图片监听内部类
	 * @author stk
	 *
	 */
	private class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 5; i++) {
				if (e.getSource() == label[i]) {
					String str = label[i].getText();
					try {
						JFrame.setDefaultLookAndFeelDecorated(true);
						if (str.length() <= 3) {
							TeamFrame frame = new TeamFrame(teamBL.getOneTeam(str), main);
							frame.setOpacity(0.9f);
							frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
						} else {
							if (playerBL.getOnePlayer(str).isNull()) {
								PlayerFrame frame = new PlayerFrame(playerBL.getOnePlayer(str), main);
								frame.setOpacity(0.9f);
								frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
							} else {
								JOptionPane.showMessageDialog(null, "此人信息不全！", "提示", JOptionPane.WARNING_MESSAGE);
							}
						}
					} catch (IOException | TranscoderException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * 刷新
	 */
	public void refresh() {
		for (int i = 0; i < 5; i++) {
			pane.remove(label[i]);
		}
		this.setData();
	}
}

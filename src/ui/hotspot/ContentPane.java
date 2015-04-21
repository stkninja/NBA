package ui.hotspot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.batik.transcoder.TranscoderException;

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
 * 热点分类内容面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class ContentPane extends JPanel {
	private TeamBLService teamBL;
	private PlayerBLService playerBL;
	private MatchBLService matchBL;
	private String type;
	private String filter;
	private JPanel top;
	private JLabel label1;
	private JPanel pane;
	private JButton[] label;
	private JButton[] button;
	private JLabel label2;
	private JComboBox<String> comboBox;
	//---------------------------------------------------------
	/**
	 * 
	 * @param condition 筛选条件
	 */
	public ContentPane(String[] condition) {
		playerBL = new PlayerBL();
		teamBL = new TeamBL();
		matchBL = new MatchBL();
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		type = condition[0];
		filter = condition[1];
		//筛选条件
		top = new JPanel(new FlowLayout());
		top.setOpaque(false);
		label1 = new JLabel("筛选条件：");
		label1.setFont(new Font("黑体", Font.BOLD, 15));
		top.add(label1);
		button = new JButton[condition.length - 1];
		for (int i = 1; i < condition.length; i++) {
			button[i - 1] = new JButton(condition[i]);
			button[i - 1].setContentAreaFilled(false);
			button[i - 1].setBorderPainted(false);
			button[i - 1].setMargin(new Insets(0, 0, 0, 0));
			button[i - 1].setFont(new Font("楷体", Font.PLAIN, 15));
			button[i - 1].setForeground(Color.BLUE);
			button[i - 1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//指针变手
			top.add(button[i - 1]);
		}
		if (type.equals("赛季热点球员") || type.equals("赛季热点球队")) {
			this.setComboBox();
		}
		this.add(top, BorderLayout.NORTH);
		//球员面板
		pane = new JPanel();
		pane.setOpaque(false);
		label = new JButton[5];
		this.setData();
		
		this.add(pane, BorderLayout.CENTER);
		//监听
		for (int i = 0; i < button.length; i++) {
			button[i].addActionListener(new ButtonListener());
		}
	}
	/**
	 * 设置数据
	 */
	private void setData() {
		switch(type) {
		case "当天热点球员" : {
			ArrayList<PlayerVO> playerList = playerBL.getTodayTopFivePlayers(filter);
			if (playerList.size() != 0) {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setText(playerList.get(i).name);
					label[i].setHorizontalTextPosition(JButton.CENTER);
					label[i].setVerticalTextPosition(JButton.BOTTOM);
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					try {
						this.setIcon(label[i], ImageIO.read(playerBL.getOnePlayer(playerList.get(i).name).portrait), "data/pic/No"+ (i+1) +".png");
					} catch (IOException e) {
						this.setIcon(label[i], (new ImageIcon("data/pic/NotFound.jpg")).getImage(), "data/pic/No"+ (i+1) +".png");
					}
				}
				for (int i = 0; i < 5; i++) {
					pane.add(label[i]);
					label[i].addActionListener(new LabelListener());
				}
			} else {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					this.setNOTFOUND(label[i], "data/pic/NotFound.jpg");
				}
				for (int i = 0; i < 5; i++) {
					pane.add(label[i]);
				}
			}
			break;
		}
		case "赛季热点球员" : {
			ArrayList<PlayerVO> playerList = playerBL.getSeasonTopFivePlayers((String)comboBox.getSelectedItem(), filter);
			if (playerList.size() != 0) {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setText(playerList.get(i).name);
					label[i].setHorizontalTextPosition(JButton.CENTER);
					label[i].setVerticalTextPosition(JButton.BOTTOM);
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					try {
						this.setIcon(label[i], ImageIO.read(playerBL.getOnePlayer(playerList.get(i).name).portrait), "data/pic/No"+ (i+1) +".png");
					} catch (IOException e) {
						this.setIcon(label[i], (new ImageIcon("data/pic/NotFound.jpg")).getImage(), "data/pic/No"+ (i+1) +".png");
					}
				}
				for (int i = 0; i < 5; i++) {
					pane.add(label[i]);
					label[i].addActionListener(new LabelListener());
				}
			} else {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					this.setNOTFOUND(label[i], "data/pic/NotFound.jpg");
				}
				for (int i = 0; i < 5; i++) {
					pane.add(label[i]);
				}
			}
			break;
		}
		case "赛季热点球队" : {
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
					try {
						SvgUtil.convertSvgFile2Png(teamBL.getOneTeam(teamList.get(i).abbName).teamLogo, logofile);
						this.setIcon(label[i], ImageIO.read(logofile), "data/pic/No"+ (i+1) +".png");
					} catch (IOException | TranscoderException e) {
						this.setIcon(label[i], (new ImageIcon("data/pic/NotFound.jpg")).getImage(), "data/pic/No"+ (i+1) +".png");
					}
				}
				for (int i = 0; i < 5; i++) {
					pane.add(label[i]);
					label[i].addActionListener(new LabelListener());
				}
			} else {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					this.setNOTFOUND(label[i], "data/pic/NotFound.jpg");
				}
				for (int i = 0; i < 5; i++) {
					pane.add(label[i]);
				}
			}
			break;
		}
		case "进步最快球员" : {
			ArrayList<PlayerVO> playerList = playerBL.getPromotionPlayers(filter);
			if (playerList.size() != 0) {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setText(playerList.get(i).name);
					label[i].setHorizontalTextPosition(JButton.CENTER);
					label[i].setVerticalTextPosition(JButton.BOTTOM);
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					try {
						this.setIcon(label[i], ImageIO.read(playerBL.getOnePlayer(playerList.get(i).name).portrait), "data/pic/No"+ (i+1) +".png");
					} catch (IOException e) {
						this.setIcon(label[i], (new ImageIcon("data/pic/NotFound.jpg")).getImage(), "data/pic/No"+ (i+1) +".png");
					}
				}
				for (int i = 0; i < 5; i++) {
					pane.add(label[i]);
					label[i].addActionListener(new LabelListener());
				}
			} else {
				for (int i = 0; i < 5; i++) {
					label[i] = new JButton();
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					this.setNOTFOUND(label[i], "data/pic/NotFound.jpg");
				}
				for (int i = 0; i < 5; i++) {
					pane.add(label[i]);
				}
			}
			break;
		}
		}
		revalidate();
	}
	/**
	 * 添加赛季选项
	 */
	private void setComboBox() {
		label2 = new JLabel("选择赛季：");
		label2.setFont(new Font("黑体", Font.BOLD, 15));
		comboBox = new JComboBox<String>((String[])matchBL.getAllSeasons().toArray(new String[matchBL.getAllSeasons().size()]));
		top.add(label2);
		top.add(comboBox);
		//监听
		comboBox.addActionListener(new ComboBoxListener());
	}
	/**
	 * 设置图片异常图标
	 * @param button 按钮
	 * @param file 图标路径
	 */
	public void setNOTFOUND(JButton button, String file) {
		Image icon = (new ImageIcon(file)).getImage();
		double scale = (double)icon.getWidth(null) / (double)icon.getHeight(null);
		Image temp = icon.getScaledInstance(button.getWidth(), (int)(button.getWidth() / scale), Image.SCALE_DEFAULT);
		button.setIcon(new ImageIcon(temp));
		button.setFocusPainted(false);//无选择效果
        button.setOpaque(false);//透明
		button.setContentAreaFilled(false);//填充
		button.setBorderPainted(false);//无边框
		button.setMargin(new Insets(0, 0, 0, 0));//无边距
	}
	/**
	 * 设置图标
	 * @param button 按钮
	 * @param icon 默认图标路径
	 * @param file 翻转图标路径
	 */
	public void setIcon(JButton button, Image icon, String file) {
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
			ContentPane.this.setData();
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
			ContentPane.this.setData();
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
							TeamFrame frame = new TeamFrame(teamBL.getOneTeam(str));
							frame.setOpacity(0.9f);
							frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
						} else {
							PlayerFrame frame = new PlayerFrame(playerBL.getOnePlayer(str));
							frame.setOpacity(0.9f);
							frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
						}
					} catch (IOException | TranscoderException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}

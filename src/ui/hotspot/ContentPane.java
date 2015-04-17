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
import javax.swing.SwingUtilities;

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
 * �ȵ�����������
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
	 * @param condition ɸѡ����
	 */
	public ContentPane(String[] condition) {
		playerBL = new PlayerBL();
		teamBL = new TeamBL();
		matchBL = new MatchBL();
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		type = condition[0];
		filter = condition[1];
		//ɸѡ����
		top = new JPanel(new FlowLayout());
		top.setOpaque(false);
		label1 = new JLabel("ɸѡ������");
		label1.setFont(new Font("����", Font.BOLD, 15));
		top.add(label1);
		button = new JButton[condition.length - 1];
		for (int i = 1; i < condition.length; i++) {
			button[i - 1] = new JButton(condition[i]);
			button[i - 1].setContentAreaFilled(false);
			button[i - 1].setBorderPainted(false);
			button[i - 1].setMargin(new Insets(0, 0, 0, 0));
			button[i - 1].setFont(new Font("����", Font.PLAIN, 15));
			button[i - 1].setForeground(Color.BLUE);
			button[i - 1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//ָ�����
			top.add(button[i - 1]);
		}
		if (type.equals("�����ȵ���Ա") || type.equals("�����ȵ����")) {
			this.setComboBox();
		}
		this.add(top, BorderLayout.NORTH);
		//��Ա���
		pane = new JPanel();
		pane.setOpaque(false);
		label = new JButton[5];
		this.setData();
		
		this.add(pane, BorderLayout.CENTER);
		//����
		for (int i = 0; i < button.length; i++) {
			button[i].addActionListener(new ButtonListener());
		}
	}
	/**
	 * ��������
	 */
	private void setData() {
		switch(type) {
		case "�����ȵ���Ա" : {
			//TODO
//			ArrayList<PlayerVO> playerList = playerBL.getTodayTopFivePlayers(filter);
			for (int i = 0; i < 5; i++) {
//				try {
//					label[i] = new JButton();
//					label[i].setSize(new Dimension(150, 150));
//					label[i].setPreferredSize(new Dimension(150, 150));
//					this.setIcon(label[i], ImageIO.read(playerBL.getOnePlayer(playerList.get(i).name).portrait), "data/pic/exit1.png");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				label[i] = new JButton(i +"");
			}
			break;
		}
		case "�����ȵ���Ա" : {
			ArrayList<PlayerVO> playerList = playerBL.getSeasonTopFivePlayers((String)comboBox.getSelectedItem(), filter);
			for (int i = 0; i < 5; i++) {
				try {
					label[i] = new JButton();
					label[i].setText(playerList.get(i).name);
					label[i].setHorizontalTextPosition(JButton.CENTER);
					label[i].setVerticalTextPosition(JButton.BOTTOM);
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					this.setIcon(label[i], ImageIO.read(playerBL.getOnePlayer(playerList.get(i).name).portrait), "data/pic/no"+ (i+1) +".png");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		}
		case "�����ȵ����" : {
			ArrayList<TeamVO> teamList = teamBL.getSeasonTopFiveTeams((String)comboBox.getSelectedItem(), filter);
			for (int i = 0; i < 5; i++) {
				File logofile = new File("logofile");
				try {
					SvgUtil.convertSvgFile2Png(teamBL.getOneTeam(teamList.get(i).abbName).teamLogo, logofile);
					label[i] = new JButton();
					label[i].setText(teamList.get(i).abbName);
					label[i].setHorizontalTextPosition(JButton.CENTER);
					label[i].setVerticalTextPosition(JButton.BOTTOM);
					label[i].setSize(new Dimension(150, 170));
					label[i].setPreferredSize(new Dimension(150, 170));
					this.setIcon(label[i], ImageIO.read(logofile), "data/pic/no"+ (i+1) +".png");
				} catch (IOException | TranscoderException e) {
					e.printStackTrace();
				}
			}
			break;
		}
		case "���������Ա" : {
			ArrayList<PlayerVO> playerList = playerBL.getPromotionPlayers(filter);
			for (int i = 0; i < 5; i++) {
				try {
					label[i] = new JButton();
					label[i].setText(playerList.get(i).name);
					label[i].setHorizontalTextPosition(JButton.CENTER);
					label[i].setVerticalTextPosition(JButton.BOTTOM);
					label[i].setSize(new Dimension(150, 150));
					label[i].setPreferredSize(new Dimension(150, 150));
					this.setIcon(label[i], ImageIO.read(playerBL.getOnePlayer(playerList.get(i).name).portrait), "data/pic/no"+ (i+1) +".png");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		}
		}
		for (int i = 0; i < 5; i++) {
			pane.add(label[i]);
			label[i].addActionListener(new LabelListener());
		}
		revalidate();
	}
	/**
	 * �������ѡ��
	 */
	private void setComboBox() {
		label2 = new JLabel("ѡ��������");
		label2.setFont(new Font("����", Font.BOLD, 15));
		comboBox = new JComboBox<String>((String[])matchBL.getAllSeasons().toArray(new String[matchBL.getAllSeasons().size()]));
		top.add(label2);
		top.add(comboBox);
		//����
		comboBox.addActionListener(new ComboBoxListener());
	}
	/**
	 * ����ͼ��
	 * @param button ��ť
	 * @param icon Ĭ��ͼ��·��
	 * @param file ��תͼ��·��
	 */
	public void setIcon(JButton button, Image icon, String file) {
		double scale = (double)icon.getWidth(null) / (double)icon.getHeight(null);
		Image temp1 = icon.getScaledInstance(button.getWidth(), (int)(button.getWidth() / scale), Image.SCALE_DEFAULT);
		ImageIcon icon2 = new ImageIcon(file);
		Image temp2 = icon2.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(temp1));
		button.setRolloverIcon(new ImageIcon(temp2));
		button.setPressedIcon(new ImageIcon(temp2));
		button.setFocusPainted(false);//��ѡ��Ч��
        button.setOpaque(false);//͸��
		button.setContentAreaFilled(false);//���
		button.setBorderPainted(false);//�ޱ߿�
		button.setMargin(new Insets(0, 0, 0, 0));//�ޱ߾�
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//ָ�����
	}
	/**
	 * ��ѡ������ڲ���
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
			for (int i = 0; i < 5; i++) {
				pane.remove(label[i]);
			}
			ContentPane.this.setData();
		}
	}
	/**
	 * ͼƬ�����ڲ���
	 * @author stk
	 *
	 */
	private class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 5; i++) {
				if (e.getSource() == label[i]) {
					String str = label[i].getText();
					SwingUtilities.invokeLater(new Runnable() {
						@SuppressWarnings("restriction")
						public void run() {
							try {
								JFrame.setDefaultLookAndFeelDecorated(true);
								if (str.length() <= 3) {
									TeamFrame frame = new TeamFrame(teamBL.getOneTeam(str));
									com.sun.awt.AWTUtilities.setWindowOpacity(frame, 0.9f);//����͸����
									com.sun.awt.AWTUtilities.setWindowShape(frame, new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));//����Բ��
								} else {
									PlayerFrame frame = new PlayerFrame(playerBL.getOnePlayer(str));
									com.sun.awt.AWTUtilities.setWindowOpacity(frame, 0.9f);//����͸����
									com.sun.awt.AWTUtilities.setWindowShape(frame, new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));//����Բ��
								}
							} catch (IOException | TranscoderException e) {
								e.printStackTrace();
							}
						}
				    });
				}
			}
		}
	}
}

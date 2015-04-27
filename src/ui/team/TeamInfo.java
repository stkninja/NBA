package ui.team;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.batik.transcoder.TranscoderException;

import ui.MainFrame;
import ui.SvgUtil;
import ui.TeamEnum;
import vo.TeamBasicInfoVO;
import businesslogic.TeamBL;
import businesslogicservice.TeamBLService;

/**
 * 球队显示的队标和名称
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class TeamInfo extends JPanel {
	public MainFrame main;
	private TeamBLService teamBL;
	/**
	 * 
	 * @param main 主框架
	 * @param team 球队缩写
	 */
	public TeamInfo(MainFrame main, String team) {
		this.main = main;
		teamBL = new TeamBL();
		TeamBasicInfoVO vo = teamBL.getOneTeam(team);
		JButton logo = new JButton();
		logo.setSize(new Dimension(75, 75));
		logo.setPreferredSize(new Dimension(75, 75));
		File logofile = new File("logofile");
		try {
			SvgUtil.convertSvgFile2Png(vo.teamLogo, logofile);
			this.setIcon(logo, ImageIO.read(logofile));
		} catch (IOException | TranscoderException e) {
			e.printStackTrace();
		}
		JButton name = new JButton(TeamEnum.valueOf(vo.abbName).name_Ch());
		name.setFont(new Font("黑体", Font.BOLD, 15));
		name.setForeground(Color.DARK_GRAY);
		name.setOpaque(false);//透明
		name.setContentAreaFilled(false);//填充
		name.setBorderPainted(false);//无边框
		name.setMargin(new Insets(0, 0, 0, 0));//无边距
		name.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//指针变手
		this.add(logo);
		this.add(name);
		
		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					TeamFrame frame = new TeamFrame(teamBL.getOneTeam(vo.abbName), main);
					frame.setOpacity(0.9f);
					frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
				} catch (IOException | TranscoderException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	/**
	 * 设置图标
	 * @param button 按钮
	 * @param icon 图标路径
	 */
	private void setIcon(JButton button, Image icon) {
		double scale1 = (double)icon.getWidth(null) / (double)icon.getHeight(null);
		Image temp1 = icon.getScaledInstance(button.getWidth(), (int)(button.getWidth() / scale1), Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(temp1));
		button.setFocusPainted(false);//无选择效果
        button.setOpaque(false);//透明
		button.setContentAreaFilled(false);//填充
		button.setBorderPainted(false);//无边框
		button.setMargin(new Insets(0, 0, 0, 0));//无边距
	}
}

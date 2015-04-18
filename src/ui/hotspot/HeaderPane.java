package ui.hotspot;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 热点分类标题面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class HeaderPane extends JPanel {
	private boolean isShow;//是否被展示
	private ImageIcon background;//背景图片
	private String path1;//图片路径1
	private String path2;//图片路径2
	//----------------------------------------------------
	/**
	 * 
	 * @param path1 背景路径1
	 * @param path2 背景路径2
	 */
	public HeaderPane(String path1, String path2) {
		this.path1 = path1;
		this.path2 = path2;
		this.setOpaque(false);
		this.isShow = false;
		this.setPreferredSize(new Dimension(0, 40));
	}
	/**
	 * 设置内容面板是否已经展示
	 * @param isShow
	 */
	public void setIsShow(boolean isShow) {
		this.isShow = isShow;
	}
	/**
	 * 绘制背景
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isShow) {
			background = new ImageIcon(path2);
		} else {
			background = new ImageIcon(path1);
		}
		Image bg = background.getImage();
		double scale = (double)bg.getWidth(null) / (double)bg.getHeight(null);
		g.drawImage(bg, 0, 0, (int)(getHeight() * scale), getHeight(), background.getImageObserver());
	}
}

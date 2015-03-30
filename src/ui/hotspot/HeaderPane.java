package ui.hotspot;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author stk
 * 热点分类标题面板
 *
 */
@SuppressWarnings("serial")
public class HeaderPane extends JPanel{
	@SuppressWarnings("unused")
	private boolean isShow;//是否被展示
	private ImageIcon background;//背景图片
	//----------------------------------------------------
	/**
	 * 
	 * @param path 背景路径
	 */
	public HeaderPane(String path) {
		background = new ImageIcon(path);
		this.setOpaque(false);
		this.isShow = false;
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
		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
	}
}

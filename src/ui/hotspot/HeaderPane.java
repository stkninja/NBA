package ui.hotspot;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author stk
 * �ȵ����������
 *
 */
@SuppressWarnings("serial")
public class HeaderPane extends JPanel{
	private boolean isShow;//�Ƿ�չʾ
	private ImageIcon background;//����ͼƬ
	private String path1;//ͼƬ·��1
	private String path2;//ͼƬ·��2
	//----------------------------------------------------
	/**
	 * 
	 * @param path1 ����·��1
	 * @param path2 ����·��2
	 */
	public HeaderPane(String path1, String path2) {
		this.path1 = path1;
		this.path2 = path2;
		this.setOpaque(false);
		this.isShow = false;
		this.setPreferredSize(new Dimension(0, 40));
	}
	/**
	 * ������������Ƿ��Ѿ�չʾ
	 * @param isShow
	 */
	public void setIsShow(boolean isShow) {
		this.isShow = isShow;
	}
	/**
	 * ���Ʊ���
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isShow) {
			background = new ImageIcon(path1);
		} else {
			background = new ImageIcon(path2);
		}
		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
	}
}

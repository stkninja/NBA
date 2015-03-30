package ui.hotspot;

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
	@SuppressWarnings("unused")
	private boolean isShow;//�Ƿ�չʾ
	private ImageIcon background;//����ͼƬ
	//----------------------------------------------------
	/**
	 * 
	 * @param path ����·��
	 */
	public HeaderPane(String path) {
		background = new ImageIcon(path);
		this.setOpaque(false);
		this.isShow = false;
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
		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
	}
}

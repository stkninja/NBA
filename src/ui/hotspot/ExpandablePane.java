package ui.hotspot;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import ui.MainFrame;

/**
 * 可折叠面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class ExpandablePane extends JPanel {
	public MainFrame main;
	private HeaderPane header;//标题面板
	private ContentPane content;//内容面板
	//-----------------------------------------------
	/**
	 * 
	 * @param path1 背景路径1
	 * @param path2 背景路径2
	 * @param condition 筛选条件
	 */
	public ExpandablePane(MainFrame main, String path1, String path2, String[] condition) {
		this.main = main;
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		header = new HeaderPane(path1, path2);
		header.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//指针变手
		content = new ContentPane(main, condition);
		content.setVisible(false);
		this.add(header, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		header.addMouseListener(new Action());
	}
	/**
	 * 监听鼠标点击标题界面
	 * @author stk
	 *
	 */
	private class Action extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			HeaderPane hp = (HeaderPane)e.getSource();
			if (content.isShowing()) {
				content.setVisible(false);
				hp.setIsShow(false);
			} else {
				content.setVisible(true);
				hp.setIsShow(true);
			}
			hp.getParent().validate();
			hp.getParent().repaint();
		}
	}
}

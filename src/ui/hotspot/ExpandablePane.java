package ui.hotspot;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * ���۵����
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class ExpandablePane extends JPanel{
	private HeaderPane header;//�������
	private ContentPane content;//�������
	//-----------------------------------------------
	/**
	 * 
	 * @param path1 ����·��1
	 * @param path2 ����·��2
	 * @param condition ɸѡ����
	 */
	public ExpandablePane(String path1, String path2, String[] condition) {
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		header = new HeaderPane(path1, path2);
		content = new ContentPane(condition);
		content.setVisible(false);
		this.add(header, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		header.addMouseListener(new Action());
	}
	/**
	 * ����������������
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

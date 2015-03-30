package ui.hotspot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 热点分类内容面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class ContentPane extends JPanel{
	private JPanel top;
	private JLabel label;
	private JPanel pane;
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	//---------------------------------------------------------
	public ContentPane(String[] condition) {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		//筛选条件
		top = new JPanel(new FlowLayout());
		top.setOpaque(false);
		label = new JLabel("筛选条件：");
		
		top.add(label);
		for (int i = 0; i < condition.length; i++) {
			JButton button = new JButton(condition[i]);
			button.setContentAreaFilled(false);
			top.add(button);
		}
		this.add(top, BorderLayout.NORTH);
		//球员面板
		pane = new JPanel();
		pane.setOpaque(false);
		l1 = new JLabel("1");
		l2 = new JLabel("2");
		l3 = new JLabel("3");
		l4 = new JLabel("4");
		l5 = new JLabel("5");
		
		pane.add(l1);
		pane.add(l2);
		pane.add(l3);
		pane.add(l4);
		pane.add(l5);
		this.add(pane, BorderLayout.CENTER);
	}
}

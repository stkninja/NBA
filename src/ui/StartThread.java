package ui;

import java.awt.geom.RoundRectangle2D;
import java.util.concurrent.Callable;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import data.Pretreatment;

/**
 * ���������߳�
 * @author stk
 *
 */
public class StartThread implements Callable<MainFrame> {
	private MainFrame frame;
	//--------------------------------------
	public StartThread(MainFrame frame) {
		this.frame = frame;
	}
	/**
	 * run
	 */
	public MainFrame call() throws Exception {
		Pretreatment.pretreatment();//Ԥ����
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}//ϵͳ���
		frame = new MainFrame();
		frame.setOpacity(0.9f);
		frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
		return frame;
	}
}

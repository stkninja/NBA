package ui;

import data.Pretreatment;

/**
 * ���������߳�
 * @author stk
 *
 */
public class UpdateThread extends Thread {
	private MainFrame frame;
	//--------------------------------------
	public UpdateThread(MainFrame frame) {
		this.frame = frame;
	}
	/**
	 * run
	 */
	public void run() {
		Pretreatment.redoMBasic();//�������ݿ�����
		frame.dispose();
	}
}

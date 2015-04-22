package ui;

import data.Pretreatment;

/**
 * 更新数据线程
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
		Pretreatment.redoMBasic();//更新数据库数据
		frame.dispose();
	}
}

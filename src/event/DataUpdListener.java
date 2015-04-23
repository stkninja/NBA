package event;

import java.util.EventListener;

import javax.swing.JOptionPane;

import ui.MainFrame;
import ui.ProgressBar;

public class DataUpdListener implements EventListener{	
	public void dataUpdated(DataUpdEvent e){}
	
	public MainFrame refresh(MainFrame mf){
		if (JOptionPane.showConfirmDialog(null, "是否需要更新数据？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION){
			Thread t = new Thread() {
				public void run() {
				}
			};
			new ProgressBar(t, "正在更新数据,请稍候……");
			JOptionPane.showMessageDialog(null, "更新完成", "提示", JOptionPane.INFORMATION_MESSAGE);
			//刷新
			mf.refresh();
		}
		return mf;
	}
}

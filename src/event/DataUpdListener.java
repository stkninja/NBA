package event;

import java.util.EventListener;

import javax.swing.JOptionPane;

import ui.MainFrame;
import ui.ProgressBar;

public class DataUpdListener implements EventListener{	
	public void dataUpdated(DataUpdEvent e){}
	
	public MainFrame refresh(MainFrame mf){
		if (JOptionPane.showConfirmDialog(null, "�Ƿ���Ҫ�������ݣ�", "��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION){
			Thread t = new Thread() {
				public void run() {
				}
			};
			new ProgressBar(t, "���ڸ�������,���Ժ򡭡�");
			JOptionPane.showMessageDialog(null, "�������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			//ˢ��
			mf.refresh();
		}
		return mf;
	}
}

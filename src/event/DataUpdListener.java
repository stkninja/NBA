package event;

import java.util.EventListener;

import javax.swing.JOptionPane;

import ui.MainFrame;
import ui.ProgressBar;
import ui.UpdateThread;

public class DataUpdListener implements EventListener{	
	public void dataUpdated(DataUpdEvent e){}
	
	public MainFrame refresh(MainFrame mf){
		if (JOptionPane.showConfirmDialog(null, "�Ƿ���Ҫ�������ݣ�", "��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION){
			Thread t = new Thread(new UpdateThread());
			new ProgressBar(t, "���ڼ�������,���Ժ򡭡�");		
		}
		mf.dispose();
		mf = new MainFrame();
		return mf;
	}
}

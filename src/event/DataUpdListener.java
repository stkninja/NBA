package event;

import java.util.EventListener;

import ui.MainFrame;

public class DataUpdListener implements EventListener{	
	
	public MainFrame refresh(MainFrame mf, DataUpdEvent e){
		//ˢ��
		mf.refresh();
		return mf;
	}
}

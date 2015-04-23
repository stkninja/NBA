package event;

import java.util.EventListener;

import ui.MainFrame;

public class DataUpdListener implements EventListener{	
	
	public MainFrame refresh(MainFrame mf, DataUpdEvent e){
		//Ë¢ÐÂ
		mf.refresh();
		return mf;
	}
}

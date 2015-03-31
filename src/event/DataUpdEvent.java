package event;

import java.util.ArrayList;

public class DataUpdEvent {
	private ArrayList<DataUpdListener> listener;
	
	public DataUpdEvent() {
		listener = new ArrayList<DataUpdListener>();
	}

	public void addDataUpdListener(DataUpdListener dul){
		listener.add(dul);
	}
	
	//
}

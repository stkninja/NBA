package event;

import java.util.EventObject;

public class DataUpdEvent extends EventObject{
	private static final long serialVersionUID = 1L;
	//封装事件源
	private Object source;
	
	public DataUpdEvent(Object source) {
		super(source);
		this.source = source;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}
}

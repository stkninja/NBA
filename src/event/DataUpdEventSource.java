package event;

import java.util.EventObject;

public class DataUpdEventSource extends EventObject{
	private static final long serialVersionUID = 1L;
	//��װ�¼�Դ
	private Object source;
	
	public DataUpdEventSource(Object source) {
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

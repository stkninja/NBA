package event;

import java.io.File;
import java.util.ArrayList;


public class DataUpdEventSource implements Runnable{
	
	private File f = new File("data\\matches");
	private ArrayList<DataUpdListener> listener = new ArrayList<DataUpdListener>();
	
	public DataUpdEventSource(){
		//��ѯ�¼��Ƿ񴥷�
		Thread t = new Thread(this);
		t.start();
	}	
	
	public void addDataUpdListener(DataUpdListener dul){
		this.listener.add(dul);
	}
	
	private void notifies(){  		
		//��֪
        for(DataUpdListener dul : listener)
        	dul.dataUpdated(new DataUpdEvent(this));
    }

	//�ж��¼��Ƿ񴥷�
	public void run() {
		String[] list = f.list();
		int oriSize = list.length;
		int preSize = list.length;
		int curSize;
		while(true){
			list = f.list();
			curSize = list.length;
			if(preSize != curSize || (preSize == curSize && curSize == oriSize)){
				try {
					//����1s
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				preSize = curSize;
			}
			else if(preSize == curSize && curSize != oriSize){
				//����
				this.notifies();
				
				try {
					//��ɸ��� ����20s
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				oriSize = curSize;
				preSize = curSize;
			}
		}
	}  
}

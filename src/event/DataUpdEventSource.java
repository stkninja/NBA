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
		int preSize = 0;
		int curSize;
		while(true){
			String[] list = f.list();
			curSize = list.length;
			if(preSize == 0 && curSize == 0){
				try {
					//����10s
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(preSize != curSize){
				try {
					//����10s
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				preSize = curSize;
			}
			else if(preSize == curSize && curSize != 0){
				//����
				this.notifies();
				
				try {
					//��ɸ��� ����20s
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				preSize = 0;
			}
		}
	}  
}

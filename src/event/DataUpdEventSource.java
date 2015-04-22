package event;

import java.io.File;
import java.util.ArrayList;

import ui.MainFrame;


public class DataUpdEventSource implements Runnable{
	
	private File f = new File("data\\matches");
	private ArrayList<DataUpdListener> listener = new ArrayList<DataUpdListener>();
	private MainFrame mf = null;
	
	public DataUpdEventSource(MainFrame mf){
		this.mf = mf;
		//��ѯ�¼��Ƿ񴥷�
		Thread t = new Thread(this);
		t.start();
	}	
	
	public void addDataUpdListener(DataUpdListener dul){
		this.listener.add(dul);
	}
	
	private void notifies(){  		
		//listener���з���
        for(DataUpdListener dul : listener){
        	dul.dataUpdated(new DataUpdEvent(this));
        	mf = dul.refresh(mf);
        }
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
					//��ɸ��� ����10s
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				oriSize = curSize;
				preSize = curSize;
			}
		}
	}  
}

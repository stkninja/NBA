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
        	mf = dul.refresh(mf, new DataUpdEvent(this));
        }
    }

	//�ж��¼��Ƿ񴥷�
	public void run() {
		String[] list = f.list();
		int preSize = list.length;
		int curSize;
		while(true){
			list = f.list();
			curSize = list.length;
			if(preSize == curSize) {
				try {
					//����1s
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				//����
				this.notifies();
				
				try {
					//��ɸ��� ����1s
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				preSize = curSize;
			}
		}
	}  
}

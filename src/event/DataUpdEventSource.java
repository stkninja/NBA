package event;

import java.io.File;
import java.util.ArrayList;


public class DataUpdEventSource implements Runnable{
	
	private File f = new File("data\\matches");
	private ArrayList<DataUpdListener> listener = new ArrayList<DataUpdListener>();
	
	public DataUpdEventSource(){
		//轮询事件是否触发
		Thread t = new Thread(this);
		t.start();
	}	
	
	public void addDataUpdListener(DataUpdListener dul){
		this.listener.add(dul);
	}
	
	private void notifies(){  		
		//告知
        for(DataUpdListener dul : listener)
        	dul.dataUpdated(new DataUpdEvent(this));
    }

	//判断事件是否触发
	public void run() {
		int preSize = 0;
		int curSize;
		while(true){
			String[] list = f.list();
			curSize = list.length;
			if(preSize == 0 && curSize == 0){
				try {
					//休眠10s
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(preSize != curSize){
				try {
					//休眠10s
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				preSize = curSize;
			}
			else if(preSize == curSize && curSize != 0){
				//触发
				this.notifies();
				
				try {
					//完成更新 休眠20s
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				preSize = 0;
			}
		}
	}  
}

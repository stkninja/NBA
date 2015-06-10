package spider.spiderLive;

public class TestMain implements Runnable{
	
	public void run() {			
		while(true){

			LiveInfo liveInfo = SpiderLive.spiderLive();
			System.out.println(liveInfo.getMatchRecords().length);
			String[] s = liveInfo.getMatchRecords()[0];
			System.out.println(s[0] + " " + s[1] + " " +  s[2] + " " +  s[3]);
				
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		new Thread(new TestMain()).start();
	}
}

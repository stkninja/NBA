package dataBase.initDB;




/*======================================================*
 * �������ݿ�#��ʼ�����ݿ�����
 *======================================================*/
public class IniDataBase {

	public static void iniDataBase() {		
		//���߳���д���
		// ��дt_player
		new Thread(new FillT_PLAYER()).start();
		
		//��дt_team		
		new Thread(new FillT_TEAM()).start();
		
		//��дt_match t_player ���6�߳�
		int threadCount = 6;
		Thread[] threads = new Thread[6];
		for(int year = 2011; year <= 2015;){
			//6�߳�֮һ����������߳�#��������һ���������
			for(int j = 0; j < threadCount; j++){
				if(threads[j] == null || !threads[j].isAlive()){
					threads[j] = new Thread(new FillT_MATCH(year));
					threads[j].start();
					year++;
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		IniDataBase.iniDataBase();
	}
}

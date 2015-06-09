package dataBase.initDB;

/*======================================================*
 * �������ݿ�#��ʼ�����ݿ�����
 *======================================================*/
public class IniDataBase {

	public static void iniDataBase() {
		//��������Ҫ�ı��
		CreatTables.creatTables();
		
		//���߳���д���
		// ��дt_player
		new Thread(new FillT_PLAYER()).start();
		
		//��дt_team		
		new Thread(new FillT_TEAM()).start();
		
		//��дt_match t_player ���6�߳�
		int threadCount = 6;
		Thread[] threads = new Thread[6];
		for(int year = 2005; year <= 2015;){
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
		iniDataBase();
	}
}

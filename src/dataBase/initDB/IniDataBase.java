package dataBase.initDB;

import dataBase.dataBaseOpe.DataBaseOpe;


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
		for(int year = 2014; year <= 2015;){
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
		
//		DataBaseOpe.createTab_UpdateSQL("DROP TABLE IF EXISTS t_player");
//		DataBaseOpe.createTab_UpdateSQL("CREATE TABLE IF NOT EXISTS "
//				+ "t_player(name VARCHAR(40), fromYear VARCHAR(4), toYear VARCHAR(4), position VARCHAR(3), height VARCHAR(5), weight VARCHAR(5), "
//				+ "birth VARCHAR(10), college VARCHAR(80))");
//		new Thread(new FillT_PLAYER()).start();
		
		DataBaseOpe.createTab_UpdateSQL("DROP TABLE IF EXISTS t_team");
		DataBaseOpe.createTab_UpdateSQL("CREATE TABLE IF NOT EXISTS "
				+ "t_team(tempFullName VARCHAR(40), tempAbbName VARCHAR(3), hisFullName VARCHAR(40), hisAbbName VARCHAR(3), "
				+ "location VARCHAR(40), competionArea VARCHAR(1), subArea VARCHAR(40), homeGround VARCHAR(40), setupTime VARCHAR(4))");
		new Thread(new FillT_TEAM()).start();

//		IniDataBase.iniDataBase();
	}
}

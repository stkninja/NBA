package dataBase.initDB;

import dataBase.dataBaseOpe.DataBaseOpe;


/*======================================================*
 * 创建数据库#初始化数据库数据
 *======================================================*/
public class IniDataBase {

	public static void iniDataBase() {
		//创建所需要的表格
		CreatTables.creatTables();
		
		//多线程填写表格
		// 填写t_player
		new Thread(new FillT_PLAYER()).start();
		
		//填写t_team		
		new Thread(new FillT_TEAM()).start();
		
		//填写t_match t_player 最大6线程
		int threadCount = 6;
		Thread[] threads = new Thread[6];
		for(int year = 2014; year <= 2015;){
			//6线程之一空闲则添加线程#并进入下一年比赛搜索
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

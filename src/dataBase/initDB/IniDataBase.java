package dataBase.initDB;

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
		for(int year = 2005; year <= 2015;){
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
		iniDataBase();
	}
}

package dataBase.initDB;

import java.util.ArrayList;

import dataBase.dataBaseOpe.DataBaseOpe;
import spider.spiderTeam.SpiderTeam;
import spider.spiderTeam.TeamBasicStruct;

public class FillT_TEAM implements Runnable{

	public static void fillT_TEAM() {
		//创建
		DataBaseOpe.createTab_UpdateSQL("DROP TABLE IF EXISTS t_team");
		DataBaseOpe.createTab_UpdateSQL("CREATE TABLE IF NOT EXISTS "
				+ "t_team(tempFullName VARCHAR(40), tempAbbName VARCHAR(3), hisFullName VARCHAR(40), hisAbbName VARCHAR(3), "
				+ "location VARCHAR(40), competionArea VARCHAR(1), subArea VARCHAR(40), homeGround VARCHAR(40), setupTime VARCHAR(4))");

		
		//插入
		ArrayList<TeamBasicStruct> lists = SpiderTeam.spiderTeam();
		for(TeamBasicStruct tbs : lists){			
			String ord = "INSERT INTO t_team VALUES('" + tbs.getTempFullName() + "', '" + tbs.getTempAbbName() + "', '" 
					+ tbs.getHistoryFullName() + "', '" + tbs.getHistoryAbblName() + "', '"
					+ tbs.getLocation() + "', '" + tbs.getCompetionArea() + "', '" + tbs.getSubArea() + "', '"
					+ tbs.getHomeGround() + "', '" + tbs.getSetupTime() + "')";
			DataBaseOpe.createTab_UpdateSQL(ord);
			//控制台输出以提示当前操作
			System.out.println("INSERT INTO t_team(" + tbs.getTempFullName() + ", " + tbs.getTempAbbName() + ")");
		}
	}
	
	public void run() {
		fillT_TEAM();
	}
}

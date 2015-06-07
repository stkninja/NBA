package dataBase.initDB;

import java.util.ArrayList;

import dataBase.dataBaseOpe.DataBaseOpe;
import spider.spiderTeam.SpiderTeam;
import spider.spiderTeam.TeamBasicStruct;

public class FillT_TEAM {

	public static void fillT_TEAM() {
		ArrayList<TeamBasicStruct> lists = SpiderTeam.spiderTeam();
		
		for(TeamBasicStruct tbs : lists){
			//存入数据库
			String ord = "INSERT INTO t_team VALUES('" + tbs.getTempFullName() + "', '" + tbs.getTempAbbName() + "', '" 
					+ tbs.getHistoryFullName() + "', '" + tbs.getHistoryAbblName() + "', '"
					+ tbs.getLocation() + "', '" + tbs.getCompetionArea() + "', '" + tbs.getSubArea() + "', '"
					+ tbs.getHomeGround() + "', '" + tbs.getSetupTime() + "')";
			DataBaseOpe.createTab_UpdateSQL(ord);
			//控制台输出以提示当前操作
			System.out.println("INSERT INTO t_team(" + tbs.getTempFullName() + ")");
		}
	}
	
	public static void main(String[] args) {
		FillT_TEAM.fillT_TEAM();
	}
}

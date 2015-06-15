package dataBase.initDB;

import java.util.ArrayList;

import spider.spiderMatch.MatchStruct;
import spider.spiderMatch.PlayerStruct;
import spider.spiderMatch.SpiderMatch;
import dataBase.dataBaseOpe.DataBaseOpe;

public class InsertMatch {

	public void insertMatch(String url, int year, String season, boolean isPlayerOff) {
		MatchStruct ms = SpiderMatch.spiderMatch(url, season, true);
		String ord = "INSERT INTO t_match_" + year + " VALUES('" + ms.getId() + "', '" + ms.getSeason() + "', '" 
				+ ms.getIsPlayOffs() + "', '" + ms.getYear() + "', '" + ms.getDate() + "', '"
				+ ms.getVtAbbName() + "', '" + ms.getVtFullName() + "', '" + ms.getVtScores() + "', '"
				+ ms.getHtAbbName() + "', '" + ms.getHtFullName() + "', '" + ms.getHtScores() + "')";
		DataBaseOpe.createTab_UpdateSQL(ord);
		//控制台输出以提示当前操作
		System.out.println("INSERT INTO t_match_" + year + "(" + ms.getId() + ")");

		
		//球员比赛数据存入数据库
		ArrayList<PlayerStruct> lists = new ArrayList<PlayerStruct>();
		lists = ms.getVtPlayers();
		lists.addAll(ms.getHtPlayers());
		for(PlayerStruct ps : lists){
			ord = "INSERT INTO t_match_player_" + year + " VALUES('" + ms.getId() + "', '" + ps.getName() + "', '" 
					+ ps.getTeam() + "', '" + ps.getIsStarter() + "', '" + ps.getMinutesPlayed() + "', '"
					+ ps.getFieldGoals() + "', '" + ps.getFieldGoalsAttempts() + "', '" + ps.getThreePoints() + "', '"
					+ ps.getThreePointsAttempts() + "', '" + ps.getFreeThrows() + "', '" + ps.getFreeThrowsAttempts() + "', '"
					+ ps.getOffensiveRebounds() + "', '" + ps.getDefensiveRebounds() + "', '" + ps.getAssists() + "', '"
					+ ps.getSteals() + "', '" + ps.getBlocks() + "', '" + ps.getTurnovers() + "', '"
					+ ps.getFouls() + "', '" + ps.getPoints() + "')";
			DataBaseOpe.createTab_UpdateSQL(ord);
			//控制台输出以提示当前操作
			System.out.println("INSERT INTO t_match_player_" + year + "(" + ms.getId() + ", " + ps.getName() + ")");
		}
	}
	
	public static void main(String[] args) {
		String url = "";
		String season = "";
		int year = 2000;
		boolean isPlayOff = true;
		new InsertMatch().insertMatch(url, year, season, isPlayOff);
	}
}

package dataBase.initDB;

import java.util.ArrayList;

import spider.spiderMatch.MatchStruct;
import spider.spiderMatch.MatchesLinks;
import spider.spiderMatch.PlayerStruct;
import spider.spiderMatch.SpiderMatch;
import dataBase.dataBaseOpe.DataBaseOpe;

/*=========================================================*
 * 同时加载t_match和t_match_player两张表格(某一赛季year-1~year)
 *=========================================================*/
public class FillT_MATCH implements Runnable{
	
	int year = 0;
	
	public FillT_MATCH(int year) {	
		this.year = year;
	}
	
	public void run() {
		DataBaseOpe.createTab_UpdateSQL("DROP TABLE IF EXISTS t_match_" + year);
		DataBaseOpe.createTab_UpdateSQL("CREATE TABLE IF NOT EXISTS "
				+ "t_match_" + year + "(mid VARCHAR(20), season VARCHAR(5), isPlayOffs VARCHAR(1), year VARCHAR(4), date VARCHAR(5), "
				+ "vtAbbName VARCHAR(3), vtFullName VARCHAR(40), vtScores VARCHAR(25),"
				+ "htAbbName VARCHAR(3), htFullName VARCHAR(40), htScores VARCHAR(25))");
	
		DataBaseOpe.createTab_UpdateSQL("DROP TABLE IF EXISTS t_match_player" + year);
		DataBaseOpe.createTab_UpdateSQL("CREATE TABLE IF NOT EXISTS "
				+ "t_match_player_" + year + "(mid VARCHAR(20), name VARCHAR(40), belongTo VARCHAR(40), isStarter VARCHAR(1), "
				+ "MinutesPlayed VARCHAR(5), fieldGoals VARCHAR(3), fieldGoalsAttempts VARCHAR(3), threePoints VARCHAR(3), threePointsAttempts VARCHAR(3), "
				+ "freeThrow VARCHAR(3), freeThrowAttempts VARCHAR(3), offensiveRebounds VARCHAR(3), deffensiveRebounds VARCHAR(3), assists VARCHAR(3), steals VARCHAR(3), "
				+ "blocks VARCHAR(3), turnovers VARCHAR(3), fouls VARCHAR(3), points VARCHAR(3))");
			
		
		String season = String.valueOf(year - 1).substring(2, 4) + "-" + String.valueOf(year).substring(2, 4);
		//常规赛
		insertInto(year, season, false);
		//季后赛
		insertInto(year, season, true);
	}
	
	/*============================================*
	 * 获得某一年的所有常规赛或季后赛#存入数据库
	 * 存入数据库每场比赛球员表现
	 *============================================*/
	private static void insertInto(int year, String season, boolean isPlayOffs){
		ArrayList<String> matchLink_urls = MatchesLinks.matchesLinks(year, isPlayOffs);
		for(String url : matchLink_urls){
			//把每一场比赛写入数据库
			MatchStruct ms = SpiderMatch.spiderMatch(url, season, isPlayOffs);
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
	}
}

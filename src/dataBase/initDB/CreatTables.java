package dataBase.initDB;

import dataBase.dataBaseOpe.DataBaseOpe;

/*=============================================================================================*
    创建nba数据库表格包括
  t_match
  t_match_player
  t_team
  t_player
 *=============================================================================================*/
public class CreatTables {
	public static void creatTables() {
		
		DataBaseOpe.createTab_UpdateSQL("DROP TABLE IF EXISTS t_match");
		DataBaseOpe.createTab_UpdateSQL("CREATE TABLE IF NOT EXISTS "
				+ "t_match(mid VARCHAR(20), season VARCHAR(5), isPlayOffs VARCHAR(1), year VARCHAR(4), date VARCHAR(5), "
				+ "vtAbbName VARCHAR(3), vtFullName VARCHAR(40), vtScores VARCHAR(25),"
				+ "htAbbName VARCHAR(3), htFullName VARCHAR(40), htScores VARCHAR(25))");
	
		DataBaseOpe.createTab_UpdateSQL("DROP TABLE IF EXISTS t_match_player");
		DataBaseOpe.createTab_UpdateSQL("CREATE TABLE IF NOT EXISTS "
				+ "t_match_player(mid VARCHAR(20), name VARCHAR(40), belongTo VARCHAR(40), isStarter VARCHAR(1), "
				+ "MinutesPlayed VARCHAR(5), fieldGoals VARCHAR(3), fieldGoalsAttempts VARCHAR(3), threePoints VARCHAR(3), threePointsAttempts VARCHAR(3), "
				+ "freeThrow VARCHAR(3), freeThrowAttempts VARCHAR(3), offensiveRebounds VARCHAR(3), deffensiveRebounds VARCHAR(3), assists VARCHAR(3), steals VARCHAR(3), "
				+ "blocks VARCHAR(3), turnovers VARCHAR(3), fouls VARCHAR(3), points VARCHAR(3))");
	
		DataBaseOpe.createTab_UpdateSQL("DROP TABLE IF EXISTS t_team");
		DataBaseOpe.createTab_UpdateSQL("CREATE TABLE IF NOT EXISTS "
				+ "t_team(tempFullName VARCHAR(40), tempAbbName VARCHAR(3), hisFullName VARCHAR(40), hisAbbName VARCHAR(3), "
				+ "location VARCHAR(40), competionArea VARCHAR(1), subArea VARCHAR(40), homeGround VARCHAR(40), setupTime VARCHAR(4))");

		DataBaseOpe.createTab_UpdateSQL("DROP TABLE IF EXISTS t_player");
		DataBaseOpe.createTab_UpdateSQL("CREATE TABLE IF NOT EXISTS "
				+ "t_player(name VARCHAR(40), fromYear VARCHAR(4), toYear VARCHAR(4), position VARCHAR(3), height VARCHAR(5), weight VARCHAR(5), "
				+ "birth VARCHAR(10), college VARCHAR(80))");
	}
}

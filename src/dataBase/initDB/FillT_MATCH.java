package dataBase.initDB;

import java.util.ArrayList;

import spider.spiderMatch.MatchStruct;
import spider.spiderMatch.MatchesLinks;
import spider.spiderMatch.PlayerStruct;
import spider.spiderMatch.SpiderMatch;
import dataBase.dataBaseOpe.DataBaseOpe;

/*=========================================================*
 * ͬʱ����t_match��t_match_player���ű���(ĳһ����year-1~year)
 *=========================================================*/
public class FillT_MATCH implements Runnable{
	
	int year = 0;
	
	public FillT_MATCH(int year) {	
		this.year = year;
	}
	
	public void run() {
		//������
		insertInto(year, false);
		//������
		insertInto(year, true);
	}
	
	/*============================================*
	 * ���ĳһ������г������򼾺���#�������ݿ�
	 * �������ݿ�ÿ��������Ա����
	 *============================================*/
	private static void insertInto(int year, boolean isPlayOffs){
		ArrayList<String> matchLink_urls = MatchesLinks.matchesLinks(year, isPlayOffs);
		for(String url : matchLink_urls){
			//��ÿһ������д�����ݿ�
			MatchStruct ms = SpiderMatch.spiderMatch(url, 
					String.valueOf(year - 1).substring(2, 4) + "-" + String.valueOf(year).substring(2, 4), isPlayOffs);
			String ord = "INSERT INTO t_match VALUES('" + ms.getId() + "', '" + ms.getSeason() + "', '" 
					+ ms.getIsPlayOffs() + "', '" + ms.getYear() + "', '" + ms.getDate() + "', '"
					+ ms.getVtAbbName() + "', '" + ms.getVtFullName() + "', '" + ms.getVtScores() + "', '"
					+ ms.getHtAbbName() + "', '" + ms.getHtFullName() + "', '" + ms.getHtScores() + "')";
			DataBaseOpe.createTab_UpdateSQL(ord);
			//����̨�������ʾ��ǰ����
			System.out.println("INSERT INTO t_match(" + ms.getId() + ")");

			
			//��Ա�������ݴ������ݿ�
			ArrayList<PlayerStruct> lists = new ArrayList<PlayerStruct>();
			lists = ms.getVtPlayers();
			lists.addAll(ms.getHtPlayers());
			for(PlayerStruct ps : lists){
				ord = "INSERT INTO t_match_player VALUES('" + ms.getId() + "', '" + ps.getName() + "', '" 
						+ ps.getTeam() + "', '" + ps.getIsStarter() + "', '" + ps.getMinutesPlayed() + "', '"
						+ ps.getFieldGoals() + "', '" + ps.getFieldGoalsAttempts() + "', '" + ps.getThreePoints() + "', '"
						+ ps.getThreePointsAttempts() + "', '" + ps.getFreeThrows() + "', '" + ps.getFreeThrowsAttempts() + "', '"
						+ ps.getOffensiveRebounds() + "', '" + ps.getDefensiveRebounds() + "', '" + ps.getAssists() + "', '"
						+ ps.getSteals() + "', '" + ps.getBlocks() + "', '" + ps.getTurnovers() + "', '"
						+ ps.getFouls() + "', '" + ps.getPoints() + "')";
				DataBaseOpe.createTab_UpdateSQL(ord);
				//����̨�������ʾ��ǰ����
				System.out.println("INSERT INTO t_match_player(" + ms.getId() + ", " + ps.getName() + ")");
			}
		}
	}
}
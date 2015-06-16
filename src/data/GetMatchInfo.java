package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import po.MatchPO;
import spider.spiderLive.LiveInfo;
import spider.spiderLive.SpiderLive;
import dataBase.dataBaseOpe.DataBaseOpe;
import dataservice.MatchService;

public class GetMatchInfo implements MatchService{
	
	public ArrayList<MatchPO> getAllMatchesAtSeason(String season) {
		int year = this.seasonToYear(season);
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match_" + year + " WHERE season = '" + season + "' ORDER BY year DESC, date DESC");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player_" + year + " WHERE mid LIKE '" + season + "%' ORDER BY mid DESC");
		ArrayList<MatchPO> ret = RSToMatchPO.toMatchPO(rs_1, rs_2);
		
		DataBaseOpe.close();
		return ret;
	}

	public ArrayList<MatchPO> getTodayAllMatches() {
		String lastDate = getLastDate();
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match_2015 WHERE date = '" + lastDate + "'");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player_2015 WHERE mid in"
				+ " (SELECT DISTINCT mid FROM t_match_2015 WHERE date = '" + lastDate + "') ORDER BY mid DESC");
		ArrayList<MatchPO> ret = RSToMatchPO.toMatchPO(rs_1, rs_2);
	
		DataBaseOpe.close();
		return ret;
	}

	public ArrayList<MatchPO> getAllMatchesAboutPlayer(String name,
			String season) {
		int year = this.seasonToYear(season);
		name = name.replace('\'', '#');
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match_" + year + " WHERE mid IN "
			+ "(SELECT DISTINCT mid FROM t_match_player_" + year + " WHERE mid LIKE '" + season + "%' AND name = '" + name + "') ORDER BY year DESC, date DESC");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player_" + year + " WHERE mid IN "
			+ "(SELECT DISTINCT mid FROM t_match_player_" + year + " WHERE mid LIKE '" + season + "%' AND name = '" + name + "') ORDER BY mid DESC");
		ArrayList<MatchPO> ret = RSToMatchPO.toMatchPO(rs_1, rs_2);
		
		DataBaseOpe.close();
		return ret;
	}

	public ArrayList<MatchPO> getLastFiveMatchesAboutPlayer(String name) {
		name = name.replace('\'', '#');
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match_2015 WHERE mid IN "
				+ "(SELECT DISTINCT mid FROM t_match_player_2015 WHERE name = '" + name + "') ORDER BY year DESC, date DESC");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player_2015 WHERE mid IN "
				+ "(SELECT DISTINCT mid FROM t_match_player_2015 WHERE name = '" + name + "') ORDER BY mid DESC");
		ArrayList<MatchPO> res =  RSToMatchPO.toMatchPO(rs_1, rs_2);
		ArrayList<MatchPO> ret = new ArrayList<MatchPO>();
		
		//未找到最近五场
		if(res == null){
			DataBaseOpe.close();			
			return new ArrayList<MatchPO>();
		}
		
		//找到
		for(int i = 0; i < res.size(); i++){
			ret.add(res.get(i));
			
			if(i >= 4)
				break;
		}
		
		DataBaseOpe.close();
		return ret;
	}

	public ArrayList<MatchPO> getAllMatchesAboutTeam(String abbName,
			String season) {
		int year = this.seasonToYear(season);
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match_" + year + " WHERE season = '" + season
				+ "' AND (vtAbbName = '" + abbName + "' OR htAbbName = '" + abbName + "') ORDER BY mid DESC");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player_" + year + " WHERE mid IN "
				+ "(SELECT DISTINCT mid FROM t_match_" + year + " WHERE season = '" + season + "' AND (vtAbbName = '" + abbName + "' OR htAbbName = '" + abbName + "')) ORDER BY mid DESC");
		ArrayList<MatchPO> ret = RSToMatchPO.toMatchPO(rs_1, rs_2);

		DataBaseOpe.close();
		return ret;
	}

	public ArrayList<MatchPO> getLastFiveMatchesAboutTeam(String abbName) {
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match_2015 WHERE vtAbbName = '" + abbName + "' OR htAbbName = '" + abbName + "' ORDER BY year DESC, date DESC");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player_2015 WHERE mid IN "
				+ "(SELECT DISTINCT mid FROM t_match_2015 WHERE vtAbbName = '" + abbName + "' OR htAbbName = '" + abbName + "') ORDER BY mid DESC");
		ArrayList<MatchPO> res =  RSToMatchPO.toMatchPO(rs_1, rs_2);
		ArrayList<MatchPO> ret = new ArrayList<MatchPO>();
		
		for(int i = 0; i < res.size(); i++){
			ret.add(res.get(i));
			
			if(i >= 4)
				break;
		}
		
		DataBaseOpe.close();
		return ret;
	}
	
	//最近赛季
	public String getLastSeason(){
		//降序排列#所以第一项为最近比赛赛季
		return getExistedSeasons().get(0);
	}
	
	//获得数据库中已存在的赛季列表
	public ArrayList<String> getExistedSeasons() {
		ArrayList<String> seasons = new ArrayList<String>();
		seasons.add("14-15");
		seasons.add("13-14");
		seasons.add("12-13");
		seasons.add("11-12");
		seasons.add("10-11");
		seasons.add("09-10");
		seasons.add("08-09");
		seasons.add("07-08");
		seasons.add("06-07");
		seasons.add("05-06");
		return seasons;
	}
	
	//获得最近日期
	public String getLastDate(){
		try {
			ResultSet rs = DataBaseOpe.querySQL("SELECT date FROM t_match_2015 ORDER BY year DESC, date DESC");
			if(rs.next())
				return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//直播
	public LiveInfo getLiveInfo() {
		return SpiderLive.spiderLive();
	}
	
	public int seasonToYear(String season){
		int year = 2000 + Integer.valueOf((season.split("-")[1]));
		return year;
	}
}

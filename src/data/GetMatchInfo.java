package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import po.MatchPO;
import dataBase.dataBaseOpe.DataBaseOpe;
import dataservice.MatchService;

public class GetMatchInfo implements MatchService{
	
	public ArrayList<MatchPO> getAllMatchesAtSeason(String season) {
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match WHERE season = '" + season + "' ORDER BY mid DESC");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE mid LIKE '" + season + "%' ORDER BY mid DESC");
		return RSToMatchPO.toMatchPO(rs_1, rs_2);
	}

	public ArrayList<MatchPO> getTodayAllMatches() {
		String lastDate = getLastDate();
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match WHERE date = '" + lastDate + "'");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE mid in"
				+ " (SELECT DISTINCT mid FROM t_match WHERE date = '" + lastDate + "')");
		return RSToMatchPO.toMatchPO(rs_1, rs_2);
	}

	public ArrayList<MatchPO> getAllMatchesAboutPlayer(String name,
			String season) {
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match WHERE mid IN "
			+ "(SELECT DISTINCT mid FROM t_match_player WHERE mid LIKE '%" + season + "%' AND name = '" + name + "') ORDER BY mid DESC");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE mid IN "
			+ "(SELECT DISTINCT mid FROM t_match_player WHERE mid LIKE '%" + season + "%' AND name = '" + name + "') ORDER BY mid DESC");
		return RSToMatchPO.toMatchPO(rs_1, rs_2);
	}

	public ArrayList<MatchPO> getLastFiveMatchesAboutPlayer(String name) {
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match WHERE mid IN "
				+ "(SELECT DISTINCT mid FROM t_match_player WHERE name = '" + name + "') ORDER BY year DESC, date DESC LIMIT 0, 5");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE mid IN "
				+ "(SELECT DISTINCT mid FROM t_match WHERE mid IN "
			+ "(SELECT DISTINCT mid FROM t_match_player WHERE name = '" + name + "')"
				+ " ORDER BY year DESC, date DESC LIMIT 0, 5)");
		return RSToMatchPO.toMatchPO(rs_1, rs_2);
	}

	public ArrayList<MatchPO> getAllMatchesAboutTeam(String abbName,
			String season) {
		
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match WHERE season = '" + season
				+ "' AND (vtAbbName = '" + abbName + "' OR htAbbName = '" + abbName + "') ORDER BY mid DESC");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE mid IN "
				+ "(SELECT DISTINCT mid FROM t_match WHERE season = '" + season + "' AND (vtAbbName = '" + abbName + "' OR htAbbName = '" + abbName + "')) ORDER BY mid DESC");
		return RSToMatchPO.toMatchPO(rs_1, rs_2);
	}

	public ArrayList<MatchPO> getLastFiveMatchesAboutTeam(String abbName) {
		ResultSet rs_1 = DataBaseOpe.querySQL("SELECT * FROM t_match WHERE mid IN "
				+ "(SELECT DISTINCT mid FROM t_match_player WHERE vtAbbName = '" + abbName + "' OR htAbbName = '" + abbName + "') ORDER BY year DESC, date DESC LIMIT 0, 5");
		ResultSet rs_2 = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE mid IN "
				+ "(SELECT DISTINCT mid FROM t_match WHERE mid IN "
			+ "(SELECT DISTINCT mid FROM t_match_player WHERE vtAbbName = '" + abbName + "' OR htAbbName = '" + abbName + "')"
				+ " ORDER BY year DESC, date DESC LIMIT 0, 5)");
		return RSToMatchPO.toMatchPO(rs_1, rs_2);
	}
	
	//最近赛季
	public String getLastSeason(){
		//降序排列#所以第一项为最近比赛赛季
		return getExistedSeasons().get(0);
	}
	
	//获得数据库中已存在的赛季列表
	public ArrayList<String> getExistedSeasons() {
		ResultSet rs = DataBaseOpe.querySQL("SELECT DISTINCT season FROM t_match ORDER BY season DESC");
		
		ArrayList<String> seasons = new ArrayList<String>();
		for(String[] temp : RSToBasicPO.to2DStringArray(rs)){
			seasons.add(temp[0]);
		}
		return seasons;
	}
	
	//获得最近日期
	public String getLastDate(){
		try {
			ResultSet rs = DataBaseOpe.querySQL("SELECT date FROM t_match ORDER BY year DESC, date DESC");
			if(rs.next())
				return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.PBasicInfoPO;
import po.PSeasonDataPO;
import po.TBasicInfoPO;
import data.statistics.PlayerCareer;
import data.statistics.PlayerSeason;
import dataBase.dataBaseOpe.DataBaseOpe;
import dataservice.MatchService;
import dataservice.PlayerService;
import dataservice.TeamService;

public class GetPlayerInfo implements PlayerService{

	public ArrayList<String> getAllPlayersName() {
		ArrayList<String> names = new ArrayList<String>();
		
		String order = "SELECT name FROM t_player";
		ResultSet rs = DataBaseOpe.querySQL(order);
		ArrayList<String[]> tab = RSToBasicPO.to2DStringArray(rs);
		for(int i = 0; i < tab.size(); i++)
			names.add(tab.get(i)[0]);
		
		return names;
	}

	public PBasicInfoPO getSinglePBasicInfo(String name) {
		String order = "SELECT * FROM t_player WHERE name = '" + name + "'";
		ResultSet rs = DataBaseOpe.querySQL(order);
		
		return RSToBasicPO.toPlayerBasic(rs);
	}

	public PSeasonDataPO getOnePSeasonDataPO(String name, String season) {
		MatchService ms = new GetMatchInfo();
		TeamService ts = new GetTeamInfo();
		PlayerService ps = new GetPlayerInfo();
		
		if(season.equals("ALL")){
			ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE name = '" + name + "'");
			ArrayList<MatchPlayerDataPO> players = RSToMatchPO.toMatchPlayerDataPO(RSToBasicPO.to2DStringArray(rs));
			return new PlayerCareer().getPlayerCareerData(players);
		}
		
		ArrayList<MatchPO> matches = ms.getAllMatchesAboutPlayer(name, season);
		ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE name = '" + name + "' AND mid LIKE '" + season + "%'");
		ArrayList<MatchPlayerDataPO> players = RSToMatchPO.toMatchPlayerDataPO(RSToBasicPO.to2DStringArray(rs));
		ArrayList<TBasicInfoPO> teams = ts.getAllTBasicInfo();
		PBasicInfoPO playerinfo = ps.getSinglePBasicInfo(name);
		return new PlayerSeason().playerSeason(matches, players, teams, playerinfo);
	}

	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season) {
		return null;			
	}
}

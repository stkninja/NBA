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
			names.add(tab.get(i)[0].replace('#', '\''));
		
		return names;
	}

	public PBasicInfoPO getSinglePBasicInfo(String name) {
		String order = "SELECT * FROM t_player WHERE name = '" + name.replace('\'', '#') + "'";
		ResultSet rs = DataBaseOpe.querySQL(order);
		
		return RSToBasicPO.toPlayerBasic(rs).get(0);
	}
	
	public ArrayList<PBasicInfoPO> getAllPlayersPBasicInfo(){
		String order = "SELECT * FROM t_player";
		ResultSet rs = DataBaseOpe.querySQL(order);
		
		return RSToBasicPO.toPlayerBasic(rs);
	}

	public PSeasonDataPO getOnePSeasonDataPO(String name, String season) {
		name = name.replace('\'', '#');
		MatchService ms = new GetMatchInfo();
		TeamService ts = new GetTeamInfo();
		PlayerService ps = new GetPlayerInfo();
		
		if(season.equals("ALL")){
			ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE name = '" + name + "'");
			ArrayList<MatchPlayerDataPO> players = RSToMatchPO.toMatchPlayerDataPO(RSToBasicPO.to2DStringArray(rs));
			
			if(players == null)
				return null;
			return new PlayerCareer().getPlayerCareerData(players);
		}
		
		ArrayList<MatchPO> matches = ms.getAllMatchesAboutPlayer(name, season);
		ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE name = '" + name + "' AND mid LIKE '" + season + "%'");
		ArrayList<MatchPlayerDataPO> players = RSToMatchPO.toMatchPlayerDataPO(RSToBasicPO.to2DStringArray(rs));
		ArrayList<TBasicInfoPO> teams = ts.getAllTBasicInfo();
		PBasicInfoPO playerinfo = ps.getSinglePBasicInfo(name);
		if(matches == null)
			return null;
		return new PlayerSeason().playerSeason(matches, players, teams, playerinfo);
	}

	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season) {
		TeamService ts = new GetTeamInfo();
		PlayerService ps = new GetPlayerInfo();
		MatchService ms = new GetMatchInfo();

		//赛季所有matchPlayerDataPO(')
		ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_match_player WHERE mid LIKE '" + season + "%' ORDER BY name DESC");
		ArrayList<MatchPlayerDataPO> mpds = RSToMatchPO.toMatchPlayerDataPO(RSToBasicPO.to2DStringArray(rs));

		//赛季所有球员名字(')
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String[]> tab = RSToBasicPO.to2DStringArray(rs);
		names.add(tab.get(0)[1].replace('#', '\''));
		
		for(int i = 1; i < tab.size(); i++){	
			//是否已存在
			for(int j = 0; j < names.size(); j++){
				if(tab.get(i)[1].replace('#', '\'').equals(names.get(j)))
					break;
				else if(j == names.size() - 1)
					names.add(tab.get(i)[1].replace('#', '\''));
			}
		}

		//赛季所有比赛(')
		ArrayList<MatchPO> matches = ms.getAllMatchesAtSeason(season);
		
		//计算
		ArrayList<PSeasonDataPO> psds = new ArrayList<PSeasonDataPO>();

		ArrayList<TBasicInfoPO> teams = ts.getAllTBasicInfo();
		ArrayList<PBasicInfoPO> pbs = ps.getAllPlayersPBasicInfo();
		for(String name : names){			
			PBasicInfoPO playerinfo = new PBasicInfoPO();
			ArrayList<MatchPO> matchesAboutPlayer = new ArrayList<MatchPO>();
			ArrayList<MatchPlayerDataPO> mpdsAboutPlayer = new ArrayList<MatchPlayerDataPO>(); 
			
			for(int i = 0; i < pbs.size(); i++){
				if(pbs.get(i).getName().equals(name)){
					playerinfo = pbs.get(i);
					pbs.remove(i);
					i--;
				}
			}
			
			for(int i = 0; i < matches.size(); i++){
				if(matches.get(i).getTeam1().existPlayer(name)){
					matchesAboutPlayer.add(matches.get(i));
				}
				else if(matches.get(i).getTeam2().existPlayer(name)){
					matches.get(i).swapTeam();
					matchesAboutPlayer.add(matches.get(i));
				}
			}

			for(int i = 0; i < mpds.size(); i++){
				if(mpds.get(i).getName().equals(name)){
					mpdsAboutPlayer.add(mpds.get(i));
					mpds.remove(i);
					i--;
				}
			}
			
			psds.add(new PlayerSeason().playerSeason(matchesAboutPlayer, mpdsAboutPlayer, teams, playerinfo));
		}
		return psds;
	}
}

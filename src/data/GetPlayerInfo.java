package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.PBasicInfoPO;
import po.PSeasonDataPO;
import po.TBasicInfoPO;
import data.pre.Predo;
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
		
		DataBaseOpe.close();
		return names;
	}

	public PBasicInfoPO getSinglePBasicInfo(String name) {
		String order = "SELECT * FROM t_player WHERE name = '" + name.replace('\'', '#') + "'";
		ResultSet rs = DataBaseOpe.querySQL(order);
		ArrayList<PBasicInfoPO> pbs = RSToBasicPO.toPlayerBasic(rs);
		
		if(pbs.size() == 0){
			PBasicInfoPO pb = new PBasicInfoPO();
			pb.setName("Louis Williams");
			return pb;
		}
			
		PBasicInfoPO ret = pbs.get(0);
		DataBaseOpe.close();
		return ret;
	}
	
	public ArrayList<PBasicInfoPO> getAllPlayersPBasicInfo(){
		String order = "SELECT * FROM t_player";
		ResultSet rs = DataBaseOpe.querySQL(order);
		ArrayList<PBasicInfoPO> ret = RSToBasicPO.toPlayerBasic(rs);
		
		DataBaseOpe.close();
		return ret;
	}

	public PSeasonDataPO getOnePSeasonDataPO(String name, String season) {
		int year;
		name = name.replace('\'', '#');
		
		//生涯统计
		if(season.equals("ALL")){
			ArrayList<MatchPlayerDataPO> players = new ArrayList<MatchPlayerDataPO>();
			for(year = 2006; year <= 2015; year++){
				ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_match_player_" + year + " WHERE name = '" + name + "'");
				players.addAll(RSToMatchPO.toMatchPlayerDataPO(RSToBasicPO.to2DStringArray(rs)));
			}
			
			DataBaseOpe.close();
			if(players.size() == 0)
				return new PSeasonDataPO();
			return new PlayerCareer().getPlayerCareerData(players);
		}
		
		//赛季统计
		ArrayList<PSeasonDataPO> psds = Predo.getAllPSeasonDataAt(season);
		for(PSeasonDataPO psd : psds){
			if(psd.getName().equals(name))
				return psd;
		}
		return new PSeasonDataPO();
	}

	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season){
		return Predo.getAllPSeasonDataAt(season);
	}
	
	//用于Predo计算
	public ArrayList<PSeasonDataPO> getAllPSeasonData_2(String season) {
		int year = this.seasonToYear(season);
		
		TeamService ts = new GetTeamInfo();
		PlayerService ps = new GetPlayerInfo();
		MatchService ms = new GetMatchInfo();

		//赛季所有matchPlayerDataPO(')
		ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_match_player_" + year + " WHERE mid LIKE '" + season + "%' ORDER BY name DESC");
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
	
	public int seasonToYear(String season){
		int year = 2000 + Integer.valueOf((season.split("-")[1]));
		return year;
	}
}

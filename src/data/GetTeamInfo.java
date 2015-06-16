package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import po.MatchPO;
import po.TBasicInfoPO;
import po.TSeasonDataPO;
import data.pre.Predo;
import data.statistics.TeamSeason;
import dataBase.dataBaseOpe.DataBaseOpe;
import dataservice.TeamService;

public class GetTeamInfo implements TeamService{

	public TBasicInfoPO getSingleTBasicInfo(String abbName) {
		String order = "SELECT * FROM t_team WHERE tempAbbName = '" + abbName + 
				"' OR hisAbbName = '" + abbName +"'";
		ResultSet rs = DataBaseOpe.querySQL(order);
		
		return RSToBasicPO.toTeamBasic(rs).get(0);
	}

	public ArrayList<TBasicInfoPO> getAllTBasicInfo() {
		ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_team");
		return RSToBasicPO.toTeamBasic(rs);
	}
	
	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season) {
		ArrayList<TBasicInfoPO> tbs = this.getAllTBasicInfo();
		for(TBasicInfoPO tb : tbs){
			if(tb.getAbbName().equals(abbName)){
				ArrayList<MatchPO> matches = new GetMatchInfo().getAllMatchesAboutTeam(tb.getAbbName(), season);
				return new TeamSeason().teamSeason(matches, tb, tb.getAbbName());
			}
			else if(tb.getHistoryAbblName().equals(abbName)){
				ArrayList<MatchPO> matches = new GetMatchInfo().getAllMatchesAboutTeam(tb.getHistoryAbblName(), season);
				return new TeamSeason().teamSeason(matches, tb, tb.getHistoryAbblName());
			}
		}
		return null;
	}

	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season){
		return Predo.getAllTSeasonDataAt(season);
	}
	
	public ArrayList<TSeasonDataPO> getAllTSeasonData_2(String season) {
		ArrayList<TSeasonDataPO> tsds = new ArrayList<TSeasonDataPO>();
		
		ArrayList<TBasicInfoPO> tbs = this.getAllTBasicInfo();
		ArrayList<MatchPO> matches = new GetMatchInfo().getAllMatchesAtSeason(season);
		String abbName = new String();
		ArrayList<MatchPO> matchesAboutTeam = new ArrayList<MatchPO>();
		for(TBasicInfoPO tb : tbs){			
			//筛选所有关于team的比赛
			abbName = new String();
			matchesAboutTeam = new ArrayList<MatchPO>();
			for(int i = 0; i < matches.size(); i++){
				if(matches.get(i).getTeam1().getAbbName().equals(tb.getAbbName()) || matches.get(i).getTeam2().getAbbName().equals(tb.getAbbName())){
					matchesAboutTeam.add(matches.get(i));
					abbName = tb.getAbbName();
				}
				else if(matches.get(i).getTeam1().getAbbName().equals(tb.getHistoryAbblName()) || matches.get(i).getTeam2().getAbbName().equals(tb.getHistoryAbblName())){
					matchesAboutTeam.add(matches.get(i));
					abbName = tb.getHistoryAbblName();
				}
			}
			
			//计算
			if(matchesAboutTeam.size() != 0)
				tsds.add(new TeamSeason().teamSeason(matchesAboutTeam, tb, abbName));
		}
		return tsds;
	}	
}

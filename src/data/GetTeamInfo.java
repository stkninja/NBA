package data;

import java.util.ArrayList;

import po.MatchPO;
import po.TBasicInfoPO;
import po.TSeasonDataPO;
import data.predo.MatchBasic;
import data.predo.TeamBasic;
import data.predo.TeamSeason;
import dataservice.MatchService;
import dataservice.TeamService;

public class GetTeamInfo implements TeamService{

	public TBasicInfoPO getSingleTBasicInfo(String abbName) {
		if(abbName.equals("NOH"))
			abbName = "NOP";
		
		ArrayList<TBasicInfoPO> pos = new TeamBasic().teamBasic();
		
		for(TBasicInfoPO po : pos)
			if(po.getAbbName().equals(abbName))
				return po;
		
		return null;
	}

	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season) {
		if(abbName.equals("NOP") && season.compareTo("12-13") <= 0)
			abbName = "NOH";
		
		ArrayList<MatchPO> matches = new MatchBasic().matchBasic(season);
		ArrayList<TBasicInfoPO> teams = new TeamBasic().teamBasic();
		
		ArrayList<TSeasonDataPO> pos = new TeamSeason().teamSeason(matches, teams, season);
		for(TSeasonDataPO po :pos)
			if(po.getAbbName().equals(abbName))
				return po;
		
		return null;
	}

	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season) {
		ArrayList<MatchPO> matches = new ArrayList<MatchPO>();
		if(season.equals("all")){
			MatchService ms = new GetMatchInfo();
			ArrayList<String> seasons = ms.getExistedSeasons();
			for(String ss : seasons)
				matches.addAll(new MatchBasic().matchBasic(ss));
		}
		else{
			matches = new MatchBasic().matchBasic(season);
		}
		ArrayList<TBasicInfoPO> teams = new TeamBasic().teamBasic();
		return new TeamSeason().teamSeason(matches, teams, season);
	}	
}

package data;

import java.util.ArrayList;

import po.MatchPO;
import po.TBasicInfoPO;
import po.TSeasonDataPO;
import data.predo.PreRead;
import data.predo.TeamSeason;
import dataservice.TeamService;

public class GetTeamInfo implements TeamService{

	public TBasicInfoPO getSingleTBasicInfo(String abbName) {
		if(abbName.equals("NOH"))
			abbName = "NOP";
		
		ArrayList<TBasicInfoPO> pos = PreRead.teams;
		
		for(TBasicInfoPO po : pos)
			if(po.getAbbName().equals(abbName))
				return po;
		
		return null;
	}

	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season) {
		if(abbName.equals("NOP") && season.compareTo("12-13") <= 0)
			abbName = "NOH";
		
		ArrayList<MatchPO> matches = PreRead.matches;
		ArrayList<TBasicInfoPO> teams = PreRead.teams;
		
		ArrayList<TSeasonDataPO> pos = new TeamSeason().teamSeason(matches, teams, season);
		for(TSeasonDataPO po :pos)
			if(po.getAbbName().equals(abbName))
				return po;
		
		return null;
	}

	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season) {
		ArrayList<MatchPO> matches = PreRead.matches;
		ArrayList<TBasicInfoPO> teams = PreRead.teams;
		return new TeamSeason().teamSeason(matches, teams, season);
	}	
}

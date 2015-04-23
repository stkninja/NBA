package data;

import java.util.ArrayList;

import po.MatchPO;
import po.PBasicInfoPO;
import po.PSeasonDataPO;
import po.TBasicInfoPO;
import data.predo.MatchBasic;
import data.predo.PlayerBasic;
import data.predo.PlayerSeason;
import data.predo.TeamBasic;
import dataservice.MatchService;
import dataservice.PlayerService;

public class GetPlayerInfo implements PlayerService{

	public ArrayList<String> getAllPlayersName() {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<PBasicInfoPO> pos = new PlayerBasic().playerBasic();
		
		for(PBasicInfoPO po : pos){
			names.add(po.getName());
		}
		return names;
	}

	public PBasicInfoPO getSinglePBasicInfo(String name) {
		ArrayList<PBasicInfoPO> pos = new PlayerBasic().playerBasic();
		
		for(PBasicInfoPO po : pos)
			if(po.getName().equals(name))
				return po;
		
		return null;
	}

	public PSeasonDataPO getOnePSeasonDataPO(String name, String season) {
		ArrayList<MatchPO> matches = new MatchBasic().matchBasic(season);
		ArrayList<PBasicInfoPO> players = new PlayerBasic().playerBasic();
		ArrayList<TBasicInfoPO> teams = new TeamBasic().teamBasic();
		
		ArrayList<PSeasonDataPO> pos = new PlayerSeason().playerSeason(matches, players, teams, season);;
		for(PSeasonDataPO po :pos)
			if(po.getName().equals(name))
				return po;
		
		return null;
	}

	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season) {
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
		ArrayList<PBasicInfoPO> players = new PlayerBasic().playerBasic();
		ArrayList<TBasicInfoPO> teams = new TeamBasic().teamBasic();
		return new PlayerSeason().playerSeason(matches, players, teams, season);			
	}
}

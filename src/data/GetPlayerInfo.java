package data;

import java.util.ArrayList;

import po.MatchPO;
import po.PBasicInfoPO;
import po.PSeasonDataPO;
import po.TBasicInfoPO;
import data.predo.PlayerSeason;
import data.predo.PreRead;
import dataservice.PlayerService;

public class GetPlayerInfo implements PlayerService{

	public ArrayList<String> getAllPlayersName() {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<PBasicInfoPO> pos = PreRead.players;
		
		for(PBasicInfoPO po : pos){
			names.add(po.getName());
		}
		return names;
	}

	public PBasicInfoPO getSinglePBasicInfo(String name) {
		ArrayList<PBasicInfoPO> pos = PreRead.players;
		
		for(PBasicInfoPO po : pos)
			if(po.getName().equals(name))
				return po;
		
		return null;
	}

	public PSeasonDataPO getOnePSeasonDataPO(String name, String season) {
		ArrayList<MatchPO> matches = PreRead.matches;
		ArrayList<PBasicInfoPO> players = PreRead.players;
		ArrayList<TBasicInfoPO> teams = PreRead.teams;
		
		ArrayList<PSeasonDataPO> pos = new PlayerSeason().playerSeason(matches, players, teams, season);;
		for(PSeasonDataPO po :pos)
			if(po.getName().equals(name))
				return po;
		
		return null;
	}

	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season) {
		ArrayList<MatchPO> matches = PreRead.matches;
		ArrayList<PBasicInfoPO> players = PreRead.players;
		ArrayList<TBasicInfoPO> teams = PreRead.teams;
		return new PlayerSeason().playerSeason(matches, players, teams, season);			
	}
}

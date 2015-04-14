package businesslogic;

import java.util.ArrayList;

import po.MatchPO;
import vo.MatchVO;
import data.GetMatchInfo;
import dataservice.MatchService;

public class MatchBL implements businesslogicservice.MatchBLService{
	private PlayerBL playerbl = null;
	private MatchService matchdata = null;
	
	public MatchBL(){
		playerbl = new PlayerBL();
		matchdata = new GetMatchInfo();
	}

	public ArrayList<MatchVO> getMatches(String season) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getAllMatchesAtSeason(season)){
			list.add(playerbl.potovo(po));
		}
		return list;
	}

	public ArrayList<MatchVO> getOneMatch(String team1, String team2,
			String season) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getMatchesAboutTwoTeams(team1, team2, season)){
			list.add(playerbl.potovo(po));
		}
		return list;
	}

	public ArrayList<MatchVO> getMatchesAboutTeam(String team, String season) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getAllMatchesAboutTeam(team, season)){
			list.add(playerbl.potovo(po));
		}
		return list;
	}

	public ArrayList<MatchVO> getAllMatches(){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for()
	}
	
	public ArrayList<MatchVO> getMatchesAboutTeam(String team, String season,
			String date, String player) {
		if(team.equals("All")){
			if(season.equals("All")){
				if(date.equals("All")){
					if(player.equals("All")){
						
					}
				}
			}
		}
	}
}

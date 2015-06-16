package businesslogicservice;

import java.util.ArrayList;

import vo.MatchVO;

public interface MatchBLService {
	public ArrayList<MatchVO> getMatches(String season,String isPlayOffs);
	
//	public ArrayList<MatchVO> getOneMatch(String team1,String team2,String season);
	
	public ArrayList<MatchVO> getMatchesAboutTeamSeasonDatePlayer(String team,String season,String date,String player);
	
	public ArrayList<String> getAllSeasons();
	
}

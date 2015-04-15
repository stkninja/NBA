package businesslogicservice;

import java.util.ArrayList;

import vo.MatchVO;
import vo.TeamBasicInfoVO;
import vo.TeamVO;

public interface TeamBLService {
	public ArrayList<TeamVO> getTeams(String season,String subArea);
	
	public TeamBasicInfoVO getOneTeam(String name);
	
	public TeamVO getSeasonTeams(String season,String name);
	
	public ArrayList<TeamVO> getSeasonEachMatch(String season,String name);
	
	public ArrayList<MatchVO> getSeasonMatches(String season,String name);
	
	public ArrayList<MatchVO> getLastFiveMatchesSpecific(String name);
	
	public ArrayList<TeamVO> getLastFiveMatches(String name);
	
	public ArrayList<TeamVO> getSeasonTopFiveTeams(String season,String filter);
	
}

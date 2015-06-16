package businesslogicservice;

import java.util.ArrayList;

import vo.TeamSeasonVO;

public interface TeamStatBLService {
	public ArrayList<TeamSeasonVO> getAllSeasonTeamData();
	
	public TeamSeasonVO getAllTeamData(String season);

	public ArrayList<Double> getThreepointPerYear(String season);
	
	public TeamSeasonVO getBefore();
	
	public double getLeagueThreepoint(String season);
}

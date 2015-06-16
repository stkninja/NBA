package businesslogicservice;

import java.util.ArrayList;

import vo.TeamSeasonVO;

public interface TeamStatBLService {
	public ArrayList<TeamSeasonVO> getAllSeasonTeamData();
	
	public TeamSeasonVO getAllTeamData(String season);

	public double[] getRandomScore(double n);
	
	public double[] getRandomThreepointShoot(double n);
	
//	public ArrayList<Double> getThreepoint();
	
	public ArrayList<Double> getThreepointPerYear(String season);
}

package businesslogicservice;

import java.util.ArrayList;

public interface Team {
	public ArrayList<TeamVO> getTeams(String subArea);
	
	public TeamVO getOneTeam(String name);
	
	public ArrayList<TeamVO> getAllTeams();
}

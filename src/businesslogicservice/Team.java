package businesslogicservice;

import java.util.ArrayList;

import vo.TeamBasicInfoVO;
import vo.TeamVO;

public interface Team {
	public ArrayList<TeamVO> getTeams(String subArea);
	
	public TeamBasicInfoVO getOneTeam(String name);
	
	public ArrayList<TeamVO> getAllTeams();
}

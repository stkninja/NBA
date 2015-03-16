package businesslogicservice;

import java.util.ArrayList;

import vo.TeamBasicInfoVO;
import vo.TeamVO;

public interface TeamBLService {
	public ArrayList<TeamVO> getTeams(String subArea);
	
	public TeamBasicInfoVO getOneTeam(String name);
	
	public ArrayList<TeamVO> getAllTeams();
}

package dataservice;

import java.util.ArrayList;
import po.TeamBasicInfoPO;

public interface ITeam {
	/**���ָ�������Ϣ*/
	public TeamBasicInfoPO getSingleTeamInfo(String name);
	/**������������Ϣ*/
	public ArrayList<TeamBasicInfoPO> getAllTeamsInfo();
}

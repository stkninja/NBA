package datalogicservice;

import java.util.ArrayList;
import po.TeamPO;

public interface ITeam {
	/**���ָ�������Ϣ*/
	public TeamPO getSingleTeamInfo(String name);
	/**������������Ϣ*/
	public ArrayList<TeamPO> getAllTeamsInfo();
}

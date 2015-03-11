package datalogicservice;

import java.util.ArrayList;
import po.TeamPO;

public interface ITeam {
	/**获得指定球队信息*/
	public TeamPO getSingleTeamInfo(String name);
	/**获得所有球队信息*/
	public ArrayList<TeamPO> getAllTeamsInfo();
}

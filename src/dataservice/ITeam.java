package dataservice;

import java.util.ArrayList;
import po.TeamBasicInfoPO;

public interface ITeam {
	/**获得指定球队信息*/
	public TeamBasicInfoPO getSingleTeamInfo(String name);
	/**获得所有球队信息*/
	public ArrayList<TeamBasicInfoPO> getAllTeamsInfo();
}

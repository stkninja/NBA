package dataservice;

import java.util.ArrayList;
import po.TeamBasicInfoPO;

public interface ITeam {
	/**获得某个球队基本信息*/
	public TeamBasicInfoPO getSingleTeamBasicInfo(String name);
	/**获得所有球队基本信息*/
	public ArrayList<TeamBasicInfoPO> getAllTeamsBasicInfo();
	/**获得所有球队名称*/
	public ArrayList<String> getAllTeamsName();
}

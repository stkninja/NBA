package dataservice;

import java.util.ArrayList;
import po.TeamBasicInfoPO;

public interface ITeam {
	/**获得某个球队基本信息*/
	public TeamBasicInfoPO getSingleTeamBasicInfo(String fullName);
	/**获得所有球队基本信息*/
	public ArrayList<TeamBasicInfoPO> getAllTeamsBasicInfo();
	/**获得所有球队名称*/
	public ArrayList<String> getAllTeamsName();
	/**获得球队赛季获胜场数*/
	public double getwinNum(String fullName, String season);
	/**获得球队赛季总比赛场数*/
	public double getMainNum(String fullName, String season);
}

package dataservice;

import java.util.ArrayList;
import po.TeamBasicInfoPO;

public interface ITeam {
	/**���ĳ����ӻ�����Ϣ*/
	public TeamBasicInfoPO getSingleTeamBasicInfo(String name);
	/**���������ӻ�����Ϣ*/
	public ArrayList<TeamBasicInfoPO> getAllTeamsBasicInfo();
	/**��������������*/
	public ArrayList<String> getAllTeamsName();
}

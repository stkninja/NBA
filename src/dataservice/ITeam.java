package dataservice;

import java.util.ArrayList;
import po.TeamBasicInfoPO;

public interface ITeam {
	/**���ĳ����ӻ�����Ϣ*/
	public TeamBasicInfoPO getSingleTeamBasicInfo(String fullName);
	/**���������ӻ�����Ϣ*/
	public ArrayList<TeamBasicInfoPO> getAllTeamsBasicInfo();
	/**��������������*/
	public ArrayList<String> getAllTeamsName();
	/**������������ʤ����*/
	public double getwinNum(String fullName, String season);
	/**�����������ܱ�������*/
	public double getMainNum(String fullName, String season);
}

package dataservice;

import java.util.ArrayList;
import po.TeamBasicInfoPO;

public interface ITeam {
	/**���ĳ����ӻ�����Ϣ*/
	public TeamBasicInfoPO getSingleTeamBasicInfo(String abbName);
	/**���������ӻ�����Ϣ*/
	public ArrayList<TeamBasicInfoPO> getAllTeamsBasicInfo();
	/**��������������*/
	public ArrayList<String> getAllTeamsAbbName();
	/**������������ʤ����*/
	public double getwinNum(String abbName, String season);
	/**�����������ܱ�������*/
	public double getMainNum(String abbName, String season);
}

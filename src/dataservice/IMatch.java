package dataservice;

import java.util.ArrayList;

import po.MatchPO;

public interface IMatch {
	/**��������ص����б�����Ϣ*/
	public ArrayList<MatchPO> getMatchesAboutTeam(String name);
	/**�����Ա��ص����б�����Ϣ*/
	public ArrayList<MatchPO> getMatchesAboutPlayer(String name);
}

package dataservice;

import java.util.ArrayList;

import po.MatchPO;

public interface IMatch {
	/**与该球队相关的所有比赛信息*/
	public ArrayList<MatchPO> getMatchesAboutTeam(String name);
	/**与该球员相关的所有比赛信息*/
	public ArrayList<MatchPO> getMatchesAboutPlayer(String name, String belongTeam);
	/**所有比赛数据*/
	public ArrayList<MatchPO> getAllMatches();
}

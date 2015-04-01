package dataservice;

import java.util.ArrayList;

import po.MatchPO;

public interface MatchService {
	public ArrayList<MatchPO> getAllMatchesAtSeason(String season);
	public ArrayList<MatchPO> getAllMatchesAbout(String name, String season);
	public ArrayList<MatchPO> getLastFiveMatchesAbout(String name);
	//获得今天的所有比赛
	public ArrayList<MatchPO> getTodayAllMatches();
}

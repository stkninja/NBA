package dataservice;

import java.util.ArrayList;

import po.MatchPO;
import spider.spiderLive.LiveInfo;

public interface MatchService {
	public ArrayList<MatchPO> getAllMatchesAtSeason(String season);
	//球员
	public ArrayList<MatchPO> getAllMatchesAboutPlayer(String name, String season);
	public ArrayList<MatchPO> getLastFiveMatchesAboutPlayer(String name);
	//球队
	public ArrayList<MatchPO> getAllMatchesAboutTeam(String abbName, String season);
	public ArrayList<MatchPO> getLastFiveMatchesAboutTeam(String abbName);
	//获得今天的所有比赛
	public ArrayList<MatchPO> getTodayAllMatches();
	//获得最近赛季
	public String getLastSeason();
	//获得所有赛季
	public ArrayList<String> getExistedSeasons();
	//直播接口
	public LiveInfo getLiveInfo();
}

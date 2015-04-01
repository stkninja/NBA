package data;

import java.util.ArrayList;

import po.MatchPO;
import dataservice.MatchService;

public class GetMatchInfo implements MatchService{

	public GetMatchInfo() {
	}

	public ArrayList<MatchPO> getAllMatches(String season) {
		return null;
	}

	public ArrayList<MatchPO> getAllMatchesAtSeason(String season) {
		return null;
	}

	public ArrayList<MatchPO> getAllMatchesAbout(String name, String season) {
		return null;
	}

	public ArrayList<MatchPO> getLastFiveMatchesAbout(String name) {
		return null;
	}

	public ArrayList<MatchPO> getTodayAllMatches() {
		return null;
	}
}

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

	public ArrayList<MatchPO> getAllMatchesAboutPlayer(String name,
			String season) {
		return null;
	}

	public ArrayList<MatchPO> getLastFiveMatchesAboutPlayer(String name) {
		return null;
	}

	public ArrayList<MatchPO> getAllMatchesAboutTeam(String abbName,
			String season) {
		return null;
	}

	public ArrayList<MatchPO> getLastFiveMatchesAboutTeam(String abbName) {
		return null;
	}

	public ArrayList<MatchPO> getMatchesAboutTwoTeams(String abbName1,
			String abbName2, String season) {
		return null;
	}
}

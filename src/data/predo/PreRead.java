package data.predo;

import java.util.ArrayList;

import data.GetMatchInfo;
import dataservice.MatchService;
import po.MatchPO;
import po.PBasicInfoPO;
import po.TBasicInfoPO;

public class PreRead {
	public static ArrayList<MatchPO> matches;
	public static ArrayList<PBasicInfoPO> players;
	public static ArrayList<TBasicInfoPO> teams;
	
	public PreRead() {
		MatchService ms = new GetMatchInfo();
		matches = new MatchBasic().matchBasic(ms.getLastSeason());
		teams = new TeamBasic().teamBasic();
		players = new PlayerBasic().playerBasic();
	}
}

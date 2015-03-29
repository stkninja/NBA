package dataservice;

import java.util.ArrayList;

import po.MatchPO;

public interface MatchService {
	public ArrayList<MatchPO> getAllMatches(String season);
}

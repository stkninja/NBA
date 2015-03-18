package data;

import java.util.ArrayList;

import po.MatchPO;
import dataservice.IMatch;



public class tempMain {
	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		
		IMatch iMatch = new GetMatchesInfo();
		ArrayList<MatchPO> matches = iMatch.getMatchesAboutPlayer("Carlos Delfino");
		
		long time2 = System.currentTimeMillis();
		System.out.println(matches.size());
	}
}

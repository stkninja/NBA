package data;

import java.util.ArrayList;

import po.MatchPO;
import data.rwArrangedFiles.WritePOs;



public class tempMain {
	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		
		WritePOs.writePOs();
		GetMatchesInfo getMatchesInfo = new GetMatchesInfo();
		ArrayList<MatchPO> list = getMatchesInfo.getMatchesAboutPlayer("Adonis Thomas");
		
		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
		System.out.println(list.get(0).getTeam1().existPlayer("Adonis Thomas"));
		System.out.println(list.get(2).getSeason());
		System.out.println(list.get(2).getDate());
		System.out.println(list.get(2).getTeam1().getAbbName());
		System.out.println(list.get(2).getTeam1().getAssists());
	}
}

package data;

import java.util.ArrayList;

import po.MatchPO;
import data.rwArrangedFiles.ReadPOs;
import dataservice.IMatch;

public class GetMatchesInfo implements IMatch{
	
	private static ArrayList<MatchPO> matchPOs = ReadPOs.readMatchPO();
	
	public ArrayList<MatchPO> getMatchesAboutTeam(String name) {
		ArrayList<MatchPO> toReturn = new ArrayList<MatchPO>();
		
		for(MatchPO matchPO : matchPOs){
			/**�ҵ��������йصı���*/
			if(matchPO.getTeam1().getAbbName().equals(name) || matchPO.getTeam2().getAbbName().equals(name)){
				/**��������� ��team1*/
				if(matchPO.getTeam2().getAbbName().equals(name))
					matchPO.swapTeam();
				toReturn.add(matchPO);	
			}
		}
		
		return toReturn;
	}

	public ArrayList<MatchPO> getMatchesAboutPlayer(String name) {
		ArrayList<MatchPO> toReturn = new ArrayList<MatchPO>();
		
		for(MatchPO matchPO : matchPOs){
			/**Ѱ����ر���*/
			boolean existInTeam1 = matchPO.getTeam1().existPlayer(name);
			boolean existInTeam2 = matchPO.getTeam2().existPlayer(name);
			if(existInTeam1 || existInTeam2){
				/**������team1*/
				if(existInTeam2)
					matchPO.swapTeam();	
				
				toReturn.add(matchPO);	
			}
		}
		
		return toReturn;
	}
}

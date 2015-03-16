package data;

import java.util.ArrayList;

import po.MatchPO;
import po.TeamBasicInfoPO;
import data.rwArrangedFiles.ReadPOs;
import dataservice.ITeam;

public class GetTeamsInfo implements ITeam{

	private static ArrayList<TeamBasicInfoPO> teamBasicInfoPOs = ReadPOs.readTeamBasicInfoPO();
	
	public TeamBasicInfoPO getSingleTeamBasicInfo(String abbName) {
		for(TeamBasicInfoPO basicInfoPO : teamBasicInfoPOs)
			if(basicInfoPO.getAbbName().equals(abbName))
				return basicInfoPO;
		
		return new TeamBasicInfoPO();
	}

	public ArrayList<TeamBasicInfoPO> getAllTeamsBasicInfo() {
		return teamBasicInfoPOs;
	}

	public ArrayList<String> getAllTeamsName() {
		ArrayList<String> names = new ArrayList<String>();
		for(TeamBasicInfoPO po : teamBasicInfoPOs)
			names.add(po.getFullName());
		
		return names;
	}

	public double getwinNum(String abbName, String season) {
		GetMatchesInfo getMatchesInfo = new GetMatchesInfo();
		ArrayList<MatchPO> matchPOs = getMatchesInfo.getMatchesAboutTeam(abbName);
		
		/**筛选非该赛季*/
		for(int i = 0; i < matchPOs.size(); i++){
			if(!(matchPOs.get(i).getSeason().equals(season))){
				matchPOs.remove(i);
				--i;
			}
		}
		
		/**计算胜场
		 * team1为该球队
		 * */
		double winNum = 0.0;
		for(MatchPO po : matchPOs)
			if(po.getTeam1().getScores() > po.getTeam2().getScores())
				winNum++;
		
		return winNum;
	}

	public double getMainNum(String abbName, String season) {
		GetMatchesInfo getMatchesInfo = new GetMatchesInfo();
		ArrayList<MatchPO> matchPOs = getMatchesInfo.getMatchesAboutTeam(abbName);
		
		/**筛选非该赛季*/
		for(int i = 0; i < matchPOs.size(); i++){
			if(!(matchPOs.get(i).getSeason().equals(season))){
				matchPOs.remove(i);
				--i;
			}
		}
		
		return matchPOs.size();
	}

}

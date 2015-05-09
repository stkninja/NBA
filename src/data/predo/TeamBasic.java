package data.predo;

import java.util.ArrayList;

import po.TBasicInfoPO;
import test.Console;
import data.io.ReadTeam;

public class TeamBasic {
	
	/**球队基本信息地址*/
	private static final String basicInfoAddr = new Console().getDataSource() + "\\teams\\teams";
	/**球队标志地址*/
	private static final String teamLogoAddr = new Console().getDataSource() + "\\teams";
	
	public ArrayList<TBasicInfoPO> teamBasic() {
		ArrayList<TBasicInfoPO> teams = new ArrayList<TBasicInfoPO>();
		
		/**整理球队的基本信息*/
		ArrayList<String> data = new ArrayList<String>(ReadTeam.readTBasicInfo(basicInfoAddr));
		teams = getAllTeams(data);
		
		return teams;
	}

	/**
	 * 整理球队的基本信息，如队名，球队标志等
	 */
	private static ArrayList<TBasicInfoPO> getAllTeams(ArrayList<String> data) {
		ArrayList<TBasicInfoPO> teams = new ArrayList<TBasicInfoPO>();
		for(String line : data){
			TBasicInfoPO team = new TBasicInfoPO();
			
			String[] info = line.split("║")[1].split("│");
			team.setTeamLogo(ReadTeam.readLogo(teamLogoAddr + "\\" + info[1].trim() + ".svg"));
			team.setFullName(info[0].trim());
			team.setAbbName(info[1].trim());
			team.setLocation(info[2].trim());
			team.setCompetionArea(info[3].trim());
			team.setSubArea(info[4].trim());
			team.setHomeGround(info[5].trim());
			team.setSetupTime(info[6].trim());
			
			teams.add(team);
		}
		return teams;
	}
}

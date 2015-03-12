package data;

import java.util.ArrayList;

import po.TeamBasicInfoPO;

/**
 * 用于对比赛数据整理
 * 统计得到球队信息
 */
public class ArrangeTeams {
	
	/**to return 球队基本信息*/
	private static ArrayList<TeamBasicInfoPO> teamsBasicInfo = new ArrayList<TeamBasicInfoPO>();
	/**球队基本信息地址*/
	private static final String basicInfoAddr = "D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\teams\\teams";
	/**球队标志地址*/
	private static final String teamLogoAddr = "D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\teams\\";

	public static ArrayList<TeamBasicInfoPO> arrangeTeamsBasicInfo() {
		
		/**整理球队的基本信息*/
		ArrayList<String> data = new ArrayList<String>(ReadTeamBasicInfo.readTeamBasicInfo(basicInfoAddr));
		initTeamsBasicInfo(data);
		
		return teamsBasicInfo;
	}
	
	/**
	 * 整理球队的基本信息，如队名，球队标志等
	 */
	private static void initTeamsBasicInfo(ArrayList<String> data) {
		for(String line : data){
			TeamBasicInfoPO teamBasicInfoPO = new TeamBasicInfoPO();
			
			String[] info = line.split("║")[1].split("│");
			teamBasicInfoPO.setTeamLogo(ReadTeamBasicInfo.readLogo(teamLogoAddr + info[1].trim() + ".svg"));
			teamBasicInfoPO.setFullName(info[0].trim());
			teamBasicInfoPO.setAbbName(info[1].trim());
			teamBasicInfoPO.setLocation(info[2].trim());
			teamBasicInfoPO.setCompetionArea(info[3].trim());
			teamBasicInfoPO.setSubArea(info[4].trim());
			teamBasicInfoPO.setHomeGround(info[5].trim());
			teamBasicInfoPO.setSetupTime(Integer.parseInt(info[6].trim()));
			//TODO 比赛总场数 胜率,etc
			
			teamsBasicInfo.add(teamBasicInfoPO);
		}
	}
}

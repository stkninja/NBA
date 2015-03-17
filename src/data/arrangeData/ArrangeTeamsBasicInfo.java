package data.arrangeData;

import java.util.ArrayList;

import data.readOriginFiles.ReadTeamBasicInfo;
import po.TeamBasicInfoPO;

/**
 * ���ڶԱ�����������
 * ͳ�Ƶõ������Ϣ
 */
public class ArrangeTeamsBasicInfo {
	
	/**to return ��ӻ�����Ϣ*/
	private static ArrayList<TeamBasicInfoPO> teamsBasicInfo = new ArrayList<TeamBasicInfoPO>();
	/**��ӻ�����Ϣ��ַ*/
	private static final String basicInfoAddr = "data\\teams\\teams";
	/**��ӱ�־��ַ*/
	private static final String teamLogoAddr = "data\\teams\\";

	public static ArrayList<TeamBasicInfoPO> arrangeTeamsBasicInfo() {
		
		/**������ӵĻ�����Ϣ*/
		ArrayList<String> data = new ArrayList<String>(ReadTeamBasicInfo.readTeamBasicInfo(basicInfoAddr));
		initTeamsBasicInfo(data);
		
		return teamsBasicInfo;
	}
	
	/**
	 * ������ӵĻ�����Ϣ�����������ӱ�־��
	 */
	private static void initTeamsBasicInfo(ArrayList<String> data) {
		for(String line : data){
			TeamBasicInfoPO teamBasicInfoPO = new TeamBasicInfoPO();
			
			String[] info = line.split("�U")[1].split("��");
			teamBasicInfoPO.setTeamLogo(ReadTeamBasicInfo.readLogo(teamLogoAddr + info[1].trim() + ".svg"));
			teamBasicInfoPO.setFullName(info[0].trim());
			teamBasicInfoPO.setAbbName(info[1].trim());
			teamBasicInfoPO.setLocation(info[2].trim());
			teamBasicInfoPO.setCompetionArea(info[3].trim());
			teamBasicInfoPO.setSubArea(info[4].trim());
			teamBasicInfoPO.setHomeGround(info[5].trim());
			teamBasicInfoPO.setSetupTime(info[6].trim());
			
			teamsBasicInfo.add(teamBasicInfoPO);
		}
	}
}

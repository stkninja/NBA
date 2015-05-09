package data.predo;

import java.util.ArrayList;

import po.TBasicInfoPO;
import test.Console;
import data.io.ReadTeam;

public class TeamBasic {
	
	/**��ӻ�����Ϣ��ַ*/
	private static final String basicInfoAddr = new Console().getDataSource() + "\\teams\\teams";
	/**��ӱ�־��ַ*/
	private static final String teamLogoAddr = new Console().getDataSource() + "\\teams";
	
	public ArrayList<TBasicInfoPO> teamBasic() {
		ArrayList<TBasicInfoPO> teams = new ArrayList<TBasicInfoPO>();
		
		/**������ӵĻ�����Ϣ*/
		ArrayList<String> data = new ArrayList<String>(ReadTeam.readTBasicInfo(basicInfoAddr));
		teams = getAllTeams(data);
		
		return teams;
	}

	/**
	 * ������ӵĻ�����Ϣ�����������ӱ�־��
	 */
	private static ArrayList<TBasicInfoPO> getAllTeams(ArrayList<String> data) {
		ArrayList<TBasicInfoPO> teams = new ArrayList<TBasicInfoPO>();
		for(String line : data){
			TBasicInfoPO team = new TBasicInfoPO();
			
			String[] info = line.split("�U")[1].split("��");
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

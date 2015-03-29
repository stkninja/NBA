package data.deal;

import java.util.ArrayList;

import po.TBasicInfoPO;
import data.readOriFiles.ReadTBasicInfo;

public class DealTBasicInfo {
	/**��ӻ�����Ϣ*/
	private static ArrayList<TBasicInfoPO> tBasicInfo = new ArrayList<TBasicInfoPO>();
	/**��ӻ�����Ϣ��ַ*/
	private static final String basicInfoAddr = "data\\teams\\teams";
	/**��ӱ�־��ַ*/
	private static final String teamLogoAddr = "data\\teams";
	
	public ArrayList<TBasicInfoPO> dealTBasicInfo() {
		/**������ӵĻ�����Ϣ*/
		ArrayList<String> data = new ArrayList<String>(ReadTBasicInfo.readTBasicInfo(basicInfoAddr));
		initTeamsBasicInfo(data);
		
		return tBasicInfo;
	}

	/**
	 * ������ӵĻ�����Ϣ�����������ӱ�־��
	 */
	private static void initTeamsBasicInfo(ArrayList<String> data) {
		for(String line : data){
			TBasicInfoPO tBasicInfoPO = new TBasicInfoPO();
			
			String[] info = line.split("�U")[1].split("��");
			tBasicInfoPO.setTeamLogo(ReadTBasicInfo.readLogo(teamLogoAddr + "\\" + info[1].trim() + ".svg"));
			tBasicInfoPO.setFullName(info[0].trim());
			tBasicInfoPO.setAbbName(info[1].trim());
			tBasicInfoPO.setLocation(info[2].trim());
			tBasicInfoPO.setCompetionArea(info[3].trim());
			tBasicInfoPO.setSubArea(info[4].trim());
			tBasicInfoPO.setHomeGround(info[5].trim());
			tBasicInfoPO.setSetupTime(info[6].trim());
			
			tBasicInfo.add(tBasicInfoPO);
		}
	}
}

package data.deal;

import java.util.ArrayList;

import po.TBasicInfoPO;
import data.readOriFiles.ReadTBasicInfo;

public class DealTBasicInfo {
	/**球队基本信息*/
	private static ArrayList<TBasicInfoPO> tBasicInfo = new ArrayList<TBasicInfoPO>();
	/**球队基本信息地址*/
	private static final String basicInfoAddr = "data\\teams\\teams";
	/**球队标志地址*/
	private static final String teamLogoAddr = "data\\teams";
	
	public ArrayList<TBasicInfoPO> dealTBasicInfo() {
		/**整理球队的基本信息*/
		ArrayList<String> data = new ArrayList<String>(ReadTBasicInfo.readTBasicInfo(basicInfoAddr));
		initTeamsBasicInfo(data);
		
		return tBasicInfo;
	}

	/**
	 * 整理球队的基本信息，如队名，球队标志等
	 */
	private static void initTeamsBasicInfo(ArrayList<String> data) {
		for(String line : data){
			TBasicInfoPO tBasicInfoPO = new TBasicInfoPO();
			
			String[] info = line.split("║")[1].split("│");
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

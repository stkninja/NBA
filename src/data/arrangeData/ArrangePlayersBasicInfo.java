package data.arrangeData;

import java.io.File;
import java.util.ArrayList;

import data.readOriginFiles.ReadPlayerBasicInfo;
import po.PlayerBasicInfoPO;

/**
 * 用于对比赛数据整理
 * 统计得到球员信息
 */
public class ArrangePlayersBasicInfo {

	private static ArrayList<PlayerBasicInfoPO> playerBasicInfo = new ArrayList<PlayerBasicInfoPO>();
	/**球员全身照地址*/
	private final static String actRootPath= "D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\players\\action";
	/**球员头像地址*/
	private final static String portraitRootPath= "D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\players\\portrait";
	/**球员信息地址*/
	private final static String infoRootPath= "D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\players\\info";
	
	public static ArrayList<PlayerBasicInfoPO> arrangePlayersBasicInfo() {
		/**遍历文件夹下所有文件*/
		File f = new File(infoRootPath);
		String[] list = null;
		if(f.isDirectory()){
			list = f.list();
		}
		
		initPlayerBasicInfo(list);
		
		return playerBasicInfo;
	}
	
	private static void initPlayerBasicInfo(String[] list){		
		for(int i = 0; i < list.length; i++){
			PlayerBasicInfoPO basicInfoPO = new PlayerBasicInfoPO();
			
			ArrayList<String> data = new ArrayList<String>(ReadPlayerBasicInfo.readPlayerBasicInfo(infoRootPath + "\\" + list[i]));
			//TODO
			basicInfoPO.setAction(ReadPlayerBasicInfo.getPlayerAction(actRootPath + "\\" + list[i] + ".png"));
			basicInfoPO.setPortrait(ReadPlayerBasicInfo.getPlayerPortrait(portraitRootPath + "\\" + list[i] + ".png"));
			basicInfoPO.setName(data.get(0).split("║")[1].split("│")[1].trim());
			basicInfoPO.setPosition(data.get(2).split("║")[1].split("│")[1].trim());
//			basicInfoPO.setSubArea(subArea);
//			basicInfoPO.setTeam(team);
			basicInfoPO.setAge(data.get(6).split("║")[1].split("│")[1].trim());
			basicInfoPO.setBirth(data.get(5).split("║")[1].split("│")[1].trim());
			basicInfoPO.setExp(data.get(7).split("║")[1].split("│")[1].trim());
			basicInfoPO.setHeight(data.get(3).split("║")[1].split("│")[1].trim());
			basicInfoPO.setNumber(data.get(1).split("║")[1].split("│")[1].trim());
			basicInfoPO.setSchool(data.get(8).split("║")[1].split("│")[1].trim());
			basicInfoPO.setWeight(data.get(4).split("║")[1].split("│")[1].trim());
//			basicInfoPO.setGameplay(gameplay);
//			basicInfoPO.setGamestart(gamestart);
			 
			playerBasicInfo.add(basicInfoPO);
		}
	}
}

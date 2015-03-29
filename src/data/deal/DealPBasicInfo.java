package data.deal;

import java.io.File;
import java.util.ArrayList;

import po.PBasicInfoPO;
import data.readOriFiles.ReadPBasicInfo;

public class DealPBasicInfo {

	private static ArrayList<PBasicInfoPO> pBasicInfo = new ArrayList<PBasicInfoPO>();
	/**球员全身照地址*/
	private final static String actRootPath= "data\\players\\action";
	/**球员头像地址*/
	private final static String portraitRootPath= "data\\players\\portrait";
	/**球员信息地址*/
	private final static String infoRootPath= "data\\players\\info";
	
	public ArrayList<PBasicInfoPO> dealPBasicInfo() {
		/**遍历文件夹下所有文件*/
		File f = new File(infoRootPath);
		String[] list = null;
		if(f.isDirectory()){
			list = f.list();
		}
		
		initPlayerBasicInfo(list);
		return pBasicInfo;
	}
	
	private static void initPlayerBasicInfo(String[] list){		
		for(int i = 0; i < list.length; i++){
			PBasicInfoPO basicInfoPO = new PBasicInfoPO();
			
			ArrayList<String> data = new ArrayList<String>(ReadPBasicInfo.readPBasicInfo(infoRootPath + "\\" + list[i]));
		
			basicInfoPO.setAction(ReadPBasicInfo.getPAction(actRootPath + "\\" + list[i] + ".png"));
			basicInfoPO.setPortrait(ReadPBasicInfo.getPPortrait(portraitRootPath + "\\" + list[i] + ".png"));
			basicInfoPO.setName(data.get(0).split("║")[1].split("│")[1].trim());
			basicInfoPO.setPosition(data.get(2).split("║")[1].split("│")[1].trim());
			basicInfoPO.setAge(data.get(6).split("║")[1].split("│")[1].trim());
			basicInfoPO.setBirth(data.get(5).split("║")[1].split("│")[1].trim());
			basicInfoPO.setExp(data.get(7).split("║")[1].split("│")[1].trim());
			basicInfoPO.setHeight(data.get(3).split("║")[1].split("│")[1].trim());
			basicInfoPO.setNumber(data.get(1).split("║")[1].split("│")[1].trim());
			basicInfoPO.setSchool(data.get(8).split("║")[1].split("│")[1].trim());
			basicInfoPO.setWeight(data.get(4).split("║")[1].split("│")[1].trim());
			 
			pBasicInfo.add(basicInfoPO);
		}
	}
}

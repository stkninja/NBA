package data.arrangeData;

import java.io.File;
import java.util.ArrayList;

import data.readOriginFiles.ReadPlayerBasicInfo;
import po.PlayerBasicInfoPO;

/**
 * ���ڶԱ�����������
 * ͳ�Ƶõ���Ա��Ϣ
 */
public class ArrangePlayersBasicInfo {

	private static ArrayList<PlayerBasicInfoPO> playerBasicInfo = new ArrayList<PlayerBasicInfoPO>();
	/**��Աȫ���յ�ַ*/
	private final static String actRootPath= "data\\players\\action";
	/**��Աͷ���ַ*/
	private final static String portraitRootPath= "data\\players\\portrait";
	/**��Ա��Ϣ��ַ*/
	private final static String infoRootPath= "data\\players\\info";
	
	public static ArrayList<PlayerBasicInfoPO> arrangePlayersBasicInfo() {
		/**�����ļ����������ļ�*/
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
		
			basicInfoPO.setAction(ReadPlayerBasicInfo.getPlayerAction(actRootPath + "\\" + list[i] + ".png"));
			basicInfoPO.setPortrait(ReadPlayerBasicInfo.getPlayerPortrait(portraitRootPath + "\\" + list[i] + ".png"));
			basicInfoPO.setName(data.get(0).split("�U")[1].split("��")[1].trim());
			basicInfoPO.setPosition(data.get(2).split("�U")[1].split("��")[1].trim());
			basicInfoPO.setAge(data.get(6).split("�U")[1].split("��")[1].trim());
			basicInfoPO.setBirth(data.get(5).split("�U")[1].split("��")[1].trim());
			basicInfoPO.setExp(data.get(7).split("�U")[1].split("��")[1].trim());
			basicInfoPO.setHeight(data.get(3).split("�U")[1].split("��")[1].trim());
			basicInfoPO.setNumber(data.get(1).split("�U")[1].split("��")[1].trim());
			basicInfoPO.setSchool(data.get(8).split("�U")[1].split("��")[1].trim());
			basicInfoPO.setWeight(data.get(4).split("�U")[1].split("��")[1].trim());
			 
			playerBasicInfo.add(basicInfoPO);
		}
	}
}

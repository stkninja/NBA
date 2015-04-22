package data.savePOs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import po.MatchPO;
import data.deal.DealMBasicInfo;

public class SaveMBasicPO {

	private final static String rootPath = "data\\ͳ��������������";
	private static ArrayList<MatchPO> seasonMatches;
	
	public static void saveAll(String season){
		/**�������ļ���*/
		File f = new File(rootPath);
		if(!f.exists())
			f.mkdir();
	
		/**����*/
		DealMBasicInfo dealMBasicInfo = new DealMBasicInfo();
		seasonMatches = dealMBasicInfo.dealMBasicInfo(season);
		
		/**�ж��ļ��Ƿ��Ѵ���*/
		File f2 = new File(rootPath + "\\" + season);
		if(f2.exists())
			f2.delete();
		/**���汾��������*/
		saveMBasicInfo(season);

		/**���汾���� �����Ա*/
		SaveTSeasonDataPO.saveTSeasonDataPO(season);
		SavePSeasonDataPO.savePSeasonDataPO(season);

	}
	
	
	private static void saveMBasicInfo(String season) {
		ObjectOutputStream oos = null;
		
		try {

			oos = new ObjectOutputStream(new FileOutputStream(rootPath + "\\" + season));	
			oos.writeObject(seasonMatches);

		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
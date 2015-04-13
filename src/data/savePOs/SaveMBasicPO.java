package data.savePOs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import po.MatchPO;
import data.deal.DealMBasicInfo;
import data.readPOs.ReadMBasicPO;

public class SaveMBasicPO {

	private final static String rootPath = "data\\ͳ��������������";
	private static ArrayList<ArrayList<MatchPO>> seasonMatches;
	
	public static void saveAll(){
		/**�������ļ���*/
		File f = new File(rootPath);
		if(!f.exists())
			f.mkdir();
	
		/**����*/
		DealMBasicInfo dealMBasicInfo = new DealMBasicInfo();
		seasonMatches = dealMBasicInfo.dealMBasicInfo();
		
		for(int i = 0; i < seasonMatches.size(); i++){
			/**�ж��ļ��Ƿ��Ѵ���*/
			String season = seasonMatches.get(i).get(0).getSeason();
			File f2 = new File(rootPath + "\\" + season);
			if(f2.exists())
				seasonMatches.get(i).addAll(ReadMBasicPO.readMBasicPO(season));
			/**���汾��������*/
			saveMBasicInfo(season, i);
			
			/**���汾���� �����Ա*/
			SaveTSeasonDataPO.saveTSeasonDataPO(season);
			SavePSeasonDataPO.savePSeasonDataPO(season);
		}
	}
	
	
	private static void saveMBasicInfo(String season, int index) {
		ObjectOutputStream oos = null;
		
		try {
				oos = new ObjectOutputStream(new FileOutputStream(rootPath + "\\" + season));	
				oos.writeObject(seasonMatches.get(index));
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
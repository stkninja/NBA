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

	private final static String rootPath = "data\\ͳ�Ʊ�����������";
	
	public static void saveAll(){
		saveMBasicInfo();
	}
	
	
	private static void saveMBasicInfo() {
		ObjectOutputStream oos = null;
		
		try {
			DealMBasicInfo dealMBasicInfo = new DealMBasicInfo();
			ArrayList<ArrayList<MatchPO>> seasonMatches = dealMBasicInfo.dealMBasicInfo();
			String season;
			
			for(int i = 0; i < seasonMatches.size(); i++){
				season = seasonMatches.get(i).get(0).getSeason();
				File f = new File(rootPath + "\\" + season);
				if(f.exists())
					seasonMatches.get(i).addAll(ReadMBasicPO.readMBasicPO(rootPath + "\\" + season));
					
				oos = new ObjectOutputStream(new FileOutputStream(rootPath + "\\" + season));	
				
				/**���汾���� �����Ա*/
				SaveTSeasonDataPO.saveTSeasonDataPO(season);
				SavePSeasonDataPO.savePSeasonDataPO(season);
			}
			
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

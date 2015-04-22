package data.savePOs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import po.MatchPO;
import data.deal.DealMBasicInfo;

public class SaveMBasicPO {

	private final static String rootPath = "data\\统计赛季比赛数据";
	private static ArrayList<MatchPO> seasonMatches;
	
	public static void saveAll(String season){
		/**创建根文件夹*/
		File f = new File(rootPath);
		if(!f.exists())
			f.mkdir();
	
		/**保存*/
		DealMBasicInfo dealMBasicInfo = new DealMBasicInfo();
		seasonMatches = dealMBasicInfo.dealMBasicInfo(season);
		
		/**判断文件是否已存在*/
		File f2 = new File(rootPath + "\\" + season);
		if(f2.exists())
			f2.delete();
		/**保存本赛季比赛*/
		saveMBasicInfo(season);

		/**保存本赛季 球队球员*/
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
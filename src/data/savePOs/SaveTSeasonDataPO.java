package data.savePOs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.deal.DealTSeasonData;

public class SaveTSeasonDataPO {

	public static void saveTSeasonDataPO(String season) {
		ObjectOutputStream oos = null;
		try {
			String rootPath = "data\\统计球队比赛数据";
			File f = new File(rootPath);
			if(!f.exists())
				f.mkdir();
			
			String path = rootPath + "\\" + season;
			File f2 = new File(path);
			if(f2.exists())
				f2.delete();
			
			oos = new ObjectOutputStream(new FileOutputStream(path));
			DealTSeasonData dealTSeasonData = new DealTSeasonData();
			oos.writeObject(dealTSeasonData.dealTSeasonData(season));
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

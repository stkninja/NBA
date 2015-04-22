package data.savePOs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.deal.DealPSeasonData;

public class SavePSeasonDataPO {	
	public static void savePSeasonDataPO(String season) {
		ObjectOutputStream oos = null;
		try {
			String rootPath = "data\\统计球员比赛数据";
			File f = new File(rootPath);
			if(!f.exists())
				f.mkdir();
			
			String path = rootPath + "\\" + season;
			File f2 = new File(rootPath + "\\" + season);
			if(f2.exists())
				f2.delete();
			
			oos = new ObjectOutputStream(new FileOutputStream(path));	
			DealPSeasonData dealPSeasonData = new DealPSeasonData();
			oos.writeObject(dealPSeasonData.dealPSeasonData(season));
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

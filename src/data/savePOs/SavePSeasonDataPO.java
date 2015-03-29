package data.savePOs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.deal.DealPSeasonData;

public class SavePSeasonDataPO {	
	public static void savePSeasonDataPO(String season) {
		ObjectOutputStream oos = null;
		try {
			String path = "data\\统计球员比赛数据" + "\\" + season;
			
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

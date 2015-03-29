package data.savePOs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.deal.DealTSeasonData;

public class SaveTSeasonDataPO {

	public static void saveTSeasonDataPO(String season) {
		ObjectOutputStream oos = null;
		try {
			String path = "data\\统计球队比赛数据" + "\\" + season;
			
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

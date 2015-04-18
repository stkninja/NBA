package data.savePOs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.deal.DealPAllMatchData;

public class SavePAllMatchDataPO {
	public static void savePAllMatchDataPO(){
		ObjectOutputStream oos = null;
		try {
			String path = "data\\统计球员历年数据";
			
			oos = new ObjectOutputStream(new FileOutputStream(path));
			DealPAllMatchData dealPAllMatchData = new DealPAllMatchData();
			oos.writeObject(dealPAllMatchData.dealPAllMatchData("all"));
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

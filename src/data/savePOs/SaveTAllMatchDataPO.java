package data.savePOs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.deal.DealTAllMatchData;

public class SaveTAllMatchDataPO {
	public static void saveTAllMatchDataPO(){
		ObjectOutputStream oos = null;
		try {
			String path = "data\\ͳ�������������";
			
			oos = new ObjectOutputStream(new FileOutputStream(path));
			DealTAllMatchData dealTAllMatchData = new DealTAllMatchData();
			oos.writeObject(dealTAllMatchData.dealTAllMatchData("all"));
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

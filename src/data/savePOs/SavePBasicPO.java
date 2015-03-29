package data.savePOs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.deal.DealPBasicInfo;

public class SavePBasicPO {

	private final static String path = "data\\统计球员基本信息";
	
	public static void savePBasicPO() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(path));
			
			DealPBasicInfo dealPBasicInfo = new DealPBasicInfo();
			oos.writeObject(dealPBasicInfo.dealPBasicInfo());
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

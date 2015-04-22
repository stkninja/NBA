package data.savePOs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.deal.DealPBasicInfo;

public class SavePBasicPO {

	private final static String path = "data\\统计球员基本信息";
	
	public static void savePBasicPO() {
		ObjectOutputStream oos = null;
		try {
			File f = new File(path);
			if(f.exists())
				f.delete();
			
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

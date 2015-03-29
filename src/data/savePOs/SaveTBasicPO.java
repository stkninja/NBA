package data.savePOs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.deal.DealTBasicInfo;

public class SaveTBasicPO {

	private final static String path = "data\\统计球队基本信息";
	
	public static void saveTBasicInfo(){
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(path));
			
			DealTBasicInfo dealTBasicInfo = new DealTBasicInfo();
			oos.writeObject(dealTBasicInfo.dealTBasicInfo());
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

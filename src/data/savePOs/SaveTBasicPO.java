package data.savePOs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.deal.DealTBasicInfo;

public class SaveTBasicPO {

	private final static String path = "data\\ͳ����ӻ�����Ϣ";
	
	public static void saveTBasicInfo(){
		ObjectOutputStream oos = null;
		try {
			File f = new File(path);
			if(f.exists())
				f.delete();
			
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

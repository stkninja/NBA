package data.readPOs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import po.PBasicInfoPO;

public class ReadPBasicPO {

	private final static String path = "data\\统计球员基本信息";
	
	@SuppressWarnings("unchecked")
	public static ArrayList<PBasicInfoPO> readPBasicPO() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(path));
			return (ArrayList<PBasicInfoPO>)ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<PBasicInfoPO>();
	}
}

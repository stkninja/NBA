package data.readPOs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import po.PSeasonDataPO;

public class ReadPSeasonDataPO {

	@SuppressWarnings("unchecked")
	public static ArrayList<PSeasonDataPO> readPSeasonDataPO(String season) {
		ObjectInputStream ois = null;
		try {
			String path = "data\\ͳ����Ա��������" + "\\" + season;
			
			ois = new ObjectInputStream(new FileInputStream(path));
			return (ArrayList<PSeasonDataPO>)ois.readObject();
			
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
		
		return new ArrayList<PSeasonDataPO>();
	}
}

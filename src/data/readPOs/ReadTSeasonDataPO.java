package data.readPOs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import po.TSeasonDataPO;

public class ReadTSeasonDataPO {

	@SuppressWarnings("unchecked")
	public static ArrayList<TSeasonDataPO> readTSeasonDataPO(String season) {
		ObjectInputStream ois = null;
		try {
			String path = "data\\统计球队比赛数据" + "\\" + season;
			
			ois = new ObjectInputStream(new FileInputStream(path));
			return (ArrayList<TSeasonDataPO>)ois.readObject();
			
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
		
		return new ArrayList<TSeasonDataPO>();
	}
}

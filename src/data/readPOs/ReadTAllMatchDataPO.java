package data.readPOs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import po.TSeasonDataPO;

public class ReadTAllMatchDataPO {

	@SuppressWarnings("unchecked")
	public static ArrayList<TSeasonDataPO> readTAllMatchDataPO() {
		ObjectInputStream ois = null;
		try {
			String path = "data\\统计球队历年数据";
			File f = new File(path);
			
			if(f.exists()){
				ois = new ObjectInputStream(new FileInputStream(path));
				return (ArrayList<TSeasonDataPO>)ois.readObject();
			}
			return new ArrayList<TSeasonDataPO>();
			
		} catch (IOException e) {
			return new ArrayList<TSeasonDataPO>();
		} catch (ClassNotFoundException e) {
			return new ArrayList<TSeasonDataPO>();
		} finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}

package data.readPOs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import po.PSeasonDataPO;

public class ReadPAllMatchDataPO {

	@SuppressWarnings("unchecked")
	public static ArrayList<PSeasonDataPO> readPAllMatchDataPO() {
		ObjectInputStream ois = null;
		try {
			String path = "data\\统计球员历年数据";
			File f = new File(path);
			
			if(f.exists()){
				ois = new ObjectInputStream(new FileInputStream(path));
				return (ArrayList<PSeasonDataPO>)ois.readObject();
			}
			return new ArrayList<PSeasonDataPO>();
			
		} catch (IOException e) {
			return new ArrayList<PSeasonDataPO>();
		} catch (ClassNotFoundException e) {
			return new ArrayList<PSeasonDataPO>();
		} finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

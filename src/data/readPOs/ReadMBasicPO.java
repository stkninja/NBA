package data.readPOs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import po.MatchPO;

public class ReadMBasicPO {

	@SuppressWarnings("unchecked")
	public static ArrayList<MatchPO> readMBasicPO(String season) {
		ObjectInputStream ois = null;
		
		try {
			String path = "data\\统计赛季比赛数据" + "\\" + season;
			File f = new File(path);
			
			if(f.exists()){
				ois = new ObjectInputStream(new FileInputStream(path));
				return (ArrayList<MatchPO>)ois.readObject();
			}
			return new ArrayList<MatchPO>();
			
		} catch (IOException e) {
			return new ArrayList<MatchPO>();
		} catch (ClassNotFoundException e) {
			return new ArrayList<MatchPO>();
		} finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}

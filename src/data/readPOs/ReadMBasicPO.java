package data.readPOs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import po.MatchPO;

public class ReadMBasicPO {

	@SuppressWarnings("unchecked")
	public static ArrayList<MatchPO> readMBasicPO(String season) {
		String path = "data\\统计赛季比赛数据" + "\\" + season;

		ObjectInputStream ois = null;
		try {
			
			ois = new ObjectInputStream(new FileInputStream(path));
			return (ArrayList<MatchPO>)ois.readObject();
			
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
		
		return new ArrayList<MatchPO>();
	}

}

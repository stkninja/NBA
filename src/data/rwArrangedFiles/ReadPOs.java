package data.rwArrangedFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import po.*;

/**
 * @author pc
 * 读取整理之后的数据
 */
@SuppressWarnings("unchecked")
public class ReadPOs {

	private final static String path1 = "data\\统计球队基本信息";
	private final static String path2 = "data\\统计球员基本信息";
	private final static String path3 = "data\\统计比赛数据";

	public static ArrayList<PlayerBasicInfoPO> readPlayerBasicInfoPO(){
		ObjectInputStream ois = null;
		try {
			
			ois = new ObjectInputStream(new FileInputStream(path2));
			return (ArrayList<PlayerBasicInfoPO>)ois.readObject();
			
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
		
		return new ArrayList<PlayerBasicInfoPO>();
	}
	
	public static ArrayList<TeamBasicInfoPO> readTeamBasicInfoPO(){
		ObjectInputStream ois = null;
		try {
			
			ois = new ObjectInputStream(new FileInputStream(path1));
			return (ArrayList<TeamBasicInfoPO>)ois.readObject();
			
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
		
		return new ArrayList<TeamBasicInfoPO>();
	}

	public static ArrayList<MatchPO> readMatchPO(){
		ObjectInputStream ois = null;
		try {
			
			ois = new ObjectInputStream(new FileInputStream(path3));
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

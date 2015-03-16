package data.rwArrangedFiles;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.arrangeData.ArrangeMatches;
import data.arrangeData.ArrangePlayersBasicInfo;
import data.arrangeData.ArrangeTeamsBasicInfo;
/**
 * @author pc
 * 在本地存储统计之后的数据以供访问
 */
public class WritePOs {
	
	private final static String path1 = "D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\球队基本信息";
	private final static String path2 = "D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\球员基本信息";
	private final static String path3 = "D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\比赛数据";
	
	public static void writePOs(){
		WritePOs.writeTeamsPOs();
		WritePOs.writePlayersPOs();
		WritePOs.writeMatchesPOs();
	}
	
	private static void writePlayersPOs(){
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(path2));
			oos.writeObject(ArrangePlayersBasicInfo.arrangePlayersBasicInfo());
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
	
	private static void writeTeamsPOs(){
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(path1));
			oos.writeObject(ArrangeTeamsBasicInfo.arrangeTeamsBasicInfo());
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
	
	private static void writeMatchesPOs(){
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(path3));
			oos.writeObject(ArrangeMatches.arrangeMatches());
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
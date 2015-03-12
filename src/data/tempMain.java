package data;

import java.util.ArrayList;


public class tempMain {
	public static void main(String[] args) {
		ArrayList<String> data = ReadTeam.readTeamInfo("D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\teams\\teams");
		new tempFrame();
	}
}

package datalogic;

import java.util.ArrayList;

public class tempMain {
	public static void main(String[] args) {
		ArrayList<String> data = ReadTeam.readTeamInfo("D:\\��Ժ�γ�\\��III\\����\\CSEIII data\\����һ����\\teams\\teams");
		System.out.println(data.get(0));
	}
}

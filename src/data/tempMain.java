package data;

import java.util.ArrayList;

import po.MatchPO;
import po.PlayerBasicInfoPO;
import po.TeamBasicInfoPO;
import data.rwArrangedFiles.ReadPOs;
import data.rwArrangedFiles.WritePOs;



public class tempMain {
	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		
		WritePOs.writePOs();
		ArrayList<TeamBasicInfoPO> data1 = ReadPOs.readTeamBasicInfoPO();
		ArrayList<PlayerBasicInfoPO> data2 = ReadPOs.readPlayerBasicInfoPO();
		ArrayList<MatchPO> data3 = ReadPOs.readMatchPO();
		
		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
		System.out.println(data1.get(0).getFullName());
		System.out.println(data1.get(0).getAbbName());
		System.out.println(data1.get(0).getTeamLogo());
		System.out.println(data2.get(0).getName());
		System.out.println(data3.get(0).getSeason());
	}
}

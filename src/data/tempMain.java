package data;

import data.rwArrangedFiles.WritePOs;



public class tempMain {
	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		
		WritePOs.writePOs();
		GetPlayersInfo getPlayersInfo = new GetPlayersInfo();
		
		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
		System.out.println(getPlayersInfo.getPlayerGameStart("Reggie Evans", "13-14"));
		System.out.println(getPlayersInfo.getPlayerGamePlay("Reggie Evans", "13-14"));
	}
}

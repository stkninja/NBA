package data.pre;

import java.util.ArrayList;

import po.PSeasonDataPO;
import data.GetPlayerInfo;

public class Predo {
	static String[] season_list = {"05-06", "06-07", "07-08", "08-09", "09-10",
								  	"10-11", "11-12", "12-13", "13-14", "14-15"};

	static ArrayList<ArrayList<PSeasonDataPO>> psds_list = new  ArrayList<ArrayList<PSeasonDataPO>>();
	
	public static void predo() {
		GetPlayerInfo gpi = new GetPlayerInfo();
		
		for(int year = 2006; year <= 2015; year++){
			psds_list.add(gpi.getAllPSeasonData_2(season_list[year - 2006]));
		}
	}
	
	public static ArrayList<PSeasonDataPO> getAllPSeasonDataAt(String season){
		int index = Integer.valueOf((season.split("-")[1])) - 6;
		return psds_list.get(index);
	}
}

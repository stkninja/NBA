package data.pre;

import java.util.ArrayList;

import po.PSeasonDataPO;
import po.TSeasonDataPO;
import data.GetPlayerInfo;
import data.GetTeamInfo;

public class Predo {
	static String[] season_list = {"05-06", "06-07", "07-08", "08-09", "09-10",
								  	"10-11", "11-12", "12-13", "13-14", "14-15"};

	static ArrayList<ArrayList<PSeasonDataPO>> psds_list = new ArrayList<ArrayList<PSeasonDataPO>>();
	static ArrayList<ArrayList<TSeasonDataPO>> tsds_list = new ArrayList<ArrayList<TSeasonDataPO>>();
	
	public static void predo() {
		GetPlayerInfo gpi = new GetPlayerInfo();
		GetTeamInfo gti = new GetTeamInfo();
		
		for(int year = 2006; year <= 2015; year++){
			psds_list.add(gpi.getAllPSeasonData_2(season_list[year - 2006]));
			System.out.println(season_list[year - 2006] + " AllPSeasonData done!");
			
			tsds_list.add(gti.getAllTSeasonData_2(season_list[year - 2006]));
			System.out.println(season_list[year - 2006] + " AllTSeasonData done!");
		}
	}
	
	public static ArrayList<PSeasonDataPO> getAllPSeasonDataAt(String season){
		int index = Integer.valueOf((season.split("-")[1])) - 6;
		return psds_list.get(index);
	}
	
	public static ArrayList<TSeasonDataPO> getAllTSeasonDataAt(String season){
		int index = Integer.valueOf((season.split("-")[1])) - 6;
		return tsds_list.get(index);
	}
	
	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		Predo.predo();
		long time2 = System.currentTimeMillis();
		System.out.println("-----overtime: -----" + (time2 - time1));
	}
}

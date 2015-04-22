package data;

import java.util.ArrayList;

import po.TBasicInfoPO;
import po.TSeasonDataPO;
import data.readPOs.ReadTAllMatchDataPO;
import data.readPOs.ReadTBasicPO;
import data.readPOs.ReadTSeasonDataPO;
import dataservice.TeamService;

public class GetTeamInfo implements TeamService{

	public GetTeamInfo() {
	}

	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season) {
		if(season.equals("all"))
			return ReadTAllMatchDataPO.readTAllMatchDataPO();
		
		return ReadTSeasonDataPO.readTSeasonDataPO(season);
	}

	public TBasicInfoPO getSingleTBasicInfo(String abbName) {
		if(abbName.equals("NOH"))
			abbName = "NOP";
		
		ArrayList<TBasicInfoPO> pos = ReadTBasicPO.readTBasicPO();
		
		for(TBasicInfoPO po : pos)
			if(po.getAbbName().equals(abbName))
				return po;
		
		return null;
	}

	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season) {
		if(abbName.equals("NOP") && season.compareTo("12-13") <= 0)
			abbName = "NOH";
		
		ArrayList<TSeasonDataPO> pos = ReadTSeasonDataPO.readTSeasonDataPO(season);
		for(TSeasonDataPO po :pos)
			if(po.getAbbName().equals(abbName))
				return po;
		
		return null;
	}
	
}

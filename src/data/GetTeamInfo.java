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
		ArrayList<TBasicInfoPO> pos = ReadTBasicPO.readTBasicPO();
		
		for(TBasicInfoPO po : pos)
			if(po.getAbbName().equals(abbName))
				return po;
		
		return new TBasicInfoPO();
	}

	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season) {
		ArrayList<TSeasonDataPO> pos = ReadTSeasonDataPO.readTSeasonDataPO(season);
		for(TSeasonDataPO po :pos)
			if(po.getAbbName().equals(abbName))
				return po;
		
		return new TSeasonDataPO();
	}
	
}

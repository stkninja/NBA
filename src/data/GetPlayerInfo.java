package data;

import java.util.ArrayList;

import po.PBasicInfoPO;
import po.PSeasonDataPO;
import data.readPOs.ReadPAllMatchDataPO;
import data.readPOs.ReadPBasicPO;
import data.readPOs.ReadPSeasonDataPO;
import dataservice.PlayerService;

public class GetPlayerInfo implements PlayerService{

	public GetPlayerInfo() {}


	public ArrayList<String> getAllPlayersName() {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<PBasicInfoPO> pos = ReadPBasicPO.readPBasicPO();
		
		for(PBasicInfoPO po : pos){
			names.add(po.getName());
		}
		return names;
	}
	
	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season) {
		if(season.equals("all"))
			return ReadPAllMatchDataPO.readPAllMatchDataPO();
		
		return ReadPSeasonDataPO.readPSeasonDataPO(season);
	}

	public PBasicInfoPO getSinglePBasicInfo(String name) {
		ArrayList<PBasicInfoPO> pos = ReadPBasicPO.readPBasicPO();
		
		for(PBasicInfoPO po : pos)
			if(po.getName().equals(name))
				return po;
		
		return null;
	}

	public PSeasonDataPO getOnePSeasonDataPO(String name, String season) {
		ArrayList<PSeasonDataPO> pos = ReadPSeasonDataPO.readPSeasonDataPO(season);
		for(PSeasonDataPO po :pos)
			if(po.getName().equals(name))
				return po;
		
		return null;
	}
	
}

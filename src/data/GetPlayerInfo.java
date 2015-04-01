package data;

import java.util.ArrayList;

import po.PBasicInfoPO;
import po.PSeasonDataPO;
import data.readPOs.ReadPBasicPO;
import data.readPOs.ReadPSeasonDataPO;
import dataservice.PlayerService;

public class GetPlayerInfo implements PlayerService{

	public GetPlayerInfo() {}

	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season) {
		return ReadPSeasonDataPO.readPSeasonDataPO(season);
	}

	public PBasicInfoPO getSinglePBasicInfo(String name) {
		ArrayList<PBasicInfoPO> pos = ReadPBasicPO.readPBasicPO();
		
		for(PBasicInfoPO po : pos)
			if(po.getName().equals(name))
				return po;
		
		return new PBasicInfoPO();
	}

	public PSeasonDataPO getOnePSeasonDataPO(String name, String season) {
		return null;
	}
}

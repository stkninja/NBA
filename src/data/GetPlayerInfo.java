package data;

import po.PBasicInfoPO;
import po.PSeasonDataPO;
import data.readOriFiles.ReadPBasicInfo;
import dataservice.PlayerService;

public class GetPlayerInfo implements PlayerService{

	public GetPlayerInfo() {}

	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season) {
		
	}

	public PBasicInfoPO getSinglePBasicInfo(String name) {
		return null;
	}
}

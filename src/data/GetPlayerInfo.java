package data;

import po.PBasicInfoPO;
import po.PSeasonDataPO;
import data.readOriFiles.ReadPBasicInfo;
import dataservice.PlayerService;

public class GetPlayerInfo implements PlayerService{

	public GetPlayerInfo() {}

	public PSeasonDataPO getAllPSeasonData(String season) {
//		return ReadPBasicInfo.readPBasicInfo(addr)
	}

	public PBasicInfoPO getSinglePBasicInfo(String name) {
		return null;
	}
}

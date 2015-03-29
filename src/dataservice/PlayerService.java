package dataservice;

import po.PBasicInfoPO;
import po.PSeasonDataPO;


public interface PlayerService {
	public PSeasonDataPO getAllPSeasonData(String season);
	public PBasicInfoPO getAllPBasicInfo();
}

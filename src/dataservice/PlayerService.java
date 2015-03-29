package dataservice;

import po.PBasicInfoPO;
import po.PSeasonDataPO;


public interface PlayerService {
	public ArratList<PSeasonDataPO> getAllPSeasonData(String season);
	public PBasicInfoPO getSinglePBasicInfo(String name);
}

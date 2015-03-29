package dataservice;

import po.TBasicInfoPO;
import po.TSeasonDataPO;


public interface TeamService {	
	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season);
	public TBasicInfoPO getSingleTBasicInfo(String abbName);
}

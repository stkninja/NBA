package dataservice;

import java.util.ArrayList;

import po.TBasicInfoPO;
import po.TSeasonDataPO;


public interface TeamService {	
	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season);
	public TBasicInfoPO getSingleTBasicInfo(String abbName);
	//获得某个球员的某赛季的比赛数据
	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season);
}

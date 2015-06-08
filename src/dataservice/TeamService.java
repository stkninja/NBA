package dataservice;

import java.util.ArrayList;

import po.TBasicInfoPO;
import po.TSeasonDataPO;


public interface TeamService {	
	//某球队基本信息
	public TBasicInfoPO getSingleTBasicInfo(String abbName);
	//获得某球队的某赛季的比赛数据
	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season);
	//获得所有球队赛季比赛数据
	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season);
}

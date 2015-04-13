package dataservice;

import java.util.ArrayList;

import po.PBasicInfoPO;
import po.PSeasonDataPO;


public interface PlayerService {
	//获得所有球员名字
	public ArrayList<String> getAllPlayersName();
	
	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season);
	public PBasicInfoPO getSinglePBasicInfo(String name);
	//获得某个球员的某赛季的比赛数据
	public PSeasonDataPO getOnePSeasonDataPO(String name, String season);
}

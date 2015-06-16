package dataservice;

import java.util.ArrayList;

import po.PBasicInfoPO;
import po.PSeasonDataPO;


public interface PlayerService {
	//获得所有球员名字
	public ArrayList<String> getAllPlayersName();
	//获得某个球员基本数据
	public PBasicInfoPO getSinglePBasicInfo(String name);
	public ArrayList<PBasicInfoPO> getAllPlayersPBasicInfo();
	//获得某个球员的某赛季的比赛数据
	public PSeasonDataPO getOnePSeasonDataPO(String name, String season);
	//获得所有球员赛季比赛数据
	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season);
	public ArrayList<PSeasonDataPO> getAllPSeasonData_2(String season);

}

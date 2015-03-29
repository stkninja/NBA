package dataservice;

import java.util.ArrayList;

import po.PBasicInfoPO;
import po.PSeasonDataPO;


public interface PlayerService {
	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season);
	public PBasicInfoPO getSinglePBasicInfo(String name);
}

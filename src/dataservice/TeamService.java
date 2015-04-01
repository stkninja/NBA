package dataservice;

import java.util.ArrayList;

import po.TBasicInfoPO;
import po.TSeasonDataPO;


public interface TeamService {	
	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season);
	public TBasicInfoPO getSingleTBasicInfo(String abbName);
	//���ĳ����Ա��ĳ�����ı�������
	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season);
}

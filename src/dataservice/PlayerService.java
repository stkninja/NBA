package dataservice;

import java.util.ArrayList;

import po.PBasicInfoPO;
import po.PSeasonDataPO;


public interface PlayerService {
	//���������Ա����
	public ArrayList<String> getAllPlayersName();
	
	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season);
	public PBasicInfoPO getSinglePBasicInfo(String name);
	//���ĳ����Ա��ĳ�����ı�������
	public PSeasonDataPO getOnePSeasonDataPO(String name, String season);
}

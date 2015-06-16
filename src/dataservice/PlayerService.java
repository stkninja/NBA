package dataservice;

import java.util.ArrayList;

import po.PBasicInfoPO;
import po.PSeasonDataPO;


public interface PlayerService {
	//���������Ա����
	public ArrayList<String> getAllPlayersName();
	//���ĳ����Ա��������
	public PBasicInfoPO getSinglePBasicInfo(String name);
	public ArrayList<PBasicInfoPO> getAllPlayersPBasicInfo();
	//���ĳ����Ա��ĳ�����ı�������
	public PSeasonDataPO getOnePSeasonDataPO(String name, String season);
	//���������Ա������������
	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season);
	public ArrayList<PSeasonDataPO> getAllPSeasonData_2(String season);

}

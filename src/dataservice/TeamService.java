package dataservice;

import java.util.ArrayList;

import po.TBasicInfoPO;
import po.TSeasonDataPO;


public interface TeamService {	
	//ĳ��ӻ�����Ϣ
	public TBasicInfoPO getSingleTBasicInfo(String abbName);
	//���ĳ��ӵ�ĳ�����ı�������
	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season);
	//����������������������
	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season);
}

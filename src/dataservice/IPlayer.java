package dataservice;

import java.util.ArrayList;
import po.PlayerBasicInfoPO;

public interface IPlayer {
	/**���ָ����Ա������Ϣ*/
	public PlayerBasicInfoPO getSinglePlayerBasicInfo(String name);
	/**���������Ա������Ϣ*/
	public ArrayList<PlayerBasicInfoPO> getAllPlayersBasicInfo();
	/**���������Ա����*/
	public ArrayList<String> getAllPlayersName();
	/**�����Ա�ȷ�����*/
	public double getPlayerGameStart(String name, String season);
	/**�����Ա��������*/	
	public double getPlayerGamePlay(String name, String season);
}

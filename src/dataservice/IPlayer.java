package dataservice;

import java.util.ArrayList;
import po.PlayerBasicInfoPO;

public interface IPlayer {
	/**���ָ����Ա������Ϣ*/
	public PlayerBasicInfoPO getSinglePlayerBasicInfo(String name);
	/**���������Ա������Ϣ*/
	public ArrayList<PlayerBasicInfoPO> getAllPlayersBasicInfo();
}

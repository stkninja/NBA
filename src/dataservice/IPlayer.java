package dataservice;

import java.util.ArrayList;
import po.PlayerBasicInfoPO;

public interface IPlayer {
	/**���ָ����Ա��Ϣ*/
	public PlayerBasicInfoPO getSinglePlayerInfo(String name);
	/**���ָ����Ա��Ϣ*/
	public ArrayList<PlayerBasicInfoPO> getAllPlayersInfo();
}

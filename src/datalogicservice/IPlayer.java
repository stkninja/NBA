package datalogicservice;

import java.util.ArrayList;
import po.PlayerPO;

public interface IPlayer {
	/**���ָ����Ա��Ϣ*/
	public PlayerPO getSinglePlayerInfo(String name);
	/**���ָ����Ա��Ϣ*/
	public ArrayList<PlayerPO> getAllPlayersInfo();
}

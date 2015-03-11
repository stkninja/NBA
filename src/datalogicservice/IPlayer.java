package datalogicservice;

import java.util.ArrayList;
import po.PlayerPO;

public interface IPlayer {
	/**获得指定球员信息*/
	public PlayerPO getSinglePlayerInfo(String name);
	/**获得指定球员信息*/
	public ArrayList<PlayerPO> getAllPlayersInfo();
}

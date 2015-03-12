package dataservice;

import java.util.ArrayList;
import po.PlayerBasicInfoPO;

public interface IPlayer {
	/**获得指定球员信息*/
	public PlayerBasicInfoPO getSinglePlayerInfo(String name);
	/**获得指定球员信息*/
	public ArrayList<PlayerBasicInfoPO> getAllPlayersInfo();
}

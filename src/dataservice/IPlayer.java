package dataservice;

import java.util.ArrayList;
import po.PlayerBasicInfoPO;

public interface IPlayer {
	/**获得指定球员基本信息*/
	public PlayerBasicInfoPO getSinglePlayerBasicInfo(String name);
	/**获得所有球员基本信息*/
	public ArrayList<PlayerBasicInfoPO> getAllPlayersBasicInfo();
}

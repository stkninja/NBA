package dataservice;

import java.util.ArrayList;
import po.PlayerBasicInfoPO;

public interface IPlayer {
	/**获得指定球员基本信息*/
	public PlayerBasicInfoPO getSinglePlayerBasicInfo(String name);
	/**获得所有球员基本信息*/
	public ArrayList<PlayerBasicInfoPO> getAllPlayersBasicInfo();
	/**获得所有球员名称*/
	public ArrayList<String> getAllPlayersName();
	/**获得球员先发场数*/
	public double getPlayerGameStart(String name, String season);
	/**获得球员参赛场数*/	
	public double getPlayerGamePlay(String name, String season);
}

package data;

import java.util.ArrayList;

import po.PlayerBasicInfoPO;
import dataservice.IPlayer;

public class GetPlayersInfo implements IPlayer{
	public PlayerBasicInfoPO getSinglePlayerBasicInfo(String name) {
		return null;
	}

	public ArrayList<PlayerBasicInfoPO> getAllPlayersBasicInfo() {
		return null;
	}

	public ArrayList<String> getAllPlayersName() {
		return null;
	}

	public double getPlayerGameStart(String name, String season) {
		return 0;
	}

	public double getPlayerGamePlay(String name, String season) {
		return 0;
	}

}

package data;

import java.util.ArrayList;

import po.MatchPO;
import po.PlayerBasicInfoPO;
import data.rwArrangedFiles.ReadPOs;
import dataservice.IPlayer;

public class GetPlayersInfo implements IPlayer{

	private static ArrayList<PlayerBasicInfoPO> playerBasicInfoPOs = ReadPOs.readPlayerBasicInfoPO();

	public PlayerBasicInfoPO getSinglePlayerBasicInfo(String name) {
		for(PlayerBasicInfoPO po : playerBasicInfoPOs)
			if(po.getName().equals(name))
				return po;
		
		/**无法找到该球员*/
		return new PlayerBasicInfoPO();
	}

	public ArrayList<PlayerBasicInfoPO> getAllPlayersBasicInfo() {
		return playerBasicInfoPOs;
	}

	public ArrayList<String> getAllPlayersName() {
		ArrayList<String> names = new ArrayList<String>();
		
		for(PlayerBasicInfoPO infoPOs : playerBasicInfoPOs)
			names.add(infoPOs.getName());
		
		return names;
	}

	/**TODO*/
	public double getPlayerGameStart(String name, String season) {
		return 0;
	}

	public double getPlayerGamePlay(String name, String season) {
		GetMatchesInfo getMatchesInfo = new GetMatchesInfo();
		ArrayList<MatchPO> matchPOs = getMatchesInfo.getMatchesAboutPlayer(name);
		
		/**筛选非该赛季*/
		for(int i = 0; i < matchPOs.size(); i++){
			if(!(matchPOs.get(i).getSeason().equals(season))){
				matchPOs.remove(i);
				--i;
			}
		}
		
		return matchPOs.size();
	}
}

package data;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.PlayerBasicInfoPO;
import data.rwArrangedFiles.ReadPOs;
import dataservice.IPlayer;

public class GetPlayersInfo implements IPlayer{

	private static ArrayList<PlayerBasicInfoPO> playerBasicInfoPOs = ReadPOs.readPlayerBasicInfoPO();
	
	public PlayerBasicInfoPO getSinglePlayerBasicInfo(String name) {
		for(PlayerBasicInfoPO po : playerBasicInfoPOs)
			if(po.getName().equals(name))
				return po;
		
		/**�޷��ҵ�����Ա*/
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

	public double getPlayerGameStart(String name, String season) {
		GetMatchesInfo getMatchesInfo = new GetMatchesInfo();
		ArrayList<MatchPO> matchPOs = getMatchesInfo.getMatchesAboutPlayer(name);
		
		/**ɸѡ�Ǹ�����*/
		for(int i = 0; i < matchPOs.size(); i++){
			if(!(matchPOs.get(i).getSeason().equals(season))){
				matchPOs.remove(i);
				--i;
			}
		}
		
		/**ͳ���ȷ�����*/
		double gameStart = 0.0;
		for(MatchPO matchPO : matchPOs){
			ArrayList<MatchPlayerDataPO> teamPlayers = matchPO.getTeam1().getTeamPlayers();
			for(MatchPlayerDataPO matchPlayerPO : teamPlayers){
				if(matchPlayerPO.getName().equals(name)){
					gameStart += matchPlayerPO.getGameStart();
					break;
				}
			}
		}
		
		return gameStart;
	}

	public double getPlayerGamePlay(String name, String season) {
		GetMatchesInfo getMatchesInfo = new GetMatchesInfo();
		ArrayList<MatchPO> matchPOs = getMatchesInfo.getMatchesAboutPlayer(name);
		
		/**ɸѡ�Ǹ�����*/
		for(int i = 0; i < matchPOs.size(); i++){
			if(!(matchPOs.get(i).getSeason().equals(season))){
				matchPOs.remove(i);
				--i;
			}
		}
		
		return matchPOs.size();
	}

	/**�����Ա���������� ����*/
	public String getTeam(String name) {
		ArrayList<MatchPO> list = (new GetMatchesInfo()).getMatchesAboutPlayer(name);
		
		if(list.size() != 0){
			/**�ҵ�����ı���*/
			MatchPO lastMatchPO = list.get(0);
			for(int i = 1; i < list.size(); i++)
				if((list.get(i).getSeason() + list.get(i).getDate()).compareTo
						(lastMatchPO.getSeason() + lastMatchPO.getDate()) > 0)
					 lastMatchPO = list.get(i);
			
			return lastMatchPO.getTeam1().getAbbName();
		}
		return "";
	}

	public String getSubArea(String name) {
		ArrayList<MatchPO> list = (new GetMatchesInfo()).getMatchesAboutPlayer(name);
		
		if(list.size() != 0){
			/**�ҵ�����ı���*/
			MatchPO lastMatchPO = list.get(0);
			for(int i = 1; i < list.size(); i++)
				if((list.get(i).getSeason() + list.get(i).getDate()).compareTo
						(lastMatchPO.getSeason() + lastMatchPO.getDate()) > 0)
					 lastMatchPO = list.get(i);
			
			return (new GetTeamsInfo()).getSubArea(lastMatchPO.getTeam1().getAbbName());
		}
		return "";
	}
}

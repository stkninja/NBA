package data.deal;

import java.io.File;
import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.MatchTeamDataPO;
import data.readOriFiles.ReadMBasicInfo;

public class DealMBasicInfo {
	/**������Ŀ¼*/
	private static final String rootDirectory = "data\\matches"; 
	/**to deal*/
	private static ArrayList<MatchPO> matchesInfo = new ArrayList<MatchPO>();
	
	/**�������ֱ���*/
	public ArrayList<ArrayList<MatchPO>> dealMBasicInfo() {
		ArrayList<MatchPO> allMatches = getAllMatches();
		ArrayList<ArrayList<MatchPO>> seasonMatches = new ArrayList<ArrayList<MatchPO>>();
		
		if(allMatches.size() != 0){
			ArrayList<MatchPO> newSeason = new ArrayList<MatchPO>();
			newSeason.add(allMatches.get(0));
			seasonMatches.add(newSeason);
			
			for(int i = 1; i < allMatches.size(); i++){
				for(int j = 0; j < seasonMatches.size(); j++){
					if(allMatches.get(i).getSeason().equals(seasonMatches.get(j).get(0).getSeason())){
						seasonMatches.get(j).add(allMatches.get(i));
						break;
					}
					else if(j == seasonMatches.size() - 1){
						newSeason = new ArrayList<MatchPO>();
						newSeason.add(allMatches.get(i));
						seasonMatches.add(newSeason);
						break;
					}
				}
			}
		}
	
		return seasonMatches;
	}
	
	private static ArrayList<MatchPO> getAllMatches(){
		/**�����ļ����������ļ�*/
		File f = new File(rootDirectory);
		String[] list = null;
		if(f.isDirectory()){
			list = f.list();
		}
		
		/**������Ϣͳ��*/
		if(list != null)
			initMatchesInfo(list);
		
		return matchesInfo;
	}
	
	/**�ж�һ�������Ƿ���������*/
	private static boolean hasDutyData;
	private static void initMatchesInfo(String[] list) {
		for(int i = 0; i < list.length; i++){
			/**��i���ļ��ľ���·��*/
			String absolutePath = rootDirectory + "\\" + list[i];
			
			MatchPO matchPO = new MatchPO();
			hasDutyData = false;
			matchPO.setSeason(list[i].split("_")[0]);
			matchPO.setDate(list[i].split("_")[1]);
						
			/**�����ļ���ȡ��ӱ�������*/
			ArrayList<MatchTeamDataPO> dataPOs = getTeams(absolutePath);
			
			if(hasDutyData){
				dataPOs = calData(dataPOs);
			}
			
			matchPO.setTeam1(dataPOs.get(0));
			matchPO.setTeam2(dataPOs.get(1));
			matchesInfo.add(matchPO);
			/**��ȡ������ɾ���ļ�*/
			File f = new File(absolutePath);
			f.delete();
		}
	}
	
	/**���ļ��л����ӵı�����Ϣ*/
	private static ArrayList<MatchTeamDataPO> getTeams(String absolutePath) {
		/**���ӱ�������*/
		MatchTeamDataPO team1DataPO = new MatchTeamDataPO();
		MatchTeamDataPO team2DataPO	= new MatchTeamDataPO();
		ArrayList<MatchTeamDataPO> dataPOs = new ArrayList<MatchTeamDataPO>();
		/**��ȡ�ı����ļ���ÿһ��
		 * temperature
		 * */
		ArrayList<String> matchData = new ArrayList<String>(ReadMBasicInfo.readMBasicInfo(absolutePath));
		ArrayList<String> team1Data = new ArrayList<String>();
		ArrayList<String> team2Data = new ArrayList<String>();
		
		team1DataPO.setAbbName(matchData.get(0).split(";")[1].split("-")[0]);
		team2DataPO.setAbbName(matchData.get(0).split(";")[1].split("-")[1]);
		team1DataPO.setScores(Double.parseDouble(matchData.get(0).split(";")[2].split("-")[0]));
		team2DataPO.setScores(Double.parseDouble(matchData.get(0).split(";")[2].split("-")[1]));
		team1DataPO.setQt1Scores(Double.parseDouble(matchData.get(1).split(";")[0].split("-")[0]));
		team2DataPO.setQt1Scores(Double.parseDouble(matchData.get(1).split(";")[0].split("-")[1]));
		team1DataPO.setQt2Scores(Double.parseDouble(matchData.get(1).split(";")[1].split("-")[0]));
		team2DataPO.setQt2Scores(Double.parseDouble(matchData.get(1).split(";")[1].split("-")[1]));
		team1DataPO.setQt3Scores(Double.parseDouble(matchData.get(1).split(";")[2].split("-")[0]));
		team2DataPO.setQt3Scores(Double.parseDouble(matchData.get(1).split(";")[2].split("-")[1]));
		team1DataPO.setQt4Scores(Double.parseDouble(matchData.get(1).split(";")[3].split("-")[0]));
		team2DataPO.setQt4Scores(Double.parseDouble(matchData.get(1).split(";")[3].split("-")[1]));
		
		for(int i = 4; i < matchData.get(1).split(";").length; i++){
			team1DataPO.addQtPlusScores(Double.parseDouble(matchData.get(1).split(";")[i].split("-")[0]));
			team2DataPO.addQtPlusScores(Double.parseDouble(matchData.get(1).split(";")[i].split("-")[1]));
		}
			
		/**ÿ֧��ӵ�������Ա���ӵ�4�п�ʼ*/
		int i = 3;
		while(!(matchData.get(i).equals(team2DataPO.getAbbName()))){
			team1Data.add(matchData.get(i));
			i++;
		}
		
		for(++i; i < matchData.size(); i++){
			team2Data.add(matchData.get(i));
		}
		
		/**team1Data*/
		for(int j = 0; j < team1Data.size(); j++){
			MatchPlayerDataPO playerDataPO = new MatchPlayerDataPO();
			String[] data = team1Data.get(j).split(";");
			
			/**���ڿ�(��)����*/
			if(isIllegalData(data).equals(DutyType.DUTY_TIME_AND_DATA) || isIllegalData(data).equals(DutyType.TIME_ZERO))
				continue;
			else if(isIllegalData(data).equals(DutyType.NULL_POINT)){
				data[17] = "-1";
				hasDutyData = true;
			}
			else if(isIllegalData(data).equals(DutyType.ONLY_DUTY_TIME)){
				data[2] = "-1:00";
				hasDutyData = true;
			}
			
			/**������Ա��������*/
			playerDataPO = setData(data);
			
			team1DataPO.addPlayer(playerDataPO);
		}
		
		/**team2Data*/
		for(int j = 0; j < team2Data.size(); j++){
			MatchPlayerDataPO playerDataPO = new MatchPlayerDataPO();
			String[] data = team2Data.get(j).split(";");
			
			/**���ڿ�(��)����*/
			if(isIllegalData(data).equals(DutyType.DUTY_TIME_AND_DATA) || isIllegalData(data).equals(DutyType.TIME_ZERO))
				continue;
			else if(isIllegalData(data).equals(DutyType.NULL_POINT)){
				data[17] = "-1";
				hasDutyData = true;
			}
			else if(isIllegalData(data).equals(DutyType.ONLY_DUTY_TIME)){
				data[2] = "-1:00";
				hasDutyData = true;
			}
			
			if(Double.parseDouble(data[3])>Double.parseDouble(data[4]) || Double.parseDouble(data[5])>Double.parseDouble(data[6]) || Double.parseDouble(data[7])>Double.parseDouble(data[8]))
			{
				System.out.println(team1DataPO.getAbbName() + "-" + team2DataPO.getAbbName());
			}
			
			/**������Ա��������*/
			playerDataPO = setData(data);
			
			team2DataPO.addPlayer(playerDataPO);
		}
		
		dataPOs.add(team1DataPO);
		dataPOs.add(team2DataPO);
		return dataPOs;
	}
	
	/**
	 * �ж��Ƿ���������ݻ������
	 * ����ֵ
	 */
	private static DutyType isIllegalData(String[] data){
		if(data[2].equals("0:00"))
			return DutyType.TIME_ZERO;
		else if(data[17].equals("None") || data[17].equals("null"))
			return DutyType.NULL_POINT;
		
		if(data[2].equals("null") || data[2].equals("None"))
			for(int i = 3; i <= 17; i++){
				if(!data[i].equals("0"))
					return DutyType.ONLY_DUTY_TIME;
				else if(i == 17)
					return DutyType.DUTY_TIME_AND_DATA;
		}
		
		return DutyType.DATA_OK;
	}

	/**������Ա��Ϣ*/
	private static MatchPlayerDataPO setData(String[] data){
		MatchPlayerDataPO playerDataPO = new MatchPlayerDataPO();
		
		playerDataPO.setName(data[0]);
		if(!(data[1].equals("")))
			playerDataPO.setGameStart(1.0);
		playerDataPO.setMinute(Double.parseDouble(data[2].split(":")[0]) + Double.parseDouble(data[2].split(":")[1]) / 60);
		playerDataPO.setAssist(Double.parseDouble(data[12]));		//������
		playerDataPO.setSteal(Double.parseDouble(data[13]));         //������
		playerDataPO.setBlock(Double.parseDouble(data[14]));         //��ñ��
		playerDataPO.setError(Double.parseDouble(data[15]));         //ʧ����
		playerDataPO.setFoul(Double.parseDouble(data[16]));          //������
		playerDataPO.setPoint(Double.parseDouble(data[17]));     //�÷�
		playerDataPO.setOffensiveRebounds(Double.parseDouble(data[9]));
		playerDataPO.setDefensiveRebounds(Double.parseDouble(data[10]));
		playerDataPO.setShoot(Double.parseDouble(data[4]));         //Ͷ����
		playerDataPO.setShootmade(Double.parseDouble(data[3]));     //Ͷ��������
		playerDataPO.setThreepoint(Double.parseDouble(data[6]));    //���ֳ�����
		playerDataPO.setThreepointmade(Double.parseDouble(data[5]));//����������
		playerDataPO.setFreethrow(Double.parseDouble(data[8]));     //���������
		playerDataPO.setFreethrowmade(Double.parseDouble(data[7])); //����������
		
		return playerDataPO;
	}
	
	/**�ɼ����������*/
	private static ArrayList<MatchTeamDataPO> calData(ArrayList<MatchTeamDataPO> pos){
		/**ÿһ�����*/
		for(int i = 0; i < pos.size(); i++){
			/**��������Ա����ĩβ*/
			ArrayList<MatchPlayerDataPO> players = pos.get(i).getTeamPlayers();
			for(int j = 0; j < players.size(); j++){
				if(players.get(j).getMinute() == -1 || players.get(j).getPoint() == -1){
					/**������ĩβ*/
					MatchPlayerDataPO temp = pos.get(i).getTeamPlayers().get(pos.get(i).getTeamPlayers().size() - 1);
					pos.get(i).getTeamPlayers().set(pos.get(i).getTeamPlayers().size() - 1, pos.get(i).getTeamPlayers().get(j));
					pos.get(i).getTeamPlayers().set(j, temp);
					break;
				}
			}
			
			/**����*/
			double point = 0;
			double minute = 0;
			for(int j = 0; j < pos.get(i).getTeamPlayers().size() - 1; j++){
				point += pos.get(i).getTeamPlayers().get(j).getPoint();
				minute += pos.get(i).getTeamPlayers().get(j).getMinute();
			}
			
			int temp = pos.get(i).getQtPlusScores().size();
			pos.get(i).getTeamPlayers().get(pos.get(i).getTeamPlayers().size() - 1).setMinute(240 + 25 * temp - minute);
			
			pos.get(i).getTeamPlayers().get(pos.get(i).getTeamPlayers().size() - 1).setPoint(pos.get(i).getScores() - point);
		}
		
		return pos;
	}
}
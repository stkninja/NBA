package data.predo;

import java.io.File;
import java.util.ArrayList;

import test.*;
import po.MatchPO;
import po.MatchPlayerDataPO;
import po.MatchTeamDataPO;
import data.io.ReadMatch;

public class MatchBasic {
	/**������Ŀ¼*/
	private static final String rootDirectory = new Console().getDataSource() + "data\\matches"; 
	
	public ArrayList<MatchPO> matchBasic(String season) {
		/**�����ļ���*/
		File f = new File(rootDirectory);
		String[] list = null;
		if(f.isDirectory()){
			list = f.list();
		}
		/**��ȡ�ļ�*/
		ArrayList<MatchPO> matches = new ArrayList<MatchPO>();
		if(list != null){
			matches = this.getAllMatches(list);
		}
		return matches;			
	}

	/**�ж�һ�������Ƿ���������*/
	private static boolean hasDutyData;
	/**��ȡ�ļ�*/
	private ArrayList<MatchPO> getAllMatches(String[] list) {
		ArrayList<MatchPO> matches = new ArrayList<MatchPO>();
		for(int i = 0; i < list.length; i++){
			/**��i���ļ��ľ���·��*/
			String absolutePath = rootDirectory + "\\" + list[i];
			MatchPO matchPO = new MatchPO();
			hasDutyData = false;
					
			/**��ӱ�������*/
			ArrayList<MatchTeamDataPO> dataPOs = getTeams(absolutePath);
			
			/**�Ƿ��пɼ���������*/
			if(hasDutyData){
				dataPOs = calData(dataPOs);
			}
			
			matchPO.setSeason(list[i].split("_")[0]);
			matchPO.setDate(list[i].split("_")[1]);
			matchPO.setTeam1(dataPOs.get(0));
			matchPO.setTeam2(dataPOs.get(1));
			matches.add(matchPO);
		}
		return matches;
	}
	
	/**���ļ��л����ӵı�����Ϣ*/
	private static ArrayList<MatchTeamDataPO> getTeams(String absolutePath) {
		/**���ӱ�������*/
		MatchTeamDataPO team1DataPO = new MatchTeamDataPO();
		MatchTeamDataPO team2DataPO	= new MatchTeamDataPO();
		ArrayList<MatchTeamDataPO> dataPOs = new ArrayList<MatchTeamDataPO>();
		
		/**
		 * ��ȡ�ı����ļ���ÿһ��
		 */
		ArrayList<String> matchData = new ArrayList<String>(ReadMatch.readMBasicInfo(absolutePath));
		ArrayList<String> team1Players = new ArrayList<String>();
		ArrayList<String> team2Players = new ArrayList<String>();
		
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
			team1Players.add(matchData.get(i));
			i++;
		}
		
		for(++i; i < matchData.size(); i++){
			team2Players.add(matchData.get(i));
		}
		
		/**team1�����Ա*/
		for(int j = 0; j < team1Players.size(); j++){
			MatchPlayerDataPO playerDataPO = new MatchPlayerDataPO();
			String[] data = team1Players.get(j).split(";");
			
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
		
		/**team2�����Ա*/
		for(int j = 0; j < team2Players.size(); j++){
			MatchPlayerDataPO playerDataPO = new MatchPlayerDataPO();
			String[] data = team2Players.get(j).split(";");
			
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
		playerDataPO.setPosition(data[1]);
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
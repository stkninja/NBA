package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.MatchTeamDataPO;

public class RSToMatchPO {
	
	/*===============================================================*
	 * rs每一行为一场比赛记录 根据mid找到对应的所有球员数据
	 *===============================================================*/
	public static ArrayList<MatchPO> toMatchPO(ResultSet matchRS, ResultSet matchPlayerRS){
		ArrayList<String[]> arrays_1 = RSToBasicPO.to2DStringArray(matchRS);
		ArrayList<String[]> arrays_2 = RSToBasicPO.to2DStringArray(matchPlayerRS);
		
		if(arrays_1 == null)
			return null;
		
		ArrayList<MatchPO> matches = new ArrayList<MatchPO>();		
		for(int i = 0; i < arrays_1.size(); i++){
			//循环查找每一场比赛
			MatchPO match = new MatchPO();
			match.setSeason(arrays_1.get(i)[1]);
			match.setIsPlayOffs(arrays_1.get(i)[2]);
			match.setDate(arrays_1.get(i)[4]);
			//team1主队 team2客队
			ArrayList<String[]> team_1players = new ArrayList<String[]>();
			ArrayList<String[]> team_2players = new ArrayList<String[]>();
			for(int j = 0; j < arrays_2.size(); j++){
				//球员已查找完#mid不同#即该场比赛球员全部找完
				if(!arrays_2.get(j)[0].equals(arrays_1.get(i)[0]) && team_1players.size() > 0)
					break;
				
				//满足mid teamFullName#添加球员
				if(arrays_2.get(j)[0].equals(arrays_1.get(i)[0]) && arrays_2.get(j)[2].equals(arrays_1.get(i)[6])){
					team_2players.add(arrays_2.get(j));
					arrays_2.remove(j);
					j--;
				}
				else if(arrays_2.get(j)[0].equals(arrays_1.get(i)[0]) && arrays_2.get(j)[2].equals(arrays_1.get(i)[9])){
					team_1players.add(arrays_2.get(j));
					arrays_2.remove(j);
					j--;
				}
			}
			match.setTeam1(toMatchTeamDataPO(arrays_1.get(i)[8], arrays_1.get(i)[10], team_1players));
			match.setTeam2(toMatchTeamDataPO(arrays_1.get(i)[5], arrays_1.get(i)[7], team_2players));
			matches.add(match);
		}
		return matches;
	}

	/*==================================================*
	 * 经过上一个方法过滤#mid必存在
	 *==================================================*/
	public static MatchTeamDataPO toMatchTeamDataPO(String abbName, String scores, ArrayList<String[]> teamplayers){
		MatchTeamDataPO mtd = new MatchTeamDataPO();
		mtd.setAbbName(abbName);
		String[] scoresOfQuarter = scores.split(";");
		mtd.setScores(Integer.parseInt(scoresOfQuarter[scoresOfQuarter.length - 1]));
		mtd.setQt1Scores(Integer.parseInt(scoresOfQuarter[0]));
		mtd.setQt2Scores(Integer.parseInt(scoresOfQuarter[1]));
		mtd.setQt3Scores(Integer.parseInt(scoresOfQuarter[2]));
		mtd.setQt4Scores(Integer.parseInt(scoresOfQuarter[3]));
		for(int i = 4; i < scoresOfQuarter.length - 1; i++){
			mtd.addQtPlusScores(Integer.parseInt(scoresOfQuarter[i]));
		}
		mtd.setTeamPlayers(toMatchPlayerDataPO(teamplayers));
		return mtd;
	}
	
	public static ArrayList<MatchPlayerDataPO> toMatchPlayerDataPO(ArrayList<String[]> teamplayers){
		if(teamplayers == null)
			return null;		
		
		ArrayList<MatchPlayerDataPO> players = new ArrayList<MatchPlayerDataPO>();
		for(String[] array : teamplayers){
			MatchPlayerDataPO mpd = new MatchPlayerDataPO();
			mpd.setName(array[1].replace('#', '\''));
			if(!array[4].equals(""))
				mpd.setMinute(Double.parseDouble(array[4].split(":")[0]) + Double.parseDouble(array[4].split(":")[1]) / 60);
			mpd.setOffensiveRebounds(Double.parseDouble(array[11]));
			mpd.setDefensiveRebounds(Double.parseDouble(array[12]));
			mpd.setAssist(Double.parseDouble(array[13]));
			mpd.setSteal(Double.parseDouble(array[14]));
			mpd.setBlock(Double.parseDouble(array[15]));
			mpd.setError(Double.parseDouble(array[16]));
			mpd.setFoul(Double.parseDouble(array[17]));
			mpd.setPoint(Double.parseDouble(array[18]));
			mpd.setShoot(Double.parseDouble(array[5]));
			mpd.setShootmade(Double.parseDouble(array[6]));
			mpd.setThreepoint(Double.parseDouble(array[7]));
			mpd.setThreepointmade(Double.parseDouble(array[8]));
			mpd.setFreethrow(Double.parseDouble(array[9]));
			mpd.setFreethrowmade(Double.parseDouble(array[10]));
			if(array[3].equals("T"))
				mpd.setGameStart(1);
			players.add(mpd);
		}
		return players;
	}
}

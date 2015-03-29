package data.deal;

import java.io.File;
import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.MatchTeamDataPO;
import data.readOriFiles.ReadMBasicInfo;

public class DealMBasicInfo {
	/**比赛根目录*/
	private static final String rootDirectory = "data\\matches"; 
	/**to deal*/
	private static ArrayList<MatchPO> matchesInfo = new ArrayList<MatchPO>();
	
	/**按赛季分比赛*/
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
		/**遍历文件夹下所有文件*/
		File f = new File(rootDirectory);
		String[] list = null;
		if(f.isDirectory()){
			list = f.list();
		}
		
		/**比赛信息统计*/
		if(list != null)
			initMatchesInfo(list);
		
		return matchesInfo;
	}
	
	private static void initMatchesInfo(String[] list) {
		for(int i = 0; i < list.length; i++){
			/**第i个文件的绝对路径*/
			String absolutePath = rootDirectory + "\\" + list[i];
			
			MatchPO matchPO = new MatchPO();
			matchPO.setSeason(list[i].split("_")[0]);
			matchPO.setDate(list[i].split("_")[1]);
						
			/**解析文件获取球队比赛数据*/
			ArrayList<MatchTeamDataPO> dataPOs = getTeams(absolutePath);
			
			if(dataPOs.size() != 0){
				matchPO.setTeam1(dataPOs.get(0));
				matchPO.setTeam2(dataPOs.get(1));
				matchesInfo.add(matchPO);
			}
			
			/**读取完数据删除文件*/
			File f = new File(absolutePath);
			f.delete();
		}
	}
	
	/**从文件中获得球队的比赛信息*/
	private static ArrayList<MatchTeamDataPO> getTeams(String absolutePath) {
		/**两队比赛数据*/
		MatchTeamDataPO team1DataPO = new MatchTeamDataPO();
		MatchTeamDataPO team2DataPO	= new MatchTeamDataPO();
		ArrayList<MatchTeamDataPO> dataPOs = new ArrayList<MatchTeamDataPO>();
		/**读取的比赛文件的每一行
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
		team1DataPO.setQtPlusScores(team1DataPO.getScores() - team1DataPO.getQt1Scores() - team1DataPO.getQt2Scores() - team1DataPO.getQt3Scores() - team1DataPO.getQt4Scores());
		team2DataPO.setQtPlusScores(team2DataPO.getScores() - team2DataPO.getQt1Scores() - team2DataPO.getQt2Scores() - team2DataPO.getQt3Scores() - team2DataPO.getQt4Scores());
		
		/**每支球队的所有球员，从第4行开始*/
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
			
			/**存在空(脏)数据 跳过该球员*/
			if(isIllegalData(data))
				continue;
			
			/**设置球员比赛属性*/
			playerDataPO = setData(data);
			
			team1DataPO.addPlayer(playerDataPO);
		}
		
		/**team2Data*/
		for(int j = 0; j < team2Data.size(); j++){
			MatchPlayerDataPO playerDataPO = new MatchPlayerDataPO();
			String[] data = team2Data.get(j).split(";");
			
			/**存在空(脏)数据 跳过该球员*/
			if(isIllegalData(data))
				continue;
			
			/**设置球员比赛属性*/
			playerDataPO = setData(data);
			
			team2DataPO.addPlayer(playerDataPO);
		}
		
		dataPOs.add(team1DataPO);
		dataPOs.add(team2DataPO);
		return dataPOs;
	}
	
	/**
	 * 判断是否存在脏数据或空数据
	 * @param data
	 * @return
	 */
	private static boolean isIllegalData(String[] data){
		if(data[2].equals("null") || data[2].equals("None") || data[2].equals("00:00") || data[2].equals("0:00"))
			return true;
		
		for(int i = 3; i <= 17; i++){
			if(data[i].equals("null") || data[i].equals("") || Double.parseDouble(data[i]) < 0)
				return true;
		}
		
		return false;
	}

	private static MatchPlayerDataPO setData(String[] data){
		MatchPlayerDataPO playerDataPO = new MatchPlayerDataPO();
		
		playerDataPO.setName(data[0]);
		if(!(data[1].equals("")))
			playerDataPO.setGameStart(1.0);
		playerDataPO.setMinute(Double.parseDouble(data[2].split(":")[0]) + Double.parseDouble(data[2].split(":")[1]) / 60);
		playerDataPO.setAssist(Double.parseDouble(data[12]));		//助攻数
		playerDataPO.setSteal(Double.parseDouble(data[13]));         //抢断数
		playerDataPO.setBlock(Double.parseDouble(data[14]));         //盖帽数
		playerDataPO.setError(Double.parseDouble(data[15]));         //失误数
		playerDataPO.setFoul(Double.parseDouble(data[16]));          //犯规数
		playerDataPO.setPoint(Double.parseDouble(data[17]));     //得分
		playerDataPO.setOffensiveRebounds(Double.parseDouble(data[9]));
		playerDataPO.setDefensiveRebounds(Double.parseDouble(data[10]));
		playerDataPO.setShoot(Double.parseDouble(data[4]));         //投篮数
		playerDataPO.setShootmade(Double.parseDouble(data[3]));     //投篮命中数
		playerDataPO.setThreepoint(Double.parseDouble(data[6]));    //三分出手数
		playerDataPO.setThreepointmade(Double.parseDouble(data[5]));//三分命中数
		playerDataPO.setFreethrow(Double.parseDouble(data[8]));     //罚球出手数
		playerDataPO.setFreethrowmade(Double.parseDouble(data[7])); //罚球命中数
		
		return playerDataPO;
	}
}

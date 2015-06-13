package spider.spiderLive;

import java.util.ArrayList;

public class LiveInfo {
	private String homeTeam = new String();
	private String visitTeam = new String();
	private String score = new String();
	//剩余时间#球队#事件#比分（主-客）
	private String[][] matchRecords = null;

	public String getHomeTeam() {
		return homeTeam;
	}
	
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	
	public String getVisitTeam() {
		return visitTeam;
	}
	
	public void setVisitTeam(String visitTeam) {
		this.visitTeam = visitTeam;
	}
	
	public String[][] getMatchRecords() {
		return matchRecords;
	}
	
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	public void setMatchRecords(String[][] matchRecords) {
		this.matchRecords = matchRecords;
	}
	
	public void setMatchRecords(ArrayList<String> matchRecords) {
		this.matchRecords = new String[matchRecords.size()][4];
		for(int i = 0; i < matchRecords.size(); i++){
			this.matchRecords[i][0] = matchRecords.get(i).split(";")[0];
			this.matchRecords[i][1] = matchRecords.get(i).split(";")[1];
			this.matchRecords[i][2] = matchRecords.get(i).split(";")[2];
			this.matchRecords[i][3] = matchRecords.get(i).split(";")[3];
		}
	}
}

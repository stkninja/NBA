package po;

import java.util.ArrayList;

public class MatchPO {
	
	private String season = new String();
	private String date = new String();
	
	private MatchTeamDataPO team1 = new MatchTeamDataPO();
	private MatchTeamDataPO team2 = new MatchTeamDataPO();
	
	private ArrayList<MatchPlayerDataPO> team1Players = new ArrayList<MatchPlayerDataPO>();
	private ArrayList<MatchPlayerDataPO> team2Players = new ArrayList<MatchPlayerDataPO>();
	
	public MatchPO() {}

	/**get()set()*/	
	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public MatchTeamDataPO getTeam1() {
		return team1;
	}

	public void setTeam1(MatchTeamDataPO team1) {
		this.team1 = team1;
	}

	public MatchTeamDataPO getTeam2() {
		return team2;
	}

	public void setTeam2(MatchTeamDataPO team2) {
		this.team2 = team2;
	}

	public ArrayList<MatchPlayerDataPO> getTeam1Players() {
		return team1Players;
	}

	public void setTeam1Players(ArrayList<MatchPlayerDataPO> team1Players) {
		this.team1Players = team1Players;
	}

	public ArrayList<MatchPlayerDataPO> getTeam2Players() {
		return team2Players;
	}

	public void setTeam2Players(ArrayList<MatchPlayerDataPO> team2Players) {
		this.team2Players = team2Players;
	}
	
	
	/**TODO*/
}

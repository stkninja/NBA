package po;

public class MatchPO{	
	private String season = new String();
	private String date = new String();
	private String isPlayOffs = new String();
	
	//team1 主队 team2 客队
	private MatchTeamDataPO team1 = new MatchTeamDataPO();
	private MatchTeamDataPO team2 = new MatchTeamDataPO();

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
	
	public String getIsPlayOffs() {
		return isPlayOffs;
	}
	
	public void setIsPlayOffs(String isPlayOffs) {
		this.isPlayOffs = isPlayOffs;
	}
}

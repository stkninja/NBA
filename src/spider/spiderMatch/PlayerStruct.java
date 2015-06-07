package spider.spiderMatch;

public class PlayerStruct {
	private String name = new String();
	private String team = new String();
	//是否先发
	private String isStarter = new String();
	private String minutesPlayed = new String();
	//投篮包括2分 3分
	private String fieldGoals = new String();
	private String fieldGoalsAttempts = new String();
	//三分
	private String threePoints = new String();
	private String threePointsAttempts = new String();
	//罚篮
	private String freeThrows = new String();
	private String freeThrowsAttempts = new String();
	//进攻防守篮板
	private String offensiveRebounds = new String();
	private String defensiveRebounds = new String();
	private String assists = new String();
	private String steals = new String();
	private String blocks = new String();
	//失误
	private String turnovers = new String();
	//犯规
	private String fouls = new String();
	private String points = new String();
	
	public String getName() {
		return name.replace('\'', '#');
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
	public String getIsStarter() {
		return isStarter;
	}
	
	public void setIsStarter(String isStarter) {
		this.isStarter = isStarter;
	}
	
	public String getMinutesPlayed() {
		return minutesPlayed;
	}
	
	public void setMinutesPlayed(String minutesPlayed) {
		this.minutesPlayed = minutesPlayed;
	}
	
	public String getFieldGoals() {
		return fieldGoals;
	}
	
	public void setFieldGoals(String fieldGoals) {
		this.fieldGoals = fieldGoals;
	}
	
	public String getFieldGoalsAttempts() {
		return fieldGoalsAttempts;
	}
	
	public void setFieldGoalsAttempts(String fieldGoalsAttempts) {
		this.fieldGoalsAttempts = fieldGoalsAttempts;
	}
	
	public String getThreePoints() {
		return threePoints;
	}
	
	public void setThreePoints(String threePoints) {
		this.threePoints = threePoints;
	}
	
	public String getThreePointsAttempts() {
		return threePointsAttempts;
	}
	
	public void setThreePointsAttempts(String threePointsAttempts) {
		this.threePointsAttempts = threePointsAttempts;
	}
	
	public String getFreeThrows() {
		return freeThrows;
	}
	
	public void setFreeThrows(String freeThrows) {
		this.freeThrows = freeThrows;
	}
	
	public String getFreeThrowsAttempts() {
		return freeThrowsAttempts;
	}
	
	public void setFreeThrowsAttempts(String freeThrowsAttempts) {
		this.freeThrowsAttempts = freeThrowsAttempts;
	}
	
	public String getOffensiveRebounds() {
		return offensiveRebounds;
	}
	
	public void setOffensiveRebounds(String offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}
	
	public String getDefensiveRebounds() {
		return defensiveRebounds;
	}
	
	public void setDefensiveRebounds(String defensiveRebounds) {
		this.defensiveRebounds = defensiveRebounds;
	}
	
	public String getAssists() {
		return assists;
	}
	
	public void setAssists(String assists) {
		this.assists = assists;
	}
	
	public String getSteals() {
		return steals;
	}
	
	public void setSteals(String steals) {
		this.steals = steals;
	}
	
	public String getBlocks() {
		return blocks;
	}
	
	public void setBlocks(String blocks) {
		this.blocks = blocks;
	}
	
	public String getTurnovers() {
		return turnovers;
	}
	
	public void setTurnovers(String turnovers) {
		this.turnovers = turnovers;
	}
	
	public String getFouls() {
		return fouls;
	}
	
	public void setFouls(String fouls) {
		this.fouls = fouls;
	}
	
	public String getPoints() {
		return points;
	}
	
	public void setPoints(String points) {
		this.points = points;
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.isStarter + " " + this.fieldGoals + " " + this.fieldGoalsAttempts 
				+ " " + this.threePoints + " " + this.threePointsAttempts + " " + this.freeThrows + " " 
				+ this.freeThrowsAttempts + " " + this.offensiveRebounds + " " + this.defensiveRebounds + 
				" " + this.assists + " " + this.steals + " " + this.blocks + " " + this.turnovers
				+ " " + this.fouls + " " + this.points;
	}
}

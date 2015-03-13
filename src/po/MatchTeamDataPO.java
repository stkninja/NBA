package po;

import java.util.ArrayList;


public class MatchTeamDataPO {

	/**球队比赛信息*/
	//球队缩写
	private String abbName = new String();
	//投篮命中数
	private double shootingHit;
	//投篮出手数
	private double shooting;
	//三分命中数
	private double threePointHits;
	//三分出手数
	private double threePoint;
	//罚球命中数
	private double freeThrowHit;
	//罚球出手数
	private double freeThrow;
	//进攻篮板数
	private double offensiveRebounds;
	//防守篮板数
	private double defensiveRebounds;
	//对手进攻篮板数
	private double opponentOffensiveRebounds;
	//对手防守篮板数
	private double opponentDefensiveRebounds;
	//篮板数
	private double rebounds;
	//助攻数
	private double assists;
	//抢断数
	private double steals;
	//盖帽数
	private double caps;
	//失误数
	private double turnovers;
	//犯规数
	private double fouls;
	//比赛得分
	private double scores;
	
	private ArrayList<MatchPlayerDataPO> team1Players = new ArrayList<MatchPlayerDataPO>();
	private ArrayList<MatchPlayerDataPO> team2Players = new ArrayList<MatchPlayerDataPO>();
	
	
	public MatchTeamDataPO() {
	}

	/**get set*/
	public String getAbbName() {
		return abbName;
	}

	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}

	public double getShootingHit() {
		return shootingHit;
	}

	public void setShootingHit(double shootingHit) {
		this.shootingHit = shootingHit;
	}

	public double getShooting() {
		return shooting;
	}

	public void setShooting(double shooting) {
		this.shooting = shooting;
	}

	public double getThreePointHits() {
		return threePointHits;
	}

	public void setThreePointHits(double threePointHits) {
		this.threePointHits = threePointHits;
	}

	public double getThreePoint() {
		return threePoint;
	}

	public void setThreePoint(double threePoint) {
		this.threePoint = threePoint;
	}

	public double getFreeThrowHit() {
		return freeThrowHit;
	}

	public void setFreeThrowHit(double freeThrowHit) {
		this.freeThrowHit = freeThrowHit;
	}

	public double getFreeThrow() {
		return freeThrow;
	}

	public void setFreeThrow(double freeThrow) {
		this.freeThrow = freeThrow;
	}

	public double getOffensiveRebounds() {
		return offensiveRebounds;
	}

	public void setOffensiveRebounds(double offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}

	public double getDefensiveRebounds() {
		return defensiveRebounds;
	}

	public void setDefensiveRebounds(double defensiveRebounds) {
		this.defensiveRebounds = defensiveRebounds;
	}

	public double getOpponentOffensiveRebounds() {
		return opponentOffensiveRebounds;
	}

	public void setOpponentOffensiveRebounds(double opponentOffensiveRebounds) {
		this.opponentOffensiveRebounds = opponentOffensiveRebounds;
	}

	public double getOpponentDefensiveRebounds() {
		return opponentDefensiveRebounds;
	}

	public void setOpponentDefensiveRebounds(double opponentDefensiveRebounds) {
		this.opponentDefensiveRebounds = opponentDefensiveRebounds;
	}

	public double getRebounds() {
		return rebounds;
	}

	public void setRebounds(double rebounds) {
		this.rebounds = rebounds;
	}

	public double getAssists() {
		return assists;
	}

	public void setAssists(double assists) {
		this.assists = assists;
	}

	public double getSteals() {
		return steals;
	}

	public void setSteals(double steals) {
		this.steals = steals;
	}

	public double getCaps() {
		return caps;
	}

	public void setCaps(double caps) {
		this.caps = caps;
	}

	public double getTurnovers() {
		return turnovers;
	}

	public void setTurnovers(double turnovers) {
		this.turnovers = turnovers;
	}

	public double getFouls() {
		return fouls;
	}

	public void setFouls(double fouls) {
		this.fouls = fouls;
	}

	public double getScores() {
		return scores;
	}

	public void setScores(double scores) {
		this.scores = scores;
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
	
}

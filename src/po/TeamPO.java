package po;

import java.awt.Image;
import java.util.ArrayList;

public class TeamPO{
	/**球队基本信息*/
	//球队标志
	private Image teamLogo;
	//球队全名
	private String fullName;
	//球队缩写
	private String abbName;
	//所在地
	private String location;
	//赛区
	private String competionArea;
	//分区
	private String subArea;
	//主场
	private String homeGround;
	//建立时间
	private int setupTime;
	
	/**球队比赛信息*/
	//比赛场数
	private double gamesNum;
	//胜利场数
	private double winsNum;
	//赛季总、场均投篮命中数
	private ArrayList<String> shootingHit = new ArrayList<String>();
	//赛季总、场均投篮出手数
	private ArrayList<String> shooting = new ArrayList<String>();
	//赛季总、场均三分命中数
	private ArrayList<String> threePointHits = new ArrayList<String>();
	//赛季总、场均三分出手数
	private ArrayList<String> threePoint = new ArrayList<String>();
	//赛季总、场均罚球命中数
	private ArrayList<String> freeThrowHit = new ArrayList<String>();
	//赛季总、场均罚球出手数
	private ArrayList<String> freeThrow = new ArrayList<String>();
	//赛季总、场均进攻篮板数
	private ArrayList<String> offensiveRebounds = new ArrayList<String>();
	//赛季总、场均防守篮板数
	private ArrayList<String> defensiveRebounds = new ArrayList<String>();
	//赛季总、场均对手进攻篮板数
	private ArrayList<String> opponentOffensiveRebounds = new ArrayList<String>();
	//赛季总、场均对手防守篮板数
	private ArrayList<String> opponentDefensiveRebounds = new ArrayList<String>();
	//赛季总、场均篮板数
	private ArrayList<String> rebounds = new ArrayList<String>();
	//赛季总、场均助攻数
	private ArrayList<String> assists = new ArrayList<String>();
	//赛季总、场均抢断数
	private ArrayList<String> steals = new ArrayList<String>();
	//赛季总、场均盖帽数
	private ArrayList<String> caps = new ArrayList<String>();
	//赛季总、场均失误数
	private ArrayList<String> turnovers = new ArrayList<String>();
	//赛季总、场均犯规数
	private ArrayList<String> fouls = new ArrayList<String>();
	//赛季总、场均比赛得分
	private ArrayList<String> scores = new ArrayList<String>();
	//赛季总、场均投篮命中率
	private ArrayList<String> shootingHitRate = new ArrayList<String>();
	//赛季总、场均罚球命中率
	private ArrayList<String> freeThrowHitRate = new ArrayList<String>();
	//赛季总、场均三分命中率
	private ArrayList<String> threePointHitRate = new ArrayList<String>();
	//赛季胜率
	private double winsRate;
	//赛季总、场均进攻回合
	private ArrayList<String> attackRound = new ArrayList<String>();
	//赛季总、场均进攻效率
	private ArrayList<String> attackEfficiency = new ArrayList<String>();
	//赛季总、场均防守效率
	private ArrayList<String> defenceEfficiency = new ArrayList<String>();
	//赛季总、场均篮板效率
	private ArrayList<String> reboundsEfficiency = new ArrayList<String>();
	//赛季总、场均抢断效率
	private ArrayList<String> stealEfficiency = new ArrayList<String>();
	//赛季总、场均助攻效率
	private ArrayList<String> assistEfficiency = new ArrayList<String>();
	
	public TeamPO() {}

	/**球队及比赛信息get方法*/
	public Image getTeamLogo() {
		return teamLogo;
	}
	
	public String getFullName() {
		return fullName;
	}

	public String getAbbName() {
		return abbName;
	}

	public String getLocation() {
		return location;
	}

	public String getCompetionArea() {
		return competionArea;
	}

	public String getSubArea() {
		return subArea;
	}

	public String getHomeGround() {
		return homeGround;
	}

	public int getSetupTime() {
		return setupTime;
	}

	public double getGamesNum() {
		return gamesNum;
	}

	public double getWinsNum() {
		return winsNum;
	}

	public ArrayList<String> getShootingHit() {
		return shootingHit;
	}

	public ArrayList<String> getShooting() {
		return shooting;
	}

	public ArrayList<String> getThreePointHits() {
		return threePointHits;
	}

	public ArrayList<String> getThreePoint() {
		return threePoint;
	}

	public ArrayList<String> getFreeThrowHit() {
		return freeThrowHit;
	}

	public ArrayList<String> getFreeThrow() {
		return freeThrow;
	}

	public ArrayList<String> getOffensiveRebounds() {
		return offensiveRebounds;
	}

	public ArrayList<String> getDefensiveRebounds() {
		return defensiveRebounds;
	}

	public ArrayList<String> getOpponentOffensiveRebounds() {
		return opponentOffensiveRebounds;
	}

	public ArrayList<String> getOpponentDefensiveRebounds() {
		return opponentDefensiveRebounds;
	}

	public ArrayList<String> getRebounds() {
		return rebounds;
	}

	public ArrayList<String> getAssists() {
		return assists;
	}

	public ArrayList<String> getSteals() {
		return steals;
	}

	public ArrayList<String> getCaps() {
		return caps;
	}

	public ArrayList<String> getTurnovers() {
		return turnovers;
	}

	public ArrayList<String> getFouls() {
		return fouls;
	}

	public ArrayList<String> getScores() {
		return scores;
	}

	public ArrayList<String> getShootingHitRate() {
		return shootingHitRate;
	}

	public ArrayList<String> getFreeThrowHitRate() {
		return freeThrowHitRate;
	}

	public ArrayList<String> getThreePointHitRate() {
		return threePointHitRate;
	}

	public double getWinsRate() {
		return winsRate;
	}

	public ArrayList<String> getAttackRound() {
		return attackRound;
	}

	public ArrayList<String> getAttackEfficiency() {
		return attackEfficiency;
	}

	public ArrayList<String> getDefenceEfficiency() {
		return defenceEfficiency;
	}

	public ArrayList<String> getReboundsEfficiency() {
		return reboundsEfficiency;
	}

	public ArrayList<String> getStealEfficiency() {
		return stealEfficiency;
	}

	public ArrayList<String> getAssistEfficiency() {
		return assistEfficiency;
	}
	
	/**球队及比赛信息set方法*/
	public void setTeamLogo(Image teamLogo) {
		this.teamLogo = teamLogo;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCompetionArea(String competionArea) {
		this.competionArea = competionArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	public void setHomeGround(String homeGround) {
		this.homeGround = homeGround;
	}

	public void setSetupTime(int setupTime) {
		this.setupTime = setupTime;
	}

	public void setGamesNum(double gamesNum) {
		this.gamesNum = gamesNum;
	}

	public void setWinsNum(double winsNum) {
		this.winsNum = winsNum;
	}

	public void setShootingHit(ArrayList<String> shootingHit) {
		this.shootingHit = shootingHit;
	}

	public void setShooting(ArrayList<String> shooting) {
		this.shooting = shooting;
	}

	public void setThreePointHits(ArrayList<String> threePointHits) {
		this.threePointHits = threePointHits;
	}

	public void setThreePoint(ArrayList<String> threePoint) {
		this.threePoint = threePoint;
	}

	public void setFreeThrowHit(ArrayList<String> freeThrowHit) {
		this.freeThrowHit = freeThrowHit;
	}

	public void setFreeThrow(ArrayList<String> freeThrow) {
		this.freeThrow = freeThrow;
	}

	public void setOffensiveRebounds(ArrayList<String> offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}

	public void setDefensiveRebounds(ArrayList<String> defensiveRebounds) {
		this.defensiveRebounds = defensiveRebounds;
	}

	public void setOpponentOffensiveRebounds(
			ArrayList<String> opponentOffensiveRebounds) {
		this.opponentOffensiveRebounds = opponentOffensiveRebounds;
	}

	public void setOpponentDefensiveRebounds(
			ArrayList<String> opponentDefensiveRebounds) {
		this.opponentDefensiveRebounds = opponentDefensiveRebounds;
	}

	public void setRebounds(ArrayList<String> rebounds) {
		this.rebounds = rebounds;
	}

	public void setAssists(ArrayList<String> assists) {
		this.assists = assists;
	}

	public void setSteals(ArrayList<String> steals) {
		this.steals = steals;
	}

	public void setCaps(ArrayList<String> caps) {
		this.caps = caps;
	}

	public void setTurnovers(ArrayList<String> turnovers) {
		this.turnovers = turnovers;
	}

	public void setFouls(ArrayList<String> fouls) {
		this.fouls = fouls;
	}

	public void setScores(ArrayList<String> scores) {
		this.scores = scores;
	}

	public void setShootingHitRate(ArrayList<String> shootingHitRate) {
		this.shootingHitRate = shootingHitRate;
	}

	public void setFreeThrowHitRate(ArrayList<String> freeThrowHitRate) {
		this.freeThrowHitRate = freeThrowHitRate;
	}

	public void setThreePointHitRate(ArrayList<String> threePointHitRate) {
		this.threePointHitRate = threePointHitRate;
	}

	public void setWinsRate(double winsRate) {
		this.winsRate = winsRate;
	}

	public void setAttackRound(ArrayList<String> attackRound) {
		this.attackRound = attackRound;
	}

	public void setAttackEfficiency(ArrayList<String> attackEfficiency) {
		this.attackEfficiency = attackEfficiency;
	}

	public void setDefenceEfficiency(ArrayList<String> defenceEfficiency) {
		this.defenceEfficiency = defenceEfficiency;
	}

	public void setReboundsEfficiency(ArrayList<String> reboundsEfficiency) {
		this.reboundsEfficiency = reboundsEfficiency;
	}

	public void setStealEfficiency(ArrayList<String> stealEfficiency) {
		this.stealEfficiency = stealEfficiency;
	}

	public void setAssistEfficiency(ArrayList<String> assistEfficiency) {
		this.assistEfficiency = assistEfficiency;
	}
	
	/**ToVO
	public TeamVO ToVO(){
		return new TeamVO(teamLogo, fullName, abbName, 
				location, competionArea, subArea, homeGround, setupTime, 
				gamesNum, winsNum, shootingHit, shooting, threePointHits, 
				threePoint, freeThrowHit, freeThrow, offensiveRebounds, 
				defensiveRebounds, opponentOffensiveRebounds, opponentDefensiveRebounds, 
				defensiveRebounds, assists, steals, caps, turnovers, fouls, scores);
	}*/
}
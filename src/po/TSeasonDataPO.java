package po;

import java.io.Serializable;

public class TSeasonDataPO implements Serializable{
	private static final long serialVersionUID = 1L;

	/**球队基本信息*/
	//球队全名
	private String fullName;
	//球队缩写
	private String abbName;
	//分区
	private String subArea;
	
	/**球队比赛信息*/
	//赛季
	private String season;
	//比赛场数
	private double gamesNum;
	//胜利场数
	private double winsNum;
	//赛季总投篮命中数
	private double allshootingHit;
	//赛季场均投篮命中数
    private double shootingHit;
	//赛季总投篮出手数
	private double allshooting;
	//赛季场均投篮出手数
	private double shooting;
	//赛季总三分命中数
	private double allthreePointHits;
	//赛季场均三分命中数
	private double threePointHits;
	//赛季总三分出手数
	private double allthreePoint;
	//赛季场均三分出手数
	private double threePoint;
	//赛季总罚球命中数
	private double allfreeThrowHit;
	//赛季场均罚球命中数
	private double freeThrowHit;
	//赛季总罚球出手数
	private double allfreeThrow;
	//赛季场均罚球出手数
	private double freeThrow;
	//赛季总进攻篮板数
	private double alloffensiveRebounds;
	//赛季场均进攻篮板数
	private double offensiveRebounds;
	//赛季总防守篮板数
	private double alldefensiveRebounds;
	//赛季场均防守篮板数
	private double defensiveRebounds;
	//赛季总对手进攻篮板数
	private double allopponentOffensiveRebounds;
	//赛季场均对手进攻篮板数
	private double opponentOffensiveRebounds;
	//赛季总对手防守篮板数
	private double allopponentDefensiveRebounds;
	//赛季场均对手防守篮板数
	private double opponentDefensiveRebounds;
	//赛季总篮板数
	private double allrebounds;
	//赛季场均篮板数
	private double rebounds;
	//赛季总助攻数
	private double allassists;
	//赛季场均助攻数
	private double assists;
	//赛季总抢断数
	private double allsteal;
	//赛季场均抢断数
	private double steal;
	//赛季总盖帽数
	private double allcaps;
	//赛季场均盖帽数
	private double caps;
	//赛季总失误数
	private double allturnovers;
	//赛季场均失误数
	private double turnovers;
	//赛季总犯规数
	private double allfouls;
	//赛季场均犯规数
	private double fouls;
	//赛季总比赛得分
	private double allscores;
	//赛季场均比赛得分
	private double scores;
	//赛季总投篮命中率
	private double allshootingHitRate;
	//赛季场均投篮命中率
	private double shootingHitRate;
	//赛季总罚球命中率
	private double allfreeThrowHitRate;
	//赛季场均罚球命中率
	private double freeThrowHitRate;
	//赛季总三分命中率
	private double allthreePointHitRate;
	//赛季场均三分命中率
	private double threePointHitRate;
	//赛季胜率
	private double winsRate;
	//赛季总进攻回合
	private double allattackRound;
	//赛季场均进攻回合
	private double attackRound;
	//赛季总进攻效率
	private double allattackEfficiency;
	//赛季场均进攻效率
	private double attackEfficiency;
	//赛季总防守效率
	private double alldefenceEfficiency;
	//赛季场均防守效率
	private double defenceEfficiency;
	//赛季总进攻篮板效率
	private double alloffensivereboundsEfficiency;
	//赛季场均进攻篮板效率
	private double offensivereboundsEfficiency;
	//总防守篮板效率
	private double alldefensivereboundsEfficiency;
	//场均防守篮板效率
	private double defensivereboundsEfficiency;
	//赛季总抢断效率
	private double allstealEfficiency;
	//赛季场均抢断效率
	private double stealEfficiency;
	//赛季总助攻效率
	private double allassistEfficiency;
	//赛季场均助攻效率
	private double assistEfficiency;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAbbName() {
		return abbName;
	}
	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}
	public String getSubArea() {
		return subArea;
	}
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public double getGamesNum() {
		return gamesNum;
	}
	public void setGamesNum(double gamesNum) {
		this.gamesNum = gamesNum;
	}
	public double getWinsNum() {
		return winsNum;
	}
	public void setWinsNum(double winsNum) {
		this.winsNum = winsNum;
	}
	public double getAllshootingHit() {
		return allshootingHit;
	}
	public void setAllshootingHit(double allshootingHit) {
		this.allshootingHit = allshootingHit;
	}
	public double getShootingHit() {
		return shootingHit;
	}
	public void setShootingHit(double shootingHit) {
		this.shootingHit = shootingHit;
	}
	public double getAllshooting() {
		return allshooting;
	}
	public void setAllshooting(double allshooting) {
		this.allshooting = allshooting;
	}
	public double getShooting() {
		return shooting;
	}
	public void setShooting(double shooting) {
		this.shooting = shooting;
	}
	public double getAllthreePointHits() {
		return allthreePointHits;
	}
	public void setAllthreePointHits(double allthreePointHits) {
		this.allthreePointHits = allthreePointHits;
	}
	public double getThreePointHits() {
		return threePointHits;
	}
	public void setThreePointHits(double threePointHits) {
		this.threePointHits = threePointHits;
	}
	public double getAllthreePoint() {
		return allthreePoint;
	}
	public void setAllthreePoint(double allthreePoint) {
		this.allthreePoint = allthreePoint;
	}
	public double getThreePoint() {
		return threePoint;
	}
	public void setThreePoint(double threePoint) {
		this.threePoint = threePoint;
	}
	public double getAllfreeThrowHit() {
		return allfreeThrowHit;
	}
	public void setAllfreeThrowHit(double allfreeThrowHit) {
		this.allfreeThrowHit = allfreeThrowHit;
	}
	public double getFreeThrowHit() {
		return freeThrowHit;
	}
	public void setFreeThrowHit(double freeThrowHit) {
		this.freeThrowHit = freeThrowHit;
	}
	public double getAllfreeThrow() {
		return allfreeThrow;
	}
	public void setAllfreeThrow(double allfreeThrow) {
		this.allfreeThrow = allfreeThrow;
	}
	public double getFreeThrow() {
		return freeThrow;
	}
	public void setFreeThrow(double freeThrow) {
		this.freeThrow = freeThrow;
	}
	public double getAlloffensiveRebounds() {
		return alloffensiveRebounds;
	}
	public void setAlloffensiveRebounds(double alloffensiveRebounds) {
		this.alloffensiveRebounds = alloffensiveRebounds;
	}
	public double getOffensiveRebounds() {
		return offensiveRebounds;
	}
	public void setOffensiveRebounds(double offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}
	public double getAlldefensiveRebounds() {
		return alldefensiveRebounds;
	}
	public void setAlldefensiveRebounds(double alldefensiveRebounds) {
		this.alldefensiveRebounds = alldefensiveRebounds;
	}
	public double getDefensiveRebounds() {
		return defensiveRebounds;
	}
	public void setDefensiveRebounds(double defensiveRebounds) {
		this.defensiveRebounds = defensiveRebounds;
	}
	public double getAllopponentOffensiveRebounds() {
		return allopponentOffensiveRebounds;
	}
	public void setAllopponentOffensiveRebounds(double allopponentOffensiveRebounds) {
		this.allopponentOffensiveRebounds = allopponentOffensiveRebounds;
	}
	public double getOpponentOffensiveRebounds() {
		return opponentOffensiveRebounds;
	}
	public void setOpponentOffensiveRebounds(double opponentOffensiveRebounds) {
		this.opponentOffensiveRebounds = opponentOffensiveRebounds;
	}
	public double getAllopponentDefensiveRebounds() {
		return allopponentDefensiveRebounds;
	}
	public void setAllopponentDefensiveRebounds(double allopponentDefensiveRebounds) {
		this.allopponentDefensiveRebounds = allopponentDefensiveRebounds;
	}
	public double getOpponentDefensiveRebounds() {
		return opponentDefensiveRebounds;
	}
	public void setOpponentDefensiveRebounds(double opponentDefensiveRebounds) {
		this.opponentDefensiveRebounds = opponentDefensiveRebounds;
	}
	public double getAllrebounds() {
		return allrebounds;
	}
	public void setAllrebounds(double allrebounds) {
		this.allrebounds = allrebounds;
	}
	public double getRebounds() {
		return rebounds;
	}
	public void setRebounds(double rebounds) {
		this.rebounds = rebounds;
	}
	public double getAllassists() {
		return allassists;
	}
	public void setAllassists(double allassists) {
		this.allassists = allassists;
	}
	public double getAssists() {
		return assists;
	}
	public void setAssists(double assists) {
		this.assists = assists;
	}
	public double getAllsteal() {
		return allsteal;
	}
	public void setAllsteal(double allsteal) {
		this.allsteal = allsteal;
	}
	public double getSteal() {
		return steal;
	}
	public void setSteal(double steal) {
		this.steal = steal;
	}
	public double getAllcaps() {
		return allcaps;
	}
	public void setAllcaps(double allcaps) {
		this.allcaps = allcaps;
	}
	public double getCaps() {
		return caps;
	}
	public void setCaps(double caps) {
		this.caps = caps;
	}
	public double getAllturnovers() {
		return allturnovers;
	}
	public void setAllturnovers(double allturnovers) {
		this.allturnovers = allturnovers;
	}
	public double getTurnovers() {
		return turnovers;
	}
	public void setTurnovers(double turnovers) {
		this.turnovers = turnovers;
	}
	public double getAllfouls() {
		return allfouls;
	}
	public void setAllfouls(double allfouls) {
		this.allfouls = allfouls;
	}
	public double getFouls() {
		return fouls;
	}
	public void setFouls(double fouls) {
		this.fouls = fouls;
	}
	public double getAllscores() {
		return allscores;
	}
	public void setAllscores(double allscores) {
		this.allscores = allscores;
	}
	public double getScores() {
		return scores;
	}
	public void setScores(double scores) {
		this.scores = scores;
	}
	public double getAllshootingHitRate() {
		return allshootingHitRate;
	}
	public void setAllshootingHitRate(double allshootingHitRate) {
		this.allshootingHitRate = allshootingHitRate;
	}
	public double getShootingHitRate() {
		return shootingHitRate;
	}
	public void setShootingHitRate(double shootingHitRate) {
		this.shootingHitRate = shootingHitRate;
	}
	public double getAllfreeThrowHitRate() {
		return allfreeThrowHitRate;
	}
	public void setAllfreeThrowHitRate(double allfreeThrowHitRate) {
		this.allfreeThrowHitRate = allfreeThrowHitRate;
	}
	public double getFreeThrowHitRate() {
		return freeThrowHitRate;
	}
	public void setFreeThrowHitRate(double freeThrowHitRate) {
		this.freeThrowHitRate = freeThrowHitRate;
	}
	public double getAllthreePointHitRate() {
		return allthreePointHitRate;
	}
	public void setAllthreePointHitRate(double allthreePointHitRate) {
		this.allthreePointHitRate = allthreePointHitRate;
	}
	public double getThreePointHitRate() {
		return threePointHitRate;
	}
	public void setThreePointHitRate(double threePointHitRate) {
		this.threePointHitRate = threePointHitRate;
	}
	public double getWinsRate() {
		return winsRate;
	}
	public void setWinsRate(double winsRate) {
		this.winsRate = winsRate;
	}
	public double getAllattackRound() {
		return allattackRound;
	}
	public void setAllattackRound(double allattackRound) {
		this.allattackRound = allattackRound;
	}
	public double getAttackRound() {
		return attackRound;
	}
	public void setAttackRound(double attackRound) {
		this.attackRound = attackRound;
	}
	public double getAllattackEfficiency() {
		return allattackEfficiency;
	}
	public void setAllattackEfficiency(double allattackEfficiency) {
		this.allattackEfficiency = allattackEfficiency;
	}
	public double getAttackEfficiency() {
		return attackEfficiency;
	}
	public void setAttackEfficiency(double attackEfficiency) {
		this.attackEfficiency = attackEfficiency;
	}
	public double getAlldefenceEfficiency() {
		return alldefenceEfficiency;
	}
	public void setAlldefenceEfficiency(double alldefenceEfficiency) {
		this.alldefenceEfficiency = alldefenceEfficiency;
	}
	public double getDefenceEfficiency() {
		return defenceEfficiency;
	}
	public void setDefenceEfficiency(double defenceEfficiency) {
		this.defenceEfficiency = defenceEfficiency;
	}
	public double getAlloffensivereboundsEfficiency() {
		return alloffensivereboundsEfficiency;
	}
	public void setAlloffensivereboundsEfficiency(
			double alloffensivereboundsEfficiency) {
		this.alloffensivereboundsEfficiency = alloffensivereboundsEfficiency;
	}
	public double getOffensivereboundsEfficiency() {
		return offensivereboundsEfficiency;
	}
	public void setOffensivereboundsEfficiency(double offensivereboundsEfficiency) {
		this.offensivereboundsEfficiency = offensivereboundsEfficiency;
	}
	public double getAlldefensivereboundsEfficiency() {
		return alldefensivereboundsEfficiency;
	}
	public void setAlldefensivereboundsEfficiency(
			double alldefensivereboundsEfficiency) {
		this.alldefensivereboundsEfficiency = alldefensivereboundsEfficiency;
	}
	public double getDefensivereboundsEfficiency() {
		return defensivereboundsEfficiency;
	}
	public void setDefensivereboundsEfficiency(double defensivereboundsEfficiency) {
		this.defensivereboundsEfficiency = defensivereboundsEfficiency;
	}
	public double getAllstealEfficiency() {
		return allstealEfficiency;
	}
	public void setAllstealEfficiency(double allstealEfficiency) {
		this.allstealEfficiency = allstealEfficiency;
	}
	public double getStealEfficiency() {
		return stealEfficiency;
	}
	public void setStealEfficiency(double stealEfficiency) {
		this.stealEfficiency = stealEfficiency;
	}
	public double getAllassistEfficiency() {
		return allassistEfficiency;
	}
	public void setAllassistEfficiency(double allassistEfficiency) {
		this.allassistEfficiency = allassistEfficiency;
	}
	public double getAssistEfficiency() {
		return assistEfficiency;
	}
	public void setAssistEfficiency(double assistEfficiency) {
		this.assistEfficiency = assistEfficiency;
	}
}

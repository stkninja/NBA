package data.predo;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchTeamDataPO;
import po.TBasicInfoPO;
import po.TSeasonDataPO;

public class TeamSeason {

	private static ArrayList<MatchPO> matches;
	private static ArrayList<TBasicInfoPO> teams;
	
	@SuppressWarnings("static-access")
	public TSeasonDataPO teamSeason(ArrayList<MatchPO> matches, ArrayList<TBasicInfoPO> teams,String abbName) {
		this.matches = matches;
		this.teams = teams;
		
			TSeasonDataPO po = new TSeasonDataPO();
			TBasicInfoPO teambasic = new TBasicInfoPO();
			teambasic = getSingleTeamBasicInfo(abbName);
			po.setFullName(teambasic.getFullName());
			po.setAbbName(abbName);
			po.setSubArea(teambasic.getSubArea());
			po.setSeason(matches.get(0).getSeason());
			po.setGamesNum(matches.size());
			po.setWinsNum(getwinNum());
			
			if(po.getGamesNum() == 0){
				return po;
			}
			
			double allopponentscores = 0;
			double allopponentattackround = 0;
			
			for(MatchPO match : matches){
				MatchTeamDataPO mp1 = match.getTeam1();
				MatchTeamDataPO mp2 = match.getTeam2();
//				double team1attackround = calculateAttackround(mp1.getShooting(),mp1.getFreeThrow(),mp1.getOffensiveRebounds(),mp2.getDefensiveRebounds(),mp1.getShootingHit(),mp1.getTurnovers());
				double team2attackround = calculateAttackround(mp2.getShooting(),mp2.getFreeThrow(),mp2.getOffensiveRebounds(),mp1.getDefensiveRebounds(),mp2.getShootingHit(),mp2.getTurnovers());
				po.setAllshooting(po.getAllshooting() + mp1.getShooting());
				po.setAllshootingHit(po.getAllshootingHit() + mp1.getShootingHit());
				po.setAllthreePoint(po.getAllthreePoint() + mp1.getThreePoint());
				po.setAllthreePointHits(po.getAllthreePointHits() + mp1.getThreePointHits());
				po.setAllfreeThrow(po.getAllfreeThrow() + mp1.getFreeThrow());
				po.setAllfreeThrowHit(po.getAllfreeThrowHit() + mp1.getFreeThrowHit());
				po.setAlloffensiveRebounds(po.getAlloffensiveRebounds() + mp1.getOffensiveRebounds());
				po.setAlldefensiveRebounds(po.getAlldefensiveRebounds() + mp1.getDefensiveRebounds());
				po.setAllopponentOffensiveRebounds(po.getAllopponentOffensiveRebounds() + mp2.getOffensiveRebounds());
				po.setAllopponentDefensiveRebounds(po.getAllopponentDefensiveRebounds() + mp2.getDefensiveRebounds());
				po.setAllassists(po.getAllassists() + mp1.getAssists());
				po.setAllsteal(po.getAllsteal() + mp1.getSteals());
				po.setAllcaps(po.getAllcaps() + mp1.getCaps());
				po.setAllturnovers(po.getAllturnovers() + mp1.getTurnovers());
				po.setAllfouls(po.getAllfouls() + mp1.getFouls());
				po.setAllscores(po.getAllscores() + mp1.getScores());
				allopponentscores = allopponentscores + mp2.getScores();
     			allopponentattackround = allopponentattackround + team2attackround;
			}
			
			po.setAllrebounds(po.getAlloffensiveRebounds() + po.getAlldefensiveRebounds());
			if(po.getAllshooting() == 0){
				po.setAllshootingHitRate(0);
			}
			else{
				po.setAllshootingHitRate(getDouble(po.getAllshootingHit() / po.getAllshooting() ));
			}
			
			if(po.getAllthreePoint() == 0){
				po.setAllthreePointHitRate(0);
			}
			else{
				po.setAllthreePointHitRate(getDouble(po.getAllthreePointHits() / po.getAllthreePoint() ));
			}
			
			if(po.getAllfreeThrow() == 0){
				po.setAllfreeThrowHitRate(0);
			}
			else{
				po.setAllfreeThrowHitRate(getDouble(po.getAllfreeThrowHit() / po.getAllfreeThrow() ));
			}
			po.setWinsRate(getDouble(po.getWinsNum() / po.getGamesNum() ));
			po.setAllattackRound(getDouble(calculateAttackround(po.getAllshooting(),po.getAllfreeThrow(),po.getAlloffensiveRebounds(),po.getAllopponentDefensiveRebounds(),po.getAllshootingHit(),po.getAllturnovers()) ));
			
			if(po.getAllattackRound() == 0){
				po.setAllattackEfficiency(0);
			}
			else{
				if(Double.isNaN(100 * po.getAllscores() / po.getAllattackRound())){
					po.setAllattackEfficiency(0);
				}
				else{
					po.setAllattackEfficiency(getDouble(100 * po.getAllscores() / po.getAllattackRound() ));
				}
			}
			
			if(allopponentattackround == 0){
				po.setAlldefenceEfficiency(0);
			}
			else{
				if(Double.isNaN(100 * allopponentscores / allopponentattackround)){
					po.setAlldefenceEfficiency(0);
				}
				else{
					po.setAlldefenceEfficiency(getDouble(100 * allopponentscores / allopponentattackround ));
				}
			}
			if(Double.isNaN(po.getAlldefenceEfficiency())){
				po.setAlldefenceEfficiency(0);
			}
			
			if(po.getAlloffensiveRebounds() + po.getAllopponentDefensiveRebounds() == 0){
				po.setAlloffensivereboundsEfficiency(0);
			}
			else{
				po.setAlloffensivereboundsEfficiency(getDouble(po.getAlloffensiveRebounds() / (po.getAlloffensiveRebounds() + po.getAllopponentDefensiveRebounds()) ));
			}
			
			if(po.getAlldefensiveRebounds() + po.getAllopponentOffensiveRebounds() == 0){
				po.setAlldefensivereboundsEfficiency(0);
			}
			else{
				po.setAlldefensivereboundsEfficiency(getDouble(po.getAlldefensiveRebounds() / (po.getAlldefensiveRebounds() + po.getAllopponentOffensiveRebounds()) ));
			}
			
			if(allopponentattackround == 0){
				po.setAllstealEfficiency(0);
			}
			else{
				if(Double.isNaN(100 * po.getAllsteal() / allopponentattackround)){
					po.setAllstealEfficiency(0);
				}
				else{
					po.setAllstealEfficiency(getDouble(100 * po.getAllsteal() / allopponentattackround ));
				}
			}
			
			if(po.getAllattackRound() == 0){
				po.setAllassistEfficiency(0);
			}
			else{
				po.setAllassistEfficiency(getDouble(100 * po.getAllassists() / po.getAllattackRound() ));
			}
			
			po.setShooting(getDouble(po.getAllshooting() / po.getGamesNum() ));
			po.setShootingHit(getDouble(po.getAllshootingHit() / po.getGamesNum() ));
			po.setThreePoint(getDouble(po.getAllthreePoint() / po.getGamesNum() ));
			po.setThreePointHits(getDouble(po.getAllthreePointHits() / po.getGamesNum() ));
			po.setFreeThrow(getDouble(po.getAllfreeThrow() / po.getGamesNum() ));
			po.setFreeThrowHit(getDouble(po.getAllfreeThrowHit() / po.getGamesNum() ));
			po.setAttackRound(getDouble(po.getAllattackRound() / po.getGamesNum() ));
			po.setOffensiveRebounds(getDouble(po.getAlloffensiveRebounds() / po.getGamesNum() ));
			po.setDefensiveRebounds(getDouble(po.getAlldefensiveRebounds() / po.getGamesNum() ));
			po.setRebounds(getDouble(po.getAllrebounds() / po.getGamesNum() ));
			po.setAssists(getDouble(po.getAllassists() / po.getGamesNum() ));
			po.setSteal(getDouble(po.getAllsteal() / po.getGamesNum() ));
			po.setCaps(getDouble(po.getAllcaps() / po.getGamesNum() ));
			po.setTurnovers(getDouble(po.getAllturnovers() / po.getGamesNum() ));
			po.setFouls(getDouble(po.getAllfouls() / po.getGamesNum() ));
			po.setScores(getDouble(po.getAllscores() / po.getGamesNum() ));
			po.setShootingHitRate(po.getAllshootingHitRate());
			po.setThreePointHitRate(po.getAllthreePointHitRate());
			po.setFreeThrowHitRate(po.getAllfreeThrowHitRate());
			po.setAttackEfficiency(po.getAllattackEfficiency());
			po.setDefenceEfficiency(po.getAlldefenceEfficiency());
			po.setOffensivereboundsEfficiency(po.getAlloffensivereboundsEfficiency());
			po.setDefensivereboundsEfficiency(po.getAlldefensivereboundsEfficiency());
			po.setStealEfficiency(po.getAllstealEfficiency());
			po.setAssistEfficiency(po.getAllassistEfficiency());
			
			
		    return po;
	}
	
	private double calculateAttackround(double allshooting,double allfreeThrow,double alloffensiveRebounds,double allopponentDefensiveRebounds,double allshootingHit,double allturnovers){
		return allshooting + 0.4 * allfreeThrow - 1.07 * (alloffensiveRebounds / (alloffensiveRebounds + allopponentDefensiveRebounds) * (allshooting - allshootingHit)) + 1.07 * allturnovers;
	}
	
	public double getDouble(double d){
		return new  java.math.BigDecimal(Double.toString(d)).setScale(1,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**********************************************************************************************
	 * 有关team的所有season比赛
	 * 该球队在team1
	 */
	private void getMatchesAboutTeam(String name) {
		for(MatchPO matchPO : matches){
			if(matchPO.getTeam1().getAbbName().equals(name) || matchPO.getTeam2().getAbbName().equals(name)){
				/**交换该球队 至team1*/
				if(matchPO.getTeam2().getAbbName().equals(name))
					matchPO.swapTeam();	
			}
		}
	}
	
	/**
	 * 获得球队所有abbName
	 */
	private ArrayList<String> getAllTeamsAbbName(String season) {
		ArrayList<String> names = new ArrayList<String>();
		for(TBasicInfoPO po : teams){
			if(po.getAbbName().equals("NOP") && season.compareTo("12-13") <= 0)
				po.setAbbName("NOH");
			names.add(po.getAbbName());
		}
		
		return names;
	}
	
	/**
	 * 获得球队基本信息
	 */
	private TBasicInfoPO getSingleTeamBasicInfo(String abbName) {
		for(TBasicInfoPO basicInfoPO : teams)
			if(basicInfoPO.getAbbName().equals(abbName))
				return basicInfoPO;
		
		return new TBasicInfoPO();
	}
	
	/**
	 * 获得胜场数
	 */
	private double getwinNum() {	
		/**计算胜场
		 * team1为该球队
		 * */
		double winNum = 0.0;
		for(MatchPO po : matchesAbout)
			if(po.getTeam1().getScores() > po.getTeam2().getScores())
				winNum++;
		
		return winNum;
	}
}

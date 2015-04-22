package data.deal;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchTeamDataPO;
import po.TBasicInfoPO;
import po.TSeasonDataPO;
import data.GetMatchInfo;
import data.readPOs.ReadMBasicPO;
import data.readPOs.ReadTBasicPO;

public class DealTAllMatchData {

	private static ArrayList<MatchPO> matches;
	private static ArrayList<TBasicInfoPO> teams;
	private static ArrayList<MatchPO> matchesAbout;
	private GetMatchInfo match;

	public DealTAllMatchData(){
		match = new GetMatchInfo();
		matches = new ArrayList<MatchPO>();
		ArrayList<String> seasons = match.getExistedSeasons();
		for(String season : seasons)
			matches.addAll(ReadMBasicPO.readMBasicPO(season));
	}
	
	public ArrayList<TSeasonDataPO> dealTAllMatchData(String season) {
		teams = ReadTBasicPO.readTBasicPO();
		
		ArrayList<TSeasonDataPO> list = new ArrayList<TSeasonDataPO>();
		for(String name : getAllTeamsAbbName()){
			getMatchesAboutTeam(name);
			TSeasonDataPO po = new TSeasonDataPO();
			TBasicInfoPO teambasic = new TBasicInfoPO();
			teambasic = getSingleTeamBasicInfo(name);
			po.setFullName(teambasic.getFullName());
			po.setAbbName(name);
			po.setSubArea(teambasic.getSubArea());
			po.setSeason(season);
			po.setGamesNum(matchesAbout.size());
			po.setWinsNum(getwinNum());
			double allopponentscores = 0;
			double allopponentattackround = 0;
			
			for(MatchPO match : matchesAbout){
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
			po.setAllshootingHitRate(Math.ceil(po.getAllshootingHit() / po.getAllshooting() * 100)/ 100);
			po.setAllthreePointHitRate(Math.ceil(po.getAllthreePointHits() / po.getAllthreePoint() * 100)/ 100);
			po.setAllfreeThrowHitRate(Math.ceil(po.getAllfreeThrowHit() / po.getAllfreeThrow() * 100)/ 100);
			po.setWinsRate(Math.ceil(po.getWinsNum() / po.getGamesNum() * 100)/ 100);
			po.setAllattackRound(Math.ceil(calculateAttackround(po.getAllshooting(),po.getAllfreeThrow(),po.getAlloffensiveRebounds(),po.getAllopponentDefensiveRebounds(),po.getAllshootingHit(),po.getAllturnovers()) * 100)/ 100);
			po.setAllattackEfficiency(Math.ceil(100 * po.getAllscores() / po.getAllattackRound() * 100)/ 100);
	        po.setAlldefenceEfficiency(Math.ceil(100 * allopponentscores / allopponentattackround * 100)/ 100);
			po.setAlloffensivereboundsEfficiency(Math.ceil(po.getAlloffensiveRebounds() / (po.getAlloffensiveRebounds() + po.getAllopponentDefensiveRebounds()) * 100)/ 100);
			po.setAlldefensivereboundsEfficiency(Math.ceil(po.getAlldefensiveRebounds() / (po.getAlldefensiveRebounds() + po.getAllopponentOffensiveRebounds()) * 100)/ 100);
			po.setAllstealEfficiency(Math.ceil(100 * po.getAllsteal() / allopponentattackround * 100)/ 100);
			po.setAllassistEfficiency(Math.ceil(100 * po.getAllassists() / po.getAllattackRound() * 100)/ 100);
			
			po.setShooting(Math.ceil(po.getAllshooting() / po.getGamesNum() * 100)/ 100);
			po.setShootingHit(Math.ceil(po.getAllshootingHit() / po.getGamesNum() * 100)/ 100);
			po.setThreePoint(Math.ceil(po.getAllthreePoint() / po.getGamesNum() * 100)/ 100);
			po.setThreePointHits(Math.ceil(po.getAllthreePointHits() / po.getGamesNum() * 100)/ 100);
			po.setFreeThrow(Math.ceil(po.getAllfreeThrow() / po.getGamesNum() * 100)/ 100);
			po.setFreeThrowHit(Math.ceil(po.getAllfreeThrowHit() / po.getGamesNum() * 100)/ 100);
			po.setAttackRound(Math.ceil(po.getAllattackRound() / po.getGamesNum() * 100)/ 100);
			po.setOffensiveRebounds(Math.ceil(po.getAlloffensiveRebounds() / po.getGamesNum() * 100)/ 100);
			po.setDefensiveRebounds(Math.ceil(po.getAlldefensiveRebounds() / po.getGamesNum() * 100)/ 100);
			po.setRebounds(Math.ceil(po.getAllrebounds() / po.getGamesNum() * 100)/ 100);
			po.setAssists(Math.ceil(po.getAllassists() / po.getGamesNum() * 100)/ 100);
			po.setSteal(Math.ceil(po.getAllsteal() / po.getGamesNum() * 100)/ 100);
			po.setCaps(Math.ceil(po.getAllcaps() / po.getGamesNum() * 100)/ 100);
			po.setTurnovers(Math.ceil(po.getAllturnovers() / po.getGamesNum() * 100)/ 100);
			po.setFouls(Math.ceil(po.getAllfouls() / po.getGamesNum() * 100)/ 100);
			po.setScores(Math.ceil(po.getAllscores() / po.getGamesNum() * 100)/ 100);
			po.setShootingHitRate(po.getAllshootingHitRate());
			po.setThreePointHitRate(po.getAllthreePointHitRate());
			po.setFreeThrowHitRate(po.getAllfreeThrowHitRate());
			po.setAttackEfficiency(po.getAllattackEfficiency());
			po.setDefenceEfficiency(po.getAlldefenceEfficiency());
			po.setOffensivereboundsEfficiency(po.getAlloffensivereboundsEfficiency());
			po.setDefensivereboundsEfficiency(po.getAlldefensivereboundsEfficiency());
			po.setStealEfficiency(po.getAllstealEfficiency());
			po.setAssistEfficiency(po.getAllassistEfficiency());
			
			list.add(po);
		}
		return list;
	}
	
	private double calculateAttackround(double allshooting,double allfreeThrow,double alloffensiveRebounds,double allopponentDefensiveRebounds,double allshootingHit,double allturnovers){
		return allshooting + 0.4 * allfreeThrow - 1.07 * (alloffensiveRebounds / (alloffensiveRebounds + allopponentDefensiveRebounds) * (allshooting - allshootingHit)) + 1.07 * allturnovers;
	}

	/**********************************************************************************************
	 * �й�team������season����
	 * �������team1
	 */
	private void getMatchesAboutTeam(String name) {		
		matchesAbout = new ArrayList<MatchPO>();
		for(MatchPO matchPO : matches){
			/**�ҵ��������йصı���*/
			if(matchPO.getTeam1().getAbbName().equals(name) || matchPO.getTeam2().getAbbName().equals(name)){
				/**��������� ��team1*/
				if(matchPO.getTeam2().getAbbName().equals(name))
					matchPO.swapTeam();
				matchesAbout.add(matchPO);	
			}
		}
	}
	
	/**
	 * ����������abbName
	 */
	private ArrayList<String> getAllTeamsAbbName() {
		ArrayList<String> names = new ArrayList<String>();
		for(TBasicInfoPO po : teams)
			names.add(po.getAbbName());
		
		return names;
	}
	
	/**
	 * �����ӻ�����Ϣ
	 */
	private TBasicInfoPO getSingleTeamBasicInfo(String abbName) {
		for(TBasicInfoPO basicInfoPO : teams)
			if(basicInfoPO.getAbbName().equals(abbName))
				return basicInfoPO;
		
		return new TBasicInfoPO();
	}
	
	/**
	 * ���ʤ����
	 */
	private double getwinNum() {	
		/**����ʤ��
		 * team1Ϊ�����
		 * */
		double winNum = 0.0;
		for(MatchPO po : matchesAbout)
			if(po.getTeam1().getScores() > po.getTeam2().getScores())
				winNum++;
		
		return winNum;
	}
}

package businesslogic;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchTeamDataPO;
import po.TeamBasicInfoPO;
import data.GetMatchesInfo;
import data.GetTeamsInfo;
import dataservice.IMatch;
import dataservice.ITeam;
import vo.TeamBasicInfoVO;
import vo.TeamVO;

public class TeamBL implements businesslogicservice.TeamBLService{
	private ITeam teamdata = null;
	private IMatch matchdata = null;
	
	public TeamBL(){
		teamdata = new GetTeamsInfo();
		matchdata = new GetMatchesInfo();
	}
	
	@Override
	public ArrayList<TeamVO> getTeams(String subArea) {
		// TODO Auto-generated method stub
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		for(TeamVO vo : getAllTeams()){
			if(vo.subArea.equals(subArea)){
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public TeamBasicInfoVO getOneTeam(String name) {
		// TODO Auto-generated method stub
		TeamBasicInfoVO vo = new TeamBasicInfoVO();
		TeamBasicInfoPO po = teamdata.getSingleTeamBasicInfo(name);
		vo.teamLogo = po.getTeamLogo();
		vo.fullName = po.getFullName();
		vo.abbName = po.getAbbName();
		vo.location = po.getLocation();
		vo.competionArea = po.getCompetionArea();
		vo.subArea = po.getSubArea();
		vo.homeGround = po.getHomeGround();
		vo.setupTime = po.getSetupTime();
		return vo;
	}

	@Override
	public ArrayList<TeamVO> getAllTeams() {
		// TODO Auto-generated method stub
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		for(String name : teamdata.getAllTeamsName()){
			list.add(findTeam(name));
		}
		return list;
	}
	
	public double calculateAttackround(double allshooting,double allfreeThrow,double alloffensiveRebounds,double allopponentDefensiveRebounds,double allshootingHit,double allturnovers){
		return allshooting + 0.4 * allfreeThrow - 1.07 * (alloffensiveRebounds / (alloffensiveRebounds + allopponentDefensiveRebounds) * (allshooting - allshootingHit)) + 1.07 * allturnovers;
	}
	
	public TeamVO findTeam(String name){
		TeamVO vo = new TeamVO();
		vo.abbName = teamdata.getSingleTeamBasicInfo(name).getAbbName();
		vo.location = teamdata.getSingleTeamBasicInfo(name).getLocation();
		vo.competionArea = teamdata.getSingleTeamBasicInfo(name).getCompetionArea();
		vo.subArea = teamdata.getSingleTeamBasicInfo(name).getSubArea();
		vo.homeGround = teamdata.getSingleTeamBasicInfo(name).getHomeGround();
		vo.setupTime = teamdata.getSingleTeamBasicInfo(name).getSetupTime();

		vo.gamesNum = Double.parseDouble(teamdata.getSingleTeamBasicInfo(name).getGamesNum().get(0).split(";")[1]);
		vo.winsNum = Double.parseDouble(teamdata.getSingleTeamBasicInfo(name).getWinsNum().get(0).split(";")[1]);
		
		double allopponentscores = 0;
		double allopponentattackround = 0;
		double shootingHitRatesum = 0,threePointHitRatesum = 0,freeThrowHitRatesum = 0,attackEfficiencysum = 0,defenceEfficiencysum = 0,offensivereboundsEfficiencysum = 0,
				defensivereboundsEfficiencysum = 0,stealEfficiencysum = 0,assistEfficiencysum = 0;
		for(MatchPO po : matchdata.getMatchesAboutTeam(name)){
			MatchTeamDataPO mp1 = po.getTeam1();
			MatchTeamDataPO mp2 = po.getTeam2();
			double team1attackround = calculateAttackround(mp1.getShooting(),mp1.getFreeThrow(),mp1.getOffensiveRebounds(),mp1.getOpponentDefensiveRebounds(),mp1.getShootingHit(),mp1.getTurnovers());
			double team2attackround = calculateAttackround(mp2.getShooting(),mp2.getFreeThrow(),mp2.getOffensiveRebounds(),mp2.getOpponentDefensiveRebounds(),mp2.getShootingHit(),mp2.getTurnovers());
			vo.allshooting = vo.allshooting + mp1.getShooting();
			vo.allshootingHit = vo.allshootingHit + mp1.getShootingHit();
			vo.allthreePoint = vo.allthreePoint + mp1.getThreePoint();
			vo.allthreePointHits = vo.allthreePointHits + mp1.getThreePointHits();
			vo.allfreeThrow = vo.allfreeThrow + mp1.getFreeThrow();
			vo.allfreeThrowHit = vo.allfreeThrowHit + mp1.getFreeThrowHit();
			vo.alloffensiveRebounds = vo.alloffensiveRebounds + mp1.getOffensiveRebounds();
			vo.alldefensiveRebounds = vo.alldefensiveRebounds + mp1.getDefensiveRebounds();
			vo.allopponentOffensiveRebounds = vo.allopponentOffensiveRebounds + mp1.getOpponentOffensiveRebounds();
			vo.allopponentDefensiveRebounds = vo.allopponentDefensiveRebounds + mp1.getOpponentDefensiveRebounds();
			vo.allrebounds = vo.allrebounds + mp1.getRebounds();
			vo.allassists = vo.allassists + mp1.getAssists();
			vo.allsteal = vo.allsteal + mp1.getSteals();
			vo.allcaps = vo.allcaps + mp1.getCaps();
			vo.allturnovers = vo.allturnovers + mp1.getTurnovers();
			vo.allfouls = vo.allfouls + mp1.getFouls();
			vo.allscores = vo.allscores + mp1.getScores();
			allopponentscores = allopponentscores + mp2.getScores();
			allopponentattackround = allopponentattackround + team2attackround;
			shootingHitRatesum += mp1.getShootingHit() / mp1.getShooting();
			threePointHitRatesum += mp1.getThreePointHits() / mp1.getThreePoint();
			freeThrowHitRatesum += mp1.getFreeThrowHit() / mp1.getFreeThrow();
			attackEfficiencysum += 100 * mp1.getScores() / team1attackround;
			defenceEfficiencysum += 100 * mp2.getScores() / team2attackround;
			offensivereboundsEfficiencysum += mp1.getOffensiveRebounds() / (mp1.getOffensiveRebounds() + mp2.getDefensiveRebounds());
			defensivereboundsEfficiencysum += mp1.getDefensiveRebounds() / (mp1.getDefensiveRebounds() + mp2.getOffensiveRebounds());
			stealEfficiencysum += 100 * mp1.getSteals() / team2attackround;
			assistEfficiencysum += 100 * mp1.getAssists() / team1attackround;
		}
		vo.allshootingHitRate = vo.allshootingHit / vo.allshooting;
		vo.allthreePointHitRate = vo.allthreePointHits / vo.allthreePoint;
		vo.allfreeThrowHitRate = vo.allfreeThrowHit / vo.allfreeThrow;
		vo.winsRate = vo.winsNum / vo.gamesNum;
		vo.allattackRound = calculateAttackround(vo.allshooting,vo.allfreeThrow,vo.alloffensiveRebounds,vo.allopponentDefensiveRebounds,vo.allshootingHit,vo.allturnovers);
		vo.allattackEfficiency = 100 * vo.allscores / vo.allattackRound;
        vo.alldefenceEfficiency = 100 * allopponentscores / allopponentattackround;
		vo.alloffensivereboundsEfficiency = vo.alloffensiveRebounds / (vo.alloffensiveRebounds + vo.allopponentDefensiveRebounds);
		vo.alldefensivereboundsEfficiency = vo.alldefensiveRebounds / (vo.alldefensiveRebounds + vo.allopponentOffensiveRebounds);
		vo.allstealEfficiency = 100 * vo.allsteal / allopponentattackround;
		vo.allassistEfficiency = 100 * vo.allassists / vo.allattackRound;
		
		vo.shooting = vo.allshooting / vo.gamesNum;
		vo.shootingHit = vo.allshootingHit / vo.gamesNum;
		vo.threePoint = vo.allthreePoint / vo.gamesNum;
		vo.threePointHits = vo.allthreePointHits / vo.gamesNum;
		vo.freeThrow = vo.allfreeThrow / vo.gamesNum;
		vo.freeThrowHit = vo.allfreeThrowHit / vo.gamesNum;
		vo.attackRound = vo.allattackRound / vo.gamesNum;
		vo.offensiveRebounds = vo.alloffensiveRebounds / vo.gamesNum;
		vo.defensiveRebounds = vo.alldefensiveRebounds / vo.gamesNum;
		vo.rebounds = vo.allrebounds / vo.gamesNum;
		vo.assists = vo.allassists / vo.gamesNum;
		vo.steal = vo.allsteal / vo.gamesNum;
		vo.caps = vo.allcaps / vo.gamesNum;
		vo.turnovers = vo.allturnovers / vo.gamesNum;
		vo.fouls = vo.allfouls / vo.gamesNum;
		vo.scores = vo.allscores / vo.gamesNum;
		vo.shootingHitRate = shootingHitRatesum / vo.gamesNum;
		vo.threePointHitRate = threePointHitRatesum / vo.gamesNum;
		vo.freeThrowHitRate = freeThrowHitRatesum / vo.gamesNum;
		vo.attackEfficiency = attackEfficiencysum / vo.gamesNum;
		vo.defenceEfficiency = defenceEfficiencysum / vo.gamesNum;
		vo.offensivereboundsEfficiency = offensivereboundsEfficiencysum / vo.gamesNum;
		vo.defensivereboundsEfficiency = defensivereboundsEfficiencysum / vo.gamesNum;
		vo.stealEfficiency = stealEfficiencysum / vo.gamesNum;
		vo.assistEfficiency = assistEfficiencysum / vo.gamesNum;
		return vo;
	}
	
}

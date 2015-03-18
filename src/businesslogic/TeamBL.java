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
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		if(subArea.equals("All")){
			return getAllTeams();
		}
		else{
			for(TeamVO vo : getAllTeams()){
				if(vo.subArea.equals(subArea)){
					list.add(vo);
				}
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

		vo.gamesNum = teamdata.getMainNum(name, "13-14");
		vo.winsNum = teamdata.getwinNum(name, "13-14");
		
		double allopponentscores = 0;
		double allopponentattackround = 0;
		double shootingHitRatesum = 0,threePointHitRatesum = 0,freeThrowHitRatesum = 0,attackEfficiencysum = 0,defenceEfficiencysum = 0,offensivereboundsEfficiencysum = 0,
				defensivereboundsEfficiencysum = 0,stealEfficiencysum = 0,assistEfficiencysum = 0;
		for(MatchPO po : matchdata.getMatchesAboutTeam(name)){
			MatchTeamDataPO mp1 = po.getTeam1();
			MatchTeamDataPO mp2 = po.getTeam2();
			double team1attackround = calculateAttackround(mp1.getShooting(),mp1.getFreeThrow(),mp1.getOffensiveRebounds(),mp2.getDefensiveRebounds(),mp1.getShootingHit(),mp1.getTurnovers());
			double team2attackround = calculateAttackround(mp2.getShooting(),mp2.getFreeThrow(),mp2.getOffensiveRebounds(),mp1.getDefensiveRebounds(),mp2.getShootingHit(),mp2.getTurnovers());
			vo.allshooting = vo.allshooting + mp1.getShooting();
			vo.allshootingHit = vo.allshootingHit + mp1.getShootingHit();
			vo.allthreePoint = vo.allthreePoint + mp1.getThreePoint();
			vo.allthreePointHits = vo.allthreePointHits + mp1.getThreePointHits();
			vo.allfreeThrow = vo.allfreeThrow + mp1.getFreeThrow();
			vo.allfreeThrowHit = vo.allfreeThrowHit + mp1.getFreeThrowHit();
			vo.alloffensiveRebounds = vo.alloffensiveRebounds + mp1.getOffensiveRebounds();
			vo.alldefensiveRebounds = vo.alldefensiveRebounds + mp1.getDefensiveRebounds();
			vo.allopponentOffensiveRebounds = vo.allopponentOffensiveRebounds + mp2.getOffensiveRebounds();
			vo.allopponentDefensiveRebounds = vo.allopponentDefensiveRebounds + mp2.getDefensiveRebounds();
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
		vo.allshootingHitRate = Math.ceil(vo.allshootingHit / vo.allshooting * 100+.5)/ 100;
		vo.allthreePointHitRate = Math.ceil(vo.allthreePointHits / vo.allthreePoint * 100+.5)/ 100;
		vo.allfreeThrowHitRate = Math.ceil(vo.allfreeThrowHit / vo.allfreeThrow * 100+.5)/ 100;
		vo.winsRate = Math.ceil(vo.winsNum / vo.gamesNum * 100+.5)/ 100;
		vo.allattackRound = Math.ceil(calculateAttackround(vo.allshooting,vo.allfreeThrow,vo.alloffensiveRebounds,vo.allopponentDefensiveRebounds,vo.allshootingHit,vo.allturnovers) * 100+.5)/ 100;
		vo.allattackEfficiency = Math.ceil(100 * vo.allscores / vo.allattackRound * 100+.5)/ 100;
        vo.alldefenceEfficiency = Math.ceil(100 * allopponentscores / allopponentattackround * 100+.5)/ 100;
		vo.alloffensivereboundsEfficiency = Math.ceil(vo.alloffensiveRebounds / (vo.alloffensiveRebounds + vo.allopponentDefensiveRebounds) * 100+.5)/ 100;
		vo.alldefensivereboundsEfficiency = Math.ceil(vo.alldefensiveRebounds / (vo.alldefensiveRebounds + vo.allopponentOffensiveRebounds) * 100+.5)/ 100;
		vo.allstealEfficiency = Math.ceil(100 * vo.allsteal / allopponentattackround * 100+.5)/ 100;
		vo.allassistEfficiency = Math.ceil(100 * vo.allassists / vo.allattackRound * 100+.5)/ 100;
		
		vo.shooting = Math.ceil(vo.allshooting / vo.gamesNum * 100+.5)/ 100;
		vo.shootingHit = Math.ceil(vo.allshootingHit / vo.gamesNum * 100+.5)/ 100;
		vo.threePoint = Math.ceil(vo.allthreePoint / vo.gamesNum * 100+.5)/ 100;
		vo.threePointHits = Math.ceil(vo.allthreePointHits / vo.gamesNum * 100+.5)/ 100;
		vo.freeThrow = Math.ceil(vo.allfreeThrow / vo.gamesNum * 100+.5)/ 100;
		vo.freeThrowHit = Math.ceil(vo.allfreeThrowHit / vo.gamesNum * 100+.5)/ 100;
		vo.attackRound = Math.ceil(vo.allattackRound / vo.gamesNum * 100+.5)/ 100;
		vo.offensiveRebounds = Math.ceil(vo.alloffensiveRebounds / vo.gamesNum * 100+.5)/ 100;
		vo.defensiveRebounds = Math.ceil(vo.alldefensiveRebounds / vo.gamesNum * 100+.5)/ 100;
		vo.rebounds = Math.ceil(vo.allrebounds / vo.gamesNum * 100+.5)/ 100;
		vo.assists = Math.ceil(vo.allassists / vo.gamesNum * 100+.5)/ 100;
		vo.steal = Math.ceil(vo.allsteal / vo.gamesNum * 100+.5)/ 100;
		vo.caps = Math.ceil(vo.allcaps / vo.gamesNum * 100+.5)/ 100;
		vo.turnovers = Math.ceil(vo.allturnovers / vo.gamesNum * 100+.5)/ 100;
		vo.fouls = Math.ceil(vo.allfouls / vo.gamesNum * 100+.5)/ 100;
		vo.scores = Math.ceil(vo.allscores / vo.gamesNum * 100+.5)/ 100;
		vo.shootingHitRate = Math.ceil(shootingHitRatesum / vo.gamesNum * 100+.5)/ 100;
		vo.threePointHitRate = Math.ceil(threePointHitRatesum / vo.gamesNum * 100+.5)/ 100;
		vo.freeThrowHitRate = Math.ceil(freeThrowHitRatesum / vo.gamesNum * 100+.5)/ 100;
		vo.attackEfficiency = Math.ceil(attackEfficiencysum / vo.gamesNum * 100+.5)/ 100;
		vo.defenceEfficiency = Math.ceil(defenceEfficiencysum / vo.gamesNum * 100+.5)/ 100;
		vo.offensivereboundsEfficiency = Math.ceil(offensivereboundsEfficiencysum / vo.gamesNum * 100+.5)/ 100;
		vo.defensivereboundsEfficiency = Math.ceil(defensivereboundsEfficiencysum / vo.gamesNum * 100+.5)/ 100;
		vo.stealEfficiency = Math.ceil(stealEfficiencysum / vo.gamesNum * 100+.5)/ 100;
		vo.assistEfficiency = Math.ceil(assistEfficiencysum / vo.gamesNum * 100+.5)/ 100;
		return vo;
	}
	
	public ArrayList<TeamVO> getAllTeams() {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		for(String name : teamdata.getAllTeamsName()){
			list.add(findTeam(name));
		}
		return list;
	}
	
	public static void main(String[] args){
		System.out.println(Math.ceil(1.444 * 100+.5)/100);
	}
}

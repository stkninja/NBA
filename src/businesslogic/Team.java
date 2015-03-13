package businesslogic;

import java.util.ArrayList;

import po.MatchPO;
import data.GetMatchesInfo;
import data.GetTeamsInfo;
import dataservice.IMatch;
import dataservice.ITeam;
import vo.TeamVO;

public class Team implements businesslogicservice.Team{
	private ITeam teamdata = null;
	private IMatch matchdata = null;
	
	public Team(){
		teamdata = new GetTeamsInfo();
		matchdata = new GetMatchesInfo();
	}
	
	@Override
	public ArrayList<TeamVO> getTeams(String subArea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamVO getOneTeam(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TeamVO> getAllTeams() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TeamVO findTeam(String name){
		TeamVO vo = new TeamVO();
		vo.location = teamdata.getSingleTeamBasicInfo(name).getLocation();
		vo.competionArea = teamdata.getSingleTeamBasicInfo(name).getCompetionArea();
		vo.subArea = teamdata.getSingleTeamBasicInfo(name).getSubArea();
		vo.homeGround = teamdata.getSingleTeamBasicInfo(name).getHomeGround();
		vo.setupTime = teamdata.getSingleTeamBasicInfo(name).getSetupTime();
		
		vo.gamesNum = Double.parseDouble(teamdata.getSingleTeamBasicInfo(name).getGamesNum().get(0).split(";")[1]);
		vo.winsNum = Double.parseDouble(teamdata.getSingleTeamBasicInfo(name).getWinsNum().get(0).split(";")[1]);
		for(MatchPO po : matchdata.getMatchesAboutTeam(name)){
			vo.allshooting = vo.allshooting + po.getTeam1().getShooting();
			vo.allshootingHit = vo.allshootingHit + po.getTeam1().getShootingHit();
			vo.allthreePoint = vo.allthreePoint + po.getTeam1().getThreePoint();
			vo.allthreePointHits = vo.allthreePointHits + po.getTeam1().getThreePointHits();
			vo.allfreeThrow = vo.allfreeThrow + po.getTeam1().getFreeThrow();
			vo.allfreeThrowHit = vo.allfreeThrowHit + po.getTeam1().getFreeThrowHit();
		}
	}
	
	public TeamVO poTovo(TeamPO po){
		TeamVO vo = new TeamVO();
		vo.teamLogo = po.getTeamLogo();
		vo.fullName = po.getFullName();
		vo.abbName = po.getAbbName();
		vo.location = po.getLocation();
		vo.competionArea = po.getCompetionArea();
		vo.subArea = po.getSubArea();
		vo.homeGround = po.getHomeGround();
		vo.setupTime = po.getSetupTime();
		vo.gamesNum = po.getGamesNum();
		vo.winsNum = po.getWinsNum();
		vo.allshootingHit = Double.parseDouble(po.getShootingHit().get(0).split(";")[1]);
		vo.shootingHit = Double.parseDouble(po.getShootingHit().get(0).split(";")[2]);
		vo.allshooting = Double.parseDouble(po.getShooting().get(0).split(";")[1]);
		vo.shooting = Double.parseDouble(po.getShooting().get(0).split(";")[2]);
		vo.allthreePointHits = Double.parseDouble(po.getThreePointHits().get(0).split(";")[1]);
		vo.threePointHits = Double.parseDouble(po.getThreePointHits().get(0).split(";")[2]);
		vo.allthreePoint = Double.parseDouble(po.getThreePoint().get(0).split(";")[1]);
		vo.threePoint = Double.parseDouble(po.getThreePoint().get(0).split(";")[2]);
		vo.allfreeThrowHit = Double.parseDouble(po.getFreeThrowHit().get(0).split(";")[1]);
		vo.freeThrowHit = Double.parseDouble(po.getFreeThrowHit().get(0).split(";")[2]);
		vo.allfreeThrow = Double.parseDouble(po.getFreeThrow().get(0).split(";")[1]);
		vo.freeThrow = Double.parseDouble(po.getFreeThrow().get(0).split(";")[2]);
		vo.alloffensiveRebounds = Double.parseDouble(po.getOffensiveRebounds().get(0).split(";")[1]);
		vo.offensiveRebounds = Double.parseDouble(po.getOffensiveRebounds().get(0).split(";")[2]);
		vo.alldefensiveRebounds = Double.parseDouble(po.getDefensiveRebounds().get(0).split(";")[1]);
		vo.defensiveRebounds = Double.parseDouble(po.getDefensiveRebounds().get(0).split(";")[2]);
		vo.allopponentOffensiveRebounds = Double.parseDouble(po.getOpponentOffensiveRebounds().get(0).split(";")[1]);
		vo.opponentOffensiveRebounds = Double.parseDouble(po.getOpponentOffensiveRebounds().get(0).split(";")[2]);
		vo.allopponentDefensiveRebounds = Double.parseDouble(po.getOpponentDefensiveRebounds().get(0).split(";")[1]);
		vo.opponentDefensiveRebounds = Double.parseDouble(po.getOpponentDefensiveRebounds().get(0).split(";")[2]);
		vo.allrebounds = Double.parseDouble(po.getRebounds().get(0).split(";")[1]);
		vo.rebounds = Double.parseDouble(po.getRebounds().get(0).split(";")[2]);
		vo.allassists = Double.parseDouble(po.getAssists().get(0).split(";")[1]);
		vo.assists = Double.parseDouble(po.getAssists().get(0).split(";")[2]);
		vo.allsteal = Double.parseDouble(po.getSteals().get(0).split(";")[1]);
		vo.steal = Double.parseDouble(po.getSteals().get(0).split(";")[2]);
		vo.allcaps = Double.parseDouble(po.getCaps().get(0).split(";")[1]);
		vo.caps = Double.parseDouble(po.getCaps().get(0).split(";")[2]);
		vo.allturnovers = Double.parseDouble(po.getTurnovers().get(0).split(";")[1]);
		vo.turnovers = Double.parseDouble(po.getTurnovers().get(0).split(";")[2]);
		vo.allfouls = Double.parseDouble(po.getFouls().get(0).split(";")[1]);
		vo.fouls = Double.parseDouble(po.getFouls().get(0).split(";")[2]);
		vo.allscores = Double.parseDouble(po.getScores().get(0).split(";")[1]);
		vo.scores = Double.parseDouble(po.getScores().get(0).split(";")[2]);
		vo.allshootingHitRate = Double.parseDouble(po.getShootingHitRate().get(0).split(";")[1]);
		vo.shootingHitRate = Double.parseDouble(po.getShootingHitRate().get(0).split(";")[2]);
		vo.allthreePointHitRate = Double.parseDouble(po.getThreePointHitRate().get(0).split(";")[1]);
		vo.threePointHitRate = Double.parseDouble(po.getThreePointHitRate().get(0).split(";")[2]);
		vo.allfreeThrowHitRate = Double.parseDouble(po.getFreeThrowHitRate().get(0).split(";")[1]);
		vo.freeThrowHitRate = Double.parseDouble(po.getFreeThrowHitRate().get(0).split(";")[2]);
		vo.allattackRound = Double.parseDouble(po.getAttackRound().get(0).split(";")[1]);
		vo.attackRound = Double.parseDouble(po.getAttackRound().get(0).split(";")[2]);
		vo.allattackEfficiency = Double.parseDouble(po.getAttackEfficiency().get(0).split(";")[1]);
		vo.attackEfficiency = Double.parseDouble(po.getAttackEfficiency().get(0).split(";")[2]);
		vo.defensiveRebounds = 
	}
	
	
}

package businesslogic;

import java.util.ArrayList;

import po.TBasicInfoPO;
import po.TSeasonDataPO;
import vo.TeamBasicInfoVO;
import vo.TeamVO;
import data.GetTeamInfo;
import dataservice.TeamService;

public class TeamBL implements businesslogicservice.TeamBLService{
	private TeamService teamdata = null;
	
	public TeamBL(){
		teamdata = new GetTeamInfo();
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
		TBasicInfoPO po = teamdata.getSingleTBasicInfo(name);
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

	public ArrayList<TeamVO> getAllTeams() {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		for(TSeasonDataPO po : teamdata.getAllTSeasonData("13-14")){
			list.add(potovo(po));
		}
		return list;
	}
	
	public TeamVO potovo(TSeasonDataPO po){
		TeamVO vo = new TeamVO();
		vo.fullName = po.getFullName();
		vo.abbName = po.getAbbName();
		vo.gamesNum = po.getGamesNum();
		vo.winsNum = po.getWinsNum();
		vo.subArea = po.getSubArea();
		vo.allshooting = po.getAllshooting();
		vo.shooting = po.getShooting();
		vo.allshootingHit = po.getAllshootingHit();
		vo.shootingHit = po.getShootingHit();
		vo.allthreePoint = po.getAllthreePoint();
		vo.threePoint = po.getThreePoint();
		vo.allthreePointHits = po.getAllthreePointHits();
		vo.threePointHits = po.getThreePointHits();
		vo.allfreeThrow = po.getAllfreeThrow();
		vo.freeThrow = po.getFreeThrow();
		vo.allfreeThrowHit = po.getAllfreeThrowHit();
		vo.freeThrowHit = po.getFreeThrowHit();
		vo.alloffensiveRebounds = po.getAlloffensiveRebounds();
		vo.offensiveRebounds = po.getOffensiveRebounds();
		vo.alldefensiveRebounds = po.getAlldefensiveRebounds();
		vo.defensiveRebounds = po.getDefensiveRebounds();
		vo.allopponentOffensiveRebounds = po.getAllopponentOffensiveRebounds();
		vo.opponentOffensiveRebounds = po.getOpponentOffensiveRebounds();
		vo.allopponentDefensiveRebounds = po.getAllopponentDefensiveRebounds();
		vo.opponentDefensiveRebounds = po.getOpponentDefensiveRebounds();
		vo.allrebounds = po.getAllrebounds();
		vo.rebounds = po.getRebounds();
		vo.allassists = po.getAllassists();
		vo.assists = po.getAssists();
		vo.allsteal = po.getAllsteal();
		vo.steal = po.getSteal();
		vo.allcaps = po.getAllcaps();
		vo.caps = po.getCaps();
		vo.allturnovers = po.getAllturnovers();
		vo.turnovers = po.getTurnovers();
		vo.allfouls = po.getAllfouls();
		vo.fouls = po.getFouls();
		vo.allscores = po.getAllscores();
		vo.scores = po.getScores();
		vo.allshootingHitRate = po.getAllshootingHitRate();
		vo.shootingHitRate = po.getShootingHitRate();
		vo.allthreePointHitRate = po.getAllthreePointHitRate();
		vo.threePointHitRate = po.getThreePointHitRate();
		vo.allfreeThrowHitRate = po.getAllfreeThrowHitRate();
		vo.freeThrowHitRate = po.getFreeThrowHitRate();
		vo.winsRate = po.getWinsRate();
		vo.allattackRound = po.getAllattackRound();
		vo.attackRound = po.getAttackRound();
		vo.allattackEfficiency = po.getAllattackEfficiency();
		vo.attackEfficiency = po.getAttackEfficiency();
		vo.alldefenceEfficiency = po.getAlldefenceEfficiency();
		vo.defenceEfficiency = po.getDefenceEfficiency();
		vo.alloffensivereboundsEfficiency = po.getAlloffensivereboundsEfficiency();
		vo.offensivereboundsEfficiency = po.getOffensivereboundsEfficiency();
		vo.alldefensivereboundsEfficiency = po.getAlldefensivereboundsEfficiency();
		vo.defensivereboundsEfficiency = po.getDefensivereboundsEfficiency();
		vo.allstealEfficiency = po.getAllstealEfficiency();
		vo.stealEfficiency = po.getStealEfficiency();
		vo.allassistEfficiency = po.getAllassistEfficiency();
		vo.assistEfficiency = po.getAssistEfficiency();
		
		return vo;
	}

}

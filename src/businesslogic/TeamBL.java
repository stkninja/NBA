package businesslogic;

import java.util.ArrayList;

import po.MatchPO;
import po.TBasicInfoPO;
import po.TSeasonDataPO;
import vo.MatchVO;
import vo.TeamBasicInfoVO;
import vo.TeamVO;
import data.GetMatchInfo;
import data.GetTeamInfo;
import dataservice.MatchService;
import dataservice.TeamService;

public class TeamBL implements businesslogicservice.TeamBLService{
	private TeamService teamdata = null;
	private PlayerBL playerbl = null;
	private MatchService matchdata = null;
	
	public TeamBL(){
		teamdata = new GetTeamInfo();
		playerbl = new PlayerBL();
		matchdata = new GetMatchInfo();
	}
	
	public ArrayList<TeamVO> getTeams(String season,String subArea) {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		if(subArea.equals("All")){
			return getSeasonTeams(season);
		}
		else{
			for(TeamVO vo : getSeasonTeams(season)){
				if(vo.subArea.equals(subArea)){
					list.add(vo);
				}
			}
		}
		return list;
	}
	
	public ArrayList<TeamVO> getSeasonTeams(String season){
		ArrayList<TeamVO> list = new ArrayList<TeamVO>(); 
		for(TSeasonDataPO po : teamdata.getAllTSeasonData(season)){
			list.add(potovo(po));
		}
		return list;
	}

	public TeamBasicInfoVO getOneTeam(String name) {
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

	public TeamVO potovo(TSeasonDataPO po){
		TeamVO vo = new TeamVO();
		vo.fullName = po.getFullName();
		vo.abbName = po.getAbbName();
		vo.season = po.getSeason();
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

	public TeamVO getSeasonTeams(String season, String name) {
		TeamVO vo = new TeamVO();
		vo = potovo(teamdata.getOneTSeasonDataPO(name, season));
		return vo;
    }

	public ArrayList<MatchVO> getSeasonMatches(String season, String name) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getAllMatchesAboutTeam(name, season)){
			list.add(playerbl.potovo(po));
		}
		return list;
	}

	public ArrayList<MatchVO> getLastFiveMatchesSpecific(String name) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getLastFiveMatchesAboutTeam(name)){
			list.add(playerbl.potovo(po));
		}
		return list;
	}

	public ArrayList<TeamVO> getLastFiveMatches(String name) {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		for(MatchVO vo : getLastFiveMatchesSpecific(name)){
			TeamVO teamvo = new TeamVO();
			if(vo.team1.abbName.equals(name)){
				teamvo.abbName = name;
				teamvo.season = vo.season;
				teamvo.shootingHit = vo.team1.getShootingHit();
				teamvo.shooting = vo.team1.getShooting();
				teamvo.threePointHits = vo.team1.getThreePointHits();
				teamvo.threePoint = vo.team1.getThreePoint();
				teamvo.freeThrowHit = vo.team1.getFreeThrowHit();
				teamvo.freeThrow = vo.team1.getFreeThrow();
				teamvo.offensiveRebounds = vo.team1.getOffensiveRebounds();
				teamvo.defensiveRebounds = vo.team1.getDefensiveRebounds();
				teamvo.rebounds = vo.team1.getRebounds();
				teamvo.assists = vo.team1.getAssists();
				teamvo.steal = vo.team1.getSteals();
				teamvo.caps = vo.team1.getCaps();
				teamvo.turnovers = vo.team1.getTurnovers();
				teamvo.fouls = vo.team1.getFouls();
			}
			else{
				teamvo.abbName = name;
				teamvo.season = vo.season;
				teamvo.shootingHit = vo.team2.getShootingHit();
				teamvo.shooting = vo.team2.getShooting();
				teamvo.threePointHits = vo.team2.getThreePointHits();
				teamvo.threePoint = vo.team2.getThreePoint();
				teamvo.freeThrowHit = vo.team2.getFreeThrowHit();
				teamvo.freeThrow = vo.team2.getFreeThrow();
				teamvo.offensiveRebounds = vo.team2.getOffensiveRebounds();
				teamvo.defensiveRebounds = vo.team2.getDefensiveRebounds();
				teamvo.rebounds = vo.team2.getRebounds();
				teamvo.assists = vo.team2.getAssists();
				teamvo.steal = vo.team2.getSteals();
				teamvo.caps = vo.team2.getCaps();
				teamvo.turnovers = vo.team2.getTurnovers();
				teamvo.fouls = vo.team2.getFouls();
			}
			
			list.add(teamvo);
		}
		
		return list;
	}

	public ArrayList<TeamVO> getSeasonEachMatch(String season, String name) {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		for(MatchVO vo : getSeasonMatches(season,name)){
			TeamVO teamvo = new TeamVO();
			if(vo.team1.abbName.equals(name)){
				teamvo.abbName = name;
				teamvo.season = season;
				teamvo.shootingHit = vo.team1.getShootingHit();
				teamvo.shooting = vo.team1.getShooting();
				teamvo.threePointHits = vo.team1.getThreePointHits();
				teamvo.threePoint = vo.team1.getThreePoint();
				teamvo.freeThrowHit = vo.team1.getFreeThrowHit();
				teamvo.freeThrow = vo.team1.getFreeThrow();
				teamvo.offensiveRebounds = vo.team1.getOffensiveRebounds();
				teamvo.defensiveRebounds = vo.team1.getDefensiveRebounds();
				teamvo.rebounds = vo.team1.getRebounds();
				teamvo.assists = vo.team1.getAssists();
				teamvo.steal = vo.team1.getSteals();
				teamvo.caps = vo.team1.getCaps();
				teamvo.turnovers = vo.team1.getTurnovers();
				teamvo.fouls = vo.team1.getFouls();
			}
			else{
				teamvo.abbName = name;
				teamvo.season = season;
				teamvo.shootingHit = vo.team2.getShootingHit();
				teamvo.shooting = vo.team2.getShooting();
				teamvo.threePointHits = vo.team2.getThreePointHits();
				teamvo.threePoint = vo.team2.getThreePoint();
				teamvo.freeThrowHit = vo.team2.getFreeThrowHit();
				teamvo.freeThrow = vo.team2.getFreeThrow();
				teamvo.offensiveRebounds = vo.team2.getOffensiveRebounds();
				teamvo.defensiveRebounds = vo.team2.getDefensiveRebounds();
				teamvo.rebounds = vo.team2.getRebounds();
				teamvo.assists = vo.team2.getAssists();
				teamvo.steal = vo.team2.getSteals();
				teamvo.caps = vo.team2.getCaps();
				teamvo.turnovers = vo.team2.getTurnovers();
				teamvo.fouls = vo.team2.getFouls();
			}
			
			list.add(teamvo);
		}
		
		return list;
	}
	
	private TeamVO findMax(ArrayList<TeamVO> templist,String filter){
		TeamVO max = new TeamVO();
		max = templist.get(0);
		for(int i = 0;i < templist.size();i ++){
			if(filter.equals("得分")){
				if(max.scores < templist.get(i).scores){
					max = templist.get(i);
				}
			}
			else if(filter.equals("篮板")){
				if(max.rebounds < templist.get(i).rebounds){
					max = templist.get(i);
				}
			}
			else if(filter.equals("助攻")){
				if(max.assists < templist.get(i).assists){
					max = templist.get(i);
				}
			}
			else if(filter.equals("盖帽")){
				if(max.caps < templist.get(i).caps){
					max = templist.get(i);
				}
			}
			else if(filter.equals("抢断")){
				if(max.steal < templist.get(i).steal){
					max = templist.get(i);
				}
			}
			else if(filter.equals("三分命中率")){
				if(max.allthreePointHitRate < templist.get(i).allthreePointHitRate){
					max = templist.get(i);
				}
			}
			else if(filter.equals("投篮命中率")){
				if(max.allshootingHitRate < templist.get(i).allshootingHitRate){
					max = templist.get(i);
				}
			}
			else if(filter.equals("罚球命中率")){
				if(max.allfreeThrowHitRate < templist.get(i).allfreeThrowHitRate){
					max = templist.get(i);
				}
			}
		}
		return max;
	}
	
	private ArrayList<TeamVO> sort(ArrayList<TeamVO> templist,String filter){
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		TeamVO vo1 = findMax(templist,filter);
		list.add(vo1);
		templist.remove(vo1);
		if(templist.size() == 0){
			return list;
		}
		TeamVO vo2 = findMax(templist,filter);
		list.add(vo2);
		templist.remove(vo2);
		if(templist.size() == 0){
			return list;
		}
		TeamVO vo3 = findMax(templist,filter);
		list.add(vo3);
		templist.remove(vo3);
		if(templist.size() == 0){
			return list;
		}
		TeamVO vo4 = findMax(templist,filter);
		list.add(vo4);
		templist.remove(vo4);
		if(templist.size() == 0){
			return list;
		}
		TeamVO vo5 = findMax(templist,filter);
		list.add(vo5);
		templist.remove(vo5);
		return list;
	}

	public ArrayList<TeamVO> getSeasonTopFiveTeams(String season, String filter) {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		ArrayList<TeamVO> templist = new ArrayList<TeamVO>();
		for(TSeasonDataPO po : teamdata.getAllTSeasonData(season)){
			templist.add(potovo(po));
		}
		list = sort(templist,filter);
		return list;
	}
}

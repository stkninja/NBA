package businesslogic;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.PlayerBasicInfoPO;
import data.GetMatchesInfo;
import data.GetPlayersInfo;
import dataservice.IMatch;
import dataservice.IPlayer;
import vo.PlayerBasicInfoVO;
import vo.PlayerVO;
import vo.TeamVO;

public class PlayerBL implements businesslogicservice.PlayerBLService{
	private IPlayer playerdata = null;
	private IMatch matchdata = null;
	private TeamBL teambl = null;
	
	public PlayerBL(){
		playerdata = new GetPlayersInfo();
		matchdata = new GetMatchesInfo();
		teambl = new TeamBL();
	}
	
	@Override
	public ArrayList<PlayerVO> getPlayers(String subArea, String position,
			String team) {
		// TODO Auto-generated method stub
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		if(subArea.equals("All")){
			if(position.equals("All")){
				if(team.equals("All")){
					return getAllPlayers();
				}
				else{
					for(PlayerVO vo : getAllPlayers()){
						if(vo.team.equals(team)){
							list.add(vo);
						}
					}
				}
			}
			else{
				if(team.equals("All")){
					for(PlayerVO vo : getAllPlayers()){
						if(vo.position.indexOf(position) >= 0){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getAllPlayers()){
						if(vo.team.equals(team) && vo.position.indexOf(position) >= 0){
							list.add(vo);
						}
					}
				}
			}
		}
		else{
			if(position.equals("All")){
				if(team.equals("All")){
					for(PlayerVO vo : getAllPlayers()){
						if(vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getAllPlayers()){
						if(vo.team.equals(team) && vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
			}
			else{
				if(team.equals("All")){
					for(PlayerVO vo : getAllPlayers()){
						if(vo.position.indexOf(position) >= 0 && vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getAllPlayers()){
						if(vo.team.equals(team) && vo.position.indexOf(position) >= 0 && vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public PlayerBasicInfoVO getOnePlayer(String name) {
		// TODO Auto-generated method stub
		PlayerBasicInfoVO vo = new PlayerBasicInfoVO();
		PlayerBasicInfoPO pp = playerdata.getSinglePlayerBasicInfo(name);
		vo.name = pp.getName();
		vo.number = pp.getNumber();
		vo.height = pp.getHeight();
		vo.weight = pp.getWeight();
		vo.birth = pp.getBirth();
		vo.age = pp.getAge();
		vo.exp = pp.getExp();
		vo.school = pp.getSchool();
		vo.portrait = pp.getPortrait();
		vo.action = pp.getAction();
		vo.position = pp.getPosition();
		return vo;
	}

	@Override
	public ArrayList<PlayerVO> getAllPlayers() {
		// TODO Auto-generated method stub
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		for(String name : playerdata.getAllPlayersName()){
			list.add(findPlayer(name));
		}
		return list;
	}
	
	public PlayerVO findPlayer(String name){
		PlayerVO vo = new PlayerVO();
		PlayerBasicInfoPO pp = playerdata.getSinglePlayerBasicInfo(name);
		vo.name = name;
		vo.team = playerdata.getTeam(name);
		TeamVO teamvo = teambl.findTeam(vo.team);
		vo.position = pp.getPosition();
		vo.subArea = playerdata.getSubArea(name);
		vo.gameplay = (int)playerdata.getPlayerGamePlay(name, "13-14");
		vo.gamestart = (int)playerdata.getPlayerGameStart(name, "13-14");
		double allopponentthreepoint = 0;
		for(MatchPO po : matchdata.getMatchesAboutTeam(vo.team)){
			allopponentthreepoint = allopponentthreepoint + po.getTeam2().getThreePoint();
		}
		
		double fieldgoalpercentsum = 0,threepointpercentsum = 0,freethrowpercentsum = 0,efficiencysum = 0,gmscsum = 0,
		realshootpercentsum = 0,shootefficiencysum = 0,reboundratesum = 0,offensivereboundratesum = 0,defensivereboundratesum = 0,assistratesum = 0,stealratesum = 0,blockratesum = 0,errorratesum = 0,usagesum = 0;
		
		if(matchdata.getMatchesAboutPlayer(name).size() == 0){
			return vo;
		}
		
		for(MatchPO po : matchdata.getMatchesAboutPlayer(name)){
			for(MatchPlayerDataPO playerpo : po.getTeam1().getTeamPlayers()){
				if(playerpo.getName().equals(name)){
					vo.allrebound = vo.allrebound + playerpo.getRebound();
					vo.alloffensiverebound = vo.alloffensiverebound + playerpo.getOffensiveRebounds();
					vo.alldefensiverebound = vo.alldefensiverebound + playerpo.getDefensiveRebounds();
					vo.allassist = vo.allassist + playerpo.getAssist();
					vo.allminute = vo.allminute + playerpo.getMinute();
					vo.alloffense = vo.alloffense + playerpo.getOffense();
					vo.alldefence = vo.alldefence + playerpo.getDefence();
					vo.allsteal = vo.allsteal + playerpo.getSteal();
					vo.allblock = vo.allblock + playerpo.getBlock();
					vo.allerror = vo.allerror + playerpo.getError();
					vo.allfoul = vo.allfoul + playerpo.getFoul();
					vo.allpoint = vo.allpoint + playerpo.getPoint();
					vo.allshoot = vo.allshoot + playerpo.getShoot();
					vo.allshootmade = vo.allshootmade + playerpo.getShootmade();
					vo.allthreepoint = vo.allthreepoint + playerpo.getThreepoint();
					vo.allthreepointmade = vo.allthreepointmade + playerpo.getThreepointmade();
					vo.allfreethrow = vo.allfreethrow + playerpo.getFreethrow();
					vo.allfreethrowmade = vo.allfreethrowmade + playerpo.getFreethrowmade();
					vo.doubledouble = vo.doubledouble + playerpo.getDoubledouble();
					if(playerpo.getShoot() != 0){
						fieldgoalpercentsum = fieldgoalpercentsum + playerpo.getShootmade() / playerpo.getShoot();
					}
					if(playerpo.getThreepoint() != 0){
						threepointpercentsum = threepointpercentsum + playerpo.getThreepointmade() / playerpo.getThreepoint();
					}
					if(playerpo.getFreethrow() != 0){
						freethrowpercentsum = freethrowpercentsum + playerpo.getFreethrowmade() / playerpo.getFreethrow();
					}
					
					efficiencysum = efficiencysum + (playerpo.getPoint() + playerpo.getRebound() + playerpo.getAssist() + playerpo.getSteal() + playerpo.getBlock()) - (playerpo.getShoot() - playerpo.getShootmade()) - (playerpo.getFreethrow() - playerpo.getFreethrowmade()) - playerpo.getError();
					gmscsum = gmscsum + playerpo.getPoint() + 0.4 * playerpo.getShootmade() - 0.7 * playerpo.getShoot() - 0.4 * (playerpo.getFreethrow() - playerpo.getFreethrowmade()) + 0.7 * playerpo.getOffensiveRebounds() + 0.3 * playerpo.getDefensiveRebounds() + playerpo.getSteal() + 0.7 * 
							playerpo.getAssist() + 0.7 * playerpo.getBlock() - 0.4 * playerpo.getFoul() - playerpo.getError();
					
					if(playerpo.getShoot() + 0.44 * playerpo.getFreethrow() != 0){
						realshootpercentsum = realshootpercentsum + playerpo.getPoint() / (2 * (playerpo.getShoot() + 0.44 * playerpo.getFreethrow()));
					}
					
					shootefficiencysum = shootefficiencysum + (playerpo.getShootmade() + 0.5 * playerpo.getThreepointmade()) / playerpo.getShoot();
					reboundratesum = reboundratesum + playerpo.getRebound() * 48 / playerpo.getMinute() / (po.getTeam1().getRebounds() + po.getTeam2().getRebounds());
					offensivereboundratesum = offensivereboundratesum + playerpo.getOffensiveRebounds() * 48 / playerpo.getMinute() / (po.getTeam1().getOffensiveRebounds() + po.getTeam2().getOffensiveRebounds());
					defensivereboundratesum = defensivereboundratesum + playerpo.getDefensiveRebounds() * 48 / playerpo.getMinute() / (po.getTeam1().getDefensiveRebounds() + po.getTeam2().getDefensiveRebounds());
					assistratesum = assistratesum + playerpo.getAssist() / (playerpo.getMinute() / 48 * po.getTeam1().getShootingHit() - playerpo.getShootmade());
					stealratesum = stealratesum + playerpo.getSteal() * 48 / playerpo.getMinute() / po.getTeam2().getOffensiveRebounds();
					blockratesum = blockratesum + playerpo.getBlock() * 48 / playerpo.getMinute() / po.getTeam2().getThreePoint();
					
					if(playerpo.getShoot() - playerpo.getThreepoint() + 0.44 * playerpo.getFreethrow() + playerpo.getError() != 0){
						errorratesum = errorratesum + playerpo.getError() / (playerpo.getShoot() - playerpo.getThreepoint() + 0.44 * playerpo.getFreethrow() + playerpo.getError());
					}
					
					usagesum = usagesum + (playerpo.getShoot() + 0.44 * playerpo.getFreethrow() + playerpo.getError()) * 48 / playerpo.getMinute() / (po.getTeam1().getShooting() + 0.44 * po.getTeam1().getFreeThrow() + po.getTeam1().getTurnovers());
				}
			}
		}
		
		vo.allfieldgoalpercent = Math.ceil(vo.allshootmade / vo.allshoot * 100)/ 100;
		
	    if(vo.allthreepoint == 0){
	    	vo.allthreepointpercent = 0;
	    }
	    else{
	    	vo.allthreepointpercent = Math.ceil(vo.allthreepointmade / vo.allthreepoint * 100)/ 100;
	    }
		
	    if(vo.allfreethrow == 0){
	    	vo.allfreethrowpercent = 0;
	    }
	    else{
	    	vo.allfreethrowpercent = Math.ceil(vo.allfreethrowmade / vo.allfreethrow * 100)/ 100;
	    }
		
		vo.allefficiency = Math.ceil(((vo.allpoint + vo.allrebound + vo.allassist + vo.allsteal + vo.allblock) - (vo.allshoot - vo.allshootmade) - (vo.allfreethrow - vo.allfreethrowmade) -vo.allerror) * 100)/ 100;
		
		vo.allgmsc = Math.ceil((vo.allpoint + 0.4 * vo.allshootmade - 0.7 * vo.allshoot - 0.4 * (vo.allfreethrow - vo.allfreethrowmade) + 0.7 * vo.alloffensiverebound + 0.3 * vo.alldefensiverebound + vo.allsteal + 0.7 * vo.allassist + 0.7 * vo.allblock - 0.4 * vo.allfoul - vo.allerror) * 100)/ 100;
				
		vo.allrealshootpercent = Math.ceil(vo.allpoint / (2 * (vo.allshoot + 0.44 * vo.allfreethrow)) * 100)/ 100;
		
		vo.allshootefficiency = Math.ceil((vo.allshootmade + 0.5 * vo.allthreepointmade) / vo.allshoot * 100)/ 100;
		
		vo.alloffensivereboundrate = Math.ceil(vo.alloffensiverebound * 48 * vo.gameplay / vo.allminute / (teamvo.alloffensiveRebounds + teamvo.allopponentOffensiveRebounds) * 100)/ 100;
		vo.alldefensivereboundrate = Math.ceil(vo.alldefensiverebound * 48 * vo.gameplay / vo.allminute / (teamvo.alldefensiveRebounds + teamvo.allopponentDefensiveRebounds) * 100)/ 100;
		vo.allreboundrate = Math.ceil(vo.allrebound * 48 * vo.gameplay / vo.allminute / (teamvo.allrebounds + teamvo.allopponentDefensiveRebounds + teamvo.allopponentOffensiveRebounds) * 100)/ 100;
		vo.allassistrate = Math.ceil(vo.allassist / (vo.allminute / (48 * vo.gameplay) * teamvo.allshootingHit - vo.allshootmade) * 100)/ 100;
		
		vo.allstealrate = Math.ceil(vo.allsteal * (48 * vo.gameplay) / vo.allminute / teambl.findTeam(vo.team).allopponentOffensiveRebounds * 100)/ 100;
		vo.allblockrate = Math.ceil(vo.allblock * (48 * vo.gameplay) / vo.allminute / allopponentthreepoint * 100)/ 100;
		
		vo.allerrorrate = Math.ceil(vo.allerror / (vo.allshoot - vo.allthreepoint + 0.44 * vo.allfreethrow + vo.allerror) * 100)/ 100;
		
		vo.allusage = Math.ceil((vo.allshoot + 0.44 * vo.allfreethrow + vo.allerror) * (48 * vo.gameplay) / vo.allminute  / (teamvo.allshooting + 0.44 * teamvo.allfreeThrow + teamvo.allturnovers) * 100)/ 100;
		
		vo.rebound = Math.ceil(vo.allrebound / vo.gameplay * 100) / 100;
		vo.offensiverebound = Math.ceil(vo.alloffensiverebound / vo.gameplay * 100) / 100;
		vo.defensiverebound = Math.ceil((vo.allrebound / vo.gameplay - vo.alloffensiverebound / vo.gameplay) * 100) / 100;
		vo.assist = Math.ceil(vo.allassist / vo.gameplay * 100)/ 100;
		vo.minute = Math.ceil(vo.allminute / vo.gameplay * 100)/ 100;
		vo.allminute = Math.ceil(vo.allminute * 100)/ 100;
		vo.offense = Math.ceil(vo.alloffense / vo.gameplay * 100)/ 100;
		vo.defence = Math.ceil(vo.alldefence / vo.gameplay * 100)/ 100;
		vo.steal = Math.ceil(vo.allsteal / vo.gameplay * 100)/ 100;
		vo.block = Math.ceil(vo.allblock / vo.gameplay * 100)/ 100;
		vo.error = Math.ceil(vo.allerror / vo.gameplay * 100)/ 100;
		vo.foul = Math.ceil(vo.allfoul / vo.gameplay * 100)/ 100;
		vo.point = Math.ceil(vo.allpoint / vo.gameplay * 100)/ 100;
		vo.shoot = Math.ceil(vo.allshoot / vo.gameplay * 100)/ 100;
		vo.shootmade = Math.ceil(vo.allshootmade / vo.gameplay * 100)/ 100;
		vo.threepoint = Math.ceil(vo.allthreepoint / vo.gameplay * 100)/ 100;
		vo.threepointmade = Math.ceil(vo.allthreepointmade / vo.gameplay * 100)/ 100;
		vo.freethrow = Math.ceil(vo.allfreethrow / vo.gameplay * 100)/ 100;
		vo.freethrowmade = Math.ceil(vo.allfreethrowmade / vo.gameplay * 100)/ 100;
		
		vo.fieldgoalpercent = Math.ceil(fieldgoalpercentsum / vo.gameplay * 100)/ 100;
		vo.threepointpercent = Math.ceil(threepointpercentsum / vo.gameplay * 100) / 100;
		vo.freethrowpercent = Math.ceil(freethrowpercentsum / vo.gameplay * 100)/ 100;
		vo.efficiency = Math.ceil(efficiencysum / vo.gameplay * 100)/ 100;
		vo.gmsc = Math.ceil(gmscsum / vo.gameplay * 100)/ 100;
		vo.realshootpercent = Math.ceil(realshootpercentsum / vo.gameplay * 100)/ 100;
		vo.shootefficiency = Math.ceil(shootefficiencysum / vo.gameplay * 100)/ 100;
		vo.reboundrate = Math.ceil(reboundratesum / vo.gameplay * 100)/ 100;
		vo.offensivereboundrate = Math.ceil(offensivereboundratesum / vo.gameplay * 100)/ 100;
		vo.defensivereboundrate = Math.ceil(defensivereboundratesum / vo.gameplay * 100)/ 100;
		vo.assistrate = Math.ceil(assistratesum / vo.gameplay * 100)/ 100;
		vo.stealrate = Math.ceil(stealratesum / vo.gameplay * 100)/ 100;
		vo.blockrate = Math.ceil(blockratesum / vo.gameplay * 100)/ 100;
		vo.errorrate = Math.ceil(errorratesum / vo.gameplay * 100)/ 100;
		vo.usage = Math.ceil(usagesum / vo.gameplay * 100)/ 100;
		
		return vo;
	}
	
}

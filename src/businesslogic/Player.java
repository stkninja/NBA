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

public class Player implements businesslogicservice.Player{
	private IPlayer playerdata = null;
	private IMatch matchdata = null;
	private Team team = null;
	
	public Player(){
		playerdata = new GetPlayersInfo();
		matchdata = new GetMatchesInfo();
		team = new Team();
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
						if(vo.position.equals(position)){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getAllPlayers()){
						if(vo.team.equals(team) && vo.position.equals(position)){
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
						if(vo.position.equals(position) && vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getAllPlayers()){
						if(vo.team.equals(team) && vo.position.equals(position) && vo.subArea.equals(subArea)){
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
		vo.team = pp.getTeam();
		vo.position = pp.getPosition();
		vo.subArea = pp.getSubArea();
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
		vo.team = pp.getTeam();
		TeamVO teamvo = team.findTeam(vo.team);
		vo.position = pp.getPosition();
		vo.subArea = pp.getSubArea();
		vo.gameplay = Integer.parseInt(pp.getGameplay().get(0).split(";")[1]);
		vo.gamestart = Integer.parseInt(pp.getGamestart().get(0).split(";")[1]);
		double allopponentthreepoint = 0;
		for(MatchPO po : matchdata.getMatchesAboutTeam(vo.team)){
			allopponentthreepoint = allopponentthreepoint + po.getTeam2().getThreePoint();
		}
		
		double fieldgoalpercentsum = 0,threepointpercentsum = 0,freethrowpercentsum = 0,efficiencysum = 0,gmscsum = 0,
		realshootpercentsum = 0,shootefficiencysum = 0,reboundratesum = 0,assistratesum = 0,blockratesum = 0,errorratesum = 0,usagesum = 0;
		
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
					vo.alldoubledouble = vo.alldoubledouble + playerpo.getDoubledouble();
					fieldgoalpercentsum = fieldgoalpercentsum + playerpo.getShootmade() / playerpo.getShoot();
					threepointpercentsum = threepointpercentsum + playerpo.getThreepointmade() / playerpo.getThreepoint();
					freethrowpercentsum = freethrowpercentsum + playerpo.getFreethrowmade() / playerpo.getFreethrow();
					efficiencysum = efficiencysum + (playerpo.getPoint() + playerpo.getRebound() + playerpo.getAssist() + playerpo.getSteal() + playerpo.getBlock()) - (playerpo.getShoot() - playerpo.getShootmade()) - (playerpo.getFreethrow() - playerpo.getFreethrowmade()) - playerpo.getError();
					gmscsum = gmscsum + playerpo.getPoint() + 0.4 * playerpo.getShootmade() - 0.7 * playerpo.getShoot() - 0.4 * (playerpo.getFreethrow() - playerpo.getFreethrowmade()) + 0.7 * playerpo.getOffensiveRebounds() + 0.3 * playerpo.getDefensiveRebounds() + playerpo.getSteal() + 0.7 * 
							playerpo.getAssist() + 0.7 * playerpo.getBlock() - 0.4 * playerpo.getFoul() - playerpo.getError();
					realshootpercentsum = realshootpercentsum + playerpo.getPoint() / (2 * (playerpo.getShoot() + 0.44 * playerpo.getFreethrow()));
					shootefficiencysum = shootefficiencysum + (playerpo.getShootmade() + 0.5 * playerpo.getThreepointmade()) / playerpo.getShoot();
					reboundratesum = reboundratesum + playerpo.getRebound() * 48 / playerpo.getMinute() / (po.getTeam1().getRebounds() + po.getTeam2().getRebounds());
					assistratesum = assistratesum + playerpo.getAssist() / (playerpo.getMinute() / 48 * po.getTeam1().getShootingHit() - playerpo.getShootmade());
					blockratesum = blockratesum + playerpo.getBlock() * 48 / playerpo.getMinute() / po.getTeam2().getThreePoint();
					errorratesum = errorratesum + playerpo.getError() / (playerpo.getShoot() - playerpo.getThreepoint() + 0.44 * playerpo.getFreethrow() + playerpo.getError());
					usagesum = usagesum + (playerpo.getShoot() + 0.44 * playerpo.getFreethrow() + playerpo.getError()) * 48 / playerpo.getMinute() / (po.getTeam1().getShooting() + 0.44 * po.getTeam1().getFreeThrow() + po.getTeam1().getTurnovers());
				}
			}
		}
		vo.allfieldgoalpercent = vo.allshootmade / vo.allshoot;
	
		vo.allthreepointpercent = vo.allthreepointmade / vo.allthreepoint;
		
		vo.allfreethrowpercent = vo.allfreethrowmade / vo.allfreethrow;
		
		vo.allefficiency = (vo.allpoint + vo.allrebound + vo.allassist + vo.allsteal + vo.allblock) - (vo.allshoot - vo.allshootmade) - (vo.allfreethrow - vo.allfreethrowmade) -vo.allerror;
		
		vo.allgmsc = vo.allpoint + 0.4 * vo.allshootmade - 0.7 * vo.allshoot - 0.4 * (vo.allfreethrow - vo.allfreethrowmade) + 0.7 * vo.alloffensiverebound + 0.3 * vo.alldefensiverebound + vo.allsteal + 0.7 * vo.allassist + 0.7 * vo.allblock - 0.4 * vo.allfoul - vo.allerror;
				
		vo.allrealshootpercent = vo.allpoint / (2 * (vo.allshoot + 0.44 * vo.allfreethrow));
		
		vo.allshootefficiency = (vo.allshootmade + 0.5 * vo.allthreepointmade) / vo.allshoot;
		
		vo.alloffensivereboundrate = vo.alloffensiverebound * 48 * vo.gameplay / vo.allminute / (teamvo.alloffensiveRebounds + teamvo.allopponentOffensiveRebounds);
		vo.alldefensivereboundrate = vo.alldefensiverebound * 48 * vo.gameplay / vo.allminute / (teamvo.alldefensiveRebounds + teamvo.allopponentDefensiveRebounds);
		vo.allreboundrate = vo.allrebound * 48 * vo.gameplay / vo.allminute / (teamvo.allrebounds + teamvo.allopponentDefensiveRebounds + teamvo.allopponentOffensiveRebounds);
		vo.allassistrate = vo.allassist / (vo.allminute / (48 * vo.gameplay) * teamvo.allshootingHit - vo.allshootmade);
		
//		vo.allstealrate = 
		vo.allblockrate = vo.allblock * (48 * vo.gameplay) / vo.allminute / allopponentthreepoint;
		
		vo.allerrorrate = vo.allerror / (vo.allshoot - vo.allthreepoint + 0.44 * vo.allfreethrow + vo.allerror);
		
		vo.allusage = (vo.allshoot + 0.44 * vo.allfreethrow + vo.allerror) * (48 * vo.gameplay) / vo.allminute  / (teamvo.allshooting + 0.44 * teamvo.allfreeThrow + teamvo.allturnovers);
		
		vo.rebound = vo.allrebound / vo.gameplay;
		vo.assist = vo.allassist / vo.gameplay;
		vo.minute = vo.allminute / vo.gameplay;
		vo.offense = 
//		vo.defence = 
		vo.steal = vo.allsteal / vo.gameplay;
		vo.block = vo.allblock / vo.gameplay;
		vo.error = vo.allerror / vo.gameplay;
		vo.foul = vo.allfoul / vo.gameplay;
		vo.point = vo.allpoint / vo.gameplay;
		vo.shoot = vo.allshoot / vo.gameplay;
		vo.shootmade = vo.allshootmade / vo.gameplay;
		vo.threepoint = vo.allthreepoint / vo.gameplay;
		vo.threepointmade = vo.allthreepointmade / vo.gameplay;
		vo.freethrow = vo.allfreethrow / vo.gameplay;
		vo.freethrowmade = vo.allfreethrowmade / vo.gameplay;
		vo.doubleDouble = vo.alldoubledouble / vo.gameplay;
		vo.fieldgoalpercent = fieldgoalpercentsum / vo.gameplay;
		vo.threepointpercent = threepointpercentsum / vo.gameplay;
		vo.freethrowpercent = freethrowpercentsum / vo.gameplay;
		vo.efficiency = efficiencysum / vo.gameplay;
		vo.gmsc = gmscsum / vo.gameplay;
		vo.realshootpercent = realshootpercentsum / vo.gameplay;
		vo.shootefficiency = shootefficiencysum / vo.gameplay;
		vo.reboundrate = reboundratesum / vo.gameplay;
		vo.assistrate = assistratesum / vo.gameplay;
		vo.blockrate = blockratesum / vo.gameplay;
		vo.errorrate = errorratesum / vo.gameplay;
		vo.usage = usagesum / vo.gameplay;
		
		return vo;
	}
	
}

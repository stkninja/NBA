package businesslogic;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.MatchTeamDataPO;
import po.PlayerBasicInfoPO;
import data.GetMatchesInfo;
import data.GetPlayersInfo;
import data.GetTeamsInfo;
import dataservice.IMatch;
import dataservice.IPlayer;
import dataservice.ITeam;
import vo.PlayerVO;

public class Player implements businesslogicservice.Player{
	private IPlayer playerdata = null;
	private ITeam teamdata = null;
	private IMatch matchdata = null;
	
	public Player(){
		playerdata = new GetPlayersInfo();
		teamdata = new GetTeamsInfo();
		matchdata = new GetMatchesInfo();
	}
	
	@Override
	public ArrayList<PlayerVO> getPlayers(String subArea, String position,
			String team) {
		// TODO Auto-generated method stub
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		for(PlayerPO po : data.getAllPlayersInfo()){
			if(po.getSubArea().equals(subArea) && po.getPosition().equals(position) ){
				list.add(poTovo(po));
			}
		}
		return list;
	}

	@Override
	public PlayerVO getOnePlayer(String name, String team) {
		// TODO Auto-generated method stub
		PlayerPO poo = new PlayerPO();
		for(PlayerPO po : data.getAllPlayersInfo()){
			if(po.getName().equals(name) && po.getTeam().equals(team)){
				poo = po;
				break;
			}
		}
		return poTovo(poo);
	}

	@Override
	public ArrayList<PlayerVO> getAllPlayers() {
		// TODO Auto-generated method stub
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		for(PlayerPO po : data.getAllPlayersInfo()){
			list.add(poTovo(po));
		}
		return list;
	}
	
	
	public PlayerVO findPlayer(String name){
		PlayerVO vo = new PlayerVO();
		vo.position = playerdata.getSinglePlayerBasicInfo(name).getPosition();
		vo.subArea = playerdata.getSinglePlayerBasicInfo(name).getSubArea();
		vo.gameplay = Integer.parseInt(playerdata.getSinglePlayerBasicInfo(name).getGameplay().get(0).split(";")[1]);
		vo.gamestart = Integer.parseInt(playerdata.getSinglePlayerBasicInfo(name).getGamestart().get(0).split(";")[1]);
		double allteamrebound = 0;
		double allteamshoot = 0;
		double allteamshootmade = 0;
		double allteamfreethrow = 0;
		double allteamerror = 0;
		double teamgamesum = Double.parseDouble(teamdata.getSingleTeamBasicInfo(team).getGamesNum().get(0).split(";")[1]);
		double allopponentrebonud = 0;
		double allopponentthreepoint = 0;
		for(MatchPO po : matchdata.getMatchesAboutTeam(team)){
			allteamrebound = allteamrebound + po.getTeam1().getRebounds();
			allteamshoot = allteamshoot + po.getTeam1().getShooting();
			allteamshootmade = allteamshootmade + po.getTeam1().getShootingHit();
			allteamfreethrow = allteamfreethrow + po.getTeam1().getFreeThrow();
			allteamerror = allteamerror + po.getTeam1().getTurnovers();
			allopponentrebonud = allopponentrebonud + po.getTeam2().getRebounds();
			allopponentthreepoint = allopponentthreepoint + po.getTeam2().getThreePoint();
		}
		
		double fieldgoalpercentsum = 0,threepointpercentsum = 0,freethrowpercentsum = 0,efficiencysum = 0,gmscsum = 0,
		realshootpercentsum = 0,shootefficiencysum = 0,reboundratesum = 0,assistratesum = 0,blockratesum = 0,errorratesum = 0,usagesum = 0;
		
		for(MatchPO po : matchdata.getMatchesAboutPlayer(name)){
			for(MatchPlayerDataPO playerpo : po.getTeam1Players()){
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
					vo.allfieldgoalpercent = vo.allshootmade / vo.allshoot;
					fieldgoalpercentsum = fieldgoalpercentsum + playerpo.getShootmade() / playerpo.getShoot();
					vo.allthreepointpercent = vo.allthreepointmade / vo.allthreepoint;
					threepointpercentsum = threepointpercentsum + playerpo.getThreepointmade() / playerpo.getThreepoint();
					vo.allfreethrowpercent = vo.allfreethrowmade / vo.allfreethrow;
					freethrowpercentsum = freethrowpercentsum + playerpo.getFreethrowmade() / playerpo.getFreethrow();
					vo.allefficiency = (vo.allpoint + vo.allrebound + vo.allassist + vo.allsteal + vo.allblock) - (vo.allshoot - vo.allshootmade) - (vo.allfreethrow - vo.allfreethrowmade) -vo.allerror;
					efficiencysum = efficiencysum + (playerpo.getPoint() + playerpo.getRebound() + playerpo.getAssist() + playerpo.getSteal() + playerpo.getBlock()) - (playerpo.getShoot() - playerpo.getShootmade()) - (playerpo.getFreethrow() - playerpo.getFreethrowmade()) - playerpo.getError();
					vo.allgmsc = vo.allpoint + 0.4 * vo.allshootmade - 0.7 * vo.allshoot - 0.4 * (vo.allfreethrow - vo.allfreethrowmade) + 0.7 * vo.alloffensiverebound + 0.3 * vo.alldefensiverebound + vo.allsteal + 0.7 * vo.allassist + 0.7 * vo.allblock - 0.4 * vo.allfoul - vo.allerror;
					gmscsum = gmscsum + playerpo.getPoint() + 0.4 * playerpo.getShootmade() - 0.7 * playerpo.getShoot() - 0.4 * (playerpo.getFreethrow() - playerpo.getFreethrowmade()) + 0.7 * playerpo.getOffensiveRebounds() + 0.3 * playerpo.getDefensiveRebounds() + playerpo.getSteal() + 0.7 * 
							playerpo.getAssist() + 0.7 * playerpo.getBlock() - 0.4 * playerpo.getFoul() - playerpo.getError();
					vo.allrealshootpercent = vo.allpoint / (2 * (vo.allshoot + 0.44 * vo.allfreethrow));
					realshootpercentsum = realshootpercentsum + playerpo.getPoint() / (2 * (playerpo.getShoot() + 0.44 * playerpo.getFreethrow()));
					vo.allshootefficiency = (vo.allshootmade + 0.5 * vo.allthreepointmade) / vo.allshoot;
					shootefficiencysum = shootefficiencysum + (playerpo.getShootmade() + 0.5 * playerpo.getThreepointmade()) / playerpo.getShoot();
//					vo.alloffensivereboundrate = vo.alloffensiverebound * 48 * teamgamesum / vo.allminute / ();
//					vo.alldefensivereboundrate
					vo.allreboundrate = vo.allrebound * 48 * teamgamesum / vo.allminute / (allteamrebound + allopponentrebonud);
					reboundratesum = reboundratesum + playerpo.getRebound() * 48 / playerpo.getMinute() / (po.getTeam1().getRebounds() + po.getTeam2().getRebounds());
					vo.allassistrate = vo.allassist / (vo.allminute / (48 * teamgamesum) * allteamshootmade - vo.allshootmade);
					assistratesum = assistratesum + playerpo.getAssist() / (playerpo.getMinute() / 48 * po.getTeam1().getShootingHit() - playerpo.getShootmade());
//					vo.allstealrate = 
					vo.allblockrate = vo.allblock * (48 * teamgamesum) / vo.allminute / allopponentthreepoint;
					blockratesum = blockratesum + playerpo.getBlock() * 48 / playerpo.getMinute() / po.getTeam2().getThreePoint();
					vo.allerrorrate = vo.allerror / (vo.allshoot - vo.allthreepoint + 0.44 * vo.allfreethrow + vo.allerror);
					errorratesum = errorratesum + playerpo.getError() / (playerpo.getShoot() - playerpo.getThreepoint() + 0.44 * playerpo.getFreethrow() + playerpo.getError());
					vo.allusage = (vo.allshoot + 0.44 * vo.allfreethrow + vo.allerror) * (48 * teamgamesum) / vo.allminute  / (allteamshoot + 0.44 * allteamfreethrow + allteamerror);
					usagesum = usagesum + (playerpo.getShoot() + 0.44 * playerpo.getFreethrow() + playerpo.getError()) * 48 / playerpo.getMinute() / (po.getTeam1().getShooting() + 0.44 * po.getTeam1().getFreeThrow() + po.getTeam1().getTurnovers());
				}
			}
		}
		vo.rebound = vo.allrebound / vo.gameplay;
		vo.assist = vo.allassist / vo.gameplay;
		vo.minute = vo.allminute / vo.gameplay;
//		vo.offense = 
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

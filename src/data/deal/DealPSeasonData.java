package data.deal;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.PBasicInfoPO;
import po.PSeasonDataPO;
import po.TBasicInfoPO;
import data.readPOs.ReadMBasicPO;
import data.readPOs.ReadPBasicPO;
import data.readPOs.ReadTBasicPO;

public class DealPSeasonData {
	
	private static ArrayList<MatchPO> matches;
	private static ArrayList<PBasicInfoPO> players;
	private static ArrayList<TBasicInfoPO> teams;
	private static ArrayList<MatchPO> matchesAbout;
	
	public ArrayList<PSeasonDataPO> dealPSeasonData(String season) {
		matches =  ReadMBasicPO.readMBasicPO(season);
		players = ReadPBasicPO.readPBasicPO();
		teams = ReadTBasicPO.readTBasicPO();
		
		
		ArrayList<PSeasonDataPO> list = new ArrayList<PSeasonDataPO>();
		
		for(String name : getAllPlayersName()){
			getMatchesAboutPlayer(name);
			ArrayList<Double> teamlist = getTeamData();
			PSeasonDataPO po = new PSeasonDataPO();
		    po.setSeason(season);
		    po.setName(name);
		    po.setTeam(getTeam());
		    po.setPosition(getSinglePlayerBasicInfo(name).getPosition());
		    po.setSubArea(getSubArea(po.getTeam()));
		    po.setGameplay(matchesAbout.size());
		    po.setGamestart((int)getPlayerGameStart(name,season));
		    
		    if(po.getGameplay() == 0){
		    	list.add(po);
		    	continue;
		    }
		    
		    double fieldgoalpercentsum = 0,threepointpercentsum = 0,freethrowpercentsum = 0,efficiencysum = 0,gmscsum = 0,
		    		realshootpercentsum = 0,shootefficiencysum = 0,reboundratesum = 0,offensivereboundratesum = 0,defensivereboundratesum = 0,assistratesum = 0,stealratesum = 0,blockratesum = 0,errorratesum = 0,usagesum = 0;
		    
		    for(MatchPO match : matchesAbout){
		    	for(MatchPlayerDataPO playerpo : match.getTeam1().getTeamPlayers()){
		    		if(playerpo.getName().equals(name)){
		    			po.setAllrebound(po.getAllrebound() + playerpo.getRebound());
						po.setAlloffensiverebound(po.getAlloffensiverebound() + playerpo.getOffensiveRebounds());
						po.setAlldefensiverebound(po.getAlldefensiverebound() + playerpo.getDefensiveRebounds());
						po.setAllassist(po.getAllassist() + playerpo.getAssist());
						po.setAllminute(po.getAllminute() + playerpo.getMinute());
						po.setAlloffense(po.getAlloffense() + playerpo.getOffense());
						po.setAlldefence(po.getAlldefence() + playerpo.getDefence());
						po.setAllsteal(po.getAllsteal() + playerpo.getSteal());
						po.setAllblock(po.getAllblock() + playerpo.getBlock());
						po.setAllerror(po.getAllerror() + playerpo.getError());
						po.setAllfoul(po.getAllfoul() + playerpo.getFoul());
						po.setAllpoint(po.getAllpoint() + playerpo.getPoint());
						po.setAllshoot(po.getAllshoot() + playerpo.getShoot());
						po.setAllshootmade(po.getAllshootmade() + playerpo.getShootmade());
						po.setAllthreepoint(po.getAllthreepoint() + playerpo.getThreepoint());
						po.setAllthreepointmade(po.getAllthreepointmade() + playerpo.getThreepointmade());
						po.setAllfreethrow(po.getAllfreethrow() + playerpo.getFreethrow());
						po.setAllfreethrowmade(po.getAllfreethrowmade() + playerpo.getFreethrowmade());
						po.setDoubledouble(po.getDoubledouble() + playerpo.getDoubledouble());
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
						reboundratesum = reboundratesum + playerpo.getRebound() * 48 / playerpo.getMinute() / (match.getTeam1().getRebounds() + match.getTeam2().getRebounds());
						if(match.getTeam1().getOffensiveRebounds() + match.getTeam2().getOffensiveRebounds() != 0){
							offensivereboundratesum = offensivereboundratesum + playerpo.getOffensiveRebounds() * 48 / playerpo.getMinute() / (match.getTeam1().getOffensiveRebounds() + match.getTeam2().getOffensiveRebounds());
						}
						if(match.getTeam1().getDefensiveRebounds() + match.getTeam2().getDefensiveRebounds() != 0){
							defensivereboundratesum = defensivereboundratesum + playerpo.getDefensiveRebounds() * 48 / playerpo.getMinute() / (match.getTeam1().getDefensiveRebounds() + match.getTeam2().getDefensiveRebounds());
						}
						if(playerpo.getMinute() / 48 * match.getTeam1().getShootingHit() - playerpo.getShootmade() != 0){
							assistratesum = assistratesum + playerpo.getAssist() / (playerpo.getMinute() / 48 * match.getTeam1().getShootingHit() - playerpo.getShootmade());
						}
						if(match.getTeam2().getOffensiveRebounds() != 0){
							stealratesum = stealratesum + playerpo.getSteal() * 48 / playerpo.getMinute() / match.getTeam2().getOffensiveRebounds();
						}
						if(match.getTeam2().getThreePoint() != 0){
							blockratesum = blockratesum + playerpo.getBlock() * 48 / playerpo.getMinute() / match.getTeam2().getThreePoint();
						}
						
						if(playerpo.getShoot() - playerpo.getThreepoint() + 0.44 * playerpo.getFreethrow() + playerpo.getError() != 0){
							errorratesum = errorratesum + playerpo.getError() / (playerpo.getShoot() - playerpo.getThreepoint() + 0.44 * playerpo.getFreethrow() + playerpo.getError());
						}
						
						if(match.getTeam1().getShooting() + 0.44 * match.getTeam1().getFreeThrow() + match.getTeam1().getTurnovers() != 0){
							usagesum = usagesum + (playerpo.getShoot() + 0.44 * playerpo.getFreethrow() + playerpo.getError()) * 48 / playerpo.getMinute() / (match.getTeam1().getShooting() + 0.44 * match.getTeam1().getFreeThrow() + match.getTeam1().getTurnovers());
						}
		    		}
		    	}
		    }
		    po.setAllpointReboundAssist(po.getAllpoint() + po.getAllrebound() + po.getAllassist());
			if(po.getAllshoot() == 0){
				po.setAllfieldgoalpercent(0);
			}
			else{
				po.setAllfieldgoalpercent(Math.ceil(po.getAllshootmade() / po.getAllshoot() * 100)/ 100);
			}
			
		    if(po.getAllthreepoint() == 0){
		    	po.setAllthreepointpercent(0);
		    }
		    else{
		    	po.setAllthreepointpercent(Math.ceil(po.getAllthreepointmade() / po.getAllthreepoint() * 100)/ 100);
		    }
			
		    if(po.getAllfreethrow() == 0){
		    	po.setAllfreethrowpercent(0);
		    }
		    else{
		    	po.setAllfreethrowpercent(Math.ceil(po.getAllfreethrowmade() / po.getAllfreethrow() * 100)/ 100);
		    }
			
			po.setAllefficiency(Math.ceil(((po.getAllpoint() + po.getAllrebound() + po.getAllassist() + po.getAllsteal() + po.getAllblock()) - (po.getAllshoot() - po.getAllshootmade()) - (po.getAllfreethrow() - po.getAllfreethrowmade()) -po.getAllerror()) * 100)/ 100);
			
			po.setAllgmsc(Math.ceil((po.getAllpoint() + 0.4 * po.getAllshootmade() - 0.7 * po.getAllshoot() - 0.4 * (po.getAllfreethrow() - po.getAllfreethrowmade()) + 0.7 * po.getAlloffensiverebound() + 0.3 * po.getAlldefensiverebound() + po.getAllsteal() + 0.7 * po.getAllassist() + 0.7 * po.getAllblock() - 0.4 * po.getAllfoul() - po.getAllerror()) * 100)/ 100);
			
			if(po.getAllshoot() + 0.44 * po.getAllfreethrow() == 0){
				po.setAllrealshootpercent(0);
			}
			else{
				po.setAllrealshootpercent(Math.ceil(po.getAllpoint() / (2 * (po.getAllshoot() + 0.44 * po.getAllfreethrow())) * 100)/ 100);
			}			
			
			po.setAllshootefficiency(Math.ceil((po.getAllshootmade() + 0.5 * po.getAllthreepointmade()) / po.getAllshoot() * 100)/ 100);
			po.setAlloffensivereboundrate(Math.ceil(po.getAlloffensiverebound() * 48 * po.getGameplay() / po.getAllminute() / (teamlist.get(0) + teamlist.get(4)) * 100)/ 100);
			po.setAlldefensivereboundrate(Math.ceil(po.getAlldefensiverebound() * 48 * po.getGameplay() / po.getAllminute() / (teamlist.get(1) + teamlist.get(5)) * 100)/ 100);
			po.setAllreboundrate(Math.ceil(po.getAllrebound() * 48 * po.getGameplay() / po.getAllminute() / (teamlist.get(2) + teamlist.get(5) + teamlist.get(4)) * 100)/ 100);
			po.setAllassistrate(Math.ceil(po.getAllassist() / (po.getAllminute() / (48 * po.getGameplay()) * teamlist.get(3) - po.getAllshootmade()) * 100)/ 100);
			
			po.setAllstealrate(Math.ceil(po.getAllsteal() * (48 * po.getGameplay()) / po.getAllminute() / teamlist.get(4) * 100)/ 100);
			if(teamlist.get(6) - teamlist.get(7) == 0){
				po.setAllblockrate(0);
			}

			else{				
				po.setAllblockrate(Math.ceil(po.getAllblock() * (48 * po.getGameplay()) / po.getAllminute() / (teamlist.get(6) - teamlist.get(7)) * 100)/ 100);
			}
			
			po.setAllerrorrate(Math.ceil(po.getAllerror() / (po.getAllshoot() - po.getAllthreepoint() + 0.44 * po.getAllfreethrow() + po.getAllerror()) * 100)/ 100);
			
			po.setAllusage(Math.ceil((po.getAllshoot() + 0.44 * po.getAllfreethrow() + po.getAllerror()) * (48 * po.getGameplay()) / po.getAllminute()  / (teamlist.get(8) + 0.44 * teamlist.get(9) + teamlist.get(10)) * 100)/ 100);
			
			po.setRebound(Math.ceil(po.getAllrebound() / po.getGameplay() * 100) / 100);
			po.setOffensiverebound(Math.ceil(po.getAlloffensiverebound() / po.getGameplay() * 100) / 100);
			po.setDefensiverebound(Math.ceil((po.getAllrebound() / po.getGameplay() - po.getAlloffensiverebound() / po.getGameplay()) * 100) / 100);
			po.setAssist(Math.ceil(po.getAllassist() / po.getGameplay() * 100)/ 100);
			po.setMinute(Math.ceil(po.getAllminute() / po.getGameplay() * 100)/ 100);
			po.setAllminute(Math.ceil(po.getAllminute() * 100)/ 100);
			po.setOffense(Math.ceil(po.getAlloffense() / po.getGameplay() * 100)/ 100);
			po.setDefence(Math.ceil(po.getAlldefence() / po.getGameplay() * 100)/ 100);
			po.setSteal(Math.ceil(po.getAllsteal() / po.getGameplay() * 100)/ 100);
			po.setBlock(Math.ceil(po.getAllblock() / po.getGameplay() * 100)/ 100);
			po.setError(Math.ceil(po.getAllerror() / po.getGameplay() * 100)/ 100);
			po.setFoul(Math.ceil(po.getAllfoul() / po.getGameplay() * 100)/ 100);
			po.setPoint(Math.ceil(po.getAllpoint() / po.getGameplay() * 100)/ 100);
			po.setShoot(Math.ceil(po.getAllshoot() / po.getGameplay() * 100)/ 100);
			po.setShootmade(Math.ceil(po.getAllshootmade() / po.getGameplay() * 100)/ 100);
			po.setThreepoint(Math.ceil(po.getAllthreepoint() / po.getGameplay() * 100)/ 100);
			po.setThreepointmade(Math.ceil(po.getAllthreepointmade() / po.getGameplay() * 100)/ 100);
			po.setFreethrow(Math.ceil(po.getAllfreethrow() / po.getGameplay() * 100)/ 100);
			po.setFreethrowmade(Math.ceil(po.getAllfreethrowmade() / po.getGameplay() * 100)/ 100);
			po.setPointReboundAssist(Math.ceil((po.getPoint() + po.getRebound() + po.getAssist()) * 100) / 100);
			
			po.setFieldgoalpercent(Math.ceil(fieldgoalpercentsum / po.getGameplay() * 100)/ 100);
			po.setThreepointpercent(Math.ceil(threepointpercentsum / po.getGameplay() * 100) / 100);
			po.setFreethrowpercent(Math.ceil(freethrowpercentsum / po.getGameplay() * 100)/ 100);
			po.setEfficiency(Math.ceil(efficiencysum / po.getGameplay() * 100)/ 100);
			po.setGmsc(Math.ceil(gmscsum / po.getGameplay() * 100)/ 100);
			po.setRealshootpercent(Math.ceil(realshootpercentsum / po.getGameplay() * 100)/ 100);
			po.setShootefficiency(Math.ceil(shootefficiencysum / po.getGameplay() * 100)/ 100);
			po.setReboundrate(Math.ceil(reboundratesum / po.getGameplay() * 100)/ 100);
			po.setOffensivereboundrate(Math.ceil(offensivereboundratesum / po.getGameplay() * 100)/ 100);
			po.setDefensivereboundrate(Math.ceil(defensivereboundratesum / po.getGameplay() * 100)/ 100);
			po.setAssistrate(Math.ceil(assistratesum / po.getGameplay() * 100)/ 100);
			po.setStealrate(Math.ceil(stealratesum / po.getGameplay() * 100)/ 100);
			po.setBlockrate(Math.ceil(blockratesum / po.getGameplay() * 100)/ 100);
			po.setErrorrate(Math.ceil(errorratesum / po.getGameplay() * 100)/ 100);
			po.setUsage(Math.ceil(usagesum / po.getGameplay() * 100)/ 100);
			
			list.add(po);
		}
		return list;
	}
	
	private ArrayList<Double> getTeamData(){
		ArrayList<Double> list = new ArrayList<Double>();
		double alloffensiveRebounds = 0,alldefensiveRebounds = 0,allrebounds = 0,allshootingHit = 0,allopponentOffensiveRebounds = 0,
				allopponentDefensiveRebounds = 0,allopponentshoot = 0,allopponentthreepoint = 0,allshooting = 0,allfreeThrow = 0,allturnovers = 0;
		for(MatchPO po : matchesAbout){
			alloffensiveRebounds += po.getTeam1().getOffensiveRebounds();
			alldefensiveRebounds += po.getTeam1().getDefensiveRebounds();
			allrebounds += po.getTeam1().getRebounds();
			allshootingHit += po.getTeam1().getShootingHit();
			allopponentOffensiveRebounds += po.getTeam2().getOffensiveRebounds();
			allopponentDefensiveRebounds += po.getTeam2().getDefensiveRebounds();
			allopponentshoot += po.getTeam2().getShooting();
			allopponentthreepoint += po.getTeam2().getThreePoint();
			allshooting += po.getTeam1().getShooting();
			allfreeThrow += po.getTeam1().getFreeThrow();
			allturnovers += po.getTeam1().getTurnovers();
		}
		list.add(alloffensiveRebounds);
		list.add(alldefensiveRebounds);
		list.add(allrebounds);
		list.add(allshootingHit);
		list.add(allopponentOffensiveRebounds);
		list.add(allopponentDefensiveRebounds);
		list.add(allopponentshoot);
		list.add(allopponentthreepoint);
		list.add(allshooting);
		list.add(allfreeThrow);
		list.add(allturnovers);
		return list;
	}
	
	
	/********************************************************************************************
	 *获得球员name在season的所有比赛数据
	 *该球员处于team1 中 
	 */
	private void getMatchesAboutPlayer(String name) {
		matchesAbout = new ArrayList<MatchPO>();
		
		for(MatchPO matchPO : matches){
			/**寻找相关比赛*/
			boolean existInTeam1 = matchPO.getTeam1().existPlayer(name);
			boolean existInTeam2 = matchPO.getTeam2().existPlayer(name);
			if(existInTeam1 || existInTeam2){
				/**交换至team1*/
				if(existInTeam2)
					matchPO.swapTeam();	
				
				matchesAbout.add(matchPO);	
			}
		}
	}
	
	/**
	 * 获得所有球员名字
	 * */
	private ArrayList<String> getAllPlayersName() {
		ArrayList<String> names = new ArrayList<String>();
		
		for(PBasicInfoPO pBasicPO : players)
			names.add(pBasicPO.getName());
		
		return names;
	}
	
	/**
	 * 获得球员在season的先发场数
	 * */
	private double getPlayerGameStart(String name, String season) {			
		/**统计先发场数*/
		double gameStart = 0.0;
		for(MatchPO matchPO : matchesAbout){
			ArrayList<MatchPlayerDataPO> teamPlayers = matchPO.getTeam1().getTeamPlayers();
			for(MatchPlayerDataPO matchPlayerPO : teamPlayers){
				if(matchPlayerPO.getName().equals(name)){
					gameStart += matchPlayerPO.getGameStart();
					break;
				}
			}
		}
		
		return gameStart;
	}
	
	/**获得球员基本信息*/
	public PBasicInfoPO getSinglePlayerBasicInfo(String name) {
		for(PBasicInfoPO po : players)
			if(po.getName().equals(name))
				return po;
		
		/**无法找到该球员*/
		return new PBasicInfoPO();
	}
	
	/**获得球员的最近的球队*/
	private String getTeam() {
		if(matchesAbout.size() != 0){
			/**找到最近的比赛*/
			MatchPO lastMatchPO = matchesAbout.get(0);
			for(int i = 1; i < matchesAbout.size(); i++)
				if((matchesAbout.get(i).getSeason() + matchesAbout.get(i).getDate()).compareTo
						(lastMatchPO.getSeason() + lastMatchPO.getDate()) > 0)
					 lastMatchPO = matchesAbout.get(i);
			
			return lastMatchPO.getTeam1().getAbbName();
		}
		return "";
	}
	
	/**获得分区*/
	private String getSubArea(String abbName) {		
			/**找到球队分区*/
			for(TBasicInfoPO po : teams)
				if(po.getAbbName().equals(abbName))
					return po.getSubArea();
		return "";
	}
}

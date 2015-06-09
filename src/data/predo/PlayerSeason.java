package data.predo;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.MatchTeamDataPO;
import po.PBasicInfoPO;
import po.PSeasonDataPO;
import po.TBasicInfoPO;

public class PlayerSeason {
	private ArrayList<MatchPO> matches;
	private ArrayList<TBasicInfoPO> teams;
	
	public PSeasonDataPO playerSeason(ArrayList<MatchPO> matches,ArrayList<MatchPlayerDataPO> players,ArrayList<TBasicInfoPO> teams,PBasicInfoPO playerinfo) {
		this.matches = matches;
		this.teams = teams;
		
//			ArrayList<Double> teamlist = getTeamData();
			PSeasonDataPO po = new PSeasonDataPO();
		    po.setSeason(matches.get(0).getSeason());
		    po.setName(players.get(0).getName());
		    po.setTeam(getTeam());
		    po.setPosition(playerinfo.getPosition());
		    po.setSubArea(getSubArea(po.getTeam()));
		    po.setGameplay(matches.size());
		    po.setGamestart((int)getPlayerGameStart(po.getName(),po.getSeason()));
		    
		    if(po.getGameplay() == 0){
		    	return po;
		    }
		    
		    double efficiencysum = 0,gmscsum = 0,time = 0,allteamoffensiveRebounds = 0,allteamdefensiveRebounds = 0,allteamrebounds = 0,allteamshootingHit = 0,allopponentOffensiveRebounds = 0,
					allopponentDefensiveRebounds = 0,allopponentrebounds = 0,allopponentshoot = 0,allopponentthreepoint = 0,allopponentshootingHit = 0,allteamshooting = 0,allteamfreeThrow = 0,allopponentfreethrow = 0,allteamturnover = 0,allopponentturnover = 0,allteampoint = 0,allopponentpoint = 0,allattackround = 0,allopponentattackround = 0;
		    for(MatchPO match : matches){
		    	double plustime = match.getTeam1().getQtPlusNum() * 5;
		    	time += plustime;
		    	MatchTeamDataPO teampo = match.getTeam1();
		    	MatchTeamDataPO opponentpo = match.getTeam2();
		    	
		    	allteamoffensiveRebounds += teampo.getOffensiveRebounds();
				allteamdefensiveRebounds += teampo.getDefensiveRebounds();
				allteamrebounds += teampo.getRebounds();
				allteamshootingHit += teampo.getShootingHit();
				allopponentOffensiveRebounds += opponentpo.getOffensiveRebounds();
				allopponentDefensiveRebounds += opponentpo.getDefensiveRebounds();
				allopponentrebounds += opponentpo.getRebounds();
				allopponentshoot += opponentpo.getShooting();
				allopponentthreepoint += opponentpo.getThreePoint();
				allopponentshootingHit += opponentpo.getShootingHit();
				allteamshooting += teampo.getShooting();
				allteamfreeThrow += teampo.getFreeThrow();
				allopponentfreethrow += opponentpo.getFreeThrow();
				allteamturnover += teampo.getTurnovers();
				allopponentturnover += opponentpo.getTurnovers();
				allteampoint += teampo.getScores();
				allopponentpoint += opponentpo.getScores();
		    	
		    	for(MatchPlayerDataPO playerpo : players){
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
						efficiencysum += (playerpo.getPoint() + playerpo.getRebound() + playerpo.getAssist() + playerpo.getSteal() + playerpo.getBlock()) - (playerpo.getShoot() - playerpo.getShootmade()) - (playerpo.getFreethrow() - playerpo.getFreethrowmade()) - playerpo.getError();
						gmscsum += (playerpo.getPoint() + 0.4 * playerpo.getShootmade() - 0.7 * playerpo.getShoot() - 0.4 * (playerpo.getFreethrow() - playerpo.getFreethrowmade()) + 0.7 * playerpo.getOffensiveRebounds() + 0.3 * playerpo.getDefensiveRebounds() + playerpo.getSteal() + 0.7 * playerpo.getAssist() + 0.7 * playerpo.getBlock() - 0.4 * playerpo.getFoul() - playerpo.getError());
		    		
		    	}
		    }
		    po.setAlloffensiverebound(getDouble(po.getAlloffensiverebound()));
		    po.setAlldefensiverebound(getDouble(po.getAlldefensiverebound()));
		    po.setAllrebound(getDouble(po.getAllrebound()));
		    po.setAllpointReboundAssist(po.getAllpoint() + po.getAllrebound() + po.getAllassist());
			if(po.getAllshoot() == 0){
				po.setAllfieldgoalpercent(0);
			}
			else{
				po.setAllfieldgoalpercent(getDouble(po.getAllshootmade() / po.getAllshoot() ));
			}
			
		    if(po.getAllthreepoint() == 0){
		    	po.setAllthreepointpercent(0);
		    }
		    else{
		    	po.setAllthreepointpercent(getDouble(po.getAllthreepointmade() / po.getAllthreepoint() ));
		    }
			
		    if(po.getAllfreethrow() == 0){
		    	po.setAllfreethrowpercent(0);
		    }
		    else{
		    	po.setAllfreethrowpercent(getDouble(po.getAllfreethrowmade() / po.getAllfreethrow() ));
		    }
			
			po.setAllefficiency(getDouble(((po.getAllpoint() + po.getAllrebound() + po.getAllassist() + po.getAllsteal() + po.getAllblock()) - (po.getAllshoot() - po.getAllshootmade()) - (po.getAllfreethrow() - po.getAllfreethrowmade()) -po.getAllerror()) ));
			
			po.setAllgmsc(getDouble((po.getAllpoint() + 0.4 * po.getAllshootmade() - 0.7 * po.getAllshoot() - 0.4 * (po.getAllfreethrow() - po.getAllfreethrowmade()) + 0.7 * po.getAlloffensiverebound() + 0.3 * po.getAlldefensiverebound() + po.getAllsteal() + 0.7 * po.getAllassist() + 0.7 * po.getAllblock() - 0.4 * po.getAllfoul() - po.getAllerror()) ));
			
			if(po.getAllshoot() + 0.44 * po.getAllfreethrow() == 0){
				po.setAllrealshootpercent(0);
			}
			else{
				po.setAllrealshootpercent(getDouble(po.getAllpoint() / (2 * (po.getAllshoot() + 0.44 * po.getAllfreethrow())) ));
			}			
			
			if(po.getAllshoot() == 0){
				po.setAllshootefficiency(0);
			}
			else{
				po.setAllshootefficiency(getDouble((po.getAllshootmade() + 0.5 * po.getAllthreepointmade()) / po.getAllshoot() ));
			}
			
			if(po.getAllminute() == 0 || allteamrebounds + allopponentrebounds == 0){
				po.setAlloffensivereboundrate(0);
			}
			else{
				po.setAlloffensivereboundrate(getDouble(po.getAlloffensiverebound() * (48 * po.getGameplay() + time) / po.getAllminute() / (allteamrebounds + allopponentrebounds) ));
			}
			
			if(po.getAllminute() == 0 || allteamrebounds + allopponentrebounds == 0){
				po.setAlldefensivereboundrate(0);
			}
			else{
				po.setAlldefensivereboundrate(getDouble(po.getAlldefensiverebound() * (48 * po.getGameplay() + time) / po.getAllminute() / (allteamrebounds + allopponentrebounds) ));
			}
			
			if(po.getAllminute() == 0 || allteamrebounds + allopponentrebounds == 0){
				po.setAllreboundrate(0);
			}
			else{
				po.setAllreboundrate(getDouble(po.getAllrebound() * (48 * po.getGameplay() + time) / po.getAllminute() / (allteamrebounds + allopponentrebounds) ));
			}
			
			if(po.getAllminute() == 0 || (48 * po.getGameplay() + time) * allteamshootingHit - po.getAllshootmade() == 0){
				po.setAllassistrate(0);
			}
			else{
				po.setAllassistrate(getDouble(po.getAllassist() / (po.getAllminute() / ((48 * po.getGameplay() + time)) * allteamshootingHit - po.getAllshootmade()) ));
			}
			
			if(po.getAllminute() == 0 || allopponentOffensiveRebounds == 0){
				po.setAllstealrate(0);
			}
			else{
				po.setAllstealrate(getDouble(po.getAllsteal() * ((48 * po.getGameplay() + time)) / po.getAllminute() / allopponentOffensiveRebounds ));
			}
			
			if(allopponentshoot - allopponentthreepoint == 0){
				po.setAllblockrate(0);
			}

			else{				
				po.setAllblockrate(getDouble(po.getAllblock() * ((48 * po.getGameplay() + time)) / po.getAllminute() / (allopponentshoot - allopponentthreepoint) ));
			}
			
			if(po.getAllshoot() - po.getAllthreepoint() + 0.44 * po.getAllfreethrow() + po.getAllerror() == 0){
				po.setAllerrorrate(0);
			}
			else{
				po.setAllerrorrate(getDouble(po.getAllerror() / (po.getAllshoot() - po.getAllthreepoint() + 0.44 * po.getAllfreethrow() + po.getAllerror()) ));
			}
			
			if(po.getAllminute() == 0 || allteamshooting + 0.44 * allteamfreeThrow + allteamturnover == 0){
				po.setAllusage(0);
			}
			else{
				po.setAllusage(getDouble((po.getAllshoot() + 0.44 * po.getAllfreethrow() + po.getAllerror()) * ((48 * po.getGameplay() + time)) / po.getAllminute()  / (allteamshooting + 0.44 * allteamfreeThrow + allteamturnover) ));
			}
			
			po.setRebound(getDouble(po.getAllrebound() / po.getGameplay()));
			po.setOffensiverebound(getDouble(po.getAlloffensiverebound() / po.getGameplay()));
			po.setDefensiverebound(getDouble((po.getAllrebound() / po.getGameplay() - po.getAlloffensiverebound() / po.getGameplay())));
			po.setAssist(getDouble(po.getAllassist() / po.getGameplay() ));
			po.setMinute(getDouble(po.getAllminute() / po.getGameplay() ));
			po.setAllminute(getDouble(po.getAllminute() ));
			po.setOffense(getDouble(po.getAlloffense() / po.getGameplay() ));
			po.setDefence(getDouble(po.getAlldefence() / po.getGameplay() ));
			po.setSteal(getDouble(po.getAllsteal() / po.getGameplay() ));
			po.setBlock(getDouble(po.getAllblock() / po.getGameplay() ));
			po.setError(getDouble(po.getAllerror() / po.getGameplay() ));
			po.setFoul(getDouble(po.getAllfoul() / po.getGameplay() ));
			po.setPoint(getDouble(po.getAllpoint() / po.getGameplay() ));
			po.setShoot(getDouble(po.getAllshoot() / po.getGameplay() ));
			po.setShootmade(getDouble(po.getAllshootmade() / po.getGameplay() ));
			po.setThreepoint(getDouble(po.getAllthreepoint() / po.getGameplay() ));
			po.setThreepointmade(getDouble(po.getAllthreepointmade() / po.getGameplay() ));
			po.setFreethrow(getDouble(po.getAllfreethrow() / po.getGameplay() ));
			po.setFreethrowmade(getDouble(po.getAllfreethrowmade() / po.getGameplay() ));
			po.setPointReboundAssist(getDouble((po.getPoint() + po.getRebound() + po.getAssist())));
			
			po.setFieldgoalpercent(po.getAllfieldgoalpercent());
			po.setThreepointpercent(po.getAllthreepointpercent());
			po.setFreethrowpercent(po.getAllfreethrowpercent());
			po.setEfficiency(getDouble(efficiencysum / po.getGameplay()));
			po.setGmsc(getDouble(gmscsum / po.getGameplay()));
			po.setRealshootpercent(po.getAllrealshootpercent());
			po.setShootefficiency(po.getAllshootefficiency());
			po.setReboundrate(po.getAllreboundrate());
			po.setOffensivereboundrate(po.getAlloffensivereboundrate());
			po.setDefensivereboundrate(po.getAlldefensivereboundrate());
			po.setAssistrate(po.getAllassistrate());
			po.setStealrate(po.getAllstealrate());
			po.setBlockrate(po.getAllblockrate());
			po.setErrorrate(po.getAllerrorrate());
			po.setUsage(po.getAllusage());
			

		    double fivepoint = 0,fiverebound = 0,fiveassist = 0;
			for(MatchPO matchpo : this.getLastFivematchesPlayer(po.getName())){
				for(MatchPlayerDataPO playerpo : matchpo.getTeam1().getTeamPlayers()){
					if(playerpo.getName().equals(po.getName())){
						fivepoint += playerpo.getPoint();
						fiverebound += playerpo.getRebound();
						fiveassist += playerpo.getAssist();
					}
				}
				for(MatchPlayerDataPO playerpo : matchpo.getTeam2().getTeamPlayers()){
					if(playerpo.getName().equals(po.getName())){
						fivepoint += playerpo.getPoint();
						fiverebound += playerpo.getRebound();
						fiveassist += playerpo.getAssist();
					}
				}
			}
			
			if(po.getGameplay() <= 5){
				po.setPointpromotion(0);
				po.setReboundpromotion(0);
				po.setAssistpromotion(0);
			}
			else{
				double fivebeforepoint = (po.getAllpoint() - fivepoint) / (po.getGameplay() - 5);
				double fivebeforerebound = (po.getAllrebound() - fiverebound) / (po.getGameplay() - 5);
				double fivebeforeassist = (po.getAllassist() - fiveassist) / (po.getGameplay() - 5);
			
				if(fivebeforepoint != 0){
					po.setPointpromotion(getDouble((fivepoint / 5 - fivebeforepoint) / fivebeforepoint ));
				}
				else if(fivepoint == 0){
					po.setPointpromotion(0);
				}
				else{
					po.setPointpromotion(0);
				}
				
				if(fivebeforerebound != 0){
					po.setReboundpromotion(getDouble((fiverebound / 5 - fivebeforerebound) / fivebeforerebound ));
				}
				else if(fiverebound == 0){
					po.setReboundpromotion(0);
				}
				else{
					po.setReboundpromotion(0);
				}
				
				if(fivebeforeassist != 0){
					po.setAssistpromotion(getDouble((fiveassist / 5 - fivebeforeassist) / fivebeforeassist));
				}
				else if(fiveassist == 0){
					po.setAssistpromotion(0);
				}
				else{
					po.setAssistpromotion(0);
				}
			}
			allattackround = calculateAttackround(allteamshooting,allteamfreeThrow,allteamoffensiveRebounds,allopponentDefensiveRebounds,allteamshootingHit,allteamturnover);
			allopponentattackround = calculateAttackround(allopponentshoot,allopponentfreethrow,allopponentOffensiveRebounds,allteamdefensiveRebounds,allopponentshootingHit,allopponentturnover);
			double percent = po.getAllminute() / (po.getGameplay() * 48);
//			po.setORPM(getDouble(100 * / allattackround));
			po.setDRPM(getDouble(100 * allopponentpoint / allopponentattackround));
			po.setRPM(getDouble(100 * (allteampoint - allopponentpoint) / percent * (allattackround + allopponentattackround)));
			
			return po;
	}
	
	public double getDouble(double d){
		return new  java.math.BigDecimal(Double.toString(d)).setScale(1,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	private double calculateAttackround(double allshooting,double allfreeThrow,double alloffensiveRebounds,double allopponentDefensiveRebounds,double allshootingHit,double allturnovers){
		return allshooting + 0.4 * allfreeThrow - 1.07 * (alloffensiveRebounds / (alloffensiveRebounds + allopponentDefensiveRebounds) * (allshooting - allshootingHit)) + 1.07 * allturnovers;
	}

/*	private ArrayList<Double> getTeamData(){
		ArrayList<Double> list = new ArrayList<Double>();
		double alloffensiveRebounds = 0,alldefensiveRebounds = 0,allrebounds = 0,allshootingHit = 0,allopponentOffensiveRebounds = 0,
				allopponentDefensiveRebounds = 0,allopponentshoot = 0,allopponentthreepoint = 0,allshooting = 0,allfreeThrow = 0,allturnovers = 0,allpoint = 0,allopponentpoint = 0;
		for(MatchPO po : matches){
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
			allpoint += po.getTeam1().getScores();
			allopponentpoint += po.getTeam2().getScores();
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
	}*/
	
	/********************************************************************************************
	
	/**
	 * 获得球员在season的先发场数
	 * */
	private double getPlayerGameStart(String name, String season) {			
		/**统计先发场数*/
		double gameStart = 0.0;
		for(MatchPO matchPO : matches){
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
	
	
/*	public PBasicInfoPO getSinglePlayerBasicInfo(String name) {
		for(PBasicInfoPO po : players)
			if(po.getName().equals(name))
				return po;
		return new PBasicInfoPO();
	}*/
	
	/**获得球员的最近的球队*/
	private String getTeam() {
		if(matches.size() != 0){
			/**找到最近的比赛*/
			MatchPO lastMatchPO = matches.get(0);
			for(int i = 1; i < matches.size(); i++)
				if((matches.get(i).getSeason() + matches.get(i).getDate()).compareTo
						(lastMatchPO.getSeason() + lastMatchPO.getDate()) > 0)
					 lastMatchPO = matches.get(i);
			
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
	
	/**球员最近五场比赛*/
	private ArrayList<MatchPO> getLastFivematchesPlayer(String name) {
		ArrayList<MatchPO> res =new ArrayList<MatchPO>();
		
		if(matches.size() <= 5)
			return matches;
		
		//找到最近五场
		int lastedIndex = 0;
		if(matches.get(lastedIndex).getDate().compareTo("06-01") > 0){
			for(int i = 0; i < 5; i++)
				res.add(matches.get(matches.size() - 1 - i));
		}
		else{
			for(; lastedIndex < matches.size(); lastedIndex++){	
				if(matches.get(lastedIndex).getDate().compareTo("06-01") > 0)
					break;
			}
			lastedIndex--;
			
			if(lastedIndex >= 4){
				for(int i = 0; i < 5; i++){
					res.add(matches.get(lastedIndex - i));
				}				
			}
			else{
				for(int i = 0; i <= lastedIndex; i++)
					res.add(matches.get(i));
				for(int i = 0; i < 5 - res.size(); i++){
					res.add(matches.get(matches.size() - 1 - i));
				}
			}
		}
		
		return res;
	}
}

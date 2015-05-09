package data.predo;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.PBasicInfoPO;
import po.PSeasonDataPO;
import po.TBasicInfoPO;

public class PlayerSeason {
	private static ArrayList<MatchPO> matches;
	private static ArrayList<PBasicInfoPO> players;
	private static ArrayList<TBasicInfoPO> teams;
	private static ArrayList<MatchPO> matchesAbout;
	
	@SuppressWarnings("static-access")
	public ArrayList<PSeasonDataPO> playerSeason(ArrayList<MatchPO> matches, ArrayList<PBasicInfoPO> players, ArrayList<TBasicInfoPO> teams, String season) {
		this.matches = matches;
		this.players = players;
		this.teams = teams;
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
		    
		    double efficiencysum = 0,gmscsum = 0,time = 0;
		    for(MatchPO match : matchesAbout){
		    	double plustime = match.getTeam1().getQtPlusNum() * 5;
		    	time += plustime;
		    	
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
						efficiencysum += (playerpo.getPoint() + playerpo.getRebound() + playerpo.getAssist() + playerpo.getSteal() + playerpo.getBlock()) - (playerpo.getShoot() - playerpo.getShootmade()) - (playerpo.getFreethrow() - playerpo.getFreethrowmade()) - playerpo.getError();
						gmscsum += (playerpo.getPoint() + 0.4 * playerpo.getShootmade() - 0.7 * playerpo.getShoot() - 0.4 * (playerpo.getFreethrow() - playerpo.getFreethrowmade()) + 0.7 * playerpo.getOffensiveRebounds() + 0.3 * playerpo.getDefensiveRebounds() + playerpo.getSteal() + 0.7 * playerpo.getAssist() + 0.7 * playerpo.getBlock() - 0.4 * playerpo.getFoul() - playerpo.getError());
		    		}
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
			po.setAlloffensivereboundrate(getDouble(po.getAlloffensiverebound() * (48 * po.getGameplay() + time) / po.getAllminute() / (teamlist.get(0) + teamlist.get(4)) ));
			po.setAlldefensivereboundrate(getDouble(po.getAlldefensiverebound() * (48 * po.getGameplay() + time) / po.getAllminute() / (teamlist.get(1) + teamlist.get(5)) ));
			po.setAllreboundrate(getDouble(po.getAllrebound() * (48 * po.getGameplay() + time) / po.getAllminute() / (teamlist.get(2) + teamlist.get(5) + teamlist.get(4)) ));
			po.setAllassistrate(getDouble(po.getAllassist() / (po.getAllminute() / ((48 * po.getGameplay() + time)) * teamlist.get(3) - po.getAllshootmade()) ));
			
			po.setAllstealrate(getDouble(po.getAllsteal() * ((48 * po.getGameplay() + time)) / po.getAllminute() / teamlist.get(4) ));
			if(teamlist.get(6) - teamlist.get(7) == 0){
				po.setAllblockrate(0);
			}

			else{				
				po.setAllblockrate(getDouble(po.getAllblock() * ((48 * po.getGameplay() + time)) / po.getAllminute() / (teamlist.get(6) - teamlist.get(7)) ));
			}
			
			if(po.getAllshoot() - po.getAllthreepoint() + 0.44 * po.getAllfreethrow() + po.getAllerror() == 0){
				po.setAllerrorrate(0);
			}
			else{
				po.setAllerrorrate(getDouble(po.getAllerror() / (po.getAllshoot() - po.getAllthreepoint() + 0.44 * po.getAllfreethrow() + po.getAllerror()) ));
			}
			
			po.setAllusage(getDouble((po.getAllshoot() + 0.44 * po.getAllfreethrow() + po.getAllerror()) * ((48 * po.getGameplay() + time)) / po.getAllminute()  / (teamlist.get(8) + 0.44 * teamlist.get(9) + teamlist.get(10)) ));
			
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
			for(MatchPO matchpo : this.getLastFiveMatchesAboutPlayer(po.getName())){
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
			list.add(po);
		}
		return list;
	}
	
	public double getDouble(double d){
		return new  java.math.BigDecimal(Double.toString(d)).setScale(1,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
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
	
	/**球员最近五场比赛*/
	private ArrayList<MatchPO> getLastFiveMatchesAboutPlayer(String name) {
		ArrayList<MatchPO> res =new ArrayList<MatchPO>();
		
		if(matchesAbout.size() <= 5)
			return matchesAbout;
		
		//找到最近五场
		int lastedIndex = 0;
		if(matchesAbout.get(lastedIndex).getDate().compareTo("06-01") > 0){
			for(int i = 0; i < 5; i++)
				res.add(matchesAbout.get(matchesAbout.size() - 1 - i));
		}
		else{
			for(; lastedIndex < matchesAbout.size(); lastedIndex++){	
				if(matchesAbout.get(lastedIndex).getDate().compareTo("06-01") > 0)
					break;
			}
			lastedIndex--;
			
			if(lastedIndex >= 4){
				for(int i = 0; i < 5; i++){
					res.add(matchesAbout.get(lastedIndex - i));
				}				
			}
			else{
				for(int i = 0; i <= lastedIndex; i++)
					res.add(matchesAbout.get(i));
				for(int i = 0; i < 5 - res.size(); i++){
					res.add(matchesAbout.get(matchesAbout.size() - 1 - i));
				}
			}
		}
		
		return res;
	}
}

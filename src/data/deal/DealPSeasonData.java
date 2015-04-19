package data.deal;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.PBasicInfoPO;
import po.PSeasonDataPO;
import po.TBasicInfoPO;
import data.GetMatchInfo;
import data.readPOs.ReadMBasicPO;
import data.readPOs.ReadPBasicPO;
import data.readPOs.ReadTBasicPO;

public class DealPSeasonData {
	private static ArrayList<MatchPO> matches;
	private static ArrayList<PBasicInfoPO> players;
	private static ArrayList<TBasicInfoPO> teams;
	private static ArrayList<MatchPO> matchesAbout;
	private GetMatchInfo match;
	
	public DealPSeasonData(){
		match = new GetMatchInfo();
	}
	
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
		    
		    double time = 0;
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
			po.setAlloffensivereboundrate(Math.ceil(po.getAlloffensiverebound() * (48 * po.getGameplay() + time) / po.getAllminute() / (teamlist.get(0) + teamlist.get(4)) * 100)/ 100);
			po.setAlldefensivereboundrate(Math.ceil(po.getAlldefensiverebound() * (48 * po.getGameplay() + time) / po.getAllminute() / (teamlist.get(1) + teamlist.get(5)) * 100)/ 100);
			po.setAllreboundrate(Math.ceil(po.getAllrebound() * (48 * po.getGameplay() + time) / po.getAllminute() / (teamlist.get(2) + teamlist.get(5) + teamlist.get(4)) * 100)/ 100);
			po.setAllassistrate(Math.ceil(po.getAllassist() / (po.getAllminute() / ((48 * po.getGameplay() + time)) * teamlist.get(3) - po.getAllshootmade()) * 100)/ 100);
			
			po.setAllstealrate(Math.ceil(po.getAllsteal() * ((48 * po.getGameplay() + time)) / po.getAllminute() / teamlist.get(4) * 100)/ 100);
			if(teamlist.get(6) - teamlist.get(7) == 0){
				po.setAllblockrate(0);
			}

			else{				
				po.setAllblockrate(Math.ceil(po.getAllblock() * ((48 * po.getGameplay() + time)) / po.getAllminute() / (teamlist.get(6) - teamlist.get(7)) * 100)/ 100);
			}
			
			po.setAllerrorrate(Math.ceil(po.getAllerror() / (po.getAllshoot() - po.getAllthreepoint() + 0.44 * po.getAllfreethrow() + po.getAllerror()) * 100)/ 100);
			
			po.setAllusage(Math.ceil((po.getAllshoot() + 0.44 * po.getAllfreethrow() + po.getAllerror()) * ((48 * po.getGameplay() + time)) / po.getAllminute()  / (teamlist.get(8) + 0.44 * teamlist.get(9) + teamlist.get(10)) * 100)/ 100);
			
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
			
			po.setFieldgoalpercent(po.getAllfieldgoalpercent());
			po.setThreepointpercent(po.getAllthreepointpercent());
			po.setFreethrowpercent(po.getAllfreethrowpercent());
			po.setEfficiency(po.getAllefficiency());
			po.setGmsc(po.getAllgmsc());
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
			for(MatchPO matchpo : match.getLastFiveMatchesAboutPlayer(po.getName())){
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
					po.setPointpromotion(Math.ceil((fivepoint / 5 - fivebeforepoint) / fivebeforepoint * 100) / 100);
				}
				else if(fivepoint == 0){
					po.setPointpromotion(0);
				}
				else{
					po.setPointpromotion(0);
				}
				
				if(fivebeforerebound != 0){
					po.setReboundpromotion(Math.ceil((fiverebound / 5 - fivebeforerebound) / fivebeforerebound * 100) / 100);
				}
				else if(fiverebound == 0){
					po.setReboundpromotion(0);
				}
				else{
					po.setReboundpromotion(0);
				}
				
				if(fivebeforeassist != 0){
					po.setAssistpromotion(Math.ceil((fiveassist / 5 - fivebeforeassist) / fivebeforeassist * 100) / 100);
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

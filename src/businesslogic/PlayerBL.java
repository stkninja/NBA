package businesslogic;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.MatchTeamDataPO;
import po.PBasicInfoPO;
import po.PSeasonDataPO;
import vo.MatchPlayerDataVO;
import vo.MatchTeamDataVO;
import vo.MatchVO;
import vo.PlayerBasicInfoVO;
import vo.PlayerVO;
import data.GetMatchInfo;
import data.GetPlayerInfo;
import dataservice.MatchService;
import dataservice.PlayerService;

public class PlayerBL implements businesslogicservice.PlayerBLService{
	private PlayerService playerdata = null;
	private MatchService matchdata = null;
	
	public PlayerBL(){
		playerdata = new GetPlayerInfo();
		matchdata = new GetMatchInfo();
	}
	
	public ArrayList<PlayerVO> getPlayers(String subArea, String position,
			String team) {
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

	public PlayerBasicInfoVO getOnePlayer(String name) {
		PlayerBasicInfoVO vo = new PlayerBasicInfoVO();
		PBasicInfoPO pp = playerdata.getSinglePBasicInfo(name);
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

	public ArrayList<PlayerVO> getAllPlayers() {
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		for(PSeasonDataPO po : playerdata.getAllPSeasonData("13-14")){
			list.add(potovo(po));
		}
		return list;
	}
	
	private PlayerVO potovo(PSeasonDataPO po){
		PlayerVO vo = new PlayerVO();
		vo.name = po.getName();
		vo.team = po.getTeam();
		vo.season = po.getSeason();
		vo.position = po.getPosition();
		vo.subArea = po.getSubArea();
		vo.gameplay = po.getGameplay();
		vo.gamestart = po.getGamestart();
		vo.allrebound = po.getAllrebound();
		vo.rebound = po.getRebound();
		vo.alloffensiverebound = po.getAlloffensiverebound();
		vo.offensiverebound = po.getOffensiverebound();
		vo.alldefensiverebound = po.getAlldefensiverebound();
		vo.defensiverebound = po.getDefensiverebound();
		vo.allassist = po.getAllassist();
		vo.assist = po.getAssist();
		vo.allminute = po.getAllminute();
		vo.minute = po.getMinute();
		vo.alloffense = po.getAlloffense();
		vo.offense = po.getOffense();
		vo.alldefence = po.getAlldefence();
		vo.defence = po.getDefence();
		vo.allsteal = po.getAllsteal();
		vo.steal = po.getSteal();
		vo.allblock = po.getAllblock();
		vo.block = po.getBlock();
		vo.allerror = po.getAllerror();
		vo.error = po.getError();
		vo.allfoul = po.getAllfoul();
		vo.foul = po.getFoul();
		vo.allpoint = po.getAllpoint();
		vo.point = po.getPoint();
		vo.allshoot = po.getAllshoot();
		vo.shoot = po.getShoot();
		vo.allshootmade = po.getAllshootmade();
		vo.shootmade = po.getShootmade();
		vo.allthreepoint = po.getAllthreepoint();
		vo.threepoint = po.getThreepoint();
		vo.allthreepointmade = po.getAllthreepointmade();
		vo.threepointmade = po.getThreepointmade();
		vo.allfreethrow = po.getAllfreethrow();
	    vo.freethrow = po.getFreethrow();
	    vo.allfreethrowmade = po.getAllfreethrowmade();
	    vo.freethrowmade = po.getFreethrowmade();
	    vo.doubledouble = po.getDoubledouble();
	    vo.allpointReboundAssist = po.getAllpointReboundAssist();
	    vo.pointReboundAssist = po.getPointReboundAssist();
	    vo.allfieldgoalpercent = po.getAllfieldgoalpercent();
	    vo.fieldgoalpercent = po.getFieldgoalpercent();
	    vo.allthreepointpercent = po.getAllthreepointpercent();
	    vo.threepointpercent = po.getThreepointpercent();
	    vo.allfreethrowpercent = po.getAllfreethrowpercent();
	    vo.freethrowpercent = po.getFreethrowpercent();
	    vo.allefficiency = po.getAllefficiency();
	    vo.efficiency = po.getEfficiency();
	    vo.allgmsc = po.getAllgmsc();
	    vo.gmsc = po.getGmsc();
	    vo.allrealshootpercent = po.getAllrealshootpercent();
	    vo.realshootpercent = po.getRealshootpercent();
	    vo.allshootefficiency = po.getAllshootefficiency();
	    vo.shootefficiency = po.getShootefficiency();
	    vo.allreboundrate = po.getAllreboundrate();
	    vo.reboundrate = po.getReboundrate();
	    vo.alloffensivereboundrate = po.getAlloffensivereboundrate();
	    vo.offensivereboundrate = po.getOffensivereboundrate();
	    vo.alldefensivereboundrate = po.getAlldefensivereboundrate();
	    vo.defensivereboundrate = po.getDefensivereboundrate();
	    vo.allassistrate = po.getAllassistrate();
	    vo.assistrate = po.getAssistrate();
	    vo.allstealrate = po.getAllstealrate();
	    vo.stealrate = po.getStealrate();
	    vo.allblockrate = po.getAllblockrate();
	    vo.blockrate = po.getBlockrate();
	    vo.allerrorrate = po.getAllerrorrate();
	    vo.errorrate = po.getErrorrate();
	    vo.allusage = po.getAllusage();
	    vo.usage = po.getUsage();
	    
	    return vo;
	}

	public ArrayList<MatchVO> getLastFiveMatches(String name) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getLastFiveMatchesAboutPlayer(name)){
			list.add(potovo(po));
		}
		return list;
	}

	public ArrayList<MatchVO> getMatchesAboutPlayer(String season, String name) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getAllMatchesAboutPlayer(name, season)){
			list.add(potovo(po));
		}
		return list;
	}

	public PlayerVO getPlayerPast(String season, String name) {
		PlayerVO vo = new PlayerVO();
		vo = potovo(playerdata.getOnePSeasonDataPO(name, season));
		return vo;
	}

	public ArrayList<PlayerVO> getTodayPlayers() {
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		for(MatchPO matchpo : matchdata.getTodayAllMatches()){
			for(MatchPlayerDataPO playerpo : matchpo.getTeam1().getTeamPlayers()){
				PlayerVO vo = new PlayerVO();
				vo.name = playerpo.getName();
				vo.team = matchpo.getTeam1().getAbbName();
				vo.offensiverebound = playerpo.getOffensiveRebounds();
				vo.defensiverebound = playerpo.getDefensiveRebounds();
				vo.assist = playerpo.getAssist();
				vo.minute = playerpo.getMinute();
				vo.steal = playerpo.getSteal();
				vo.block = playerpo.getBlock();
				vo.error = playerpo.getError();
				vo.foul = playerpo.getFoul();
				vo.point = playerpo.getPoint();
				vo.shoot = playerpo.getShoot();
				vo.shootmade = playerpo.getShootmade();
				vo.threepoint = playerpo.getThreepoint();
				vo.threepointmade = playerpo.getThreepointmade();
				vo.freethrow = playerpo.getFreethrow();
				vo.freethrowmade = playerpo.getFreethrowmade();
				vo.gamestart = (int) playerpo.getGameStart();
				vo.rebound = playerpo.getRebound();
				vo.assist = playerpo.getAssist();
				vo.block = playerpo.getBlock();
				vo.steal = playerpo.getSteal();
				list.add(vo);
			}
			
			for(MatchPlayerDataPO playerpo : matchpo.getTeam2().getTeamPlayers()){
				PlayerVO vo = new PlayerVO();
				vo.name = playerpo.getName();
				vo.team = matchpo.getTeam2().getAbbName();
				vo.offensiverebound = playerpo.getOffensiveRebounds();
				vo.defensiverebound = playerpo.getDefensiveRebounds();
				vo.assist = playerpo.getAssist();
				vo.minute = playerpo.getMinute();
				vo.steal = playerpo.getSteal();
				vo.block = playerpo.getBlock();
				vo.error = playerpo.getError();
				vo.foul = playerpo.getFoul();
				vo.point = playerpo.getPoint();
				vo.shoot = playerpo.getShoot();
				vo.shootmade = playerpo.getShootmade();
				vo.threepoint = playerpo.getThreepoint();
				vo.threepointmade = playerpo.getThreepointmade();
				vo.freethrow = playerpo.getFreethrow();
				vo.freethrowmade = playerpo.getFreethrowmade();
				vo.gamestart = (int) playerpo.getGameStart();
				vo.rebound = playerpo.getRebound();
				vo.assist = playerpo.getAssist();
				vo.block = playerpo.getBlock();
				vo.steal = playerpo.getSteal();
				list.add(vo);
			}
		}
		return list;
	}

	public ArrayList<PlayerVO> getSeasonPlayers(String season) {
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		for(PSeasonDataPO po : playerdata.getAllPSeasonData(season)){
			list.add(potovo(po));
		}
		return list;
	}
	
	public MatchVO potovo(MatchPO po){
		MatchVO vo = new MatchVO();
		vo.season = po.getSeason();
		vo.date = po.getDate();
		vo.team1 = potovo(po.getTeam1());
		vo.team2 = potovo(po.getTeam2());
		
		return vo;
	}

	public MatchTeamDataVO potovo(MatchTeamDataPO po){
		MatchTeamDataVO vo = new MatchTeamDataVO();
		vo.abbName = po.getAbbName();
		vo.scores = po.getScores();
		vo.qt1Scores = po.getQt1Scores();
		vo.qt2Scores = po.getQt2Scores();
		vo.qt3Scores = po.getQt3Scores();
		vo.qt4Scores = po.getQt4Scores();
		vo.qtPlusScores = po.getQtPlusScores();
		for(MatchPlayerDataPO mp : po.getTeamPlayers()){
			vo.teamPlayers.add(potovo(mp));
		}
		
		return vo;
	}
	
	public MatchPlayerDataVO potovo(MatchPlayerDataPO po){
		MatchPlayerDataVO vo = new MatchPlayerDataVO();
		vo.name = po.getName();
		vo.offensiveRebounds = po.getOffensiveRebounds();
		vo.defensiveRebounds = po.getDefensiveRebounds();
		vo.assist = po.getAssist();
		vo.minute = po.getMinute();
		vo.steal = po.getSteal();
		vo.block = po.getBlock();
		vo.error = po.getError();
		vo.foul = po.getFoul();
		vo.point = po.getPoint();
		vo.shoot = po.getShoot();
		vo.shootmade = po.getShootmade();
		vo.threepoint = po.getThreepoint();
		vo.threepointmade = po.getThreepointmade();
		vo.freethrow = po.getFreethrow();
		vo.freethrowmade = po.getFreethrowmade();
		vo.gameStart = po.getGameStart();
		
		return vo;
	}
	
	private PlayerVO findMax(ArrayList<PlayerVO> templist,String filter){
		PlayerVO max = new PlayerVO();
		max = templist.get(0);
		for(int i = 0;i < templist.size();i ++){
			if(filter.equals("得分")){
				if(max.point < templist.get(i).point){
					max = templist.get(i);
				}
			}
			else if(filter.equals("篮板")){
				if(max.rebound < templist.get(i).rebound){
					max = templist.get(i);
				}
			}
			else if(filter.equals("助攻")){
				if(max.assist < templist.get(i).assist){
					max = templist.get(i);
				}
			}
			else if(filter.equals("盖帽")){
				if(max.block < templist.get(i).block){
					max = templist.get(i);
				}
			}
			else if(filter.equals("抢断")){
				if(max.steal < templist.get(i).steal){
					max = templist.get(i);
				}
			}
			else if(filter.equals("三分命中率")){
				if(max.allthreepointpercent < templist.get(i).allthreepointpercent){
					max = templist.get(i);
				}
			}
			else if(filter.equals("投篮命中率")){
				if(max.allfieldgoalpercent < templist.get(i).allfieldgoalpercent){
					max = templist.get(i);
				}
			}
			else if(filter.equals("罚球命中率")){
				if(max.allfreethrowpercent < templist.get(i).allfreethrowpercent){
					max = templist.get(i);
				}
			}
		}
		return max;
	}
	
	private ArrayList<PlayerVO> sort(ArrayList<PlayerVO> templist,String filter){
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		PlayerVO vo1 = findMax(templist,filter);
		list.add(vo1);
		templist.remove(vo1);
		if(templist.size() == 0){
			return list;
		}
		PlayerVO vo2 = findMax(templist,filter);
		list.add(vo2);
		templist.remove(vo2);
		if(templist.size() == 0){
			return list;
		}
		PlayerVO vo3 = findMax(templist,filter);
		list.add(vo3);
		templist.remove(vo3);
		if(templist.size() == 0){
			return list;
		}
		PlayerVO vo4 = findMax(templist,filter);
		list.add(vo4);
		templist.remove(vo4);
		if(templist.size() == 0){
			return list;
		}
		PlayerVO vo5 = findMax(templist,filter);
		list.add(vo5);
		templist.remove(vo5);
		return list;
	}

	public ArrayList<PlayerVO> getTodayTopFivePlayers(String filter) {
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> templist = getTodayPlayers();
		list = sort(templist,filter);
		return list;
	}
	
	public ArrayList<PlayerVO> getSeasonTopFivePlayers(String season,
			String filter) {
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> templist = getSeasonPlayers(season);
		list = sort(templist,filter);
		return list;
	}
	
	private PlayerVO findPromotionMax(ArrayList<PlayerVO> templist,String filter){
		PlayerVO max = new PlayerVO();
		max = templist.get(0);
		for(int i = 0;i < templist.size();i ++){
			if(filter.equals("场均得分")){
				if(max.pointpromotion < templist.get(i).pointpromotion){
					max = templist.get(i);
				}
			}
			else if(filter.equals("场均篮板")){
				if(max.reboundpromotion < templist.get(i).reboundpromotion){
					max = templist.get(i);
				}
			}
			else if(filter.equals("场均助攻")){
				if(max.assistpromotion < templist.get(i).assistpromotion){
					max = templist.get(i);
				}
			}
		}
		return max;
	}
	
	private ArrayList<PlayerVO> sortPromotion(ArrayList<PlayerVO> templist,String filter){
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		PlayerVO vo1 = findPromotionMax(templist,filter);
		list.add(vo1);
		templist.remove(vo1);
		if(templist.size() == 0){
			return list;
		}
		PlayerVO vo2 = findPromotionMax(templist,filter);
		list.add(vo2);
		templist.remove(vo2);
		if(templist.size() == 0){
			return list;
		}
		PlayerVO vo3 = findPromotionMax(templist,filter);
		list.add(vo3);
		templist.remove(vo3);
		if(templist.size() == 0){
			return list;
		}
		PlayerVO vo4 = findPromotionMax(templist,filter);
		list.add(vo4);
		templist.remove(vo4);
		if(templist.size() == 0){
			return list;
		}
		PlayerVO vo5 = findPromotionMax(templist,filter);
		list.add(vo5);
		templist.remove(vo5);
		return list;
	}

	public ArrayList<PlayerVO> getPromotionPlayers(String filter) {
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> templist = getSeasonPlayers(matchdata.getLastSeason());
		list = sortPromotion(templist,filter);
		return list;
	}
}

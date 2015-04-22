package businesslogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.PBasicInfoPO;
import po.PSeasonDataPO;
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
	private MatchBL matchbl = null;
	
	public PlayerBL(){
		playerdata = new GetPlayerInfo();
		matchdata = new GetMatchInfo();
		matchbl = new MatchBL();
	}
	
	public ArrayList<PlayerVO> getPlayers(String season,String subArea, String position,
			String team) {
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		if(subArea.equals("All")){
			if(position.equals("All")){
				if(team.equals("All")){
					return getSeasonPlayers(season);
				}
				else{
					for(PlayerVO vo : getSeasonPlayers(season)){
						if(vo.team.equals(team)){
							list.add(vo);
						}
					}
				}
			}
			else{
				if(team.equals("All")){
					for(PlayerVO vo : getSeasonPlayers(season)){
						if(vo.position.indexOf(position) >= 0){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getSeasonPlayers(season)){
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
					for(PlayerVO vo : getSeasonPlayers(season)){
						if(vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getSeasonPlayers(season)){
						if(vo.team.equals(team) && vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
			}
			else{
				if(team.equals("All")){
					for(PlayerVO vo : getSeasonPlayers(season)){
						if(vo.position.indexOf(position) >= 0 && vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getSeasonPlayers(season)){
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
		if(pp == null){
			return vo;
		}
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
	    vo.pointpromotion = po.getPointpromotion();
	    vo.reboundpromotion = po.getReboundpromotion();
	    vo.assistpromotion = po.getAssistpromotion();
	    
	    return vo;
	}

	public ArrayList<MatchVO> getLastFiveMatches(String name) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getLastFiveMatchesAboutPlayer(name)){
			list.add(matchbl.potovo(po));
		}
		return list;
	}

	public PlayerVO getPlayerPast(String name) {
		PlayerVO vo = new PlayerVO();
		for(PSeasonDataPO po : playerdata.getAllPSeasonData("all")){
			if(po.getName().equals(name)){
				vo = potovo(po);
			}
		}
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
	
	private PlayerVO findMax(ArrayList<PlayerVO> templist,String filter){
		PlayerVO max = new PlayerVO();
		if(templist.size() == 0){
			return null;
		}
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
		if(vo1 == null){
			return list;
		}
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
	
	public double getAttribute(PlayerVO vo,String filter){
		double res = 0;
		switch (filter){
		case "参赛场数":
			res = vo.gameplay;
			break;
		case "先发场数":
			res = vo.gamestart;
		    break;
		case "篮板数":
			res = vo.allrebound;
			break;
		case "助攻数":
			res = vo.allassist;
			break;
		case "在场时间":
			res = vo.allminute;
			break;
		case "投篮命中率":
			res = vo.allfieldgoalpercent;
			break;
		case "三分命中率":
			res = vo.allthreepointpercent;
			break;
		case "罚球命中率":
			res = vo.allfreethrowpercent;
			break;
		case "进攻数":
			res = vo.alloffense;
			break;
		case "防守数":
			res = vo.alldefence;
			break;
		case "抢断数":
			res = vo.allsteal;
			break;
		case "盖帽数":
			res = vo.allblock;
			break;
		case "失误数":
			res = vo.allerror;
			break;
		case "犯规数":
			res = vo.allfoul;
			break;
		case "得分":
			res = vo.allpoint;
			break;
		case "效率":
			res = vo.allefficiency;
			break;
		case "GmSc效率值":
			res = vo.allgmsc;
			break;
		case "真实命中率":
			res = vo.allrealshootpercent;
			break;
		case "投篮效率":
			res = vo.allshootefficiency;
			break;
		case "篮板率":
			res = vo.allreboundrate;
			break;
		case "进攻篮板率":
			res = vo.alloffensivereboundrate;
			break;
		case "防守篮板率":
			res = vo.alldefensivereboundrate;
			break;
		case "助攻率":
			res = vo.allassistrate;
			break;
		case "抢断率":
			res = vo.allstealrate;
			break;
		case "盖帽率":
			res = vo.allblockrate;
			break;
		case "失误率":
			res = vo.allerrorrate;
			break;
		case "使用率":
			res = vo.allusage;
			break;
		case "得分/篮板/助攻":
			res = vo.allpointReboundAssist;
			break;
		case "场均篮板数":
			res = vo.rebound;
			break;
		case "场均助攻数":
			res = vo.assist;
			break;
		case "场均在场时间":
			res = vo.minute;
			break;
		case "场均投篮命中率":
			res = vo.fieldgoalpercent;
			break;
		case "场均三分命中率":
			res = vo.threepointpercent;
			break;
		case "场均罚球命中率":
			res = vo.freethrowpercent;
			break;
		case "场均进攻数":
			res = vo.offense;
			break;
		case "场均防守数":
			res = vo.defence;
			break;
		case "场均抢断数":
			res = vo.steal;
			break;
		case "场均盖帽数":
			res = vo.block;
			break;
		case "场均失误数":
			res = vo.error;
			break;
		case "场均犯规数":
			res = vo.foul;
			break;
		case "场均得分":
			res = vo.point;
			break;
		case "场均效率":
			res = vo.efficiency;
			break;
		case "场均GmSc效率值":
			res = vo.gmsc;
			break;
		case "场均真实命中率":
			res = vo.realshootpercent;
			break;
		case "场均投篮效率":
			res = vo.shootefficiency;
			break;
		case "场均篮板率":
			res = vo.reboundrate;
			break;
		case "场均进攻篮板率":
			res = vo.offensivereboundrate;
			break;
		case "场均防守篮板率":
			res = vo.defensivereboundrate;
			break;
		case "场均助攻率":
			res = vo.assistrate;
			break;
		case "场均抢断率":
			res = vo.stealrate;
			break;
		case "场均盖帽率":
			res = vo.blockrate;
			break;
		case "场均失误率":
			res = vo.errorrate;
			break;
		case "场均使用率":
			res = vo.usage;
			break;
		case "场均得分/篮板/助攻":
			res = vo.pointReboundAssist;
			break;
		case "两双":
			res = vo.doubledouble;
			break;
		}
		return res;		
	}
	
	public ArrayList<PlayerVO> sortPlayer(ArrayList<PlayerVO> list,ArrayList<String> filter,String sortOrder){
		Comparator<PlayerVO> comparator = new Comparator<PlayerVO>(){
			public int compare(PlayerVO vo1, PlayerVO vo2) {
				int res = 0;
				for(int i = 0;i < filter.size();i ++){
					if(filter.get(i).equals("无")){
						break;
					}
					if(filter.get(i).equals("球员名称")){
						if(!vo1.name.equals(vo2.name)){
							res = vo1.name.compareTo(vo2.name);
							break;
						}
						else{
							continue;
						}
					}
					else if(filter.get(i).equals("所属球队")){
						if(!vo1.team.equals(vo2.team)){
							res = vo1.team.compareTo(vo2.team);
							break;
						}
						else{
							continue;
						}
					}
					else{
						if(getAttribute(vo1,filter.get(i)) != getAttribute(vo2,filter.get(i))){
							if(getAttribute(vo1,filter.get(i)) > getAttribute(vo2,filter.get(i))){
								res = 1;
							}
							else{
								res = -1;
							}
//							res = (int) ((int)getAttribute(vo1,filter.get(i)) - getAttribute(vo2,filter.get(i)));
							break;
						}
						else{
							continue;
						}
					}
				}
				return res;
			}
		};
		Collections.sort(list,comparator);
		if(sortOrder.equals("升序")){
			return list;
		}
		else{
			ArrayList<PlayerVO> reslist = new ArrayList<PlayerVO>();
			for(int j = list.size() - 1;j >= 0;j --){
				reslist.add(list.get(j));
			}
			return reslist;
		}
	}

	public PlayerVO getPlayerVO(String name) {
		PlayerVO vo = new PlayerVO();
		vo = potovo(playerdata.getOnePSeasonDataPO(name, matchdata.getLastSeason()));
		return vo;
	}

	public ArrayList<PlayerVO> getAllSeasonPlayer(String name) {
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		for(String season : matchdata.getExistedSeasons()){
			list.add(potovo(playerdata.getOnePSeasonDataPO(name,season)));
		}
		return list;
	}
	
	public ArrayList<PlayerVO> getAllSeasonPlayer(){
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		for(PSeasonDataPO po : playerdata.getAllPSeasonData("all")){
			list.add(potovo(po));
		}
		return list;
	}
	
	public int age(PlayerVO vo){
		return Integer.parseInt(getOnePlayer(vo.name).age);
	}
	
	public ArrayList<PlayerVO> getFilterPlayers(String position,String league,String age){
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		if(league.equals("West")){
			if(age.equals("All")){
				for(PlayerVO vo1 : getPlayers("all","Southwest",position,"All")){
					list.add(vo1);
				}
				for(PlayerVO vo2 : getPlayers("all","Northwest",position,"All")){
					list.add(vo2);
				}
				for(PlayerVO vo3 : getPlayers("all","Pacific",position,"All")){
					list.add(vo3);
				}
			}
			else if(age.equals("<=22")){
				for(PlayerVO vo1 : getPlayers("all","Southwest",position,"All")){
					if(age(vo1) <= 22){
						list.add(vo1);
					}
				}
				for(PlayerVO vo2 : getPlayers("all","Northwest",position,"All")){
					if(age(vo2) <= 22){
						list.add(vo2);
					}
				}
				for(PlayerVO vo3 : getPlayers("all","Pacific",position,"All")){
					if(age(vo3) <= 22){
						list.add(vo3);
					}
				}
			}
			else if(age.equals("22< X <=25")){
				for(PlayerVO vo1 : getPlayers("all","Southwest",position,"All")){
					if(age(vo1) > 22 && age(vo1) <= 25){
						list.add(vo1);
					}
				}
				for(PlayerVO vo2 : getPlayers("all","Northwest",position,"All")){
					if(age(vo2) > 22 && age(vo2) <= 25){
						list.add(vo2);
					}
				}
				for(PlayerVO vo3 : getPlayers("all","Pacific",position,"All")){
					if(age(vo3) > 22 && age(vo3) <= 25){
						list.add(vo3);
					}
				}
			}
			else if(age.equals("25< X <=30")){
				for(PlayerVO vo1 : getPlayers("all","Southwest",position,"All")){
					if(age(vo1) > 25 && age(vo1) <= 30){
						list.add(vo1);
					}
				}
				for(PlayerVO vo2 : getPlayers("all","Northwest",position,"All")){
					if(age(vo2) > 25 && age(vo2) <= 30){
						list.add(vo2);
					}
				}
				for(PlayerVO vo3 : getPlayers("all","Pacific",position,"All")){
					if(age(vo3) > 25 && age(vo3) <= 30){
						list.add(vo3);
					}
				}
			}
			else if(age.equals(">30")){
				for(PlayerVO vo1 : getPlayers("all","Southwest",position,"All")){
					if(age(vo1) > 30){
						list.add(vo1);
					}
				}
				for(PlayerVO vo2 : getPlayers("all","Northwest",position,"All")){
					if(age(vo2) > 30){
						list.add(vo2);
					}
				}
				for(PlayerVO vo3 : getPlayers("all","Pacific",position,"All")){
					if(age(vo3) > 30){
						list.add(vo3);
					}
				}
			}
		}
		else if(league.equals("East")){
			if(age.equals("All")){
				for(PlayerVO vo1 : getPlayers("all","Atlantic",position,"All")){
					list.add(vo1);
				}
				for(PlayerVO vo2 : getPlayers("all","Central",position,"All")){
					list.add(vo2);
				}
				for(PlayerVO vo3 : getPlayers("all","Southeast",position,"All")){
					list.add(vo3);
				}
			}
			else if(age.equals("<=22")){
				for(PlayerVO vo1 : getPlayers("all","Atlantic",position,"All")){
					if(age(vo1) <= 22){
						list.add(vo1);
					}
				}
				for(PlayerVO vo2 : getPlayers("all","Central",position,"All")){
					if(age(vo2) <= 22){
						list.add(vo2);
					}
				}
				for(PlayerVO vo3 : getPlayers("all","Southeast",position,"All")){
					if(age(vo3) <= 22){
						list.add(vo3);
					}
				}
			}
			else if(age.equals("22< X <=25")){
				for(PlayerVO vo1 : getPlayers("all","Atlantic",position,"All")){
					if(age(vo1) > 22 && age(vo1) <= 25){
						list.add(vo1);
					}
				}
				for(PlayerVO vo2 : getPlayers("all","Central",position,"All")){
					if(age(vo2) > 22 && age(vo2) <= 25){
						list.add(vo2);
					}
				}
				for(PlayerVO vo3 : getPlayers("all","Southeast",position,"All")){
					if(age(vo3) > 22 && age(vo3) <= 25){
						list.add(vo3);
					}
				}
			}
			else if(age.equals("25< X <=30")){
				for(PlayerVO vo1 : getPlayers("all","Atlantic",position,"All")){
					if(age(vo1) > 25 && age(vo1) <= 30){
						list.add(vo1);
					}
				}
				for(PlayerVO vo2 : getPlayers("all","Central",position,"All")){
					if(age(vo2) > 25 && age(vo2) <= 30){
						list.add(vo2);
					}
				}
				for(PlayerVO vo3 : getPlayers("all","Southeast",position,"All")){
					if(age(vo3) > 25 && age(vo3) <= 30){
						list.add(vo3);
					}
				}
			}
			else if(age.equals(">30")){
				for(PlayerVO vo1 : getPlayers("all","Atlantic",position,"All")){
					if(age(vo1) > 30){
						list.add(vo1);
					}
				}
				for(PlayerVO vo2 : getPlayers("all","Central",position,"All")){
					if(age(vo2) > 30){
						list.add(vo2);
					}
				}
				for(PlayerVO vo3 : getPlayers("all","Southeast",position,"All")){
					if(age(vo3) > 30){
						list.add(vo3);
					}
				}
			}
		}
		else if(league.equals("All")){
			for(PlayerVO vo : getPlayers("all","All",position,"All")){
				if(age.equals("All")){
					list.add(vo);
				}
				else if(age.equals("<=22")){
					if(age(vo) <= 22){
						list.add(vo);
					}
				}
				else if(age.equals("22< X <=25")){
					if(age(vo) > 22 && age(vo) <= 25){
						list.add(vo);
					}
				}
				else if(age.equals("25< X <=30")){
					if(age(vo) > 25 && age(vo) <= 30){
						list.add(vo);
					}
				}
				else if(age.equals(">30")){
					if(age(vo) > 30){
						list.add(vo);
					}
				}
			}
		}
		return list;
	}

	public ArrayList<String> getFilters() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("无");
		list.add("球员名称");
		list.add("所属球队");
		list.add("参赛场数");
		list.add("先发场数");
		list.add("篮板数");
		list.add("助攻数");
		list.add("在场时间");
		list.add("投篮命中率");
		list.add("三分命中率");
		list.add("罚球命中率");
		list.add("进攻数");
		list.add("防守数");
		list.add("抢断数");
		list.add("盖帽数");
		list.add("失误数");
		list.add("犯规数");
		list.add("得分");
		list.add("效率");
		list.add("GmSc效率值");
		list.add("真实命中率");
		list.add("投篮效率");
		list.add("篮板率");
		list.add("进攻篮板率");
		list.add("防守篮板率");
		list.add("助攻率");
		list.add("抢断率");
		list.add("盖帽率");
		list.add("失误率");
		list.add("使用率");
		list.add("场均得分");
		list.add("场均篮板数");
		list.add("场均助攻数");
		list.add("场均在场时间");
		list.add("场均抢断数");
		list.add("场均盖帽数");
		list.add("场均失误数");
		list.add("场均犯规数");
		list.add("场均得分/篮板/助攻");
		list.add("得分/篮板/助攻");
		list.add("两双");
		
		return list;
	}

	public ArrayList<PlayerVO> getPlayersInfo(String season,String name) {
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		for(String playername : playerdata.getAllPlayersName()){
			if(playername.indexOf(name) >= 0){
				list.add(potovo(playerdata.getOnePSeasonDataPO(playername, season)));
			}
		}
		return list;
	}
}

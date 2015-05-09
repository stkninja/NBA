package test;

import java.util.ArrayList;

import data.GetMatchInfo;
import dataservice.MatchService;
import businesslogic.PlayerBL;
import businesslogic.TeamBL;
import test.data.PlayerHighInfo;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.PlayerNormalInfo;
import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;
import vo.PlayerVO;
import vo.TeamVO;

public class Calculate {
	PlayerBL playerbl = null;
	TeamBL teambl = null;
	Conversion con = null;
	Parse p = null;
	private MatchService matchdata = null;
	
	public Calculate(){
		playerbl = new PlayerBL();
		teambl = new TeamBL();
		con = new Conversion();
		p = new Parse();
		matchdata = new GetMatchInfo();
	}
	
	public String change(String filter){
		String res = "";
		switch (filter){
		case "point":
			res = "场均得分";
			break;
		case "score":
			res = "场均得分";
			break;
		case "rebound":
			res = "场均篮板数"; 
			break;
		case "assist":
			res = "场均助攻数";
			break;
		case "blockShot":
			res = "场均盖帽数";
			break;
		case "steal":
			res = "场均抢断数";
			break;
		case "foul":
			res = "场均犯规数";
			break;
		case "fault":
			res = "场均失误数";
			break;
		case "minute":
			res = "场均在场时间";
			break;
		case "efficient":
			res = "效率";
			break;
		case "shot":
			res = "投篮命中率";
			break;
		case "three":
			res = "三分命中率";
			break;
		case "penalty":
			res = "罚球命中率";
			break;
		case "doubleTwo":
			res = "两双";
			break;
		case "realShot":
			res = "真实命中率";
			break;
		case "GmSc":
			res = "GmSc效率值";
			break;
		case "shotEfficient":
			res = "投篮效率";
			break;
		case "reboundEfficient":
			res = "篮板率";
			break;
		case "offendReboundEfficient":
			res = "进攻篮板率";
			break;
		case "defendReboundEfficient":
			res = "防守篮板率";
			break;
		case "assistEfficient":
			res = "助攻率";
			break;
		case "stealEfficient":
			res = "抢断率";
			break;
		case "blockShotEfficient":
			res = "盖帽率";
			break;
		case "faultEfficient":
			res = "失误率";
			break;
		case "frequency":
			res = "使用率";
			break;
			
			
		case "defendRebound":
			res = "场均防守篮板数";
			break;
		case "offendRebound":
			res = "场均进攻篮板数";
			break;
		case "winRate":
			res = "胜率";
			break;
		case "offendRound":
			res = "场均进攻回合";
			break;
		case "offendEfficient":
			res = "进攻效率";
			break;
		case "defendEfficient":
			res = "防守效率";
			break;
		}
		return res;
	}
	
	public String changeHot(String filter){
		String res = "";
		switch (filter){
		case "score":
			res = "场均得分";
			break;
		case "rebound":
			res = "场均篮板";
			break;
		case "assist":
			res = "场均助攻";
			break;
		}
		return res;
	}
	
	public String changetosortHot(String filter){
		String res = "";
		switch (filter){
		case "score":
			res = "近5场得分提升率";
			break;
		case "rebound":
			res = "近5场篮板提升率";
			break;
		case "assist":
			res = "近5场助攻提升率";
			break;
		}
		return res;
	}
	
	public String changeKing(String filter){
		String res = "";
		switch (filter){
		case "score":
			res = "得分";
			break;
		case "rebound":
			res = "篮板";
			break;
		case "assist":
			res = "助攻";
			break;
		}
		return res;
	}
	
	public ArrayList<PlayerVO> getPlayerFilterPlayerVO(String s){
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		String str[] = p.filter(s);
		list = playerbl.getFilterPlayers(str[2],str[4],str[6]);
		return list;
	}
	
	public ArrayList<PlayerVO> sortPlayer(String s,ArrayList<PlayerVO> temp){
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		ArrayList<String> filter = new ArrayList<String>();
		ArrayList<String> sortOrder = new ArrayList<String>();
		ArrayList<String> sortParse = p.sort(s);
		for(int i = 0;i < sortParse.size();i ++){
			filter.add(change(sortParse.get(i)));
			sortOrder.add(sortParse.get(i + 1));
			i ++;
		}
		filter.add("球员名称");
		sortOrder.add("asc");
		list = playerbl.sortPlayer(temp, filter, sortOrder);
		return list;
	}
	
	public ArrayList<PlayerNormalInfo> getPlayerNormal(String s){
		ArrayList<PlayerNormalInfo> list = new ArrayList<PlayerNormalInfo>();
		ArrayList<PlayerVO> temp = sortPlayer(s,getPlayerFilterPlayerVO(s));
		if(getPlayerFilterPlayerVO(s).size() < p.number(s)){
			for(PlayerVO vo : temp){
				list.add(con.playervotoNormal(vo,s));
			}
		}
		else{
			for(int i = 0;i < p.number(s);i ++){
				list.add(con.playervotoNormal(temp.get(i), s));
			}
		}
		return list;
	}
	
	public ArrayList<PlayerHotInfo> getPlayerHot(String s){
		ArrayList<PlayerHotInfo> list = new ArrayList<PlayerHotInfo>();
		ArrayList<PlayerVO> topfive = playerbl.getPromotionPlayers(changeHot(p.allorHotorKing(s)));
		if(s.indexOf("-n") < 0){
			for(PlayerVO vo1 : topfive){
				list.add(con.playervotoHot(vo1, s));
			}
		}
		else{
			ArrayList<String> filter = new ArrayList<String>();
			ArrayList<String> sortOrder = new ArrayList<String>();
			filter.add(changetosortHot(p.allorHotorKing(s)));
			filter.add("球员名称");
			sortOrder.add("desc");
			sortOrder.add("asc");
			ArrayList<PlayerVO> sortP = playerbl.sortPlayer(playerbl.getSeasonPlayers(matchdata.getLastSeason()), filter, sortOrder);
			if(sortP.size() < p.number(s)){
				for(PlayerVO vo2 : sortP){
					list.add(con.playervotoHot(vo2, s));
				}
			}
			else{
				for(int i = 0;i < p.number(s);i ++){
					list.add(con.playervotoHot(sortP.get(i),s));
				}
			}
		}
		return list;
	}
	
	public ArrayList<PlayerKingInfo> getPlayerKing(String s){
		ArrayList<PlayerKingInfo> list = new ArrayList<PlayerKingInfo>();
		if(p.allorHotorKing(s).indexOf("daily") >= 0){
			if(s.indexOf("-n") < 0){
				ArrayList<PlayerVO> dailytopfive = playerbl.getTodayTopFivePlayers(changeKing(p.allorHotorKing(s).split(" ")[0]));
				for(PlayerVO vo1 : dailytopfive){
					list.add(con.playervotoKing(vo1, s));
				}
			}
			else{
				ArrayList<String> filter = new ArrayList<String>();
				ArrayList<String> sortOrder = new ArrayList<String>();
				filter.add(change(p.allorHotorKing(s).split(" ")[0]));
				filter.add("球员名称");
				sortOrder.add("desc");
				sortOrder.add("asc");
				ArrayList<PlayerVO> sortPdaily = playerbl.sortPlayer(playerbl.getTodayPlayers(), filter, sortOrder);
				if(sortPdaily.size() < p.number(s)){
					for(PlayerVO vo2 : sortPdaily){
						list.add(con.playervotoKing(vo2, s));
					}
				}
				else{
					for(int i = 0;i < p.number(s);i ++){
						list.add(con.playervotoKing(sortPdaily.get(i),s));
					}
				}
			}
		}
		else{
			if(s.indexOf("-n") < 0){
				ArrayList<PlayerVO> seasontopfive = playerbl.getSeasonTopFivePlayers(matchdata.getLastSeason(), changeKing(p.allorHotorKing(s).split(" ")[0]));
				for(PlayerVO vo3 : seasontopfive){
					list.add(con.playervotoKing(vo3, s));
				}
			}
			else{
				ArrayList<String> filter = new ArrayList<String>();
				ArrayList<String> sortOrder = new ArrayList<String>();
				filter.add(change(p.allorHotorKing(s).split(" ")[0]));
				filter.add("球员名称");
				sortOrder.add("desc");
				sortOrder.add("asc");
				ArrayList<PlayerVO> sortPseason = playerbl.sortPlayer(playerbl.getSeasonPlayers(matchdata.getLastSeason()), filter, sortOrder);
				if(sortPseason.size() < p.number(s)){
					for(PlayerVO vo4 : sortPseason){
						list.add(con.playervotoKing(vo4, s));
					}
				}
				else{
					for(int i = 0;i < p.number(s);i ++){
						list.add(con.playervotoKing(sortPseason.get(i),s));
					}
				}
			}
		}
		return list;
	}
	
	public ArrayList<PlayerHighInfo> getPlayerHigh(String s){
		ArrayList<PlayerHighInfo> list = new ArrayList<PlayerHighInfo>();
		ArrayList<PlayerVO> temp = sortPlayer(s,getPlayerFilterPlayerVO(s));
		if(getPlayerFilterPlayerVO(s).size() < p.number(s)){
			for(PlayerVO vo : temp){
				list.add(con.playervotoHigh(vo));
			}
		}
		else{
			for(int i = 0;i < p.number(s);i ++){
				list.add(con.playervotoHigh(temp.get(i)));
			}
		}
		return list;
	}
	
	public ArrayList<TeamVO> sortTeam(String s,ArrayList<TeamVO> temp){
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		ArrayList<String> filter = new ArrayList<String>();
		ArrayList<String> sortOrder = new ArrayList<String>();
		ArrayList<String> sortParse = p.sort(s);
		for(int i = 0;i < sortParse.size();i ++){
			filter.add(change(sortParse.get(i)));
			sortOrder.add(sortParse.get(i + 1));
			i ++;
		}
		filter.add("球队名称");
		sortOrder.add("asc");
		list = teambl.sortTeam(temp, filter, sortOrder);
		return list;
	}
	
	public ArrayList<TeamNormalInfo> getTeamNormal(String s){
		ArrayList<TeamNormalInfo> list = new ArrayList<TeamNormalInfo>();
		ArrayList<TeamVO> temp = teambl.getSeasonTeams(matchdata.getLastSeason());
        ArrayList<TeamVO> sortT = sortTeam(s,temp);
		if(p.number(s) > 30){
			for(TeamVO vo : sortT){
				list.add(con.teamvotoNormal(vo,s));
			}
		}
		else{
			for(int i = 0;i < p.number(s);i ++){
				list.add(con.teamvotoNormal(sortT.get(i), s));
			}
		}
		return list;
	}
	
	public ArrayList<TeamHotInfo> getTeamHot(String s){
		ArrayList<TeamHotInfo> list = new ArrayList<TeamHotInfo>();
		ArrayList<String> filter = new ArrayList<String>();
		ArrayList<String> sortOrder = new ArrayList<String>();
		filter.add(change(p.allorHotorKing(s)));
		filter.add("球队名称");
		sortOrder.add("desc");
		sortOrder.add("asc");
		ArrayList<TeamVO> sortT = teambl.sortTeam(teambl.getSeasonTeams(matchdata.getLastSeason()), filter, sortOrder);
		if(s.indexOf("-n") < 0){
			for(int i = 0;i < 5;i ++){
				list.add(con.teamvotoHot(sortT.get(i),s));
			}
		}
		else{
			for(int i = 0;i < p.number(s);i ++){
				list.add(con.teamvotoHot(sortT.get(i),s));
			}
		}
		return list;
	}
	
	public ArrayList<TeamHighInfo> getTeamHigh(String s){
		ArrayList<TeamHighInfo> list = new ArrayList<TeamHighInfo>();
		ArrayList<TeamVO> temp = teambl.getSeasonTeams(matchdata.getLastSeason());
        ArrayList<TeamVO> sortT = sortTeam(s,temp);
        if(p.number(s) > 30){
			for(TeamVO vo : sortT){
				list.add(con.teamvotoHigh(vo,s));
			}
		}
		else{
			for(int i = 0;i < p.number(s);i ++){
				list.add(con.teamvotoHigh(sortT.get(i), s));
			}
		}
		return list;
	}
	
}

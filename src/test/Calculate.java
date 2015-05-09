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
			res = "�����÷�";
			break;
		case "score":
			res = "�����÷�";
			break;
		case "rebound":
			res = "����������"; 
			break;
		case "assist":
			res = "����������";
			break;
		case "blockShot":
			res = "������ñ��";
			break;
		case "steal":
			res = "����������";
			break;
		case "foul":
			res = "����������";
			break;
		case "fault":
			res = "����ʧ����";
			break;
		case "minute":
			res = "�����ڳ�ʱ��";
			break;
		case "efficient":
			res = "Ч��";
			break;
		case "shot":
			res = "Ͷ��������";
			break;
		case "three":
			res = "����������";
			break;
		case "penalty":
			res = "����������";
			break;
		case "doubleTwo":
			res = "��˫";
			break;
		case "realShot":
			res = "��ʵ������";
			break;
		case "GmSc":
			res = "GmScЧ��ֵ";
			break;
		case "shotEfficient":
			res = "Ͷ��Ч��";
			break;
		case "reboundEfficient":
			res = "������";
			break;
		case "offendReboundEfficient":
			res = "����������";
			break;
		case "defendReboundEfficient":
			res = "����������";
			break;
		case "assistEfficient":
			res = "������";
			break;
		case "stealEfficient":
			res = "������";
			break;
		case "blockShotEfficient":
			res = "��ñ��";
			break;
		case "faultEfficient":
			res = "ʧ����";
			break;
		case "frequency":
			res = "ʹ����";
			break;
			
			
		case "defendRebound":
			res = "��������������";
			break;
		case "offendRebound":
			res = "��������������";
			break;
		case "winRate":
			res = "ʤ��";
			break;
		case "offendRound":
			res = "���������غ�";
			break;
		case "offendEfficient":
			res = "����Ч��";
			break;
		case "defendEfficient":
			res = "����Ч��";
			break;
		}
		return res;
	}
	
	public String changeHot(String filter){
		String res = "";
		switch (filter){
		case "score":
			res = "�����÷�";
			break;
		case "rebound":
			res = "��������";
			break;
		case "assist":
			res = "��������";
			break;
		}
		return res;
	}
	
	public String changetosortHot(String filter){
		String res = "";
		switch (filter){
		case "score":
			res = "��5���÷�������";
			break;
		case "rebound":
			res = "��5������������";
			break;
		case "assist":
			res = "��5������������";
			break;
		}
		return res;
	}
	
	public String changeKing(String filter){
		String res = "";
		switch (filter){
		case "score":
			res = "�÷�";
			break;
		case "rebound":
			res = "����";
			break;
		case "assist":
			res = "����";
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
		filter.add("��Ա����");
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
			filter.add("��Ա����");
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
				filter.add("��Ա����");
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
				filter.add("��Ա����");
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
		filter.add("�������");
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
		filter.add("�������");
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

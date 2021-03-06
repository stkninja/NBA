package businesslogic;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.MatchTeamDataPO;
import po.TSeasonDataPO;
import vo.MatchPlayerDataVO;
import vo.MatchTeamDataVO;
import vo.MatchVO;
import data.GetMatchInfo;
import data.GetTeamInfo;
import dataservice.MatchService;
import dataservice.TeamService;

public class MatchBL implements businesslogicservice.MatchBLService{
	private MatchService matchdata = null;
	private TeamService teamdata = null;
	
	public MatchBL(){
		matchdata = new GetMatchInfo();
		teamdata = new GetTeamInfo();
	}

	public ArrayList<MatchVO> getMatches(String season,String isPlayOffs) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getAllMatchesAtSeason(season)){
			if(po.getIsPlayOffs().equals(isPlayOffs)){
				list.add(potovo(po));
			} else if (isPlayOffs.equals("All")) {
				list.add(potovo(po));
			}
		}
		return list;
	}
	
/*	public ArrayList<MatchVO> getOneMatch(String team1, String team2,
			String season) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getMatchesAboutTwoTeams(team1, team2, season)){
			list.add(potovo(po));
		}
		return list;
	}*/
	
	public ArrayList<String> getAllTeamsName(String season){
		ArrayList<String> list = new ArrayList<String>();
		for(TSeasonDataPO po : teamdata.getAllTSeasonData(season)){
			list.add(po.getAbbName());
		}
		return list;
	}

	public ArrayList<MatchVO> getMatchesAboutTeam(String team, String season) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(String teamname : getAllTeamsName(season)){
			if(teamname.indexOf(team) >= 0){
				for(MatchPO po : matchdata.getAllMatchesAboutTeam(teamname, season)){
					list.add(potovo(po));
				}
			}
		}
		
		return list;
	}

	public ArrayList<MatchVO> getAllMatches(){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(String s : matchdata.getExistedSeasons()){
			for(MatchPO po : matchdata.getAllMatchesAtSeason(s)){
				list.add(potovo(po));
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getAllMatchesAboutPlayer(String name){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(String s : matchdata.getExistedSeasons()){
			for(MatchPO po : matchdata.getAllMatchesAtSeason(s)){
				for(MatchPlayerDataPO player : po.getTeam1().getTeamPlayers()){
					if(player.getName().indexOf(name) >= 0){
						if(list.size() != 0 && list.get(list.size() - 1) == potovo(po)){
							break;
						}
						list.add(potovo(po));
					}
				}
				for(MatchPlayerDataPO player : po.getTeam2().getTeamPlayers()){
					if(player.getName().indexOf(name) >= 0){
						if(list.size() != 0 && list.get(list.size() - 1) == potovo(po)){
							break;
						}
						list.add(potovo(po));
					}
				}
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getDataMatches(String date){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchVO vo : getAllMatches()){
			if(vo.date.equals(date)){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getDateMatchesAboutPlayer(String date,String player){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchVO vo : getAllMatchesAboutPlayer(player)){
			if(vo.date.equals(date)){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getMatchesAboutPlayer(String season,String player){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getAllMatchesAtSeason(season)){
			for(MatchPlayerDataPO name : po.getTeam1().getTeamPlayers()){
				if(name.getName().indexOf(player) >= 0){
					if(list.size() != 0 && list.get(list.size() - 1) == potovo(po)){
						break;
					}
					list.add(potovo(po));
				}
			}
			for(MatchPlayerDataPO name : po.getTeam2().getTeamPlayers()){
				if(name.getName().indexOf(player) >= 0){
					if(list.size() != 0 && list.get(list.size() - 1) == potovo(po)){
						break;
					}
					list.add(potovo(po));
				}
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getSeasonDateMatches(String season,String date){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchVO vo : getMatches(season, "All")){
			if(vo.date.equals(date)){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getSeaonDateMatchesAboutPlayer(String season,String date,String player){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchVO vo : getMatchesAboutPlayer(season,player)){
			if(vo.date.equals(date)){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getAllMatchesAboutTeam(String team){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>(); 
		for(MatchVO vo : getAllMatches()){
			if(vo.team1.abbName.indexOf(team) >= 0 || vo.team2.abbName.indexOf(team) >= 0){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getMatchesAboutTeamPlayer(String team,String player){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>(); 
		for(MatchVO vo : getAllMatchesAboutPlayer(player)){
			if(vo.team1.abbName.indexOf(team) >= 0 || vo.team2.abbName.indexOf(team) >= 0){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getMatchesAboutTeamDate(String team,String date){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>(); 
		for(MatchVO vo : getAllMatchesAboutTeam(team)){
			if(vo.date.equals(date)){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getMatchesAboutTeamDatePlayer(String team,String date,String player){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>(); 
		for(MatchVO vo : getMatchesAboutTeamPlayer(team,player)){
			if(vo.date.equals(date)){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getMatchesAboutTeamSeasonPlayer(String team,String season,String player){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>(); 
		for(MatchVO vo : getMatchesAboutTeamPlayer(team,player)){
			if(vo.season.equals(season)){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getMatchesAboutTeamSeasonDate(String team,String season,String date){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>(); 
		for(MatchVO vo : getMatchesAboutTeam(team,season)){
			if(vo.date.equals(date)){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getAllMatchesAboutTeamSeasonDatePlayer(String team,String season,String date,String player){
		ArrayList<MatchVO> list = new ArrayList<MatchVO>(); 
		for(MatchVO vo : getMatchesAboutTeamSeasonPlayer(team,season,player)){
			if(vo.date.equals(date)){
				list.add(vo);
			}
		}
		return list;
	}
	
	public ArrayList<MatchVO> getMatchesAboutTeamSeasonDatePlayer(String team, String season,
			String date, String player, String isPlayOffs) {
		if(team.equals("All")){
			if(season.equals("All")){
				if(date.equals("All")){
					if(player.equals("All")){
						return getAllMatches();
					}
					else{
						return getAllMatchesAboutPlayer(player);
					}
				}
				else{
					if(player.equals("All")){
						return getDataMatches(date);
					}
					else{
						return getDateMatchesAboutPlayer(date,player);
					}
				}
			}
			else{
				if(date.equals("All")){
					if(player.equals("All")){
						return getMatches(season, isPlayOffs);
					}
					else{
						return getMatchesAboutPlayer(season,player);
					}
				}
				else{
					if(player.equals("All")){
						return getSeasonDateMatches(season,date);
					}
					else{
						return getSeaonDateMatchesAboutPlayer(season,date,player);
					}
				}
			}
		}
		else{
			if(season.equals("All")){
				if(date.equals("All")){
					if(player.equals("All")){
						return getAllMatchesAboutTeam(team);
					}
					else{
						return getMatchesAboutTeamPlayer(team,player);
					}
				}
				else{
					if(player.equals("All")){
						return getMatchesAboutTeamDate(team,date);
					}
					else{
						return getMatchesAboutTeamDatePlayer(team,date,player);
					}
				}
			}
			else{
				if(date.equals("All")){
					if(player.equals("All")){
						return getMatchesAboutTeam(team,season);
					}
					else{
						return getMatchesAboutTeamSeasonPlayer(team,season,player);
					}
				}
				else{
					if(player.equals("All")){
						return getMatchesAboutTeamSeasonDate(team,season,date);
					}
					else{
						return getAllMatchesAboutTeamSeasonDatePlayer(team,season,date,player);
					}
				}
			}
		}
	}
	
	public MatchVO potovo(MatchPO po){
		MatchVO vo = new MatchVO();
		vo.season = po.getSeason();
		vo.date = po.getDate();
		vo.team1 = potovo(po.getTeam1());
		vo.team2 = potovo(po.getTeam2());
		vo.isPlayOffs = po.getIsPlayOffs();
		
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

	public ArrayList<String> getAllSeasons() {
		ArrayList<String> list = new ArrayList<String>();
		list = matchdata.getExistedSeasons();
		return list;
	}
}

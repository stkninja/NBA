package businesslogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import po.MatchPO;
import po.TBasicInfoPO;
import po.TSeasonDataPO;
import vo.MatchVO;
import vo.TeamBasicInfoVO;
import vo.TeamVO;
import data.GetMatchInfo;
import data.GetTeamInfo;
import dataservice.MatchService;
import dataservice.TeamService;

public class TeamBL implements businesslogicservice.TeamBLService{
	private TeamService teamdata = null;
	private MatchBL matchbl = null;
	private MatchService matchdata = null;
	
	public TeamBL(){
		teamdata = new GetTeamInfo();
		matchbl = new MatchBL();
		matchdata = new GetMatchInfo();
	}
	
	public ArrayList<TeamVO> getTeams(String season,String subArea) {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		if(subArea.equals("All")){
			return getSeasonTeams(season);
		}
		else{
			for(TeamVO vo : getSeasonTeams(season)){
				if(vo.subArea.equals(subArea)){
					list.add(vo);
				}
			}
		}
		return list;
	}
	
	public ArrayList<TeamVO> getSeasonTeams(String season){
		ArrayList<TeamVO> list = new ArrayList<TeamVO>(); 
		for(TSeasonDataPO po : teamdata.getAllTSeasonData(season)){
			list.add(potovo(po));
		}
		return list;
	}

	public TeamBasicInfoVO getOneTeam(String name) {
		TeamBasicInfoVO vo = new TeamBasicInfoVO();
		TBasicInfoPO po = teamdata.getSingleTBasicInfo(name);
		if(po == null){
			return null;
		}
		vo.teamLogo = po.getTeamLogo();
		vo.fullName = po.getFullName();
		vo.abbName = po.getAbbName();
		vo.location = po.getLocation();
		vo.competionArea = po.getCompetionArea();
		vo.subArea = po.getSubArea();
		vo.homeGround = po.getHomeGround();
		vo.setupTime = po.getSetupTime();
		return vo;
	}

	public TeamVO potovo(TSeasonDataPO po){
		TeamVO vo = new TeamVO();
		vo.fullName = po.getFullName();
		vo.abbName = po.getAbbName();
		vo.season = po.getSeason();
		vo.gamesNum = po.getGamesNum();
		vo.winsNum = po.getWinsNum();
		vo.subArea = po.getSubArea();
		vo.allshooting = po.getAllshooting();
		vo.shooting = po.getShooting();
		vo.allshootingHit = po.getAllshootingHit();
		vo.shootingHit = po.getShootingHit();
		vo.allthreePoint = po.getAllthreePoint();
		vo.threePoint = po.getThreePoint();
		vo.allthreePointHits = po.getAllthreePointHits();
		vo.threePointHits = po.getThreePointHits();
		vo.allfreeThrow = po.getAllfreeThrow();
		vo.freeThrow = po.getFreeThrow();
		vo.allfreeThrowHit = po.getAllfreeThrowHit();
		vo.freeThrowHit = po.getFreeThrowHit();
		vo.alloffensiveRebounds = po.getAlloffensiveRebounds();
		vo.offensiveRebounds = po.getOffensiveRebounds();
		vo.alldefensiveRebounds = po.getAlldefensiveRebounds();
		vo.defensiveRebounds = po.getDefensiveRebounds();
		vo.allopponentOffensiveRebounds = po.getAllopponentOffensiveRebounds();
		vo.opponentOffensiveRebounds = po.getOpponentOffensiveRebounds();
		vo.allopponentDefensiveRebounds = po.getAllopponentDefensiveRebounds();
		vo.opponentDefensiveRebounds = po.getOpponentDefensiveRebounds();
		vo.allrebounds = po.getAllrebounds();
		vo.rebounds = po.getRebounds();
		vo.allassists = po.getAllassists();
		vo.assists = po.getAssists();
		vo.allsteal = po.getAllsteal();
		vo.steal = po.getSteal();
		vo.allcaps = po.getAllcaps();
		vo.caps = po.getCaps();
		vo.allturnovers = po.getAllturnovers();
		vo.turnovers = po.getTurnovers();
		vo.allfouls = po.getAllfouls();
		vo.fouls = po.getFouls();
		vo.allscores = po.getAllscores();
		vo.scores = po.getScores();
		vo.allshootingHitRate = po.getAllshootingHitRate();
		vo.shootingHitRate = po.getShootingHitRate();
		vo.allthreePointHitRate = po.getAllthreePointHitRate();
		vo.threePointHitRate = po.getThreePointHitRate();
		vo.allfreeThrowHitRate = po.getAllfreeThrowHitRate();
		vo.freeThrowHitRate = po.getFreeThrowHitRate();
		vo.winsRate = po.getWinsRate();
		vo.allattackRound = po.getAllattackRound();
		vo.attackRound = po.getAttackRound();
		vo.allattackEfficiency = po.getAllattackEfficiency();
		vo.attackEfficiency = po.getAttackEfficiency();
		vo.alldefenceEfficiency = po.getAlldefenceEfficiency();
		vo.defenceEfficiency = po.getDefenceEfficiency();
		vo.alloffensivereboundsEfficiency = po.getAlloffensivereboundsEfficiency();
		vo.offensivereboundsEfficiency = po.getOffensivereboundsEfficiency();
		vo.alldefensivereboundsEfficiency = po.getAlldefensivereboundsEfficiency();
		vo.defensivereboundsEfficiency = po.getDefensivereboundsEfficiency();
		vo.allstealEfficiency = po.getAllstealEfficiency();
		vo.stealEfficiency = po.getStealEfficiency();
		vo.allassistEfficiency = po.getAllassistEfficiency();
		vo.assistEfficiency = po.getAssistEfficiency();
		
		return vo;
	}

	public TeamVO getSeasonTeams(String season, String name) {
		TeamVO vo = new TeamVO();
		vo = potovo(teamdata.getOneTSeasonDataPO(name, season));
		return vo;
    }

	public ArrayList<MatchVO> getSeasonMatches(String season, String name) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getAllMatchesAboutTeam(name, season)){
			list.add(matchbl.potovo(po));
		}
		return list;
	}

	public ArrayList<MatchVO> getLastFiveMatchesSpecific(String name) {
		ArrayList<MatchVO> list = new ArrayList<MatchVO>();
		for(MatchPO po : matchdata.getLastFiveMatchesAboutTeam(name)){
			list.add(matchbl.potovo(po));
		}
		return list;
	}

	public ArrayList<TeamVO> getLastFiveMatches(String name) {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		for(MatchVO vo : getLastFiveMatchesSpecific(name)){
			TeamVO teamvo = new TeamVO();
			if(vo.team1.abbName.equals(name)){
				teamvo.abbName = name;
				teamvo.season = vo.season;
				teamvo.shootingHit = vo.team1.getShootingHit();
				teamvo.shooting = vo.team1.getShooting();
				teamvo.threePointHits = vo.team1.getThreePointHits();
				teamvo.threePoint = vo.team1.getThreePoint();
				teamvo.freeThrowHit = vo.team1.getFreeThrowHit();
				teamvo.freeThrow = vo.team1.getFreeThrow();
				teamvo.offensiveRebounds = vo.team1.getOffensiveRebounds();
				teamvo.defensiveRebounds = vo.team1.getDefensiveRebounds();
				teamvo.rebounds = vo.team1.getRebounds();
				teamvo.assists = vo.team1.getAssists();
				teamvo.steal = vo.team1.getSteals();
				teamvo.caps = vo.team1.getCaps();
				teamvo.turnovers = vo.team1.getTurnovers();
				teamvo.fouls = vo.team1.getFouls();
			}
			else{
				teamvo.abbName = name;
				teamvo.season = vo.season;
				teamvo.shootingHit = vo.team2.getShootingHit();
				teamvo.shooting = vo.team2.getShooting();
				teamvo.threePointHits = vo.team2.getThreePointHits();
				teamvo.threePoint = vo.team2.getThreePoint();
				teamvo.freeThrowHit = vo.team2.getFreeThrowHit();
				teamvo.freeThrow = vo.team2.getFreeThrow();
				teamvo.offensiveRebounds = vo.team2.getOffensiveRebounds();
				teamvo.defensiveRebounds = vo.team2.getDefensiveRebounds();
				teamvo.rebounds = vo.team2.getRebounds();
				teamvo.assists = vo.team2.getAssists();
				teamvo.steal = vo.team2.getSteals();
				teamvo.caps = vo.team2.getCaps();
				teamvo.turnovers = vo.team2.getTurnovers();
				teamvo.fouls = vo.team2.getFouls();
			}
			
			list.add(teamvo);
		}
		
		return list;
	}

	public ArrayList<TeamVO> getSeasonEachMatch(String season, String name) {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		for(MatchVO vo : getSeasonMatches(season,name)){
			TeamVO teamvo = new TeamVO();
			if(vo.team1.abbName.equals(name)){
				teamvo.abbName = name;
				teamvo.season = season;
				teamvo.shootingHit = vo.team1.getShootingHit();
				teamvo.shooting = vo.team1.getShooting();
				teamvo.threePointHits = vo.team1.getThreePointHits();
				teamvo.threePoint = vo.team1.getThreePoint();
				teamvo.freeThrowHit = vo.team1.getFreeThrowHit();
				teamvo.freeThrow = vo.team1.getFreeThrow();
				teamvo.offensiveRebounds = vo.team1.getOffensiveRebounds();
				teamvo.defensiveRebounds = vo.team1.getDefensiveRebounds();
				teamvo.rebounds = vo.team1.getRebounds();
				teamvo.assists = vo.team1.getAssists();
				teamvo.steal = vo.team1.getSteals();
				teamvo.caps = vo.team1.getCaps();
				teamvo.turnovers = vo.team1.getTurnovers();
				teamvo.fouls = vo.team1.getFouls();
			}
			else{
				teamvo.abbName = name;
				teamvo.season = season;
				teamvo.shootingHit = vo.team2.getShootingHit();
				teamvo.shooting = vo.team2.getShooting();
				teamvo.threePointHits = vo.team2.getThreePointHits();
				teamvo.threePoint = vo.team2.getThreePoint();
				teamvo.freeThrowHit = vo.team2.getFreeThrowHit();
				teamvo.freeThrow = vo.team2.getFreeThrow();
				teamvo.offensiveRebounds = vo.team2.getOffensiveRebounds();
				teamvo.defensiveRebounds = vo.team2.getDefensiveRebounds();
				teamvo.rebounds = vo.team2.getRebounds();
				teamvo.assists = vo.team2.getAssists();
				teamvo.steal = vo.team2.getSteals();
				teamvo.caps = vo.team2.getCaps();
				teamvo.turnovers = vo.team2.getTurnovers();
				teamvo.fouls = vo.team2.getFouls();
			}
			
			list.add(teamvo);
		}
		
		return list;
	}
	
	private TeamVO findMax(ArrayList<TeamVO> templist,String filter){
		TeamVO max = new TeamVO();
		max = templist.get(0);
		for(int i = 0;i < templist.size();i ++){
			if(filter.equals("�÷�")){
				if(max.scores < templist.get(i).scores){
					max = templist.get(i);
				}
			}
			else if(filter.equals("����")){
				if(max.rebounds < templist.get(i).rebounds){
					max = templist.get(i);
				}
			}
			else if(filter.equals("����")){
				if(max.assists < templist.get(i).assists){
					max = templist.get(i);
				}
			}
			else if(filter.equals("��ñ")){
				if(max.caps < templist.get(i).caps){
					max = templist.get(i);
				}
			}
			else if(filter.equals("����")){
				if(max.steal < templist.get(i).steal){
					max = templist.get(i);
				}
			}
			else if(filter.equals("����������")){
				if(max.allthreePointHitRate < templist.get(i).allthreePointHitRate){
					max = templist.get(i);
				}
			}
			else if(filter.equals("Ͷ��������")){
				if(max.allshootingHitRate < templist.get(i).allshootingHitRate){
					max = templist.get(i);
				}
			}
			else if(filter.equals("����������")){
				if(max.allfreeThrowHitRate < templist.get(i).allfreeThrowHitRate){
					max = templist.get(i);
				}
			}
		}
		return max;
	}
	
	private ArrayList<TeamVO> sort(ArrayList<TeamVO> templist,String filter){
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		TeamVO vo1 = findMax(templist,filter);
		list.add(vo1);
		templist.remove(vo1);
		if(templist.size() == 0){
			return list;
		}
		TeamVO vo2 = findMax(templist,filter);
		list.add(vo2);
		templist.remove(vo2);
		if(templist.size() == 0){
			return list;
		}
		TeamVO vo3 = findMax(templist,filter);
		list.add(vo3);
		templist.remove(vo3);
		if(templist.size() == 0){
			return list;
		}
		TeamVO vo4 = findMax(templist,filter);
		list.add(vo4);
		templist.remove(vo4);
		if(templist.size() == 0){
			return list;
		}
		TeamVO vo5 = findMax(templist,filter);
		list.add(vo5);
		templist.remove(vo5);
		return list;
	}

	public ArrayList<TeamVO> getSeasonTopFiveTeams(String season, String filter) {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		ArrayList<TeamVO> templist = new ArrayList<TeamVO>();
		for(TSeasonDataPO po : teamdata.getAllTSeasonData(season)){
			templist.add(potovo(po));
		}
		list = sort(templist,filter);
		return list;
	}
	
	public double getAttribute(TeamVO vo,String filter){
		double res = 0;
		switch (filter){
		case "��������":
			res = vo.gamesNum;
			break;
		case "ʤ��":
			res = vo.winsRate;
			break;
		case "Ͷ��������":
			res = vo.allshootingHit;
			break;
		case "Ͷ�����ִ���":
			res = vo.allshooting;
			break;
		case "����������":
			res = vo.allthreePointHits;
			break;
		case "���ֳ�����":
			res = vo.allthreePoint;
			break;
		case "����������":
			res = vo.allfreeThrowHit;
			break;
		case "���������":
			res = vo.allfreeThrow;
			break;
		case "����������":
			res = vo.alloffensiveRebounds;
			break;
		case "����������":
			res = vo.alldefensiveRebounds;
			break;
		case "�����غ�":
			res = vo.allattackRound;
			break;
		case "����Ч��":
			res = vo.allattackEfficiency;
			break;
		case "����Ч��":
			res = vo.alldefenceEfficiency;
			break;
		case "��������Ч��":
			res = vo.alloffensivereboundsEfficiency;
			break;
		case "��������Ч��":
			res = vo.alldefensivereboundsEfficiency;
			break;
		case "����Ч��":
			res = vo.allstealEfficiency;
			break;
			
		case "������":
			res = vo.allrebounds;
			break;
		case "������":
			res = vo.allassists;
			break;
		case "Ͷ��������":
			res = vo.allshootingHitRate;
			break;
		case "����������":
			res = vo.allthreePointHitRate;
			break;
		case "����������":
			res = vo.allfreeThrowHitRate;
			break;
		case "������":
			res = vo.allsteal;
			break;
		case "��ñ��":
			res = vo.allcaps;
			break;
		case "ʧ����":
			res = vo.allturnovers;
			break;
		case "������":
			res = vo.allfouls;
			break;
		case "�����÷�":
			res = vo.allscores;
			break;
		case "����Ч��":
			res = vo.allassistEfficiency;
			break;
			
		case "����Ͷ��������":
			res = vo.shootingHit;
			break;
		case "����Ͷ�����ִ���":
			res = vo.shooting;
			break;
		case "��������������":
			res = vo.threePointHits;
			break;
		case "�������ֳ�����":
			res = vo.threePoint;
			break;
		case "��������������":
			res = vo.freeThrowHit;
			break;
		case "�������������":
			res = vo.freeThrow;
			break;
		case "��������������":
			res = vo.offensiveRebounds;
			break;
		case "��������������":
			res = vo.defensiveRebounds;
			break;
		case "����������":
			res = vo.rebounds;
			break;
		case "���������غ�":
			res = vo.attackRound;
			break;
		case "����������":
			res = vo.assists;
			break;
		case "����Ͷ��������":
			res = vo.shootingHitRate;
			break;
		case "��������������":
			res = vo.threePointHitRate;
			break;
		case "��������������":
			res = vo.freeThrowHitRate;
			break;
		case "����������":
			res = vo.steal;
			break;
		case "������ñ��":
			res = vo.caps;
			break;
		case "����ʧ����":
			res = vo.turnovers;
			break;
		case "����������":
			res = vo.fouls;
			break;
		case "���������÷�":
			res = vo.scores;
			break;
		case "������������Ч��":
			res = vo.offensivereboundsEfficiency;
			break;
		case "������������Ч��":
			res = vo.defensivereboundsEfficiency;
			break;
		case "��������Ч��":
			res = vo.attackEfficiency;
			break;
		case "��������Ч��":
			res = vo.defenceEfficiency;
			break;
		case "��������Ч��":
			res = vo.assistEfficiency;
			break;
		case "��������Ч��":
			res = vo.stealEfficiency;
			break;
		}
		return res;		
	}
	
	public ArrayList<TeamVO> sortTeam(ArrayList<TeamVO> list,ArrayList<String> filter,String sortOrder){
		Comparator<TeamVO> comparator = new Comparator<TeamVO>(){
			public int compare(TeamVO vo1, TeamVO vo2) {
				int res = 0;
				for(int i = 0;i < filter.size();i ++){
					if(filter.get(i).equals("��")){
						break;
					}
					if(filter.get(i).equals("�������")){
						if(!vo1.fullName.equals(vo2.fullName)){
							res = vo1.fullName.compareTo(vo2.fullName);
							break;
						}
						else{
							continue;
						}
					}
					else if(filter.get(i).equals("������д")){
						if(!vo1.abbName.equals(vo2.abbName)){
							res = vo1.abbName.compareTo(vo2.abbName);
							break;
						}
						else{
							continue;
						}
					}
					else{
						if(getAttribute(vo1,filter.get(i)) != getAttribute(vo2,filter.get(i))){
							res = (int) ((int)getAttribute(vo1,filter.get(i)) - getAttribute(vo2,filter.get(i)));
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
		if(sortOrder.equals("����")){
			return list;
		}
		else{
			ArrayList<TeamVO> reslist = new ArrayList<TeamVO>();
			for(int j = list.size() - 1;j >= 0;j --){
				reslist.add(list.get(j));
			}
			return reslist;
		}
	}

	public ArrayList<TeamVO> getAllSeasonTeam(String name) {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		for(String season : matchdata.getExistedSeasons()){
			list.add(getSeasonTeams(season,name));
		}
		return list;
	}

	public ArrayList<String> getFilters() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("�������");
		list.add("������д");
		list.add("��������");
		list.add("ʤ��");
		list.add("Ͷ��������");
		list.add("Ͷ�����ִ���");
		list.add("����������");
		list.add("���ֳ�����");
		list.add("����������");
		list.add("���������");
		list.add("����������");
		list.add("����������");
		list.add("�����غ�");
		list.add("����Ч��");
		list.add("����Ч��");
		list.add("��������Ч��");
		list.add("��������Ч��");
		list.add("����Ч��");
		list.add("������");
		list.add("������");
		list.add("Ͷ��������");
		list.add("����������");
		list.add("����������");
		list.add("������");
		list.add("��ñ��");
		list.add("ʧ����");
		list.add("������");
		list.add("�����÷�");
		list.add("����Ч��");
		
		return list;
	}
	
	public static void main(String[] args){
		TeamBL bl = new TeamBL();
		System.out.println(bl.getOneTeam("ATL").competionArea);
	}
}

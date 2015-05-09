package test;

import test.data.PlayerHighInfo;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.PlayerNormalInfo;
import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;
import vo.PlayerVO;
import vo.TeamBasicInfoVO;
import vo.TeamVO;
import businesslogic.PlayerBL;
import businesslogic.TeamBL;

public class Conversion {
	PlayerBL playerbl = null;
	TeamBL teambl = null;
	Parse p = null;
	
	public Conversion(){
		playerbl = new PlayerBL();
		teambl = new TeamBL();
		p = new Parse();
	}
	
	public PlayerNormalInfo playervotoNormal(PlayerVO vo,String s){
		PlayerNormalInfo playernormal = new PlayerNormalInfo();
		playernormal.setAge(Integer.parseInt(playerbl.getOnePlayer(vo.name).age));
		if(s.indexOf("-avg") >= 0 || (s.indexOf("-avg") < 0 && s.indexOf("-total") < 0)){
			playernormal.setAssist(vo.assist);
			playernormal.setBlockShot(vo.block);
			playernormal.setDefend(vo.defence);
			playernormal.setEfficiency(vo.efficiency);
			playernormal.setFault(vo.error);
			playernormal.setFoul(vo.foul);
			playernormal.setMinute(vo.minute);
			playernormal.setName(vo.name);
			playernormal.setNumOfGame(vo.gameplay);
			playernormal.setOffend(vo.offense);
			playernormal.setPenalty(vo.freethrowpercent);
			playernormal.setPoint(vo.point);
			playernormal.setRebound(vo.rebound);
			playernormal.setShot(vo.fieldgoalpercent);
			playernormal.setStart(vo.gamestart);
			playernormal.setSteal(vo.steal);
			playernormal.setTeamName(vo.team);
			playernormal.setThree(vo.threepointpercent);
		}
		else{
			playernormal.setAssist(vo.allassist);
			playernormal.setBlockShot(vo.allblock);
			playernormal.setDefend(vo.alldefence);
			playernormal.setEfficiency(vo.efficiency);
			playernormal.setFault(vo.allerror);
			playernormal.setFoul(vo.allfoul);
			playernormal.setMinute(vo.allminute);
			playernormal.setName(vo.name);
			playernormal.setNumOfGame(vo.gameplay);
			playernormal.setOffend(vo.alloffense);
			playernormal.setPenalty(vo.freethrowpercent);
			playernormal.setPoint(vo.allpoint);
			playernormal.setRebound(vo.allrebound);
			playernormal.setShot(vo.fieldgoalpercent);
			playernormal.setStart(vo.gamestart);
			playernormal.setSteal(vo.allsteal);
			playernormal.setTeamName(vo.team);
			playernormal.setThree(vo.threepointpercent);
		}
		
		return playernormal;
	}
	
	public PlayerHotInfo playervotoHot(PlayerVO vo,String s){
		PlayerHotInfo playerHot = new PlayerHotInfo();
		String str = p.allorHotorKing(s);
		
			
			playerHot.setField(str);
			playerHot.setName(vo.name);
			playerHot.setPosition(vo.position);
			playerHot.setTeamName(vo.team);
			if(str.equals("score")){
				playerHot.setUpgradeRate(vo.pointpromotion);
				playerHot.setValue(vo.point);
			}
			else if(str.equals("rebound")){
				playerHot.setUpgradeRate(vo.reboundpromotion);
				playerHot.setValue(vo.rebound);
			}
			else if(str.equals("assist")){
				playerHot.setUpgradeRate(vo.assistpromotion);
				playerHot.setValue(vo.assist);
			}
		
		return playerHot;
	}
	
	public PlayerKingInfo playervotoKing(PlayerVO vo,String s){
		PlayerKingInfo playerKing = new PlayerKingInfo();
		String str = p.allorHotorKing(s);
			playerKing.setField(str.split(" ")[0]);
			playerKing.setName(vo.name);
			playerKing.setPosition(vo.position);
			playerKing.setTeamName(vo.team);
			if(playerKing.getField().equals("score")){
				playerKing.setValue(vo.point);
			}
			else if(playerKing.getField().equals("rebound")){
				playerKing.setValue(vo.rebound);
			}
			else if(playerKing.getField().equals("assist")){
				playerKing.setValue(vo.assist);
			}
		
		return playerKing;
	}
	
	public PlayerHighInfo playervotoHigh(PlayerVO vo){
		PlayerHighInfo playerHigh = new PlayerHighInfo();
		playerHigh.setAssistEfficient(vo.assistrate);
		playerHigh.setBlockShotEfficient(vo.blockrate);
		playerHigh.setDefendReboundEfficient(vo.defensivereboundrate);
		playerHigh.setFaultEfficient(vo.errorrate);
		playerHigh.setFrequency(vo.usage);
		playerHigh.setGmSc(vo.gmsc);
		playerHigh.setLeague(getLeague(vo));
		playerHigh.setName(vo.name);
		playerHigh.setOffendReboundEfficient(vo.offensivereboundrate);
		playerHigh.setPosition(vo.position);
		playerHigh.setRealShot(vo.realshootpercent);
		playerHigh.setReboundEfficient(vo.reboundrate);
		playerHigh.setShotEfficient(vo.shootefficiency);
		playerHigh.setStealEfficient(vo.stealrate);
		playerHigh.setTeamName(vo.team);
		
		return playerHigh;
	}
	
	public String getLeague(PlayerVO vo){
		TeamBasicInfoVO vo1 = teambl.getOneTeam(vo.team);
		if(vo1.competionArea.equals("W")){
			return "West";
		}
		else if(vo1.competionArea.equals("E")){
			return "East";
		}
		else{
			return "All";
		}
	}
	
	public TeamNormalInfo teamvotoNormal(TeamVO vo,String s){
		TeamNormalInfo teamNormal = new TeamNormalInfo();
		if(s.indexOf("-avg") >= 0 || (s.indexOf("-avg") < 0 && s.indexOf("-total") < 0)){
			teamNormal.setAssist(vo.assists);
			teamNormal.setBlockShot(vo.caps);
			teamNormal.setDefendRebound(vo.defensiveRebounds);
			teamNormal.setFault(vo.turnovers);
			teamNormal.setFoul(vo.fouls);
			teamNormal.setNumOfGame((int)vo.gamesNum);
			teamNormal.setOffendRebound(vo.offensiveRebounds);
			teamNormal.setPenalty(vo.freeThrowHitRate);
			teamNormal.setPoint(vo.scores);
			teamNormal.setRebound(vo.rebounds);
			teamNormal.setShot(vo.shootingHitRate);
			teamNormal.setSteal(vo.steal);
			teamNormal.setTeamName(vo.fullName);
			teamNormal.setThree(vo.threePointHitRate);
		}
		else{
			teamNormal.setAssist(vo.allassists);
			teamNormal.setBlockShot(vo.allcaps);
			teamNormal.setDefendRebound(vo.alldefensiveRebounds);
			teamNormal.setFault(vo.allturnovers);
			teamNormal.setFoul(vo.allfouls);
			teamNormal.setNumOfGame((int)vo.gamesNum);
			teamNormal.setOffendRebound(vo.alloffensiveRebounds);
			teamNormal.setPenalty(vo.freeThrowHitRate);
			teamNormal.setPoint(vo.allscores);
			teamNormal.setRebound(vo.allrebounds);
			teamNormal.setShot(vo.shootingHitRate);
			teamNormal.setSteal(vo.allsteal);
			teamNormal.setTeamName(vo.fullName);
			teamNormal.setThree(vo.threePointHitRate);
		}
		return teamNormal;
	}
	
	public TeamHotInfo teamvotoHot(TeamVO vo,String s){
		TeamHotInfo teamHot = new TeamHotInfo();
		String str = p.allorHotorKing(s);
		
			teamHot.setField(str);
			if(teambl.getOneTeam(vo.abbName).competionArea.equals("W")){
				teamHot.setLeague("West");
			}
			else if(teambl.getOneTeam(vo.abbName).competionArea.equals("E")){
				teamHot.setLeague("East");
			}
			else{
				teamHot.setLeague("All");
			}
			teamHot.setTeamName(vo.abbName);
			switch (str){
			case "score":
				teamHot.setValue(vo.scores);
				break;
			case "rebound":
				teamHot.setValue(vo.rebounds);
				break;
			case "assist":
				teamHot.setValue(vo.assists);
				break;
			case "blockShot":
				teamHot.setValue(vo.caps);
				break;
			case "steal":
				teamHot.setValue(vo.steal);
				break;
			case "foul":
				teamHot.setValue(vo.fouls);
				break;
			case "fault":
				teamHot.setValue(vo.turnovers);
				break;
			case "shot":
				teamHot.setValue(vo.shootingHitRate);
				break;
			case "three":
				teamHot.setValue(vo.threePointHitRate);
				break;
			case "penalty":
				teamHot.setValue(vo.freeThrowHitRate);
				break;
			case "defendRebound":
				teamHot.setValue(vo.defensiveRebounds);
				break;
			case "offendRebound":
				teamHot.setValue(vo.offensiveRebounds);
				break;
			}
			
		
		return teamHot;
	}

	public TeamHighInfo teamvotoHigh(TeamVO vo,String s){
		TeamHighInfo teamHigh = new TeamHighInfo();
		teamHigh.setAssistEfficient(vo.assistEfficiency);
		teamHigh.setDefendEfficient(vo.defenceEfficiency);
		teamHigh.setDefendReboundEfficient(vo.defensivereboundsEfficiency);
		teamHigh.setOffendEfficient(vo.attackEfficiency);
		teamHigh.setOffendReboundEfficient(vo.offensivereboundsEfficiency);
		if(s.indexOf("-avg") >= 0 || (s.indexOf("-avg") < 0 && s.indexOf("-total") < 0)){
			teamHigh.setOffendRound(vo.offensiveRebounds);
		}
		else{
			teamHigh.setOffendRound(vo.alloffensiveRebounds);
		}
		teamHigh.setStealEfficient(vo.stealEfficiency);
		teamHigh.setTeamName(vo.abbName);
		teamHigh.setWinRate(vo.winsRate);
		return teamHigh;
	}
	
/*	public static void main(String[] args){
		PlayerKingInfo p = new PlayerKingInfo();
		p.setField("score");
		p.setName("zk");
		p.setPosition("F");
		p.setTeamName("HOU");
		p.setValue(1);
	
		try{
			File file = new File("test.txt");
	        if(!file.exists()){
	        	file.createNewFile();
	        	return;
	        }
         
	        PrintStream out = new PrintStream(new FileOutputStream(file));
	        out.print("1");
			out.print(p.toString());
			
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		
	}*/
}

package vo;

import java.util.ArrayList;

public class MatchTeamDataVO {
	/**球队比赛信息*/
	//球队缩写
	public String abbName = new String();
	//比赛得分
	public double scores;
	public double qt1Scores;
	public double qt2Scores;
	public double qt3Scores;
	public double qt4Scores;
	public ArrayList<Double> qtPlusScores = new ArrayList<Double>();
	public ArrayList<MatchPlayerDataVO> teamPlayers = new ArrayList<MatchPlayerDataVO>();
	
	public MatchTeamDataVO(){
		
	}
	
	/**获得球队比赛数据方法*/
	public double getShootingHit() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.shootmade;
		}
		return total;
	}

	public double getShooting() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.shoot;
		}
		return total;
	}

	public double getThreePointHits() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.threepointmade;
		}
		return total;
	}

	public double getThreePoint() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.threepoint;
		}
		return total;
	}

	public double getFreeThrowHit() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.freethrowmade;
		}
		return total;
	}

	public double getFreeThrow() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.freethrow;
		}
		return total;
	}

	public double getOffensiveRebounds() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.offensiveRebounds;
		}
		return total;
	}

	public double getDefensiveRebounds() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.defensiveRebounds;
		}
		return total;
	}

	public double getRebounds() {
		return getOffensiveRebounds() + getDefensiveRebounds();
	}

	public double getAssists() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.assist;
		}
		return total;
	}

	public double getSteals() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.steal;
		}
		return total;
	}

	public double getCaps() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.block;
		}
		return total;
	}
	
	public double getTurnovers() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.error;
		}
		return total;
	}

	public double getFouls() {
		double total = 0.0;
		for(MatchPlayerDataVO vo : teamPlayers){
			total += vo.foul;
		}
		return total;
	}
	
	public boolean existPlayer(String name){
		for(MatchPlayerDataVO matchPlayerDataVO : teamPlayers)
			if(matchPlayerDataVO.name.equals(name))
				return true;
		
		return false;
	}
}

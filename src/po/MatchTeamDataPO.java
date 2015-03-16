package po;

import java.io.Serializable;
import java.util.ArrayList;


public class MatchTeamDataPO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**��ӱ�����Ϣ*/
	//�����д
	private String abbName = new String();
	//�����÷�
	private double scores;
	private ArrayList<MatchPlayerDataPO> teamPlayers = new ArrayList<MatchPlayerDataPO>();

	/**get set*/
	public String getAbbName() {
		return abbName;
	}
	
	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}

	public double getScores() {
		return scores;
	}
	
	public void setScores(double scores) {
		this.scores = scores;
	}
	
	public ArrayList<MatchPlayerDataPO> getTeamPlayers() {
		return teamPlayers;
	}
	
	public void setTeamPlayers(ArrayList<MatchPlayerDataPO> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}

	
	/**�����ӱ������ݷ���*/
	public double getShootingHit() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getShootmade();
		}
		return total;
	}

	public double getShooting() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getShoot();
		}
		return total;
	}

	public double getThreePointHits() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getThreepointmade();
		}
		return total;
	}

	public double getThreePoint() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getShoot();
		}
		return total;
	}

	public double getFreeThrowHit() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getFreethrowmade();
		}
		return total;
	}

	public double getFreeThrow() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getFreethrow();
		}
		return total;
	}

	public double getOffensiveRebounds() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getShootmade();
		}
		return total;
	}

	public double getDefensiveRebounds() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getDefensiveRebounds();
		}
		return total;
	}

	public double getRebounds() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getRebound();
		}
		return total;
	}

	public double getAssists() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getAssist();
		}
		return total;
	}

	public double getSteals() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getSteal();
		}
		return total;
	}

	public double getCaps() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getBlock();
		}
		return total;
	}
	
	public double getTurnovers() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getError();
		}
		return total;
	}

	public double getFouls() {
		double total = 0.0;
		for(MatchPlayerDataPO dataPO : teamPlayers){
			total += dataPO.getFoul();
		}
		return total;
	}
	
	/**����Ա�������������Ա*/
	public void addPlayer(MatchPlayerDataPO playerDataPO){
		this.teamPlayers.add(playerDataPO);
	}
}

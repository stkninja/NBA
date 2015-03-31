package po;

import java.io.Serializable;
import java.util.ArrayList;


public class MatchTeamDataPO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**球队比赛信息*/
	//球队缩写
	private String abbName = new String();
	//比赛得分
	private double scores;
	private double qt1Scores;
	private double qt2Scores;
	private double qt3Scores;
	private double qt4Scores;
	private ArrayList<Double> qtPlusScores = new ArrayList<Double>();
	private ArrayList<MatchPlayerDataPO> teamPlayers = new ArrayList<MatchPlayerDataPO>();

	/**get set*/
	public double getQt1Scores() {
		return qt1Scores;
	}
	
	public void setQt1Scores(double qt1Scores) {
		this.qt1Scores = qt1Scores;
	}
	
	public double getQt2Scores() {
		return qt2Scores;
	}
	
	public void setQt2Scores(double qt2Scores) {
		this.qt2Scores = qt2Scores;
	}
	
	public double getQt3Scores() {
		return qt3Scores;
	}
	
	public void setQt3Scores(double qt3Scores) {
		this.qt3Scores = qt3Scores;
	}
	
	public double getQt4Scores() {
		return qt4Scores;
	}
	
	public void setQt4Scores(double qt4Scores) {
		this.qt4Scores = qt4Scores;
	}
	
	public ArrayList<Double> getQtPlusScores() {
		return qtPlusScores;
	}
	
	public void addQtPlusScores(double qtPlusScores) {
		this.qtPlusScores.add(qtPlusScores);
	}
	
	public int getQtPlusNum(){
		return qtPlusScores.size();
	}
	
	public double getScores() {
		return scores;
	}
	
	public void setScores(double scores) {
		this.scores = scores;
	}
	
	public String getAbbName() {
		return abbName;
	}
	
	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}

	public ArrayList<MatchPlayerDataPO> getTeamPlayers() {
		return teamPlayers;
	}
	
	public void setTeamPlayers(ArrayList<MatchPlayerDataPO> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}

	
	/**获得球队比赛数据方法*/
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
			total += dataPO.getThreepoint();
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
			total += dataPO.getOffensiveRebounds();
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
	
	/**在球员数据中添加新球员*/
	public void addPlayer(MatchPlayerDataPO playerDataPO){
		this.teamPlayers.add(playerDataPO);
	}
	
	public boolean existPlayer(String name){
		for(MatchPlayerDataPO matchPlayerDataPO : teamPlayers)
			if(matchPlayerDataPO.getName().equals(name))
				return true;
		
		return false;
	}
}

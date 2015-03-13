package po;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class TeamBasicInfoPO{
	/**球队基本信息*/
	//球队标志
	private ImageIcon teamLogo = null;
	//球队全名
	private String fullName = new String();
	//球队缩写
	private String abbName = new String();
	//所在地
	private String location = new String();
	//赛区
	private String competionArea = new String();
	//分区
	private String subArea = new String();
	//主场
	private String homeGround = new String();
	//建立时间
	private int setupTime;
	//比赛场数
	private ArrayList<String> gamesNum;
	//胜利场数
	private ArrayList<String> winsNum;
	//赛季胜率
	private ArrayList<String> winsRate;
	
	public TeamBasicInfoPO() {}

	/**球队get方法*/
	public ImageIcon getTeamLogo() {
		return teamLogo;
	}
	
	public String getFullName() {
		return fullName;
	}

	public String getAbbName() {
		return abbName;
	}

	public String getLocation() {
		return location;
	}

	public String getCompetionArea() {
		return competionArea;
	}

	public String getSubArea() {
		return subArea;
	}

	public String getHomeGround() {
		return homeGround;
	}

	public int getSetupTime() {
		return setupTime;
	}

	public ArrayList<String> getGamesNum() {
		return gamesNum;
	}

	public ArrayList<String> getWinsNum() {
		return winsNum;
	}
	
	public ArrayList<String> getWinsRate() {
		return winsRate;
	}

	
	/**球队及比赛信息set方法*/
	public void setTeamLogo(ImageIcon teamLogo) {
		this.teamLogo = teamLogo;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCompetionArea(String competionArea) {
		this.competionArea = competionArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	public void setHomeGround(String homeGround) {
		this.homeGround = homeGround;
	}

	public void setSetupTime(int setupTime) {
		this.setupTime = setupTime;
	}

	public void setGamesNum(ArrayList<String> gamesNum) {
		this.gamesNum = gamesNum;
	}

	public void setWinsNum(ArrayList<String> winsNum) {
		this.winsNum = winsNum;
	}

	public void setWinsRate(ArrayList<String> winsRate) {
		this.winsRate = winsRate;
	}
	
	/**ToVO
	public TeamVO ToVO(){
		return new TeamVO(teamLogo, fullName, abbName, 
				location, competionArea, subArea, homeGround, setupTime, 
				gamesNum, winsNum, shootingHit, shooting, threePointHits, 
				threePoint, freeThrowHit, freeThrow, offensiveRebounds, 
				defensiveRebounds, opponentOffensiveRebounds, opponentDefensiveRebounds, 
				defensiveRebounds, assists, steals, caps, turnovers, fouls, scores);
	}*/
}
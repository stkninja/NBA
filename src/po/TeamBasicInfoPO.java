package po;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class TeamBasicInfoPO{
	/**��ӻ�����Ϣ*/
	//��ӱ�־
	private ImageIcon teamLogo = null;
	//���ȫ��
	private String fullName = new String();
	//�����д
	private String abbName = new String();
	//���ڵ�
	private String location = new String();
	//����
	private String competionArea = new String();
	//����
	private String subArea = new String();
	//����
	private String homeGround = new String();
	//����ʱ��
	private int setupTime;
	//��������
	private ArrayList<String> gamesNum;
	//ʤ������
	private ArrayList<String> winsNum;
	//����ʤ��
	private ArrayList<String> winsRate;
	
	public TeamBasicInfoPO() {}

	/**���get����*/
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

	
	/**��Ӽ�������Ϣset����*/
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
package po;

import java.io.File;
import java.io.Serializable;

public class TBasicInfoPO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**球队基本信息*/
	//球队标志
	private File teamLogo = null;
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
	private String setupTime;
	
	/**球队get方法*/
	public File getTeamLogo() {
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

	public String getSetupTime() {
		return setupTime;
	}

	
	/**球队set方法*/
	public void setTeamLogo(File teamLogo) {
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

	public void setSetupTime(String setupTime) {
		this.setupTime = setupTime;
	}
}
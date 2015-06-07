package spider.spiderTeam;


public class TeamBasicStruct{	

	//现在球队全名
	private String tempFullName = new String();
	//现在球队缩写
	private String tempAbbName = new String();
	//曾经球队全名
	private String historyFullName = new String();
	//曾经球队缩写
	private String historyAbblName = new String();
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

	public String getTempFullName() {
		return tempFullName;
	}

	public void setTempFullName(String tempFullName) {
		this.tempFullName = tempFullName;
	}

	public String getTempAbbName() {
		return tempAbbName;
	}

	public void setTempAbbName(String tempAbbName) {
		this.tempAbbName = tempAbbName;
	}

	public String getHistoryFullName() {
		return historyFullName;
	}

	public void setHistoryFullName(String historyFullName) {
		this.historyFullName = historyFullName;
	}

	public String getHistoryAbblName() {
		return historyAbblName;
	}

	public void setHistoryAbblName(String historyAbblName) {
		this.historyAbblName = historyAbblName;
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
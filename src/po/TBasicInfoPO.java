package po;


public class TBasicInfoPO{	
	/**球队基本信息*/
	//球队全名
	private String fullName = new String();
	//球队缩写
	private String abbName = new String();
	//曾经球队全名
	private String historyFullName = new String();
	//曾经球队缩写
	private String historyAbblName = new String();
	private String historyAbblName2 = new String();

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
	
	public String getHistoryFullName() {
		return historyFullName;
	}

	public String getHistoryAbblName() {
		return historyAbblName;
	}
	
	/**球队set方法*/
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
	

	public void setHistoryFullName(String historyFullName) {
		this.historyFullName = historyFullName;
	}

	public void setHistoryAbblName(String historyAbblName) {
		this.historyAbblName = historyAbblName;
	}
	

	public String getHistoryAbblName2() {
		return historyAbblName2;
	}

	public void setHistoryAbblName2(String historyAbblName2) {
		this.historyAbblName2 = historyAbblName2;
	}
}
package spider.spiderTeam;


public class TeamBasicStruct{	

	//�������ȫ��
	private String tempFullName = new String();
	//���������д
	private String tempAbbName = new String();
	//�������ȫ��
	private String historyFullName = new String();
	//���������д
	private String historyAbblName = new String();
	//���ڵ�
	private String location = new String();
	//����
	private String competionArea = new String();
	//����
	private String subArea = new String();
	//����
	private String homeGround = new String();
	//����ʱ��
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
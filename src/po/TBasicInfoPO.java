package po;


public class TBasicInfoPO{	
	/**��ӻ�����Ϣ*/
	//���ȫ��
	private String fullName = new String();
	//�����д
	private String abbName = new String();
	//�������ȫ��
	private String historyFullName = new String();
	//���������д
	private String historyAbblName = new String();
	private String historyAbblName2 = new String();

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
	
	/**���get����*/	
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
	
	/**���set����*/
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
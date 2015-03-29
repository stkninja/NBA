package po;

import java.io.File;
import java.io.Serializable;

public class TBasicInfoPO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**��ӻ�����Ϣ*/
	//��ӱ�־
	private File teamLogo = null;
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
	private String setupTime;
	
	/**���get����*/
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

	
	/**���set����*/
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
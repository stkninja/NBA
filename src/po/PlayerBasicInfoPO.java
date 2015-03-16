package po;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerBasicInfoPO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name = new String();
	private String number;
	private String height = new String();
	private String weight = new String();
	private String birth = new String();
	private String age;
	private String exp;
	private String school =new String();
	private File portrait = null;                 //肖像
	private File action = null;                    //动作照
	private String position = new String();
	/**TODO*/
	private String team = new String();
	private String subArea = new String();                  //分区
	
	public PlayerBasicInfoPO(){
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getSubArea() {
		return subArea;
	}
	
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	
	public File getPortrait() {
		return portrait;
	}
	
	public void setPortrait(File portrait) {
		this.portrait = portrait;
	}
	
	public File getAction() {
		return action;
	}
	
	public void setAction(File action) {
		this.action = action;
	}

	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getExp() {
		return exp;
	}


	public void setExp(String exp) {
		this.exp = exp;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}
}

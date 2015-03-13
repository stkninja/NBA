package po;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PlayerBasicInfoPO{
	private String name = new String();
	private int number;
	private String height = new String();
	private String weight = new String();
	private String birth = new String();
	private int age;
	private int exp;
	private String school =new String();
	private ImageIcon portrait = null;                 //肖像
	private ImageIcon action = null;                    //动作照
	private String team = new String();
	private String position = new String();
	private String subArea = new String();                  //分区
	private ArrayList<String> gameplay;      //参赛场数
	private ArrayList<String> gamestart;     //先发场数
	
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
	
	public ArrayList<String> getGameplay() {
		return gameplay;
	}
	
	public void setGameplay(ArrayList<String> gameplay) {
		this.gameplay = gameplay;
	}
	
	public ArrayList<String> getGamestart() {
		return gamestart;
	}
	
	public void setGamestart(ArrayList<String> gamestart) {
		this.gamestart = gamestart;
	}
	
	public String getSubArea() {
		return subArea;
	}
	
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	
	public ImageIcon getPortrait() {
		return portrait;
	}
	
	public void setPortrait(ImageIcon portrait) {
		this.portrait = portrait;
	}
	
	public ImageIcon getAction() {
		return action;
	}
	
	public void setAction(ImageIcon action) {
		this.action = action;
	}

	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
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


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public int getExp() {
		return exp;
	}


	public void setExp(int exp) {
		this.exp = exp;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}
	
}

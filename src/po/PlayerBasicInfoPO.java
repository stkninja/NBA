package po;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PlayerBasicInfoPO{
	private String name = new String();
	private ImageIcon portrait = null;                 //肖像
	private ImageIcon action = null;                    //动作照
	private String team = new String();
	private String position = new String();
	private String subArea = new String();                  //分区
	private ArrayList<String> gameplay;      //参赛场数
	private ArrayList<String> gamestart;     //先发场数
	
	public PlayerBasicInfoPO(){
		name = new String();
		portrait = null;
		action = null;
		team = new String();
		position = new String();
		subArea = new String();
		gameplay = new ArrayList<String>();
		gamestart = new ArrayList<String>();
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
}

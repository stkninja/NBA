package po;

import java.awt.Image;
import java.util.ArrayList;

public class PlayerPO{
	private String name;
	private Image portrait;                  //Ф��
	private Image action;                    //������
	private String team;
	private String position;
	private String subArea;                  //����
	private ArrayList<String> gameplay;      //��������
	private ArrayList<String> gamestart;     //�ȷ�����
	private ArrayList<String> rebound;       //������
	private ArrayList<String> assist;        //������
	private ArrayList<String> minute;        //�ڳ�ʱ��
	private ArrayList<String> offense;       //������
	private ArrayList<String> defence;       //������
	private ArrayList<String> steal;         //������
	private ArrayList<String> block;         //��ñ��
	private ArrayList<String> error;         //ʧ����
	private ArrayList<String> foul;          //������
	private ArrayList<String> point;         //�÷�
	private ArrayList<String> shoot;         //Ͷ����
	private ArrayList<String> shootmade;     //Ͷ��������
	private ArrayList<String> threepoint;    //���ֳ�����
	private ArrayList<String> threepointmade;//����������
	private ArrayList<String> freethrow;     //���������
	private ArrayList<String> freethrowmade; //����������
	private ArrayList<String> doubledouble;  //��˫
	
	public PlayerPO(){
		this.gameplay = new ArrayList<String>();
		this.gamestart = new ArrayList<String>();
		this.rebound = new ArrayList<String>();
		this.assist = new ArrayList<String>();
		this.minute = new ArrayList<String>();
		this.offense = new ArrayList<String>();
		this.defence = new ArrayList<String>();
		this.steal = new ArrayList<String>();
		this.block = new ArrayList<String>();
		this.error = new ArrayList<String>();
		this.foul = new ArrayList<String>();
		this.point = new ArrayList<String>();
		this.shoot = new ArrayList<String>();
		this.shootmade = new ArrayList<String>();
		this.threepoint = new ArrayList<String>();
		this.threepointmade = new ArrayList<String>();
		this.freethrow = new ArrayList<String>();
		this.freethrowmade = new ArrayList<String>();
		this.doubledouble = new ArrayList<String>();
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
	public ArrayList<String> getRebound() {
		return rebound;
	}
	public void setRebound(ArrayList<String> rebound) {
		this.rebound = rebound;
	}
	public ArrayList<String> getAssist() {
		return assist;
	}
	public void setAssist(ArrayList<String> assist) {
		this.assist = assist;
	}
	public ArrayList<String> getMinute() {
		return minute;
	}
	public void setMinute(ArrayList<String> minute) {
		this.minute = minute;
	}
	public ArrayList<String> getOffense() {
		return offense;
	}
	public void setOffense(ArrayList<String> offense) {
		this.offense = offense;
	}
	public ArrayList<String> getDefence() {
		return defence;
	}
	public void setDefence(ArrayList<String> defence) {
		this.defence = defence;
	}
	public ArrayList<String> getSteal() {
		return steal;
	}
	public void setSteal(ArrayList<String> steal) {
		this.steal = steal;
	}
	public ArrayList<String> getBlock() {
		return block;
	}
	public void setBlock(ArrayList<String> block) {
		this.block = block;
	}
	public ArrayList<String> getError() {
		return error;
	}
	public void setError(ArrayList<String> error) {
		this.error = error;
	}
	public ArrayList<String> getFoul() {
		return foul;
	}
	public void setFoul(ArrayList<String> foul) {
		this.foul = foul;
	}
	public ArrayList<String> getPoint() {
		return point;
	}
	public void setPoint(ArrayList<String> point) {
		this.point = point;
	}
	public ArrayList<String> getThreepoint() {
		return threepoint;
	}
	public void setThreepoint(ArrayList<String> threepoint) {
		this.threepoint = threepoint;
	}
	public ArrayList<String> getFreethrow() {
		return freethrow;
	}
	public void setFreethrow(ArrayList<String> freethrow) {
		this.freethrow = freethrow;
	}
	public ArrayList<String> getDoubledouble() {
		return doubledouble;
	}
	public void setDoubledouble(ArrayList<String> doubledouble) {
		this.doubledouble = doubledouble;
	}
	public ArrayList<String> getShoot() {
		return shoot;
	}
	public void setShoot(ArrayList<String> shoot) {
		this.shoot = shoot;
	}
	public String getSubArea() {
		return subArea;
	}
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	public Image getPortrait() {
		return portrait;
	}
	public void setPortrait(Image portrait) {
		this.portrait = portrait;
	}
	public Image getAction() {
		return action;
	}
	public void setAction(Image action) {
		this.action = action;
	}

	public ArrayList<String> getShootmade() {
		return shootmade;
	}

	public void setShootmade(ArrayList<String> shootmade) {
		this.shootmade = shootmade;
	}

	public ArrayList<String> getThreepointmade() {
		return threepointmade;
	}

	public void setThreepointmade(ArrayList<String> threepointmade) {
		this.threepointmade = threepointmade;
	}

	public ArrayList<String> getFreethrowmade() {
		return freethrowmade;
	}

	public void setFreethrowmade(ArrayList<String> freethrowmade) {
		this.freethrowmade = freethrowmade;
	}
}

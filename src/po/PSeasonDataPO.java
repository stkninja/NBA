package po;

import java.io.Serializable;

public class PSeasonDataPO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String season;
	private String name;
	private String team;
	private String position;
	private String subArea;                  //����
	private int gameplay;                    //��������
	private int gamestart;                   //�ȷ�����
	private double allrebound;                  //��������
	private double rebound;                  //����������
	private double alloffensiverebound;      //�ܽ���������
	private double offensiverebound;         //��������������
	private double alldefensiverebound;      //�ܷ���������
	private double defensiverebound;         //��������������
	private double allassist;                   //��������
	private double assist;                   //����������
	private double allminute;                //���ڳ�ʱ��
	private double minute;                   //�����ڳ�ʱ��
	private double alloffense;                  //�ܽ�����
    private double offense;                  //����������
	private double alldefence;                  //�ܷ�����
	private double defence;                  //����������
	private double allsteal;                    //��������
	private double steal;                    //����������
	private double allblock;                    //�ܸ�ñ��
	private double block;                    //������ñ��
	private double allerror;                    //��ʧ����
	private double error;                    //����ʧ����
	private double allfoul;                     //�ܷ�����
	private double foul;                     //����������
	private double allpoint;                    //�ܵ÷�
	private double point;                    //�����÷�
	private double allshoot;                    //��Ͷ��������
	private double shoot;                    //����Ͷ��������
	private double allshootmade;             //��Ͷ��������
	private double shootmade;                //����Ͷ��������
	private double allthreepoint;               //�����ֳ�����
	private double threepoint;               //�������ֳ�����
	private double allthreepointmade;        //������������
	private double threepointmade;           //��������������
	private double allfreethrow;                //�ܷ��������
	private double freethrow;                //�������������
	private double allfreethrowmade;         //�ܷ���������
	private double freethrowmade;            //��������������
	private double doubledouble;                //��˫
	private double allpointReboundAssist;       //�ܵ÷�/����/����
	private double pointReboundAssist;       //�����÷�/����/����
	private double allfieldgoalpercent;      //��Ͷ��������
	private double fieldgoalpercent;         //����Ͷ��������
	private double allthreepointpercent; //������������
	private double threepointpercent; //��������������
	private double allfreethrowpercent;  //�ܷ���������
	private double freethrowpercent;  //��������������
	private double allefficiency;    //��Ч��
	private double efficiency;    //����Ч��
	private double allgmsc;          //��GmScЧ��ֵ
	private double gmsc;          //����GmScЧ��ֵ
	private double allrealshootpercent;  //����ʵ������
	private double realshootpercent;  //������ʵ������
	private double allshootefficiency;   //��Ͷ��Ч��
	private double shootefficiency;   //����Ͷ��Ч��
	private double allreboundrate;       //��������
	private double reboundrate;       //����������
	private double alloffensivereboundrate;  //�ܽ���������
	private double offensivereboundrate;  //��������������
	private double alldefensivereboundrate;  //�ܷ���������
	private double defensivereboundrate;  //��������������
	private double allassistrate;        //��������
	private double assistrate;        //����������
	private double allstealrate;     //��������
	private double stealrate;     //����������
	private double allblockrate;     //�ܸ�ñ��
	private double blockrate;     //������ñ��
	private double allerrorrate;     //��ʧ����
	private double errorrate;     //����ʧ����
	private double allusage;         //��ʹ����
	private double usage;         //����ʹ����
	
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
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
	public int getGameplay() {
		return gameplay;
	}
	public void setGameplay(int gameplay) {
		this.gameplay = gameplay;
	}
	public int getGamestart() {
		return gamestart;
	}
	public void setGamestart(int gamestart) {
		this.gamestart = gamestart;
	}
	public double getAllrebound() {
		return allrebound;
	}
	public void setAllrebound(double allrebound) {
		this.allrebound = allrebound;
	}
	public double getRebound() {
		return rebound;
	}
	public void setRebound(double rebound) {
		this.rebound = rebound;
	}
	public double getAlloffensiverebound() {
		return alloffensiverebound;
	}
	public void setAlloffensiverebound(double alloffensiverebound) {
		this.alloffensiverebound = alloffensiverebound;
	}
	public double getOffensiverebound() {
		return offensiverebound;
	}
	public void setOffensiverebound(double offensiverebound) {
		this.offensiverebound = offensiverebound;
	}
	public double getAlldefensiverebound() {
		return alldefensiverebound;
	}
	public void setAlldefensiverebound(double alldefensiverebound) {
		this.alldefensiverebound = alldefensiverebound;
	}
	public double getDefensiverebound() {
		return defensiverebound;
	}
	public void setDefensiverebound(double defensiverebound) {
		this.defensiverebound = defensiverebound;
	}
	public double getAllassist() {
		return allassist;
	}
	public void setAllassist(double allassist) {
		this.allassist = allassist;
	}
	public double getAssist() {
		return assist;
	}
	public void setAssist(double assist) {
		this.assist = assist;
	}
	public double getAllminute() {
		return allminute;
	}
	public void setAllminute(double allminute) {
		this.allminute = allminute;
	}
	public double getMinute() {
		return minute;
	}
	public void setMinute(double minute) {
		this.minute = minute;
	}
	public double getAlloffense() {
		return alloffense;
	}
	public void setAlloffense(double alloffense) {
		this.alloffense = alloffense;
	}
	public double getOffense() {
		return offense;
	}
	public void setOffense(double offense) {
		this.offense = offense;
	}
	public double getAlldefence() {
		return alldefence;
	}
	public void setAlldefence(double alldefence) {
		this.alldefence = alldefence;
	}
	public double getDefence() {
		return defence;
	}
	public void setDefence(double defence) {
		this.defence = defence;
	}
	public double getAllsteal() {
		return allsteal;
	}
	public void setAllsteal(double allsteal) {
		this.allsteal = allsteal;
	}
	public double getSteal() {
		return steal;
	}
	public void setSteal(double steal) {
		this.steal = steal;
	}
	public double getAllblock() {
		return allblock;
	}
	public void setAllblock(double allblock) {
		this.allblock = allblock;
	}
	public double getBlock() {
		return block;
	}
	public void setBlock(double block) {
		this.block = block;
	}
	public double getAllerror() {
		return allerror;
	}
	public void setAllerror(double allerror) {
		this.allerror = allerror;
	}
	public double getError() {
		return error;
	}
	public void setError(double error) {
		this.error = error;
	}
	public double getAllfoul() {
		return allfoul;
	}
	public void setAllfoul(double allfoul) {
		this.allfoul = allfoul;
	}
	public double getFoul() {
		return foul;
	}
	public void setFoul(double foul) {
		this.foul = foul;
	}
	public double getAllpoint() {
		return allpoint;
	}
	public void setAllpoint(double allpoint) {
		this.allpoint = allpoint;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public double getAllshoot() {
		return allshoot;
	}
	public void setAllshoot(double allshoot) {
		this.allshoot = allshoot;
	}
	public double getShoot() {
		return shoot;
	}
	public void setShoot(double shoot) {
		this.shoot = shoot;
	}
	public double getAllshootmade() {
		return allshootmade;
	}
	public void setAllshootmade(double allshootmade) {
		this.allshootmade = allshootmade;
	}
	public double getShootmade() {
		return shootmade;
	}
	public void setShootmade(double shootmade) {
		this.shootmade = shootmade;
	}
	public double getAllthreepoint() {
		return allthreepoint;
	}
	public void setAllthreepoint(double allthreepoint) {
		this.allthreepoint = allthreepoint;
	}
	public double getThreepoint() {
		return threepoint;
	}
	public void setThreepoint(double threepoint) {
		this.threepoint = threepoint;
	}
	public double getAllthreepointmade() {
		return allthreepointmade;
	}
	public void setAllthreepointmade(double allthreepointmade) {
		this.allthreepointmade = allthreepointmade;
	}
	public double getThreepointmade() {
		return threepointmade;
	}
	public void setThreepointmade(double threepointmade) {
		this.threepointmade = threepointmade;
	}
	public double getAllfreethrow() {
		return allfreethrow;
	}
	public void setAllfreethrow(double allfreethrow) {
		this.allfreethrow = allfreethrow;
	}
	public double getFreethrow() {
		return freethrow;
	}
	public void setFreethrow(double freethrow) {
		this.freethrow = freethrow;
	}
	public double getAllfreethrowmade() {
		return allfreethrowmade;
	}
	public void setAllfreethrowmade(double allfreethrowmade) {
		this.allfreethrowmade = allfreethrowmade;
	}
	public double getFreethrowmade() {
		return freethrowmade;
	}
	public void setFreethrowmade(double freethrowmade) {
		this.freethrowmade = freethrowmade;
	}
	public double getDoubledouble() {
		return doubledouble;
	}
	public void setDoubledouble(double doubledouble) {
		this.doubledouble = doubledouble;
	}
	public double getAllpointReboundAssist() {
		return allpointReboundAssist;
	}
	public void setAllpointReboundAssist(double allpointReboundAssist) {
		this.allpointReboundAssist = allpointReboundAssist;
	}
	public double getPointReboundAssist() {
		return pointReboundAssist;
	}
	public void setPointReboundAssist(double pointReboundAssist) {
		this.pointReboundAssist = pointReboundAssist;
	}
	public double getAllfieldgoalpercent() {
		return allfieldgoalpercent;
	}
	public void setAllfieldgoalpercent(double allfieldgoalpercent) {
		this.allfieldgoalpercent = allfieldgoalpercent;
	}
	public double getFieldgoalpercent() {
		return fieldgoalpercent;
	}
	public void setFieldgoalpercent(double fieldgoalpercent) {
		this.fieldgoalpercent = fieldgoalpercent;
	}
	public double getAllthreepointpercent() {
		return allthreepointpercent;
	}
	public void setAllthreepointpercent(double allthreepointpercent) {
		this.allthreepointpercent = allthreepointpercent;
	}
	public double getThreepointpercent() {
		return threepointpercent;
	}
	public void setThreepointpercent(double threepointpercent) {
		this.threepointpercent = threepointpercent;
	}
	public double getAllfreethrowpercent() {
		return allfreethrowpercent;
	}
	public void setAllfreethrowpercent(double allfreethrowpercent) {
		this.allfreethrowpercent = allfreethrowpercent;
	}
	public double getFreethrowpercent() {
		return freethrowpercent;
	}
	public void setFreethrowpercent(double freethrowpercent) {
		this.freethrowpercent = freethrowpercent;
	}
	public double getAllefficiency() {
		return allefficiency;
	}
	public void setAllefficiency(double allefficiency) {
		this.allefficiency = allefficiency;
	}
	public double getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}
	public double getAllgmsc() {
		return allgmsc;
	}
	public void setAllgmsc(double allgmsc) {
		this.allgmsc = allgmsc;
	}
	public double getGmsc() {
		return gmsc;
	}
	public void setGmsc(double gmsc) {
		this.gmsc = gmsc;
	}
	public double getAllrealshootpercent() {
		return allrealshootpercent;
	}
	public void setAllrealshootpercent(double allrealshootpercent) {
		this.allrealshootpercent = allrealshootpercent;
	}
	public double getRealshootpercent() {
		return realshootpercent;
	}
	public void setRealshootpercent(double realshootpercent) {
		this.realshootpercent = realshootpercent;
	}
	public double getAllshootefficiency() {
		return allshootefficiency;
	}
	public void setAllshootefficiency(double allshootefficiency) {
		this.allshootefficiency = allshootefficiency;
	}
	public double getShootefficiency() {
		return shootefficiency;
	}
	public void setShootefficiency(double shootefficiency) {
		this.shootefficiency = shootefficiency;
	}
	public double getAllreboundrate() {
		return allreboundrate;
	}
	public void setAllreboundrate(double allreboundrate) {
		this.allreboundrate = allreboundrate;
	}
	public double getReboundrate() {
		return reboundrate;
	}
	public void setReboundrate(double reboundrate) {
		this.reboundrate = reboundrate;
	}
	public double getAlloffensivereboundrate() {
		return alloffensivereboundrate;
	}
	public void setAlloffensivereboundrate(double alloffensivereboundrate) {
		this.alloffensivereboundrate = alloffensivereboundrate;
	}
	public double getOffensivereboundrate() {
		return offensivereboundrate;
	}
	public void setOffensivereboundrate(double offensivereboundrate) {
		this.offensivereboundrate = offensivereboundrate;
	}
	public double getAlldefensivereboundrate() {
		return alldefensivereboundrate;
	}
	public void setAlldefensivereboundrate(double alldefensivereboundrate) {
		this.alldefensivereboundrate = alldefensivereboundrate;
	}
	public double getDefensivereboundrate() {
		return defensivereboundrate;
	}
	public void setDefensivereboundrate(double defensivereboundrate) {
		this.defensivereboundrate = defensivereboundrate;
	}
	public double getAllassistrate() {
		return allassistrate;
	}
	public void setAllassistrate(double allassistrate) {
		this.allassistrate = allassistrate;
	}
	public double getAssistrate() {
		return assistrate;
	}
	public void setAssistrate(double assistrate) {
		this.assistrate = assistrate;
	}
	public double getAllstealrate() {
		return allstealrate;
	}
	public void setAllstealrate(double allstealrate) {
		this.allstealrate = allstealrate;
	}
	public double getStealrate() {
		return stealrate;
	}
	public void setStealrate(double stealrate) {
		this.stealrate = stealrate;
	}
	public double getAllblockrate() {
		return allblockrate;
	}
	public void setAllblockrate(double allblockrate) {
		this.allblockrate = allblockrate;
	}
	public double getBlockrate() {
		return blockrate;
	}
	public void setBlockrate(double blockrate) {
		this.blockrate = blockrate;
	}
	public double getAllerrorrate() {
		return allerrorrate;
	}
	public void setAllerrorrate(double allerrorrate) {
		this.allerrorrate = allerrorrate;
	}
	public double getErrorrate() {
		return errorrate;
	}
	public void setErrorrate(double errorrate) {
		this.errorrate = errorrate;
	}
	public double getAllusage() {
		return allusage;
	}
	public void setAllusage(double allusage) {
		this.allusage = allusage;
	}
	public double getUsage() {
		return usage;
	}
	public void setUsage(double usage) {
		this.usage = usage;
	}
}

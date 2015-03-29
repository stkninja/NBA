package po;

import java.io.Serializable;

public class PSeasonDataPO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String season;
	private String name;
	private String team;
	private String position;
	private String subArea;                  //分区
	private int gameplay;                    //参赛场数
	private int gamestart;                   //先发场数
	private double allrebound;                  //总篮板数
	private double rebound;                  //场均篮板数
	private double alloffensiverebound;      //总进攻篮板数
	private double offensiverebound;         //场均进攻篮板数
	private double alldefensiverebound;      //总防守篮板数
	private double defensiverebound;         //场均防守篮板数
	private double allassist;                   //总助攻数
	private double assist;                   //场均助攻数
	private double allminute;                //总在场时间
	private double minute;                   //场均在场时间
	private double alloffense;                  //总进攻数
    private double offense;                  //场均进攻数
	private double alldefence;                  //总防守数
	private double defence;                  //场均防守数
	private double allsteal;                    //总抢断数
	private double steal;                    //场均抢断数
	private double allblock;                    //总盖帽数
	private double block;                    //场均盖帽数
	private double allerror;                    //总失误数
	private double error;                    //场均失误数
	private double allfoul;                     //总犯规数
	private double foul;                     //场均犯规数
	private double allpoint;                    //总得分
	private double point;                    //场均得分
	private double allshoot;                    //总投篮出手数
	private double shoot;                    //场均投篮出手数
	private double allshootmade;             //总投篮命中数
	private double shootmade;                //场均投篮命中数
	private double allthreepoint;               //总三分出手数
	private double threepoint;               //场均三分出手数
	private double allthreepointmade;        //总三分命中数
	private double threepointmade;           //场均三分命中数
	private double allfreethrow;                //总罚球出手数
	private double freethrow;                //场均罚球出手数
	private double allfreethrowmade;         //总罚球命中数
	private double freethrowmade;            //场均罚球命中数
	private double doubledouble;                //两双
	private double allpointReboundAssist;       //总得分/篮板/助攻
	private double pointReboundAssist;       //场均得分/篮板/助攻
	private double allfieldgoalpercent;      //总投篮命中率
	private double fieldgoalpercent;         //场均投篮命中率
	private double allthreepointpercent; //总三分命中率
	private double threepointpercent; //场均三分命中率
	private double allfreethrowpercent;  //总罚球命中率
	private double freethrowpercent;  //场均罚球命中率
	private double allefficiency;    //总效率
	private double efficiency;    //场均效率
	private double allgmsc;          //总GmSc效率值
	private double gmsc;          //场均GmSc效率值
	private double allrealshootpercent;  //总真实命中率
	private double realshootpercent;  //场均真实命中率
	private double allshootefficiency;   //总投篮效率
	private double shootefficiency;   //场均投篮效率
	private double allreboundrate;       //总篮板率
	private double reboundrate;       //场均篮板率
	private double alloffensivereboundrate;  //总进攻篮板率
	private double offensivereboundrate;  //场均进攻篮板率
	private double alldefensivereboundrate;  //总防守篮板率
	private double defensivereboundrate;  //场均防守篮板率
	private double allassistrate;        //总助攻率
	private double assistrate;        //场均助攻率
	private double allstealrate;     //总抢断率
	private double stealrate;     //场均抢断率
	private double allblockrate;     //总盖帽率
	private double blockrate;     //场均盖帽率
	private double allerrorrate;     //总失误率
	private double errorrate;     //场均失误率
	private double allusage;         //总使用率
	private double usage;         //场均使用率
	
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

package vo;

import java.util.ArrayList;

public class PlayerVO {
	public String name;
	public String team;
	public String position;
	public String subArea;                  //分区
	public ArrayList<String> gameplay;      //参赛场数
	public ArrayList<String> gamestart;     //先发场数
	public ArrayList<String> rebound;       //篮板数
	public ArrayList<String> assist;        //助攻数
	public ArrayList<String> minute;        //在场时间
	public ArrayList<String> offense;       //进攻数
	public ArrayList<String> defence;       //防守数
	public ArrayList<String> steal;         //抢断数
	public ArrayList<String> block;         //盖帽数
	public ArrayList<String> error;         //失误数
	public ArrayList<String> foul;          //犯规数
	public ArrayList<String> point;         //得分
	public ArrayList<String> shoot;         //投篮数
	public ArrayList<String> threepoint;    //三分球
	public ArrayList<String> freethrow;     //罚球数
	public ArrayList<String> doubledouble;  //两双
	public ArrayList<String> fieldgoalpercent;  //投篮命中率
	public ArrayList<String> threepointpercent; //三分命中率
	public ArrayList<String> freethrowpercent;  //罚球命中率
	public ArrayList<String> efficiency;    //效率
	public ArrayList<String> gmsc;          //GmSc效率值
	public ArrayList<String> realshootpercent;  //真实命中率
	public ArrayList<String> shootefficiency;   //投篮效率
	public ArrayList<String> reboundrate;       //篮板率
	public ArrayList<String> offensivereboundrate;  //进攻篮板率
	public ArrayList<String> defensivereboundrate;  //防守篮板率
	public ArrayList<String> assistrate;        //助攻率
	public ArrayList<String> stealrate;     //抢断率
	public ArrayList<String> blockrate;     //盖帽率
	public ArrayList<String> errorrate;     //失误率
	public ArrayList<String> usage;         //使用率
}

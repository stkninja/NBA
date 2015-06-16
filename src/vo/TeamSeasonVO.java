package vo;

public class TeamSeasonVO {
	public String season;
	
	//基础数据
	public double avgpoint;//赛季所有球队的场均得分
	public double varpoint;//赛季球队场均得分的方差
	public double avgrebound;//赛季所有球队的场均篮板
	public double varrebound;//赛季球队场均篮板的方差
	public double avgassist;//赛季所有球队的场均助攻
	public double varassist;//赛季球队场均助攻的方差
	public double avgshoot;//赛季球队场均投篮出手
	public double avgthreepoint;//赛季球队场均三分球出手
	public double avgthreepointOfpoint;//赛季球队三分球出手占总投篮出手比重
	public double avgblock;//赛季所有球队的场均盖帽
	public double avgsteal;//场均抢断
	public double avgdefensiveRebounds;//场均防守篮板
	public double varthreepoint;
	public double threepointrate;
	public double avgthreepointmade;
	public double avgtwopoint;
	public double avgfreethrowpoint;
	
	//高阶数据
	public double avgattackround;//赛季球队场均进攻回合
	public double attackefficiency;//赛季球队进攻效率
	public double defenceefficiency;//赛季球队防守效率
	
	public double timeperattack;//每次进攻回合所花时间
}

package vo;

public class MatchPlayerDataVO {
	public String name = new String();
	public double offensiveRebounds;	//进攻篮板数
	public double defensiveRebounds;	//防守篮板数
	public double assist;        //助攻数
	public double minute;        //在场时间
	public double steal;         //抢断数
	public double block;         //盖帽数
	public double error;         //失误数
	public double foul;          //犯规数
	public double point;         //得分
	public double shoot;         //投篮数
	public double shootmade;     //投篮命中数
	public double threepoint;    //三分出手数
	public double threepointmade;//三分命中数
	public double freethrow;     //罚球出手数
	public double freethrowmade; //罚球命中数
	public double gameStart = 0.0;	  //比赛先发场数（0 / 1）
	
	public MatchPlayerDataVO(){
		
	}
}

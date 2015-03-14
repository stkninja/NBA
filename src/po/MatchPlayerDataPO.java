package po;


public class MatchPlayerDataPO {
	private String name = new String();
	//进攻篮板数
	private double offensiveRebounds;
	//防守篮板数
	private double defensiveRebounds;
	private double assist;        //助攻数
	private double minute;        //在场时间
	private double offense;       //进攻数
	private double defence;       //防守数
	private double steal;         //抢断数
	private double block;         //盖帽数
	private double error;         //失误数
	private double foul;          //犯规数
	private double point;         //得分
	private double shoot;         //投篮数
	private double shootmade;     //投篮命中数
	private double threepoint;    //三分出手数
	private double threepointmade;//三分命中数
	private double freethrow;     //罚球出手数
	private double freethrowmade; //罚球命中数
	private double doubledouble;  //两双
	
	public MatchPlayerDataPO() {}
	
	/**get set*/
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getAssist() {
		return assist;
	}

	public void setAssist(double assist) {
		this.assist = assist;
	}

	public double getMinute() {
		return minute;
	}

	public void setMinute(double minute) {
		this.minute = minute;
	}

	public double getOffense() {
		return offense;
	}

	public void setOffense(double offense) {
		this.offense = offense;
	}

	public double getDefence() {
		return defence;
	}

	public void setDefence(double defence) {
		this.defence = defence;
	}

	public double getSteal() {
		return steal;
	}

	public void setSteal(double steal) {
		this.steal = steal;
	}

	public double getBlock() {
		return block;
	}

	public void setBlock(double block) {
		this.block = block;
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}

	public double getFoul() {
		return foul;
	}

	public void setFoul(double foul) {
		this.foul = foul;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public double getShoot() {
		return shoot;
	}

	public void setShoot(double shoot) {
		this.shoot = shoot;
	}

	public double getShootmade() {
		return shootmade;
	}

	public void setShootmade(double shootmade) {
		this.shootmade = shootmade;
	}

	public double getThreepoint() {
		return threepoint;
	}

	public void setThreepoint(double threepoint) {
		this.threepoint = threepoint;
	}

	public double getThreepointmade() {
		return threepointmade;
	}

	public void setThreepointmade(double threepointmade) {
		this.threepointmade = threepointmade;
	}

	public double getFreethrow() {
		return freethrow;
	}

	public void setFreethrow(double freethrow) {
		this.freethrow = freethrow;
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

	public double getOffensiveRebounds() {
		return offensiveRebounds;
	}

	public void setOffensiveRebounds(double offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}

	public double getDefensiveRebounds() {
		return defensiveRebounds;
	}

	public void setDefensiveRebounds(double defensiveRebounds) {
		this.defensiveRebounds = defensiveRebounds;
	}
	
	/**总篮板数*/
	public double getRebound() {
		return this.offensiveRebounds + this.defensiveRebounds;
	}
}

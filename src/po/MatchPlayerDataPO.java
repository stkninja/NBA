package po;


public class MatchPlayerDataPO {
	private String name = new String();
	//����������
	private double offensiveRebounds;
	//����������
	private double defensiveRebounds;
	private double assist;        //������
	private double minute;        //�ڳ�ʱ��
	private double offense;       //������
	private double defence;       //������
	private double steal;         //������
	private double block;         //��ñ��
	private double error;         //ʧ����
	private double foul;          //������
	private double point;         //�÷�
	private double shoot;         //Ͷ����
	private double shootmade;     //Ͷ��������
	private double threepoint;    //���ֳ�����
	private double threepointmade;//����������
	private double freethrow;     //���������
	private double freethrowmade; //����������
	private double doubledouble;  //��˫
	
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
	
	/**��������*/
	public double getRebound() {
		return this.offensiveRebounds + this.defensiveRebounds;
	}
}

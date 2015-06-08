package po;

public class MatchPlayerDataPO{	
	private String name = new String();
	private double minute;        //�ڳ�ʱ��
	private double offensiveRebounds;	//����������
	private double defensiveRebounds;	//����������
	private double assist;        //������
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
	private double gameStart = 0.0;	  //�����ȷ�������0 / 1��
		
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
	
	public double getGameStart() {
		return gameStart;
	}
	
	public void setGameStart(double gameStart) {
		this.gameStart = gameStart;
	}
	
	/**��������*/
	public double getRebound() {
		return this.offensiveRebounds + this.defensiveRebounds;
	}
	
	/**������*/
	public double getDefence(){
		return this.defensiveRebounds;
	}
	
	/**������*/
	public double getOffense(){
		return this.offensiveRebounds;
	}
	
	/**��˫ 
	 * �÷������������ϸ�ñ ��������ڵ���10��Ϊ��˫
	 * */
	public double getDoubledouble() {
		int num = 0;
		
		if(this.point >= 10)
			num++;
		if(this.getRebound() >= 10)
			num++;
		if(this.assist >= 10)
			num++;
		if(this.steal >= 10)
			num++;
		if(this.block >= 10)
			num++;
	
		if(num >= 2)
			return 1.0;
		else
			return 0.0;
	}
}

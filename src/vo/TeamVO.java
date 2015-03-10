package vo;

import java.awt.Image;
import java.util.ArrayList;

public class TeamVO {
	/**��ӻ�����Ϣ*/
	//��ӱ�־
	public Image teamLogo;
	//���ȫ��
	public String fullName;
	//�����д
	public String abbName;
	//���ڵ�
	public String location;
	//����
	public String competionArea;
	//����
	public String subArea;
	//����
	public String homeGround;
	//����ʱ��
	public int setupTime;
	
	/**��ӱ�����Ϣ*/
	//��������
	public double gamesNum;
	//ʤ������
	public double winsNum;
	//�����ܡ�����Ͷ��������
	public ArrayList<String> shootingHit = new ArrayList<String>();
	//�����ܡ�����Ͷ��������
	public ArrayList<String> shooting = new ArrayList<String>();
	//�����ܡ���������������
	public ArrayList<String> threePointHits = new ArrayList<String>();
	//�����ܡ��������ֳ�����
	public ArrayList<String> threePoint = new ArrayList<String>();
	//�����ܡ���������������
	public ArrayList<String> freeThrowHit = new ArrayList<String>();
	//�����ܡ��������������
	public ArrayList<String> freeThrow = new ArrayList<String>();
	//�����ܡ���������������
	public ArrayList<String> offensiveRebounds = new ArrayList<String>();
	//�����ܡ���������������
	public ArrayList<String> defensiveRebounds = new ArrayList<String>();
	//�����ܡ��������ֽ���������
	public ArrayList<String> opponentOffensiveRebounds = new ArrayList<String>();
	//�����ܡ��������ַ���������
	public ArrayList<String> opponentDefensiveRebounds = new ArrayList<String>();
	//�����ܡ�����������
	public ArrayList<String> rebounds = new ArrayList<String>();
	//�����ܡ�����������
	public ArrayList<String> assists = new ArrayList<String>();
	//�����ܡ�����������
	public ArrayList<String> steals = new ArrayList<String>();
	//�����ܡ�������ñ��
	public ArrayList<String> caps = new ArrayList<String>();
	//�����ܡ�����ʧ����
	public ArrayList<String> turnovers = new ArrayList<String>();
	//�����ܡ�����������
	public ArrayList<String> fouls = new ArrayList<String>();
	//�����ܡ����������÷�
	public ArrayList<String> scores = new ArrayList<String>();
	//�����ܡ�����Ͷ��������
	public ArrayList<String> shootingHitRate = new ArrayList<String>();
	//�����ܡ���������������
	public ArrayList<String> freeThrowHitRate = new ArrayList<String>();
	//�����ܡ���������������
	public ArrayList<String> threePointHitRate = new ArrayList<String>();
	//����ʤ��
	public double winsRate;
	//�����ܡ����������غ�
	public ArrayList<String> attackRound = new ArrayList<String>();
	//�����ܡ���������Ч��
	public ArrayList<String> attackEfficiency = new ArrayList<String>();
	//�����ܡ���������Ч��
	public ArrayList<String> defenceEfficiency = new ArrayList<String>();
	//�����ܡ���������Ч��
	public ArrayList<String> reboundsEfficiency = new ArrayList<String>();
	//�����ܡ���������Ч��
	public ArrayList<String> stealEfficiency = new ArrayList<String>();
	//�����ܡ���������Ч��
	public ArrayList<String> assistEfficiency = new ArrayList<String>();

	public TeamVO(Image teamLogo, String fullName, String abbName,
			String location, String competionArea, String subArea,
			String homeGround, int setupTime, double gamesNum, double winsNum,
			ArrayList<String> shootingHit, ArrayList<String> shooting,
			ArrayList<String> threePointHits, ArrayList<String> threePoint,
			ArrayList<String> freeThrowHit, ArrayList<String> freeThrow,
			ArrayList<String> offensiveRebounds,
			ArrayList<String> defensiveRebounds,
			ArrayList<String> opponentOffensiveRebounds,
			ArrayList<String> opponentDefensiveRebounds,
			ArrayList<String> rebounds, ArrayList<String> assists,
			ArrayList<String> steals, ArrayList<String> caps,
			ArrayList<String> turnovers, ArrayList<String> fouls,
			ArrayList<String> scores) {
		this.initTeamInfo(teamLogo, fullName, abbName,
				location, competionArea, subArea,
				homeGround, setupTime);
		this.initGamesInfo(gamesNum, winsNum,
				shootingHit, shooting,
				threePointHits, threePoint,
				freeThrowHit, freeThrow,
				offensiveRebounds,
				defensiveRebounds,
				opponentOffensiveRebounds,
				opponentDefensiveRebounds,
				rebounds, assists,
				steals, caps,
				turnovers, fouls,
				scores);
	}
	
	/**��ʼ�������Ϣ*/
	private void initTeamInfo(Image teamLogo, String fullName, String abbName,
			String location, String competionArea, String subArea,
			String homeGround, int setupTime){
		this.teamLogo = teamLogo;
		this.fullName = fullName;
		this.abbName = abbName;
		this.location = location;
		this.competionArea = competionArea;
		this.subArea = subArea;
		this.homeGround = homeGround;
		this.setupTime = setupTime;
	}
	
	/**��ʼ��������Ϣ*/
	private void initGamesInfo(double gamesNum, double winsNum,
			ArrayList<String> shootingHit, ArrayList<String> shooting,
			ArrayList<String> threePointHits, ArrayList<String> threePoint,
			ArrayList<String> freeThrowHit, ArrayList<String> freeThrow,
			ArrayList<String> offensiveRebounds,
			ArrayList<String> defensiveRebounds,
			ArrayList<String> opponentOffensiveRebounds,
			ArrayList<String> opponentDefensiveRebounds,
			ArrayList<String> rebounds, ArrayList<String> assists,
			ArrayList<String> steals, ArrayList<String> caps,
			ArrayList<String> turnovers, ArrayList<String> fouls,
			ArrayList<String> scores){
		this.gamesNum = gamesNum;
		this.winsNum = winsNum;
		this.shootingHit = shootingHit;
		this.shooting = shooting;
		this.threePointHits = threePointHits;
		this.threePoint = threePoint;
		this.freeThrowHit = freeThrowHit;
		this.freeThrow = freeThrow;
		this.offensiveRebounds = offensiveRebounds;
		this.defensiveRebounds = defensiveRebounds;
		this.opponentOffensiveRebounds = opponentOffensiveRebounds;
		this.opponentDefensiveRebounds = opponentDefensiveRebounds;
		this.rebounds = rebounds;
		this.assists = assists;
		this.steals = steals;
		this.caps = caps;
		this.turnovers = turnovers;
		this.fouls = fouls;
		this.scores = scores;
	}
	

}

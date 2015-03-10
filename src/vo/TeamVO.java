package vo;

import java.awt.Image;
import java.util.ArrayList;

public class TeamVO {
	/**球队基本信息*/
	//球队标志
	public Image teamLogo;
	//球队全名
	public String fullName;
	//球队缩写
	public String abbName;
	//所在地
	public String location;
	//赛区
	public String competionArea;
	//分区
	public String subArea;
	//主场
	public String homeGround;
	//建立时间
	public int setupTime;
	
	/**球队比赛信息*/
	//比赛场数
	public double gamesNum;
	//胜利场数
	public double winsNum;
	//赛季总、场均投篮命中数
	public ArrayList<String> shootingHit = new ArrayList<String>();
	//赛季总、场均投篮出手数
	public ArrayList<String> shooting = new ArrayList<String>();
	//赛季总、场均三分命中数
	public ArrayList<String> threePointHits = new ArrayList<String>();
	//赛季总、场均三分出手数
	public ArrayList<String> threePoint = new ArrayList<String>();
	//赛季总、场均罚球命中数
	public ArrayList<String> freeThrowHit = new ArrayList<String>();
	//赛季总、场均罚球出手数
	public ArrayList<String> freeThrow = new ArrayList<String>();
	//赛季总、场均进攻篮板数
	public ArrayList<String> offensiveRebounds = new ArrayList<String>();
	//赛季总、场均防守篮板数
	public ArrayList<String> defensiveRebounds = new ArrayList<String>();
	//赛季总、场均对手进攻篮板数
	public ArrayList<String> opponentOffensiveRebounds = new ArrayList<String>();
	//赛季总、场均对手防守篮板数
	public ArrayList<String> opponentDefensiveRebounds = new ArrayList<String>();
	//赛季总、场均篮板数
	public ArrayList<String> rebounds = new ArrayList<String>();
	//赛季总、场均助攻数
	public ArrayList<String> assists = new ArrayList<String>();
	//赛季总、场均抢断数
	public ArrayList<String> steals = new ArrayList<String>();
	//赛季总、场均盖帽数
	public ArrayList<String> caps = new ArrayList<String>();
	//赛季总、场均失误数
	public ArrayList<String> turnovers = new ArrayList<String>();
	//赛季总、场均犯规数
	public ArrayList<String> fouls = new ArrayList<String>();
	//赛季总、场均比赛得分
	public ArrayList<String> scores = new ArrayList<String>();
	//赛季总、场均投篮命中率
	public ArrayList<String> shootingHitRate = new ArrayList<String>();
	//赛季总、场均罚球命中率
	public ArrayList<String> freeThrowHitRate = new ArrayList<String>();
	//赛季总、场均三分命中率
	public ArrayList<String> threePointHitRate = new ArrayList<String>();
	//赛季胜率
	public double winsRate;
	//赛季总、场均进攻回合
	public ArrayList<String> attackRound = new ArrayList<String>();
	//赛季总、场均进攻效率
	public ArrayList<String> attackEfficiency = new ArrayList<String>();
	//赛季总、场均防守效率
	public ArrayList<String> defenceEfficiency = new ArrayList<String>();
	//赛季总、场均篮板效率
	public ArrayList<String> reboundsEfficiency = new ArrayList<String>();
	//赛季总、场均抢断效率
	public ArrayList<String> stealEfficiency = new ArrayList<String>();
	//赛季总、场均助攻效率
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
	
	/**初始化球队信息*/
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
	
	/**初始化比赛信息*/
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

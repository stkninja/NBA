package vo;

import java.util.ArrayList;

public class MatchTeamDataVO {
	public String abbName = new String();
	//比赛得分
	public double scores;
	public ArrayList<MatchPlayerDataVO> teamPlayers = new ArrayList<MatchPlayerDataVO>();
	
	public MatchTeamDataVO(){
		
	}
}

package data;

import java.util.ArrayList;

import po.MatchPO;
import po.PlayerBasicInfoPO;
import po.TeamBasicInfoPO;



public class tempMain {
	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		
//		ArrayList<PlayerBasicInfoPO> data = ArrangePlayersBasicInfo.arrangePlayersBasicInfo();
//		ArrayList<TeamBasicInfoPO> data2 = ArrangeTeamsBasicInfo.arrangeTeamsBasicInfo();		
		ArrayList<MatchPO> data3 = ArrangeMatches.arrangeMatches();
		
		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
	}
}

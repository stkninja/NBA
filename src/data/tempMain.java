package data;

import java.util.ArrayList;

import po.PlayerBasicInfoPO;
import po.TeamBasicInfoPO;



public class tempMain {
	public static void main(String[] args) {
		ArrayList<PlayerBasicInfoPO> data = ArrangePlayersBasicInfo.arrangePlayersBasicInfo();
//		ArrayList<TeamBasicInfoPO> data2 = ArrangeTeams.arrangeTeamsBasicInfo();		
		System.out.println(data.size());
	}
}

package data;

import java.util.ArrayList;

import po.TeamBasicInfoPO;


public class tempMain {
	public static void main(String[] args) {
		ArrayList<TeamBasicInfoPO> data = ArrangeTeams.arrangeTeams();
		System.out.println(data.size());
	}
}

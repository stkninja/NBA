package data;

import java.util.ArrayList;

import po.TeamBasicInfoPO;
import dataservice.ITeam;

public class GetTeamsInfo implements ITeam{

	public TeamBasicInfoPO getSingleTeamBasicInfo(String fullName) {
		return null;
	}

	public ArrayList<TeamBasicInfoPO> getAllTeamsBasicInfo() {
		return null;
	}

	public ArrayList<String> getAllTeamsName() {
		return null;
	}

	public double getwinNum(String fullName, String season) {
		return 0;
	}

	public double getMainNum(String fullName, String season) {
		return 0;
	}

}

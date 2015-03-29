package businesslogic;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchTeamDataPO;
import po.TeamBasicInfoPO;
import data.GetMatchesInfo;
import data.GetTeamsInfo;
import dataservice.IMatch;
import dataservice.ITeam;
import vo.TeamBasicInfoVO;
import vo.TeamVO;

public class TeamBL implements businesslogicservice.TeamBLService{
	private ITeam teamdata = null;
	private IMatch matchdata = null;
	
	public TeamBL(){
		teamdata = new GetTeamsInfo();
		matchdata = new GetMatchesInfo();
	}
	
	@Override
	public ArrayList<TeamVO> getTeams(String subArea) {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		if(subArea.equals("All")){
			return getAllTeams();
		}
		else{
			for(TeamVO vo : getAllTeams()){
				if(vo.subArea.equals(subArea)){
					list.add(vo);
				}
			}
		}
		
		return list;
	}

	@Override
	public TeamBasicInfoVO getOneTeam(String name) {
		// TODO Auto-generated method stub
		TeamBasicInfoVO vo = new TeamBasicInfoVO();
		TeamBasicInfoPO po = teamdata.getSingleTeamBasicInfo(name);
		vo.teamLogo = po.getTeamLogo();
		vo.fullName = po.getFullName();
		vo.abbName = po.getAbbName();
		vo.location = po.getLocation();
		vo.competionArea = po.getCompetionArea();
		vo.subArea = po.getSubArea();
		vo.homeGround = po.getHomeGround();
		vo.setupTime = po.getSetupTime();
		return vo;
	}

	public ArrayList<TeamVO> getAllTeams() {
		ArrayList<TeamVO> list = new ArrayList<TeamVO>();
		for(String name : teamdata.getAllTeamsAbbName()){
			list.add(findTeam(name));
		}
		return list;
	}

}

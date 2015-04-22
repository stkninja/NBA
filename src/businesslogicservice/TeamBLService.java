package businesslogicservice;

import java.util.ArrayList;

import vo.MatchVO;
import vo.TeamBasicInfoVO;
import vo.TeamVO;

public interface TeamBLService {
	public ArrayList<TeamVO> getTeams(String season,String subArea);
	
	public TeamBasicInfoVO getOneTeam(String name);
	
	public ArrayList<TeamVO> getTeamsInfo(String season,String name);
	 
	public TeamVO getSeasonTeams(String season,String name);
	
	public ArrayList<TeamVO> getAllSeasonTeam(String name);
	
	public ArrayList<TeamVO> getSeasonEachMatch(String season,String name);
	
	public ArrayList<MatchVO> getSeasonMatches(String season,String name);
	
	public ArrayList<MatchVO> getLastFiveMatchesSpecific(String name);
	
	public ArrayList<TeamVO> getLastFiveMatches(String name);
	
	public ArrayList<TeamVO> sortTeam(ArrayList<TeamVO> list,ArrayList<String> filter,String sortOrder);
	//排序算法，filter为排序属性，sortOrder的值为"升序"或"降序"
	
	public ArrayList<String> getFilters();
    //返回所有排序条件
	
	public ArrayList<TeamVO> getSeasonTopFiveTeams(String season,String filter);
	
}

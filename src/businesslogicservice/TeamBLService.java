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
	//�����㷨��filterΪ�������ԣ�sortOrder��ֵΪ"����"��"����"
	
	public ArrayList<String> getFilters();
    //����������������
	
	public ArrayList<TeamVO> getSeasonTopFiveTeams(String season,String filter);
	
}

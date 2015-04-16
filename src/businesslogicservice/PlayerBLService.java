package businesslogicservice;

import java.util.ArrayList;

import vo.MatchVO;
import vo.PlayerBasicInfoVO;
import vo.PlayerVO;

public interface PlayerBLService {
	public ArrayList<PlayerVO> getPlayers(String season,String subArea,String position,String team);
	
	public PlayerBasicInfoVO getOnePlayer(String name);

	public ArrayList<MatchVO> getLastFiveMatches(String name);
	
	public PlayerVO getPlayerPast(String season,String name);
	
	public ArrayList<PlayerVO> getAllSeasonPlayer(String name);
	
	public ArrayList<PlayerVO> getSeasonPlayers(String season);
	
	public ArrayList<PlayerVO> sortPlayer(ArrayList<PlayerVO> list,ArrayList<String> filter,String sortOrder);
	//�����㷨��filterΪ�������ԣ�sortOrder��ֵΪ"����"��"����"
	
	public ArrayList<PlayerVO> getTodayTopFivePlayers(String filter);//����ɸѡ�����ӵ���ɸѡǰ5��
	
	public ArrayList<PlayerVO> getSeasonTopFivePlayers(String season,String filter);//����ɸѡ����������ɸѡǰ5��
	
	public ArrayList<PlayerVO> getPromotionPlayers(String filter);//����ɸѡ����ɸѡ���������Ա
}

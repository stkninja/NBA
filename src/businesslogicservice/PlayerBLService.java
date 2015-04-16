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
	//排序算法，filter为排序属性，sortOrder的值为"升序"或"降序"
	
	public ArrayList<PlayerVO> getTodayTopFivePlayers(String filter);//依据筛选条件从当天筛选前5名
	
	public ArrayList<PlayerVO> getSeasonTopFivePlayers(String season,String filter);//依据筛选条件从赛季筛选前5名
	
	public ArrayList<PlayerVO> getPromotionPlayers(String filter);//依据筛选条件筛选进步最快球员
}

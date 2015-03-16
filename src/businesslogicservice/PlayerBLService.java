package businesslogicservice;

import java.util.ArrayList;

import vo.PlayerBasicInfoVO;
import vo.PlayerVO;

public interface PlayerBLService {
	public ArrayList<PlayerVO> getPlayers(String subArea,String position,String team);
	
	public PlayerBasicInfoVO getOnePlayer(String name);
	
	public ArrayList<PlayerVO> getAllPlayers();
}

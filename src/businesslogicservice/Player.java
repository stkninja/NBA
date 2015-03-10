package businesslogicservice;

import java.util.ArrayList;

import vo.PlayerVO;

public interface Player {
	public ArrayList<PlayerVO> getPlayers(String subArea,String position,String team);
	
	public PlayerVO getOnePlayer(String name,String team);
	
	public ArrayList<PlayerVO> getAllPlayers();
	
}

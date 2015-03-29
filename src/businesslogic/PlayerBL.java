package businesslogic;

import java.util.ArrayList;

import po.MatchPO;
import po.MatchPlayerDataPO;
import po.PlayerBasicInfoPO;
import data.GetMatchesInfo;
import data.GetPlayersInfo;
import dataservice.IMatch;
import dataservice.IPlayer;
import vo.MatchPlayerDataVO;
import vo.PlayerBasicInfoVO;
import vo.PlayerVO;
import vo.TeamVO;

public class PlayerBL implements businesslogicservice.PlayerBLService{
	private IPlayer playerdata = null;
	private IMatch matchdata = null;
	private TeamBL teambl = null;
	
	public PlayerBL(){
		playerdata = new GetPlayersInfo();
		matchdata = new GetMatchesInfo();
		teambl = new TeamBL();
	}
	
	@Override
	public ArrayList<PlayerVO> getPlayers(String subArea, String position,
			String team) {
		// TODO Auto-generated method stub
		ArrayList<PlayerVO> list = new ArrayList<PlayerVO>();
		if(subArea.equals("All")){
			if(position.equals("All")){
				if(team.equals("All")){
					return getAllPlayers();
				}
				else{
					for(PlayerVO vo : getAllPlayers()){
						if(vo.team.equals(team)){
							list.add(vo);
						}
					}
				}
			}
			else{
				if(team.equals("All")){
					for(PlayerVO vo : getAllPlayers()){
						if(vo.position.indexOf(position) >= 0){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getAllPlayers()){
						if(vo.team.equals(team) && vo.position.indexOf(position) >= 0){
							list.add(vo);
						}
					}
				}
			}
		}
		else{
			if(position.equals("All")){
				if(team.equals("All")){
					for(PlayerVO vo : getAllPlayers()){
						if(vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getAllPlayers()){
						if(vo.team.equals(team) && vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
			}
			else{
				if(team.equals("All")){
					for(PlayerVO vo : getAllPlayers()){
						if(vo.position.indexOf(position) >= 0 && vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
				else{
					for(PlayerVO vo : getAllPlayers()){
						if(vo.team.equals(team) && vo.position.indexOf(position) >= 0 && vo.subArea.equals(subArea)){
							list.add(vo);
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public PlayerBasicInfoVO getOnePlayer(String name) {
		// TODO Auto-generated method stub
		PlayerBasicInfoVO vo = new PlayerBasicInfoVO();
		PlayerBasicInfoPO pp = playerdata.getSinglePlayerBasicInfo(name);
		vo.name = pp.getName();
		vo.number = pp.getNumber();
		vo.height = pp.getHeight();
		vo.weight = pp.getWeight();
		vo.birth = pp.getBirth();
		vo.age = pp.getAge();
		vo.exp = pp.getExp();
		vo.school = pp.getSchool();
		vo.portrait = pp.getPortrait();
		vo.action = pp.getAction();
		vo.position = pp.getPosition();
		return vo;
	}

	@Override
	public ArrayList<PlayerVO> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MatchPlayerDataVO> getPlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

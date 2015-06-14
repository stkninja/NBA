package businesslogic;

import spider.spiderLive.LiveInfo;
import vo.LiveInfoVO;
import data.GetMatchInfo;
import dataservice.MatchService;

public class LiveBL implements businesslogicservice.LiveBLService{
	private MatchService matchdata = null;
	
	public LiveBL(){
		matchdata = new GetMatchInfo();
	}
	
	@SuppressWarnings("static-access")
	public LiveInfoVO getLiveInfo(){
		LiveInfoVO vo = new LiveInfoVO();
		LiveInfo po = matchdata.getLiveInfo();
		vo.homeScore = Integer.parseInt(po.getScore().split("-")[0]);
		vo.visitScore = Integer.parseInt(po.getScore().split("-")[1]);
		vo.homeTeam = po.getHomeTeam();
		vo.visitTeam = po.getVisitTeam();
		vo.live = po.getMatchRecords();
		int temp = 1;
		for(int i = 0;i < po.getMatchRecords().length;i ++){
			if(vo.live[i][2].equals("第1节开始")){
				temp = 1;
				break;
				
			}
			else if(vo.live[i][2].equals("第2节开始")){
				temp = 2;
				break;
				
			}
			else if(vo.live[i][2].equals("第3节开始")){
				temp = 3;
				break;
				
			}
			else if(vo.live[i][2].equals("第4节开始")){
				temp = 4;
				break;
				
			}
		}
		
		if(temp == 1){
			vo.matchrecord[0] = vo.homeScore;
			vo.matchrecord[1] = vo.visitScore;
		}
		else if(temp == 2){
			vo.matchrecord[2] = vo.homeScore - objectToInt(vo.matchrecord[0]);
			vo.matchrecord[3] = vo.visitScore - objectToInt(vo.matchrecord[1]);
		}
		else if(temp == 3){
			vo.matchrecord[4] = vo.homeScore - objectToInt(vo.matchrecord[0]) - objectToInt(vo.matchrecord[2]);
			vo.matchrecord[5] = vo.visitScore - objectToInt(vo.matchrecord[1]) - objectToInt(vo.matchrecord[3]);
		}
		else if(temp == 4){
			vo.matchrecord[6] = vo.homeScore - objectToInt(vo.matchrecord[0]) - objectToInt(vo.matchrecord[2]) - objectToInt(vo.matchrecord[4]);
			vo.matchrecord[7] = vo.visitScore - objectToInt(vo.matchrecord[1]) - objectToInt(vo.matchrecord[3]) - objectToInt(vo.matchrecord[5]);
		}
		
		return vo;
	}
	
	public int objectToInt(Object o){
		return Integer.parseInt(String.valueOf(o));
	}
}

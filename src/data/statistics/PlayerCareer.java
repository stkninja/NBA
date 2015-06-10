package data.statistics;

import java.util.ArrayList;

import po.MatchPlayerDataPO;
import po.PSeasonDataPO;

public class PlayerCareer {
	public PSeasonDataPO getPlayerCareerData(ArrayList<MatchPlayerDataPO> players){
		PSeasonDataPO po = new PSeasonDataPO();
		po.setGameplay(players.size());
		for(MatchPlayerDataPO playerpo : players){
			if(playerpo.getGameStart() == 1){
				po.setGamestart(po.getGamestart() + 1);
			}
			po.setAllminute(po.getAllminute() + playerpo.getMinute());
			po.setAllshoot(po.getAllshoot() + playerpo.getShoot());
			po.setAllshootmade(po.getAllshootmade() + playerpo.getShootmade());
			po.setAllthreepoint(po.getAllthreepoint() + playerpo.getThreepoint());
			po.setAllthreepointmade(po.getAllthreepointmade() + playerpo.getThreepointmade());
			po.setAllfreethrow(po.getAllfreethrow() + playerpo.getFreethrow());
			po.setAllfreethrowmade(po.getAllfreethrowmade() + playerpo.getFreethrowmade());
			po.setAlloffensiverebound(po.getAlloffensiverebound() + playerpo.getOffensiveRebounds());
			po.setAlldefensiverebound(po.getAlldefensiverebound() + playerpo.getDefensiveRebounds());
			po.setAllassist(po.getAllassist() + playerpo.getAssist());
			po.setAllsteal(po.getAllsteal() + playerpo.getSteal());
			po.setAllblock(po.getAllblock() + playerpo.getBlock());
			po.setAllerror(po.getAllerror() + playerpo.getError());
			po.setAllfoul(po.getAllfoul() + playerpo.getFoul());
			po.setAllpoint(po.getAllpoint() + playerpo.getPoint());
		}
		po.setAllfieldgoalpercent(getDouble(po.getAllshootmade() / po.getAllshoot()));
		po.setAllthreepointpercent(getDouble(po.getAllthreepointmade() / po.getAllthreepoint()));
		po.setAllfreethrowpercent(getDouble(po.getAllfreethrowmade() / po.getAllfreethrow()));
		po.setAlloffensiverebound(getDouble(po.getAlloffensiverebound()));
		po.setAlldefensiverebound(getDouble(po.getAlldefensiverebound()));
		po.setAllrebound(getDouble(po.getAlloffensiverebound() + po.getAlldefensiverebound()));
		
		return po;
	}
	
	public double getDouble(double d){
		return new  java.math.BigDecimal(Double.toString(d)).setScale(1,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}

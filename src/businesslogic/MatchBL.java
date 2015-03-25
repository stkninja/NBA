package businesslogic;

import java.util.ArrayList;

import vo.MatchVO;
import data.GetMatchesInfo;
import dataservice.IMatch;

public class MatchBL implements businesslogicservice.MatchBLService{
	private IMatch matchdata = null;
	
	public MatchBL(){
		matchdata = new GetMatchesInfo();
	}

	@Override
	public ArrayList<MatchVO> getAllMatches() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

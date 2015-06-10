package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import po.PSeasonDataPO;
import po.TBasicInfoPO;
import po.TSeasonDataPO;
import dataBase.dataBaseOpe.DataBaseOpe;
import dataservice.PlayerService;
import dataservice.TeamService;

public class GetTeamInfo implements TeamService{

	public TBasicInfoPO getSingleTBasicInfo(String abbName) {
		String order = "SELECT * FROM t_team WHERE tempAbbName = '" + abbName + 
				"' OR hisAbbName = '" + abbName +"'";
		ResultSet rs = DataBaseOpe.querySQL(order);
		
		return RSToBasicPO.toTeamBasic(rs).get(0);
	}

	public ArrayList<TBasicInfoPO> getAllTBasicInfo() {
		ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_team");
		return RSToBasicPO.toTeamBasic(rs);
	}
	
	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season) {
		return null;
	}

	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season) {
		return null;
	}	
	
	public static void main(String[] args) {
		PlayerService ps = new GetPlayerInfo();
		PSeasonDataPO psd = ps.getOnePSeasonDataPO("Stephen Curry", "13-14");
	}
}

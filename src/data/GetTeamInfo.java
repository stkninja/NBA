package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import po.TBasicInfoPO;
import po.TSeasonDataPO;
import dataBase.dataBaseOpe.DataBaseOpe;
import dataservice.TeamService;

public class GetTeamInfo implements TeamService{

	public TBasicInfoPO getSingleTBasicInfo(String abbName) {
		String order = "SELECT * FROM t_team WHERE tempAbbName = '" + abbName + 
				"' OR hisAbbName = '" + abbName +"'";
		ResultSet rs = DataBaseOpe.querySQL(order);
		
		return RSToBasicPO.toTeamBasic(rs);
	}

	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season) {
		return null;
	}

	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season) {
		return null;
	}	
	
	public static void main(String[] args) {
		GetMatchInfo ms = new GetMatchInfo();
		System.out.println(ms.getLastDate());
	}
}

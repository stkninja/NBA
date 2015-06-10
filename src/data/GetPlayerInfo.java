package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import po.PBasicInfoPO;
import po.PSeasonDataPO;
import dataBase.dataBaseOpe.DataBaseOpe;
import dataservice.PlayerService;

public class GetPlayerInfo implements PlayerService{

	public ArrayList<String> getAllPlayersName() {
		ArrayList<String> names = new ArrayList<String>();
		
		String order = "SELECT name FROM t_player";
		ResultSet rs = DataBaseOpe.querySQL(order);
		ArrayList<String[]> tab = RSToBasicPO.to2DStringArray(rs);
		for(int i = 0; i < tab.size(); i++)
			names.add(tab.get(i)[0]);
		
		return names;
	}

	public PBasicInfoPO getSinglePBasicInfo(String name) {
		String order = "SELECT * FROM t_player WHERE name = '" + name + "'";
		ResultSet rs = DataBaseOpe.querySQL(order);
		
		return RSToBasicPO.toPlayerBasic(rs);
	}

	public PSeasonDataPO getOnePSeasonDataPO(String name, String season) {
		return null;
	}

	public ArrayList<PSeasonDataPO> getAllPSeasonData(String season) {
		return null;			
	}
}

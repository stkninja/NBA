package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import po.MatchPO;
import po.TBasicInfoPO;
import po.TSeasonDataPO;
import data.pre.Predo;
import data.statistics.TeamSeason;
import dataBase.dataBaseOpe.DataBaseOpe;
import dataservice.TeamService;

public class GetTeamInfo implements TeamService{

	public TBasicInfoPO getSingleTBasicInfo(String abbName) {
		String order = "SELECT * FROM t_team WHERE tempAbbName = '" + abbName + 
				"' OR hisAbbName = '" + abbName +"' OR hisAbbName2 = '" + abbName + "'";
		ResultSet rs = DataBaseOpe.querySQL(order);
		TBasicInfoPO ret =  RSToBasicPO.toTeamBasic(rs).get(0);
		
		DataBaseOpe.close();
		return ret;
	}

	public ArrayList<TBasicInfoPO> getAllTBasicInfo() {
		ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_team");
		ArrayList<TBasicInfoPO> ret = RSToBasicPO.toTeamBasic(rs);
		
		DataBaseOpe.close();
		return ret;
	}
	
	public TSeasonDataPO getOneTSeasonDataPO(String abbName, String season) {
		ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_team WHERE tempAbbName = '" + abbName + "' OR hisAbbName = '" + abbName + "'");
		try {
			rs.next();
			String tempAbbName = rs.getString(2);
			String hisAbbName = rs.getString(4);
			String hisAbbName2 = rs.getString(5);
			DataBaseOpe.close();

			ArrayList<TSeasonDataPO> tsds = Predo.getAllTSeasonDataAt(season);
			for(TSeasonDataPO tsd : tsds){
				if(tsd.getAbbName().equals(hisAbbName) || tsd.getAbbName().equals(tempAbbName) || tsd.getAbbName().equals(hisAbbName2))
					return tsd;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<TSeasonDataPO> getAllTSeasonData(String season){
		return Predo.getAllTSeasonDataAt(season);
	}
	
	public ArrayList<TSeasonDataPO> getAllTSeasonData_2(String season) {
		ArrayList<TSeasonDataPO> tsds = new ArrayList<TSeasonDataPO>();
		
		ArrayList<TBasicInfoPO> tbs = this.getAllTBasicInfo();
		ArrayList<MatchPO> matches = new GetMatchInfo().getAllMatchesAtSeason(season);
		String abbName = new String();
		ArrayList<MatchPO> matchesAboutTeam = new ArrayList<MatchPO>();
		for(TBasicInfoPO tb : tbs){			
			//筛选所有关于team的比赛
			abbName = new String();
			matchesAboutTeam = new ArrayList<MatchPO>();
			for(int i = 0; i < matches.size(); i++){
				if(matches.get(i).getTeam1().getAbbName().equals(tb.getAbbName()) || matches.get(i).getTeam2().getAbbName().equals(tb.getAbbName())){
					matchesAboutTeam.add(matches.get(i));
					abbName = tb.getAbbName();
				}
				else if(matches.get(i).getTeam1().getAbbName().equals(tb.getHistoryAbblName()) || matches.get(i).getTeam2().getAbbName().equals(tb.getHistoryAbblName())){
					matchesAboutTeam.add(matches.get(i));
					abbName = tb.getHistoryAbblName();
				}
				else if(matches.get(i).getTeam1().getAbbName().equals(tb.getHistoryAbblName2()) || matches.get(i).getTeam2().getAbbName().equals(tb.getHistoryAbblName2())){
					matchesAboutTeam.add(matches.get(i));
					abbName = tb.getHistoryAbblName2();
				}
			}
			
			//计算
			if(matchesAboutTeam.size() != 0)
				tsds.add(new TeamSeason().teamSeason(matchesAboutTeam, tb, abbName));
		}
		return tsds;
	}	
}

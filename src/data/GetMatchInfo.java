package data;

import java.io.File;
import java.util.ArrayList;

import po.MatchPO;
import data.predo.MatchBasic;
import dataservice.MatchService;

public class GetMatchInfo implements MatchService{
	
	public ArrayList<MatchPO> getAllMatchesAtSeason(String season) {
		return new MatchBasic().matchBasic(season);
	}

	public ArrayList<MatchPO> getTodayAllMatches() {
		String season = new String();
		season = this.getLastSeason();
		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
		
		if(season.equals(""))
			return res;
		else{
			ArrayList<MatchPO> pos = this.getAllMatchesAtSeason(season);
			
			String date = new String();
			date = this.getLastData(pos);
			
			for(MatchPO po : pos)
				if(po.getDate().equals(date))
					res.add(po);
		}
		return res;
	}

	public ArrayList<MatchPO> getAllMatchesAboutPlayer(String name,
			String season) {
		ArrayList<MatchPO> pos = this.getAllMatchesAtSeason(season);
		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
		for(MatchPO po : pos){
			if(po.getTeam1().existPlayer(name) || po.getTeam2().existPlayer(name))
				res.add(po);
		}
		
		return res;
	}

	public ArrayList<MatchPO> getLastFiveMatchesAboutPlayer(String name) {
		String season = this.getLastSeason();
		ArrayList<MatchPO> pos = this.getAllMatchesAboutPlayer(name, season);
		ArrayList<MatchPO> res =new ArrayList<MatchPO>();
		
		if(pos.size() <= 5)
			return pos;
		
		//找到最近五场
		int lastedIndex = 0;
		if(pos.get(lastedIndex).getDate().compareTo("06-01") > 0){
			for(int i = 0; i < 5; i++)
				res.add(pos.get(pos.size() - 1 - i));
		}
		else{
			for(; lastedIndex < pos.size(); lastedIndex++){	
				if(pos.get(lastedIndex).getDate().compareTo("06-01") > 0)
					break;
			}
			lastedIndex--;
			
			if(lastedIndex >= 4){
				for(int i = 0; i < 5; i++){
					res.add(pos.get(lastedIndex - i));
				}				
			}
			else{
				for(int i = 0; i <= lastedIndex; i++)
					res.add(pos.get(i));
				for(int i = 0; i < 5 - res.size(); i++){
					res.add(pos.get(pos.size() - 1 - i));
				}
			}
		}
		
		return res;
	}

	public ArrayList<MatchPO> getAllMatchesAboutTeam(String abbName,
			String season) {
		if(abbName.equals("NOP") && season.compareTo("12-13") <= 0)
			abbName = "NOH";
		
		ArrayList<MatchPO> pos = this.getAllMatchesAtSeason(season);
		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
		for(MatchPO po : pos){
			if(po.getTeam1().getAbbName().equals(abbName) || po.getTeam2().getAbbName().equals(abbName))
				res.add(po);
		}
		
		return res;
	}

	public ArrayList<MatchPO> getLastFiveMatchesAboutTeam(String abbName) {
		String season = this.getLastSeason();
		if(abbName.equals("NOP") && season.compareTo("12-13") <= 0)
			abbName = "NOH";
		
		ArrayList<MatchPO> pos = this.getAllMatchesAboutTeam(abbName, season);
		ArrayList<MatchPO> res =new ArrayList<MatchPO>();
		
		if(pos.size() <= 5)
			return pos;
		
		//找到最近五场
		int lastedIndex = 0;
		if(pos.get(lastedIndex).getDate().compareTo("06-01") > 0){
			for(int i = 0; i < 5; i++)
				res.add(pos.get(pos.size() - 1 - i));
		}
		else{
			for(; lastedIndex < pos.size(); lastedIndex++){	
				if(pos.get(lastedIndex).getDate().compareTo("06-01") > 0)
					break;
			}
			lastedIndex--;
			
			if(lastedIndex >= 4){
				for(int i = 0; i < 5; i++){
					res.add(pos.get(lastedIndex - i));
				}				
			}
			else{
				for(int i = 0; i <= lastedIndex; i++)
					res.add(pos.get(i));
				for(int i = 0; i < 5 - res.size(); i++){
					res.add(pos.get(pos.size() - 1 - i));
				}
			}
		}
		
		return res;
	}

//	public ArrayList<MatchPO> getMatchesAboutTwoTeams(String abbName1,
//			String abbName2, String season) {
//		ArrayList<MatchPO> pos = this.getAllMatchesAtSeason(season);
//		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
//		
//		for(MatchPO po : pos)
//			if((po.getTeam1().getAbbName().equals(abbName1) && po.getTeam2().getAbbName().equals(abbName2)) ||
//					(po.getTeam1().getAbbName().equals(abbName2) && po.getTeam2().getAbbName().equals(abbName1)))
//				res.add(po);
//		return res;
//	}
	
	//最近赛季
	public String getLastSeason(){
		File f = new File("data\\matches");
		if(f.exists()){
			String[] seasons = f.list();
			return seasons[seasons.length - 1];
		}
		else
			return "";
	}
	
	//获得数据库中已存在的赛季列表
	public ArrayList<String> getExistedSeasons() {
		File f = new File("data\\matches");
		String[] fileNames = f.list();
		
		ArrayList<String> seasons = new ArrayList<String>();
		seasons.add(fileNames[0].split("_")[0]);
		for(int i = 1; i < fileNames.length; i++){
			for(int j = 0; j < seasons.size(); j++){
				if(seasons.get(j).equals(fileNames[i].split("_")[0]))
					break;
				else if(j == seasons.size() - 1)
					seasons.add(fileNames[i].split("_")[0]);
			}
		}
		return seasons;
	}
	
	//最近比赛日期
	public String getLastData(ArrayList<MatchPO> matches){
		String date = new String();
		
		date = matches.get(0).getDate();
		for(int i = 1; i < matches.size(); i++){
			if((date.compareTo("06-01") < 0 && matches.get(i).getDate().compareTo("06-01") < 0 && date.compareTo(matches.get(i).getDate()) < 0) ||
			   (date.compareTo("06-01") > 0 && matches.get(i).getDate().compareTo("06-01") < 0) ||
			   (date.compareTo("06-01") > 0 && matches.get(i).getDate().compareTo("06-01") > 0 && date.compareTo(matches.get(i).getDate()) < 0))
				date = matches.get(i).getDate();
		}
		return date;
	}
}

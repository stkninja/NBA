package data;

import java.io.File;
import java.util.ArrayList;

import po.MatchPO;
import data.readPOs.ReadMBasicPO;
import dataservice.MatchService;

public class GetMatchInfo implements MatchService{
	public ArrayList<MatchPO> getAllMatchesAtSeason(String season) {
		return ReadMBasicPO.readMBasicPO(season);
	}

	public ArrayList<MatchPO> getTodayAllMatches() {
		String season = new String();
		season = this.getLastSeason();
		ArrayList<MatchPO> pos = ReadMBasicPO.readMBasicPO(season);
		String date = new String();
		date = this.getLastData(pos);
		
		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
		for(MatchPO po : pos)
			if(po.getDate().equals(date))
				res.add(po);
		return res;
	}

	public ArrayList<MatchPO> getAllMatchesAboutPlayer(String name,
			String season) {
		ArrayList<MatchPO> pos = ReadMBasicPO.readMBasicPO(season);
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
		for(int i = 0; i < 5; i++){
			String date = this.getLastData(pos);
			int index;
			for(index = 0; index < pos.size(); index++)
				if(pos.get(index).getDate().equals(date))
					break;
			res.add(pos.get(index));
			pos.remove(index);
		}
		return res;
	}

	public ArrayList<MatchPO> getAllMatchesAboutTeam(String abbName,
			String season) {
		ArrayList<MatchPO> pos = ReadMBasicPO.readMBasicPO(season);
		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
		for(MatchPO po : pos){
			if(po.getTeam1().getAbbName().equals(abbName) || po.getTeam2().getAbbName().equals(abbName))
				res.add(po);
		}
		
		return res;
	}

	public ArrayList<MatchPO> getLastFiveMatchesAboutTeam(String abbName) {
		String season = this.getLastSeason();
		ArrayList<MatchPO> pos = this.getAllMatchesAboutTeam(abbName, season);
		ArrayList<MatchPO> res =new ArrayList<MatchPO>();
		
		if(pos.size() <= 5)
			return pos;
		
		//找到最近五场
		for(int i = 0; i < 5; i++){
			String date = this.getLastData(pos);
			int index;
			for(index = 0; index < pos.size(); index++)
				if(pos.get(index).getDate().equals(date))
					break;
			res.add(pos.get(index));
			pos.remove(index);
		}
		return res;
	}

	public ArrayList<MatchPO> getMatchesAboutTwoTeams(String abbName1,
			String abbName2, String season) {
		ArrayList<MatchPO> pos = ReadMBasicPO.readMBasicPO(season);
		ArrayList<MatchPO> res = new ArrayList<MatchPO>();
		
		for(MatchPO po : pos)
			if((po.getTeam1().getAbbName().equals(abbName1) && po.getTeam2().getAbbName().equals(abbName2)) ||
					(po.getTeam1().getAbbName().equals(abbName2) && po.getTeam2().getAbbName().equals(abbName1)))
				res.add(po);
		return res;
	}
	
	//最近赛季
	public String getLastSeason(){
		File f = new File("data\\统计赛季比赛数据");
		String[] seasons = f.list();
		return seasons[seasons.length - 1];
	}
	
	//获得数据库中已存在的赛季列表
	public ArrayList<String> getExistedSeasons() {
		File f = new File("data\\统计赛季比赛数据");
		String[] seasons = f.list();
		
		ArrayList<String> array = new ArrayList<String>();
		for(int i = 0; i < seasons.length; i++){
			array.add(seasons[i]);
		}
		return array;
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

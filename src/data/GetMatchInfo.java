package data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.MatchPO;
import data.readPOs.ReadMBasicPO;
import dataservice.MatchService;

public class GetMatchInfo implements MatchService{
	public ArrayList<MatchPO> getAllMatchesAtSeason(String season) {
		return ReadMBasicPO.readMBasicPO(season);
	}

	public ArrayList<MatchPO> getTodayAllMatches() {
		String season = new String();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd"); 
		String date = dateFormat.format(new Date());

		SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
		String year = yearFormat.format(new Date());
		
		//确定赛季
		if(date.compareTo("06-01") < 0)
			season = (Integer.parseInt(year) - 1) + "-" + year;
		else 
			season = year + "-" + (Integer.parseInt(year) - 1);
		
		ArrayList<MatchPO> pos = ReadMBasicPO.readMBasicPO(season);
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
		String lastSeason = this.getLastSeason();
		ArrayList<MatchPO> pos = this.getAllMatchesAboutPlayer(name, lastSeason);
		
		ArrayList<MatchPO> res =new ArrayList<MatchPO>();
		ArrayList<MatchPO> matchesBefore1_1 = new ArrayList<MatchPO>();
		ArrayList<MatchPO> matchesAfter1_1 = new ArrayList<MatchPO>();
		
		if(pos.size() <= 5)
			return pos;
		
		//找到最近五场
		//区分季前赛 常规赛 季后赛
		for(MatchPO po : pos){
			if(po.getDate().compareTo("06-01") < 0)
				matchesAfter1_1.add(po);
			else
				matchesBefore1_1.add(po);
		}
		
		if(matchesAfter1_1.size() >= 5)
			for(int i = 0; i < 5; i++){
				int index = this.getLastMatchIndex(matchesAfter1_1);
				res.add(matchesAfter1_1.get(index));
				matchesAfter1_1.remove(index);
			}
		else{
			for(int i = 0; i < 5 - matchesAfter1_1.size(); i++){
				int index = this.getLastMatchIndex(matchesBefore1_1);
				res.add(matchesBefore1_1.get(index));
				matchesBefore1_1.remove(index);
			}
			res.addAll(matchesAfter1_1);
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
		String lastSeason = this.getLastSeason();
		ArrayList<MatchPO> pos = this.getAllMatchesAboutTeam(abbName, lastSeason);
		
		ArrayList<MatchPO> res =new ArrayList<MatchPO>();
		ArrayList<MatchPO> matchesBefore1_1 = new ArrayList<MatchPO>();
		ArrayList<MatchPO> matchesAfter1_1 = new ArrayList<MatchPO>();
		
		if(pos.size() <= 5)
			return pos;
		
		//找到最近五场
		//区分季前赛 常规赛 季后赛
		for(MatchPO po : pos){
			if(po.getDate().compareTo("06-01") < 0)
				matchesAfter1_1.add(po);
			else
				matchesBefore1_1.add(po);
		}
		
		if(matchesAfter1_1.size() >= 5)
			for(int i = 0; i < 5; i++){
				int index = this.getLastMatchIndex(matchesAfter1_1);
				res.add(matchesAfter1_1.get(index));
				matchesAfter1_1.remove(index);
			}
		else{
			for(int i = 0; i < 5 - matchesAfter1_1.size(); i++){
				int index = this.getLastMatchIndex(matchesBefore1_1);
				res.add(matchesBefore1_1.get(index));
				matchesBefore1_1.remove(index);
			}
			res.addAll(matchesAfter1_1);
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
	
	//最近比赛
	private int getLastMatchIndex(ArrayList<MatchPO> pos){
		int lastIndex = 0;
		for(int i = 1; i < pos.size(); i++)
			if(pos.get(lastIndex).getDate().compareTo(pos.get(i).getDate()) < 0)
				lastIndex = i;
		return lastIndex;
	}
}

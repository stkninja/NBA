package spider.spiderTeam;

import java.util.ArrayList;

/*=====================================================*
 * 解析本地球队数据
 * 现在球队全名;现在球队缩写;曾经球队全名;现在球队全名;所在地;赛区;分区;主场;建立时间
 *=====================================================*/
public class SpiderTeam {
	static String[][] teamInfo = 
		   {{"Atlanta Hawks", "ATL", "", "", "Atlanta", "E", "Southeast", "Philips Arena", "1949"},
			{"Brooklyn Nets", "BRK", "", "", "Brooklyn", "E", "Atlantic", "Barclays Center", "1976"},
			{"Boston Celtics", "BOS", "", "", "Boston", "W", "Atlantic", "TD Garden", "1946"},	
			{"Charlotte Hornets", "CHA", "Charlotte Bobcats", "CHA", "Charlotte", "E", "Southeast", "Time Warner Cable Arena", "1988"},
			{"Chicago Bulls", "CHI", "", "", "Chicago", "E", "Central", "United Center", "1966"},
			{"Cleveland Cavaliers", "CLE", "", "", "Cleveland", "E", "Central", "Quicken Loans Arena", "1970"},
			{"Dallas Mavericks", "DAL", "", "", "Dallas", "W", "Southwest", "American Airlines Center", "1980"},
			{"Denver Nuggets", "DEN", "", "", "Denver", "W", "Northwest", "Pepsi Center", "1976"},
			{"Detroit Pistons", "DET", "", "", "Detroit", "E", "Central", "The Palace of Auburn Hills", "1948"},
			{"Golden State Warriors", "GSW", "", "", "Golden State", "W", "Pacific", "Oracle Arena", "1946"},
			{"Houston Rockets", "HOU", "", "", "Houston", "W", "Southwest", "Toyota Center", "1967"},
			{"Indiana Pacers", "IND", "", "", "Indiana", "E", "Central", "Bankers Life Fieldhouse", "1976"},
			{"Los Angeles Clippers", "LAC", "", "", "Los Angeles", "W", "Pacific	", "STAPLES Center", "1970"},
			{"Los Angeles Lakers", "LAL", "", "", "Los Angeles", "W", "Pacific", "STAPLES Center", "1948"},
			{"Memphis Grizzlies", "MEM", "Vancouver Grizzlies", "VAN", "Memphis", "W", "Southwest", "FedEx Forum", "1995"},
			{"Miami Heat", "MIA", "", "", "Miami", "E", "Southeast", "AmericanAirlines Arena", "1988"},
			{"Milwaukee Bucks", "MIL", "", "", "Milwaukee", "E", "Central", "BMO Harris Bradley Center", "1968"},
			{"Minnesota Timberwolves", "MIN", "", "", "Minnesota", "W", "Northwest", "Target Center", "1989"},
			{"New Orleans Pelicans", "NOP", "New Orleans Hornets", "NOH", "New Orleans", "W", "Southwest", "Smoothie King Arena", "2002"},
			{"New York Knicks", "NYK", "", "", "New York", "E", "Atlantic", "Madison Square Garden (IV)", "1946"},
			{"Oklahoma City Thunder", "OKC", "Seattle SuperSonics", "SEA", "Oklahoma City", "W", "Northwest", "Chesapeake Energy Arena", "1967"},
			{"Orlando Magic", "ORL", "", "", "Orlando", "E", "Southeast", "Amway Center", "1989"},
			{"Philadelphia 76ers", "PHI", "", "", "Philadelphia", "E", "Atlantic", "Wells Fargo Center", "1949"},
			{"Phoenix Suns", "PHO", "", "", "Phoenix", "W", "Pacific", "US Airways Center", "1968"},
			{"Portland Trail Blazers", "POR", "", "", "Portland", "W", "Northwest", "Moda Center", "1970"},
			{"Sacramento Kings", "SAC", "", "", "Sacramento", "W", "Pacific", "Sleep Train Arena", "1948"},
			{"San Antonio Spurs", "SAS", "", "", "San Antonio", "W", "Southwest", "AT&T Center", "1976"},
			{"Toronto Raptors", "TOR", "", "", "Toronto", "E", "Atlantic	", "Air Canada Centre", "1995"},
			{"Utah Jazz", "UTA", "", "", "Utah", "W", "Northwest", "EnergySolutions Arena", "1974"},
			{"Washington Wizards", "WAS", "", "", "Washington", "E", "Southeast", "Verizon Center", "1961"}};
	
	
	
	public static ArrayList<TeamBasicStruct> spiderTeam() {
		ArrayList<TeamBasicStruct> lists = new ArrayList<TeamBasicStruct>();
		
		for(int i = 0; i < 30; i++){
			TeamBasicStruct teamBasicStruct = new TeamBasicStruct();
			teamBasicStruct.setTempFullName(teamInfo[i][0]);
			teamBasicStruct.setTempAbbName(teamInfo[i][1]);
			teamBasicStruct.setHistoryFullName(teamInfo[i][2]);
			teamBasicStruct.setHistoryAbblName(teamInfo[i][3]);
			teamBasicStruct.setLocation(teamInfo[i][4]);
			teamBasicStruct.setCompetionArea(teamInfo[i][5]);
			teamBasicStruct.setSubArea(teamInfo[i][6]);
			teamBasicStruct.setHomeGround(teamInfo[i][7]);
			teamBasicStruct.setSetupTime(teamInfo[i][8]);
			
			lists.add(teamBasicStruct);
		}
		
		return lists;
	}
}

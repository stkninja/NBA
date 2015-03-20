package ui;

/**
 * 
 * @date 2015年3月16日
 * @author stk
 *
 */

/*
 * 球队地区
 */
public enum Region {
	ATLANTIC {
		public String[] getTeam() {
//			return new String[]{"Boston Celtics", "Brooklyn Nets", "New York Knicks", "Philadelphia 76ers", "Toronto Raptors"};
			return new String[]{"BOC", "BKN", "NYK", "PHI", "TOR"};
		}
	},
	CENTRAL {
		public String[] getTeam() {
//			return new String[]{"Chicago Bulls", "Cleveland Cavaliers", "Detroit Pistons", "Indiana Pacers", "Milwaukee Bucks"};
			return new String[]{"CHI", "CLE", "DET", "IND", "MIL"};
		}
	},
	SOUTHEAST {
		public String[] getTeam() {
//			return new String[]{"Atlanta Hawks", "Charlotte Hornets", "Miami Heat", "Orlando Magic", "Washington Wizards"};
			return new String[]{"ATL", "CHA", "MIA", "ORL", "WAS"};
		}
	},
	SOUTHWEST {
		public String[] getTeam() {
//			return new String[]{"Dallas Mavericks", "Houston Rockets", "Memphis Grizzlies", "New Orleans Pelicans", "San Antonio Spurs"};
			return new String[]{"DAL", "HOU", "MEM", "NOP", "SAS"};
		}
	},
	NORTHWEST {
		public String[] getTeam() {
//			return new String[]{"Denver Nuggets", "Minnesota Timberwolves", "Oklahoma City Thunder", "Portland Trail Blazers", "Utah Jazz"};
			return new String[]{"DEN", "MIN", "OKC", "POR", "UTA"};
		}
	},
	PACIFIC {
		public String[] getTeam() {
//			return new String[]{"Golden State Warriors", "Los Angeles Clippers", "Los Angeles Lakers", "Phoenix Suns", "Sacramento Kings"};
			return new String[]{"GSW", "LAC", "LAL", "PHX", "SAC"};
		}
	};
	//--------------------------------------
	public abstract String[] getTeam();
	
	public String[] getRegion() {
		return new String[]{"All", "Atlantic", "Central", "Southeast", "Southwest", "Northwest", "Pacific"};
	}
}

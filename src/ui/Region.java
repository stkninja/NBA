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
			return new String[]{"Boston Celtics", "Brooklyn Nets", "New York Knicks", "Philadelphia 76ers", "Toronto Raptors"};
		}
	},
	CENTRAL {
		public String[] getTeam() {
			return new String[]{"Chicago Bulls", "Cleveland Cavaliers", "Detroit Pistons", "Indiana Pacers", "Milwaukee Bucks"};
		}
	},
	SOUTHEAST {
		public String[] getTeam() {
			return new String[]{"Atlanta Hawks", "Charlotte Hornets", "Miami Heat", "Orlando Magic", "Washington Wizards"};
		}
	},
	SOUTHWEST {
		public String[] getTeam() {
			return new String[]{"Dallas Mavericks", "Houston Rockets", "Memphis Grizzlies", "New Orleans Pelicans", "San Antonio Spurs"};
		}
	},
	NORTHWEST {
		public String[] getTeam() {
			return new String[]{"Denver Nuggets", "Minnesota Timberwolves", "Oklahoma City Thunder", "Portland Trail Blazers", "Utah Jazz"};
		}
	},
	PACIFIC {
		public String[] getTeam() {
			return new String[]{"Golden State Warriors", "Los Angeles Clippers", "Los Angeles Lakers", "Phoenix Suns", "Sacramento Kings"};
		}
	};
	//--------------------------------------
	public abstract String[] getTeam();
	
	public String[] getRegion() {
		return new String[]{"ALL", "ATLANTIC", "CENTRAL", "SOUTHEAST", "SOUTHWEST", "NORTHWEST", "PACIFIC"};
	}
}

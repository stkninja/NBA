package ui;

/**
 * 球队地区枚举
 * @author stk
 *
 */
public enum Region {
	Atlantic {
		public String[] getTeam() {
//			return new String[]{"Boston Celtics", "Brooklyn Nets", "New York Knicks", "Philadelphia 76ers", "Toronto Raptors"};
			return new String[]{"BOS", "BKN", "NYK", "PHI", "TOR"};
		}
	},
	Central {
		public String[] getTeam() {
//			return new String[]{"Chicago Bulls", "Cleveland Cavaliers", "Detroit Pistons", "Indiana Pacers", "Milwaukee Bucks"};
			return new String[]{"CHI", "CLE", "DET", "IND", "MIL"};
		}
	},
	Southeast {
		public String[] getTeam() {
//			return new String[]{"Atlanta Hawks", "Charlotte Hornets", "Miami Heat", "Orlando Magic", "Washington Wizards"};
			return new String[]{"ATL", "CHA", "MIA", "ORL", "WAS"};
		}
	},
	Southwest {
		public String[] getTeam() {
//			return new String[]{"Dallas Mavericks", "Houston Rockets", "Memphis Grizzlies", "New Orleans Pelicans", "San Antonio Spurs"};
			return new String[]{"DAL", "HOU", "MEM", "NOC", "SAS"};
		}
	},
	Northwest {
		public String[] getTeam() {
//			return new String[]{"Denver Nuggets", "Minnesota Timberwolves", "Oklahoma City Thunder", "Portland Trail Blazers", "Utah Jazz"};
			return new String[]{"DEN", "MIN", "OKC", "POR", "UTA"};
		}
	},
	Pacific {
		public String[] getTeam() {
//			return new String[]{"Golden State Warriors", "Los Angeles Clippers", "Los Angeles Lakers", "Phoenix Suns", "Sacramento Kings"};
			return new String[]{"GSW", "LAC", "LAL", "PHX", "SAC"};
		}
	};
	//--------------------------------------
	/**
	 * 获得分区的球队
	 * @return 球队名
	 */
	public abstract String[] getTeam();
	/**
	 * 获得分区
	 * @return 分区名
	 */
	public static String[] getRegion() {
		return new String[]{"All", "Atlantic", "Central", "Southeast", "Southwest", "Northwest", "Pacific"};
	}
}

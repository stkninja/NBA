package ui;

/**
 * 球队枚举
 * @author stk
 *
 */
public enum TeamEnum {
	//East
	//Southeast
	ATL("ATL", "Atlanta Hawks", "亚特兰大 老鹰"),
	CHA("CHA", "Charlotte Hornets", "夏洛特 黄蜂"),
	MIA("MIA", "Miami Heat", "迈阿密 热火"),
	ORL("ORL", "Orlando Magic", "奥兰多 魔术"),
	WAS("WAS", "Washington Wizards", "华盛顿 奇才"),
	//Central
	CHI("CHI", "Chicago Bulls", "芝加哥 公牛"),
	CLE("CLE", "Cleveland Cavaliers", "克利夫兰 骑士"),
	DET("DET", "Detroit Pistons", "底特律 活塞"),
	IND("IND", "Indiana Pacers", "印第安纳 步行者"),
	MIL("MIL", "Milwaukee Bucks", "密尔沃基 雄鹿"),
	//Atlantic
	BOS("BOS", "Boston Celtics", "波士顿 凯尔特人"),
	BKN("BKN", "Brooklyn Nets", "布鲁克林 篮网"),
	NYK("NYK", "New York Knicks", "纽约 尼克斯"),
	PHI("PHI", "Philadelphia 76ers", "费城 76人"),
	TOR("TOR", "Toronto Raptors", "多伦多 猛龙"),
	//West
	//Pacific
	GSW("GSW", "Golden State Warriors", "金州 勇士"),
	LAC("LAC", "Los Angeles Clippers", "洛杉矶 快船"),
	LAL("LAL", "Los Angeles Lakers", "洛杉矶 湖人"),
	PHX("PHX", "Phoenix Suns", "菲尼克斯 太阳"),
	SAC("SAC", "Sacramento Kings", "萨克拉门托 国王"),
	//Northwest
	DEN("DEN", "Denver Nuggets", "丹佛 掘金"),
	MIN("MIN", "Minnesota Timberwolves", "明尼苏达 森林狼"),
	OKC("OKC", "Oklahoma City Thunder", "奥克拉荷马城 雷霆"),
	POR("POR", "Portland Trail Blazers", "波特兰 开拓者"),
	UTA("UTA", "Utah Jazz", "犹他 爵士"),
	//Southwest
	DAL("DAL", "Dallas Mavericks", "达拉斯 小牛"),
	HOU("HOU", "Houston Rockets", "休斯顿 火箭"),
	MEM("MEM", "Memphis Grizzlies", "孟菲斯 灰熊"),
	NOP("NOP", "New Orleans Pelicans", "新奥尔良 鹈鹕"),
	NOH("NOH", "New Orleans Hornet", "新奥尔良 黄蜂"),
	SAS("SAS", "San Antonio Spurs", "圣安东尼奥 马刺"),
	
	All("All", "All", "所有球队"),
	NONE("NONE", "", "");
	
	private String abbr;
	private String name_En;
	private String name_Ch;
	
	private TeamEnum(String abbr, String name_En, String name_Ch) {
		this.abbr = abbr;
		this.name_En = name_En;
		this.name_Ch = name_Ch;
	}
	public String abbr() { return abbr; }
	public String name_En() { return name_En; }
	public String name_Ch() { return name_Ch; }
	public static String[] getSoutheast() { return new String[]{"ATL", "CHA", "MIA", "ORL", "WAS"}; }
	public static String[] getCentral() { return new String[]{"CHI", "CLE", "DET", "IND", "MIL"}; }
	public static String[] getAtlantic() { return new String[]{"BOS", "BKN", "NYK", "PHI", "TOR"}; }
	public static String[] getPacific() { return new String[]{"GSW", "LAC", "LAL", "PHX", "SAC"}; }
	public static String[] getNorthwest() { return new String[]{"DEN", "MIN", "OKC", "POR", "UTA"}; }
	public static String[] getSouthwest() { return new String[]{"DAL", "HOU", "MEM", "NOP", "SAS"}; }
	public static String[] getRegion() { return new String[]{"All", "Southeast", "Central", "Atlantic", "Pacific", "Northwest", "Southwest"}; }
	public static String[] getTeam(String region) {
		switch (region) {
		case "Southeast" : return getSoutheast();
		case "Central" : return getCentral();
		case "Atlantic" : return getAtlantic();
		case "Pacific" : return getPacific();
		case "Northwest" : return getNorthwest();
		case "Southwest" : return getSouthwest();
		}
		return null;
	}
	public static TeamEnum valueToEnum(String value) {
		for (TeamEnum i : TeamEnum.values()) {
			if (i.abbr.equals(value) || i.name_Ch.equals(value) || i.name_En.equals(value))
				return i;
		}
		return TeamEnum.NONE;
	}
}

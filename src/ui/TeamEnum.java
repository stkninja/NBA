package ui;

/**
 * ���ö��
 * @author stk
 *
 */
public enum TeamEnum {
	//East
	//Southeast
	ATL("ATL", "Atlanta Hawks", "�������� ��ӥ"),
	CHA("CHA", "Charlotte Hornets", "������ �Ʒ�"),
	MIA("MIA", "Miami Heat", "������ �Ȼ�"),
	ORL("ORL", "Orlando Magic", "������ ħ��"),
	WAS("WAS", "Washington Wizards", "��ʢ�� ���"),
	//Central
	CHI("CHI", "Chicago Bulls", "֥�Ӹ� ��ţ"),
	CLE("CLE", "Cleveland Cavaliers", "�������� ��ʿ"),
	DET("DET", "Detroit Pistons", "������ ����"),
	IND("IND", "Indiana Pacers", "ӡ�ڰ��� ������"),
	MIL("MIL", "Milwaukee Bucks", "�ܶ��ֻ� ��¹"),
	//Atlantic
	BOS("BOS", "Boston Celtics", "��ʿ�� ��������"),
	BKN("BKN", "Brooklyn Nets", "��³���� ����"),
	NYK("NYK", "New York Knicks", "ŦԼ ���˹"),
	PHI("PHI", "Philadelphia 76ers", "�ѳ� 76��"),
	TOR("TOR", "Toronto Raptors", "���׶� ����"),
	//West
	//Pacific
	GSW("GSW", "Golden State Warriors", "���� ��ʿ"),
	LAC("LAC", "Los Angeles Clippers", "��ɼ� �촬"),
	LAL("LAL", "Los Angeles Lakers", "��ɼ� ����"),
	PHX("PHX", "Phoenix Suns", "�����˹ ̫��"),
	SAC("SAC", "Sacramento Kings", "���������� ����"),
	//Northwest
	DEN("DEN", "Denver Nuggets", "���� ���"),
	MIN("MIN", "Minnesota Timberwolves", "�����մ� ɭ����"),
	OKC("OKC", "Oklahoma City Thunder", "�¿�������� ����"),
	POR("POR", "Portland Trail Blazers", "������ ������"),
	UTA("UTA", "Utah Jazz", "���� ��ʿ"),
	//Southwest
	DAL("DAL", "Dallas Mavericks", "����˹ Сţ"),
	HOU("HOU", "Houston Rockets", "��˹�� ���"),
	MEM("MEM", "Memphis Grizzlies", "�Ϸ�˹ ����"),
	NOP("NOP", "New Orleans Pelicans", "�°¶��� ����"),
	NOH("NOH", "New Orleans Hornet", "�°¶��� �Ʒ�"),
	SAS("SAS", "San Antonio Spurs", "ʥ������� ���"),
	
	All("All", "All", "�������"),
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

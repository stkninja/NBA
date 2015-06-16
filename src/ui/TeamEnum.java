package ui;

/**
 * ���ö��
 * @author stk
 *
 */
public enum TeamEnum {
	//East
	//Southeast
	ATL("ATL", "Atlanta Hawks", "�������� ��ӥ", "��ӥ"),
	CHO("CHO", "Charlotte Hornets", "������ �Ʒ�", "�Ʒ�"),
	MIA("MIA", "Miami Heat", "������ �Ȼ�", "�Ȼ�"),
	ORL("ORL", "Orlando Magic", "������ ħ��", "ħ��"),
	WAS("WAS", "Washington Wizards", "��ʢ�� ���", "���"),
	//Central
	CHI("CHI", "Chicago Bulls", "֥�Ӹ� ��ţ", "��ţ"),
	CLE("CLE", "Cleveland Cavaliers", "�������� ��ʿ", "��ʿ"),
	DET("DET", "Detroit Pistons", "������ ����", "����"),
	IND("IND", "Indiana Pacers", "ӡ�ڰ��� ������", "������"),
	MIL("MIL", "Milwaukee Bucks", "�ܶ��ֻ� ��¹", "��¹"),
	//Atlantic
	BOS("BOS", "Boston Celtics", "��ʿ�� ��������", "��������"),
	BRK("BRK", "Brooklyn Nets", "��³���� ����", "����"),
	NYK("NYK", "New York Knicks", "ŦԼ ���˹", "���˹"),
	PHI("PHI", "Philadelphia 76ers", "�ѳ� 76��", "76��"),
	TOR("TOR", "Toronto Raptors", "���׶� ����", "����"),
	//West
	//Pacific
	GSW("GSW", "Golden State Warriors", "���� ��ʿ", "��ʿ"),
	LAC("LAC", "Los Angeles Clippers", "��ɼ� �촬", "�촬"),
	LAL("LAL", "Los Angeles Lakers", "��ɼ� ����", "����"),
	PHO("PHO", "Phoenix Suns", "�����˹ ̫��", "̫��"),
	SAC("SAC", "Sacramento Kings", "���������� ����", "����"),
	//Northwest
	DEN("DEN", "Denver Nuggets", "���� ���", "���"),
	MIN("MIN", "Minnesota Timberwolves", "�����մ� ɭ����", "ɭ����"),
	OKC("OKC", "Oklahoma City Thunder", "�¿�������� ����", "����"),
	POR("POR", "Portland Trail Blazers", "������ ������", "������"),
	UTA("UTA", "Utah Jazz", "���� ��ʿ", "��ʿ"),
	//Southwest
	DAL("DAL", "Dallas Mavericks", "����˹ Сţ", "Сţ"),
	HOU("HOU", "Houston Rockets", "��˹�� ���", "���"),
	MEM("MEM", "Memphis Grizzlies", "�Ϸ�˹ ����", "����"),
	NOP("NOP", "New Orleans Pelicans", "�°¶��� ����", "����"),
	SAS("SAS", "San Antonio Spurs", "ʥ������� ���", "���"),
	
	All("All", "All", "�������", ""),
	NONE("NONE", "", "", "");
	
	private String abbr;
	private String name_En;
	private String name_Ch;
	private String name_abbCh;
	
	private TeamEnum(String abbr, String name_En, String name_Ch, String name_abbCh) {
		this.abbr = abbr;
		this.name_En = name_En;
		this.name_Ch = name_Ch;
		this.name_abbCh = name_abbCh;
	}
	public String abbr() { return abbr; }
	public String name_En() { return name_En; }
	public String name_Ch() { return name_Ch; }
	public String name_abbCh() { return name_abbCh; }
	public static String[] getSoutheast() { return new String[]{"ATL", "CHO", "MIA", "ORL", "WAS"}; }
	public static String[] getCentral() { return new String[]{"CHI", "CLE", "DET", "IND", "MIL"}; }
	public static String[] getAtlantic() { return new String[]{"BOS", "BRK", "NYK", "PHI", "TOR"}; }
	public static String[] getPacific() { return new String[]{"GSW", "LAC", "LAL", "PHO", "SAC"}; }
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
			if (i.abbr.equals(value) || i.name_Ch.equals(value) || i.name_En.equals(value) || i.name_abbCh.equals(value))
				return i;
		}
		return TeamEnum.NONE;
	}
}

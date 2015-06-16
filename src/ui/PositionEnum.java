package ui;

/**
 * λ��ö��
 * @author stk
 *
 */
public enum PositionEnum {
	All("All", "����λ��"),
	G("G", "����"),
	C("C", "�з�"),
	F("F", "ǰ��"),
	GC("G-C", "����-�з�"),
	GF("G-F", "����-ǰ��"),
	CG("C-G", "�з�-����"),
	CF("C-F", "�з�-ǰ��"),
	FG("F-G", "ǰ��-����"),
	FC("F-C", "ǰ��-�з�"),
	NONE("", " ");
	
	private String abbr;
	private String name_Ch;
	private PositionEnum(String abbr, String name_Ch) {
		this.abbr = abbr;
		this.name_Ch = name_Ch;
	}
	public String abbr() { return abbr; }
	public String name_Ch() { return name_Ch; }
	public static PositionEnum valueToEnum(String value) {
		for (PositionEnum i : PositionEnum.values()) {
			if (i.name_Ch.equals(value) || i.abbr.equals(value))
				return i;
		}
		return null;
	}
}

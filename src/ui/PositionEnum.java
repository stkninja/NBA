package ui;

/**
 * 位置枚举
 * @author stk
 *
 */
public enum PositionEnum {
	All("All", "所有位置"),
	G("G", "后卫"),
	C("C", "中锋"),
	F("F", "前锋"),
	GC("G-C", "后卫-中锋"),
	GF("G-F", "后卫-前锋"),
	CG("C-G", "中锋-后卫"),
	CF("C-F", "中锋-前锋"),
	FG("F-G", "前锋-后卫"),
	FC("F-C", "前锋-中锋"),
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

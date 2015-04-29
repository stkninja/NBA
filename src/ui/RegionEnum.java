package ui;

/**
 * 地区枚举
 * @author stk
 *
 */
public enum RegionEnum {
	Southeast("Southeast", "东南分区"),
	Central("Central", "中央分区"),
	Atlantic("Atlantic", "大西洋分区"),
	Pacific("Pacific", "太平洋分区"),
	Northwest("Northwest", "西北分区"),
	Southwest("Southwest", "西南分区"),
	All("All", "所有分区"),
	E("E", "东区"),
	W("W", "西区");
	
	private String name_En;
	private String name_Ch;
	private RegionEnum(String name_En, String name_Ch) {
		this.name_En = name_En;
		this.name_Ch = name_Ch;
	}
	public String name_En() { return name_En; }
	public String name_Ch() { return name_Ch; }
	public static RegionEnum valueToEnum(String value) {
		for (RegionEnum i : RegionEnum.values()) {
			if (i.name_En.equals(value) || i.name_Ch.equals(value))
				return i;
		}
		return null;
	}
}

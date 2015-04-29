package ui;

/**
 * ����ö��
 * @author stk
 *
 */
public enum RegionEnum {
	Southeast("Southeast", "���Ϸ���"),
	Central("Central", "�������"),
	Atlantic("Atlantic", "���������"),
	Pacific("Pacific", "̫ƽ�����"),
	Northwest("Northwest", "��������"),
	Southwest("Southwest", "���Ϸ���"),
	All("All", "���з���"),
	E("E", "����"),
	W("W", "����");
	
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

package dataBase.initDB;

/*======================================================*
 * 创建数据库#初始化数据库数据
 *======================================================*/
public class IniDataBase {

	public static void iniDataBase() {
		//创建所需要的表格
		CreatTables.creatTables();
		//填写表格
		FillT_TEAM.fillT_TEAM();
		FillT_PLAYER.fillT_PLAYER();
		FillT_MATCH.fillT_MATCH();
	}

	public static void main(String[] args) {
		IniDataBase.iniDataBase();
	}

}

package dataBase.initDB;

/*======================================================*
 * �������ݿ�#��ʼ�����ݿ�����
 *======================================================*/
public class IniDataBase {

	public static void iniDataBase() {
		//��������Ҫ�ı��
		CreatTables.creatTables();
		//��д���
		FillT_TEAM.fillT_TEAM();
		FillT_PLAYER.fillT_PLAYER();
		FillT_MATCH.fillT_MATCH();
	}

	public static void main(String[] args) {
		IniDataBase.iniDataBase();
	}

}

package dataBase.initDB;


public class IniTeam {

	public static void main(String[] args) {
		new Thread(new FillT_TEAM()).start();
	}

}

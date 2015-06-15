package dataBase.initDB;


public class IniPlayer {

	public static void main(String[] args){
		new Thread(new FillT_PLAYER()).start();
	}

}

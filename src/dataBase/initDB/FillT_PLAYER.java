package dataBase.initDB;

import java.util.ArrayList;

import dataBase.dataBaseOpe.DataBaseOpe;
import spider.spiderPlayer.PlayerBasicStruct;
import spider.spiderPlayer.SpiderPlayer;

public class FillT_PLAYER implements Runnable{

	public static void fillT_PLAYER() {
		//����
		DataBaseOpe.createTab_UpdateSQL("DROP TABLE IF EXISTS t_player");
		DataBaseOpe.createTab_UpdateSQL("CREATE TABLE IF NOT EXISTS "
				+ "t_player(name VARCHAR(40), fromYear VARCHAR(4), toYear VARCHAR(4), position VARCHAR(3), height VARCHAR(5), weight VARCHAR(5), "
				+ "birth VARCHAR(10), college VARCHAR(80), link VARCHAR(50))");
		
		char[] firstChar = {'a', 'b', 'c', 'd', 'e', 'f',
							'g', 'h', 'i', 'j', 'k', 'l',
							'm', 'n', 'o', 'p', 'q', 'r',
							's', 't', 'u', 'v', 'w', 'y',
							'z'};
		
		//��char��ͷ��������Ա
		for(int i = 0; i < firstChar.length; i++){
			ArrayList<PlayerBasicStruct> lists = SpiderPlayer.spiderPlayer(firstChar[i]);
			
			for(PlayerBasicStruct pbs : lists){
				//�������ݿ�
				String ord = "INSERT INTO t_player VALUES('" + pbs.getName() + "', '" + pbs.getFrom() + "', '" 
						+ pbs.getTo() + "', '" + pbs.getPosition() + "', '"
						+ pbs.getHeight() + "', '" + pbs.getWeight() + "', '" + pbs.getBirth() + "', '"
						+ pbs.getCollege() + "', '" + pbs.getLink() + "')";
				DataBaseOpe.createTab_UpdateSQL(ord);
				//����̨�������ʾ��ǰ����
				System.out.println("INSERT INTO t_player(" + pbs.getName() + ", " + pbs.getCollege() + ", " + pbs.getLink() + ")");
			}
		}
	}

	public void run() {
		fillT_PLAYER();
	}
}

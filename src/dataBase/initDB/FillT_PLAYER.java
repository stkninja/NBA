package dataBase.initDB;

import java.util.ArrayList;

import dataBase.dataBaseOpe.DataBaseOpe;
import spider.spiderPlayer.PlayerBasicStruct;
import spider.spiderPlayer.SpiderPlayer;

public class FillT_PLAYER implements Runnable{

	public static void fillT_PLAYER() {
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
						+ pbs.getCollege() + "')";
				DataBaseOpe.createTab_UpdateSQL(ord);
				//����̨�������ʾ��ǰ����
				System.out.println("INSERT INTO t_player(" + pbs.getName() + ", " + pbs.getCollege() + ")");
			}
		}
	}

	public void run() {
		fillT_PLAYER();
	}
}

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
		
		
		//以char开头的所有球员
		for(int i = 0; i < firstChar.length; i++){
			ArrayList<PlayerBasicStruct> lists = SpiderPlayer.spiderPlayer(firstChar[i]);
			
			for(PlayerBasicStruct pbs : lists){
				//存入数据库
				String ord = "INSERT INTO t_player VALUES('" + pbs.getName() + "', '" + pbs.getFrom() + "', '" 
						+ pbs.getTo() + "', '" + pbs.getPosition() + "', '"
						+ pbs.getHeight() + "', '" + pbs.getWeight() + "', '" + pbs.getBirth() + "', '"
						+ pbs.getCollege() + "')";
				DataBaseOpe.createTab_UpdateSQL(ord);
				//控制台输出以提示当前操作
				System.out.println("INSERT INTO t_player(" + pbs.getName() + ", " + pbs.getCollege() + ")");
			}
		}
	}

	public void run() {
		fillT_PLAYER();
	}
}

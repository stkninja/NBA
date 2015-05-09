package data.predo;

import java.io.File;
import java.util.ArrayList;

import po.PBasicInfoPO;
import test.Console;
import data.io.ReadPlayer;

public class PlayerBasic {

	/**球员全身照地址*/
	private final static String actRootPath= new Console().getDataSource() + "\\players\\action";
	/**球员头像地址*/
	private final static String portraitRootPath= new Console().getDataSource() + "\\players\\portrait";
	/**球员信息地址*/
	private final static String infoRootPath= new Console().getDataSource() + "\\players\\info";
	
	public ArrayList<PBasicInfoPO> playerBasic() {
		ArrayList<PBasicInfoPO> players = new ArrayList<PBasicInfoPO>();
		
		/**遍历文件夹下所有文件*/
		File f = new File(infoRootPath);
		String[] list = null;
		if(f.isDirectory()){
			list = f.list();
		}
		
		if(list != null)
			players = getAllPlayers(list);
		return players;
	}
	
	private static ArrayList<PBasicInfoPO> getAllPlayers(String[] list){	
		ArrayList<PBasicInfoPO> players = new ArrayList<PBasicInfoPO>();
		for(int i = 0; i < list.length; i++){
			PBasicInfoPO player = new PBasicInfoPO();
			
			ArrayList<String> data = new ArrayList<String>(ReadPlayer.readPBasicInfo(infoRootPath + "\\" + list[i]));
			player.setAction(ReadPlayer.getPAction(actRootPath + "\\" + list[i] + ".png"));
			player.setPortrait(ReadPlayer.getPPortrait(portraitRootPath + "\\" + list[i] + ".png"));
			player.setName(data.get(0).split("║")[1].split("│")[1].trim());
			player.setPosition(data.get(2).split("║")[1].split("│")[1].trim());
			player.setAge(data.get(6).split("║")[1].split("│")[1].trim());
			player.setBirth(data.get(5).split("║")[1].split("│")[1].trim());
			player.setExp(data.get(7).split("║")[1].split("│")[1].trim());
			player.setHeight(data.get(3).split("║")[1].split("│")[1].trim());
			player.setNumber(data.get(1).split("║")[1].split("│")[1].trim());
			player.setSchool(data.get(8).split("║")[1].split("│")[1].trim());
			player.setWeight(data.get(4).split("║")[1].split("│")[1].trim());
			 
			players.add(player);
		}
		return players;
	}
}

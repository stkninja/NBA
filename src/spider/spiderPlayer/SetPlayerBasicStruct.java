package spider.spiderPlayer;

import java.util.ArrayList;

public class SetPlayerBasicStruct {

	public static ArrayList<PlayerBasicStruct> setPlayerBasicStruct(String[] eachLine) {
		String key_1 = "<td align=\"left\" ><a href=\"/players";
		String key_2 = "<td align=\"left\" ><strong><a href=\"/players";
		ArrayList<Integer> indexTab = new ArrayList<Integer>();
		//找到所有包含关键字的行的行号
		for(int i = 0; i < eachLine.length; i++){
			if(eachLine[i].indexOf(key_1) >= 0 || eachLine[i].indexOf(key_2) >= 0)
				indexTab.add(i);
		}
		
		ArrayList<PlayerBasicStruct> lists = new ArrayList<PlayerBasicStruct>();
		for(int i : indexTab){
			PlayerBasicStruct pbs = new PlayerBasicStruct();
			
			String line = eachLine[i];
			int index_1 = line.indexOf("html\">");
			int index_2 = line.indexOf("</a>");
			if(index_1 >= 0 && index_2 >= 0)
				pbs.setName(line.substring(index_1 + 6, index_2).replace('\'', '#'));
			index_1 = line.indexOf("/players");
			index_2 = line.indexOf("html");
			if(index_1 >= 0 && index_2 >= 0)
				pbs.setLink(line.substring(index_1, index_2 + 4));
			
			line = eachLine[i + 1];
			index_1 = line.indexOf(">");
			index_2 = line.indexOf("</td>");
			if(index_1 >= 0 && index_2 >= 0)
				pbs.setFrom(line.substring(index_1 + 1, index_2));
			
			line = eachLine[i + 2];
			index_1 = line.indexOf(">");
			index_2 = line.indexOf("</td>");
			if(index_1 >= 0 && index_2 >= 0)
				pbs.setTo(line.substring(index_1 + 1, index_2));
			
			line = eachLine[i + 3];
			index_1 = line.indexOf(">");
			index_2 = line.indexOf("</td>");
			if(index_1 >= 0 && index_2 >= 0)
				pbs.setPosition(line.substring(index_1 + 1, index_2));
			
			line = eachLine[i + 4];
			index_1 = line.indexOf(">");
			index_2 = line.indexOf("</td>");
			if(index_1 >= 0 && index_2 >= 0)
				pbs.setHeight(line.substring(index_1 + 1, index_2));
			
			line = eachLine[i + 5];
			index_1 = line.indexOf(">");
			index_2 = line.indexOf("</td>");
			if(index_1 >= 0 && index_2 >= 0)
				pbs.setWeight(line.substring(index_1 + 1, index_2));
			
			line = eachLine[i + 6];
			index_1 = line.indexOf("csk=\"");
			index_2 = line.indexOf("\">");
			if(index_1 >= 0 && index_2 >= 0)
				pbs.setBirth(line.substring(index_1 + 5, index_2));
			
			line = eachLine[i + 7];
			index_1 = line.indexOf("\">");
			index_2 = line.indexOf("</a>");
			if(index_1 > 0 && index_2 >= 0)
				pbs.setCollege(line.substring(index_1 + 2, index_2).replace('\'', '#'));				
			
			lists.add(pbs);
		}
		return lists;
	}
}

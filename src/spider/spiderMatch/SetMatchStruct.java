package spider.spiderMatch;

import java.util.ArrayList;

/*============================================================================*
 *该类实现对url中的比赛数据源吗解析
 *============================================================================*/
public class SetMatchStruct {

	/*============================================================================*
	 * season:13-14
	 * 调用setPlayerStruct赋值MatchStruct中的属性
	 *============================================================================*/
	public static MatchStruct setMatchStruct(String url, String season, boolean isPlayerOffs, String[] eachLine) {
		MatchStruct match = new MatchStruct();
		
		//vtFullName
		String key = "<span class=\"bold_text large_text\"><a href=";
		String vtFullName = new String();
		int i = 0;
		for(; i < eachLine.length; i++){
			if(eachLine[i].indexOf(key) >= 0){
				int index = eachLine[i].indexOf("</a>");
				vtFullName = eachLine[i].substring(66, index);
				break;
			}
		}
		i++;
		
		//htFullName
		String htFullName = new String();
		for(; i < eachLine.length; i++){
			if(eachLine[i].indexOf(key) >= 0){
				int index = eachLine[i].indexOf("</a>");
				htFullName = eachLine[i].substring(66, index);
				break;
			}
		}
		i++;
		
		//vtScores vtAbbName
		key = "<td><a href=\"/teams/";
		String vtAbbName = new String();
		String vtScores = new String();
		for(; i < eachLine.length; i++){
			if(eachLine[i].indexOf(key) >= 0){
				vtAbbName = eachLine[i].substring(20, 23);
				
				for(int j = 1;;j++){
					if(!eachLine[i + j].equals("</tr>")){
						int index_1 = eachLine[i + j].indexOf("\">");
						int index_2 = eachLine[i + j].indexOf("</td>");
						vtScores += eachLine[i + j].substring(index_1 + 2, index_2) + ";";
					}
					else
						break;
				}
				break;
			}
		}
		i++;
		
		//htAbbName htScores
		String htAbbName = new String();
		String htScores = new String();
		for(; i < eachLine.length; i++){
			if(eachLine[i].indexOf(key) >= 0){
				htAbbName = eachLine[i].substring(20, 23);
				
				for(int j = 1;;j++){
					if(!eachLine[i + j].equals("</tr>")){
						int index_1 = eachLine[i + j].indexOf("\">");
						int index_2 = eachLine[i + j].indexOf("</td>");
						htScores += eachLine[i + j].substring(index_1 + 2, index_2) + ";";
					}
					else
						break;
				}
				break;
			}
		}
		
		//id year date vtName htName, etc
		key = "/boxscores/";
		int index = url.indexOf(key);
		String id = season + "_" + url.substring(index + 15, index + 17)
				+ "-" + url.substring(index + 17, index + 19) + "_" + vtAbbName + "_" + htAbbName;
		match.setId(id);
		match.setSeason(season);
		
		if(isPlayerOffs)
			match.setIsPlayOffs("T");
		else 
			match.setIsPlayOffs("F");
		
		match.setYear(url.substring(index + 11, index + 15));
		match.setDate(url.substring(index + 15, index + 17)
				+ "-" + url.substring(index + 17, index + 19));
		
		match.setVtAbbName(vtAbbName);
		match.setHtAbbName(htAbbName);
		match.setVtFullName(vtFullName);
		match.setHtFullName(htFullName);
		
		match.setVtScores(vtScores);
		match.setHtScores(htScores);
		
		match.addAllVtPlayers(getPlayerStructList(eachLine, "vt", vtFullName));
		match.addAllHtPlayers(getPlayerStructList(eachLine, "ht", htFullName));
		
		return match;
	}
	
	/*===============================================================================*
	 * 该方法用于实现筛选整理出源码中的主队或客队的所有PlayerStruct
	 *===============================================================================*/
	private static ArrayList<PlayerStruct> getPlayerStructList(String[] eachLine, String type, String teamFullName){
		//返回值
		ArrayList<PlayerStruct> lists = new ArrayList<PlayerStruct>();
		
		//标注所有key_1 key_2位置
		String key_1 = "Starters";
		String key_2 = "<td align=\"left\"  csk=\"";
		ArrayList<Integer> indexOfKey_1 = new ArrayList<Integer>();
		ArrayList<Integer> indexOfKey_2 = new ArrayList<Integer>();
		for(int i = 0; i < eachLine.length; i++){
			if(eachLine[i].indexOf(key_1) >= 0)
				indexOfKey_1.add(i);
			if(eachLine[i].indexOf(key_2) >= 0 && eachLine[i + 1].indexOf("Did Not Play") <= 0 && eachLine[i + 1].indexOf("Player Suspended") <= 0)
				indexOfKey_2.add(i);
		}
		
		//根据type筛选有效的index
		//vt则找到第1,2个key_1,中间包含有效数据
		//ht则找到第三四个key_1,中包含有效数据
		int index_1 = 0;
		int index_2 = 0;
		if(type.equals("vt")){
			index_1 = indexOfKey_1.get(0);
			index_2 = indexOfKey_1.get(1);
		}
		else if(type.equals("ht")){
			index_1 = indexOfKey_1.get(2);
			index_2 = indexOfKey_1.get(3);
		}
		
		//遍历查找所有球员
		for(int i = 0; i < indexOfKey_2.size(); i++){
			if(indexOfKey_2.get(i) < index_2 && indexOfKey_2.get(i) > index_1){
				PlayerStruct playerStruct = SetPlayStruct.setPlayerStruct(eachLine, indexOfKey_2.get(i), teamFullName);
				lists.add(playerStruct);
			}
		}
		
		return lists;
	}
}

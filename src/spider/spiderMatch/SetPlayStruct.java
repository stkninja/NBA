package spider.spiderMatch;

public class SetPlayStruct {
	/*=================================================================*
	 * 该方法被调用以赋值PlayStruct中各个属性
	 *=================================================================*/
	public static PlayerStruct setPlayerStruct(String[] eachLine, int startIndex, String teamFullName){
		PlayerStruct playerStruct = new PlayerStruct();
		
		int index_1 = eachLine[startIndex].indexOf("html\">");
		int index_2 = eachLine[startIndex].indexOf("</a>");
		playerStruct.setName(eachLine[startIndex].substring(index_1 + 6, index_2));
		
		playerStruct.setTeam(teamFullName);
		
		index_1 = eachLine[startIndex + 1].indexOf(">");
		index_2 = eachLine[startIndex + 1].indexOf("</td>");
		playerStruct.setMinutesPlayed(eachLine[startIndex + 1].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 2].indexOf(">");
		index_2 = eachLine[startIndex + 2].indexOf("</td>");
		playerStruct.setFieldGoals(eachLine[startIndex + 2].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 3].indexOf(">");
		index_2 = eachLine[startIndex + 3].indexOf("</td>");
		playerStruct.setFieldGoalsAttempts(eachLine[startIndex + 3].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 5].indexOf(">");
		index_2 = eachLine[startIndex + 5].indexOf("</td>");
		playerStruct.setThreePoints(eachLine[startIndex + 5].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 6].indexOf(">");
		index_2 = eachLine[startIndex + 6].indexOf("</td>");
		playerStruct.setThreePointsAttempts(eachLine[startIndex + 6].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 8].indexOf(">");
		index_2 = eachLine[startIndex + 8].indexOf("</td>");
		playerStruct.setFreeThrows(eachLine[startIndex + 8].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 9].indexOf(">");
		index_2 = eachLine[startIndex + 9].indexOf("</td>");
		playerStruct.setFreeThrowsAttempts(eachLine[startIndex + 9].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 11].indexOf(">");
		index_2 = eachLine[startIndex + 11].indexOf("</td>");
		playerStruct.setOffensiveRebounds(eachLine[startIndex + 11].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 12].indexOf(">");
		index_2 = eachLine[startIndex + 12].indexOf("</td>");
		playerStruct.setDefensiveRebounds(eachLine[startIndex + 12].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 14].indexOf(">");
		index_2 = eachLine[startIndex + 14].indexOf("</td>");
		playerStruct.setAssists(eachLine[startIndex + 14].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 15].indexOf(">");
		index_2 = eachLine[startIndex + 15].indexOf("</td>");
		playerStruct.setSteals(eachLine[startIndex + 15].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 16].indexOf(">");
		index_2 = eachLine[startIndex + 16].indexOf("</td>");
		playerStruct.setBlocks(eachLine[startIndex + 16].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 17].indexOf(">");
		index_2 = eachLine[startIndex + 17].indexOf("</td>");
		playerStruct.setTurnovers(eachLine[startIndex + 17].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 18].indexOf(">");
		index_2 = eachLine[startIndex + 18].indexOf("</td>");
		playerStruct.setFouls(eachLine[startIndex + 18].substring(index_1 + 1, index_2));
		
		index_1 = eachLine[startIndex + 19].indexOf(">");
		index_2 = eachLine[startIndex + 19].indexOf("</td>");
		playerStruct.setPoints(eachLine[startIndex + 19].substring(index_1 + 1, index_2));
		
		return playerStruct;
	}
}

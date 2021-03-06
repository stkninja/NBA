package spider.spiderLive;

import java.util.ArrayList;

import spider.htmlParser.HtmlParser;

public class SpiderLive {

	public static LiveInfo spiderLive() {
		
		//不同日期比赛网页地址
		String url = "http://g.hupu.com/nba/daily/playbyplay_150123.html";
		String srcCode = HtmlParser.getHtmlContent(url, "utf-8");
		if(srcCode == null)
			return null;
					
		String[] eachLine = srcCode.split("\n");
		
		//解析
		String homeTeam = new String();
		String visitTeam = new String();
		String score = "0-0";
		String[] teamScores = new String[8];
		ArrayList<String> matchRecords = new ArrayList<String>();
		int index = 0;
		
		for(int i = 0; i < eachLine.length; i++){
			int index_1;
			int index_2;
			if((index_1 = eachLine[i].indexOf("比分")) >= 0){
				homeTeam = eachLine[i].substring(index_1 + 3, index_1 + 5);
				visitTeam = eachLine[i].substring(index_1 + 8, index_1 + 10);
			}
			else if(eachLine[i].indexOf("<td>勇士</td>") >= 0){
				for(int j = 0; j < 4; j++){
					teamScores[j] = eachLine[i + j + 1].substring(eachLine[i + j + 1].indexOf(">") + 1, eachLine[i + j + 1].indexOf("</td>"));
				}
				i += 4;
			}
			else if(eachLine[i].indexOf("<td>骑士</td>") >= 0){
				for(int j = 0; j < 4; j++){
					teamScores[j + 4] = eachLine[i + j + 1].substring(eachLine[i + j + 1].indexOf(">") + 1, eachLine[i + j + 1].indexOf("</td>"));
				}
				i += 4;
			}
			else if(eachLine[i].indexOf("<tr sid=") >= 0 && eachLine[i].indexOf("class=\"pause\">") >= 0){
				index_1 = eachLine[i + 1].indexOf("<b>");
				index_2 = eachLine[i + 1].indexOf("</b>");
				matchRecords.add(" ;"+" ;" + eachLine[i + 1].substring(index_1 + 3, index_2) + "; ");
				i++;
			}
			else if(eachLine[i].indexOf("<tr sid=") >= 0 && eachLine[i].indexOf("class=\"pause\">") < 0){
				String temp = new String();
				index_1 = eachLine[i + 1].indexOf(">");
				index_2 = eachLine[i + 1].indexOf("</td>");
				temp += eachLine[i + 1].substring(index_1 + 1, index_2) + ";";
				
				index_1 = eachLine[i + 2].indexOf(">");
				index_2 = eachLine[i + 2].indexOf("</td>");
				temp += eachLine[i + 2].substring(index_1 + 1, index_2) + ";";
				
				index_2 = eachLine[i + 4].indexOf("</td>");
				String temptemp = eachLine[i + 4].substring(0, index_2).trim();
				index_1 = temptemp.indexOf("<b>");
				index_2 = temptemp.indexOf("</b>");
				if(index_1 >= 0 || index_2 >= 0)
					temp += temptemp.substring(index_1 + 3, index_2) + ";";
				else
					temp += temptemp + ";";
				
				index_1 = eachLine[i + 5].indexOf(">");
				index_2 = eachLine[i + 5].indexOf("</td>");
				temp += eachLine[i + 5].substring(index_1 + 1, index_2);
				
				if(index == 0){
					score = eachLine[i + 5].substring(index_1 + 1, index_2);
					index++;
				}
				matchRecords.add(temp);
				i += 5;
			}
		}
		
		//返回值
		LiveInfo liveInfo = new LiveInfo();
		liveInfo.setTeamScores(teamScores);
		liveInfo.setHomeTeam(homeTeam);
		liveInfo.setVisitTeam(visitTeam);
		liveInfo.setScore(score);
		liveInfo.setMatchRecords(matchRecords);
		return liveInfo;
	}
}

package spider.spiderMatch;

import java.util.ArrayList;

import spider.htmlParser.HtmlParser;

/*=======================================================================================*
 *MatchesLinks类通过爬虫下载源码#同时分析找到该赛季所有比赛链接
 *=======================================================================================*/
public class MatchesLinks {
	
	public static ArrayList<String> matchesLinks(int year, boolean isPlayOffs) {
		String url = new String();
		String srcCode = new String();
		ArrayList<String> allLinks = new ArrayList<String>();
		
		//url的网页源码 可找到 当前赛季的所有比赛链接
		url = "http://www.basketball-reference.com/leagues/NBA_" + year + "_games.html";
		srcCode = HtmlParser.getHtmlContent(url, "utf-8");
		String[] eachLine = srcCode.split("\n");
		
		//分析源码 找到该赛季所有比赛链接
		allLinks = getAllLinks(eachLine, year, isPlayOffs);
		return allLinks;
	}
	
	/*==================================================================================*
	 *该方法对源码srcCode解析#发现其中的所有当前赛季比赛链接
	 *==================================================================================*/
	private static ArrayList<String> getAllLinks(String[] eachLine, int year, boolean isPlayOffs){
		//找到季后赛的第一行
		int indexOfPlayOffs = 0;
		String key_isPlayOffs = "div_games_playoffs";
		for(int i = 0; i < eachLine.length; i++){
			if(eachLine[i].indexOf(key_isPlayOffs) > 0){
				indexOfPlayOffs = i;
				break;
			}
		}

		//key_1 key_2 为比赛链接关键字 包含该关键字的链接为要搜索的链接
		String key_1 = "/boxscores/" + String.valueOf(year - 1);
		String key_2 = "/boxscores/" + String.valueOf(year);
		ArrayList<String> allLinks = new ArrayList<String>();
		
		int startLine = 0;
		int endLine = eachLine.length;
		if(isPlayOffs)
			startLine = indexOfPlayOffs;
		else
			endLine = indexOfPlayOffs;
		//遍历所有行寻找有效链接
		for(int i = startLine; i < endLine; i++){
			int index_1 = eachLine[i].indexOf(key_1);
			int index_2 = eachLine[i].indexOf(key_2);
			
			//改行不存在关键字#跳过
			if(index_1 < 0 && index_2 < 0)
				continue;
			
			//有效连接长均为28字符
			if(index_1 >= 0)
				allLinks.add("http://www.basketball-reference.com" + eachLine[i].substring(index_1, index_1 + 28));
			else if(index_2 >= 0)
				allLinks.add("http://www.basketball-reference.com" + eachLine[i].substring(index_2, index_2 + 28));		
		}
		
		return allLinks;
	}
}

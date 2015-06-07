package spider.spiderMatch;

import spider.htmlParser.HtmlParser;

/*====================================================================================*
 *url:某场比赛的网址
 *该类获得url比赛的源码#解析比赛源码#保存比赛信息
 *return MatchStruct
 *====================================================================================*/
public class SpiderMatch {

	public static MatchStruct spiderMatch(String url, String season, boolean isPlayOffs) {
		String srcCode = HtmlParser.getHtmlContent(url, "utf-8");
		String[] eachLine = srcCode.split("\n");
		//match为解析出来比赛数据即球员数据
		MatchStruct match = SetMatchStruct.setMatchStruct(url, season, isPlayOffs, eachLine);
		return match;
	}
	
	//测试用main函数
	public static void main(String[] args) {
		MatchStruct m = spiderMatch("http://www.basketball-reference.com/boxscores/198510250ATL.html", "85-86", true);
		System.out.println(m.getVtAbbName());
		System.out.println(m.getVtFullName());
		System.out.println(m.getHtAbbName());
		System.out.println(m.getHtFullName());
		System.out.println(m.getVtScores());
		System.out.println(m.getHtScores());
		System.out.println(m.getVtPlayers().get(5).toString());
	}
}

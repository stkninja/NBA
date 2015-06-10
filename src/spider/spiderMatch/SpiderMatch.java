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
}

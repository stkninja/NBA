package spider.spiderMatch;

import spider.htmlParser.HtmlParser;

/*====================================================================================*
 *url:ĳ����������ַ
 *������url������Դ��#��������Դ��#���������Ϣ
 *return MatchStruct
 *====================================================================================*/
public class SpiderMatch {

	public static MatchStruct spiderMatch(String url, String season, boolean isPlayOffs) {
		String srcCode = HtmlParser.getHtmlContent(url, "utf-8");
		String[] eachLine = srcCode.split("\n");
		//matchΪ���������������ݼ���Ա����
		MatchStruct match = SetMatchStruct.setMatchStruct(url, season, isPlayOffs, eachLine);
		return match;
	}
}

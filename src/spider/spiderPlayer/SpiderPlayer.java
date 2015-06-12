package spider.spiderPlayer;

import java.util.ArrayList;

import spider.htmlParser.HtmlParser;

/*=====================================================*
 * 查找名字首字母是 ch 的所有球员的基本信息
 *=====================================================*/
public class SpiderPlayer {

	public static ArrayList<PlayerBasicStruct> spiderPlayer(char ch) {
		String url = "http://www.basketball-reference.com/players/" + ch + "/";
		String srcCode = HtmlParser.getHtmlContent(url, "utf-8");
		String[] eachLine = srcCode.split("\n");
		
		ArrayList<PlayerBasicStruct> lists = SetPlayerBasicStruct.setPlayerBasicStruct(eachLine);
		return lists;
	}
}

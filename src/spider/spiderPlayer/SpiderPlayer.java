package spider.spiderPlayer;

import java.util.ArrayList;

import spider.htmlParser.HtmlParser;

/*=====================================================*
 * ������������ĸ�� ch ��������Ա�Ļ�����Ϣ
 *=====================================================*/
public class SpiderPlayer {

	public static ArrayList<PlayerBasicStruct> spiderPlayer(char ch) {
		String url = "http://www.basketball-reference.com/players/" + ch + "/";
		String srcCode = HtmlParser.getHtmlContent(url, "utf-8");
		String[] eachLine = srcCode.split("\n");
		
		ArrayList<PlayerBasicStruct> lists = SetPlayerBasicStruct.setPlayerBasicStruct(eachLine);
		return lists;
	}
	
	public static void main(String[] args) {
		ArrayList<PlayerBasicStruct> lists = spiderPlayer('z');
		System.out.println(lists.get(15).toString());
		System.out.println(lists.size());
	}
}

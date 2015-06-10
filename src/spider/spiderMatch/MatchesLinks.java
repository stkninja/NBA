package spider.spiderMatch;

import java.util.ArrayList;

import spider.htmlParser.HtmlParser;

/*=======================================================================================*
 *MatchesLinks��ͨ����������Դ��#ͬʱ�����ҵ����������б�������
 *=======================================================================================*/
public class MatchesLinks {
	
	public static ArrayList<String> matchesLinks(int year, boolean isPlayOffs) {
		String url = new String();
		String srcCode = new String();
		ArrayList<String> allLinks = new ArrayList<String>();
		
		//url����ҳԴ�� ���ҵ� ��ǰ���������б�������
		url = "http://www.basketball-reference.com/leagues/NBA_" + year + "_games.html";
		srcCode = HtmlParser.getHtmlContent(url, "utf-8");
		String[] eachLine = srcCode.split("\n");
		
		//����Դ�� �ҵ����������б�������
		allLinks = getAllLinks(eachLine, year, isPlayOffs);
		return allLinks;
	}
	
	/*==================================================================================*
	 *�÷�����Դ��srcCode����#�������е����е�ǰ������������
	 *==================================================================================*/
	private static ArrayList<String> getAllLinks(String[] eachLine, int year, boolean isPlayOffs){
		//�ҵ��������ĵ�һ��
		int indexOfPlayOffs = 0;
		String key_isPlayOffs = "div_games_playoffs";
		for(int i = 0; i < eachLine.length; i++){
			if(eachLine[i].indexOf(key_isPlayOffs) > 0){
				indexOfPlayOffs = i;
				break;
			}
		}

		//key_1 key_2 Ϊ�������ӹؼ��� �����ùؼ��ֵ�����ΪҪ����������
		String key_1 = "/boxscores/" + String.valueOf(year - 1);
		String key_2 = "/boxscores/" + String.valueOf(year);
		ArrayList<String> allLinks = new ArrayList<String>();
		
		int startLine = 0;
		int endLine = eachLine.length;
		if(isPlayOffs)
			startLine = indexOfPlayOffs;
		else
			endLine = indexOfPlayOffs;
		//����������Ѱ����Ч����
		for(int i = startLine; i < endLine; i++){
			int index_1 = eachLine[i].indexOf(key_1);
			int index_2 = eachLine[i].indexOf(key_2);
			
			//���в����ڹؼ���#����
			if(index_1 < 0 && index_2 < 0)
				continue;
			
			//��Ч���ӳ���Ϊ28�ַ�
			if(index_1 >= 0)
				allLinks.add("http://www.basketball-reference.com" + eachLine[i].substring(index_1, index_1 + 28));
			else if(index_2 >= 0)
				allLinks.add("http://www.basketball-reference.com" + eachLine[i].substring(index_2, index_2 + 28));		
		}
		
		return allLinks;
	}
}

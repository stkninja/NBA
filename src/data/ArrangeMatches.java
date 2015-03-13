package data;

import java.io.File;
import java.util.ArrayList;

import po.MatchPO;
import po.MatchTeamDataPO;

public class ArrangeMatches {
	
	/**������Ŀ¼*/
	private static final String rootDirectory = "D:\\��Ժ�γ�\\��III\\����\\CSEIII data\\����һ����\\matches"; 
	/**to return*/
	private static ArrayList<MatchPO> matchesInfo = new ArrayList<MatchPO>();
	
	public static ArrayList<MatchPO> arrangeMatches() {
		
		/**�����ļ����������ļ�*/
		File f = new File(rootDirectory);
		String[] list = null;
		if(f.isDirectory()){
			list = f.list();
		}
		
		/**������Ϣͳ��*/
		if(list != null)
			initMatchesInfo(list);
		
		return matchesInfo;
	}

	private static void initMatchesInfo(String[] list) {
		for(int i = 0; i < list.length; i++){
			/**��i���ļ��ľ���·��*/
			String absolutePath = rootDirectory + "\\" + list[i];
			
			/**�����ļ�*/
			MatchPO matchPO = new MatchPO();
			matchPO.setSeason(list[i].split("_")[0]);
			matchPO.setDate(list[i].split("_")[1]);
			
			matchPO.setTeam1(getTeams(absolutePath, list[i].split("_")[2].split("-")[0]));
			matchPO.setTeam1(getTeams(absolutePath, list[i].split("_")[2].split("-")[1]));
			 
			matchesInfo.add(matchPO);
		}
	}
	
	/**���ļ��л����ӵı�����Ϣ*/
	private static MatchTeamDataPO getTeams(String absolutePath, String name) {
		MatchTeamDataPO teamDataPO = new MatchTeamDataPO();
		
		
		
		return teamDataPO;
	}
}

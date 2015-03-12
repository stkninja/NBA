package data;

import java.io.File;
import java.util.ArrayList;

import po.MatchPO;

public class ArrangeMatches {
	
	/**比赛根目录*/
	private static final String rootDirectory = "D:\\软院课程\\软工III\\数据\\CSEIII data\\迭代一数据\\matches"; 
	/**to return*/
	private static ArrayList<MatchPO> matchesInfo = new ArrayList<MatchPO>();
	
	public static ArrayList<MatchPO> arrangeMatches() {
		
		/**遍历文件夹下所有文件*/
		File f = new File(rootDirectory);
		String[] list = null;
		if(f.isDirectory()){
			list = f.list();
		}
		
		/**初始化比赛信息*/
		if(list != null)
			initMatchesInfo(list);
		
		return matchesInfo;
	}

	private static void initMatchesInfo(String[] list) {
		for(int i = 0; i < list.length; i++){
			/**第i个文件的绝对路径*/
			String absolutePath = rootDirectory + "\\" + list[i];
			
			/**解析文件*/
			MatchPO matchPO = new MatchPO();
			matchPO.setSeason(list[i].split("_")[0]);
			matchPO.setDate(list[i].split("_")[1]);
			
			matchesInfo.add(matchPO);
		}
	}
}

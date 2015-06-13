package dataservice;

import java.util.ArrayList;

import po.MatchPO;
import spider.spiderLive.LiveInfo;

public interface MatchService {
	public ArrayList<MatchPO> getAllMatchesAtSeason(String season);
	//��Ա
	public ArrayList<MatchPO> getAllMatchesAboutPlayer(String name, String season);
	public ArrayList<MatchPO> getLastFiveMatchesAboutPlayer(String name);
	//���
	public ArrayList<MatchPO> getAllMatchesAboutTeam(String abbName, String season);
	public ArrayList<MatchPO> getLastFiveMatchesAboutTeam(String abbName);
	//��ý�������б���
	public ArrayList<MatchPO> getTodayAllMatches();
	//����������
	public String getLastSeason();
	//�����������
	public ArrayList<String> getExistedSeasons();
	//ֱ���ӿ�
	public LiveInfo getLiveInfo();
}

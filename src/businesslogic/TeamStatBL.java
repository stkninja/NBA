package businesslogic;

import java.util.ArrayList;
import java.util.Random;

import po.MatchPO;
import po.MatchTeamDataPO;
import po.TSeasonDataPO;
import vo.TeamSeasonVO;
import data.GetMatchInfo;
import data.GetTeamInfo;
import dataservice.MatchService;
import dataservice.TeamService;

public class TeamStatBL implements businesslogicservice.TeamStatBLService{
	private TeamService teamdata = null;
	private MatchService matchdata = null;
	
	public TeamStatBL(){
		teamdata = new GetTeamInfo();
		matchdata = new GetMatchInfo();
	}
	
	public TeamSeasonVO getAllTeamData(String season){
		TeamSeasonVO vo = new TeamSeasonVO();
		double pointsum = 0,reboundsum = 0,assistsum = 0,blocksum = 0,shootsum = 0,threepointsum = 0,attackroundsum = 0,attackEffiency = 0,defenceEffiencysum = 0,gameplaysum = 0,
				sum1 = 0,sum2 = 0,sum3 = 0,threepointsquare = 0,varthreepoint = 0;
		
		TSeasonDataPO po = teamdata.getOneTSeasonDataPO("GSW", season);
		
		for(MatchPO match : matchdata.getAllMatchesAboutTeam("GSW", season)){
			MatchTeamDataPO po1 = match.getTeam1();
			MatchTeamDataPO po2 = match.getTeam2();
			if(po1.getAbbName().equals("GSW")){
				double rate = po1.getThreePointHits() / po1.getThreePoint();
				threepointsquare += rate * rate;
			}
			else{
				double rate = po2.getThreePointHits() / po1.getThreePoint();
				threepointsquare += rate * rate;
			}
		}
		vo.threepointrate = po.getAllthreePointHitRate();
		vo.varthreepoint = threepointsquare / po.getGamesNum() - po.getAllthreePointHitRate() * po.getAllthreePointHitRate();
		vo.avgthreepoint = po.getAllthreePoint();
		vo.avgthreepointmade = po.getThreePointHits() * 3;
		vo.avgtwopoint = (po.getShootingHit() - po.getThreePointHits()) * 2;
		vo.avgfreethrowpoint = po.getFreeThrowHit();
		
		vo.avgpoint = po.getScores();
		vo.avgrebound = po.getRebounds();
		vo.avgassist = po.getAssists();
		vo.avgblock = po.getCaps();
		vo.avgshoot = po.getShooting();
		vo.avgthreepoint = po.getThreePoint();
		vo.attackefficiency = po.getAttackEfficiency();
		vo.avgthreepointOfpoint = po.getAllthreePoint() / po.getAllshooting();
//?		vo.defenceefficiency = getDouble();
/*		for(TSeasonDataPO po : teamdata.getAllTSeasonData(season)){
			sum1 += (po.getScores() - vo.avgpoint) * (po.getScores() - vo.avgpoint);
			sum2 += (po.getRebounds() - vo.avgrebound) * (po.getRebounds() - vo.avgrebound);
			sum3 += (po.getAssists() - vo.avgassist) * (po.getAssists() - vo.avgassist);
		}
		
		vo.varpoint = getDouble(sum1 / 30);
		vo.varrebound = getDouble(sum2 / 30);
		vo.varassist = getDouble(sum3 / 30);*/
		
		vo.season = season;
		
		return vo;
	}
	
/*	public ArrayList<Double> getThreepointAndShoot(){
		ArrayList<Double> list = new ArrayList<Double>();
		double threepointsum = 0,shootsum = 0,threepointsquare = 0,threepointshoot = 0;
		double lxy = 0,lxx = 0;
		double a = 0,b = 0;
		for(TeamSeasonVO vo : getAllSeasonTeamData()){
			threepointsum += vo.avgthreepoint;
			shootsum += vo.avgshoot;
			threepointsquare += vo.avgthreepoint * vo.avgthreepoint;
			threepointshoot += vo.avgthreepoint * vo.avgshoot;
		}
		
		lxy = threepointshoot - threepointsum * shootsum / 6;
		lxx = threepointsquare - threepointsum * threepointsum / 6;
		
		b = getDouble(lxy / lxx);
		a = getDouble(shootsum / 6 - lxy * threepointsum / (6 * lxx));
		
		double SR = 0,ST = 0,Se = 0,r2 = 0,Ve = 0,Sy = 0;
		for(TeamSeasonVO vo : getAllSeasonTeamData()){
			SR += (a + b * vo.avgthreepoint - shootsum / 6) * (a + b * vo.avgthreepoint - shootsum / 6);
			ST += (vo.avgshoot - shootsum / 6) * (vo.avgshoot - shootsum / 6);
			Se += (vo.avgshoot - a - b * vo.avgthreepoint) * (vo.avgshoot - a - b * vo.avgthreepoint);
		}
		System.out.println(SR);
		System.out.println(ST);
		r2 = getDouble(SR / ST);
		Sy = getDouble(Math.sqrt(Se / 4));
		
		list.add(a);
		list.add(b);
		list.add(r2);
		list.add(Sy);
		
		return list;
	}*/
	
	public ArrayList<Double> getYearThreepoint(){
		ArrayList<Double> list = new ArrayList<Double>();
		double threepointsum = 0,yearsum = 0,yearsquare = 0,yearThreepoint = 0,year = 2009;
		double lxy = 0,lxx = 0;
		double a = 0,b = 0;
		for(TeamSeasonVO vo : getAllSeasonTeamData()){
			threepointsum += vo.avgthreepoint;
			yearThreepoint += vo.avgthreepoint * year;
			year ++;
		}
		yearsum = 2009 + 2010 + 2011 + 2012 + 2013 + 2014;
		yearsquare = 2009 * 2009 + 2010 * 2010 + 2011 * 2011 + 2012 * 2012 + 2013 * 2013 + 2014 * 2014;
		
		lxy = yearThreepoint - threepointsum * yearsum / 6;
		lxx = yearsquare - yearsum * yearsum / 6;
		
		b = getDouble(lxy / lxx);
		a = getDouble(threepointsum / 6 - lxy * yearsum / (6 * lxx));
		
		double SR = 0,ST = 0,Se = 0,r2 = 0,Ve = 0,Sy = 0;
		year = 2009;
		for(TeamSeasonVO vo : getAllSeasonTeamData()){
			SR += (a + b * year - threepointsum / 6) * (a + b * year - threepointsum / 6);
			ST += (vo.avgthreepoint - threepointsum / 6) * (vo.avgthreepoint - threepointsum / 6);
			Se += (vo.avgthreepoint - a - b * year) * (vo.avgthreepoint - a - b * year);
			year ++;
		}
		System.out.println(SR);
		System.out.println(ST);
		r2 = getDouble(SR / ST);
		Sy = getDouble(Math.sqrt(Se / 4));
		
		list.add(a);
		list.add(b);
		list.add(r2);
		list.add(Sy);
		
		return list;
	}
	
	public ArrayList<Double> getThreepointPerYear(String season){
		ArrayList<Double> list = new ArrayList<Double>();
		for(MatchPO po : matchdata.getAllMatchesAboutTeam("GSW", season)){
			if(po.getTeam1().getAbbName().equals("GSW")){
				list.add(po.getTeam1().getThreePoint());
			}
			else{
				list.add(po.getTeam2().getThreePoint());
			}
		}
		
		return list;
	}
	
	public ArrayList<Double> getEfficiencyAndWinRate(String season){
		ArrayList<Double> list = new ArrayList<Double>();
		double attackEfficiencysum = 0,defenceEfficiencysum = 0,attackEfficiencysquare = 0,defenceEfficiencysquare = 0,winratesum = 0,winratesquare = 0,
				attackWin = 0,defenceWin = 0;
		for(TSeasonDataPO po : teamdata.getAllTSeasonData(season)){
			attackEfficiencysum += po.getAllattackEfficiency();
			defenceEfficiencysum += po.getAlldefenceEfficiency();
			attackEfficiencysquare += po.getAllattackEfficiency() * po.getAllattackEfficiency();
			defenceEfficiencysquare += po.getAlldefenceEfficiency() * po.getAlldefenceEfficiency();
			winratesum += po.getWinsRate();
			winratesquare += po.getWinsRate() * po.getWinsRate();
			attackWin += po.getAllattackEfficiency() * po.getWinsRate();
			defenceWin += po.getAlldefenceEfficiency() * po.getWinsRate();
		}
		
		double attackrxy = (10 * attackWin - attackEfficiencysum * winratesum) / (Math.sqrt((10 * attackEfficiencysquare - attackEfficiencysum * attackEfficiencysum) * (10 * winratesquare - winratesum * winratesum)));
		double defencerxy = (10 * defenceWin - defenceEfficiencysum * winratesum) / (Math.sqrt((10 * defenceEfficiencysquare - defenceEfficiencysum * defenceEfficiencysum) * (10 * winratesquare - winratesum * winratesum)));
		
		list.add(attackrxy);
		list.add(defencerxy);
		
		return list;
	}
	
	public ArrayList<Double> getAllSeasonRxy(){
		ArrayList<Double> list = new ArrayList<Double>();
		for(String season : getAllSeason()){
			list.addAll(getEfficiencyAndWinRate(season));
		}
		return list;
	}
	
	public ArrayList<TeamSeasonVO> getAllSeasonTeamData(){
		ArrayList<TeamSeasonVO> list = new ArrayList<TeamSeasonVO>();
		for(String season : getGSWSeason()){
			list.add(getAllTeamData(season));
		}
		return list;
	}
	
	public double[] getChampionScore(){
		double[] list = new double[82];
		int i = 0;
		for(MatchPO po : matchdata.getAllMatchesAboutTeam("GSW", "14-15")){
			if(po.getIsPlayOffs().equals("F")){
				if(po.getTeam1().getAbbName().equals("GSW")){
					list[i] = po.getTeam1().getScores();
					i ++;
				}
				else{
					list[i] = po.getTeam2().getScores();
					i ++;
				}
			}
		}
		return list;
	}
	
	public double[] getRandomScore(double n){
		double[] list = new double[82];
		double[] temp = getChampionScore();
		for(int i = 0;i < n;i ++){
			list[i] = temp[new Random().nextInt(82)];
		}
		return list;
	}
	
	public double[] getNormal(double n){
		double[] list = new double[25];
		double f = 0,e2 = 0,e = 0,sum = 0,square = 0;
		double[] temp = getRandomScore(n);
		for(double d :temp){
			sum += d;
			square += d * d;
		}
		f = getDouble(sum / n);
		e2 = getDouble(square / n - f * f);
		e = getDouble(Math.sqrt(e2));	
		double[] a = new double[6];
		double[] p = new double[6];
		double[] np = new double[6];
		double[] n2np = new double[6];
		for(double d : temp){
			 if(95 < d && d <= 100){
				 a[0] ++;
			 }
			 else if(100 < d && d <= 105){
				 a[1] ++;
			 }
			 else if(105 < d && d <= 110){
				 a[2] ++;
			 }
			 else if(110 < d && d <= 125){
				 a[3] ++;
			 }
			 else if(125 < d && d <= 135){
				 a[4] ++;
			 }
		 }
		 a[5] = n;
		 
		 p[0] = fai((100 - f) / e);
		 p[1] = fai((105 - f) / e) - p[0];
		 p[2] = fai((110 - f) / e) - p[1] - p[0];
		 p[3] = fai((125 - f) / e) - p[2] - p[1] - p[0];
		 p[4] = fai((135 - f) / e) - p[3] - p[2] - p[1] - p[0];
		 p[5] = 1;
		 
		 for(int i = 0;i < 5;i ++){
			 np[i] = n * p[i];
			 n2np[i] = a[i] * a[i] / np[i];
		 }
		 
		 double X2= 0,sum1 = 0;
		 for(double d : n2np){
			 sum1 += d;
		 }
		 n2np[5] = sum1;
		 X2 = sum1 - n;
		 
		 for(int i = 0;i < 6;i ++){
			 list[i] = a[i];
			 list[i + 6] = p[i];
			 list[i + 12] = np[i];
			 list[i + 18] = n2np[i];
		 }
		 list[24] = X2;
	     return list;
	}
	
	public ArrayList<MatchPO> getChampionThreepointshoot(){
		ArrayList<MatchPO> list = new ArrayList<MatchPO>();
		int i = 0;
		for(MatchPO po : matchdata.getAllMatchesAboutTeam("GSW", "14-15")){
			if(po.getIsPlayOffs().equals("F")){
				list.add(po);
			}
		}
		return list;
	}
	
	public double[] getRandomThreepointShoot(double n){
		double[] res = new double[2 * 82];
		int[] list = new int[82];
		int[] count = new int[82];
		for(int i = 0;i < 82;i ++){
			count[i] = i;
		}
		Random r = new Random();
		for(int j = 0;j < n;j ++){
			int m = r.nextInt(82 - j);
			list[j] = count[m];
			for(int k = m;k < 82 - j - 1;k ++){
				count[k] = count[k + 1];
			}
		}
		
		ArrayList<MatchPO> temp = getChampionThreepointshoot();
		for(int k = 0;k < n;k ++){
			MatchTeamDataPO po1 = temp.get(list[k]).getTeam1();
			MatchTeamDataPO po2 = temp.get(list[k]).getTeam2();
			if(po1.getAbbName().equals("GSW")){
				res[k * 2] = po1.getThreePoint();
				res[k * 2 + 1] = po1.getShooting();
			}
			else{
				res[k * 2] = po2.getThreePoint();
				res[k * 2 + 1] = po2.getShooting();
			}
		}
		
		return res;
	}
	
	public double getRxy(double n){
		double threepointysum = 0,shootsum = 0,threepointsquare = 0,shootsquare = 0,
				threepointshoot = 0;
		double[] temp = getRandomThreepointShoot(n);
		for(int i = 0;i < n;i ++){
			threepointysum += temp[i * 2];
			shootsum += temp[i * 2 + 1];
			threepointsquare += temp[i] * temp[i];
			shootsquare += temp[i * 2 + 1] * temp[i * 2 + 1];
			threepointshoot += temp[i * 2] * temp[i * 2 + 1];
		}
		
		double rxy = getDouble((n * threepointshoot - threepointysum * shootsum) / (Math.sqrt((n * threepointsquare - threepointysum * threepointysum) * (n * shootsquare - shootsum * shootsum))));
		
		return rxy;
	}
	
	public ArrayList<Double> getScores(int start,int end){
		ArrayList<Double> list = new ArrayList<Double>();
		for(String season : getSeasons(start,end)){
			list.add(teamdata.getOneTSeasonDataPO("GSW", season).getScores());	
		}
	    return list;
	}
	
	public ArrayList<String> getSeasons(int start,int end){
		ArrayList<String> list = new ArrayList<String>();
		for(int i = start;i <= end;i ++){
			list.add(intToString(i));
		}
		return list;
	}
	
	public String intToString(int year){
		String season = "";
		switch (year){
		case 2005:
			season = "05-06";
			break;
		case 2006:
			season = "06-07";
			break;
		case 2007:
			season = "07-08";
			break;
		case 2008:
			season = "08-09";
			break;
		case 2009:
			season = "09-10";
			break;
		case 2010:
			season = "10-11";
			break;
		case 2011:
			season = "11-12";
			break;
		case 2012:
			season = "12-13";
			break;	
		case 2013:
			season = "13-14";
			break;
		case 2014:
			season = "14-15";
			break;
		}
		
		return season;
	}
	
	public ArrayList<String> getGSWSeason(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("09-10");
		list.add("10-11");
		list.add("11-12");
		list.add("12-13");
		list.add("13-14");
		list.add("14-15");
		return list;
	}
	
	public ArrayList<String> getAllSeason(){
		ArrayList<String> list = new ArrayList<String>();
		int i = 2005,j = 2006;
		String season = "";
		while(i <= 2014){
			if(j < 2010){
				season = "0" + String.valueOf(i - 2000) + "-" + "0" + String.valueOf(j - 2000);
			}
			else if(i == 2009 && j == 2010){
				season = "09-10";
			}
			else if(i >= 2010){
				season = String.valueOf(i - 2000) + "-" + String.valueOf(j - 2000);
			}
			list.add(season);
			i ++;
			j ++;
		}
		return list;
	}
	
	public double getDouble(double d){
		return new  java.math.BigDecimal(Double.toString(d)).setScale(3,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public double getDouble2(double d){
		return new  java.math.BigDecimal(Double.toString(d)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double y(double x) {  
		return 1 / Math.sqrt(2 * Math.PI) * Math.pow(Math.E, -x * x / 2); 
	} 
	
	public static double fai(double x) {  
		if (x < -3.9) {   
			return 0;  
		}
        else if (x > 3.9) {   
        	return 1;  
        }  
		double f = 0;  
		double pc = -5;  
		double step = 0.00001;  
		for (double i = pc; i < x; i += step)  
			f += y(i) * step;  
		return f;
	}
	
	public static void main(String[] args){
		TeamStatBL bl = new TeamStatBL();
//		System.out.println(bl.getAllTeamData("10-11").avgpoint);
//		System.out.println(bl.getAllSeasonTeamData().size());
//		System.out.println("0" + String.valueOf(2003 - 2000));
/*		double d = 0;
		for(TSeasonDataPO po : bl.teamdata.getAllTSeasonData("13-14")){
			d += po.getAllassists();
		}*/
		
/*    	for(double d : bl.getEfficiencyAndWinRate("13-14")){
			System.out.println(d);
		}*/
//		System.out.println(bl.getAllTeamData("11-12").avgshoot);
/*    	for(TSeasonDataPO po : bl.teamdata.getAllTSeasonData("14-15")){
    		System.out.println(po.getWinsRate());
    	}*/
//		System.out.println(bl.matchdata.getAllMatchesAboutTeam("GSW", "14-15").size());
/*		int[] list = new int[82];
		int[] count = new int[82];
		for(int i = 0;i < 82;i ++){
			count[i] = i;
		}
		Random r = new Random();
		for(int j = 0;j < 10;j ++){
			int m = r.nextInt(82 - j);
			list[j] = count[m];
			System.out.println(list[j]);
			for(int k = m;k < 82 - j - 1;k ++){
				count[k] = count[k + 1];
			}
		}*/
		
/*		for(int i = 0;i < 2;i ++){
			System.out.println(list[new Random().nextInt(4)]);
		}*/
		
/*		System.out.println(bl.getNormal(20)[12]);
		System.out.println(bl.getNormal(20)[13]);*/
//		System.out.println(bl.getRxy(30));
//		System.out.println(bl.teamdata.getOneTSeasonDataPO("GSW", "14-15").getAllshootingHit());
/*		for(double d : bl.getYearThreepoint()){
			System.out.println(d);
		}*/
	}
}

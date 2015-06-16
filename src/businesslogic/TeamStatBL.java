package businesslogic;

import java.util.ArrayList;
import java.util.Random;

import po.MatchPO;
import po.MatchTeamDataPO;
import po.TSeasonDataPO;
import vo.TeamSeasonVO;
import data.GetMatchInfo;
import data.GetTeamInfo;
import data.pre.Predo;
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
		double threepointsquare = 0;
		
		TSeasonDataPO po = teamdata.getOneTSeasonDataPO("GSW", season);
		
		double rate = 0,ratesum = 0;
		for(MatchPO match : matchdata.getAllMatchesAboutTeam("GSW", season)){
			MatchTeamDataPO po1 = match.getTeam1();
			MatchTeamDataPO po2 = match.getTeam2();
			if(po1.getAbbName().equals("GSW")){
				rate = po1.getThreePointHits() / po1.getThreePoint();
				threepointsquare += rate * rate;
				ratesum += rate;
			}
			else{
				rate = po2.getThreePointHits() / po2.getThreePoint();
				threepointsquare += rate * rate;
				ratesum += rate;
			}
		}
		vo.avgthreepointHit = po.getThreePointHits();
		vo.threepointrate = getDouble3(po.getAllthreePointHits() / po.getAllthreePoint());
		vo.varthreepoint = threepointsquare / po.getGamesNum() - ratesum  * ratesum / (po.getGamesNum() * po.getGamesNum());
		vo.avgthreepoint = po.getThreePoint();
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
		
		vo.season = season;
		
		return vo;
	}
	
	public double getLeagueThreepoint(String season){
		double threepointsum = 0,gameplaysum = 0;
		for(TSeasonDataPO po : teamdata.getAllTSeasonData(season)){
			gameplaysum += po.getGamesNum();
			threepointsum += po.getAllthreePoint();
		}
		
		return getDouble2(threepointsum / gameplaysum);
	}
	
	public TeamSeasonVO getBefore(){
		TeamSeasonVO vo = new TeamSeasonVO();
		ArrayList<String> list = new ArrayList<String>();
		list.add("05-06");
		list.add("06-07");
		list.add("07-08");
		list.add("08-09");
		double threepointHitsum = 0,threepointsum = 0,shootsum = 0;
		for(String season : list){
			TeamSeasonVO vo1 = getAllTeamData(season);
			threepointHitsum += vo1.avgthreepointHit;
			threepointsum += vo1.avgthreepoint;
			shootsum += vo1.avgshoot;
		}
		vo.avgthreepointHit = getDouble2(threepointHitsum / 4);
		vo.avgthreepoint = getDouble2(threepointsum / 4);
		vo.avgthreepointrate = getDouble2(threepointHitsum / threepointsum);
		vo.avgshoot = getDouble2(shootsum / 4);
		
		return vo;
	}
	
	public ArrayList<Double> getYearThreepointOfShoot(){
		ArrayList<Double> list = new ArrayList<Double>();
		double threepointOfShootsum = 0,yearsum = 0,yearsquare = 0,yearThreepointOfShoot = 0,year = 2009;
		double lxy = 0,lxx = 0;
		double a = 0,b = 0;
		for(TeamSeasonVO vo : getAllSeasonTeamData()){
			threepointOfShootsum += vo.avgthreepointOfpoint;
			yearThreepointOfShoot += vo.avgthreepointOfpoint * year;
			year ++;
		}
		yearsum = 2009 + 2010 + 2011 + 2012 + 2013 + 2014;
		yearsquare = 2009 * 2009 + 2010 * 2010 + 2011 * 2011 + 2012 * 2012 + 2013 * 2013 + 2014 * 2014;
		
		lxy = yearThreepointOfShoot - threepointOfShootsum * yearsum / 6;
		lxx = yearsquare - yearsum * yearsum / 6;
		
		b = getDouble(lxy / lxx);
		a = getDouble(threepointOfShootsum / 6 - lxy * yearsum / (6 * lxx));
		
		double SR = 0,ST = 0,Se = 0,r2 = 0,Sy = 0;
		year = 2009;
		for(TeamSeasonVO vo : getAllSeasonTeamData()){
			SR += (a + b * year - threepointOfShootsum / 6) * (a + b * year - threepointOfShootsum / 6);
			ST += (vo.avgthreepointOfpoint - threepointOfShootsum / 6) * (vo.avgthreepointOfpoint - threepointOfShootsum / 6);
			Se += (vo.avgthreepointOfpoint - a - b * year) * (vo.avgthreepointOfpoint - a - b * year);
			year ++;
		}
		r2 = getDouble(SR / ST);
		Sy = getDouble(Math.sqrt(Se / 4));
		
		list.add(a);
		list.add(b);
		list.add(r2);
		list.add(Sy);
		
		return list;
	}
	
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
	
    public double[] getNormal(int start,int end){
    	double[] list = new double[14];
    	ArrayList<String> temp = new ArrayList<String>();
    	temp = getSeasons(start,end);
    	double A1 = 0,A2 = 0,A3 = 0,A4 = 0,B2 = 0,B3 = 0,B4 = 0,g1 = 0,g2 = 0,e1 = 0,e2 = 0,f2 = 0,u1 = 0,u2 = 0;
    	double count = 0;
    	for(String season : temp){
    		for(MatchPO po : matchdata.getAllMatchesAboutTeam("GSW", season)){
    			MatchTeamDataPO po1 = po.getTeam1();
    			MatchTeamDataPO po2 = po.getTeam2();
    			if(po1.getAbbName().equals("GSW")){
    				A1 += po1.getThreePointHits() / po1.getThreePoint();
    				A2 += (po1.getThreePointHits() / po1.getThreePoint()) * (po1.getThreePointHits() / po1.getThreePoint());
    				A3 += (po1.getThreePointHits() / po1.getThreePoint()) * (po1.getThreePointHits() / po1.getThreePoint()) * (po1.getThreePointHits() / po1.getThreePoint());
    				A4 += (po1.getThreePointHits() / po1.getThreePoint()) * (po1.getThreePointHits() / po1.getThreePoint()) * (po1.getThreePointHits() / po1.getThreePoint()) * (po1.getThreePointHits() / po1.getThreePoint());
    			}
    			else{
    				A1 += po2.getThreePointHits() / po2.getThreePoint();
    				A2 += (po2.getThreePointHits() / po2.getThreePoint()) * (po2.getThreePointHits() / po2.getThreePoint());
    				A3 += (po2.getThreePointHits() / po2.getThreePoint()) * (po2.getThreePointHits() / po2.getThreePoint()) * (po2.getThreePointHits() / po2.getThreePoint());
    				A4 += (po2.getThreePointHits() / po2.getThreePoint()) * (po2.getThreePointHits() / po2.getThreePoint()) * (po2.getThreePointHits() / po2.getThreePoint()) * (po2.getThreePointHits() / po2.getThreePoint());
    			}
    			count ++;
    		}
    	}
    	A1 = getDouble4(A1 / count);
    	A2 = getDouble4(A2 / count);
    	A3 = getDouble4(A3 / count);
    	A4 = getDouble4(A4 / count);
    	
    	B2 = getDouble4(A2 - A1 * A1);
    	B3 = getDouble4(A3 - 3 * A2 * A1 + 2 * A1 * A1 * A1);
    	B4 = getDouble4(A4 - 4 * A3 * A1 + 6 * A2 * A1 * A1 - 3 * A1 * A1 * A1 * A1);
    	g1 = getDouble4(B3 / Math.sqrt(B2 * B2 *B2));
    	g2 = getDouble4(B4 / B2 * B2);
    	e1 = getDouble4(Math.sqrt(6 * (count - 2) / (count + 1) / (count + 3)));
		e2 = getDouble4(Math.sqrt(24 * count * (count - 2) * (count - 3) / ((count + 1) / (count + 1) / (count + 3) / (count + 5))));
		f2 = getDouble4(3 - 6 / (count + 1));
		u1 = getDouble4(g1 / e1);
		u2 = getDouble4((g2 - f2) / e2);
		
		list[0] = A1;
	    list[1] = A2;
	    list[2] = A3;
	    list[3] = A4;
	    list[4] = B2;
	    list[5] = B3;
	    list[6] = B4;
	    list[7] = g1;
	    list[8] = g2;
	    list[9] = e1;
	    list[10] = e2;
	    list[11] = f2;
	    list[12] = u1;
	    list[13] = u2;
	    
	    return list;
	}
    
    public double[] getF(){
    	double[] list = new double[14];
    	double[] temp1 = getNormal(2009,2011);
    	double[] temp2 = getNormal(2012,2014);
    	double s1 = temp1[4];
    	double s2 = temp2[4];
    	
    	double F = getDouble4(s1 / s2);
    	
    	list[0] = s1;
    	list[1] = s2;
    	list[2] = F;
    	
    	return list;
    }
    
	public ArrayList<TeamSeasonVO> getAllSeasonTeamData(){
		ArrayList<TeamSeasonVO> list = new ArrayList<TeamSeasonVO>();
		for(String season : getGSWSeason()){
			list.add(getAllTeamData(season));
		}
		return list;
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
	
	public double getDouble(double d){
		return new  java.math.BigDecimal(Double.toString(d)).setScale(3,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public double getDouble2(double d){
		return new  java.math.BigDecimal(Double.toString(d)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public double getDouble3(double d){
		return new  java.math.BigDecimal(Double.toString(d)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public double getDouble4(double d){
		return new  java.math.BigDecimal(Double.toString(d)).setScale(4,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
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
		Predo.predo();
		for(double d : bl.getYearThreepoint()){
			System.out.println(d);
		}
		
	}
}

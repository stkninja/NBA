package test;

import java.io.PrintStream;
import java.util.ArrayList;

import test.data.PlayerHighInfo;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.PlayerNormalInfo;
import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;


public class Console {
	Conversion con = null;
	Parse p = null;
	Calculate cal = null;
	private static String datasource = "";
	
	public Console(){
		con = new Conversion();
		p = new Parse();
		cal = new Calculate();	
	}
	
	public String getDataSource(){
		return datasource;
	}
	
	public void execute(PrintStream out,String[] args){
		String s ="";
		for(String str : args){
			s += str + " ";
		}
		s = s.substring(0, s.length() - 1);
		
		if(p.getDataSource(s) != null){
			datasource = p.getDataSource(s);
		}
		
		int i = 0;
		ArrayList<PlayerNormalInfo> playerNormalList = cal.getPlayerNormal(s);
		if(p.isPlayer(s).equals("player")){
			if(s.indexOf("-avg") >= 0 || s.indexOf("-total") >= 0){
				if(p.allorHotorKing(s).equals("all")){
					for(i = 0;i < playerNormalList.size();i ++){
						out.print(i + 1);
						out.println();
						out.print(playerNormalList.get(i));
						out.println();
					}
				}
			}
			else if(s.indexOf("-avg") < 0 && s.indexOf("-total") < 0){
				if(s.indexOf("all") >= 0){
					for(i = 0;i < playerNormalList.size();i ++){
						out.print(i + 1);
						out.println();
						out.print(playerNormalList.get(i));
						out.println();
					}
				}
				else if(s.indexOf("-hot") >= 0){
					for(i = 0;i < cal.getPlayerHot(s).size();i ++){
						out.print(i + 1);
						out.println();
						out.print(cal.getPlayerHot(s).get(i));
						out.println();
					}
				}
                else if(s.indexOf("-king") >= 0){
                	for(i = 0;i < cal.getPlayerKing(s).size();i ++){
						out.print(i + 1);
						out.println();
						out.print(cal.getPlayerKing(s).get(i));
						out.println();
					}
				}
                else{
                	if(p.isHigh(s)){
                		for(i = 0;i < cal.getPlayerHigh(s).size();i ++){
    						out.print(i + 1);
    						out.println();
    						out.print(cal.getPlayerHigh(s).get(i));
    						out.println();
    					}
                	}
                	else{
                		for(i = 0;i < playerNormalList.size();i ++){
    						out.print(i + 1);
    						out.println();
    						out.print(playerNormalList.get(i));
    						out.println();
    					}
                	}
                }
			}
		}
		else if(p.isPlayer(s).equals("team")){
			if(s.indexOf("-avg") >= 0 || s.indexOf("-total") >= 0){
				if(s.indexOf("all") >= 0 || (s.indexOf("all") < 0 && s.indexOf("hot") < 0)){
					for(i = 0;i < cal.getTeamNormal(s).size();i ++){
						out.print(i + 1);
						out.println();
						out.print(cal.getTeamNormal(s).get(i));
						out.println();
					}
				}
			}
			else if(s.indexOf("-avg") < 0 && s.indexOf("-total") < 0){
				if(s.indexOf("all") >= 0){
					for(i = 0;i < cal.getTeamNormal(s).size();i ++){
						out.print(i + 1);
						out.println();
						out.print(cal.getTeamNormal(s).get(i));
						out.println();
					}
				}
				else if(p.allorHotorKing(s).indexOf("hot") >= 0){
					for(i = 0;i < cal.getTeamHot(s).size();i ++){
						out.print(i + 1);
						out.println();
						out.print(cal.getTeamHot(s).get(i));
						out.println();
					}
				}
                
                else{
                	if(p.isHigh(s)){
                		for(i = 0;i < cal.getTeamHigh(s).size();i ++){
    						out.print(i + 1);
    						out.println();
    						out.print(cal.getTeamHigh(s).get(i));
    						out.println();
    					}
                	}
                	else{
                		for(i = 0;i < cal.getTeamNormal(s).size();i ++){
    						out.print(i + 1);
    						out.println();
    						out.print(cal.getTeamNormal(s).get(i));
    						out.println();
    					}
                	}
                }
			}
		}
	}
	
/*	public static void main(String[] args){
		Console c = new Console();
		String res = "";
		c.execute(System.out, new String[]{"--datasource","D:\\data"});
		System.out.println(datasource);
	}*/
}

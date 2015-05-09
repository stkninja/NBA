package test;

import java.io.PrintStream;

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
			s += str;
		}
		
		if(p.getDataSource(s) != null){
			datasource = p.getDataSource(s);
		}
		
		if(p.isPlayer(s)){
			if(s.indexOf("-avg") >= 0 || s.indexOf("-total") >= 0){
				if(p.allorHotorKing(s).equals("all")){
					for(PlayerNormalInfo p : cal.getPlayerNormal(s)){
						out.print(p);
					}
				}
			}
			else if(s.indexOf("-avg") < 0 && s.indexOf("-total") < 0){
				if(s.indexOf("all") >= 0){
					for(PlayerNormalInfo p : cal.getPlayerNormal(s)){
						out.print(p);
					}
				}
				else if(s.indexOf("-hot") >= 0){
					for(PlayerHotInfo p : cal.getPlayerHot(s)){
						out.print(p);
					}
				}
                else if(s.indexOf("-king") >= 0){
                	for(PlayerKingInfo p : cal.getPlayerKing(s)){
						out.print(p);
					}
				}
                else{
                	if(p.isHigh(s)){
                		for(PlayerHighInfo p : cal.getPlayerHigh(s)){
    						out.print(p);
    					}
                	}
                	else{
                		for(PlayerNormalInfo p : cal.getPlayerNormal(s)){
    						out.print(p);
    					}
                	}
                }
			}
		}
		else{
			if(s.indexOf("-avg") >= 0 || s.indexOf("-total") >= 0){
				if(s.indexOf("all") >= 0 || (s.indexOf("all") < 0 && s.indexOf("hot") < 0)){
					for(TeamNormalInfo t :cal.getTeamNormal(s)){
						out.print(t);
					}
				}
			}
			else if(s.indexOf("-avg") < 0 && s.indexOf("-total") < 0){
				if(s.indexOf("all") >= 0){
					for(TeamNormalInfo t :cal.getTeamNormal(s)){
						out.print(t);
					}
				}
				else if(p.allorHotorKing(s).indexOf("hot") >= 0){
					for(TeamHotInfo t :cal.getTeamHot(s)){
						out.print(t);
					}
				}
                
                else{
                	if(p.isHigh(s)){
                		for(TeamHighInfo t :cal.getTeamHigh(s)){
    						out.print(t);
    					}
                	}
                	else{
                		for(TeamNormalInfo t :cal.getTeamNormal(s)){
    						out.print(t);
    					}
                	}
                }
			}
		}
	}
	
}

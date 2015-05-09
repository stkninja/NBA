package test;

import java.util.ArrayList;

public class Parse {
	public String getDataSource(String s){
		if(s.indexOf("--datasoruce") >= 0){
			return s.split(" ")[1];
		}
		return null;
	}
	public boolean isPlayer(String s){
		if(s.indexOf("-player") >= 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String allorHotorKing(String s){
		String res = "";
		if(s.indexOf("-all") >= 0 || (s.indexOf("-hot") < 0 && s.indexOf("-king") < 0 && s.indexOf("-all") < 0)){
			res = "all";
		}
		else if(s.indexOf("-hot") >= 0){
			int i = s.indexOf("-hot");
			String temp = s.substring(i + 5);
			if(temp.indexOf(" ") >= 0){
				res = temp.split(" ")[0];
			}
			else{
				res = temp;
			}
		}
		else if(s.indexOf("-king") >= 0){
			int j = s.indexOf("-king");
			String temp = s.substring(j + 5);
			res = temp.split(" ")[0] + " " + temp.split(" ")[1].substring(1);
		}
		return res;
	}
	
	public int number(String s){
		int res = 0;
		if(s.indexOf("-n") >= 0){
			int i = s.indexOf("-n");
			String temp = s.substring(i + 3);
			if(temp.indexOf(" ") >= 0){
				res = Integer.parseInt(temp.split(" ")[0]);
			}
			else{
				res = Integer.parseInt(temp);
			}
		}
		else{
			if(isPlayer(s)){
				res = 50;
			}
			else{
				res = 30;
			}
		}
		return res;
	}
	
	public boolean isHigh(String s){
		if(s.indexOf("-high") >= 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	@SuppressWarnings("null")
	public String[] filter(String s){
		String[] res = null;
		
			res[0] = "filter";
			res[1] = "position";
			res[3] = "league";
			res[5] = "age";
			if(s.indexOf("position") >= 0){
				if(s.charAt(s.indexOf(".") + 1) == 'A'){
					res[2] = "All";
				}
				else{
					res[2] = s.substring(s.indexOf("position") + 9, s.indexOf("position") + 10);
				}
			}
			else{
				res[2] = "All";
			}
			
			if(s.indexOf("league") >= 0){
				if(s.charAt(s.indexOf("league") + 7) == 'A'){
					res[4] = "All";
				}
				else{
					res[4] = s.substring(s.indexOf("league") + 7, s.indexOf("league") + 11);
				}
			}
			else{
				res[4] = "All";
			}
			
			if(s.indexOf("age") >= 0){
				if(s.charAt(s.indexOf("age") + 4) == 'A'){
					res[6] = "All";
				}
				else if(s.charAt(s.indexOf("age") + 4) == '>'){
					res[6] = ">30";
				}
				else if(s.charAt(s.indexOf("age") + 4) == '<'){
					res[6] = "<=22";
				}
				else{
					res[6] = s.substring(s.indexOf("age") + 4, s.indexOf("age") + 14);
				}
			}
			else{
				res[6] = "All";
			}
		
		
		return res;
	}
	
	public ArrayList<String> sort(String s){
		ArrayList<String> list = new ArrayList<String>();
		if(s.indexOf("-sort") >= 0){
			String temp = s.substring(s.indexOf("-sort") + 6);
			if(temp.indexOf(" ") >= 0){
				String str = temp.split(" ")[0];
				if(str.indexOf(",") >= 0){
					String[] c = str.split(",");
					for(String string : c){
						list.add(string.split("\\.")[0]);
						list.add(string.split("\\.")[1]);
					}
				}
				else{
					list.add(str.split("\\.")[0]);
					list.add(str.split("\\.")[1]);
				}
			}
			else{
				if(temp.indexOf(",") >= 0){
					String[] c = temp.split(",");
					for(String string : c){
						list.add(string.split("\\.")[0]);
						list.add(string.split("\\.")[1]);
					}
				}
				else{
					list.add(temp.split("\\.")[0]);
					list.add(temp.split("\\.")[1]);
				}
			}
		}
		else{
			if(isPlayer(s)){
				if(!isHigh(s)){
					list.add("score");
					list.add("desc");
				}
				else{
					list.add("realShot");
					list.add("desc");
				}
			}
			else{
				if(!isHigh(s)){
					list.add("score");
					list.add("desc");
				}
				else{
					list.add("winRate");
					list.add("desc");
				}
			}
		}
		
		return list;
	}
	
/*	public static void main(String[] args){
		Parse p = new Parse();
		String str[] = {"-player",""};
	}*/
}

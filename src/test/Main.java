package test;


public class Main {
	public static void main(String[] args){
		
		Console c = new Console();
		c.execute(System.out, new String[]{"--datasource","D:\\data"});
//		c.execute(System.out, new String[]{"-player","-n","5","-filter","position.F,league.West","-sort","shot.desc"});
	
		c.execute(System.out, new String[]{"-team"});
	}
}

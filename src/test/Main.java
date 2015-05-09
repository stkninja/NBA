package test;

public class Main {
	public static void main(String[] args){
		Console c = new Console();
		c.execute(System.out, new String[]{"--datasource","D:\\data"});
		c.execute(System.out, new String[]{"-team","-n","5"});
	}
}

package test;

public class DataSource {
	private DataSource(){
		
	}
	
	private static DataSource datasource = null;
	private String source = "";
	
	public static DataSource getInstance(){
		if(datasource == null){
			datasource = new DataSource();
		}
		return datasource;
	}
	
	public void setSource(String s){
		datasource.source = s;
	}
	
	public String getSource(){
		return datasource.source;
	}
}

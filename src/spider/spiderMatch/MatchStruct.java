package spider.spiderMatch;

import java.util.ArrayList;

/*==================================================================*
 * 类似matchPO
 *==================================================================*/
public class MatchStruct {
	//id:example:14-15_08-10_LOL_GSW
	private String id = new String();
	private String season = new String();
	private String isPlayOffs = new String();
	//date 08-10
	private String year = new String();
	private String date = new String();
	private String vtAbbName = new String();
	private String htAbbName = new String();
	private String vtFullName = new String();
	private String htFullName = new String();
	//最后一项为总得分
	private String vtScores = new String();
	private String htScores = new String();
	private ArrayList<PlayerStruct> vtPlayers = new ArrayList<PlayerStruct>();
	private ArrayList<PlayerStruct> htPlayers = new ArrayList<PlayerStruct>();
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getVtAbbName() {
		return vtAbbName;
	}

	public void setVtAbbName(String vtAbbName) {
		this.vtAbbName = vtAbbName;
	}

	public String getHtAbbName() {
		return htAbbName;
	}

	public void setHtAbbName(String htAbbName) {
		this.htAbbName = htAbbName;
	}

	public String getVtFullName() {
		return vtFullName;
	}

	public void setVtFullName(String vtFullName) {
		this.vtFullName = vtFullName;
	}

	public String getHtFullName() {
		return htFullName;
	}

	public void setHtFullName(String htFullName) {
		this.htFullName = htFullName;
	}
	
	public void addVtPlayer(PlayerStruct p){
		this.vtPlayers.add(p);
	}
	
	public void addAllVtPlayers(ArrayList<PlayerStruct> lists){
		this.vtPlayers.addAll(lists);
		//前五为首发
		for(int i = 0; i < 5; i++){
			this.vtPlayers.get(i).setIsStarter("T");
		}
		for(int i = 5; i < vtPlayers.size(); i++){
			this.vtPlayers.get(i).setIsStarter("F");
		}
	}
	
	public void addHtPlayers(PlayerStruct p){
		this.htPlayers.add(p);
	}
	
	public void addAllHtPlayers(ArrayList<PlayerStruct> lists){
		this.htPlayers.addAll(lists);
		//前五为首发
		for(int i = 0; i < 5; i++){
			this.htPlayers.get(i).setIsStarter("T");
		}
		for(int i = 5; i < htPlayers.size(); i++){
			this.htPlayers.get(i).setIsStarter("F");
		}
	}
	
	public ArrayList<PlayerStruct> getVtPlayers() {
		return vtPlayers;
	}
	
	public ArrayList<PlayerStruct> getHtPlayers() {
		return htPlayers;
	}

	public String getVtScores() {
		return vtScores;
	}

	public void setVtScores(String vtScores) {
		//去除末尾的分号
		this.vtScores = vtScores.substring(0, vtScores.length() - 1);
	}

	public String getHtScores() {
		return htScores;
	}

	public void setHtScores(String htScores) {
		this.htScores = htScores.substring(0, htScores.length() - 1);
	}
	
	public String getSeason() {
		return season;
	}
	
	public void setSeason(String season) {
		this.season = season;
	}
	
	public String getIsPlayOffs() {
		return isPlayOffs;
	}
	
	public void setIsPlayOffs(String isPlayOffs) {
		this.isPlayOffs = isPlayOffs;
	}
	
	@Override
	public String toString() {
		return this.id + ";" + this.season + ";" + this.isPlayOffs + ";" + this.year + ";" + 
				this.date + ";" + this.vtAbbName + ";" + this.vtFullName + ";" + this.vtScores + ";" + 
				this.htAbbName + ";" + this.htFullName + ";" + this.htScores;
	}
}

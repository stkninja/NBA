package vo;


import javax.swing.ImageIcon;

public class TeamBasicInfoVO {
	public ImageIcon teamLogo = null;
	//球队全名
	public String fullName = new String();
	//球队缩写
	public String abbName = new String();
	//所在地
	public String location = new String();
	//赛区
	public String competionArea = new String();
	//分区
	public String subArea = new String();
	//主场
	public String homeGround = new String();
	//建立时间
	public String setupTime;
	
	public TeamBasicInfoVO(){
		
	}
}

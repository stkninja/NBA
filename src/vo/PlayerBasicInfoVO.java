package vo;

import java.io.File;

public class PlayerBasicInfoVO {
	public String name = new String();
	public String number = null;
	public String height = null;
	public String weight = null;
	public String birth = null;
	public String age = "0";
	public String exp = null;
	public String school = null;
	public File portrait = null;                 //Ð¤Ïñ
	public File action = null;                    //¶¯×÷ÕÕ
	public String position = "";
	
	public PlayerBasicInfoVO(){

	}
	
	public boolean isNull(){
		if(number == null && height == null && weight == null && birth == null && age == "0" && exp == null && school == null && portrait == null && 
				action == null && position == ""){
			return false;
		}
		else{
			return true;
		}
	}
}

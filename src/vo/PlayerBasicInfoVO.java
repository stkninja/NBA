package vo;

import java.io.File;

public class PlayerBasicInfoVO {
	public String name = new String();
	public String number;
	public String height = new String();
	public String weight = new String();
	public String birth = new String();
	public String age;
	public String exp;
	public String school =new String();
	public File portrait = null;                 //Ð¤Ïñ
	public File action = null;                    //¶¯×÷ÕÕ
	public String position = new String();
	public boolean isNull;
	
	public PlayerBasicInfoVO(){
		if(number.equals(null) && height.equals(null) && weight.equals(null) && birth.equals(null) && age.equals(null) && exp.equals(null) && school.equals(null) && portrait.equals(null) && 
				action.equals(null) && position.equals(null)){
			isNull = false;
		}
		else{
			isNull = true;
		}
	}
	
}

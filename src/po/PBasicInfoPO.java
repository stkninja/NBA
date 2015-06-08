package po;


public class PBasicInfoPO{	
	private String name = new String();
	private String height = new String();
	private String weight = new String();
	private String birth = new String();
	private String age = new String();
	private String exp = new String();
	private String school =new String();
	private String position = new String();            
	
	/***sets gets**/
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}

	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}
}

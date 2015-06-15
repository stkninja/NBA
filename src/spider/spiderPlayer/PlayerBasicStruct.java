package spider.spiderPlayer;

public class PlayerBasicStruct {
	private String name = new String();
	private String from = new String();
	private String to = new String();
	private String position = new String();
	private String height = new String();
	private String weight = new String();
	private String birth = new String();
	private String college = new String();
	private String link = new String();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
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
	
	public String getCollege() {
		return college;
	}
	
	public void setCollege(String college) {
		this.college = college;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.from + " " + this.to + " " + this.position + " " + this.weight + " "
				+ this.height + " " + this.birth + " " + this.college;
	}
}

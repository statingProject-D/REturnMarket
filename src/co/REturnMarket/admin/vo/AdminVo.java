package co.REturnMarket.admin.vo;

public class AdminVo {
	private String id;
	private String pw;
	private String name;
	private int phone;
	private String email;
	private String grade;
	private boolean registration;
	
	public AdminVo() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public boolean isRegistration() {
		return registration;
	}

	public void setRegistration(boolean registration) {
		this.registration = registration;
	}
}

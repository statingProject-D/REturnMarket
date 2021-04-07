package co.REturnMarket.admin.user.vo;

public class UserVo {
	private String user_id;
	private String pw;
	private String re_pw;
	private String name;
	private String phone;
	private String email;
	private String grade;
	private int addrNum;
	private String addr1;
	private String addr2;
	private boolean emailChk;
	
	public UserVo() {}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getRe_pw() {
		return re_pw;
	}

	public void setRe_pw(String re_pw) {
		this.re_pw = re_pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
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

	public int getAddrNum() {
		return addrNum;
	}

	public void setAddrNum(int addrNum) {
		this.addrNum = addrNum;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public boolean isEmailChk() {
		return emailChk;
	}

	public void setEmailChk(boolean emailChk) {
		this.emailChk = emailChk;
	}
}
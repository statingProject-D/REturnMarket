package vo;

public class JjimBean {
	private int no;
	private int pdNum;
	private String user_id;
	private String uploader;
	private String pdName;
	private String pdCategory;
	private String pdGroup;
	
	public JjimBean() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getPdNum() {
		return pdNum;
	}

	public void setPdNum(int pdNum) {
		this.pdNum = pdNum;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public String getPdCategory() {
		return pdCategory;
	}

	public void setPdCategory(String pdCategory) {
		this.pdCategory = pdCategory;
	}

	public String getPdGroup() {
		return pdGroup;
	}

	public void setPdGroup(String pdGroup) {
		this.pdGroup = pdGroup;
	}
}

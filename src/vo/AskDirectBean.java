package vo;

public class AskDirectBean {
	private int no;
	private int pdNum;
	private String pdName;
	private String uploader;
	private String user_id;
	private String askWord;
	
	public AskDirectBean() {}

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
	
	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAskWord() {
		return askWord;
	}

	public void setAskWord(String askWord) {
		this.askWord = askWord;
	}
}

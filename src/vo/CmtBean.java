package vo;

public class CmtBean {
	private int cmt_no;
	private int cmt_board;
	private String user_id;
	private String date;
	private int cmt_parent;
	private String content;
	
	public CmtBean() {}

	public int getCmt_no() {
		return cmt_no;
	}

	public void setCmt_no(int cmt_no) {
		this.cmt_no = cmt_no;
	}

	public int getCmt_board() {
		return cmt_board;
	}

	public void setCmt_board(int cmt_board) {
		this.cmt_board = cmt_board;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCmt_parent() {
		return cmt_parent;
	}

	public void setCmt_parent(int cmt_parent) {
		this.cmt_parent = cmt_parent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

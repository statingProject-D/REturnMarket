package vo;

public class BidBean {
	private int no;
	private int pdNum;
	private int bidNo;
	private String user_id;
	private String nowtime;
	private int startPrice;
	private int unit;
	private int nowPrice;
	private int finalPrice;
	
	public BidBean() {}
	
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

	public int getBidNo() {
		return bidNo;
	}

	public void setBidNo(int bidNo) {
		this.bidNo = bidNo;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNowtime() {
		return nowtime;
	}

	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}

	public int getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(int nowPrice) {
		this.nowPrice = nowPrice;
	}

	public int getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(int finalPrice) {
		this.finalPrice = finalPrice;
	}
}

package vo;

public class PdBean {
	private int pdNum;
	private String user_id;
	private String pdName;
	private String pdCategory;
	private String pdImg;
	private int pdCount;
	private String pdInfo;
	private String pdGroup;
	private int pdViewCount; // 조회수
	private int auStartPrice; // 경매 시작가격
	private int auUnit; // 입찰 단위
	private String auStartDay; // 경매 시작일
	private String auEndDay; // 경매 마지막날
	private int auDirectPrice; // 직거래가
	
	public PdBean() {}

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

	public String getPdImg() {
		return pdImg;
	}

	public void setPdImg(String pdImg) {
		this.pdImg = pdImg;
	}

	public int getPdCount() {
		return pdCount;
	}

	public void setPdCount(int pdCount) {
		this.pdCount = pdCount;
	}

	public String getPdInfo() {
		return pdInfo;
	}

	public void setPdInfo(String pdInfo) {
		this.pdInfo = pdInfo;
	}

	public String getPdGroup() {
		return pdGroup;
	}

	public void setPdGroup(String pdGroup) {
		this.pdGroup = pdGroup;
	}
	
	public int getPdViewCount() {
		return pdViewCount;
	}

	public void setPdViewCount(int pdViewCount) {
		this.pdViewCount = pdViewCount;
	}

	public int getAuStartPrice() {
		return auStartPrice;
	}

	public void setAuStartPrice(int auStartPrice) {
		this.auStartPrice = auStartPrice;
	}

	public int getAuUnit() {
		return auUnit;
	}

	public void setAuUnit(int auUnit) {
		this.auUnit = auUnit;
	}

	public String getAuStartDay() {
		return auStartDay;
	}

	public void setAuStartDay(String auStartDay) {
		this.auStartDay = auStartDay;
	}

	public String getAuEndDay() {
		return auEndDay;
	}

	public void setAuEndDay(String auEndDay) {
		this.auEndDay = auEndDay;
	}

	public int getAuDirectPrice() {
		return auDirectPrice;
	}

	public void setAuDirectPrice(int auDirectPrice) {
		this.auDirectPrice = auDirectPrice;
	}
}

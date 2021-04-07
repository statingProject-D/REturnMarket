package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.AskDirectBean;
import vo.BidBean;
import vo.JjimBean;
import vo.PdBean;
import vo.UserBean;

public class PdDAO {
	private static PdDAO pdDao;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static PdDAO getInstance() {
		if(pdDao == null) {
			pdDao = new PdDAO();
		}
		return pdDao;
	}

	public void setConnection(Connection con) {
		this.con = con;		
	}

	// 상품업로드
	public int uploadPd(PdBean pdBean) {
		int uploadPd = 0;
		int pdNum = 0;
		String num_sql = "select max(pdNum) from pd";
		
		try {
			pstmt = con.prepareStatement(num_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pdNum = rs.getInt(1) + 1;
			} else {
				return pdNum = 1;
			}
			
			String sql = "insert into pd values(?,?,?,?,?,?,?,?,0,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			pstmt.setString(2, pdBean.getUser_id());
			pstmt.setString(3, pdBean.getPdName());
			pstmt.setString(4, pdBean.getPdCategory());
			pstmt.setString(5, pdBean.getPdImg());
			pstmt.setInt(6, pdBean.getPdCount());
			pstmt.setString(7, pdBean.getPdInfo());
			pstmt.setString(8, pdBean.getPdGroup());
			pstmt.setInt(9, pdBean.getAuStartPrice());
			pstmt.setInt(10, pdBean.getAuUnit());
			pstmt.setString(11, pdBean.getAuEndDay());
			pstmt.setInt(12, pdBean.getAuDirectPrice());
			
			uploadPd = pstmt.executeUpdate();
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return uploadPd; // 업로드 실패
	}

	// 상품 리스트 불러오기
	public ArrayList<PdBean> getPdList() {
		ArrayList<PdBean> pdList = null;
		String sql = "select * from pd";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pdList = new ArrayList<PdBean>();
				
				do {
					PdBean pdBean = new PdBean();
					pdBean.setPdNum(rs.getInt("pdNum"));
					pdBean.setUser_id(rs.getString("user_id"));
					pdBean.setPdName(rs.getString("pdName"));
					pdBean.setPdCategory(rs.getString("pdCategory"));
					pdBean.setPdImg(rs.getString("pdImg"));
					pdBean.setPdCount(rs.getInt("pdCount"));
					pdBean.setPdInfo(rs.getString("pdInfo"));
					pdBean.setPdGroup(rs.getString("pdGroup"));
					pdBean.setPdViewCount(rs.getInt("pdViewCount"));
					pdBean.setAuStartPrice(rs.getInt("auStartPrice"));
					pdBean.setAuUnit(rs.getInt("auUnit"));
					pdBean.setAuStartDay(rs.getString("auStartDay"));
					pdBean.setAuEndDay(rs.getString("auEndDay"));
					pdBean.setAuDirectPrice(rs.getInt("auDirectPrice"));
					
					pdList.add(pdBean);
				} while (rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return pdList;
	}

	// 조회수 증가
	public int upViewCount(int pdNum) {
		int upViewCount = 0;
		String sql = "update pd set pdViewCount = pdViewCount + 1 where pdNum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			upViewCount = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return upViewCount;
	}

	// view 불러오기
	public PdBean getPdView(int pdNum) {
		PdBean pdBean = null;
		String sql = "select * from pd where pdNum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pdBean = new PdBean();
				pdBean.setPdNum(rs.getInt("pdNum"));
				pdBean.setUser_id(rs.getString("user_id"));
				pdBean.setPdName(rs.getString("pdName"));
				pdBean.setPdCategory(rs.getString("pdCategory"));
				pdBean.setPdImg(rs.getString("pdImg"));
				pdBean.setPdCount(rs.getInt("pdCount"));
				pdBean.setPdInfo(rs.getString("pdInfo"));
				pdBean.setPdGroup(rs.getString("pdGroup"));
				pdBean.setPdViewCount(rs.getInt("pdViewCount"));
				pdBean.setAuStartPrice(rs.getInt("auStartPrice"));
				pdBean.setAuUnit(rs.getInt("auUnit"));
				pdBean.setAuStartDay(rs.getString("auStartDay"));
				pdBean.setAuEndDay(rs.getString("auEndDay"));
				pdBean.setAuDirectPrice(rs.getInt("auDirectPrice"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return pdBean;
	}

	// 입찰자 확인
	public int bidUpload(BidBean bidBean) {
		int uploadBid = 0;
		int bidNo = 0;
		String bidNo_sql = "select max(bidNo) from bid where pdNum = ?";
		
		try {
			pstmt = con.prepareStatement(bidNo_sql);
			pstmt.setInt(1, bidBean.getPdNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bidNo = rs.getInt(1) + 1;
			} else {
				return bidNo;
			}
			
			int no = 0;
			String no_sql = "select max(no) from bid";
			
			pstmt = con.prepareStatement(no_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1) + 1;
			} else {
				return no;
			}
			
			String sql = "insert into bid values(?, ?, ?, ?, now(), ?, ?, ?, 0)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, bidBean.getPdNum());
			pstmt.setInt(3, bidNo);
			pstmt.setString(4, bidBean.getUser_id());
			pstmt.setInt(5, bidBean.getStartPrice());
			pstmt.setInt(6, bidBean.getUnit());
			pstmt.setInt(7, bidBean.getStartPrice() + (bidBean.getUnit() * bidNo));
			uploadBid = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return uploadBid;
	}

	// 마지막 가격 조회
	public int finalPrice(BidBean bidBean) {
		int fPValue = 0;
		int finalPrice = 0;
		
		try {
			int bidNo = 0;
			String no_sql = "select max(bidNo) from bid where pdNum = ?";
			
			pstmt = con.prepareStatement(no_sql);
			pstmt.setInt(1, bidBean.getPdNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bidNo = rs.getInt(1);
			} else {
				return bidNo;
			}
			
			// 마지막 값 조회
			String finalPrice_sql = "select nowPrice from bid where pdNum = ? order by nowPrice desc limit 1";
			pstmt = con.prepareStatement(finalPrice_sql);
			pstmt.setInt(1, bidBean.getPdNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				finalPrice = rs.getInt(1);
				
				String upFinalPrice_sql = "update bid set finalPrice = ? where bidNo = ?";
				pstmt = con.prepareStatement(upFinalPrice_sql);
				pstmt.setInt(1, finalPrice);
				pstmt.setInt(2, bidNo);
				
				fPValue = pstmt.executeUpdate();
			} else {
				return finalPrice;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return fPValue;
	}

	// 입찰 리스트 불러오기
	public ArrayList<BidBean> getBidList(BidBean bidBean) {
		ArrayList<BidBean> bidList = null;
		String sql = "select * from bid where pdNum = ? order by no desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bidBean.getPdNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bidList = new ArrayList<BidBean>();
				
				do {
					BidBean bidListBean = new BidBean();
					bidListBean.setPdNum(rs.getInt("pdNum"));
					bidListBean.setBidNo(rs.getInt("bidNo"));
					bidListBean.setUser_id(rs.getString("user_id"));
					bidListBean.setNowtime(rs.getString("nowtime"));
					bidListBean.setStartPrice(rs.getInt("startPrice"));
					bidListBean.setUnit(rs.getInt("unit"));
					bidListBean.setNowPrice(rs.getInt("nowPrice"));
					bidListBean.setFinalPrice(rs.getInt("finalPrice"));
					
					bidList.add(bidListBean);
				} while (rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return bidList;
	}

	// 마지막 입찰자 확인
	public String bidLastUser(BidBean bidBean) {
		String lastBidUser = null;
		String find_sql ="select user_id from bid where pdNum = ? order by nowPrice desc limit 1";
		
		try {
			pstmt = con.prepareStatement(find_sql);
			pstmt.setInt(1, bidBean.getPdNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				lastBidUser = rs.getString(1);
			} else {
				return lastBidUser;
			}
						
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return lastBidUser;
	}

	// 카테고리 만으로 조회
	public ArrayList<PdBean> searchCategory(String bgCg, String smCg) {
		ArrayList<PdBean> pdList = null;
		String sql = "select * from pd where pdGroup = ? and pdCategory = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bgCg);
			pstmt.setString(2, smCg);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pdList = new ArrayList<PdBean>();
				
				do {
					PdBean pdBean = new PdBean();
					pdBean.setPdNum(rs.getInt("pdNum"));
					pdBean.setUser_id(rs.getString("user_id"));
					pdBean.setPdName(rs.getString("pdName"));
					pdBean.setPdCategory(rs.getString("pdCategory"));
					pdBean.setPdImg(rs.getString("pdImg"));
					pdBean.setPdCount(rs.getInt("pdCount"));
					pdBean.setPdInfo(rs.getString("pdInfo"));
					pdBean.setPdGroup(rs.getString("pdGroup"));
					pdBean.setPdViewCount(rs.getInt("pdViewCount"));
					pdBean.setAuStartPrice(rs.getInt("auStartPrice"));
					pdBean.setAuUnit(rs.getInt("auUnit"));
					pdBean.setAuStartDay(rs.getString("auStartDay"));
					pdBean.setAuEndDay(rs.getString("auEndDay"));
					pdBean.setAuDirectPrice(rs.getInt("auDirectPrice"));
					
					pdList.add(pdBean);
				} while (rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return pdList;
	}
	
	// 검색어로만 검색
	public ArrayList<PdBean> pdNameSearch(String searchPdName) {
		ArrayList<PdBean> pdList = null;
		String sql = "select * from pd where pdName like ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" +searchPdName +"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pdList = new ArrayList<PdBean>();
				
				do {
					PdBean pdBean = new PdBean();
					pdBean.setPdNum(rs.getInt("pdNum"));
					pdBean.setUser_id(rs.getString("user_id"));
					pdBean.setPdName(rs.getString("pdName"));
					pdBean.setPdCategory(rs.getString("pdCategory"));
					pdBean.setPdImg(rs.getString("pdImg"));
					pdBean.setPdCount(rs.getInt("pdCount"));
					pdBean.setPdInfo(rs.getString("pdInfo"));
					pdBean.setPdGroup(rs.getString("pdGroup"));
					pdBean.setPdViewCount(rs.getInt("pdViewCount"));
					pdBean.setAuStartPrice(rs.getInt("auStartPrice"));
					pdBean.setAuUnit(rs.getInt("auUnit"));
					pdBean.setAuStartDay(rs.getString("auStartDay"));
					pdBean.setAuEndDay(rs.getString("auEndDay"));
					pdBean.setAuDirectPrice(rs.getInt("auDirectPrice"));
					
					pdList.add(pdBean);
				} while (rs.next());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return pdList;
	}

	// 전체 검색
	public ArrayList<PdBean> totalSearch(String bgCg, String smCg, String searchPdName) {
		ArrayList<PdBean> pdList = null;
		String sql = "select * from pd where pdGroup = ? and pdCategory = ? and pdName like ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bgCg);
			pstmt.setString(2, smCg);
			pstmt.setString(3, "%" +searchPdName +"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pdList = new ArrayList<PdBean>();
				
				do {
					PdBean pdBean = new PdBean();
					pdBean.setPdNum(rs.getInt("pdNum"));
					pdBean.setUser_id(rs.getString("user_id"));
					pdBean.setPdName(rs.getString("pdName"));
					pdBean.setPdCategory(rs.getString("pdCategory"));
					pdBean.setPdImg(rs.getString("pdImg"));
					pdBean.setPdCount(rs.getInt("pdCount"));
					pdBean.setPdInfo(rs.getString("pdInfo"));
					pdBean.setPdGroup(rs.getString("pdGroup"));
					pdBean.setPdViewCount(rs.getInt("pdViewCount"));
					pdBean.setAuStartPrice(rs.getInt("auStartPrice"));
					pdBean.setAuUnit(rs.getInt("auUnit"));
					pdBean.setAuStartDay(rs.getString("auStartDay"));
					pdBean.setAuEndDay(rs.getString("auEndDay"));
					pdBean.setAuDirectPrice(rs.getInt("auDirectPrice"));
					
					pdList.add(pdBean);
				} while (rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return pdList;
	}

	// 찜 더하기
	public int addJjim(JjimBean jjimBean, String user_id) {
		int addJjim = 0;
		int no = 0;
		String no_sql = "select max(no) from jjim";
		
		try {
			pstmt = con.prepareStatement(no_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1) + 1;
			} else {
				return addJjim;
			}
			
			String sql = "insert into jjim values(?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, jjimBean.getPdNum());
			pstmt.setString(3, user_id);
			pstmt.setString(4, jjimBean.getUser_id());
			pstmt.setString(5, jjimBean.getPdName());
			pstmt.setString(6, jjimBean.getPdCategory());
			pstmt.setString(7, jjimBean.getPdGroup());
			addJjim = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return addJjim;
	}

	// 찜한거 두번 들어가는거 방지
	public String chkMultiJjim(String user_id, int pdNum) {
		String multiChk = null;
		String sql = "select user_id from jjim where pdNum = ? and user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				multiChk = rs.getString(1);
			} else {
				return "first";
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return multiChk;
	}

	// 유저 찜리스트 가져오기
	public ArrayList<JjimBean> getJjimList(String user_id) {
		ArrayList<JjimBean> jjimList = null;
		String sql = "select * from jjim where user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				jjimList = new ArrayList<JjimBean>();
				
				do {
					JjimBean jjimBean = new JjimBean();
					jjimBean.setNo(rs.getInt("no"));
					jjimBean.setPdNum(rs.getInt("pdNum"));
					jjimBean.setUser_id(user_id);
					jjimBean.setUploader(rs.getString("uploader"));
					jjimBean.setPdName(rs.getString("pdName"));
					jjimBean.setPdCategory(rs.getString("pdCategory"));
					jjimBean.setPdGroup(rs.getString("pdGroup"));
					
					jjimList.add(jjimBean);
				} while (rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return jjimList;
	}

	// 찜하기 삭제 (유저아이디 필요시)
	public int deleteJjim(int pdNum, String user_id) {
		int delJjim = 0;
		String sql = "delete from jjim where pdNum = ? and user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			pstmt.setString(2, user_id);
			delJjim = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return delJjim;
	}

	// 즉시구매가 요청 추가
	public int addAskDirect(AskDirectBean askDirectBean) {
		int addAskDirect = 0; 
		int no = 0;
		String no_sql = "select max(no) from askDirect";		
		
		try {
			pstmt = con.prepareStatement(no_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1) + 1;
			} else {
				return no;
			}
			
			String sql = "insert into askDirect values(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, askDirectBean.getPdNum());
			pstmt.setString(3, askDirectBean.getPdName());
			pstmt.setString(4, askDirectBean.getUploader());
			pstmt.setString(5, askDirectBean.getUser_id());
			pstmt.setString(6, askDirectBean.getAskWord());
			
			addAskDirect = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return addAskDirect;
	}

	// 요청 리스트 불러오기
	public ArrayList<AskDirectBean> getDirectList(PdBean pdBean, String user_id) {
		ArrayList<AskDirectBean> directList = null;
		String no_sql = "select count(uploader) from askDirect where uploader = ?";
		int no = 0;
		
		try {
			pstmt = con.prepareStatement(no_sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1) + 1;
			} 
			
			String sql = "select * from askDirect where uploader = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				directList = new ArrayList<AskDirectBean>();
				
				do {
					AskDirectBean askDirectBean = new AskDirectBean();
					
					askDirectBean.setNo(no);
					askDirectBean.setPdNum(rs.getInt("pdNum"));
					askDirectBean.setPdName(rs.getString("pdName"));
					askDirectBean.setUploader(rs.getString("uploader"));
					askDirectBean.setUser_id(rs.getString("user_id"));
					askDirectBean.setAskWord(rs.getString("askWord"));
					
					directList.add(askDirectBean);
				} while(rs.next());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return directList;
	}

	// 게시물 삭제
	public int deletePd(int pdNum) {
		int deletePd = 0;
		String sql = "delete p, b, j, a from pd as p left join bid as b on b.pdNum = p.pdNum" + 
				" left join jjim as j on j.pdNum = b.pdNum" + 
				" left join askDirect as a on b.pdNum = a.pdNum where p.pdNum = ?"; 
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			deletePd = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return deletePd;
	}

	// 즉시 구매 요청 리스트 삭제
	public int delDirectList(int pdNum, String requester) {
		int delDirectList = 0;
		String sql = "delete from askDirect where pdNum = ? and user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			pstmt.setString(2, requester);
			delDirectList = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return delDirectList;
	}

	// 게시물 수정
	public int modiPd(PdBean pdBean) {
		int modiPd = 0;
		String sql = "update pd set pdName = ?, pdCategory = ?, pdImg = ?, pdCount = ?, pdInfo = ?, pdGroup = ?, auStartPrice = ?, auUnit = ?, auEndDay = ?, auDirectPrice= ? where pdNum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdBean.getPdName());
			pstmt.setString(2, pdBean.getPdCategory());
			pstmt.setString(3, pdBean.getPdImg());
			pstmt.setInt(4, pdBean.getPdCount());
			pstmt.setString(5, pdBean.getPdInfo());
			pstmt.setString(6, pdBean.getPdGroup());
			pstmt.setInt(7, pdBean.getAuStartPrice());
			pstmt.setInt(8, pdBean.getAuUnit());
			pstmt.setString(9, pdBean.getAuEndDay());
			pstmt.setInt(10, pdBean.getAuDirectPrice());
			pstmt.setInt(11, pdBean.getPdNum());
			
			modiPd = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return modiPd;
	}

	// 글 수정시 bid 초기화를 위한 삭제
	public int deleteBid(PdBean pdBean) {
		int deleteBid = 0;
		String sql = "delete from bid where pdNum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pdBean.getPdNum());
			deleteBid = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return deleteBid;
	}

	// 내 pdList보기
	public ArrayList<PdBean> getMyPdList(String user_id) {
		ArrayList<PdBean> myPdList = null;
		String sql = "select * from pd where user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				myPdList = new ArrayList<PdBean>();
				
				do {
					PdBean pdBean = new PdBean();
					pdBean.setPdNum(rs.getInt("pdNum"));
					pdBean.setUser_id(rs.getString("user_id"));
					pdBean.setPdName(rs.getString("pdName"));
					pdBean.setPdCategory(rs.getString("pdCategory"));
					pdBean.setPdImg(rs.getString("pdImg"));
					pdBean.setPdCount(rs.getInt("pdCount"));
					pdBean.setPdInfo(rs.getString("pdInfo"));
					pdBean.setPdGroup(rs.getString("pdGroup"));
					pdBean.setPdViewCount(rs.getInt("pdViewCount"));
					pdBean.setAuStartPrice(rs.getInt("auStartPrice"));
					pdBean.setAuUnit(rs.getInt("auUnit"));
					pdBean.setAuStartDay(rs.getString("auStartDay"));
					pdBean.setAuEndDay(rs.getString("auEndDay"));
					pdBean.setAuDirectPrice(rs.getInt("auDirectPrice"));
					
					myPdList.add(pdBean);
				} while (rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return myPdList;
	}

	// 낙찰자 입찰정보 확인 / 보내기
	public BidBean getBuyerBidInfo(int pdNum) {
		BidBean bidBean = null;
		
		String bid_sql = "select * from bid where pdNum = ? order by finalPrice desc limit 1;"; // 최종 낙찰자 정보 가져오기
		
		try {
			pstmt = con.prepareStatement(bid_sql);
			pstmt.setInt(1, pdNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bidBean = new BidBean();
				bidBean.setNo(rs.getInt("no"));
				bidBean.setPdNum(rs.getInt("pdNum"));
				bidBean.setBidNo(rs.getInt("bidNo"));
				bidBean.setUser_id(rs.getString("user_id"));
				bidBean.setNowtime(rs.getString("nowtime"));
				bidBean.setStartPrice(rs.getInt("startPrice"));
				bidBean.setUnit(rs.getInt("unit"));
				bidBean.setNowPrice(rs.getInt("nowPrice"));
				bidBean.setFinalPrice(rs.getInt("finalPrice"));
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return bidBean;
	}

	// 낙찰자 유저정보 확인 / 보내기
	public UserBean getBuyerUserInfo(BidBean bidBean) {
		UserBean userBean = null;
		String user_sql = "select * from user where user_id = ?";
		
		try {
			pstmt = con.prepareStatement(user_sql);
			pstmt.setString(1, bidBean.getUser_id());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userBean = new UserBean();
				userBean.setUser_id(rs.getString("user_id"));
				userBean.setName(rs.getString("name"));
				userBean.setPhone(rs.getString("phone"));
				userBean.setEmail(rs.getString("email"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return userBean;
	}
}
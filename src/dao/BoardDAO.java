package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.CmtBean;
import vo.NoticeBean;
import vo.QnaBean;

public class BoardDAO {
	private static BoardDAO boardDao;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static BoardDAO getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDAO();
		}
		return boardDao;
	}

	public void setConnection(Connection con) {
		this.con = con;		
	}

	// 불러올 글의 갯수 파악
	public int selectAllNoticeCount() {
		int selectNotice = 0;
		String sql = "select count(*) from notice"; // 불러올 글의 총 갯수를 확인
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				selectNotice = rs.getInt(1); // 총 열의 갯수를 가져온다.
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return selectNotice; // 값이 0 이면 반환할 값이 없다.
	}

	// 공지 리스트 보기
	public ArrayList<NoticeBean> selectAllNoticeList(int page, int limit) {
		ArrayList<NoticeBean> list = new ArrayList<NoticeBean>();
		String sql = "select * from notice order by no desc limit ?, ?";   //처음 시작하는 글위치 /읽기 시작할 row 번호
		int startrow = (page - 1) * limit; // 시작 열 번호
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeBean noticeBean = new NoticeBean();
				noticeBean.setNo(rs.getInt("no"));
				noticeBean.setUser_id(rs.getString("user_id"));
				noticeBean.setTitle(rs.getString("title"));
				noticeBean.setContent(rs.getString("content"));
				noticeBean.setDate(rs.getDate("date"));
				list.add(noticeBean); // ArrayList에 정보를 1열씩 추가
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return list; // 실패
	}

	public ArrayList<NoticeBean> getNoticeList() {

		return null;
	}

	// 검색어로 notice 안에꺼 찾기
	public ArrayList<NoticeBean> searchNoticeList(String searchWord) {
		ArrayList<NoticeBean> noticeList = null;
		String sql = "select * from notice where title like ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" +searchWord +"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeList = new ArrayList<NoticeBean>();
				
				do {
				NoticeBean noticeBean = new NoticeBean();
				noticeBean.setNo(rs.getInt("no"));
				noticeBean.setUser_id(rs.getString("user_id"));
				noticeBean.setTitle(rs.getString("title"));
				noticeBean.setContent(rs.getString("content"));
				noticeBean.setDate(rs.getDate("date"));
				
				noticeList.add(noticeBean);
				} while(rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return noticeList;
	}

	// 공지사항 화면 불러오기
	public NoticeBean getNoticeBean(int no) {
		NoticeBean noticeBean = null;
		String sql = "select * from notice where no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeBean = new NoticeBean();
				noticeBean.setNo(rs.getInt(1));
				noticeBean.setUser_id(rs.getString("user_id"));
				noticeBean.setTitle(rs.getString("title"));
				noticeBean.setContent(rs.getString("content"));
				noticeBean.setDate(rs.getDate("date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace();} 
		}
		
		return noticeBean;
	}

	// qna 불러올 글 갯수 확인
	public int selectAllQnaCount() {
		int selectQna = 0;
		String sql = "select count(*) from qna"; // 불러올 글의 총 갯수를 확인
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				selectQna = rs.getInt(1); // 총 열의 갯수를 가져온다.
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return selectQna; // 값이 0 이면 반환할 값이 없다.
	}

	// Qna 리스트 보기
	public ArrayList<QnaBean> selectAllQnaList(int page, int limit) {
		ArrayList<QnaBean> qnaList = new ArrayList<QnaBean>();
		String sql = "select * from qna order by no desc limit ?, ?";   //처음 시작하는 글위치 /읽기 시작할 row 번호
		int startrow = (page - 1) * limit; // 시작 열 번호
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaBean qnaBean = new QnaBean();
				qnaBean.setNo(rs.getInt("no"));
				qnaBean.setTitle(rs.getString("title"));
				qnaBean.setUser_id(rs.getString("user_id"));
				qnaBean.setContent(rs.getString("content"));
				qnaBean.setViewCount(rs.getInt("viewCount"));
				qnaBean.setDate(rs.getString("date"));
				
				qnaList.add(qnaBean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return qnaList; // 실패
	}

	// qna 정보 가져오기
	public QnaBean getQnaInfo(int no) {
		QnaBean qnaBean = null;
		String sql = "select * from qna where no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qnaBean = new QnaBean();
				qnaBean.setNo(rs.getInt("no"));
				qnaBean.setTitle(rs.getString("title"));
				qnaBean.setUser_id(rs.getString("user_id"));
				qnaBean.setContent(rs.getString("content"));
				qnaBean.setViewCount(rs.getInt("viewCount"));
				qnaBean.setDate(rs.getString("date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return qnaBean;
	}

	// qna 업로드
	public int uploadQna(QnaBean qnaBean) {
		int no = 0;
		int uploadQna = 0;
		String no_sql = "select max(no) from qna";
		String sql = "insert into qna values(?,?,?,?,0, now())";
		
		try {
			pstmt = con.prepareStatement(no_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1) + 1;
			} else {
				return no;
			}
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, qnaBean.getTitle());
			pstmt.setString(3, qnaBean.getUser_id());
			pstmt.setString(4, qnaBean.getContent());
			uploadQna = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return uploadQna;
	}

	// 조회수 올리기
	public int qnaViewCount(int no) {
		int viewCount = 0;
		int updateViewCount = 0;
		String count_sql = "select viewCount from qna where no = ?";
		String count_up = "update qna set viewCount = ? where no = ?";
		
		try {
			pstmt = con.prepareStatement(count_sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				viewCount = rs.getInt(1) + 1;
			}
			
			pstmt = con.prepareStatement(count_up);
			pstmt.setInt(1, viewCount);
			pstmt.setInt(2, no);
			updateViewCount = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return updateViewCount;
	}

	// 댓글 불러오기
	public ArrayList<CmtBean> getComments(int no) {
		ArrayList<CmtBean> cmtList = null;
		CmtBean cmtBean = null;
		String sql = "select * from comment where cmt_board = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cmtList = new ArrayList<CmtBean>();
				
				do {
				cmtBean = new CmtBean();
				cmtBean.setCmt_no(rs.getInt("cmt_no"));
				cmtBean.setCmt_board(rs.getInt("cmt_board"));
				cmtBean.setUser_id(rs.getString("user_id"));
				cmtBean.setDate(rs.getString("date"));
				cmtBean.setCmt_parent(rs.getInt("cmt_parent"));
				cmtBean.setContent(rs.getString("content"));
				
				cmtList.add(cmtBean);
				} while(rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return cmtList;
	}

	// 댓글 업로드
	public int uploadComment(CmtBean cmtBean) {
		int uploadCmt = 0;
		int no = 0;
		String no_sql = "select max(cmt_no) from comment"; 
		String sql = "insert into comment values(?,?,?,now(),0,?)";
		
		try {
			pstmt = con.prepareStatement(no_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1) + 1;
			} else {
				return no;
			}
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, cmtBean.getCmt_board());
			pstmt.setString(3, cmtBean.getUser_id());
			pstmt.setString(4, cmtBean.getContent());
			uploadCmt = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return uploadCmt;
	}

	// qna 수정
	public int updateQna(QnaBean qnaBean) {
		int updateQna = 0;
		String sql = "update qna set title = ?, content = ? where no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qnaBean.getTitle());
			pstmt.setString(2, qnaBean.getContent());
			pstmt.setInt(3, qnaBean.getNo());
			updateQna = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return updateQna;
	}

	// qna 삭제
	public int deleteQna(int no) {
		int deleteQna = 0;
		String sql = "delete from qna where no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			deleteQna = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return deleteQna;
	}
	
	// 해당 댓글 삭제
		public int deletAnyComment(int cmt_no) {
			int deleteAnyComment = 0;
			String sql = "delete from comment where cmt_no = ?"; 
					
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cmt_no);
				deleteAnyComment = pstmt.executeUpdate();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
			}
			
			return deleteAnyComment;
		}

	// comment 전체 삭제
	public int deletComment(int no) {
		int deleteCmt = 0;
		String sql = "delete from comment where cmt_board = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			deleteCmt = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return deleteCmt;
	}

	// 검색어로 qna 찾기
	public ArrayList<QnaBean> getQnaSearchWord(int page, int limit, String searchWord) {
		ArrayList<QnaBean> qnaList = null;
		String sql = "select * from qna where title like ? order by no desc limit ?, ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+ searchWord +"%");
			pstmt.setInt(2, page - 1);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qnaList = new ArrayList<QnaBean>();
				
				do {
					QnaBean qnaBean = new QnaBean();
					
					qnaBean.setNo(rs.getInt("no"));
					qnaBean.setTitle(rs.getString("title"));
					qnaBean.setUser_id(rs.getString("user_id"));
					qnaBean.setContent(rs.getString("content"));
					qnaBean.setViewCount(rs.getInt("viewCount"));
					qnaBean.setDate(rs.getString("date"));
					
					qnaList.add(qnaBean);
				} while(rs.next());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return qnaList;
	}
}

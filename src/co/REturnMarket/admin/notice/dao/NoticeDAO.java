package co.REturnMarket.admin.notice.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.REturnMarket.admin.common.DAO;
import co.REturnMarket.admin.notice.vo.NoticeVo;

public class NoticeDAO extends DAO{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public NoticeDAO() {
		super();
	}
	
	// 공지사항 전체 조회
	public ArrayList<NoticeVo> selectNotice() {
		ArrayList<NoticeVo> voList = null;
		NoticeVo vo = null;
		String sql = "select * from notice";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				voList = new ArrayList<NoticeVo>();
				
				do {
					vo = new NoticeVo();
					vo.setNo(rs.getInt("no"));
					vo.setUser_id(rs.getString("user_id"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setDate(rs.getString("date"));
					voList.add(vo);
				} while(rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return voList;
	}

	// 공지사항 삭제
	public int deleteNotice(int no) {
		int r = 0; // r = result
		String sql = "delete from notice where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			r = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}
	
	// 공지사항 업로드
	public int uploadNotice(NoticeVo vo) {
		int r = 0; // r = result
		int no = 0;
		String no_sql = "select max(no) from notice";
		String sql = "insert into notice values(?,?,?,?,now())";
		
		try {
			pstmt = conn.prepareStatement(no_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1) + 1;
			} else {
				return no;
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, vo.getUser_id());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			r = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
				
		return r;
	}
	
	// 공지사항 제목 검색
	public ArrayList<NoticeVo> searchNotice(String search) {
		ArrayList<NoticeVo> voList = null;
		NoticeVo vo = null;
		String sql = "select * from notice where title like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" +search +"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				voList = new ArrayList<NoticeVo>();
				
				do {
					vo = new NoticeVo();
					vo.setNo(rs.getInt("no"));
					vo.setUser_id(rs.getString("user_id"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setDate(rs.getString("date"));
					
					voList.add(vo);
				} while(rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return voList;
	}
	
	// 공지사항 보기
	public NoticeVo getNotice(int no) {
		NoticeVo vo = null;
		String sql = "select * from notice where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new NoticeVo();
				vo.setNo(rs.getInt("no"));
				vo.setUser_id(rs.getString("user_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getString("date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}
	
	// 공지사항 수정 
	public int updateNotice(NoticeVo vo) {
		int r = 0; // r = reuslt
		String sql = "update notice set user_id = ?, title = ?, content = ? where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUser_id());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getNo());
			r = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}
	
	private void close() {
		try {
			if(rs != null)  rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

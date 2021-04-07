package co.REturnMarket.admin.user.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.REturnMarket.admin.common.DAO;
import co.REturnMarket.admin.user.vo.UserVo;

public class UserDAO extends DAO{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		super();
	}
	
	// 사용자 조회
	public ArrayList<UserVo> selectUser() {
		ArrayList<UserVo> voList = null;
		UserVo vo = null;
		String sql = "select * from user";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				voList = new ArrayList<UserVo>();
				
				do {
					vo = new UserVo();
					
					vo.setUser_id(rs.getString("user_id"));
					vo.setPw(rs.getString("pw"));
					vo.setRe_pw(rs.getString("re_pw"));
					vo.setName(rs.getString("name"));
					vo.setPhone(rs.getString("phone"));
					vo.setEmail(rs.getString("email"));
					vo.setGrade(rs.getString("grade"));
					vo.setAddrNum(rs.getInt("addrNum"));
					vo.setAddr1(rs.getString("addr1"));
					vo.setAddr2(rs.getString("addr2"));
					vo.setEmailChk(rs.getBoolean("emailChk"));
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
	
	// 이용자 정보 가져오기
	public UserVo getUserInfo(String user_id) {
		UserVo vo = null;
		String sql = "select * from user where user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVo();
				vo.setUser_id(rs.getString("user_id"));
				vo.setPw(rs.getString("pw"));
				vo.setRe_pw(rs.getString("re_pw"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));
				vo.setGrade(rs.getString("grade"));
				vo.setAddrNum(rs.getInt("addrNum"));
				vo.setAddr1(rs.getString("addr1"));
				vo.setAddr2(rs.getString("addr2"));
				vo.setEmailChk(rs.getBoolean("emailChk"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}
	
	// 이용자 id 검색
	public ArrayList<UserVo> searchUserId(String search) {
		ArrayList<UserVo> voList = null;
		UserVo vo = null;
		String sql = "select * from user where user_id like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" +search +"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				voList = new ArrayList<UserVo>();
				
				do {
					vo = new UserVo();
					vo.setUser_id(rs.getString("user_id"));
					vo.setPw(rs.getString("pw"));
					vo.setRe_pw(rs.getString("re_pw"));
					vo.setName(rs.getString("name"));
					vo.setPhone(rs.getString("phone"));
					vo.setEmail(rs.getString("email"));
					vo.setGrade(rs.getString("grade"));
					vo.setAddrNum(rs.getInt("addrNum"));
					vo.setAddr1(rs.getString("addr1"));
					vo.setAddr2(rs.getString("addr2"));
					vo.setEmailChk(rs.getBoolean("emailChk"));
					
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
	
	// 이용자 삭제(삭제시 올린 게시물 다 사라짐)
	public int deleteUser(String user_id) {
		int r = 0; // r = result
		String sql = "delete u, p, b, j, a, q, c from user as u left join pd as p on p.user_id = u.user_id" + 
				" left join bid as b on b.user_id = u.user_id" + 
				" left join jjim as j on j.user_id = b.user_id" + 
				" left join askdirect as a on a.user_id = j.user_id" + 
				" left join qna as q on q.user_id = a.user_id" + 
				" left join comment as c on c.user_id = q.user_id where u.user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			r = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}
	
	// 유저 정보 변경
	public int modiUser(UserVo vo) {
		int r = 0; // r = result
		String sql = "update user set name = ?, phone = ?, email = ?, grade = ?, addrNum = ?, addr1 = ?, addr2 = ?, emailChk = ? where user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getGrade());
			pstmt.setInt(5, vo.getAddrNum());
			pstmt.setString(6, vo.getAddr1());
			pstmt.setString(7, vo.getAddr2());
			pstmt.setBoolean(8, vo.isEmailChk());
			pstmt.setString(9, vo.getUser_id());
			r = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}
	
	// 이용자 비밀번호 변경
	public int updatePw(String user_id, String hashpw) {
		int r = 0; // r = result
		String sql = "update user set pw = ? where user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hashpw);
			pstmt.setString(2, user_id);
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

package co.REturnMarket.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.REturnMarket.admin.common.DAO;
import co.REturnMarket.admin.vo.AdminVo;

public class AdminDAO extends DAO {
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public AdminDAO() {
		super();
	}

	// 회원가입
	public int insertUser(AdminVo vo) {
		int r = 0; // r = result
		String sql = "insert into admin values(?,?,?,?,?,'admin',false)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setInt(4, vo.getPhone());
			pstmt.setString(5, vo.getEmail());
			r = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}

	// 로그인 정보 확인하기
	public int loginAdmin(String id, String hashpw) {
		String sql = "select pw, registration from admin where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(hashpw)) {
					if(rs.getBoolean("registration") == true) {
						return 1; // 로그인 성공
					} else {
						return 2; // 관리자 승인 필요
					}
				} else {
					return 0; // 비밀번호 불일치
				}
			} else {
				return -1; // id값 불일치
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return -2; // db오류
	}
	
	// 관리자 승인 없은 관리자 조회
	public ArrayList<AdminVo> getAdmins() {
		ArrayList<AdminVo> voList = null;
		AdminVo adminVo = null;
		String sql = "select * from admin where registration = false";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				voList = new ArrayList<AdminVo>();
				
				do {
				adminVo = new AdminVo();
				adminVo.setId(rs.getString("id"));
				adminVo.setPw(rs.getString("pw"));
				adminVo.setName(rs.getString("name"));
				adminVo.setPhone(rs.getInt("phone"));
				adminVo.setEmail(rs.getString("email"));
				adminVo.setGrade(rs.getString("grade"));
				adminVo.setRegistration(rs.getBoolean("registration"));
				voList.add(adminVo);
				} while(rs.next());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return voList;
	}
	
	// 관리자 승인 허가
	public int updateAdmin(String id) {
		int r = 0; // r = result
		String sql = "update admin set registration = true where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			r = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}
	
	// 관리자 승인 거부 및 삭제
	public int deleteAdmin(String id) {
		int r = 0;
		String sql = "delete from admin where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.UserBean;

public class UserDAO {
	private static UserDAO userDao;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static UserDAO getInstance() {
		if(userDao == null) {
			userDao = new UserDAO();
		}
		return userDao;
	}

	public void setConnection(Connection con) {
		this.con = con;		
	}

	// 회원가입
	public int join(UserBean userBean) throws Exception {
		int joinUser = 0;
		
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?,false)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userBean.getUser_id());
			pstmt.setString(2, userBean.getPw());
			pstmt.setString(3, userBean.getRe_pw());
			pstmt.setString(4, userBean.getName());
			pstmt.setString(5, userBean.getPhone());
			pstmt.setString(6, userBean.getEmail());
			pstmt.setString(7, userBean.getGrade());			
			pstmt.setInt(8, userBean.getAddrNum());
			pstmt.setString(9, userBean.getAddr1());
			pstmt.setString(10, userBean.getAddr2());
			
			joinUser = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return joinUser; // 회원가입 실패
	}

	// db의 유저 이메일 호출
	public String getUserEmail(String user_id) {
		String sql = "select email from user where user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return rs.getString(1); // 이메일 주소 가져오기
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) { rs.close(); } } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return null; // db error
	}
	
	// 이메일 체크 되었는지 확인
	public boolean getUserEmailChecked(String user_id) {
		String sql = "select emailChk from user where user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return rs.getBoolean(1); // 이메일 등록 여부 반환
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) { rs.close(); } } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		return false; // DB error
	}

	// 이메일 체크 된것을 확인후 변경해주는 것
	public boolean setUserEmailChk(String user_id) {
		String sql = "update user set emailChk = true where user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();
			
			return true; // 이메일 등록 설정 성공
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) { rs.close(); } } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return false; // emailchk 등록 설정 변경 실패
	}

	// 로그인
	public boolean login(String user_id, String hashpw) {
		String sql = "select pw from user where user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(hashpw)) {
					return true; // 비밀번호 일치
				} else {
					return false; // 비밀번호 불일치
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) { rs.close(); } } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return false; // db 오류
	}
	
	// 사용자 정보를 가져온다.
	public UserBean getUserInfo(String requester) {
		String sql = "select * from user where user_id = ?";
		UserBean userBean = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, requester);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userBean = new UserBean();
				userBean.setUser_id(requester);
				userBean.setPw(rs.getString("pw"));
				userBean.setRe_pw(rs.getString("re_pw"));
				userBean.setName(rs.getString("name"));
				userBean.setPhone(rs.getString("phone"));
				userBean.setEmail(rs.getString("email"));
				userBean.setGrade(rs.getString("grade"));
				userBean.setAddrNum(rs.getInt("addrNum"));
				userBean.setAddr1(rs.getString("addr1"));
				userBean.setAddr2(rs.getString("addr2"));
				userBean.setEmailChk(rs.getBoolean("emailChk"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) { rs.close(); } } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return userBean;
	}
	
	// 회원 정보 변경
	public int updateUserInfo(UserBean userBean) {
		int updateUserInfo = 0;
		String sql = "update user set pw = ?, re_pw = ?, name = ?, phone = ?, email = ?, addrNum = ?, addr1 = ?, addr2 = ? where user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userBean.getPw());
			pstmt.setString(2, userBean.getRe_pw());
			pstmt.setString(3, userBean.getName());
			pstmt.setString(4, userBean.getPhone());
			pstmt.setString(5, userBean.getEmail());
			pstmt.setInt(6, userBean.getAddrNum());
			pstmt.setString(7, userBean.getAddr1());
			pstmt.setString(8, userBean.getAddr2());
			pstmt.setString(9, userBean.getUser_id());
			updateUserInfo =  pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return updateUserInfo; // 수정 실패
	}
	
	// 회원 id 찾기
	public String getUserId(UserBean userBean) {
		String sql = "select user_id from user where name = ? and phone = ? and email = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userBean.getName());
			pstmt.setString(2, userBean.getPhone());
			pstmt.setString(3, userBean.getEmail());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1); // 아이디 가져오기
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) { rs.close(); } } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); } 
		}
		
		return null; // 아이디 가져오기 실패
	}
	
	// 비밀번호 변경을 위한 유저 정보 매칭
	public boolean matchingUserInfo(UserBean userBean) {
		String sql = "select * from user where user_id = ? and name = ? and phone = ? and email = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userBean.getUser_id());
			pstmt.setString(2, userBean.getName());
			pstmt.setString(3, userBean.getPhone());
			pstmt.setString(4, userBean.getEmail());
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 정보가 일치할경우 true값 반환
				return true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) { rs.close(); } } catch(Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); } 
		}
		
		return false; // 정보가 일치하지 않을 경우 false반환
	}
	
	// 사용자 비밀번호 변경
	public boolean changeUserPw(UserBean userBean) {
		String sql = "update user set pw = ?, re_pw = ? where user_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userBean.getPw());
			pstmt.setString(2, userBean.getRe_pw());
			pstmt.setString(3, userBean.getUser_id());
			pstmt.executeUpdate();
			
			return true; // 비밀번호 변경 성공
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); } 
		}
		
		return false; // 비밀번호 변경 실패
	}
	
	// 회원 탈퇴
	public boolean deleteUser(String user_id, String hashpw) {
		String pw_sql = "select user_id from user where user_id = ? and pw = ?"; 
		String sql = "delete u, p, b, j, a, q, c from user as u left join pd as p on p.user_id = u.user_id" + 
				" left join bid as b on b.user_id = u.user_id" + 
				" left join jjim as j on j.user_id = b.user_id" + 
				" left join askdirect as a on a.user_id = j.user_id" + 
				" left join qna as q on q.user_id = a.user_id" + 
				" left join comment as c on c.user_id = q.user_id where u.user_id = ?";
		
		try {
			pstmt = con.prepareStatement(pw_sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, hashpw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(user_id)) { // 아이디 값이 일치한다면 삭제 실행
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, user_id);
					pstmt.executeUpdate();
					
					return true; // 회원 탈퇴 성공
				} else {
					return false; //  회원 탈퇴 정보 불일치
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) { pstmt.close(); } } catch(Exception e) { e.printStackTrace(); }
		}
		
		return false; // 회원 탈퇴 실패
	}
}

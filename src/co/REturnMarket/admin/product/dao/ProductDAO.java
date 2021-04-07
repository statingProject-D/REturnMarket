package co.REturnMarket.admin.product.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.REturnMarket.admin.common.DAO;
import co.REturnMarket.admin.product.vo.ProductVo;

public class ProductDAO extends DAO{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ProductDAO() {
		super();
	}
	
	// 상품 전체 조회
	public ArrayList<ProductVo> selectProduct() {
		ArrayList<ProductVo> voList = null;
		ProductVo vo = null;
		String sql = "select * from pd";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				voList = new ArrayList<ProductVo>();

				do {
					vo = new ProductVo();
					
					vo.setPdNum(rs.getInt("pdNum"));
					vo.setUser_id(rs.getString("user_id"));
					vo.setPdName(rs.getString("pdName"));
					vo.setPdCategory(rs.getString("pdCategory"));
					vo.setPdImg(rs.getString("pdImg"));
					vo.setPdCount(rs.getInt("pdCount"));
					vo.setPdInfo(rs.getString("pdInfo"));
					vo.setPdGroup(rs.getString("pdGroup"));
					vo.setPdViewCount(rs.getInt("pdViewCount"));
					vo.setAuStartPrice(rs.getInt("auStartPrice"));
					vo.setAuUnit(rs.getInt("auUnit"));
					vo.setAuStartDay(rs.getString("auStartDay"));
					vo.setAuEndDay(rs.getString("auEndDay"));
					vo.setAuDirectPrice(rs.getInt("auDirectPrice"));
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

	// 상품명 검색
	public ArrayList<ProductVo> searchPdName(String search) {
		ArrayList<ProductVo> voList = null;
		ProductVo vo = null;
		String sql = "select * from pd where pdName like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" +search +"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				voList = new ArrayList<ProductVo>();
				
				do {
					vo = new ProductVo();
					vo.setPdNum(rs.getInt("pdNum"));
					vo.setUser_id(rs.getString("user_id"));
					vo.setPdName(rs.getString("pdName"));
					vo.setPdCategory(rs.getString("pdCategory"));
					vo.setPdImg(rs.getString("pdImg"));
					vo.setPdCount(rs.getInt("pdCount"));
					vo.setPdInfo(rs.getString("pdInfo"));
					vo.setPdGroup(rs.getString("pdGroup"));
					vo.setPdViewCount(rs.getInt("pdViewCount"));
					vo.setAuStartPrice(rs.getInt("auStartPrice"));
					vo.setAuUnit(rs.getInt("auUnit"));
					vo.setAuStartDay(rs.getString("auStartDay"));
					vo.setAuEndDay(rs.getString("auEndDay"));
					vo.setAuDirectPrice(rs.getInt("auDirectPrice"));
					
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
	
	// 상품 정보 불러오기
	public ProductVo getProduct(int pdNum) {
		ProductVo vo = null; // r = result
		String sql = "select * from pd where pdNum = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new ProductVo();
				vo.setPdNum(pdNum);
				vo.setUser_id(rs.getString("user_id"));
				vo.setPdName(rs.getString("pdName"));
				vo.setPdCategory(rs.getString("pdCategory"));
				vo.setPdImg(rs.getString("pdImg"));
				vo.setPdCount(rs.getInt("pdCount"));
				vo.setPdInfo(rs.getString("pdInfo"));
				vo.setPdGroup(rs.getString("pdGroup"));
				vo.setPdViewCount(rs.getInt("pdViewCount"));
				vo.setAuStartPrice(rs.getInt("auStartPrice"));
				vo.setAuUnit(rs.getInt("auUnit"));
				vo.setAuStartDay(rs.getString("auStartDay"));
				vo.setAuEndDay(rs.getString("auEndDay"));
				vo.setAuDirectPrice(rs.getInt("auDirectPrice"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	// 상품 삭제
	public int deleteProduct(int pdNum) {
		int r = 0; // r = result
		String sql = "delete p, b, j, a from pd as p left join bid as b on b.pdNum = p.pdNum" + 
				" left join jjim as j on j.pdNum = b.pdNum" + 
				" left join askDirect as a on b.pdNum = a.pdNum where p.pdNum = ?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdNum);
			r = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}
	
	// 관리자가 상품 수정
	public int updateProduct(ProductVo vo) {
		int r = 0; // r = result
		String sql = "update pd set pdName = ?, pdCategory = ?, pdImg = ?, pdCount = ?, pdInfo = ?, pdGroup = ?, auStartPrice = ?, auUnit = ?, auEndDay = ?, auDirectPrice = ? where pdNum = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPdName());
			pstmt.setString(2, vo.getPdCategory());
			pstmt.setString(3, vo.getPdImg());
			pstmt.setInt(4, vo.getPdCount());
			pstmt.setString(5, vo.getPdInfo());
			pstmt.setString(6, vo.getPdGroup());
			pstmt.setInt(7, vo.getAuStartPrice());
			pstmt.setInt(8, vo.getAuUnit());
			pstmt.setString(9, vo.getAuEndDay());
			pstmt.setInt(10, vo.getAuDirectPrice());
			pstmt.setInt(11, vo.getPdNum());
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

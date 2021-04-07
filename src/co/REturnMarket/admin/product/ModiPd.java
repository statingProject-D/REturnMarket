package co.REturnMarket.admin.product;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.REturnMarket.admin.common.Command;
import co.REturnMarket.admin.product.dao.ProductDAO;
import co.REturnMarket.admin.product.vo.ProductVo;

public class ModiPd implements Command {
	public String exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ServletContext context = request.getServletContext();
		String realFolder = "/upload"; // 파일 업로드될 서버 상의 물리적인 경로
		String saveFolder = "/upload";
		realFolder = context.getRealPath(saveFolder);
		int size = 10 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, realFolder, size, "UTF-8", new DefaultFileRenamePolicy());
		
		int pdNum = Integer.parseInt(request.getParameter("pdNum"));
		String newPdImg = multi.getFilesystemName("pdImg");
		String defaultPdImg = multi.getParameter("defaultPdImg");
		ProductDAO pdDao = new ProductDAO();
		ProductVo vo = new ProductVo();
		
		vo.setPdNum(pdNum);
		vo.setPdName(multi.getParameter("pdName"));
		vo.setPdCategory(multi.getParameter("pdCategory"));
		if(newPdImg != null) { // 새로운 이미지가 있다면 새로운 이미지로 재업로드
			vo.setPdImg(newPdImg);
		} else { // 이미지 업로드 하지않으면 기존 사용하던 이미지로
			vo.setPdImg(defaultPdImg);
		}
		vo.setPdCount(Integer.parseInt(multi.getParameter("pdCount")));
		vo.setPdInfo(multi.getParameter("pdInfo"));
		vo.setPdGroup(multi.getParameter("pdGroup"));
		vo.setAuStartPrice(Integer.parseInt(multi.getParameter("auStartPrice")));
		vo.setAuUnit(Integer.parseInt(multi.getParameter("auUnit")));
		vo.setAuEndDay(multi.getParameter("auEndDay"));
		vo.setAuDirectPrice(Integer.parseInt(multi.getParameter("auDirectPrice")));
		
		int updateSuccess = pdDao.updateProduct(vo);
		
		if(updateSuccess < 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('상품 수정에 실패하였습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
		return "productInfo.ad";
	}
}

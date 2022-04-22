package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.MemberVO;
 
@WebServlet("/idcheck")
public class C08_IdDupCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public C08_IdDupCheck() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request_Parameter
		String id = request.getParameter("id");
		MemberService service = new MemberService();
		MemberVO vo = new MemberVO();
		String uri = "/member/idDupCheck.jsp";
		
		// 2. Service
		// => selectOne 이용 : 전달된 ID 의 존재여부 확인
		// 	  -> notNull : 존재 -> 사용불가 
		//    -> Null    : 없음 -> 사용가능
		//    -> 그러므로 전달된 ID 보관해 view 에서 사용
		request.setAttribute("newID",id);
		vo.setId(id);
		if ( service.selectOne(vo) != null ) {
			// 사용불가
			request.setAttribute("idUse", "F");
		}else {
			// 사용가능
			request.setAttribute("idUse", "T");
		}
		// 3. View 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doGet 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

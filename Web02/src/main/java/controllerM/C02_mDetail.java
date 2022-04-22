package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.MemberVO;

@WebServlet("/mdetail")
public class C02_mDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C02_mDetail() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
    	// => Service 준비
		MemberService service = new MemberService();
		MemberVO vo = new MemberVO();
		String uri = "/member/memberDetail.jsp";
		String message = null;
		String id = null;
		// => 요청구분 :  내정보 ,  mlist의 a Tag 에 따라 id 처리
		if ( request.getParameter("id")!=null ) {
			id = request.getParameter("id");
		}else {
			HttpSession session = request.getSession(false);
			if ( session!=null && session.getAttribute("loginID")!=null ) {
				id =(String)session.getAttribute("loginID");
			}
		}
		// 2. Service 처리
		vo.setId(id);
		vo=service.selectOne(vo);
		
		if (vo != null) {
			request.setAttribute("apple", vo);
			if ( "U".equals(request.getParameter("jcode")) ) uri="/member/updateForm.jsp";
			// "jcode" 가 없는경우 NullPointerException 발생
			// => 예방차원에서 위처럼 비교
		}else {
			message="~~ "+id+"님의 자료는 존재하지 않습니다 ~~";
			request.setAttribute("message", message); 
		}	
		// 3. 결과(View -> forward) 처리 
    	request.getRequestDispatcher(uri).forward(request, response);
	
	} //doGet
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

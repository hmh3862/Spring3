package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.MemberVO;
 
@WebServlet("/login")
public class C03_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public C03_Login() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request_Parameter
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberService service = new MemberService();
		MemberVO vo = new MemberVO();
		String uri = "/member/loginForm.jsp";
		
		// 2. Service
		// => selectOne 이용
		//    -> id, password 오류 구분 가능
		//	  -> DAO, Service 에 메서드 추가하지 않아도 됨. 
		//    -> password 암호화 적용된 경우에도 비교 가능함.
		vo.setId(id);
		vo = service.selectOne(vo);
		if ( vo != null ) { 
			// ID 는 일치 -> Password 확인
			if ( vo.getPassword().equals(password) ) {
				// Login 성공 -> login 정보 session에 보관, home
				request.getSession().setAttribute("loginID", id);
				request.getSession().setAttribute("loginName", vo.getName());
				uri="/index.jsp" ;
			}else {
				// Password 오류
				request.setAttribute("message", "~~ Password 오류,  다시 하세요 ~~");
			}
		}else {	// ID 오류
			request.setAttribute("message", "~~ ID 오류,  다시 하세요 ~~");
		} //else
		// 3. View 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doGet 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

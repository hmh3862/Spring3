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

@WebServlet("/mdelete")
public class C07_mDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C07_mDelete() {
        super();
    }
	// ** Member Delete : 회원탈퇴
	// => member delete, session 무효화
	// => 삭제 성공 -> index.jsp,
	//    삭제 실패 -> memberDetail.jsp
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
    	// => Service 준비
		MemberService service = new MemberService();
		MemberVO vo = new MemberVO();
		String uri = "/index.jsp";
		String message = null;
		String id = null;
		
		// 2. Service 처리
		// 로그인 정보에서 ID 가져오기
		// => 관리자기능 추가
		//    loginID == 'admin' && request.getParameter("id") != null 
		//    -> 관리자가 member 를 삭제하는중
		HttpSession session = request.getSession(false);
		if ( session!=null && session.getAttribute("loginID")!=null ) {
			id =(String)session.getAttribute("loginID");
			// ** 삭제 가능 : 관리자의 작업인지 확인
			if (id.equals("admin") && request.getParameter("id")!=null)
				vo.setId(request.getParameter("id"));
			else vo.setId(id);
			
			if ( service.delete(vo)>0 ) {
				// 삭제 성공 : 성공 message, index.jsp, session무효화
				if (!id.equals("admin")) {
					session.invalidate();
					message = "~~ 탈퇴 성공 : 1개월후 재가입 가능합니다. ~~" ;
				}else {
					uri = "/mlist"; // 관리자 작업인 경우 
					message = "~~ member 삭제 성공 !!! ~~" ;
				}
			}else {
				// 삭제 오류 -> mdetail -> memberDetail.jsp 
				message = "~~ 탈퇴 오류 : 다시 하세요. ~~" ;
				uri="/mdetail";
			}
		}else { // session is null -> loginForm.jsp
			message = "~~ 로그인 정보 없음 : 로그인 후 탈퇴 가능 합니다. ~~" ;
			uri="/member/loginForm.jsp" ;
		}
		// 3. 결과(View -> forward) 처리 
		request.setAttribute("message", message);
    	request.getRequestDispatcher(uri).forward(request, response);
	
	} //doGet
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

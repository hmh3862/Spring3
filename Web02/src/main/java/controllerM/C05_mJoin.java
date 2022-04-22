package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.MemberVO;
 
@WebServlet("/join")
public class C05_mJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public C05_mJoin() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => 필요한 객체 인스턴스 정의
		// => request_Parameter
		//    한글처리 (utf-8 인 경우 get 방식은 안해도 되지만, post 방식은 반드시 해야함_getParameter 하기전에 ) 
		// => Parameter -> vo에 set
		MemberService service = new MemberService();
		MemberVO vo = new MemberVO();
		String uri = "/member/loginForm.jsp";
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String lev = request.getParameter("lev");
		String birthd = request.getParameter("birthd");
		int point = Integer.parseInt(request.getParameter("point"));
		double weight = Double.parseDouble(request.getParameter("weight"));
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(name);
		vo.setLev(lev);
		vo.setBirthd(birthd);
		vo.setPoint(point);
		vo.setWeight(weight);
		
		// 2. Service
		// => service, DAO에 insert 메서드 추가 
		// => 성공후 -> 로그인 유도 : loginForm.jsp
		//    실패후 -> 재가입 유도 : joinForm.jsp
		 if ( service.insert(vo) > 0 ) {
			 // insert 성공
			 request.setAttribute("message", "~~ 회원가입 완료!!, 로그인 후 이용하세요 ~~");
		 }else { 
			 // insert 실패
			 request.setAttribute("message", "~~ 회원가입 실패!!, 다시 하세요 ~~");
			 uri="/member/joinForm.jsp";
		 }
		// 3. View 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doGet 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.MemberVO;
 
@WebServlet("/mupdate")
public class C06_mUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public C06_mUpdate() {
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
		String uri = null;
		
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
		// => service, DAO에 update 메서드 추가 
		// => 성공후 
		//		-> 수정된 정보확인 : memberList.jsp (출력가능하도록 요청)
		//		-> name 수정한 경우도 있을수있으므로 session 에 보관중인 loginName도 변경
		//    실패후 -> 다시 수정하기 : 수정가능한 폼 출력
		 if ( service.update(vo) > 0 ) {
			 // update 성공
			 request.setAttribute("message", "~~ 회원정보 수정 완료 !!!  ~~");
			 request.getSession().setAttribute("loginName", vo.getName());
			 uri = "/mlist";
		 }else { 
			 // update 실패 : 수정가능한 폼 출력할수있도록 요청 
			 request.setAttribute("message", "~~ 회원정보 수정 실패!!, 다시 하세요 ~~");
			 uri="/mdetail?jcode=U";
		 }
		// 3. View 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doGet 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

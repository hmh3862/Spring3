package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class C04_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C04_Logout() {
        super();
    }
    // ** Logout
    // => session 삭제(무효화)후 index 로 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// ** session 인스턴스 정의 후 삭제하기
    	// => 매개변수: 없거나, true, false
    	// => false : session 이 없을때 null 을 return;
    	HttpSession session = request.getSession(false);
    	if (session!=null) session.invalidate();
    	String uri="/index.jsp";
    	request.setAttribute("message", "~~ 로그아웃 되었습니다 ~~");
    	request.getRequestDispatcher(uri).forward(request, response);
    } // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

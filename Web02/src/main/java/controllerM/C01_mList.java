package controllerM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.MemberVO;

@WebServlet("/mlist")
public class C01_mList extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public C01_mList() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 1. 요청분석
    	// => Service 준비
    	MemberService service = new MemberService();
    	List<MemberVO> list = new ArrayList<MemberVO>();
    	String uri = "/member/memberList.jsp";
    	
    	// 2. Service 처리
    	list = service.selectList();
    	if ( list!=null ) {
    		request.setAttribute("banana", list);
    	}else {
    		request.setAttribute("message", "~~ 출력 자료가 없습니다 ~~");
    	}
    	// 3. 결과(View -> forward) 처리 
    	request.getRequestDispatcher(uri).forward(request, response);
    } //doGet 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

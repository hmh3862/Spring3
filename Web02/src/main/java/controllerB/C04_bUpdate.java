package controllerB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.BoardVO;

@WebServlet("/bupdate")
public class C04_bUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public C04_bUpdate() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 1. 요청분석
    	// => Service 준비
    	// => request Parameter 처리 ( post 방식 전송 -> 한글처리 )
    	BoardService service = new BoardService();
    	BoardVO vo = new BoardVO();
    	String uri = "/blist";
    	
    	request.setCharacterEncoding("utf-8");
    	vo.setSeq(Integer.parseInt(request.getParameter("seq")));
    	vo.setTitle(request.getParameter("title"));
    	vo.setContent(request.getParameter("content"));
    	
    	// 2. Service 처리
    	if ( service.update(vo) > 0 ) { 
    		// 글수정 성공 -> blist
    		request.setAttribute("message", "~~ 글수정 성공 !!! ~~");
    	}else {
    		request.setAttribute("message", "~~ 글수정 실패 !!! ~~");
    		uri = "/bdetail?jcode=U&seq="+vo.getSeq();
    	}
    	// 3. 결과(View -> forward) 처리 
    	request.getRequestDispatcher(uri).forward(request, response);
    } //doGet 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

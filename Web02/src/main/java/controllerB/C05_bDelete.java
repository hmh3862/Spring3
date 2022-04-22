package controllerB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.BoardVO;

@WebServlet("/bdelete")
public class C05_bDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public C05_bDelete() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 1. 요청분석
    	// => Service 준비
    	// => request Parameter 처리
    	BoardService service = new BoardService();
    	BoardVO vo = new BoardVO();
    	String uri = "/blist";
    	vo.setSeq(Integer.parseInt(request.getParameter("seq")));
    	
    	// 2. Service 처리
    	if ( service.delete(vo) > 0 ) { 
    		// 글삭제 성공 -> blist
    		request.setAttribute("message", "~~ 글삭제 성공 !!! ~~");
    	}else {
    		request.setAttribute("message", "~~ 글삭제 실패 !!! ~~");
    		uri = "/bdetail?seq="+vo.getSeq();
    	}
    	// 3. 결과(View -> forward) 처리 
    	request.getRequestDispatcher(uri).forward(request, response);
    } //doGet 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

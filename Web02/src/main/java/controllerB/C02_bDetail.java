package controllerB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.BoardVO;

@WebServlet("/bdetail")
public class C02_bDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public C02_bDetail() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 1. 요청분석
    	// => Service 준비
    	BoardService service = new BoardService();
    	BoardVO vo = new BoardVO();
    	String uri = "/board/boardDetail.jsp";
    	
    	// 2. Service 처리
    	// => 조회수 증가 추가하기
    	//    글보는이와 글쓴이가 다를때, 
    	//    수정화면 요청이 아닌경우_ 글보는이와 글쓴이가 같은 경우, 그러나 admin 이 수정하는 경우는 예외
    	// => 조회수 증가의 조건
    	//    글보는이(loginID)와 글쓴이가 다를때 && jcode!="U" 
    	// => DAO, Service 에 countUp 메서드 추가 
    	
    	vo.setSeq(Integer.parseInt(request.getParameter("seq")));
    	vo = service.selectOne(vo);
    	if ( vo!=null ) {
    		// 조회수 증가 추가
    		String loginID = (String)request.getSession().getAttribute("loginID") ;
    		if ( !vo.getId().equals(loginID) &&    
    			 !"U".equals(request.getParameter("jcode")) ) {
    			// 조회수 증가
    			if ( service.countUp(vo) > 0 )
    					vo.setCnt(vo.getCnt()+1) ;
    		} //if
    		
    		request.setAttribute("apple", vo);
    		// 글 수정인지 확인
    		if ( "U".equals(request.getParameter("jcode")) ) 
    			uri = "/board/bupdateForm.jsp";
    	}else {
    		request.setAttribute("message", "~~ 글번호에 해당하는 자료가 없습니다 ~~");
    	}
    	// 3. 결과(View -> forward) 처리 
    	request.getRequestDispatcher(uri).forward(request, response);
    } //doGet 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} //class

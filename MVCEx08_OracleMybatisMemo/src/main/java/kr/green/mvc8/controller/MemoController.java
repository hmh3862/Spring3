package kr.green.mvc8.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.green.mvc8.service.MemoService;
import kr.green.mvc8.vo.CommVO;
import kr.green.mvc8.vo.MemoVO;
import kr.green.mvc8.vo.PagingVO;

@Controller
public class MemoController {

	@Autowired
	private MemoService memoService;
	
	// 목록보기
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list")
	public String selectList(@ModelAttribute CommVO commVO, Model model, HttpServletRequest request){
		// -------------------------------------------------------------------------------------------
		// POST로 리다이렉트 전송이 있을 경우 받는 방법
		// RequestContextUtils.getInputFlashMap(request)로 맵이 존재하는지 판단해서
		// 있으면 POST처리를 하고 없으면 GET으로 받아서 처리를 한다.
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap!=null) {
			Map<String, String> map = (Map<String, String>) flashMap.get("map");
			commVO.setCurrentPage(Integer.parseInt(map.get("p")));
			commVO.setPageSize(Integer.parseInt(map.get("s")));
			commVO.setBlockSize(Integer.parseInt(map.get("b")));
		}
		// -------------------------------------------------------------------------------------------
		PagingVO<MemoVO> pagingVO = memoService.selectList(commVO.getCurrentPage(), commVO.getPageSize(), commVO.getBlockSize());
		model.addAttribute("pv", pagingVO);
		return "list";
	}
	// 저장
	// 수정
	// 삭제
	/*
	@RequestMapping(value = "/updateOk")
	public String updateOk(@ModelAttribute CommVO commVO,@ModelAttribute MemoVO memoVO){
		case 1:
		switch (commVO.getMode()) {
			memoService.insert(memoVO);
			break;
		case 2:
			memoService.update(memoVO);
			break;
		case 3:
			memoService.delete(memoVO);
			break;
		}
		return "redirect:/list?p=" + commVO.getCurrentPage() + "&s=" + commVO.getPageSize() + "&b=" + commVO.getBlockSize(); // GET방식의 전송이 된다.
	}
	*/
	
	// 리다이렉트시 POST 전송하기 : 파라메터에 RedirectAttributes를 추가한다.
	@RequestMapping(value = "/updateOk")
	public String updateOk(@ModelAttribute CommVO commVO,@ModelAttribute MemoVO memoVO, RedirectAttributes redirectAttributes){
		switch (commVO.getMode()) {
		case 1:
			memoService.insert(memoVO);
			commVO.setCurrentPage(1); // 새글 쓸때는 1페이지로 이동 
			break;
		case 2:
			memoService.update(memoVO);
			break;
		case 3:
			memoService.delete(memoVO);
			break;
		}
		
		// Redirect시 POST전송 하려면 map에 넣어서 RedirectAttributes에 담아서 전송하면 된다.
		Map<String, String> map = new HashMap<>();
		map.put("p", commVO.getCurrentPage()+"");
		map.put("s", commVO.getPageSize()+"");
		map.put("b", commVO.getBlockSize()+"");
		// System.out.println(map);
		redirectAttributes.addFlashAttribute("map", map);
		
		return "redirect:/list"; // 이렇게 하면 POST전송이 된다.
	}

}

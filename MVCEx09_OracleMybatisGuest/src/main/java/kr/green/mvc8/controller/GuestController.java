package kr.green.mvc8.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.mvc8.service.GuestService;
import kr.green.mvc8.vo.GuestVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	// 목록보기
	@RequestMapping(value = "/list")
	public String selectList(Model model) {
		List<GuestVO> list = guestService.selectList();
		model.addAttribute("list", list);
		model.addAttribute("newLine", "\n");
		model.addAttribute("br", "<br/>");
		return "list";
	}
	
	// 원본글 저장
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertGet(@ModelAttribute GuestVO guestVO) {
		return "redirect:/list";
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertPost(@ModelAttribute GuestVO guestVO, HttpServletRequest request) {
		guestVO.setIp(request.getRemoteAddr()); // ip저장
		guestService.insert(guestVO);
		return "redirect:/list";
	}
	// 댓글 저장
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String replyGet(@ModelAttribute GuestVO guestVO) {
		log.info("replyGet 호출");
		return "redirect:/list";
	}
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String replyPost(@ModelAttribute GuestVO guestVO, HttpServletRequest request) {
		log.info("replyPost 호출 : " + guestVO);
		guestVO.setIp(request.getRemoteAddr()); // ip저장
		guestService.reply(guestVO);
		return "redirect:/list";
	}
	// 글수정
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGet(@ModelAttribute GuestVO guestVO) {
		log.info("updateGet 호출");
		return "redirect:/list";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePost(@ModelAttribute GuestVO guestVO, HttpServletRequest request) {
		log.info("updatePost 호출 : " + guestVO);
		guestVO.setIp(request.getRemoteAddr()); // ip저장
		guestService.update(guestVO);
		return "redirect:/list";
	}

	// 글삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGet(@ModelAttribute GuestVO guestVO) {
		log.info("deleteGet 호출");
		return "redirect:/list";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePost(@ModelAttribute GuestVO guestVO) {
		log.info("deletePost 호출 : " + guestVO);
		guestService.delete(guestVO);
		return "redirect:/list";
	}
	
	
}

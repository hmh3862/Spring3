package kr.green.mvc6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.green.mvc6.service.StudentService;
import kr.green.mvc6.vo.StudentVO;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	// 보기
	@RequestMapping(value = "/list")
	public String selectAll(Model model) {
		model.addAttribute("list", studentService.selectAll());
		return "list";
	}
	// 저장하기
	// 수정하기
	@RequestMapping(value = "/updateOk", method = RequestMethod.GET)
	public String updateOkGet() {
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/updateOk", method = RequestMethod.POST)
	public String updateOk(@ModelAttribute StudentVO studentVO, @RequestParam(defaultValue = "1", required = false) int m) {
		switch (m) {
		case 1:
			studentService.insert(studentVO);
			break;
		case 2:
			studentService.update(studentVO);
			break;
		}
		return "redirect:/list";
	}
	// 삭제하기
	@RequestMapping(value = "/delete")
	public String delete(@ModelAttribute StudentVO studentVO) {
		if(studentVO.getIdx()>0) {
			studentService.delete(studentVO);
		}
		return "redirect:/list";
	}
}

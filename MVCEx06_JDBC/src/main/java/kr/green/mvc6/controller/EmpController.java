package kr.green.mvc6.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.green.mvc6.service.EmpService;
import kr.green.mvc6.vo.EmpVO;

@Controller
public class EmpController {

	@Autowired
	private EmpService empService;
	
	@RequestMapping(value = "/list")
	public String selectList(Model model) {
		try {
			model.addAttribute("totalCount", empService.selectCount());
			model.addAttribute("list", empService.selectAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	@RequestMapping(value = "/updateOk", method = RequestMethod.GET)
	public String insertGet(@ModelAttribute EmpVO empVO) {
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/updateOk", method = RequestMethod.POST)
	public String insertPost(@ModelAttribute EmpVO empVO, @RequestParam(required = false, defaultValue = "1") Integer mode) {
		try {
			if(mode==1) {
				empService.insert(empVO);
			}else {
				empService.update(empVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/list";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam int idx) {
		try {
			empService.delete(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/list";
	}
}

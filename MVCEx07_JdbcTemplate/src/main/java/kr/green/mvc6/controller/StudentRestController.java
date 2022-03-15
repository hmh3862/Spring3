package kr.green.mvc6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.green.mvc6.service.StudentService;
import kr.green.mvc6.vo.StudentList;
import kr.green.mvc6.vo.StudentVO;

@RestController
public class StudentRestController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/rest/selectByIdx.json", produces = {MediaType.APPLICATION_JSON_VALUE}) 
	public StudentVO selectByIdxJSON(@RequestParam(defaultValue = "1", required = false) int idx) {
		return studentService.selectByIdx(idx);
	}
	@RequestMapping(value = "/rest/selectByIdx.xml", produces = {MediaType.APPLICATION_XML_VALUE}) 
	public StudentVO selectByIdxXML(@RequestParam(defaultValue = "1", required = false) int idx) {
		return studentService.selectByIdx(idx);
	}

	@RequestMapping(value = "/rest/select.json", produces = {MediaType.APPLICATION_JSON_VALUE}) 
	public List<StudentVO> selectAllJSON() {
		return studentService.selectAll();
	}
	@RequestMapping(value = "/rest/select.xml", produces = {MediaType.APPLICATION_XML_VALUE}) 
	public StudentList selectAllJXML() {
		StudentList list = new StudentList();
		list.setStudentList(studentService.selectAll());
		return list;
	}
}

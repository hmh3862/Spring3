package kr.green.mvc8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.green.mvc8.dao.MemoDAO;
import kr.green.mvc8.service.MemoService;
import kr.green.mvc8.vo.CommVO;
import kr.green.mvc8.vo.MemoVO;
import kr.green.mvc8.vo.PagingVO;

@RestController
public class MemoRestController {

	@Autowired
	private MemoService memoService;
	@Autowired
	private MemoDAO     memoDAO;
	
	@RequestMapping(value = "/rest/list")
	public PagingVO<MemoVO> selectList(
			@RequestParam(defaultValue = "1", required = false) int p,
			@RequestParam(defaultValue = "10", required = false) int s,
			@RequestParam(defaultValue = "10", required = false) int b
			){
		return memoService.selectList(p, s, b);
	}
	
	@RequestMapping(value = "/rest/list2")
	public PagingVO<MemoVO> selectList2(@ModelAttribute CommVO commVO){
		return memoService.selectList(commVO.getCurrentPage(), commVO.getPageSize(), commVO.getBlockSize());
	}
	
	@RequestMapping(value = "/rest/select")
	public MemoVO selectByIdx(@RequestParam(defaultValue = "1", required = false) int idx){
		return memoDAO.selectByIdx(idx);
	}
	
}

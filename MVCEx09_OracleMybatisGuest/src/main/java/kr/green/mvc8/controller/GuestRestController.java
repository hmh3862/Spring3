package kr.green.mvc8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.green.mvc8.dao.GuestDAO;
import kr.green.mvc8.vo.GuestVO;
import kr.green.mvc8.vo.GuestbooktList;

@RestController
public class GuestRestController {
	
	@Autowired
	private GuestDAO guestDAO;
	
	@RequestMapping(value = "/guestbook.json", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<GuestVO> selectListJson(){
		return guestDAO.selectList();
	}

	@RequestMapping(value = "/guestbook.xml", produces = {MediaType.APPLICATION_XML_VALUE})
	public GuestbooktList selectListXml(){
		GuestbooktList list = new GuestbooktList();
		list.setGuestList(guestDAO.selectList());
		return list;
	}
	
}

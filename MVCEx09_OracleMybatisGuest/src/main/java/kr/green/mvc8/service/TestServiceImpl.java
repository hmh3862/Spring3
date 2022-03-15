package kr.green.mvc8.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.mvc8.dao.TestDAO;

@Service("testService")
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestDAO testDAO;

	@Override
	public Date selectToday() {
		return testDAO.selectToday();
	}
}

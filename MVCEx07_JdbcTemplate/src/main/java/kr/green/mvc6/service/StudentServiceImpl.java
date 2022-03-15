package kr.green.mvc6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.mvc6.dao.StudentDAO;
import kr.green.mvc6.vo.StudentVO;

@Service("studentService")
public class StudentServiceImpl  implements StudentService{
	
	@Autowired
	private StudentDAO studentDAO;

	@Override
	public StudentVO selectByIdx(int idx) {
		return studentDAO.selectByIdx(idx);
	}

	@Override
	public List<StudentVO> selectAll() {
		return studentDAO.selectAll();
	}

	@Override
	public boolean insert(StudentVO studentVO) {
		return studentDAO.insert(studentVO);
	}

	@Override
	public boolean update(StudentVO studentVO) {
		return studentDAO.update(studentVO);
	}

	@Override
	public boolean delete(StudentVO studentVO) {
		return studentDAO.delete(studentVO);
	}
}

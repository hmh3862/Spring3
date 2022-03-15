package kr.green.mvc6.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.mvc6.dao.EmpDAO;
import kr.green.mvc6.vo.EmpVO;

@Service
public class EmpService {

	@Autowired
	private EmpDAO empDAO;

	// 1. 개수 얻기
	public int selectCount() throws SQLException {
		return empDAO.selectCount();
	}
	// 2. 1개 얻기
	public EmpVO selectByIdx(int idx) throws SQLException {
		return empDAO.selectByIdx(idx);
	}
	// 3. 모두 얻기
	public List<EmpVO> selectAll() throws SQLException {
		return empDAO.selectAll();
	}
	// 4. 저장하기
	public int insert(EmpVO empVO) throws SQLException {
		return empDAO.insert(empVO);
	}
	// 5. 수정하기
	public int update(EmpVO empVO) throws SQLException {
		return empDAO.update(empVO);
	}
	// 6. 삭제하기
	public int delete(int idx) throws SQLException {
		return empDAO.delete(idx);
	}
}

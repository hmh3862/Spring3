package kr.green.mvc6.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.green.mvc6.vo.StudentMapper;
import kr.green.mvc6.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

@Repository("studentDAO")
@Slf4j
public class StudentDAOImple implements StudentDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public StudentVO selectByIdx(int idx) {
		log.info("{}의 selectByIdx 호출 : {}", this.getClass().getName(), idx);
		StudentVO studentVO = null;

		studentVO = jdbcTemplate.queryForObject("select * from student where idx=?", new Object[] {idx}, new StudentMapper());
		
		log.info("{}의 selectByIdx 호출 결과 : {}", this.getClass().getName(), studentVO);
		return studentVO;
	}

	@Override
	public List<StudentVO> selectAll() {
		return jdbcTemplate.query("select * from student order by idx desc", new StudentMapper());
	}

	@Override
	public boolean insert(StudentVO studentVO) {
		return jdbcTemplate.update("insert into student values (student_idx_seq.nextval,?,?)", studentVO.getName(), studentVO.getSection()) > 0;
	}

	@Override
	public boolean update(StudentVO studentVO) {
		return jdbcTemplate.update("update student set name=?, section=? where idx=?", studentVO.getName(), studentVO.getSection(), studentVO.getIdx()) > 0;
	}

	@Override
	public boolean delete(StudentVO studentVO) {
		return jdbcTemplate.update("delete from student where idx=?", studentVO.getIdx()) > 0;
	}
}

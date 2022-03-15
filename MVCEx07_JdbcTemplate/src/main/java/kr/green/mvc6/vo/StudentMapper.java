package kr.green.mvc6.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

// 테이블의 구조와 VO를 연결시켜주는 클래스
public class StudentMapper implements RowMapper<StudentVO>{

	@Override
	public StudentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에서 데이터를 읽어 자바 객체에 넣 리턴해 준다.
		StudentVO studentVO = new StudentVO();
		studentVO.setIdx(rs.getInt("idx"));
		studentVO.setName(rs.getString("name"));
		studentVO.setSection(rs.getString("section"));
		return studentVO;
	}

}

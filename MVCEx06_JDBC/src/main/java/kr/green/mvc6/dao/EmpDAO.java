package kr.green.mvc6.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.green.mvc6.vo.EmpVO;

@Repository
public class EmpDAO {

	@Autowired
	private DataSource dataSource;
	
	// 1. 개수 얻기
	public int selectCount() throws SQLException {
		int count = 0;
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from emp");
		rs.next();
		count = rs.getInt(1);
		rs.close();
		stmt.close();
		return count;
	}
	// 2. 1개 얻기
	public EmpVO selectByIdx(int idx) throws SQLException {
		EmpVO empVO = null;
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from emp where idx=" + idx);
		if(rs.next()) {
			empVO = new EmpVO();
			empVO.setIdx(rs.getInt("idx"));
			empVO.setName(rs.getString("name"));
			empVO.setRole(rs.getString("role"));
		}
		rs.close();
		stmt.close();
		return empVO;
	}
	// 3. 모두 얻기
	public List<EmpVO> selectAll() throws SQLException {
		List<EmpVO> list = null;
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from emp order by idx desc");
		if(rs.next()) {
			list = new ArrayList<>();
			do {
				EmpVO empVO = new EmpVO();
				empVO.setIdx(rs.getInt("idx"));
				empVO.setName(rs.getString("name"));
				empVO.setRole(rs.getString("role"));
				list.add(empVO);
			}while(rs.next());
		}
		rs.close();
		stmt.close();
		return list;
	}
	// 4. 저장하기
	public int insert(EmpVO empVO) throws SQLException {
		int count = 0;
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		count = stmt.executeUpdate("insert into emp values (emp_id_seq.nextval, '" +empVO.getName() + "','"+ empVO.getRole() +"')");
		return count;
	}
	// 5. 수정하기
	public int update(EmpVO empVO) throws SQLException {
		int count = 0;
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		count = stmt.executeUpdate("update emp set name= '" +empVO.getName() + "', role='"+ empVO.getRole() +"' where idx = " + empVO.getIdx());
		return count;
	}
	// 6. 삭제하기	
	public int delete(int idx) throws SQLException {
		int count = 0;
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		count = stmt.executeUpdate("delete from emp where idx= " + idx);
		return count;
	}
}

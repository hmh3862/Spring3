package kr.green.mvc6.service;

import java.util.List;

import kr.green.mvc6.vo.StudentVO;

public interface StudentService {
	StudentVO selectByIdx(int idx); // 1개얻기
	List<StudentVO> selectAll(); // 모두 얻기
	boolean insert(StudentVO studentVO); // 저장
	boolean update(StudentVO studentVO); // 수정
	boolean delete(StudentVO studentVO); // 삭제
}

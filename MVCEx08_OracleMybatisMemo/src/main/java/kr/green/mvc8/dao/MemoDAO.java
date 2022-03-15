package kr.green.mvc8.dao;

import java.util.HashMap;
import java.util.List;

import kr.green.mvc8.vo.MemoVO;

public interface MemoDAO {
	// <!-- 1. 개수얻기 -->
	int selectCount();
	// <!-- 2. 1개 얻기 -->
	MemoVO selectByIdx(int idx);
	// <!-- 3. 1페이지 얻기 -->
	List<MemoVO> selectList(HashMap<String, Integer> map);
	// <!-- 4. 저장 -->
	void insert(MemoVO memoVO);
	// <!-- 5. 수정 -->
	void update(MemoVO memoVO);
	// <!-- 6. 삭제 -->
	void delete(int idx);
}

package kr.green.mvc8.service;

import kr.green.mvc8.vo.MemoVO;
import kr.green.mvc8.vo.PagingVO;

public interface MemoService {
	// 1. 목록보기
	PagingVO<MemoVO> selectList(int currentPage, int pageSize, int blockSize);
	
	// 2. 저장하기
	void insert(MemoVO memoVO);
	// 3. 수정하기
	void update(MemoVO memoVO);
	// 4. 삭제하기
	void delete(MemoVO memoVO);
}

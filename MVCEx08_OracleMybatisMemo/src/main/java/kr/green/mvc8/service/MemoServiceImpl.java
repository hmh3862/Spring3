package kr.green.mvc8.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.mvc8.dao.MemoDAO;
import kr.green.mvc8.vo.MemoVO;
import kr.green.mvc8.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("memoService")
public class MemoServiceImpl implements MemoService {
	@Autowired
	private MemoDAO memoDAO;

	@Override
	public PagingVO<MemoVO> selectList(int currentPage, int pageSize, int blockSize) {
		log.info("selectList 호출 : {}", currentPage + ", " + pageSize + ", " + blockSize);
		PagingVO<MemoVO> pagingVO = null;

		int totalCount = memoDAO.selectCount();
		pagingVO = new PagingVO<>(currentPage, pageSize, blockSize, totalCount);
		if (totalCount > 0) {
			HashMap<String, Integer> map = new HashMap<>();
			map.put("startNo", pagingVO.getStartNo());
			map.put("endNo", pagingVO.getEndNo());
			List<MemoVO> list = memoDAO.selectList(map);
			pagingVO.setList(list);
		}
		log.info("selectList 리턴 : {}", pagingVO);
		return pagingVO;
	}

	@Override
	public void insert(MemoVO memoVO) {
		log.info("insert 호출 : {}", memoVO);
		if (memoVO != null)
			memoDAO.insert(memoVO);
	}

	@Override
	public void update(MemoVO memoVO) {
		log.info("update 호출 : {}", memoVO);
		if (memoVO != null) {
			MemoVO dbVO = memoDAO.selectByIdx(memoVO.getIdx());
			if (dbVO != null && dbVO.getPassword().equals(memoVO.getPassword())) {
				memoDAO.update(memoVO);
			}
		}
	}

	@Override
	public void delete(MemoVO memoVO) {
		log.info("delete 호출 : {}", memoVO);
		if (memoVO != null) {
			MemoVO dbVO = memoDAO.selectByIdx(memoVO.getIdx());
			if (dbVO != null && dbVO.getPassword().equals(memoVO.getPassword())) {
				memoDAO.delete(memoVO.getIdx());
			}
		}
	}
}

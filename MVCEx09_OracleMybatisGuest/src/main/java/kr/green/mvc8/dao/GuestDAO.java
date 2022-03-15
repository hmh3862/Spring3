package kr.green.mvc8.dao;

import java.util.HashMap;
import java.util.List;

import kr.green.mvc8.vo.GuestVO;

public interface GuestDAO {
	List<GuestVO> selectList();
	GuestVO       selectByIdx(int idx);
	void          update(GuestVO guestVO);
	void          updateSeq(HashMap<String, Integer> map);
	void          reply(GuestVO guestVO);
	void          insert(GuestVO guestVO);
	// 삭제를 위한 쿼리
	void          delete1(int idx); // Y비교하고
	void          delete2(int idx); // Y비교 않하고
	List<GuestVO> selectSeqList(HashMap<String, Integer> map);
	void          updateDel(int idx);
	List<GuestVO> selectDelList();
}

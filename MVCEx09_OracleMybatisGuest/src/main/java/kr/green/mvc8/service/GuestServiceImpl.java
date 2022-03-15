package kr.green.mvc8.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.mvc8.dao.GuestDAO;
import kr.green.mvc8.vo.GuestVO;

@Service("guestService")
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDAO guestDAO;

	@Override // 모두 얻기
	public List<GuestVO> selectList() {
		return guestDAO.selectList();
	}

	@Override // 원본글 저장
	public void insert(GuestVO guestVO) {
		if(guestVO!=null) guestDAO.insert(guestVO);
	}

	@Override // 답변 저장
	public void reply(GuestVO guestVO) {
		if(guestVO!=null) {
			// ref가 같으면서 나보다 seq가 큰값 들을 모두 seq값을 1씩 증가시킨다.
			HashMap<String, Integer> map = new HashMap<>();
			map.put("ref", guestVO.getRef());
			map.put("seq", guestVO.getSeq());
			guestDAO.updateSeq(map);
			// ref는 그대로
			// seq는 +1
			guestVO.setSeq(guestVO.getSeq()+1);
			// lev는 +1
			guestVO.setLev(guestVO.getLev()+1);
			
			// 댓글 저장
			guestDAO.reply(guestVO);
		}
	}

	@Override
	public void update(GuestVO guestVO) {
		if(guestVO!=null) {
			// DB에서 해당 글번호의 글 읽어온다.
			GuestVO dbVO = guestDAO.selectByIdx(guestVO.getIdx()); 
			// 글이 있으면서 비번이 같으면 수정한다.
			if(dbVO!=null && dbVO.getPassword().equals(guestVO.getPassword())) {
				guestDAO.update(guestVO);
			}
		}
	}

	@Override
	public void delete(GuestVO guestVO) {
		if(guestVO!=null) {
			// DB에서 해당 글번호의 글 읽어온다.
			GuestVO dbVO = guestDAO.selectByIdx(guestVO.getIdx()); 
			// 글이 있으면서 비번이 같으면 삭제한다.
			if(dbVO!=null && dbVO.getPassword().equals(guestVO.getPassword())) {
				// 자식이 있으면 삭제표시만하고 자식이 없으면 지운다.
				HashMap<String, Integer> map = new HashMap<>();
				map.put("ref", dbVO.getRef());
				map.put("seq", dbVO.getSeq());
				List<GuestVO> list = guestDAO.selectSeqList(map); // ref가 같으면서 seq가 크거나 같은것들 가져오기
				
				int childCount = 0; // 자식의 개수를 구한다.
				if(list!=null) {
					int lev = list.get(0).getLev(); // 0번의 레벨값
					for(int i=1;i<list.size();i++) {
						if(lev>=list.get(i).getLev()) break; // 같은 레벨이 나온면 종료
						childCount++; // 자식의 개수 증가
					}
				}
				// 자식이 있으면 삭제표시만하고 자식이 없으면 지운다.
				if(childCount==0) {
					guestDAO.delete2(guestVO.getIdx()); // 지우기 === 이 때는 삭제표시가 바뀌지 않은 상태 'N'에서 삭제
				}else {
					guestDAO.updateDel(guestVO.getIdx()); // 삭제 표시
				}
				// 전체글을 반복하면서 del값이 'Y'이면서 자식이 없는 항목은 완전 삭제를 해줘야 한다.
				List<GuestVO> delList = guestDAO.selectDelList(); // 삭제표시된 목록
				if(delList!=null) {
					for(GuestVO vo : delList) { // 반복
						// 반복하는 요소의 자식의 개수
						HashMap<String, Integer> map2 = new HashMap<>();
						map2.put("ref", vo.getRef());
						map2.put("seq", vo.getSeq());
						List<GuestVO> list2  = guestDAO.selectSeqList(map2);
						int count = 0;
						if(list2!=null) {
							int lev = list2.get(0).getLev();
							for(int i=1;i<list2.size();i++) {
								if(lev>=list2.get(i).getLev()) break;
								count++;
							}
						}
						if(count==0) { // 삭제 표시가 있으면서 자식이 없다면 
							guestDAO.delete1(vo.getIdx()); // 이미 삭제표시가 'Y'인 상태에서 삭제 쿼리 조건에 del='Y'를 포함
						} // end if
					}// end for
				}// end if
			}//end if
		} // end if
	} // end method
	
	
}

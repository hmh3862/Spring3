package aop03;

import java.util.Random;

//** Aop 구현
//1 단계 : 핵심적 관심사항 과  공통적 관심사항 분리
//=> 핵심적 관심사항만 구현
//=> 공통적 관심사항(Aspect) 분리 : 별도의 클래스로 분리 -> MyAspect.java

public class Girl implements Person {

	@Override
	public void doStudying() throws Exception {
		System.out.println("~~ 회원관리를 만듭니다 ~~ => 핵심적 관심사항");
		// if (new Random().nextBoolean()) {
		if (1==2)	// 항상 false -> 무조건 성공
			// 실패 => Exception 발생
			throw new Exception("~~ 홀랑 다 날렸다 ㅠㅠㅠ !!! ~~ => 예외발생");
	} //doStudying
} //class
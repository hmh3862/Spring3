package iocDI01_xml;

// ** Test1. 절차지향
class SsTV {
	public void  turnOn() { System.out.println("~~ SsTV turnOn ~~"); }
	public void  turnOff() { System.out.println("~~ SsTV turnOff ~~"); }
	public void  soundUp() { System.out.println("~~ SsTV soundUp ~~"); }
	public void  soundDown() { System.out.println("~~ SsTV soundDown ~~"); }
} //SsTV
class LgTV {
	public void  powerOn() { System.out.println("~~ LgTV powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTV powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ LgTV volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ LgTV volumeDown ~~"); }
} //LgTV

// ** Test2. 객체지향 : 다형성적용
interface TV {
	void  powerOn();	// abstract public 은 생략가능
	void  powerOff();
	void  volumeUp();
	void  volumeDown();
}
class SsTVi implements TV {
	public SsTVi() { System.out.println("~~ SsTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVi powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ SsTVi volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ SsTVi volumeDown ~~"); }
} //SsTVi

class LgTVi implements TV {
	public LgTVi() { System.out.println("~~ LgTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ LgTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTVi powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ LgTVi volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ LgTVi volumeDown ~~"); }
} //LgTVi

// Test3. Factory 패턴적용 (IOC/DI 입문)
class BeanFactory {
	public TV getBean(String tv) {
		if ( tv.equals("ss")) return new SsTVi();
		else return new LgTVi();
	}
} // BeanFactory


public class TVUser01 {

	public static void main(String[] args) {
		// ** Test1. 절차지향
		// => tv 교체가 필요 : 완전 재코딩
		System.out.println("** Test1. 절차지향 **");
//		SsTV tv = new SsTV();
//		tv.turnOn();
//		tv.soundUp();
//		tv.soundDown();
//		tv.turnOff();
		
		// => LgTV 로 교체 (완전 재코딩)
		LgTV tv = new LgTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		// ** Test2. 객체지향 : 다형성적용
	    // => 관련성이 없는 두객체를 하나의 인터페이스로 묶어줌 
	    // => tv 교체가 필요
	    //    우측의 클래스만 교체 
		// => 단, 소스코드의 수정이 필요함
		System.out.println("** Test2. 객체지향 : 다형성적용 **");
		TV tvi = new LgTVi();
		tvi.powerOn();
		tvi.volumeUp();
		tvi.volumeDown();
		tvi.powerOff();
		
	    // Test3. Factory 패턴적용 (IOC/DI 입문)
	    // => 실행시에 코드 수정없이 클래스(TV) 교체
	    // => user 의 요구사항(필요한 클래스) Factory에 전달하는 방법
	    // => xml, @, JavaCode (JavaConfig)
		System.out.println("** Test3. Factory 패턴적용 (IOC/DI 입문) **");
		BeanFactory bf = new BeanFactory(); // 실시간으로 소스코드 수정없이 요청 전달
		TV tvf = bf.getBean(args[0]);
		if ( tvf!=null ) {
			tvf.powerOn();
			tvf.volumeUp();
			tvf.volumeDown();
			tvf.powerOff();
		}else System.out.println("** TV 선택 오류 **");
		
	} // main
} // class

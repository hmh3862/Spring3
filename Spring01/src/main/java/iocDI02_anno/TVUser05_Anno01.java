package iocDI02_anno;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Annotation, @, 애노테이션
// => 컴파일러에게 알려줌
// => Bean 생성 @ -> @Component
//    Java : @Component
//    Spring : @Controller, @Service, @Repository

interface TV {
	void  powerOn();	// abstract public 은 생략가능
	void  powerOff();
	void  volumeUp();
	void  volumeDown();
}

@Component("tv")
class SsTVi implements TV {
	public SsTVi() { System.out.println("~~ SsTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVi powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ SsTVi volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ SsTVi volumeDown ~~"); }
} //SsTVi

@Component
class LgTVi implements TV {
	public LgTVi() { System.out.println("~~ LgTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ LgTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTVi powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ LgTVi volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ LgTVi volumeDown ~~"); }
} //LgTVi

public class TVUser05_Anno01 {

	public static void main(String[] args) {
	    // 1. 스프링 컨테이너 구동(생성)
	    AbstractApplicationContext sc = new  
	       GenericXmlApplicationContext("iocDI02_anno/app05.xml");
	   
	    // 2. 필요한 객체를 전달받고 서비스 실행 
	    TV tv = (TV)sc.getBean("tv"); 
	    tv.powerOn();
	    tv.volumeUp();
	    tv.volumeDown();
	    tv.powerOff();
	     
	    sc.close();
	} //main

} //class

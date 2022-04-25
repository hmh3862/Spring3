package aop03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// ** 순수 Java
		//Person programmerB = new Boy();
		//Person programmerG = new Girl();
		
		// ** IOC/DI 적용: 스프링 컨테이너를 통해 주입받기
		AbstractApplicationContext sc = 
				new GenericXmlApplicationContext("aop03.xml");
		Person programmerB = (Person)sc.getBean("boy");
		Person programmerG = (Person)sc.getBean("girl");
		
		try {
			System.out.println("\n** Girl Test **");
			programmerB.doStudying();
			System.out.println("\n** Boy Test **");
			programmerG.doStudying();
		} catch (Exception e) {
			System.out.println("\n** main Exception => "+e.toString());
		}
		
		sc.close();
	}//main

} //class

package iocDI01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** 스프링이 제공하는 BeanFactory
//=> 스프링 컨테이너
//=> AbstractApplicationContext 와 GenericXmlApplicationContext 계층도 
//=> Object -> DefaultResourceLoader (C) -> AbstractApplicationContext (A) 
//-> GenericApplicationContext (C) -> GenericXmlApplicationContext (C) 

//public abstract class AbstractApplicationContext extends DefaultResourceLoader
//		implements ConfigurableApplicationContext, DisposableBean {....

//public class GenericXmlApplicationContext extends GenericApplicationContext {...

//=> 컨테이너는 xml (설정화일), @, JavaCode (JavaConfig) 등의 
//   전달사항을 통해 요구하는 클래스를 생성, 보관, 제공

//** xml (설정화일)


public class TVUser02 {

	public static void main(String[] args) {
		// 1. 콩공장(BeanFactory) 생성
		// => 스프링 컨테이너 구동(생성)
		AbstractApplicationContext sc = new 
				GenericXmlApplicationContext("iocDI01_xml/app02.xml");
		
		// 2. 객체를 전달받고 실행
		TV tv = (TV)sc.getBean("tv");
		if ( tv!=null ) {
			tv.powerOn();
			tv.volumeUp();
			tv.volumeDown();
			tv.powerOff();
		}else System.out.println("** TV 선택 오류 **");
		
		// 3. SingleTon Test
		// => 스프링 프레임웤의 모든 작업은 싱글톤을 기본으로함.
		// => 싱글톤 (한개의 인스턴스만 허용 하는것) 적용 Test
		TV tvl = (TV)sc.getBean("tv");
		TV tvs1 = (TV)sc.getBean("tvs");
		TV tvs2 = (TV)sc.getBean("tvs");
		System.out.println("** SingleTon Test **");
		System.out.println("** prototype tv => "+tv);
		System.out.println("** prototype tvl => "+tvl);
		System.out.println("** singleton tvs1 => "+tvs1);
		System.out.println("** singleton tvs2 => "+tvs2);

	} //main

} //class

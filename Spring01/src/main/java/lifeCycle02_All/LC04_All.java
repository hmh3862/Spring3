package lifeCycle02_All;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** Bean(객체) 의 LifeCycle 
//=> 객체생성 -> 사용 -> 소멸
//=> 해당되는 특정시점에 자동실행 : xml, @, interface

//** Test4. ALL
//=> 실행순서: @ -> interface -> xml

class LifeCycleTest implements InitializingBean, DisposableBean {
	
	public LifeCycleTest() { System.out.println("~~ LifeCycleTest_All 생성자 ~~"); }
	
	// 1. xml 적용 메서드
	public void begin() { System.out.println("~~ LifeCycleTest_xml begin() ~~"); }
	public void end() { System.out.println("~~ LifeCycleTest_xml end() ~~"); }
	
	// 2. @, Annotation
	@PostConstruct
	public void beginAnno() { System.out.println("~~ LifeCycleTest @PostConstruct ~~"); }
	@PreDestroy
	public void endAnno() { System.out.println("~~ LifeCycleTest @PreDestroy ~~"); }
	
	// 3. interface
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("~~ LifeCycleTestI afterPropertiesSet() ~~");		
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("~~ LifeCycleTestI destroy() ~~");
	}

	public void list() { System.out.println("~~ LifeCycleTest list() ~~"); }
	public void login() { System.out.println("~~ LifeCycleTest login() ~~"); }
} //LifeCycleTest

public class LC04_All {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("lifeCycle02_All/lc_All.xml");
		LifeCycleTest lc = (LifeCycleTest)sc.getBean("lc");
		lc.login();
		lc.list();
		sc.close();
	} //main

} //class

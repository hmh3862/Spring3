package lifeCycle01;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Test2. Annotation, @
//=> @PostConstruct , @PreDestroy
//=> 실행 순서
//   생성자 -> @PostConstruct (자동호출) -> 사용...  
//          -> 컨테이너 Close -> @PreDestroy (자동호출)

@Component("lca")
class LifeCycleTestA {
	public LifeCycleTestA() { System.out.println("~~ LifeCycleTest 생성자 ~~"); }
	
	@PostConstruct
	public void begin() { System.out.println("~~ LifeCycleTest @PostConstruct ~~"); }
	@PreDestroy
	public void end() { System.out.println("~~ LifeCycleTest @PreDestroy ~~"); }
	public void list() { System.out.println("~~ LifeCycleTest list() ~~"); }
	public void login() { System.out.println("~~ LifeCycleTest login() ~~"); }
} //LifeCycleTest

public class LC02_anno {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new
	            GenericXmlApplicationContext("lifeCycle01/lc01.xml");
	    LifeCycleTestA lc = (LifeCycleTestA)sc.getBean("lc");
	    lc.login();
	    lc.list();
	    sc.close();

	} //main

} //class

package lifeCycle01;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("lci")
class LifeCycleTestI implements InitializingBean, DisposableBean {
	public LifeCycleTestI() { System.out.println("~~ LifeCycleTest 생성자 ~~"); }
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("~~ LifeCycleTest @PostConstruct ~~");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("~~ LifeCycleTest @PreDestroy ~~");
	}
	public void list() { System.out.println("~~ LifeCycleTest list() ~~"); }
	public void login() { System.out.println("~~ LifeCycleTest login() ~~"); }
} //LifeCycleTest

public class LC03_interface {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new
	            GenericXmlApplicationContext("lifeCycle01/lc01.xml");
	    LifeCycleTestI lc = (LifeCycleTestI)sc.getBean("lci");
	    lc.login();
	    lc.list();
	    sc.close();

	}

}

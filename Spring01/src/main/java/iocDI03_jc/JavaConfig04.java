package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//** Test4) xml 을 통해서 JC 사용
//=> xml 에서 JC 화일을 import 적용 

@Configuration
public class JavaConfig04 {
	@Bean
	public TV tvl() {
		return new LgTVsi(spb(), "Yellow", 11229000) ;
	}
	
	@Bean // => User 클래스에서 사용한다면 지정해야함(
	public Speakeri spb() { return new SpeakerB() ;	}
	
	@Bean
	public TV tva() { return new AiTVsi(); }
}

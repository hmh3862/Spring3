package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java bean configuration class를 이용한 DI
//=> Test03 : 스피커 2개중 선택 
//=> 생성자 를 이용한 주입..

//*** JC 와 @ 병행사용
//*** JC , @, xml 병행사용
//=> JC 내에서 xml 로 생성된 객체를 직접 사용하는것은 허용 되지 않음. 
// 단, User 클래스에서는 사용가능

//** 실습
//=> SsTVsi , Speaker 는 xml 로 생성
//=> LgTVsi, AiTVsi 는 JC 의 @Bean 으로 생성
//=> LgTVsi (Speaker 생성자주입) 
// AiTVsi (Speaker @Autowired)   

@ImportResource("iocDI03_jc/jcapp03.xml")
@Configuration
public class JavaConfig03 {
	@Bean
	public TV tvl() {
		return new LgTVsi(spb(), "Yellow", 11229000) ;
	}
	
	@Bean // => User 클래스에서 사용한다면 지정해야함
	public Speakeri spb() { return new SpeakerB() ;	}
	
	@Bean
	public TV tva() { return new AiTVsi(); }
}

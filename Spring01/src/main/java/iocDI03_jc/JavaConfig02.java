package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java bean configuration class를 이용한 DI
//=> Test02 : 스피커 1개 사용 TV 
//=> 생성자 를 이용한 주입 & JC에서 xml 병행사용

//** JC 에서 xml 병행 사용하기 
//=> @ImportResource("iocDI03_jc/jcapp02.xml")
//=> AiTVs 생성은 xml 로 

@ImportResource("iocDI03_jc/jcapp02.xml")
@Configuration
public class JavaConfig02 {
	// 1) 고전적 방법
	@Bean
	// => 생성된 Bean을 컨테이너에 전달하기 위한 경우에만 사용
	public TV tvs() { return new SsTVs(); }
	
	// 2) IOC/DI : 생성자 주입
	@Bean
	// public TV tvl() { return new LgTVs(new Speaker(), "Blue", 1122000); }
	public TV tvl() { return new LgTVs(sp(), "Blue", 1122000); }
	
	// => Speaker 생성
	public Speaker sp() { return new Speaker(); }
	
    // 3) xml 병행사용 Test
    // => xml 로 생성
	
} //class

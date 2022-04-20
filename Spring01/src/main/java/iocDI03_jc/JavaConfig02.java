package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig02 {
	@Bean
	public TV tvs() { return new SsTVs(); }
	
	@Bean
	public TV tvl() { return new LgTVs(new Speaker(), "Blue", 1122000); }
	
	
} //class

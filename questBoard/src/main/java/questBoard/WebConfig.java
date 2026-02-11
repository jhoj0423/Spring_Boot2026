package questBoard;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	// addResourceHandlers : 정적 리소스(HTML,CSS,JS등) 을 관리하는 메소드이다
	// 외부의 물리적인 경롤를 웹에서 사용하는 URL 주소로 메핑하는 설정을 담당한다.
	
	
	// file:///c:/upload/ => 파일이 저장되는 물리적인 경로이다. -> 수정가능
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**")
				.addResourceLocations("file:///c:/upload/");
	}
}
package zipFile.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("zipFile."))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
	}
	
	private ApiInfo apiInfo() {
        return new ApiInfo(
                "BRE CLOUD APIs",
                "Describes all backend API used in CBPAS client.",
                "1.0",
                "Terms of service",
                new Contact("Prashant Yadu", "www.quintiles.com", "Prashant.Yadu@quintiles.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}

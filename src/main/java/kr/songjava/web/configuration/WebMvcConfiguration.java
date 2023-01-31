package kr.songjava.web.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.songjava.web.error.CustomErrorAttributes;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		return jsonView;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = 
			new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:config/messages/message");
		source.setDefaultEncoding("UTF-8");
		source.setDefaultLocale(Locale.ENGLISH);
		return source;
	}
	
	@Bean
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean factory =
			new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource());
		return factory;
	}
	
	@Bean
	public CustomErrorAttributes customErrorAttributes() {
		return new CustomErrorAttributes();
	}
	
}

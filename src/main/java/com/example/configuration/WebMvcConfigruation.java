package com.example.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.error.ExampleErrorAttributes;

@Configuration
@EnableAspectJAutoProxy
public class WebMvcConfigruation implements WebMvcConfigurer {

	/**
	 * 다국어 처리 메세지 소스 빈으로 등록
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = 
			new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:config/messages/message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * Validator 빈 등록
	 */
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	
	/**
	 * 에러 메세지 커스 사용 빈 등록
	 * @return
	 */
	@Bean
	public ExampleErrorAttributes errorAttributes() {
		return new ExampleErrorAttributes();
	}
}

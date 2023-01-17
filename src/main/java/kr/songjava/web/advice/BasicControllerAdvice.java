package kr.songjava.web.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.songjava.web.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class BasicControllerAdvice {
	
	private final MappingJackson2JsonView jsonView;

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleRuntimeException(RuntimeException e, HttpServletRequest request) {
		log.error("handleRuntimeException", e);
		String requested = request.getHeader("X-Requested-With");
	
		if (requested != null && requested.equals("XMLHttpRequest")) {
			//ModelAndView model = new ModelAndView(jsonView);
			ModelAndView model = new ModelAndView("jsonView", HttpStatus.BAD_REQUEST);
			model.addObject("message", e.getMessage());
			return model;
		}
		
		ModelAndView model = new ModelAndView("/error/message.html");
		model.addObject("message", e.getMessage());
		
		return model;
	}
	
	@ExceptionHandler(ApiException.class)
	public ModelAndView handleApiException(ApiException e, HttpServletRequest request) {
		log.error("handleApiException", e);
		ModelAndView model = new ModelAndView("/error/api.html");
		model.addObject("message", e.getMessage());
		return model;
	}
	
}

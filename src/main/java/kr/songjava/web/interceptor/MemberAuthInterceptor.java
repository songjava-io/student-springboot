package kr.songjava.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.songjava.web.exception.ApiException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberAuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("requestURI : {}", request.getRequestURI());
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			// 현재(URL에 맵핑된) 실행 될 컨트롤러에 메소드에 선언된 어노테이션을 가져옴
			RequestConfig config = method.getMethodAnnotation(RequestConfig.class);
			log.info("config : {}", config);
			if (config != null) {
				// 본인인증 체크가 필수라면...
				if (config.realnameCheck()) {
					// 세션에 저장된 본인인증 값을 가져옴
					Object realnameCheck = request.getSession().getAttribute("realnameCheck");
					// 위에 값이 object 이므로 boolean으로 변환
					boolean realname = realnameCheck == null ? false : (boolean) realnameCheck;
					// 본인인증이 안된경우
					if (!realname) {
						throw new ApiException("본인인증이 필요합니다.");
					}
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			// 현재(URL에 맵핑된) 실행 될 컨트롤러에 메소드에 선언된 어노테이션을 가져옴
			RequestConfig config = method.getMethodAnnotation(RequestConfig.class);
			if (config != null) {
				modelAndView.addObject("menu", config.menu());
			}
		}		
	}
	
	
}

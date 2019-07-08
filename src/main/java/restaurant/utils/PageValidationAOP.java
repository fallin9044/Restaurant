package restaurant.utils;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class PageValidationAOP {
	// shiro
	private static final String registerPage = "redirect:register";

	@Pointcut("execution(* restaurant.controller.*.*.*(..))")
	public void waiterControllerJointPointExpression() {
	}

	@Around("waiterControllerJointPointExpression()")
	public Object pageAroundMethod(ProceedingJoinPoint pjd) {

		Object result = null;
		System.out.println("page环绕通知");
		Object[] args = pjd.getArgs();
		HttpSession session = (HttpSession) args[0];
		try {
			if (!WebUtils.pageValidate(session)) {
				throw new PageValidationException();
			}
			result = pjd.proceed();
		} catch (PageValidationException e) {
			System.out.println("page异常通知");
			result = registerPage;
		} catch (Throwable e) {
		}

		return result;
	}
}

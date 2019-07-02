package restaurant.utils;

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

	@Pointcut("execution(* restaurant.controller.*.*.*(..))")
	public void waiterControllerJointPointExpression() {
	}

	@Around("waiterControllerJointPointExpression()")
	public Object pageAroundMethod(ProceedingJoinPoint pjd) {

		Object result = null;
		System.out.println("page环绕通知");
		try {
			WebUtils.pageValidate();
			System.out.println("page前置通知");
			result = pjd.proceed();
			System.out.println("page后置通知");
		} catch (PageValidationException e) {
			System.out.println("page异常通知");
			result = "validation";
		} catch (Throwable e) {
		}
		WebUtils.setSessionAttribute("name", "宋文翰");
		String name = (String) WebUtils.getSessionAttribute("name");
		System.out.println("name是"+name);

		System.out.println("page必定执行的通知");
		return result;
	}
}

package restaurant.utils;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class AuthorityAOP {

	private static final String waiterPage = "redirect:/waiter/tableStatus";
	private static final String managerPage = "redirect:/managerIndex";
	
	@Pointcut("execution(* restaurant.controller.managerController.*.*(..))")
	public void waiterControllerJointPointExpression() {
	}
	
	@Around("waiterControllerJointPointExpression()")
	public Object pageWaiterAroundMethod(ProceedingJoinPoint pjd) {

		Object result = null;
		System.out.println("WaiterPage环绕通知");
		Object[] args = pjd.getArgs();
		HttpSession session = (HttpSession) args[0];
		try {
			int authority = (int) session.getAttribute("authority");
			if (authority==2) {
				throw new PageValidationException();
			}
			result = pjd.proceed();
		} catch (PageValidationException e) {
			System.out.println("WaiterPage异常通知");
			result = waiterPage;
		} catch (Throwable e) {
		}

		return result;
	}
	
	@Pointcut("execution(* restaurant.controller.waiterController.*.*(..))")
	public void managerControllerJointPointExpression() {
	}
	
	@Around("managerControllerJointPointExpression()")
	public Object pageManagerAroundMethod(ProceedingJoinPoint pjd) {

		Object result = null;
		System.out.println("ManagerPage环绕通知");
		Object[] args = pjd.getArgs();
		HttpSession session = (HttpSession) args[0];
		try {
			int authority = (int) session.getAttribute("authority");
			if (authority==1) {
				throw new PageValidationException();
			}
			result = pjd.proceed();
		} catch (PageValidationException e) {
			System.out.println("ManagerPage异常通知");
			result = managerPage;
		} catch (Throwable e) {
		}

		return result;
	}
	
}

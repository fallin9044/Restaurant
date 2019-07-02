package restaurant.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtils {

	public static void pageValidate(){
		System.out.println("页面判断是否登陆");
		HttpSession httpSession = getSession();
		String name = (String) httpSession.getAttribute("name");
		if(name==null){
			throw new PageValidationException();
		}
	}
	
	public static Object getSessionAttribute(String name){
		System.out.println("获取session的某一项的值");
		HttpSession httpSession = getSession();
		return httpSession.getAttribute(name);
	}
	
	public static void setSessionAttribute(String name,Object value){
		System.out.println("设置session的某一项的值");
		HttpSession httpSession = getSession();
		httpSession.setAttribute(name, value);
	}
	
	public static HttpSession getSession() {
		System.out.println("获取session");
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}

	public static HttpServletRequest getRequest() {
		System.out.println("获取request");
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}

}

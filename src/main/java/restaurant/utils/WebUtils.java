package restaurant.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

public class WebUtils {

	public static boolean pageValidate() {
		System.out.println("页面判断是否登陆");
		HttpSession httpSession = getSession();
		Object personId = httpSession.getAttribute("personId");
		if (personId == null) {
			return false;
		}
		return true;
	}

	public static Object getSessionAttribute(String name) {
		System.out.println("获取session的某一项的值");
		HttpSession httpSession = getSession();
		return httpSession.getAttribute(name);
	}

	public static void setSessionAttribute(String name, Object value) {
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

	public static Object setModelAndView(String viewname, Map<String, Object> map) {
		ModelAndView mav = new ModelAndView(viewname);
		mav.addAllObjects(map);
		return mav;
	}

}

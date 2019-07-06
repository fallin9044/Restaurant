package restaurant.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

public class WebUtils {

	public static <T> void getObjectList(List<T> org, List<T> target,int start,int count){
		for(int i=start;i<start+count&&i<org.size();i++){
			target.add(org.get(i));
		}
	}
	
	public static int[] getPagingInfo(int start, int count, int total) {
		int[] info = new int[5];
		int next = start + count;
		int pre = start - count;
		int last;
		if (0 == total % count)
			last = total - count;
		else
			last = total - total % count;
		
		int pagecount = start / count + 1;
 		pre = pre < 0 ? 0 : pre;
		next = next > last ? last : next;
		info[0] = next;
		info[1] = pre;
		info[2] = last;
		info[3] = pagecount;
		info[4] = total/count;
		return info;
	}

	public static boolean pageValidate(HttpSession httpSession) {
		System.out.println("页面判断是否登陆");
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

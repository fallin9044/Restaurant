package restaurant.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import restaurant.entity.Reserves;

public class WebUtils {

	public static <T> void getObjectList(List<T> org, List<T> target, int start, int count) {
		for (int i = start; i < start + count && i < org.size(); i++) {
			target.add(org.get(i));
		}
	}

	public static void getFirstReserve(List<Reserves> org, List<Reserves> target) {
		Map<Long, Integer> tmp = new HashMap<>();
		Integer index = 0;
		for (Reserves res : org) {
			if (tmp.get(res.getTableId()) == null) {
				tmp.put(res.getTableId(), index);
			} else {
				Reserves temp = org.get(tmp.get(res.getTableId()));
				if (res.getReserveTime().before(temp.getReserveTime())) {
					tmp.replace(res.getTableId(), index);
				}
			}
			index++;
		}
		for (Long key : tmp.keySet()) {
			index = tmp.get(key);
			target.add(org.get(index));
		}
	}

	/**
	 * 对页码进行操作
	 * 
	 * @author swhan wychen
	 * @param start
	 *            默认0
	 * @param count
	 * @param total
	 * @return info[5]
	 */
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
		int tmp = total / count;
		if ((total - tmp * count) != 0) {
			tmp++;
		}
		info[4] = tmp;
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

	public static Object setModelAndView(String viewname, Map<String, Object> map) {
		ModelAndView mav = new ModelAndView(viewname);
		mav.addAllObjects(map);
		return mav;
	}

}

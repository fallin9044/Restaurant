package restaurant.controller.managerController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import restaurant.service.managerService.TableAndReserveService;

@Controller
public class TableAndReserveController {
	
	@Autowired
	TableAndReserveService managerService;

	private static final String manageTable = "manage_table";
	private static final String manageReserve = "manage_reserve";

	
	@RequestMapping(value = "/manage/table")
	public Object getTables(HttpSession session) {
		return managerService.loadTables(manageTable);
	}
	
	@RequestMapping(value = "/manage/reserveList")
	public Object getReserves(HttpSession session,@RequestParam(value="tableId") long tableId) {
		return managerService.loadReserves(manageReserve,tableId);
	}
	
	@RequestMapping(value = "/manage/searchresult")
	public Object getReserves(HttpSession session,@RequestParam(value="date") String time) {
		return managerService.loadSearchedTable(manageTable,time);
	}
	
	@RequestMapping(value = "manage/addReserve")
	@ResponseBody
	public Object addReserve(HttpSession session,@RequestParam(value="time")String time,
			@RequestParam(value="tele") String tele,
			@RequestParam(value="tableId") long tableId) {
		return managerService.isAbleReserve(time, tele, tableId);
	}
	
	@RequestMapping(value = "manage/deleteReserve")
	@ResponseBody
	public Object deleteReserve(HttpSession session,@RequestParam(value="id") long id) {
		return managerService.deleteReserve(id);
	}
	
	@RequestMapping(value = "manage/addTable")
	@ResponseBody
	public Object addTable(HttpSession session,@RequestParam(value="id") long id,
			@RequestParam(value="number") int number) {
		return managerService.addTable(id,number);
	}
	
	@RequestMapping(value = "manage/deleteTable")
	@ResponseBody
	public Object deleteTable(HttpSession session,@RequestParam(value="id") long id) {
		return managerService.deleteTable(id);
	}
}

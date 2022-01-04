package mc.sn.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mc.sn.admin.adminService;
import mc.sn.login.MemberVO;

@Controller("closedController")
public class closedController {
	@Autowired 
	private closedService service;
	
	
	@RequestMapping(value="/closed_write", method = RequestMethod.GET)
	public ModelAndView closed_write(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("closed_board/write");
		return mav;
	}
	
	
	/*
	 * @RequestMapping(value = "/closed_list", method = RequestMethod.GET) public
	 * ModelAndView memberList(HttpServletRequest request, HttpServletResponse
	 * response) throws Exception { ModelAndView mav = new ModelAndView();
	 * List<ClosedVO> closedList = new ArrayList<ClosedVO>();
	 * mav.setViewName("closed_board/list"); closedList = service.searchAll();
	 * mav.addObject("closedList",closedList); return mav; }
	 */
	
	@RequestMapping(value = "/closed_register", method = RequestMethod.POST)
	public void closed_register(@ModelAttribute("vo") ClosedVO vo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean flag = true;
		flag = service.alreadyExist(vo);	// 이미 있으면 false 없으면 true
		System.out.println(vo);
		System.out.println(flag);
		if(!flag) {
			response.getWriter().print(flag);
		} else {
			flag = service.register(vo);	
			response.getWriter().print(flag);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/closed_delete", method = RequestMethod.POST)
	public void closed_delete(@RequestParam("closedId")String closed_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean flag = service.closedDelete(closed_id);		
		response.getWriter().print(flag);	
	}
	
	@RequestMapping(value = "/closed_update", method = RequestMethod.GET)
	public ModelAndView closed_update(@RequestParam("id")String closedId,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("closed_board/update_form");
		ClosedVO vo = service.searchOneById(closedId);	
		mav.addObject("vo",vo);
		return mav;
	}
	
	@RequestMapping(value = "/closed_change", method = RequestMethod.POST)
	public void closed_change(@ModelAttribute("vo") ClosedVO vo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean flag = service.change(vo);	
		response.getWriter().print(flag);
	}
	
	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView myList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<ClosedVO> closedList = new ArrayList<ClosedVO>();
		mav.setViewName("closed_board/myList");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		closedList = service.searchById(id);		
		mav.addObject("closedList",closedList);
		return mav;		
	}
}

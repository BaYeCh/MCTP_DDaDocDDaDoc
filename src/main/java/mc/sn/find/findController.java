package mc.sn.find;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("findController")
public class findController {
	@Autowired
	private findService service;
	
	// 아이디 찾기 페이지
	@RequestMapping(value = "/findId", method=RequestMethod.POST)
	public void findId(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("birth") String birth,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = service.findId(name,email,birth);
		response.getWriter().print(result);	
		if(!result.equals("")) {	
			HttpSession session = request.getSession();
			session.setAttribute("find_result", "아이디 : "+result);
		}
	}
	
	// 비밀번호 찾기 페이지
	@RequestMapping(value = "/findPwd", method=RequestMethod.POST)
	public void findPwd(@RequestParam("id") String id, @RequestParam("email") String email, @RequestParam("birth") String birth,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String result = service.findPwd(id,email,birth);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);	
		if(!result.equals("")) {	
			HttpSession session = request.getSession();
			session.setAttribute("find_result", "비밀번호 : "+result);
		}
	}
	
	@RequestMapping(value = "/result", method=RequestMethod.GET)
	public ModelAndView result(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member_info/find/result");
		return mav;	
	}
}

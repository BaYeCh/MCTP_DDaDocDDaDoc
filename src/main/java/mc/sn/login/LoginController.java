package mc.sn.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller("loginController")
public class LoginController {

	@Autowired 
	private LoginService service;
	
	@ResponseBody
	@RequestMapping(value = "/login_check", method = RequestMethod.POST)
	public void check(@RequestParam("id") String id,
					  @RequestParam("pwd") String pwd,
		                       HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean flag = service.checkLogin(id, pwd);
		if(flag) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
		}
		response.getWriter().print(flag);			
	}
	
	@RequestMapping(value = "/login_form", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member_info/login/loginForm");
		return mav;		
	}
	
	@RequestMapping(value = "/findId_form", method=RequestMethod.GET)
	public String findId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String view = "member_info/find/findId";
		return view;		
	}
	
	@RequestMapping(value = "/findPwd_form", method=RequestMethod.GET)
	public String findPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String view = "member_info/find/findPwd";
		return view;		
	}
	
	@RequestMapping(value = "/join_form", method = RequestMethod.GET)
	public ModelAndView joinForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member_info/join/join");
		return mav;		
	}
	
	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	public ModelAndView mainPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mainPage");
		return mav;		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member_info/login/loginForm");
		return mav;		
	}
}



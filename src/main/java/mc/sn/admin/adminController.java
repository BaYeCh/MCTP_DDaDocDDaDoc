package mc.sn.admin;

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
import org.springframework.web.servlet.ModelAndView;

import mc.sn.login.MemberVO;

@Controller("adminController")
public class adminController {
	@Autowired 
	private adminService service;
	
	@RequestMapping(value = "/admin_form", method = RequestMethod.GET)
	public ModelAndView adminForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member_info/admin/adminForm");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		MemberVO vo = service.findById(id);		
		mav.addObject("vo",vo);
		return mav;		
	}
	
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public void change(@ModelAttribute("vo") MemberVO vo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean flag = service.change(vo);	
		System.out.println(vo);
		response.getWriter().print(flag);
	}
	
	@RequestMapping(value = "/member_list", method = RequestMethod.GET)
	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		mav.setViewName("member_info/admin/memberList");
		memberList = service.searchAll();		
		mav.addObject("memberList",memberList);
		return mav;		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView memberDelete(@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member_info/admin/memberList");
		boolean flag = service.memberDelete(id);		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		memberList = service.searchAll();		
		mav.addObject("memberList",memberList);
		return mav;		
	}
	
	@RequestMapping(value = "/updateToAdmin", method = RequestMethod.GET)
	public ModelAndView updateToAdmin(@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member_info/admin/memberList");
		boolean flag = service.updateToAdmin(id);		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		memberList = service.searchAll();		
		mav.addObject("memberList",memberList);
		return mav;			
	}
	
	@RequestMapping(value = "/updateToMember", method = RequestMethod.GET)
	public ModelAndView updateToMember(@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member_info/admin/memberList");
		boolean flag = service.updateToMember(id);	
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		memberList = service.searchAll();		
		mav.addObject("memberList",memberList);
		return mav;			
	}
	
}
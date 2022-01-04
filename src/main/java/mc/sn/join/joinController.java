package mc.sn.join;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mc.sn.login.MemberVO;

@Controller("joinController")
public class joinController {
	@Autowired
	private joinService service;
	
	@ResponseBody
	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
	public void addMember(@ModelAttribute("member") MemberVO member,
									HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println(member);
		boolean flag =  service.addMember(member);
		response.getWriter().print(flag);
		
	}
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public void checkMember(@RequestParam("id") String id,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag = service.findMemberId(id);
		response.getWriter().print(flag);
		//System.out.println(flag);
	}
	@ResponseBody
	@RequestMapping(value = "/emailCheck", method = RequestMethod.POST)
	public void checkMemberEmail(@RequestParam("email") String email,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag = service.findMemberEmail(email);
		response.getWriter().print(flag);
		//System.out.println(flag);
	}
	@ResponseBody
	@RequestMapping(value = "/nickNameCheck", method = RequestMethod.POST)
	public void checkMemberNickname(@RequestParam("nickname") String nickname,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag = service.findMemberSubName(nickname);
		response.getWriter().print(flag);
		//System.out.println(flag);
	}

}

package mc.sn.hospital;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

import com.google.gson.Gson;

@Controller("hospitalController")
public class hospitalController {
	@Autowired
	private hospitalService service;
	@Autowired
	private hospitalVO hospitalVO;

	// 맵 페이지
	@RequestMapping(value = "/map1", method = RequestMethod.GET)
	public ModelAndView map(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "hospital";
		mav.setViewName(url);
		return mav;
	}

	// 도로명 -> 위도, 경도
	@ResponseBody
	@RequestMapping(value = "/web02", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	public void getGeo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		String addr = req.getParameter("locale");
		res.setContentType("text/text; charset=utf-8");
		String result = service.geocode(addr);
		res.getWriter().print(result);
	}

	// hp_id -> 병원 정보
	@ResponseBody
	@RequestMapping(value = "/hospital", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	public void getHpid(@ModelAttribute("hosp") hospitalVO hosp,
			@RequestParam("time") String time,
			HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/text; charset=utf-8");
		ArrayList<hospitalVO> list = new ArrayList<hospitalVO>();
		
		String dates = time;
		String[] array = dates.split("T");
		String hp_diagTime = this.getTime(array[1]);	//시간
		hosp.setHp_diagTime(hp_diagTime);
		
		int week_day = this.getWeek(array[0]);	//요일
		hosp.setWeek_day(week_day);
		System.out.println(hosp);
		
		list = service.hp_id(hosp,array[0]);
		for(hospitalVO vo : list) {	//
			System.out.println(vo);
		}
		// System.out.println("controller "+result);
		String hospital = new Gson().toJson(list);
		System.out.println(hospital);
		res.getWriter().print(hospital);
	}
	
	@RequestMapping(value = "/hospital_detail", method = RequestMethod.GET)
	public ModelAndView map(@RequestParam("hpid") String hpid,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		hospitalVO2 vo = service.hosp_info(hpid);
		List<evalVO> evalList = service.eval(vo.getHpid());
		mav.setViewName("hospital-info");
		mav.addObject("vo",vo);
		mav.addObject("evalList", evalList);
		return mav;
	}
	
	public String getTime(String time) {
		String[] times = time.split(":");
		String hp_diagTime = times[0]+times[1];
		return hp_diagTime;
		
	}
	
	public int getWeek(String time) {
		String[] a = time.split("-");		
		// 1. LocalDate 생성
		LocalDate date = LocalDate.of( Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]));
		// 2. DayOfWeek 객체 구하기
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		// 3. 숫자 요일 구하기
		int week_day = dayOfWeek.getValue();
		return week_day;
	}
	
	@RequestMapping(value = "/pharmacy_detail", method = RequestMethod.GET)
	public ModelAndView map2(@RequestParam("hpid") String hpid,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		String url = "pharmacy-info";
		hospitalVO2 vo = service.pharm_info(hpid);
		List<evalVO> evalList = service.eval(vo.getHpid());
		mav.setViewName(url);
		mav.addObject("vo",vo);
		mav.addObject("evalList", evalList);
		return mav;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/pharmacy", method = RequestMethod.POST)
	public void getPhid(@ModelAttribute("hosp") hospitalVO hosp,
			@RequestParam("time") String time,
			HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/text; charset=utf-8");

		ArrayList<hospitalVO> list = new ArrayList<hospitalVO>();
		 
		String dates = time;
		String[] array = dates.split("T");
		String hp_diagTime = this.getTime(array[1]);	//시간
		hosp.setHp_diagTime(hp_diagTime);
		
		int week_day = this.getWeek(array[0]);	//요일
		hosp.setWeek_day(week_day);
		System.out.println(hosp);
		
		list = service.ph_id(hosp);
		for(hospitalVO vo : list) {	//
			System.out.println(vo);
		}
		// System.out.println("controller "+result);
		String hospital = new Gson().toJson(list);
		System.out.println(hospital);
		res.getWriter().print(hospital);
	}
	
	@RequestMapping(value = "/eval_form", method = RequestMethod.GET)
	public ModelAndView evalForm(@RequestParam("hosp_name") String hosp_name,
			@RequestParam("location") String location,
			@RequestParam("hpid") String hpid
			,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		String url = "evalForm";
		mav.setViewName(url);
		HttpSession session = request.getSession();
		String writer = (String) session.getAttribute("id");
		evalVO vo = new evalVO();
		vo.setHosp_name(hosp_name);
		vo.setLocation(location);
		vo.setWriter(writer);
		vo.setHpid(hpid);
		mav.addObject("vo",vo);
		return mav;
	}
	
	@RequestMapping(value = "/eval_form2", method = RequestMethod.GET)
	public ModelAndView evalForm2(@RequestParam("hosp_name") String hosp_name,
			@RequestParam("location") String location,
			@RequestParam("hpid") String hpid
			,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		String url = "evalForm2";
		mav.setViewName(url);
		HttpSession session = request.getSession();
		String writer = (String) session.getAttribute("id");
		evalVO vo = new evalVO();
		vo.setHosp_name(hosp_name);
		vo.setLocation(location);
		vo.setWriter(writer);
		vo.setHpid(hpid);
		mav.addObject("vo",vo);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/eval_register", method = RequestMethod.POST)
	public void evalForm(@ModelAttribute("vo") evalVO vo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		boolean flag = service.insertEval(vo);
		response.getWriter().print(flag);
	}
	
	
}

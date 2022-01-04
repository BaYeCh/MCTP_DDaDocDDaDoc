package mc.sn.hospital;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import mc.sn.closed.ClosedVO;
import mc.sn.closed.closedDAO;




@Service("hospitalService")
public class hospitalService {
	
	@Autowired
	private closedDAO dao;
	
	// 도로명 -> 위도, 경도
	public String geocode(String words) {
		// TODO Auto-generated method stub
		 StringBuffer res = null;
		 //naver cloud platform
		 String clientId = "h6n207rm9v";//애플리케이션 클라이언트 아이디값";
	     String clientSecret = "1rmNeRWace0fUWvzfFycPJNonXm1yBYIAPzGVAWP";//애플리케이션 클라이언트 시크릿값";
	     try {
	         String text = URLEncoder.encode(words, "UTF-8");
	         String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + text;
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	         con.setDoOutput(true);

	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { // 정상 호출
	             br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf8"));
	         } else {  // 오류 발생
	             br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"utf8"));
	         }
	         String inputLine;
	         res = new StringBuffer();
	         while ((inputLine = br.readLine()) != null) {
	             res.append(inputLine);
	         }
	         br.close();
	         //System.out.println("service "+res.toString());
	     } catch (Exception e) {
	         System.out.println(e);
	     }
		return res.toString();
	}

	//hpid -> 병원 정보
	public hospitalVO hosp_detail(hospitalVO hosp, String hpid, String distance) throws ParserConfigurationException, SAXException, IOException {
		//data.go.kr open Api key
		String servicekey = "EWdxpP6bbAOIpmqp0unjTiqqLOwllK5fOBPhvZhqRYZkEBksUGXGIMuzoe7HJPqUisG8YhgOnzYkYtBUwOC0ow%3D%3D";
		String hosp_detail_url = "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlBassInfoInqire?"+"servicekey="+servicekey+"&HPID="+hpid ;
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(hosp_detail_url);
		boolean case_hdt_blank = false;
		int real_diagTime = 0;
		String week_day_str=null;
		hospitalVO hosp1 = new hospitalVO();
		//추가 변수들
		boolean case_name_etc = false;
		String subject = null;
		
		
		week_day_str=this.getWeekDay(hosp.getWeek_day());//
		// root tag 
		doc.getDocumentElement().normalize();
		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		// 파싱할 tag
		NodeList nList = doc.getElementsByTagName("item");	//변경함
		//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
		
		for(int temp = 0; temp < nList.getLength(); temp++){
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;
				try {
				real_diagTime = (Integer.valueOf(getTagValue("dutyTime"+hosp.getWeek_day()+"c", eElement))%100) > 20
						? (real_diagTime=Integer.valueOf(getTagValue("dutyTime"+hosp.getWeek_day()+"c", eElement))-20)
								: (real_diagTime=Integer.valueOf(getTagValue("dutyTime"+hosp.getWeek_day()+"c", eElement))-60 );
				} catch (NullPointerException e) {
					return hosp1;
				}
				case_hdt_blank = Integer.valueOf(hosp.getHp_diagTime()) >= Integer.valueOf(getTagValue("dutyTime"+hosp.getWeek_day()+"s", eElement)) 
						&& Integer.valueOf(hosp.getHp_diagTime()) <= real_diagTime;


				// 진료 과를 선택하지 않았을 때 나오는 출력 2021.12.20 추가 (참고)위 변수도 추가되어있음
				if( hosp.getHp_subject().equals("상관없음") ) {
					subject = "과";
					case_name_etc = getTagValue("dgidIdName", eElement).contains(subject);
				}
				else if ( hosp.getHp_subject().equals("기타") ){
					case_name_etc = ( !getTagValue("dgidIdName", eElement).contains("소아청소년과")
									&& !getTagValue("dgidIdName", eElement).contains("이비인후과")
									&& !getTagValue("dgidIdName", eElement).contains("치과")
									&& !getTagValue("dgidIdName", eElement).contains("안과")
									&& !getTagValue("dgidIdName", eElement).contains("피부과")
									&& !getTagValue("dgidIdName", eElement).contains("내과")
									);
				}
				else {
					subject = hosp.getHp_subject();
					case_name_etc = getTagValue("dgidIdName", eElement).contains(subject);
				}
				//요까지
//				System.out.println(hosp.getHp_subject());
					//담당과와 진료시간을 고려한 상세정보 제공
					if( case_name_etc
							&& case_hdt_blank){
						if ( (Integer.valueOf(hosp.getHp_diagTime()) >= 1200 && Integer.valueOf(hosp.getHp_diagTime()) <= 1300)) {						
							hosp1.setMessage("12:00~13:00는 점심시간이며, 병원마다 다를 수 있습니다.");
						}
						hosp1.setLang(getTagValue("wgs84Lon", eElement));
						hosp1.setLat(getTagValue("wgs84Lat", eElement));
						hosp1.setHpid(getTagValue("hpid", eElement));
						hosp1.setHp_name(getTagValue("dutyName", eElement));
						hosp1.setHp_subject(getTagValue("dgidIdName", eElement));
						hosp1.setHp_addr(getTagValue("dutyAddr", eElement));
						hosp1.setHp_tell(getTagValue("dutyTel1", eElement));
						hosp1.setWeek_day_str(week_day_str);
						hosp1.setHp_dutyTime1(getTagValue("dutyTime"+hosp.getWeek_day()+"s", eElement));
						hosp1.setHp_dutyTime2(getTagValue("dutyTime"+hosp.getWeek_day()+"c",eElement));
						hosp1.setDistance(distance);
					} 
			}
		}
		return hosp1;
	}
	private String getWeekDay(int week_day) {
		String week_day_str = null;
		if(week_day==1) {
			week_day_str ="월";
		} else if(week_day==2) {
			week_day_str = "화";
		} else if(week_day==3) {
			week_day_str = "수";
		} else if(week_day==4) {
			week_day_str = "목";
		} else if(week_day==5) {
			week_day_str = "금";
		} else if(week_day==6) {
			week_day_str = "토";
		} else if (week_day==7) {
			week_day_str = "일";
		}
		return week_day_str;
	}

	private String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}

	public ArrayList<hospitalVO> hp_id(hospitalVO hosp,String date) {
		String servicekey = "EWdxpP6bbAOIpmqp0unjTiqqLOwllK5fOBPhvZhqRYZkEBksUGXGIMuzoe7HJPqUisG8YhgOnzYkYtBUwOC0ow%3D%3D";	// 병원 위치 찾기
		int pageNo = 1;	//입력 사항
		String numOfRows = "100";	//입력 사항
		ArrayList<hospitalVO> list = new ArrayList<hospitalVO>();
		int cnt = 0;
		try{
			while(true){
				// parsing할 url 지정(API 키 포함해서) 바꿔야됨
				String url = "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncLcinfoInqire?serviceKey="+servicekey+"&WGS84_LON="+hosp.getLang()+
						"&WGS84_LAT="+hosp.getLat()+"&pageNo="+Integer.toString(pageNo)+"&numOfRows="+numOfRows;
			
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				
				// root tag 
				doc.getDocumentElement().normalize();
				//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
				//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
				
				for(int temp = 0; temp < nList.getLength(); temp++){
					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
						
						Element eElement = (Element) nNode;
						System.out.println("병원코드  : " + getTagValue("hpid", eElement));
						String hpid = getTagValue("hpid", eElement);
						String distance = getTagValue("distance", eElement);
						hospitalVO vo = this.hosp_detail(hosp, hpid, distance);
						if(vo.getHp_dutyTime1()!=null) {
							String name = vo.getHp_name();
							String time = hosp.getHp_diagTime();
							List<ClosedVO> closedList = dao.isExist(name);	//이름 있는지 여부
							if(closedList.size()==0) {
								list.add(vo);
								cnt +=1;
							} else {
								for(ClosedVO vo1 : closedList) {
									boolean flag = this.checkTime(vo1.getClosedStart(),vo1.getClosedEnd(),date,time);
									if(!flag) {
										list.add(vo);
										cnt +=1;
									}
								}
							}
						}
						/*
						 * for(hospitalVO vo1 : list) { System.out.println(vo1); }
						 */
						
					}	// for end
					if(cnt > 9){	//변경함
						break;
					}
				
				}	// if end
				
				pageNo += 1;
				//System.out.println("page number : "+pageNo);
				if(pageNo > 1){	//변경함
					break;
				}
			}	// while end
		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
		return list;
	}	// main end

	private boolean checkTime(String closedStart, String closedEnd, String date, String time) {
		boolean flag= false;
		String[] startDate = closedStart.split("T");
		String[] endDate = closedStart.split("T");
		String[] startDate2 = startDate[0].split("-");
		String[] startTime = startDate[1].split(":");
		String[] endDate2 = endDate[0].split("-");
		String[] endTime = endDate[1].split(":");
		int startYear = Integer.parseInt(startDate2[0]);
		int startMonth = Integer.parseInt(startDate2[1]);
		int startDay = Integer.parseInt(startDate2[2]);
		int startHour = Integer.parseInt(startTime[0]);
		int startMinute = Integer.parseInt(startTime[1]);
		int startTimes = startHour*100 + startMinute; 
		
		int endYear = Integer.parseInt(endDate2[0]);
		int endMonth = Integer.parseInt(endDate2[1]);
		int endDay = Integer.parseInt(endDate2[2]);
		int endHour = Integer.parseInt(endTime[0]);
		int endMinute = Integer.parseInt(endTime[1]);
		int endTimes = endHour*100 + endMinute; 
		
		String[] dates = date.split("-");
		int year = Integer.parseInt(dates[0]);
		int month = Integer.parseInt(dates[1]);
		int day = Integer.parseInt(dates[2]);
		int times = Integer.parseInt(time);

		if(((startYear<=year) && (year<=endYear)) || ((startMonth<=month) && (month<=endMonth)) || 
				((startDay<=day) && (day<=endDay)) || ((startTimes<=times) && (times<=endTimes))) {
			flag = true;
		}
		
		return flag;
	}

	public hospitalVO2 hosp_info(String hpid) throws ParserConfigurationException, SAXException, IOException{
		// TODO Auto-generated method stub
		String servicekey = "EWdxpP6bbAOIpmqp0unjTiqqLOwllK5fOBPhvZhqRYZkEBksUGXGIMuzoe7HJPqUisG8YhgOnzYkYtBUwOC0ow%3D%3D";
		String hosp_detail_url = "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlBassInfoInqire?"+"servicekey="+servicekey+"&HPID="+hpid ;
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(hosp_detail_url);
		String week_day_str=null;
		hospitalVO2 hosp1 = new hospitalVO2();
		// root tag 
		doc.getDocumentElement().normalize();
		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		// 파싱할 tag
		NodeList nList = doc.getElementsByTagName("item");	//변경함
		//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
		
		for(int temp = 0; temp < nList.getLength(); temp++){
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;								
						hosp1.setHpid(getTagValue("hpid", eElement));
						hosp1.setHp_name(getTagValue("dutyName", eElement));
						hosp1.setHp_subject(getTagValue("dgidIdName", eElement));
						hosp1.setHp_addr(getTagValue("dutyAddr", eElement));
						hosp1.setHp_tell(getTagValue("dutyTel1", eElement));
						
						List<String> dutytimeC = new ArrayList<String>();
						List<String> dutytimeS = new ArrayList<String>();
						
						for(int i =0;i<8;i++) {
							try{
								dutytimeC.add(getTagValue("dutyTime"+Integer.toString(i+1)+"c", eElement));
								dutytimeS.add(getTagValue("dutyTime"+Integer.toString(i+1)+"s", eElement)); 
							}catch (NullPointerException e){
							    //에러시 수행
								dutytimeC.add("운영 안함");
								dutytimeS.add("운영 안함");
							}
						}
						hosp1.setHp_dutyTimeC(dutytimeC);
						hosp1.setHp_dutyTimeS(dutytimeS);
			}	
		}	

		return hosp1;
	}

	public ArrayList<hospitalVO> ph_id(hospitalVO hosp) {
		String servicekey = "EWdxpP6bbAOIpmqp0unjTiqqLOwllK5fOBPhvZhqRYZkEBksUGXGIMuzoe7HJPqUisG8YhgOnzYkYtBUwOC0ow%3D%3D";	// 병원 위치 찾기
		int pageNo = 1;	//입력 사항
		String numOfRows = "100";	//입력 사항
		ArrayList<hospitalVO> list = new ArrayList<hospitalVO>();
		int cnt = 0;
		try{
			while(true){
				// parsing할 url 지정(API 키 포함해서) 바꿔야됨
				String url = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyLcinfoInqire?serviceKey="+servicekey+"&WGS84_LON="+hosp.getLang()+
						"&WGS84_LAT="+hosp.getLat()+"&pageNo="+Integer.toString(pageNo)+"&numOfRows="+numOfRows;
			
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				
				// root tag 
				doc.getDocumentElement().normalize();
				//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
				//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
				
				for(int temp = 0; temp < nList.getLength(); temp++){
					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
						
						Element eElement = (Element) nNode;
						System.out.println("약국코드  : " + getTagValue("hpid", eElement));
						String hpid = getTagValue("hpid", eElement);
						String distance = getTagValue("distance", eElement);
						hospitalVO vo = this.pharm_detail(hosp, hpid, distance);
						if(vo.getHp_dutyTime1()!=null) {
							list.add(vo);
							cnt +=1;
						}
						/*
						 * for(hospitalVO vo1 : list) { System.out.println(vo1); }
						 */
						
					}	// for end
					if(cnt > 2){	//변경함
						break;
					}
				
				}	// if end
				
				pageNo += 1;
				//System.out.println("page number : "+pageNo);
				if(pageNo > 1){	//변경함
					break;
				}
			}	// while end
		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
		return list;
	}
	
	public hospitalVO pharm_detail(hospitalVO hosp, String hpid, String distance) throws ParserConfigurationException, SAXException, IOException {
		//data.go.kr open Api key
		String servicekey = "EWdxpP6bbAOIpmqp0unjTiqqLOwllK5fOBPhvZhqRYZkEBksUGXGIMuzoe7HJPqUisG8YhgOnzYkYtBUwOC0ow%3D%3D";
		String hosp_detail_url = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyBassInfoInqire?servicekey="+servicekey+"&HPID="+hpid ;
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(hosp_detail_url);
		boolean case_hdt_blank = false;
		int real_diagTime = 0;
		String week_day_str=null;
		hospitalVO hosp1 = new hospitalVO();
		
		
		week_day_str=this.getWeekDay(hosp.getWeek_day());//
		// root tag 
		doc.getDocumentElement().normalize();
		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		// 파싱할 tag
		NodeList nList = doc.getElementsByTagName("item");	//변경함
		//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
		
		for(int temp = 0; temp < nList.getLength(); temp++){
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;
				try {
				real_diagTime = (Integer.valueOf(getTagValue("dutyTime"+hosp.getWeek_day()+"c", eElement))%100) > 20
						? (real_diagTime=Integer.valueOf(getTagValue("dutyTime"+hosp.getWeek_day()+"c", eElement))-20)
								: (real_diagTime=Integer.valueOf(getTagValue("dutyTime"+hosp.getWeek_day()+"c", eElement))-60 );
				} catch (NullPointerException e) {
					return hosp1;
				}
				// 진료 시간을 입력하지 않았을 때 출력
				if(hosp.getHp_diagTime().equals("")) {
					case_hdt_blank = true;
				} else {
					case_hdt_blank = Integer.valueOf(getTagValue("dutyTime"+hosp.getWeek_day()+"s", eElement)) <= Integer.valueOf(hosp.getHp_diagTime())
					&& Integer.valueOf(hosp.getHp_diagTime()) <= real_diagTime;
				}
					
					//담당과와 진료시간을 고려한 상세정보 제공
					if(case_hdt_blank){
						if ( (Integer.valueOf(hosp.getHp_diagTime()) >= 1200 && Integer.valueOf(hosp.getHp_diagTime()) <= 1300)) {
						hosp1.setMessage("12:00~13:00는 점심시간이며, 병원마다 다를 수 있습니다.");
						} 	// if2 end
						hosp1.setLang(getTagValue("wgs84Lon", eElement));
						hosp1.setLat(getTagValue("wgs84Lat", eElement));
						hosp1.setHpid(getTagValue("hpid", eElement));
						hosp1.setHp_name(getTagValue("dutyName", eElement));
						hosp1.setHp_addr(getTagValue("dutyAddr", eElement));
						hosp1.setHp_tell(getTagValue("dutyTel1", eElement));
						hosp1.setWeek_day_str(week_day_str);
						hosp1.setHp_dutyTime1(getTagValue("dutyTime"+hosp.getWeek_day()+"s", eElement));
						hosp1.setHp_dutyTime2(getTagValue("dutyTime"+hosp.getWeek_day()+"c",eElement));
						hosp1.setDistance(distance);
					} 
			}
		}
		return hosp1;
	}
	
	public hospitalVO2 pharm_info(String hpid) throws ParserConfigurationException, SAXException, IOException{
		// TODO Auto-generated method stub
		String servicekey = "EWdxpP6bbAOIpmqp0unjTiqqLOwllK5fOBPhvZhqRYZkEBksUGXGIMuzoe7HJPqUisG8YhgOnzYkYtBUwOC0ow%3D%3D";
		String hosp_detail_url = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyBassInfoInqire?servicekey="+servicekey+"&HPID="+hpid ;
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(hosp_detail_url);
		String week_day_str=null;
		hospitalVO2 hosp1 = new hospitalVO2();
		// root tag 
		doc.getDocumentElement().normalize();
		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		// 파싱할 tag
		NodeList nList = doc.getElementsByTagName("item");	//변경함
		//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
		
		for(int temp = 0; temp < nList.getLength(); temp++){
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;								
						hosp1.setHpid(getTagValue("hpid", eElement));
						hosp1.setHp_name(getTagValue("dutyName", eElement));
						hosp1.setHp_addr(getTagValue("dutyAddr", eElement));
						hosp1.setHp_tell(getTagValue("dutyTel1", eElement));
						
						List<String> dutytimeC = new ArrayList<String>();
						List<String> dutytimeS = new ArrayList<String>();
						
						for(int i =0;i<8;i++) {
							try{
								dutytimeC.add(getTagValue("dutyTime"+Integer.toString(i+1)+"c", eElement));
								dutytimeS.add(getTagValue("dutyTime"+Integer.toString(i+1)+"s", eElement)); 
							}catch (NullPointerException e){
							    //에러시 수행
								dutytimeC.add("운영 안함");
								dutytimeS.add("운영 안함");
							}
						}
						hosp1.setHp_dutyTimeC(dutytimeC);
						hosp1.setHp_dutyTimeS(dutytimeS);
			}	
		}	

		return hosp1;
	}

	public List<evalVO> eval(String hpid) {
		// TODO Auto-generated method stub
		List<evalVO> evalList = dao.eval(hpid);
		return evalList;
	}

	public boolean insertEval(evalVO vo) {
		// TODO Auto-generated method stub
		boolean flag = dao.insertEval(vo);
		return flag;
	}
	
	
}

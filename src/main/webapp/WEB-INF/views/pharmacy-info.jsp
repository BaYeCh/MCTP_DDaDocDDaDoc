<%@page import="mc.sn.closed.ClosedVO"%>
<%@page import="mc.sn.hospital.evalVO"%>
<%@page import="java.util.List"%>
<%@page import="mc.sn.hospital.hospitalVO2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>약국 정보 페이지 | {타이틀}</title>
    <link rel="stylesheet" href="resources/css/hospital-info.css" />
  </head>
  <body>
  <%hospitalVO2 vo = (hospitalVO2) request.getAttribute("vo"); %>
    <section class="board-wrap">
      <div class="board-title">
        <h1>약국 정보</h1>
        <p>위치, 오픈시간 등에 대한 정보를 제공합니다.</p>
      </div>
      <div class="board-hospital-wrap">
        <div class="board-hospital">
          <div class="title"><%=vo.getHp_name() %></div>
          <div class="tab-area">
            <input type="radio" id="tab1" name="tab" checked />
            <label for="tab1">약국 정보</label>
            <input type="radio" id="tab2" name="tab" />
            <label for="tab2">오픈 시간</label>
            <input type="radio" id="tab3" name="tab" />
            <label for="tab3">약국 리뷰</label>
            <div class="content cont1">
              <p>위치 : <%=vo.getHp_addr() %></p>
            </div>
            <div class="content cont2">
            	<p>오픈 시간</p>
              <table class="time-list">
              <% List<String> open = vo.getHp_dutyTimeS();%>
              <% List<String> close = vo.getHp_dutyTimeC();%>
                <tr>
                  <td>월</td>
                  <td><%=open.get(0) %> ~ <%=close.get(0) %></td>
                </tr>
                <tr>
                  <td>화</td>
                  <td><%=open.get(1) %> ~ <%=close.get(1) %></td>
                </tr>
                <tr>
                  <td>수</td>
                  <td><%=open.get(2) %> ~ <%=close.get(2) %></td>
                </tr>
                <tr>
                  <td>목</td>
                  <td><%=open.get(3) %> ~ <%=close.get(3) %></td>
                </tr>
                <tr>
                  <td>금</td>
                  <td><%=open.get(4) %> ~ <%=close.get(4) %></td>
                </tr>
                <tr>
                  <td>토</td>
                  <td><%=open.get(5) %> ~ <%=close.get(5) %></td>
                </tr>
                <tr>
                  <td>일</td>
                  <td><%=open.get(6) %> ~ <%=close.get(6) %></td>
                </tr>
                <tr>
                  <td>공휴일</td>
                  <td><%=open.get(7) %> ~ <%=close.get(7) %></td>
                </tr>
                <tr>
                  <td>점심 시간</td>
                  <td>1200 ~ 1300</td>
                </tr>
              </table>    
            </div>                   	
            <div class="content cont3">
            	<table class="board-list">
                <tr class="list-title">
                	<td class="cont-menu">제목</td>
                	<td class="cont-menu">약국 이름</td>
                	<td class="cont-menu">글쓴이</td>
                </tr>
                <%List<evalVO> evalList = (List)request.getAttribute("evalList");%>
                <%for(evalVO vo1 : evalList){%>
	                <tr class="list">
	                	<td class="cont"><%=vo1.getTitle() %></td>
	                	<td class="cont"><%=vo1.getHosp_name() %></td>
	                	<td class="cont"><%=vo1.getWriter() %></td>
	                </tr>
	            <%} %>
            </table> 
          </div>
        </div>
        </div>
        <div class="btn-wrap">
       	 	<a href="./eval_form2?hosp_name=<%=vo.getHp_name()%>&location=<%=vo.getHp_addr()%>&hpid=<%=vo.getHpid()%>">등록</a>
          	<a href="./mainPage" class="on">뒤로가기</a>
        </div>
      </div>
    </section>
  </body>
</html>

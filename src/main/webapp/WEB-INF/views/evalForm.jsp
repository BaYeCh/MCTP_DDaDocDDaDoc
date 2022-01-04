<%@page import="mc.sn.hospital.evalVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>info | (타이틀)</title>
    <link rel="stylesheet" href="resources/css/eval-Form.css" />
    <script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="resources/js/eval.js"></script>
  </head>
  <body>
  <%String id = (String)session.getAttribute("id"); 
if (id == null){
	%>
	<script type="text/javascript">
	alert("로그인이 필요합니다.")
	window.location.assign("./login_form");
	</script>
<%}
evalVO vo = (evalVO) request.getAttribute("vo");
%>
    <section class="board-wrap">
      <div class="board-title">
        <h1>병원 리뷰</h1>
        <p>해당 병원에 대한 리뷰를 작성해주세요.</p>
      </div>
      <div class="board-write-wrap">
        <div class="board-write">
          <div class="title">
            <input type="text" id="title" placeholder="제목을 입력하세요." autofocus />
          </div>
          <div class="info">
          	<input type="text" id="hosp_name" value="<%=vo.getHosp_name()%>" onfocus="javascript:blur()"/>
          	<input type="text" id="location" value="<%=vo.getLocation() %>" onfocus="javascript:blur()"/>
          	<input type="text" id="writer" value="<%=vo.getWriter() %>" onfocus="javascript:blur()"/>
            <input type="hidden" id="hpid" value="<%=vo.getHpid() %>" onfocus="javascript:blur()"/>
          </div>
          <div class="cont">
            <textarea  id="content" placeholder="내용을 입력하세요." autofocus></textarea>
          </div>
        </div>
        <div class="btn-wrap">
        	<button id="evalRegister">등록</button>       	
          	<a href="./hospital_detail?hpid=<%=vo.getHpid()%>">취소</a>
        </div>
      </div>
    </section>
  </body>
</html>

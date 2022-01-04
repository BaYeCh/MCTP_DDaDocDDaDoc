<%@page import="mc.sn.closed.ClosedVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/closed_board/list.css">
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="resources/js/closed.js"></script>
<title>전체 목록</title>
	<script
      src="https://kit.fontawesome.com/b6e6ccd4f8.js"
      crossorigin="anonymous"
    ></script>
</head>
<body>
<%String id = (String)session.getAttribute("id"); 
if (id == null){
	%>
	<script type="text/javascript">
	window.location.assign("./closed_write");
	</script>
<%}%>

	<div class="logo">
        <a href="mainPage">
            <i class="fas fa-hospital-user"></i>
            따닥따닥
    	</a>
    </div>
    <section class="board-wrap">
        <div class="board-title">
            <h1>내가 쓴 휴원 정보</h1>
            <p>휴가 및 개인사유 등으로 휴원하는 병원에 대한 정보를 공유해주세요!</p>
        </div>
        <div class="board-list-wrap">
            <table class="board-list">
                <tr class="list-title">
                	<td class="cont-menu">병원이름</td>
                	<td class="cont-menu">위치</td>
                	<td class="cont-menu">휴원기간</td>
                	<td class="cont-menu">글쓴이</td>
                	<td class="cont-menu">수정</td>
                	<td class="cont-menu">삭제</td>
                </tr>
                <%List<ClosedVO> closedList = (List)request.getAttribute("closedList");%>
                <%for(ClosedVO vo : closedList){ %>
	                <tr class="list">
	                	<td class="cont" id="closedId" style="display:none"><%=vo.getClosedId() %></td>
	                	<td class="cont"><a href="./hospital_detail?"><%=vo.getHospitalName() %></a></td>
	                	<td class="cont"><%=vo.getHospitalAddr() %></td>
	                	<td class="cont"><%=vo.getClosedStart()%> ~ <%=vo.getClosedEnd()%></td>
	                	<td class="cont"><%=vo.getWriterId() %></td>
	                	<td class="cont"><a href="./closed_update?id=<%=vo.getClosedId()%>">수정</a></td>
	                	<td class="cont"><button id="delete">삭제</button></td>
	                </tr>
	            <%} %>
            </table>
            <div class="board-page">
                <a href="#" class="btn first"><<</a>
                <a href="#" class="btn prev"><</a>
                <a href="#" class="num on">1</a>
                <a href="#" class="num">2</a>
                <a href="#" class="num">3</a>
                <a href="#" class="num">4</a>
                <a href="#" class="num">5</a>
                <a href="#" class="btn next">>></a>
                <a href="#" class="btn last">></a>
            </div>
            <div class="btn-wrap">
                <a href="./closed_write" class="on">등록</a>
            </div>
        </div>
    </section>
</body>
</html>
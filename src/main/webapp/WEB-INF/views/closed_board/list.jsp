<%-- <%@page import="mc.sn.closed.ClosedVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/closed_board/list.css">
<title>전체 목록</title>
</head>
<body>
<% String id = (String) session.getAttribute("id");%>
    <section class="board-wrap">
        <div class="board-title">
            <h1>휴원 정보</h1>
            <p>휴가 및 개인사유 등으로 휴원하는 병원에 대한 정보를 공유해주세요!</p>               
            <%if(id!=null){ %>
            	<script type="text/javascript">
            	window.location.assign("./myList?id=<%=id%>");
            	</script>
            <%}else{%>
        </div>
        <div class="board-list-wrap">
            <div class="board-list">
                <div class="top">
                    <div class="cont-menu">병원이름</div>
                    <div class="cont-menu">위치</div>
                   	<div class="cont-menu">휴원기간</div>
                    <div class="cont-menu">글쓴이</div>
<!--                     <div class="cont-menu">수정</div>
                    <div class="cont-menu">삭제</div> -->
                </div>
                <%List<ClosedVO> closedList = (List)request.getAttribute("closedList");%>
                <%for(ClosedVO vo : closedList){ %>
                <div>
                </div>
                <div class="bottom">
                    <div class="cont"><a href="./hospital_detail?"><%=vo.getHospitalName() %></a></div>
                    <div class="cont"><%=vo.getHospitalAddr() %></div>
                    <div class="cont"><%=vo.getClosedStart()%> ~ <%=vo.getClosedEnd()%></div>
                    <div class="cont"><%=vo.getWriterId() %></div>
                    <div class="cont"><a href="./closed_update?id="<%=vo.getClosedId()%>>수정</a></div>
                    <div class="cont"><a href="./closed_delte?id="<%=vo.getClosedId()%>>삭제</a></div>
                </div>  
                <%} %>  
            </div>
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
</html> --%>
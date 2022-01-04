<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board | (타이틀)</title>
    <link rel="stylesheet" href="resources/css/closed_board/write.css">    
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
    <script type="text/javascript" src = "resources/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src = "resources/js/closed.js"></script>
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
%>
    <section class="board-wrap">
        <div class="board-title">
            <h1>휴원 정보</h1>
            <p>휴가 및 개인사유 등으로 휴원하는 병원에 대한 정보를 공유해주세요!</p>
        </div>
        <%String writer = (String)session.getAttribute("id"); %>
        <div class="board-write-wrap">
            <div class="board-write">
                <div class="title">
                    <label for="tilte">병원 이름</label>
                    <input type="text" id="title" placeholder="[지역] 병원 이름" required id="name">
                </div>
                <div class="info">
                    <label for="info">작성자</label>
                    <span><input type="text" id="writer" value=<%=writer %> id="writer" onfocus="javascript:blur()"></span>
                </div>
                <div class="cont">
                    <div class="content">
                        <label>도로명 주소 : </label>
                        <input type="text" id="addr">
                    </div>
                    <div class="content">
                        <label>휴원 기간 : </label>
                        <input type="datetime-local" value="" id="time1">
                        &nbsp&nbsp~&nbsp&nbsp
                        <input type="datetime-local" value="" id="time2">
                    </div>
                </div>
            </div>
            <div class="btn-wrap">
                <button id="closed_add">등록</button>
                <a href="./myList">목록으로</a>
            </div>
        </div>
    </section>

</body>
</html>
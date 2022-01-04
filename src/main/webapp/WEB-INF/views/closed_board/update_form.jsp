<%@page import="mc.sn.closed.ClosedVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
    <link rel="stylesheet" href="resources/css/closed_board/write.css">    
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
    <script type="text/javascript" src = "resources/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src = "resources/js/closed.js"></script>
</head>
<body>
    <section class="board-wrap">
        <div class="board-title">
            <h1>휴원 정보</h1>
            <p>휴가 및 개인사유 등으로 휴원하는 병원에 대한 정보를 공유해주세요!</p>
        </div>
        <div class="board-write-wrap">
            <div class="board-write">
            	<span id="closedId" style="display:none">${vo.closedId}</span>
                <div class="title">
                    <label for="name">병원 이름</label>
                    <input type="text" id="name" placeholder="[지역] 병원 이름" required id="name" value=${vo.hospitalName}>
                </div>
                <div class="info">
                    <label for="info">작성자</label>
                    <span><input type="text" id="writer" value=${vo.writerId} readonly="readonly"></span>
                </div>
                <div class="cont">
                    <div class="content">
                        <label>도로명 주소 : </label>
                        <input type="text" id="addr" value=${vo.hospitalAddr}>
                    </div>
                    <div class="content">
                        <label>휴원 기간 : </label>
                        <input type="datetime-local" id="time1" value=${vo.closedStart}> ~ 
                        <input type="datetime-local" id="time2" value=${vo.closedEnd}>
                    </div>
                </div>
            </div>
            <div class="btn-wrap">
                <button id="change">수정</button>
                <a href="./myList">목록으로</a>
            </div>
        </div>
    </section>

</body>
</html>
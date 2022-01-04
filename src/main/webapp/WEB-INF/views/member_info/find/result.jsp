<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과</title>
<link rel="stylesheet" href="resources/css/find_result.css">
<script
      src="https://kit.fontawesome.com/b6e6ccd4f8.js"
      crossorigin="anonymous"
    ></script>
</head>
<body>
<section class="result-area">
	<header class="header">
        <div class="logo">
	          <a href="mainPage">
	            <i class="fas fa-hospital-user"></i>
	            따닥따닥
	          </a>
        </div>
    </header>
	<h1>찾기 결과</h1>
	<div class="print-id">
		<%String result = (String) session.getAttribute("find_result"); %>
		<%=result %>
	</div>
	<div class="a-area">
		<a href="./login_form">로그인</a>
	</div>
</section>
</body>
</html>
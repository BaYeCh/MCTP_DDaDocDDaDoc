<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="resources/css/loginForm.css">
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="resources/js/login.js"></script>  
    <script
      src="https://kit.fontawesome.com/b6e6ccd4f8.js"
      crossorigin="anonymous"
    ></script>
</head>
<body>
    <section class="login-form">
   	    <header class="header">
        	<div class="logo">
	          	<a href="mainPage">
	            	<i class="fas fa-hospital-user"></i>
	            	따닥따닥
	          	</a>
        	</div>
        </header>
        <h1>Login</h1>
        <div class="int-area">
            <input type="text" name="id" id="id" autocomplete="off" required>
            <label for="id">ID</label>
        </div>
        <div class="int-area">
            <input type="password" name="pw" id="pw" autocomplete="off" required>
            <label for="pw">PASSWORD</label>
        </div>
        <div class="btn-area">
            <button id= "login">LOGIN</button>                
        </div>
		<div class="caption">
		    <a href="./findId_form">아이디 찾기</a>
		    <a href="./findPwd_form">비밀번호 찾기</a>
		    <a href="./join_form">회원가입</a>
		</div>
    </section>
</body>
</html>
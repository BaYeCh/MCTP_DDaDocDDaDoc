<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/findID_PWD.css">
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="resources/js/find.js"></script>  
<title>Id 찾기</title>
<script
      src="https://kit.fontawesome.com/b6e6ccd4f8.js"
      crossorigin="anonymous"
    ></script>
</head>
<body>
    <section class="find">
    	<header class="header">
        	<div class="logo">
	          	<a href="mainPage">
	            	<i class="fas fa-hospital-user"></i>
	            	따닥따닥
	          	</a>
        	</div>
        </header>
        <h1>아이디 찾기</h1>
            <div class="int-area">            
                <input autocomplete="off" type="text" name="name" id="name" required autofocus>
                <label for="name">NAME</label>
            </div>
            <div class="int-area">
                <input autocomplete="off" type="email" name="email" id="email" required>
                <label for="email">EMAIL</label>
            </div>
            <div class="int-area">
				<input type="text" name="birth" id="birth" required autocomplete="off" placeholder="생년월일 8자리">
				<label for="email">생년월일</label>
			</div>
            <div class="btn-area">
                <button id="findId">Find ID</button>
            </div>
    </section>
</body>
</html>
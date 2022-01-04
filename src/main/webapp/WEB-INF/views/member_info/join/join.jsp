<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="resources/css/joinForm.css">
    <script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script> 
    <script type="text/javascript" src="resources/js/join.js"></script>   
	<script
      src="https://kit.fontawesome.com/b6e6ccd4f8.js"
      crossorigin="anonymous"
    ></script>
</head>
<body>
    <section class="join-form">
    	<header class="header">
        	<div class="logo">
          		<a href="mainPage">
            		<i class="fas fa-hospital-user"></i>
            		따닥따닥
          		</a>
        	</div>
     	</header>
        <h1>회원가입</h1>
            <div class="int-area">
                <input type="text" name="id" id="id" autocomplete="off" required>
                <label for="id">ID</label>
                <!-- 이 쪽에 id 중복확인 로직 달아주시면 되세요!!-->
                <span id="id-check" class="id-check"><button id="id_check">ID 중복확인</button></span>
            </div>
            <div class="int-area">
                <input type="password" name="pwd" id="pwd" autocomplete="off" required>
                <label for="pwd">PASSWORD</label>
            </div>
            <div class="int-area">
                <input type="password" name="checkpw" id="checkpw"  onchange="checkPW()" autocomplete="off" required>
                <label for="checkpw">PASSWORD 확인</label>
            </div>
            <span id="message" class="message"></span>
            <div class="int-area">
                <input type="text" name="name" id="name" autocomplete="off" required>
                <label for="name">이름</label>
            </div>
            <div class="int-area">
                <input type="text" name="nickname" id="nickname" autocomplete="off" required>
                <label for="name">닉네임</label>
                <!-- 이 쪽에 id 중복확인 로직 달아주시면 되세요!!-->
                <span id="sub-check" class="sub-check"><button id="nickName_check">중복확인</button></span>
            </div>
            <div class="int-area">
                <input type="text" name="birth" id="birth" autocomplete="off" required placeholder="8자리 ex) 19910102">
                <label for="birth">생년월일</label>    
            </div>
            <div class="int-area">
                <input type="text" name="tel" id="tel" autocomplete="off" required placeholder="숫자만 입력">
                <label for="tel">휴대폰 번호</label>
            </div>
            <div class="int-area">
                <input type="email" name="email" id="email" autocomplete="off" required>
                <label for="email">이메일</label>
                <span id="email-check" class="email-check"><button id="email_check">중복확인</button></span>
            </div>
            <div class="int-area addr-area">
                <input type="text" name="address1" id="address1" autocomplete="off" required placeholder="시/도">&nbsp;
                <label for="address1">주소</label>
                <input type="text" name="address2" id="address2" autocomplete="off" required placeholder="시/군/구">
            </div>
            <div class="int-area">
                <input type="text" name="address3" id="address3" autocomplete="off" required placeholder="상세주소">
            </div>
            <div class="btn-area">
                <button id="member_add">회원가입</button>
            </div>
    </section>
</body>
</html>
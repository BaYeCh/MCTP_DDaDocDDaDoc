<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보 수정</title>
    <link rel="stylesheet" href="resources/css/admin.css">
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/admin.js"></script>
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
        <h1>정보수정</h1>
            <div class="int-area">
                <input type="text" name="id" id="id" autocomplete="off" required onfocus="javascript:blur()" value=${vo.id}>
                <label for="id">ID</label>
            </div>
            <div class="int-area">
                <input type="password" name="pwd" id="pwd" autocomplete="off" required value=${vo.pwd}>
                <label for="pwd">PASSWORD</label>
            </div>
            <div class="int-area">
                <input type="password" name="checkpw" id="checkpw"  onchange="checkPW()" autocomplete="off" required value=${vo.pwd}>
                <label for="checkpw">PASSWORD 확인</label>
            </div>
            <span id="message" class="message"></span>
            <div class="int-area">
                <input type="text" name="name" id="name" autocomplete="off" required value=${vo.name}>
                <label for="name">이름</label>
            </div>
            <div class="int-area">
                <input type="text" name="nickname" id="nickname" autocomplete="off" required value=${vo.nickname}>
                <label for="name">닉네임</label>
            </div>
            <div class="int-area">
                <input type="text" name="birth" id="birth" autocomplete="off" required placeholder="8자리 ex) 19910102" value=${vo.birth}>
                <label for="birth">생년월일</label>    
            </div>
            <div class="int-area">
                <input type="text" name="tel" id="tel" autocomplete="off" required placeholder="숫자만 입력" value=${vo.tel}>
                <label for="tel">휴대폰 번호</label>
            </div>
            <div class="int-area">
                <input type="email" name="email" id="email" autocomplete="off" required value=${vo.email}>
                <label for="email">이메일</label>
            </div>
            <div class="int-area addr-area">
                <input type="text" name="address1" id="address1" autocomplete="off" required placeholder="시/도" value=${vo.address1}>&nbsp;
                <label for="address1">주소</label>
                <input type="text" name="address2" id="address2" autocomplete="off" required placeholder="시/군/구" value=${vo.address2}>
            </div>
            <div class="int-area">
                <input type="text" name="address3" id="address3" autocomplete="off" required placeholder="상세주소" value=${vo.address3}>
            </div>
            <div class="btn-area">
                <button id="update">수정</button>
            </div>
    </section>
</body>
</html>
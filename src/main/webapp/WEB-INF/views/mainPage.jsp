<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"
    />
    <script
      type="text/javascript"
      src="resources/js/jquery-3.6.0.min.js"
    ></script>
    <script type="text/javascript" src="resources/js/hospital.js"></script>
    <script
      type="text/javascript"
      src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=h6n207rm9v"
    ></script>
    <script
      src="https://kit.fontawesome.com/b6e6ccd4f8.js"
      crossorigin="anonymous"
    ></script>
    <title>따닥따닥</title>
    <link rel="stylesheet" href="resources/css/main_page.css" />
  </head>
  <body>
    <!-- 지도 -->
    <section class="all">
      <header class="header">
        <div class="logo">
          <a href="mainPage">
            <i class="fas fa-hospital-user"></i>
            따닥따닥
          </a>
        </div>

        <div class="nav">
        <%String id = (String)session.getAttribute("id"); 
		if (id != null){%>
			<a href="./admin_form">마이페이지</a>
		<%}
		%>
          <%if (id == null){%>
          <a href="./login_form">로그인</a>
          <%} else{%>
          <a href="./logout">로그아웃</a>
          <%} %>
        </div>
      </header>
      <section class="main-area">
        <div class="search-area">
          <div>
            <label for="locale">현위치</label>
            <input
              type="text"
              id="locale"
              autocomplete="off"
              placeholder="위치 주변의 병원을 불러옵니다."
            />
          </div>
          <div>
            <label for="branch">진료 과</label>
            <select name="branch" id="hp_subject">
              <option value="상관없음">상관없음</option>
              <option value="소아청소년과">소아청소년과</option>
              <option value="이비인후과">이비인후과</option>
              <option value="치과">치과</option>
              <option value="안과">안과</option>
              <option value="피부과">피부과</option>
              <option value="내과">내과</option>
              <option value="기타">기타</option>
              
            </select>
          </div>
          <div>
            <label for="time">이용 시간</label>
            <input type="datetime-local" id="time" />
          </div>
          <button type="submit" id="btn"><i class="fas fa-search"></i></button>
          <a href="./myList">
          <span>휴원정보 등록</span>
          <i class="fas fa-notes-medical"></i>
          </a>
          <a href="./view_chat">
          <span>FAQ 챗봇</span>
          <i class="fas fa-comment-medical"></i>
          </a>
        </div>
        <section class="map-area">
          <div class="map" id="map"></div>
        </section>
        <section class="list">
          <div class="list-wrap">
            <div class="list-t"><img src="resources/imgs/hospital_pin.png" alt="이미지 없음" width="25px" height="25px"> 병원</div>
            <div class="hos-list">
              <% for(int i =0; i<10; i++) {%>
              <div class="hospitalList">
                <div id="lang<%=i%>" style="display: none"></div>
                <div id="lat<%=i%>" style="display: none"></div>
                <div class="name_hos" id="name<%=i%>"></div>
                <div id="addr<%=i%>"></div>
                <div id="subj<%=i%>"></div>
                <div id="dutyTime<%=i%>"></div>
                <div id="message<%=i%>"></div>
                <div id="distance<%=i%>"></div>
                <div id="link<%=i%>"></div>
                <%-- <a id="link<%=i%>"></a><br />
                --%> <%-- <button class="pharmacy" id="pharmacy<%=i%>"></button
                ><br />
                --%>
                <div id="pharmacy_btn<%=i%>"></div>
                <br />
              </div>
              <%}%>
            </div>
          </div>
          <div class="list-wrap">
            <div class="list-t"><img src="resources/imgs/pharmacy_pin.png" alt="이미지 없음" width="25px" height="25px"> 약국</div>
            <div class="med-list">
              <%for(int i =0; i<3; i++) {%>
              <div class="pharmacyList">
                <div class="name_pha" id="phName<%=i%>"></div>
                <div id="phAddr<%=i%>"></div>
                <div id="phSubj<%=i%>"></div>
                <div id="phDutyTime<%=i%>"></div>
                <div id="phMessage<%=i%>"></div>
                <div id="phDistance<%=i%>"></div>
                <div id="phLink<%=i%>"></div>
                <%-- <a id="phLink<%=i%>"></a><br />
                --%> <%--
                <button
                  class="pharmacy_detail"
                  id="pharmacy_detail<%=i%>"
                ></button>
                --%>
                <div id="pharmacy_detail_btn<%=i%>"></div>
                <br />
              </div>
              <%} %>
            </div>
          </div>
        </section>
      </section>
    </section>
  </body>
</html>

<%@page import="mc.sn.login.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 조회</title>
</head>
<body>
<%List<MemberVO> memberList = (List)request.getAttribute("memberList");%>
<table>
	<tr>
		<td>아이디</td><td>관리자여부</td><td>삭제</td><td>관리자로</td><td>일반회원으로</td>
	</tr>
<%for(MemberVO vo : memberList){ %>
	<tr>
		<td><%=vo.getId() %></td>
		<td><%=vo.getAdmin() %></td>
		<td><a href="./delete?id=<%=vo.getId() %>">삭제</a></td>
		<td><a href="./updateToAdmin?id=<%=vo.getId() %>">관리자</a></td>
		<td><a href="./updateToMember?id=<%=vo.getId() %>">회원</a></td>
	</tr>	
<% }%>
</table>
</body>
</html>
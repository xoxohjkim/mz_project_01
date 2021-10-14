<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello</title>

<link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/slider.css"/>">
<script src="https://code.jquery.com/jquery-3.6.0.js"  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<!-- admin -->


</head>
<body>
<header>
<c:if test="${loginUser == null }">
	<div>
		<a href="<c:url value="/login" />"> 로그인</a>
		<a href="<c:url value="/register" />"> 회원가입</a>
	</div>
</c:if>

<c:if test="${loginUser != null }">
	<form name="loginForm" method="post" action="/doLogin" autocomplete="off" id="loginForm">
		<div>
			${loginUser.nickname}(${loginUser.id})님
			<a href="<c:url value="/mypage/boardList" />"> 마이페이지</a>
			<a href="<c:url value="/logout" />"> 로그아웃</a>
		</div>
	</form>
	</c:if>
	
<div>
	<a href="<c:url value="/board?kind=gnr" />"> 자유</a>
	<a href="<c:url value="/board?kind=img" />"> 이미지</a>
</div>


</header>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<!--${board}  -->

<c:forEach var="file" items="${fileList}" varStatus="status">
	<img src="/resources/${file.path}" width="30%" height="30%">
</c:forEach>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<script type="text/javascript">

//첫번째 방법
$(document).ready(function() {
	history.replaceState({}, null, location.pathname
});

</script>

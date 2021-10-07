<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<script>
$(document).ready(function(){
	
	$('#loginBtn').click(function(){
		var data = {
				id: $('#id').val(),
				pwd: $('#pwd').val()
		};
		
		$.ajax({
			url : "/login",
			type:'POST',
			//dataType: 'json', 
			contentType: 'application/json; charset=utf-8', 
			data: JSON.stringify(data),
			success:function(res){
				console.log(res)
				if(res == 1){
					window.location.href='/main'
				}else {
					alert('아이디나 비밀번호가 맞지 않습니다.');
				}
			},
			error: function(request,status,error){
				alert('에러' + request.status+request.responseText+error);
				console.log(error);
			}
		});
	});
});
</script>	
	
	
	<form name="loginForm" method="post" action="/doLogin" autocomplete="off" id="loginForm">
		<div>
			<input type="text" id="id" name="id" placeholder="아이디" required>
		</div>
		<div>
			<input type="password" id="pwd" name="pwd" placeholder="비밀번호" required>
		</div>
		<div>
			<input type="button" id="loginBtn" value="로그인">
		</div>
	</form>

	
	
	
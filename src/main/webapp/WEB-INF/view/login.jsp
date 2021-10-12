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
				}else if(res == 2){
					alert('이메일 인증을 완료해주세요.')
				} else {
					alert('아이디나 비밀번호가 맞지 않습니다.');
				}
			},
			error: function(request,status,error){
				alert('에러' + request.status+request.responseText+error);
				console.log(error);
			}
		});
	});
	
	$('#searchInfo').click(function(){
		
	});
});
</script>	
	
	
	<form name="loginForm" method="post" action="/doLogin" autocomplete="off" id="loginForm">
		<div>
			<input type="text" id="id" name="id" value="admin" required >
		</div>
		<div>
			<input type="password" id="pwd" name="pwd" value="1234" required value="1234">
		</div>
		<div>
			<input type="button" id="loginBtn" value="로그인">
			<input type="button" id="searchInfo" value="아이디/비밀번호찾기" onclick='location.href="/find_idpw"'>
		</div>
	</form>

	
	
	
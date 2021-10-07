<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<script>
	$(document).ready(function(){
		
		
		$('#registerBtn').click(function(){
			
			var newMember = {
					id : $('#id').val(),
					pwd : $('#pwd').val(),
					email : $('#email').val(),
					name : $('#name').val(),
					nickname : $('#nickname').val(),
			};
			console.log(newMember);
			//JSON.stringify(newMember)
			
			$.ajax({
				url : "/register",
				type:'POST',
				data: $('#doRegister').serialize(),
				success:function(res){
					if(res == 1){
						alert('회원가입이 완료되었습니다.');
						window.location.href= '/main';
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
	
	<form name="register" method="post" action="/register" autocomplete="off" id="doRegister">
		<div>
			<input type="text" id="id" name="id" placeholder="아이디" value="test234">
		</div>
		
		<div>
			<input type="password" id="pwd" name="pwd" placeholder="비밀번호" value="ehqt1223">
		</div>
		
		<div>
			<input type="text" id="email" name="email" placeholder="이메일" value="test@test.com">
		</div>
		
		<div>
			<input type="text" id="name" name="name" placeholder="이름" value="김이름">
		</div>
		
		<div>
			<input type="text" id="nickname" name="nickname" placeholder="닉네임" value="닉넴ㅇ">
		</div>
		
		<div>
			<input type="button" value="회원가입" id="registerBtn">
		</div>
	</form>
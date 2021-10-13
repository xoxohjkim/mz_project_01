<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<script>
$(document).ready(function(){

	$('#updateInfoBtn').click(function(){
			
		confirm('email', $('#email').val())
			
		/* if(!/^[a-zA-Z0-9]{8,20}$/.test($('#pwd').val())){
		     alert("비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.");
		     return false;
		}
		if($('#pwd').val() == "" && $('#pwd2').val() == ""){
			alert('비밀번호를 입력해주세요.')
			return false;
		}
		if($('#pwd').val() != $('#pwd2').val()){
			alert('비밀번호가 일치하지 않습니다..');
			return false;
		} */
		
	});
	
});

function confirm(condition, keyword){
		var data = {
				cond : condition,
				key : $('#email').val()
			}
		console.log(data)
			
			$.ajax({
				url: "/infoConfirm",
				type: "POST",
				contentType:"application/json; charset=utf-8",
				dataType: "json",
				cache : false,
				data : JSON.stringify(data),
				success: function(res) {
					console.log(res)
				},
				error: function(request,status,error){
					alert('에러' + request.status+request.responseText+error);
				}
			});
}

</script>


<div>
	<a href="/mypage/boardList">내 게시글</a>
	<a href="/mypage/commentList">내 댓글</a>
	<a href="/mypage/myInfo">회원정보 변경</a>
</div>

회원 정보 변경

<form  method="post" autocomplete="off" id="UpdateInfoForm">
		<div>
			<input type="text" id="id" name="id" placeholder="아이디" value="${loginUser.id }" disabled>
		</div>
		
		<div>
			<input type="password" id="pwd" name="pwd" placeholder="비밀번호">
		</div>
		<div>
			<input type="password" id="pwd2" name="pwd2" placeholder="비밀번호 확인">
		</div>
		
		<div>
			<input type="text" id="email" name="email" placeholder="이메일" value="${loginUser.email}">
		</div>
		
		<div>
			<input type="text" id="name" name="name" placeholder="이름" value="${loginUser.name}">
		</div>
		
		<div>
			<input type="text" id="nickname" name="nickname" placeholder="닉네임" value="${loginUser.nickname}">
		</div>
		
		<div>
			<input type="button" value="회원정보수정" id="updateInfoBtn">
		</div>
	</form>
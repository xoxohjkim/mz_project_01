<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<script>

$(document).ready(function(){
	var rtn = true;
	
	$('#updateInfoBtn').on("click", function(){
			
		var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		
		if(!/^[a-zA-Z0-9]{8,20}$/.test($('#pwd').val())){
		     alert("비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.");
		     return;
		}else if($('#pwd').val() == "" && $('#pwd2').val() == ""){
			alert('비밀번호를 입력해주세요.');
			  return;
		}else if($('#pwd').val() != $('#pwd2').val()){
			alert('비밀번호가 일치하지 않습니다.');
			return;
		}else if('${loginUser.email}' != $('#email').val() ){
			if(confirmInfo('email', $('#email').val()) == false ){
				return;
			}
		
		}else if(!reg_email.test($('#email').val()) || $('#email').val() == "") {
			alert('이메일 형식에 맞게 입력해주세요.');
				return;
		}else if('${loginUser.nickname}' != $('#nickname').val()){
			if(confirmInfo('nickname', $('#nickname').val() ) == false){
				return;
			}
		}
		
		$.ajax({
			url: "/updateInfo",
			type: "POST",
			data : $('#updateInfoForm').serialize(),
			success: function(res) {
				if(res == 1){
					if(confirm('정보가 변경되었습니다.')==true){
						window.location.reload();
					}
				}
			},
			error: function(request,status,error){
				alert('에러' + request.status+request.responseText+error);
			}
		}); 
	
		
	});
	
});


function confirmInfo(condition, keyword){
		var rtn = true;
		
		var data = {
				cond : condition,
				key : keyword
			}
		
		var word = "";
		if(condition == 'email'){
			word = "이메일";
		}else if(condition == 'nickname'){
			word = "닉네임";
		}
			
		$.ajax({
			url: "/infoConfirm",
			type: "POST",
			contentType:"application/json; charset=utf-8",
			dataType: "json",
			cache : false,
			async: false,
			data : JSON.stringify(data),
			success: function(res) {
				if(res == 1){
					alert('이미 존재하는 ' + word + '입니다.');
					rtn = false;
				}
			},
			error: function(request,status,error){
				alert('에러' + request.status+request.responseText+error);
				rtn = false;
			}
		});
		
		return rtn;
		
}


</script>


<div>
	<a href="/mypage/boardList">내 게시글</a>
	<a href="/mypage/commentList">내 댓글</a>
	<a href="/mypage/myInfo">회원정보 변경</a>
</div>

회원 정보 변경
	<form action="post" id="updateInfoForm">
		<div>
			<input type="text" id="id" name="id" placeholder="아이디" value="${loginUser.id }">
		</div>
		<div>
			<input type="password" id="pwd" name="pwd" placeholder="비밀번호">
		</div>
		<div>
			<input type="password" id="pwd2" name="pwd2" placeholder="비밀번호 확인">
		</div>
		
		<div>
			<input type="text" id="email" name="email"  placeholder="이메일" value="${loginUser.email}">
		</div>
		
		<div>
			<input type="text" id="name" name="name" placeholder="이름" value="${loginUser.name}">
		</div>
		
		<div>
			<input type="text" id="nickname" name="nickname"  placeholder="닉네임" value="${loginUser.nickname}">
		</div>
		
		<div>
			<input type="button" value="회원정보수정" id="updateInfoBtn">
		</div>
	</form>
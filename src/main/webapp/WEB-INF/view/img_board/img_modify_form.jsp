<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<style>
.imgFile {cursor:pointer;}
</style>
<script>
$(document).ready(function(){
	
	var kind = getParameterByName('kind');
	var act = getParameterByName('act');
	
	/*
	var length = ${fileList.size()};
	console.log(length)
	var arr = new Array();
	for(var i=0; i <length; i++) {
		arr.push(${fileList[i].path})
		console.log(${fileList[i].path})
	}
	console.log(arr)
	*/
	
	
	var delArr = new Array();

	$(document).on('click', '#imgFile', function(){
		//console.log($(this).attr('path'))
		$(this).remove()
		
		var data = $(this).attr('imgId');
		delArr.push(data);
		
		console.log(delArr)
	});
	
	 $("input[type='file']").change(function(e){
		
	      //div 내용 비워주기
	      $('#preview').empty();
	      	
	      var files = e.target.files;
	      var arr = Array.prototype.slice.call(files);
	      
	      //업로드 가능 파일인지 체크
	      for(var i=0;i<files.length;i++){
	        if(!checkExtension(files[i].name,files[i].size)){
	          return false;
	        }
	      } 
	      preview(arr);

	 });//file change
	 
	 
	 $('#imgModifyBtn').click(function(){
		 
			var formData = new FormData();
	        var file = $("input[id='uploadFile']")[0].files;
	        
	        for(var pair of formData.entries()) {
	            console.log(pair[0]+ ', '+ pair[1]); 
	      	}
	      
	      	for(var i=0; i<file.length; i++){
	         	console.log(file[i]);
	         	formData.append('uploadFile', file[i]);
	      	}
	      	
	      	formData.append('title' , $('#title').val())
	      	formData.append('content', $('#content').val())
	      	formData.append('delArr', delArr)
	      	
	      	console.log(formData)
	      	
	      	if (confirm("게시물을 수정하시겠습니까?")) {
				$.ajax({
					type: "POST",
					enctype: "multipart/form-data",
					url: "/form/" + kind + "/" + act + "/" + ${board.id},
					data : formData,
					processData: false,
					contentType: false,
					success: function (data) {
						window.location.href='/board?kind=${kind}&id=${board.id}';
					},
					error: function(request,status,error){
						alert('에러' + request.status+request.responseText+error);
						console.log(error);
					}
				});
	      	}
		});

});

//파라미터값 가져오기
//model로 받은값 오류남ㅠ
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function checkExtension(fileName,fileSize){

    var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
    var maxSize = 20971520;  //20MB
    
    if(fileSize >= maxSize){
      alert('파일 사이즈 초과');
      $("input[type='file']").val("");  //파일 초기화
      return false;
    }
    
    if(regex.test(fileName)){
      alert('업로드 불가능한 파일이 있습니다.');
      $("input[type='file']").val("");  //파일 초기화
      return false;
    }
    return true;
  }
  
  
function preview(arr){
	arr.forEach(function(f){
	  
	  //파일명이 길면 파일명...으로 처리
	  var fileName = f.name;
	  if(fileName.length > 10){
	    fileName = fileName.substring(0,7)+"...";
	  }
	  
	  //div에 이미지 추가
	  var str = '<div class="preview_div">';
	  //str += '<span>'+fileName+'</span><br>';
	  
	  //이미지 파일 미리보기
	  if(f.type.match('image.*')){
	    var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
	    reader.onload = function (e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
	      //str += '<button type="button" id="delBtn" value="'+f.name+'" fname="' + f.name + '">x</button><br>';
	      str += '<ul><li><img src="'+ e.target.result+'" title="'+f.name+'" width=150 height=150/>';
	      str += '</li></ul></div>';
	      $(str).appendTo('#preview');
	    } 
	    reader.readAsDataURL(f);
	  }else{
	    str += '<img src="/resources/img/fileImg.png" title="'+f.name+'" width=100 height=100 />';
	    $(str).appendTo('#preview');
	  }
	});//arr.forEach
}


</script>


<form method="post" name="dataForm" id="data" autocomplete="off" enctype="multipart/form-data">
	<table>
		<tr>
			<td>제목</td> <td><input type="text" name="title" id="title" value="${board.title}"></td>
		</tr>
		
		<tr>
			<td>내용</td> <td><input type="text" name="content" id="content" value="${board.content}"></td>
		</tr>
	</table>
		<div class="fileArea">
			  <input type="file" name="file" id="uploadFile" multiple >
			  <div id="preview">
			  </div>
		</div>
</form>

		<div style="clear:both">
		<span>등록된 파일</span>
			<div>
			 	 <c:forEach var="file" items="${fileList}" varStatus="status">
					<img src="/resources/upload/img/${board.member.id}/${file.name}" imgId="${file.id}" id="imgFile" class="imgFile" width="100">
				</c:forEach>
			</div>
		</div>
<div>
	<input type="button" id="goImgList" value="목록">
	<input type="button" id="imgModifyBtn" value="수정">
	
</div>	
	
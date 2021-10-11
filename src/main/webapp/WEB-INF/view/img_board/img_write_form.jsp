<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.js"  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<script>
$(document).ready(function(){
	
	var kind = getParameterByName('kind');
	var act = getParameterByName('act');
	
	$('#gnrWriteBtn').click(function(){
		 if (confirm("게시물을 작성하시겠습니까?")) {
			
			 $.ajax({
					url : "/form/" + kind + "/" + act ,
					type:'POST',
					data: $('#doImgWrite').serialize(),
					success:function(res){
						if(res == 1){
							window.location.href='main'
						}
					},
					error: function(request,status,error){
						alert('에러' + request.status+request.responseText+error);
						console.log(error);
					}
				});
		 } 
		
	});
	

//파일업로드	
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
	    
	    
	$('#imgWriteBtn').click(function(){
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
      	
      	if (confirm("게시물을 작성하시겠습니까?")) {
			$.ajax({
				type: "POST",
				enctype: "multipart/form-data",
				url: "/form/" + kind + "/" + act,
				data : formData,
				processData: false,
				contentType: false,
				success: function (data) {
					window.location.href='/board?kind=' + kind
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
	      str += '<ul><li><img src="'+ e.target.result+'" title="'+f.name+'" width=150 height=150 />';
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
~이미지게시판~
<form method="post" name="dataForm" id="data" autocomplete="off" enctype="multipart/form-data">
	<table>
		<tr>
			<td>제목</td> <td><input type="text" name="title" id="title"></td>
		</tr>
		
		<tr>
			<td>내용</td> <td><input type="text" name="content" id="content"></td>
		</tr>
	</table>
		<div class="fileArea">
			  <input type="file" name="file" id="uploadFile" multiple >
			  <div id="preview"></div>
		</div>
</form>

<div class="img_write_btns">
	<input type="button" id="imgWriteBtn" value="글쓰기">
</div>
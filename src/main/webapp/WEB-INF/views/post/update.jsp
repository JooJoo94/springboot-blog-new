<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for="title">제목</label> 
			<input type="text" class="form-control" placeholder="Enter Title" id="title" value="${post.title}">
		</div>
		<div class="form-group">
			<label for="content">내용</label> 
			<textarea class="form-control" rows="5" id="content">${post.content}</textarea>
		</div>
	</form>
	
	<button id="update--submit" value="${post.id}" class="btn btn-primary">수정</button>

</div>

<script>
	$('#update--submit').on('click', function(){
		var data = {
				id: $('#update--submit').val(),
				title: $('#title').val(),
				content: $('#content').val()
			}

			$.ajax({
				type: 'PUT',
				url: '/post/update',
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: 'json'
			}).done(function(r){
				if(r.statusCode == 200){
					alert('수정 성공');
					location.href = '/';
				}else{
					alert('수정 실패');
				}
			}).fail(function(r){
				alert('수정 실패');
			});

	});
		

	
</script>

<%@include file="../include/footer.jsp"%>






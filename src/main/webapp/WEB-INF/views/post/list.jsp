<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>

<div class="container">

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="post" items="${posts}">
				<tr>
					<td>${post.id}</td>
					<td><a href="/post/detail/${post.id}">${post.title}</a></td>
					<td>${post.username}</td>
					<td>${post.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@include file="../include/footer.jsp"%>
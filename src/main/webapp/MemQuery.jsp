<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>查詢會員結果</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

header {
	background-color: #4CAF50;
	color: white;
	text-align: center;
	padding: 20px;
}

.container {
	width: 95vw;
	margin: 30px auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

h2 {
	text-align: center;
	color: #4CAF50;
}

table {
	margin: 0 auto;
	max-width: 90vw;
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table th, table td {
	max-width: 100px;
	word-wrap: break-word;
	padding: 12px;
	text-align: center;
	border: 1px solid #ddd;
}

table th {
	background-color: #4CAF50;
	color: white;
}

table td img {
	width: 100px;
	height: 100px;
	border-radius: 50%;
}

.action-buttons a, input {
	white-space: nowrap;
	margin: 0 auto;
	padding: 1px 12px;
	color: white;
	text-decoration: none;
	border-radius: 4px;
	font-size: 12px;
	border: 0;
}
.pwd {
	background: black;
}

.pwd:hover {
	background: white;
}
.edit-btn {
	background-color: #4CAF50;
}

.edit-btn:hover {
	background-color: #45a049;
}

.delete-btn {
	background-color: #f44336;
}

.delete-btn:hover {
	background-color: #e53935;
}

footer {
	text-align: center;
	padding: 10px;
	background-color: #333;
	color: white;
	margin-top: 40px;
}
</style>
</head>
<body>

	<header>
		<h1>查詢會員結果</h1>
	</header>

	<div class="container">
		<h2>會員資料</h2>
		<table>
			<thead>
				<tr>
					<th>編號</th>
					<th>帳號</th>
					<th>密碼</th>
					<th>姓</th>
					<th>名</th>
					<th>頭像</th>
					<th>電話</th>
					<th>生日</th>
					<th>性別</th>
					<th>狀態</th>
					<th>建立日期</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${memVO.memberID}</td>
					<td>${memVO.account}</td>
					<td><span class="pwd">${memVO.password}</span></td>
					<td>${memVO.lastName}</td>
					<td>${memVO.firstName}</td>
					<td>
					<c:choose>
							<c:when test="${not empty memVO.avatarBase64}">
								<img src="data:image/png;base64,${memVO.avatarBase64}"
									alt="Avatar">
							</c:when>
						</c:choose>
					</td>
					<td>${memVO.phoneNumber}</td>
					<td>${memVO.birthday}</td>
					<td>${memVO.gender}</td>
					<td>${memVO.status}</td>
					<td>${memVO.createTime}</td>
					<td class="action-buttons">
						<form class="button-group" action="mem.do" method="post"
							name="form">
							<input type="submit" class="edit-btn" value="修改"> <input
								type="hidden" name="action" value="getInfoBeforeUpdate">
							<input type="hidden" name="member_id" value="${memVO.memberID}">
						</form>
						<form class="button-group" action="mem.do" method="post"
							name="form">
							<input type="submit" class="delete-btn" value="刪除"> <input
								type="hidden" name="action" value="delete"> <input
								type="hidden" name="member_id" value="${memVO.memberID}">
						</form>
					</td>

				</tr>
			</tbody>
		</table>
		<div class="action-buttons" style="text-align: center; margin: 10px">
			<a href="MemIndex.jsp" class="edit-btn">回到首頁</a>
		</div>
	</div>
	<footer>
		<p>&copy; 2024 會員管理系統. 版權所有.</p>
	</footer>
	<script>
		function init() {
			let delBtn = document.querySelectorAll(".delete-btn");
			delBtn.forEach((i)=>{
				i.addEventListener('click',
						function() {
							if (!confirm("是否要刪除此會員")) {
								event.preventDefault();
							}
						})

			})
		}
		window.addEventListener('load',init);
	</script>

</body>
</html>

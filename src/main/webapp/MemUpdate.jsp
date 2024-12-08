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
<title>會員更新資料</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.container {
	width: 800px;
	margin: 50px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	display: block;
}

img {
	display:block;
	width: 200px;
	margin: 0 auto;
	border-radius: 50%;
}

h2 {
	text-align: center;
	color: #333;
}

form {
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 20px;
}

label {
	font-size: 14px;
	color: #333;
	margin-bottom: 5px;
}

input, select {
	width: 100%;
	padding: 10px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.full-width {
	grid-column: span 2;
}

.form-group {
	display: flex;
	flex-direction: column;
}

.form-group input[type="file"] {
	padding: 0;
	border: none;
	background-color: #f9f9f9;
}

.form-group input[type="file"]::file-selector-button {
	background-color: #4CAF50;
	color: white;
	padding: 10px;
	border: none;
	cursor: pointer;
}

.form-group input[type="file"]::file-selector-button:hover {
	background-color: #45a049;
}

.form-actions {
	text-align: center;
	grid-column: span 2;
}

.form-actions button {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
}

.form-actions button:hover {
	background-color: #45a049;
}
</style>
</head>
<body>

	<div class="container">
		<h2>更新會員資料</h2>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<td><c:choose>
				<c:when test="${not empty memVO.avatarBase64}">
					<div class="img">
						<img src="data:image/png;base64,${memVO.avatarBase64}"
							alt="Avatar">
					</div>
				</c:when>
			</c:choose></td>

		<form action="mem.do" method="post" name="form"
			enctype="multipart/form-data">
			<!-- 編號 -->
			<div class="form-group full-width">
				<label for="id">編號</label> <input type="text" id="id" name="id"
					value="${memVO.memberID}" disabled>
			</div>

			<!-- 帳號 -->
			<div class="form-group">
				<label for="account">帳號</label> <input type="text" id="account"
					name="account" value="${memVO.account}" readonly>
			</div>

			<!-- 密碼 -->
			<div class="form-group">
				<label for="password">密碼</label> <input type="password"
					id="password" name="password" value="${memVO.password}" required>
			</div>

			<!-- 姓 -->
			<div class="form-group">
				<label for="lastName">姓</label> <input type="text" id="lastName"
					name="lastName" value="${memVO.lastName}" required>
			</div>

			<!-- 名 -->
			<div class="form-group">
				<label for="firstName">名</label> <input type="text" id="firstName"
					name="firstName" value="${memVO.firstName}" required>
			</div>

			<!-- 頭像 -->
			<div class="form-group full-width">
				<label for="avatar">頭像</label> <input type="file" id="avatar"
					name="avatar" value="${memVO.avatar}" accept="image/*">
			</div>

			<!-- 電話 -->
			<div class="form-group">
				<label for="phoneNumber">電話</label> <input type="tel"
					id="phoneNumber" name="phoneNumber" value="${memVO.phoneNumber}"
					required>
			</div>

			<!-- 生日 -->
			<div class="form-group">
				<label for="birthday">生日</label> <input type="date" id="birthday"
					name="birthday" value="${memVO.birthday}" required>
			</div>

			<!-- 性別 -->
			<div class="form-group">
				<label for="gender">性別</label> <select id="gender" name="gender"
					value="${memVO.gender}" required>
					<option value="M">男性</option>
					<option value="F">女性</option>
					<option value="X">其他</option>
				</select>
			</div>

			<!-- 狀態 -->
			<div class="form-group">
				<label for="status">狀態</label> <select id="status" name="status"
					value="${memVO.status}" required>
					<option value="1">啟用</option>
					<option value="0">停用</option>
				</select>
			</div>

			<!-- 建立日期 -->
			<div class="form-group">
				<label for="createTime">建立日期</label> <input type="input"
					id="createTime" name="createTime" value="${memVO.createTime}"
					disabled>
			</div>

			<!-- 提交按鈕 -->
			<div class="form-actions">
				<button type="submit">更新</button>
				<input type="hidden" name="member_id" value="${memVO.memberID}">
				<input type="hidden" name="action" value="update">
			</div>
		</form>
		<br>
		<form action="mem.do" method="post" name="form">
			<div class="form-actions">
				<button type="submit">回到列表</button>
				<input type="hidden" name="action" value="getAll">
			</div>

		</form>
	</div>

</body>
</html>

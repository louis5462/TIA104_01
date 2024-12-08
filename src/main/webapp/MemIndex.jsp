<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員管理首頁</title>
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
	width: 100%;
	max-width: 800px;
	margin: 50px auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

h2 {
	text-align: center;
	color: #4CAF50;
}

.search-section {
	margin: 30px 0;
	text-align: center;
}

.search-section input[type="text"] {
	width: 60%;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.button-group {
	display: flex;
	justify-content: center;
	gap: 20px;
	margin-top: 30px;
}

.button-group button, input[type="submit"] {
	padding: 12px 20px;
	font-size: 16px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.button-group button:hover {
	background-color: #45a049;
}

.button-group button:active {
	background-color: #388e3c;
}

footer {
	text-align: center;
	margin-top: 40px;
	padding: 10px;
	background-color: #333;
	color: white;
}
</style>
</head>
<body>

	<header>
		<h1>會員管理系統</h1>
	</header>

	<div class="container">
		<h2>快速搜尋</h2>

		<!-- 會員編號快速搜尋 -->
		<div class="search-section">
			<c:if test="${not empty errorMsgs}">
					<c:forEach var="message" items="${errorMsgs}">
						<p style="color: red">${message}</p>
					</c:forEach>
			</c:if>

			<form class="button-group" action="mem.do" method="post" name="form">
				<input type="text" id="member_id" name="member_id"
					placeholder="輸入會員編號..."> <input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</form>
		</div>

		<!-- 按鈕區域 -->
		<div class="button-group">
			<!-- 所有會員列表按鈕 -->
			<a href="ListAllMem.jsp">
				<button>所有會員列表</button>
			</a>
			<!-- 新增會員按鈕 -->
			<a href="MemAdd.jsp">
				<button>新增會員</button>
			</a>
		</div>
	</div>

	<footer>
		<p>&copy; 2024 會員管理系統. 版權所有.</p>
	</footer>

</body>
</html>

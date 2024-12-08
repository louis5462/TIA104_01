package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.member.model.MemService;
import com.member.model.MemVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		switch (action) {
		case "getOne_For_Display":
			getOneInfo(req, res);
			break;
		case "getInfoBeforeUpdate":
			getInfoBeforeUpdate(req, res);
			break;
		case "update":
			update(req, res);
			break;
		case "insert":
			insert(req, res);
			break;
		case "getAll":
			getAll(req, res);
			break;
		case "delete":
			delete(req, res);
			break;
		default:
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			errorMsgs.add("動作發生錯誤，請重試一次");
			RequestDispatcher failureView = req.getRequestDispatcher("/MemIndex.jsp");
			failureView.forward(req, res);
			return;
		}
	}

	public void getOneInfo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 轉送到首頁
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		// 取得會員編號
		String str = req.getParameter("member_id");
		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入會員編號");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/MemIndex.jsp");
			failureView.forward(req, res);
			return;
		}
		// 錯誤判斷，確認會員是否為空
		Integer memberID = null;
		try {
			memberID = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("編號有誤，請輸入數字");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/MemIndex.jsp");
			failureView.forward(req, res);
			return;
		}
		// 開始查詢
		MemService memSvc = new MemService();
		MemVO memVO = memSvc.getOneMem(memberID);
		if (memVO == null) {
			errorMsgs.add("查無此人");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/MemIndex.jsp");
			failureView.forward(req, res);
			return;
		}
		// 成功查詢
		req.setAttribute("memVO", memVO);
		String url = "/MemQuery.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	public void getInfoBeforeUpdate(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// 接收更改的ID
		Integer memberID = Integer.valueOf(req.getParameter("member_id").trim());
		// 查資料
		MemService memSvc = new MemService();
		MemVO memVO = memSvc.getOneMem(memberID);
		// 取得資料，存入req
		req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
		String url = "/MemUpdate.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
		successView.forward(req, res);
	}

	public void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 設定錯誤訊息
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		// 接收參數
		String account = req.getParameter("account");
		String accountReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";
		if (account == null || account.trim().length() == 0) {
			errorMsgs.add("沒有輸入電子信箱");
		} else if (!account.trim().matches(accountReg)) {
			errorMsgs.add("請輸入電子信箱");
		}
		
		String password = req.getParameter("password").trim();
		String pwdReg = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*+\\-=\\*]{6,12}$";
		if (password == null || password.trim().length() == 0) {
			errorMsgs.add("沒有輸入密碼");
		} else if (!password.trim().matches(pwdReg)) {
			errorMsgs.add("請輸入6-12字密碼，並應包含至少一個英文字母、一個數字，可接受下列符號：!@#$%^&*+-=*");
		}
		
		String lastName = req.getParameter("lastName").trim();
		String nameReg = "^[\\u4e00-\\u9FFFa-zA-Z]{1,20}$";
		if (lastName == null || lastName.trim().length() == 0) {
			errorMsgs.add("沒有輸入姓氏");
		} else if (!lastName.trim().matches(nameReg)) {
			errorMsgs.add("請輸入英文或中文姓");
		}

		String firstName = req.getParameter("firstName").trim();
		if (firstName == null || firstName.trim().length() == 0) {
			errorMsgs.add("沒有輸入名字");
		} else if (!firstName.trim().matches(nameReg)) {
			errorMsgs.add("請輸入英文或中文名");
		}

		java.sql.Date birthday = null;
		try {
			birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
		} catch (IllegalArgumentException e) {
			birthday = new java.sql.Date(System.currentTimeMillis());
			errorMsgs.add("日期錯誤");
		}

		String phoneNumber = req.getParameter("phoneNumber").trim();
		String phoneReg = "^[0-9]{8,10}$";
		if (phoneNumber == null || phoneNumber.trim().length() == 0) {
			errorMsgs.add("沒有輸入電話");
		} else if (!phoneNumber.trim().matches(phoneReg)) {
			errorMsgs.add("請輸入電話（8-10碼）");
		}
		
		String gender = req.getParameter("gender").trim();
		if (gender == null || gender.trim().length() == 0) {
			errorMsgs.add("沒有輸入性別");
		}
		byte[] avatar = null;
		Part parts = req.getPart("avatar");
		InputStream in = parts.getInputStream();
		avatar = in.readAllBytes(); // Java 9 的新方法
		in.read(avatar);
		in.close();

		Byte status = null;
		try {
			status = Byte.valueOf(req.getParameter("status").trim());
		} catch (NumberFormatException e) {
			status = 0;
			errorMsgs.add("請選擇狀態");
		}

		Integer memberID = Integer.valueOf(req.getParameter("member_id").trim());
		MemVO memVO = new MemVO();
		memVO.setAccount(account);
		memVO.setPassword(password);
		memVO.setLastName(lastName);
		memVO.setFirstName(firstName);
		memVO.setAvatar(avatar);
		memVO.setBirthday(birthday);
		memVO.setPhoneNumber(phoneNumber);
		memVO.setGender(gender);
		memVO.setStatus(status);
		memVO.setMemberID(memberID);

		// 有錯誤訊息就送回去
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
			RequestDispatcher failureView = req.getRequestDispatcher("/MemUpdate.jsp");
			failureView.forward(req, res);
			return; // 程式中斷
		}

		// 更新資料
		MemService memSvc = new MemService();
		memVO = memSvc.update(account, password, lastName, firstName, avatar, birthday, phoneNumber, gender, status,
				memberID);
		// 更新完成，跳轉到查詢頁面
		req.setAttribute("member_ID", memberID); // 資料庫update成功後，查詢最新結果在跳轉回去
		getOneInfo(req, res);
	}

	public void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 設定錯誤訊息
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		// 接收參數
		String account = req.getParameter("account");
		String accountReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";
		if (account == null || account.trim().length() == 0) {
			errorMsgs.add("沒有輸入電子信箱");
		} else if (!account.trim().matches(accountReg)) {
			errorMsgs.add("請輸入電子信箱");
		}

		String password = req.getParameter("password").trim();
		String pwdReg = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*+\\-=\\*]{6,12}$";
		if (password == null || password.trim().length() == 0) {
			errorMsgs.add("沒有輸入密碼");
		} else if (!password.trim().matches(pwdReg)) {
			errorMsgs.add("請輸入6-12字密碼，並應包含至少一個英文字母、一個數字，可接受下列符號：!@#$%^&*+-=*");
		}
		
		String lastName = req.getParameter("lastName").trim();
		String nameReg = "^[\\u4e00-\\u9FFFa-zA-Z]{1,20}$";
		if (lastName == null || lastName.trim().length() == 0) {
			errorMsgs.add("沒有輸入姓氏");
		} else if (!lastName.trim().matches(nameReg)) {
			errorMsgs.add("請輸入英文或中文姓");
		}

		String firstName = req.getParameter("firstName").trim();
		if (firstName == null || firstName.trim().length() == 0) {
			errorMsgs.add("沒有輸入名字");
		} else if (!firstName.trim().matches(nameReg)) {
			errorMsgs.add("請輸入英文或中文名");
		}

		java.sql.Date birthday = null;
		try {
			birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
		} catch (IllegalArgumentException e) {
			birthday = new java.sql.Date(System.currentTimeMillis());
			errorMsgs.add("日期錯誤");
		}

		String phoneNumber = req.getParameter("phoneNumber").trim();
		String phoneReg = "^[0-9]{8,10}$";
		if (phoneNumber == null || phoneNumber.trim().length() == 0) {
			errorMsgs.add("沒有輸入電話");
		} else if (!phoneNumber.trim().matches(phoneReg)) {
			errorMsgs.add("請輸入電話（8-10碼）");
		}
		
		String gender = req.getParameter("gender").trim();
		if (gender == null || gender.trim().length() == 0) {
			errorMsgs.add("沒有輸入性別");
		}
		byte[] avatar = null;
		Part parts = req.getPart("avatar");
		InputStream in = parts.getInputStream();
		avatar = in.readAllBytes(); // Java 9 的新方法
		in.read(avatar);
		in.close();

		Byte status = null;
		try {
			status = Byte.valueOf(req.getParameter("status").trim());
		} catch (NumberFormatException e) {
			status = 0;
			errorMsgs.add("請選擇狀態");
		}

		MemVO memVO = new MemVO();
		memVO.setAccount(account);
		memVO.setPassword(password);
		memVO.setLastName(lastName);
		memVO.setFirstName(firstName);
		memVO.setAvatar(avatar);
		memVO.setBirthday(birthday);
		memVO.setPhoneNumber(phoneNumber);
		memVO.setGender(gender);
		memVO.setStatus(Byte.valueOf("1"));

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("MemVO", memVO);
			RequestDispatcher failureView = req.getRequestDispatcher("/MemAdd.jsp");
			failureView.forward(req, res);
			return;
		}
		// 呼叫服務
		MemService memSvc = new MemService();
		memVO = memSvc.insert(account, password, lastName, firstName, avatar, birthday, phoneNumber, gender, status);
		// 轉向到全部顯示
		String url = "/ListAllMem.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	public void getAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String url = "/ListAllMem.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

	public void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 取得參數值
		Integer memberID = Integer.valueOf(req.getParameter("member_id"));
		// 執行刪除
		MemService memSvc = new MemService();
		memSvc.delete(memberID);
		// 轉回去查詢頁面
		String url = "/ListAllMem.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}
}

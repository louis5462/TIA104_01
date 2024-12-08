package com.member.model;
import java.sql.Date;
import java.util.List;

public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemDAO();
	}

	public MemVO insert(String account, String password, String lastName, String firstName, byte[] avatar,
			Date birthday, String phoneNumber, String gender, Byte status) {
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
		dao.insert(memVO);
		return memVO;	
	}

	public MemVO update(String account, String password, String lastName, String firstName, byte[] avatar,
			Date birthday, String phoneNumber, String gender, Byte status, Integer memberID) {
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
		dao.update(memVO);
		return memVO;	

	}
	
	public void delete(Integer memberID) {
		dao.delete(memberID);
	}

	public MemVO getOneMem(Integer memberID) {
		return dao.findByPK(memberID);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
}

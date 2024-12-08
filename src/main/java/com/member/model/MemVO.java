package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Base64;

public class MemVO implements java.io.Serializable {
	private Integer memberID;
	private String account;
	private String password;
	private String lastName;
	private String firstName;
	private byte[] avatar;
	private Date birthday;
	private String phoneNumber;
	private String gender;
	private Byte status;
	private Timestamp createTime;
	private String avatarBase64;

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public String getAvatarBase64() {
		return avatarBase64;
	}
	
	public void setAvatarBase64(byte[] avatar) {
		if (avatar != null) {
			this.avatarBase64 = Base64.getEncoder().encodeToString(avatar);
		}
	}


}

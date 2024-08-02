package com.kh.model.vo;

import java.sql.Date;

/* 
 * vo (Value Object)
 * : 한 명의 회원에 대한 데이터를 저장할 수 있는 객체 
 *  --> DB 테이블에서 한 행의 데이터  
 */


public class Member {
	
	// 필드부 => 
	// DB 테이블의 컬럼명과 매칭시켜서 정의 
	
	/*
	 * USERNO NUMBER PRIMARY KEY, -- 회원번호 USERID VARCHAR2(20) NOT NULL
	 * UNIQUE,--회원아이디 -- 회원 비밀번호 USERPW VARCHAR2(20) NOT NULL, -- 회원 비밀번호 USERNMME
	 * VARCHAR2(20) NOT NULL,-- 이름 GENDER CHAR(1) CHECK(GENDER IN('M','F')), AGE
	 * NUMBER , -- 나이 EMAIL VARCHAR2(30), ADDRESS VARCHAR2(100),-- 주소 PHONE
	 * VARCHAR2(13), HOBBY VARCHAR2(50), ENROLLDATE DATE DEFAULT SYSDATE NOT NULL
	 * --가입일
	 */
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private char gender;
	private int age;
	private String email;
	private String address;
	private String phone;
	private String hobby;
	private Date enrollDate; // java.sql.Date
	

	//생성자부
	public Member() {}
	
	public Member(String userId, String userPw,String userName,char gender) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.gender = gender;
		// 매개변수 생성자를 이용하거나 Setter메소드를 이용하여 
		
	}

	public Member(int userNo, String userId, String userPw, String userName, char gender, int age, String email,
			String address, String phone, String hobby, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPw() {
		return userPw;
	}


	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getHobby() {
		return hobby;
	}


	public void setHobby(String hobby) {
		this.hobby = hobby;
	}


	public Date getEnrollDate() {
		return enrollDate;
	}


	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}


	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", address=" + address + ", phone="
				+ phone + ", hobby=" + hobby + ", enrollDate=" + enrollDate + "]";
	}



	
	
	

	
	
}

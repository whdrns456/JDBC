package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {

	
	// Controller : View를 통해서 사용자가 요청한 기능에 대해 처리하는 력할
	// 요청 받은 메소드에서 전달된 데이터를 가공처리한 후 DAO로 전달 
	// DAO로부터 반환 받은 결과에 따라 성공인지 실패인지 판단후 응답
	
	
	/**
	 *  회원 추가 요청을 처리할 메소드 	
	 *  @param userId 회원아이디
	 */
	public void insertMember(String userId,String  userPw,String name,char gender) {
		// View로부터 전달 받은 값들을 dao에게 바로 전달하지 않고 
		// 어딘가(Member)에 담아서 전달 
		Member m = new Member(userId,userPw,name,gender);
		
		int result =new MemberDao().insertMember(m);
		
		if(result > 0) {
			// DAO의 결과 : 성공했을 경우 성공화면 표시 
			new MemberMenu().displaySuccess("회원추가 성공");
		}else {
			// DAO의 결과 : 실패했을 경우 실패화면 표시 
			new MemberMenu().displayFailed("회원 추가 실패!");
		}
		
		
		
		
	}
	
	public void deleteUser(String id) {
		
		int result =new MemberDao().deleteUser(id);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("회원삭제성공");
		}else{
			new MemberMenu().displayFailed("회원탈퇴실패");
		}
		
		
	}
	
	public void selectList() {
		ArrayList<Member>list = new MemberDao().selectList();
		
		if(list.isEmpty()) {
			// 조회결과가 없다면 (=> 리스트가 비어있다면)
			new MemberMenu().displayNoData(" 전체 조회 결과가 없음! ");
		}else {
			new MemberMenu().displayMemberList(list);
		}	
	}
	
	public void searchById(String userId) {
		Member m = new MemberDao().selectByUserId(userId);
		
		if(m == null) {
			// 검색결과가 없는 경우 
			new MemberMenu().displayNoData(userId + "에 해당하는 결과가 없음!");
		}else {
			new MemberMenu().displayMember(m);
		}		
	}
	
	public void modifyByUser(String id,String pw, 
						   	String name, String address,
						   	String phone, String hobby){
		// 전달 받은 값을 Member 객체로 생성하여 DAO에게 전달 
		
		Member m = new Member();
		m.setUserId(id);
		m.setUserPw(pw);
		m.setUserName(name);
		m.setAddress(address);
		m.setPhone(phone);
		m.setHobby(hobby);
		
		// DML (UPDATE) --> int(처리된 행 수)
		
		
		int result  = new MemberDao().updateById(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("회원 정보 수정 성공");
		}else {
			new MemberMenu().displaySuccess("회원 정보 수정 실패");
		}
		
		
		
	}
	
	/** 사용자가 입력한 이름을 키워드로 하여 회원 정보 검색
	 *  @param keyword 이름에 대한 키워드 값
	 * 
	 */
	public void searchByName(String keyword) {
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("해당 이름에 대한 회원 정보가 없습니다.");
		}else {
			new MemberMenu().displayMemberList(list);
		}
		
		
	}
	
	
	
}

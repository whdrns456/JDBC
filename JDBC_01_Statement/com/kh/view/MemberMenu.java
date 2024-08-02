package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

// View : 사용자가 보게될 시각적인 요소(화면) 출력 및 입력 
public class MemberMenu {

	// Scanner 객체 생성 
	private Scanner sc = new Scanner(System.in);
	// MemberController 객체 생성 
	private MemberController mc = new MemberController();
	
	/*
	 * 	* 사용자가 보게 될 첫 화면 : 메인메뉴
	 * 
	 */


	public void mainMenu() {
		
		while(true) {
		
		System.out.println("====== 회원 관리 프로그램 =======");
		System.out.println("1. 회원 추가");     // C
		System.out.println("2. 회원 전체 조회"); //  R
		System.out.println("3. 회원 아이디로 검색"); // R
		System.out.println("4. 회원 정보 수정"); // U
		System.out.println("5. 회원 탈퇴");    // D 
		System.out.println("6. 회원이름으로 키워드 검색");
		System.out.println("9. 프로그램 종료"); // 
		
		System.out.println(">> 메뉴 번호 : ");
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch(menu) {
		
		
		case 1: 
			addMenu();
			break;

		case 2: 
			mc.selectList();
			break;

		case 3: 
			searchById();
			break;

		case 4: 
			modifyByUser();
			break;

		case 5:
			deleteUser();
			break;

		case 9: 
			System.out.println("프로그램 종료합니다...");
			return;	
		
		}
		
		}
	}
	
	public void addMenu() {
		System.out.println("아이디 : ");
		String userId = sc.nextLine();
		
		System.out.println("비밀번호 : ");
		String userPw = sc.nextLine();
		
		System.out.println("이름 : ");
		String name = sc.nextLine();
		
		System.out.println("성별(M/F)");
		char gender = sc.nextLine().charAt(0);
	
		// 회원 추가 요청 
		mc.insertMember(userId, userPw,name, gender);
	
	}
	
	
	public void displaySuccess(String messge) {
		
		System.out.println("서비스 요청 성공 : " + messge);
	}
	
	/** 
	 * 요청 처리 후 실패했을 경우 사용자에게 표시할 화면 
	 * @param message 실패메세지 
	 * 
	 */
	public void displayFailed(String messge) {
		
		System.out.println("서비스 요청 실패 : " + messge);
	}

	/** 조회 결과가 없을 때 사용자에게 표시할 화면 
	 * @param messge 조회 결과에 메세지
	 * 
	 */
	
	public void displayNoData(String messge){
		System.out.println(messge);
}
		public void displayMemberList(ArrayList<Member> list){
		System.out.println("---조회 결과 ----");
		
		for(Member m : list) {
			System.out.println(m);
		}
}
	public void searchById() {
		System.out.println("조회할 아이디 : ");
		String id = sc.nextLine();
	
		mc.searchById(id);
}
	
	public void displayMember(Member m) {
		System.out.println("--조회결과--");
		System.out.println(m);
		
	}
	
	public void deleteUser() {
		System.out.println("삭제할 아이디를 입력하세요");
		String id = sc.nextLine();
		
		mc.deleteUser(id);
		
	}
	
	public void modifyByUser() {
		
		System.out.println("===회원 정보 수정===");
		System.out.println("회원 아이디 : ");
		String id = sc.nextLine();
		
		System.out.println("1.변경할 비밀번호");
		String pw = sc.nextLine();
		System.out.println("2.변경할 이름");
		String name = sc.nextLine();
		System.out.println("3.변경할 주소");
		String address = sc.nextLine();
		System.out.println("4.변경할 연락처");
		String phone = sc.nextLine();
		System.out.println("5.변경할 취미");
		String hobby = sc.nextLine();
		
		// 컨트롤러에게 입력 받은 값 전달(=> 회원 정보 수정해줘, 요청)
		mc.modifyByUser(id,pw,name,address,phone,hobby);
	
		

	
	
		
				
		
	}
	

	
	
	
	
} 

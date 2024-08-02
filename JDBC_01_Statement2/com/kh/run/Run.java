package com.kh.run;

import com.kh.view.MemberMenu;

public class Run {

	public static void main(String[] args) {
		
		/* 
		 * * MCV 
		 * 
		 *  - M (Model) : 데이터 처리 담당(데이터 담기위한 클래스(vo))
		 *   			= > 데이터가 저장된 공간(db,file,..)과 
		 *  					 직접 접근해서 주고받는 클래스 (DAO)
		 *  - V : (View) : 화면을 담당하는 (사용자가 보게 되는 시작적인 요소, 출력/입력)
		 *  
		 *  - C : (Controller) : 사용자의 요청을 처리해주는 담당 (요청에 대한 처리 후 그에 해당하는 응답)
		 * 
		 */
			
		// Run : 프로그램 실행담당 (=> 사용자가 보게될 첫 화면을 보여주고 끝)
		
		new MemberMenu().mainMenu();
		
	}

}

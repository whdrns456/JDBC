package com.kh.model.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.vo.Member;

public class MemberDao {
	
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER_NAME = "C##JDBC";
	private final String PASSWORD = "JDBC";
	
	
	// DAO Data Access Object : DB에 직접 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과 반환()
	// => 

	/*
	 *  * JDBC용 객체 
	 *  - Connection : DB 연결정보를 담고있는 객체 
	 *  - Statement : 연결된 DB에 sql문을 전달해서 실행하고 결과를 받아주는 객체 
	 *  - ResultSet : SELECT문(DQL) 실행 후 조회된 결과물을 담고있는 객체 
	 *  
	 *  * JDBC 과정 
	 *  [1] jdbc driver 등록 : 사용할 DBMS(오라클)에서 제공하는 클래스 등록 
	 *  [2] Connection 객체 생성 : DB정보 (url, 사용자명, 비밀번호)를 통해 해당 DB와 연결하면서 생성 
	 *  [3] Statement 객체 생성 : Connection 객체를 이용해서 생성. sql문을 실행하고 결과를 받아줄 것임 
	 *  [4] sql문 전달해서 실행 후 결과 받기 
	 *  	- SELECT문 실행 시 ResultSet 객체로 조회 결과를 받음 
	 *  	- DML(INSERT/UPDATE/DELETE) 실행 시 int 타입으로 처리 결과를 받음(처리된 행 수 )
	 *  [5] 결과에 대한 처리 
	 *     - ResultSet 객체에서 데이터를 하나씩 추출하여 vo객체로 옮겨 담기 (저장)
	 *     - DML의 경우 트랜잭션 처리 (성공했을 때는 commit, 실패했을 떄는 rollback)
	 *  [6] 자원 반납 => 생성 역순으로    
	 */
	
	/**
	 *  사용자가 입력한 정보들을 DB에 추가하는 메소드 (=> 회원 정보 추가)
	 * @param m 사용자가 입력한 값들이 담겨있는 Member 객체 
	 * 
	 */
	
	public int insertMember(Member m) {
		// insert문 --> int (처리된 행 수) --> 트랜잭션 처리 
		int result = 0;
		
		String sql = "INSERT INTO MEMBER VALUES (SEQ_USERNO.NEXTVAL, "
				+"'"+m.getUserId() + "', "         //'user01',
				+"'"+m.getUserPw() + "', " 
				+"'"+ m.getUserName() + "', "
				+"'"+ m.getGender() + "', "
					+ m.getAge() + ","
				+"'"+m.getEmail() + "',"
				+"'"+m.getAddress()+"',"
				+"'"+ m.getPhone() + "',"
				+"'"+ m.getHobby() + "', SYSDATE)";
		
		System.out.println("----------------------------------------------");
		System.out.println(sql);
		System.out.println("----------------------------------------------");
	Connection conn = null;
	java.sql.Statement stmt = null;
		
	try {
		// 1) jdbc driver 등록 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2) Connection 객체 생성 => DB 연결 
		conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
		conn.setAutoCommit(false);
		
		// 3) Statement 객체 생성 
		stmt = conn.createStatement();
		// 4) 실행 후 결과 받기 
		result = stmt.executeUpdate(sql);
		// 5) 트랜잭션 처리 
		if(result > 0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		
		
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	
	
	return result;
	
	}
	
	public ArrayList<Member> selectList() {
		// SELECT문(여러 행 조회) --> ResultSet 객체 --> ArrayList<Member>에 담기 
		ArrayList<Member>list = new ArrayList<Member>(); // 리스트가 비어있는 상태
		
		
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rset = null; 
		
		
		try {
			// 1) jdbc driver 등록 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
		
			// 3) Statement 객체 생성 
			stmt = conn.createStatement();
			
			
			// 4)5) sql문 실행 후 결과 받기 
			
			// 실행할 sql문 
			String sql = "SELECT * FROM MEMBER";
			
			rset = stmt.executeQuery(sql);
			
			// 조회는 트랜잭션 처리가 필요없다. 
			
			
			// 6) sql문 실행 결과를 하나하나 추출하기 
			// * 데이터 있는 지 여부 확인 => rset.next(); boolean (데이터가 있으면 true, 없으면 false)
			
			while(rset.next()) {
				// * 데이터를 가지고 올 때, '컬럼명' 또는 '컬럼순번'을 사용하여 추출 
				Member m = new Member(
						rset.getInt("USERNO"),
						rset.getString("USERID"),
						rset.getString("USERPW"),
						rset.getString("USERNAME"),
						rset.getString("GENDER") == null ? ' ' : rset.getString("GENDER").charAt(0),
						rset.getInt("AGE"),
						rset.getString("EMAIL"),
						rset.getString("ADDRESS"),
						rset.getString("PHONE"),
						rset.getString("HOBBY"),
						rset.getDate("ENROLLDATE")
						); 
				
				list.add(m);
				// 컬럼명으로 추출 
		}
			
			// 반복문이 끝나는 ㅣㅅ점..
			// 조회된 데이터가 없다면 ? 리스트는 비어 있을 것임 
			// 조회된 데이턱 
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
		}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	  return list;
	}
	
	public Member selectByUserId(String userId) {
		
		Member m = null;
		
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문 
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = '" + userId + "'";
		
		
		System.out.println("--------------------------------------");
		System.out.println(sql);
		System.out.println("--------------------------------------");
		try {
		// 1) jdbc driver 등록 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2) Connection 객체 생성
		conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
		
		// 3) Statement 객체 생성 
		stmt = conn.createStatement();
		
		// 4) sql문 실행 및 결과
		rset = stmt.executeQuery(sql);
		if(rset.next()) {
			 m = new Member(
					rset.getInt("USERNO"),
					rset.getString("USERID"),
					rset.getString("USERPW"),
					rset.getString("USERNAME"),
					rset.getString("GENDER") == null ? ' ' : rset.getString("GENDER").charAt(0),
					rset.getInt("AGE"),
					rset.getString("EMAIL"),
					rset.getString("ADDRESS"),
					rset.getString("PHONE"),
					rset.getString("HOBBY"),
					rset.getDate("ENROLLDATE")
					); 
		}
		// 조건문이 끝난 시점에...
		// 조회된 데이터가 없다면 ? m(Member) --> null
		// 조회된 데이터가 있다면 ? m(Member) --> 새로 생성된 객체
		
		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return m;
	}
	
	public int deleteUser(String id) {
		
	
	int result = 0;
		
		 
		

	// JDBC용 객체 선언 및 null로 초기화
	Connection conn = null;
	java.sql.Statement stmt = null;
		
	
	// 실행시킬 쿼리문 작성
	String sql = "DELETE FROM MEMBER WHERE USERID = '" + id + "'";
	
	try {
		
		// 1) jdbc driver 등록 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2) Connection 객체 생성 => DB 연결 
		conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
		conn.setAutoCommit(false); 
		
		// 3) Statement 객체 생성 : 쿼리문을 실행하고 결과를 받아주는 것  
		stmt = conn.createStatement();
		
		// 4) 실행 후 결과 받기 
		result = stmt.executeUpdate(sql);
		
		
		// 5) DML문을 실행시켰으므로 트랜잭션 처리  
		if(result > 0) {
		conn.commit();
		}else {
		conn.rollback();
		}
		
		
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			stmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	return result;
	

	}
	
	public int updateById(Member m){
		
		String sql = "UPDATE MEMBER "
				+ "SET USERPW = '" + m.getUserPw() +"',"
				+		"USERNAME = '" + m.getUserName() + "',"
				+		"ADDRESS = '" + m.getAddress() + "',"
				+ 		"PHONE = '" + m.getPhone() + "'," 
				+ 		"HOBBY = '" + m.getHobby() + "'"
				+ "WHERE USERID = '" + m.getUserId() + "'";
		
		Connection conn = null;
		java.sql.Statement stmt = null;
		
		int result = 0;
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		conn.setAutoCommit(false); 
		
		stmt = conn.createStatement();
		
		result  = stmt.executeUpdate(sql);
		
		
		if(result > 0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
		
	}
	
	public ArrayList<Member>selectByUserName(String keyword) {
		
		ArrayList<Member>list = new ArrayList<Member>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 조회 결과를 받아줄 rset 
		
		String sql = "SELECT * FROM MEMBER "
				+ "WHERE USERNAME LIKE ?";
		
		// 연결 연산자로 '%'|| " + keyword + "||'%'"
		
		try {
			// 1) jdbc driver 등록 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
			// 3) Statement 객체 생성 
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+ keyword +"%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member(
						rset.getInt("USERNO"),
						rset.getString("USERID"),
						rset.getString("USERPW"),
						rset.getString("USERNAME"),
						rset.getString("GENDER") == null ? ' ' : rset.getString("GENDER").charAt(0),
						rset.getInt("AGE"),
						rset.getString("EMAIL"),
						rset.getString("ADDRESS"),
						rset.getString("PHONE"),
						rset.getString("HOBBY"),
						rset.getDate("ENROLLDATE")
						); 
				
				list.add(m);
				
				}
			
		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
			rset.close();
			pstmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}
		}
			
		return list;
		}
	
	
	
	
	
}
	
	
	
	







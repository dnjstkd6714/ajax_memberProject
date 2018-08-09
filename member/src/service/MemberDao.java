// 2018. 8. 8(수) 이원상	MemberDao.java
package service;

import java.sql.*;
import java.util.*;
import service.Member;

public class MemberDao {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	String sql = null;
	public MemberDao() {				// default 생성자
		super();						// 조상클래스 생성자 호출(object)
	}
	/*
	 * 메소드 용도 : 회원의 목록 조회
	 * 매개변수 : Connection 인스턴스 참조값(DB연결주소)
	 * 리턴값 : 전체 회원을 List data type으로 리턴
	 * Member클래스 인스턴수변수(프로퍼티,멤버변수) String id,String pw,String name,int age, String gender
	 */
	public List<Member> selectMemberList(Connection connection) {
		List<Member> memberList = new ArrayList<Member>();
		try {
			this.sql = "select id,pw,name,age,gender from member";
			this.preparedStatement = connection.prepareStatement(sql);
			this.resultSet = this.preparedStatement.executeQuery();
			while(this.resultSet.next()) {
				Member member = new Member();
				member.setId(this.resultSet.getString(1));
				member.setPw(this.resultSet.getString(2));
				member.setName(this.resultSet.getString(3));
				member.setAge(this.resultSet.getInt(4));
				member.setGender(this.resultSet.getString(5));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			service.DBUtill.close(null, this.preparedStatement, null);
		}
		return memberList; 
	}
	
	/*
	 * 메소드 용도 : 1명의 회원 등록
	 * 매개변수 : Connection 인스턴스 참조값(DB연결주소), Member클래스의 인스턴스 참조값
	 * 리턴값 : boolean(입력한 행이 0초과시 true, 1미만시 false)
	 * Member클래스 인스턴수변수(프로퍼티,멤버변수) String id,String pw,String name,int age, String gender
	 */
	public boolean insertMember(Connection connection, Member member) {
		int result=0;
		try {
			this.sql = "INSERT INTO member (id, pw, name, age, gender) VALUES (?, ?, ?, ?, ?)";
			this.preparedStatement = connection.prepareStatement(sql);
			this.preparedStatement.setString(1, member.getId());
			this.preparedStatement.setString(2, member.getPw());
			this.preparedStatement.setString(3, member.getName());
			this.preparedStatement.setInt(4, member.getAge());
			this.preparedStatement.setString(5, member.getGender());
			result=this.preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			service.DBUtill.close(null, this.preparedStatement, null);
		}
		if(result > 0) {
			return true;											// 입력된 행이 1 이상일시 리턴값 true 
		}else {
			return false;											// 입력된 행의 수가 1보다 작을시 false
		}
	}
	
	/*
	 * 메소드 용도 : 1명의 회원 수정
	 * 매개변수 : Connection 인스턴스 참조값(DB연결주소), Member클래스의 인스턴스 참조값
	 * 리턴값 : boolean(삭제한 행이 0초과시 true, 1미만시 false
	 * Member클래스 인스턴수변수(프로퍼티,멤버변수) String id,String pw,String name,int age, String gender
	 */
	public boolean updateMember(Connection connection, Member member) {
		int result=0;
		try {
			this.sql = "UPDATE member SET id=?,pw=?,name=?,age=?,gender=? WHERE id=?";
			this.preparedStatement = connection.prepareStatement(sql);
			this.preparedStatement.setString(1, member.getId());
			this.preparedStatement.setString(2, member.getPw());
			this.preparedStatement.setString(3, member.getName());
			this.preparedStatement.setInt(4, member.getAge());
			this.preparedStatement.setString(5, member.getGender());
			this.preparedStatement.setString(6, member.getId());
			result = this.preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			service.DBUtill.close(null, this.preparedStatement, null);
		}
		if(result > 0) {
			return true;											// 수정된 행이 1 이상일시 리턴값 true 
		}else {
			return false;											// 수정된 행의 수가 1보다 작을시 false
		}
	}
	
	/*
	 * 메소드 용도 : 1명의 회원 삭제
	 * 매개변수 : Connection 인스턴스 참조값(DB연결주소), Member클래스의 인스턴스 참조값
	 * 리턴값 : boolean(삭제한 행이 0초과시 true, 1미만시 false)
	 * Member클래스 인스턴수변수(프로퍼티,멤버변수) String id,String pw,String name,int age, String gender
	 */	
	public boolean deleteMember(Connection connection, Member member) {
		int result=0;
		try {
			this.sql = "DELETE FROM member WHERE id = ?";
			this.preparedStatement = connection.prepareStatement(sql);
			this.preparedStatement.setString(1, member.getId());
			result = this.preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			service.DBUtill.close(null, this.preparedStatement, null);
		}
		if(result > 0) {
			return true;											// 삭제된 행이 1 이상일시 리턴값 true 
		}else {
			return false;											// 삭제된 행의 수가 1보다 작을시 false
		}
	}
}

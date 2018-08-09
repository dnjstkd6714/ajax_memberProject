// 2018. 8. 8(수) 이원상	MemberService.java
package service;

import java.sql.*;
import java.util.*;

public class MemberService {
	private Connection connection = null;
	private MemberDao memberDao;
		
	public MemberService(){												// 생성자
		super();														// 조상클래스 생성자 호출
		try {		
			this.connection = service.DBUtill.getConnection();			// 인스턴스 생성시 DB연결	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 메소드 용도 : 회원의 목록 조회
	 * 매개변수 : 없음
	 * 리턴값 : 전체 회원을 List data type으로 리턴
	 * Member클래스 인스턴수변수(프로퍼티,멤버변수) String id,String pw,String name,int age, String gender
	 */
	public List<Member> getMember() {
		List<Member> memberList = new ArrayList<Member>();
		this.memberDao = new MemberDao();
		try {
			this.connection.setAutoCommit(false);
			memberList = this.memberDao.selectMemberList(this.connection);
			this.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtill.close(null, null, this.connection);
		}
		return memberList;
	}
	
	/*
	 * 메소드 용도 : 1명의 회원 등록
	 * 매개변수 : Member클래스의 인스턴스 참조값
	 * 리턴값 : boolean(수정한 행이 있을시 true, 없을시 false)
	 * Member클래스 인스턴수변수(프로퍼티,멤버변수) String id,String pw,String name,int age, String gender
	 */
	public boolean addMember(Member member) {
		this.memberDao = new MemberDao();
		boolean result = false;
		try {
			this.connection.setAutoCommit(false);
			result = this.memberDao.insertMember(this.connection, member);
			this.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtill.close(null, null, this.connection);
		}
		return result;
	}
	
	/*
	 * 메소드 용도 : 1명의 회원 수정
	 * 매개변수 : Member클래스의 인스턴스 참조값
	 * 리턴값 : boolean(수정한 행이 있을시 true, 없을시 false)
	 * Member클래스 인스턴수변수(프로퍼티,멤버변수) String id,String pw,String name,int age, String gender
	 */
	public boolean modifyMember(Member member) {
		this.memberDao = new MemberDao();
		boolean result = false;
		try {
			this.connection.setAutoCommit(false);
			result = this.memberDao.updateMember(this.connection, member);
			this.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtill.close(null, null, this.connection);
		}
		return result;
		
	}
	
	/*
	 * 메소드 용도 : 1명의 회원 삭제
	 * 매개변수 : Member클래스의 인스턴스 참조값
	 * 리턴값 : boolean(수정한 행이 있을시 true, 없을시 false)
	 * Member클래스 인스턴수변수(프로퍼티,멤버변수) String id,String pw,String name,int age, String gender
	 */	
	public boolean removeMember(Member member) {
		this.memberDao = new MemberDao();
		boolean result = false;
		try {
			this.connection.setAutoCommit(false);
			result = this.memberDao.deleteMember(this.connection, member);
			this.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtill.close(null, null, this.connection);
		}
		return result;
	}
}

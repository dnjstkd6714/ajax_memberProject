// 2018. 8. 8(수) 이원상	Member.java
/*
CREATE TABLE `member` (
	`id` VARCHAR(50) NULL DEFAULT NULL,
	`pw` VARCHAR(50) NULL DEFAULT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`age` INT(11) NULL DEFAULT NULL,
	`gender` VARCHAR(50) NULL DEFAULT NULL
)
*/	
package service;

public class Member {				// 멤버변수 캡슐화
	private String id;				// 회원 아이디
	private String pw;				// 회원 패스워드
	private String name;			// 회원 이름
	private int age;				// 회원 나이
	private String gender;			// 회원 성별
	
	public Member() {				// default 생성자
		super();					// 조상클래스 object 호출
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}

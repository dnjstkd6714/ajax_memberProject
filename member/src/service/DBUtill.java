// 2018. 8. 8(수) 이원상	DBUtill.java
package service;

import java.sql.*;

public class DBUtill {
	/* 메소드 설명 : 클래스 메소드로 mysql root아이디의 member DB연결하기 위함
	 * 매개변수 : 없음
	 * 리턴갑 : DriverManager.getConnection메소드 호출의 결과갑으로 DB연결 Connection data type의 인스턴스 참조값을 리턴 
	*/
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/member?useUnicode=true&characterEncoding=utf-8", "root", "java0000");
	}
	/* 메소드 설명 : JDBC인스턴스를 DB에 반환하기 위한 메소드
	 * 매개변수 : ResultSet의 인스턴스, Statement의 인스턴스, Connection의 인스턴스
	 * 리턴갑 : 없음.
	*/
	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

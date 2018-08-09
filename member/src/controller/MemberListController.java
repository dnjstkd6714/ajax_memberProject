// 2018. 8. 8(수) 이원상	MemberListcontroller.java
package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.Member;
import service.MemberService;

@WebServlet("/MemberListController")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService;   
    public MemberListController() {						// default생성자
        super();										// 조상클래스 생성자 호출
    }
    //doget메소드 오버라이딩
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.memberService = new MemberService();						// 멤버서비스 인스턴스 생성
		List<Member> memberList = memberService.getMember();			// 회원조회 메소드 호출, 리턴값 대입
		Gson gson = new Gson();											// Gson 인스턴스 생성
		String jsonMemberList = gson.toJson(memberList);				// gson.tojson 메소드 (매개변수 obj) 매소드 용도 해당 객체를 json파일에 맞게 String형태로 리턴
		response.setContentType("application/json;charset=utf-8");		// 응답의 콘텐츠 타입 설정(클라이언트의 요청에 응답할 컨텐츠, 캐릭터 설정), 공부해야함
		response.getWriter().write(jsonMemberList);						// 요청페이지로 응답
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}

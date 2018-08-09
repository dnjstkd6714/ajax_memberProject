// 2018. 8. 8(수) 이원상	MemberModifyController.java
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.Member;
import service.MemberService;

@WebServlet("/MemberModifyController")
public class MemberModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService;  
    private Member member;       
    public MemberModifyController() {				// default생성자
        super();									// 조상클래스 생성자 호출
    }
    //dopost 오버라이딩
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");						// post 방식 캐릭터 설정
		this.member = new Member(); 								// Member 인스턴스 생성
		String id = request.getParameter("id");						// par값 받아오기(Json post요청)
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		this.member.setId(id);											// member 인스턴스에 setting
		this.member.setPw(pw);
		this.member.setName(name);
		this.member.setAge(age);
		this.member.setGender(gender);
		this.memberService = new MemberService();						// MemberService클래스내 메소드 호출을 위한 인스턴스 생성
		boolean modifyResult = this.memberService.modifyMember(member);	// 회원수정 메소드 호출
																		// 수정된 행이 있을경우 true, 없을시 false
		if(modifyResult) {	
			response.setContentType("application/json;charset=utf-8");	// 응답의 콘텐츠 타입 설정(클라이언트의 요청에 응답할 컨텐츠, 캐릭터 설정), 공부해야함
			Gson gson = new Gson();										// Gson 인스턴스 생성
			String jsonMember = gson.toJson(this.member);				// gson.tojson 메소드 (매개변수 obj) 매소드 용도 해당 객체를 json파일에 맞게 String형태로 리턴
			response.getWriter().write(jsonMember);						// 요청페이지로 응답			
		}
	}
}

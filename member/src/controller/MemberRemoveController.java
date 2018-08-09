// 2018. 8. 8(수) 이원상	MemberRemoveController.java
package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.Member;
import service.MemberService;

@WebServlet("/MemberRemoveController")
public class MemberRemoveController extends HttpServlet {
    private MemberService memberService;  
    private Member member;       
    private List<Member> memberList = new ArrayList<Member>();
    private Gson gson = new Gson();
	private static final long serialVersionUID = 1L;
       
    public MemberRemoveController() {			// default생성자
        super();								// 조상클래스 생성자 호출
    }
    //doget 오버라이딩
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteRequestId[] = request.getParameterValues("ck[]");	// 넘겨받은 값을 String 배열 변수에 대입
		boolean removeResult =false;									
		for(int i=0; i<deleteRequestId.length; i++) {					// 넘겨받은 배열의 길이만큼 반복
			this.member = new Member();									// Member 인스턴스 생성
			this.memberService = new MemberService();					// memberService 인스턴스 생성
			this.member.setId(deleteRequestId[i]);						// member인스턴스에 클라이언트에게 넘겨받은 id값 대입
			System.out.println(this.member.getId());					// 확인
			removeResult = this.memberService.removeMember(this.member);	// member참조변수 대입하여 반복문안에서 메소드 호출
			if(removeResult) {												// 삭제된 행이 있을시 true, 없을시 false
				this.memberList.add(this.member);							// 삭제할 member인스턴스를 List화
			}
		}		
		response.setContentType("application/json;charset=utf-8");	// 응답의 콘텐츠 타입 설정(클라이언트의 요청에 응답할 컨텐츠, 캐릭터 설정), 공부해야함
		String jsonMember = this.gson.toJson(this.memberList);		// gson.tojson 메소드 (매개변수 obj) 매소드 용도 해당 객체를 json파일에 맞게 String형태로 리턴		
		response.getWriter().write(jsonMember);						// 요청페이지로 응답
		System.out.println(jsonMember+"<--jsonMember");				// 확인
	}
}

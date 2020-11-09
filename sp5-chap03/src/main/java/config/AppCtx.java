package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration  // 이 클래스는 Spring 환경설정과 관련된 클래스이다. 라고 말해주는 애노테이션.
public class AppCtx {     // Assembler클래스를 대신하여 스프링을 사용하는 코드를 작성하는 클래스.

	@Bean // @configuration설정된 클래스의 메소드에서 사용하는 애노테이션.
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());  // MemberRegsiterService 생성자를호출할 때 memberDao()메서드를 호출.
		                                                // 즉, memberDao()가 생성한 객체를 MemberRegisterService 생성자를 통해 주입.
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());  // 이 메서드는 세터 setMemberDao() 메서드를 이용해서 의존 객체를 주입
		return pwdSvc;
	}

}

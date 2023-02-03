package com.hello.springboothello.ex.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hello.springboothello.ex.domain.Member;
import com.hello.springboothello.ex.repository.MemberRepository;


/* 스프링부트 테스트 */
// test는 제일 편한 방법 사용해도 된다
// 단위 테스트가 훨씬 좋은 테스트일 확률이 높다. 스프링부트 없이 테스트하는 법을 연습해야 한다.

@SpringBootTest
@Transactional  // test 시작 전, 실행 후 실행되어 db를 clear해준다.
class MemberServiceIntegrationTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("spring");

		// when
		Long saveId = memberService.join(member);

		// then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	public void 중복회원예외() {
		// given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");

		// when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> memberService.join(member2));

		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}
}

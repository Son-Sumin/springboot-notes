package com.hello.springboothello.ex.repository;

import java.util.List;
import java.util.Optional;

import com.hello.springboothello.ex.domain.Member;

public interface MemberRepository {
	Member save(Member member);
<<<<<<< HEAD

=======
	
>>>>>>> 7b996a9b4ce24fb4022fc6f51e525f4bca9e4ea0
	Optional<Member> findById(Long id);   // Optional: null에 대한 처리 용이
	Optional<Member> findByName(String name);
	List<Member> findAll();

}

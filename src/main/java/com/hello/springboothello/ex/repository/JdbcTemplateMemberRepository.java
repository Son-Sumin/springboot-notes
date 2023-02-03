package com.hello.springboothello.ex.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.hello.springboothello.ex.domain.Member;


public class JdbcTemplateMemberRepository implements MemberRepository {
<<<<<<< HEAD

	private final JdbcTemplate jdbcTemplate;

=======
	
	private final JdbcTemplate jdbcTemplate;
	
>>>>>>> 7b996a9b4ce24fb4022fc6f51e525f4bca9e4ea0
//	@Autowired  // 생성자가 딱 하나이면 생략 가능
	public JdbcTemplateMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);  // 스프링이 자동으로 dataSource injection해줌
	}

	@Override
	public Member save(Member member) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
<<<<<<< HEAD

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", member.getName());
		// 여기까지 insert문 역할. 아래는 setter 역할

=======
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", member.getName());
		// 여기까지 insert문 역할. 아래는 setter 역할
		
>>>>>>> 7b996a9b4ce24fb4022fc6f51e525f4bca9e4ea0
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		member.setId(key.longValue());
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return jdbcTemplate.query("select * from member", memberRowMapper());
	}
<<<<<<< HEAD

=======
	
>>>>>>> 7b996a9b4ce24fb4022fc6f51e525f4bca9e4ea0
	private RowMapper<Member> memberRowMapper() {
		return (rs, rowNum) -> {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				return member;
			};
	}

}

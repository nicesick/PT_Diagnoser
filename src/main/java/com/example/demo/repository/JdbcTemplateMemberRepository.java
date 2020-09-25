package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Member;

@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTemplateMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Member member) {
		int rslt = jdbcTemplate.update("INSERT INTO GUEST (USER_ID, PWD, E_NUM, EMAIL , NAME) VALUES (?,?,?,?,?) ", member.getId(), member.getPwd(), member.geteNum(),member.getEmail(), member.getName());
		return rslt;
	}

	@Override
	public Optional<Member> findById(Long id) {
		List<Member> result = jdbcTemplate.query("select * from GUEST where id = ? ", memberRowMapper(), id);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = jdbcTemplate.query("select * from GUEST where name = ? ", memberRowMapper(), name);
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		List<Member> result = jdbcTemplate.query("select * from GUEST ", memberRowMapper());
		return result;
	}

	private RowMapper<Member> memberRowMapper() {
		return (rs, rowNum) -> {
			Member item = new Member();
			item.setId(rs.getString("id"));
			item.setName(rs.getString("name"));
			return item;
		};
	}
}

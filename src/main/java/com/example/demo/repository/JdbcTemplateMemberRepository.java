package com.example.demo.repository;

import com.example.demo.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

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
	public Optional<Member> findById(String id) {
		List<Member> result = jdbcTemplate.query("select * from GUEST where user_id = ? ", memberRowMapper(), id);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = jdbcTemplate.query("select * from GUEST where name = ? ", memberRowMapper(), name);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByENum(String eNum) {
		List<Member> result = jdbcTemplate.query("select * from GUEST where e_num = ? ", memberRowMapper(), eNum);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByEmail(String email) {
		List<Member> result = jdbcTemplate.query("select * from GUEST where email = ? ", memberRowMapper(), email);
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		List<Member> result = jdbcTemplate.query("select * from GUEST ", memberRowMapper());
		return result;
	}
	
	@Override
	public int modifyUserInfo(Member member) {
		int result = jdbcTemplate.update(""
				+ "UPDATE GUEST SET PWD = ?, EMAIL=? WHERE USER_ID = ?",
				member.getPwd(), member.getEmail(), member.getId());
		return result;
	}

	private RowMapper<Member> memberRowMapper() {
		return (rs, rowNum) -> {
			Member item = new Member();
			item.setId(rs.getString("user_id"));
			item.setPwd(rs.getString("pwd"));
			item.seteNum(rs.getString("e_num"));
			item.setEmail(rs.getString("email"));
			item.setName(rs.getString("name"));
			return item;
		};
	}
}

package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.MemberResult;
import com.example.demo.dto.MemberResultSum;

@Repository
public class JdbcTemplateMemberResultRepository implements MemberResultRepository {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTemplateMemberResultRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public MemberResult save(MemberResult memberResult) {
		return null;
	}

	@Override
	public Optional<MemberResultSum> findById(String id) {
		List<MemberResultSum> result = jdbcTemplate.query("SELECT a.USER_ID        AS userId" +
				"      , SUM(CASE WHEN b.CATEGORY = 's' THEN a.SCORE ELSE 0 END) AS speechResult" +
				"      , SUM(CASE WHEN b.CATEGORY = 'p' THEN a.SCORE ELSE 0 END) AS presentationResult" +
				"      , SUM(CASE WHEN b.CATEGORY = 'u' THEN a.SCORE ELSE 0 END) AS unrestResult" +
				"      , SUM(CASE WHEN b.CATEGORY = 'e' THEN a.SCORE ELSE 0 END) AS evaluationResult" +
				"  FROM RESULT a" +
				"      , QUESTION b" +
				" WHERE a.QUESTION_ID = b.ID" +
				"   AND a.USER_ID = ? " +
				"GROUP BY a.USER_ID", memberResultSumRowMapper(), id);

		return result.stream().findAny();
	}

	@Override
	public List<MemberResultSum> findAll() {
		List<MemberResultSum> results = jdbcTemplate.query("SELECT a.USER_ID        AS userId" +
				"      , SUM(CASE WHEN b.CATEGORY = 's' THEN a.SCORE ELSE 0 END) AS speechResult" +
				"      , SUM(CASE WHEN b.CATEGORY = 'p' THEN a.SCORE ELSE 0 END) AS presentationResult" +
				"      , SUM(CASE WHEN b.CATEGORY = 'u' THEN a.SCORE ELSE 0 END) AS unrestResult" +
				"      , SUM(CASE WHEN b.CATEGORY = 'e' THEN a.SCORE ELSE 0 END) AS evaluationResult" +
				"  FROM RESULT a" +
				"      , QUESTION b" +
				" WHERE a.QUESTION_ID = b.ID" +
				"GROUP BY a.USER_ID", memberResultSumRowMapper());

		return results;
	}

	@Override
	public int saveResult(MemberResult result) {
		int rslt = jdbcTemplate.update("Insert into RESULT (USER_ID, QUESTION_ID, SCORE) VALUES (?,?,?) ",result.getUser_id(), result.getQuestion_id(), result.getScore());
		return rslt;
	}
	
	private RowMapper<MemberResultSum> memberResultSumRowMapper() {
		return (rs, rowNum) -> {
			MemberResultSum item = new MemberResultSum();

			item.setUser_id(rs.getString("userId"));
			item.setSpeechResult(rs.getInt("speechResult"));
			item.setPresentationResult(rs.getInt("presentationResult"));
			item.setUnrestResult(rs.getInt("unrestResult"));
			item.setEvaluationResult(rs.getInt("evaluationResult"));

			return item;
		};
	}
	
	
	
}

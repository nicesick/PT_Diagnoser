package com.example.demo.repository;

import com.example.demo.dto.MemberResult;
import com.example.demo.dto.MemberResultSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

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
				", a.WORK_DTIM      AS workDtim" +
				", SUM(CASE WHEN a.CATEGORY = 's' THEN a.SCORE ELSE 0 END) AS speechResult" +
				", SUM(CASE WHEN a.CATEGORY = 'p' THEN a.SCORE ELSE 0 END) AS presentationResult" +
				", SUM(CASE WHEN a.CATEGORY = 'u' THEN a.SCORE ELSE 0 END) AS unrestResult" +
				", SUM(CASE WHEN a.CATEGORY = 'e' THEN a.SCORE ELSE 0 END) AS evaluationResult" +
				"  FROM RESULT a" +
				"  AND a.USER_ID = ?" +
				"GROUP BY a.USER_ID, a.WORK_DTIM", memberResultSumRowMapper(), id);

		return result.stream().findAny();
	}

	@Override
	public Optional<MemberResultSum> findByENum(String eNum) {
		List<MemberResultSum> result = jdbcTemplate.query("SELECT a.USER_ID        AS userId" +
				", a.WORK_DTIM      AS workDtim" +
				", SUM(CASE WHEN a.CATEGORY = 's' THEN a.SCORE ELSE 0 END) AS speechResult" +
				", SUM(CASE WHEN a.CATEGORY = 'p' THEN a.SCORE ELSE 0 END) AS presentationResult" +
				", SUM(CASE WHEN a.CATEGORY = 'u' THEN a.SCORE ELSE 0 END) AS unrestResult" +
				", SUM(CASE WHEN a.CATEGORY = 'e' THEN a.SCORE ELSE 0 END) AS evaluationResult" +
				"  FROM RESULT a" +
				"  AND a.E_NUM = ?" +
				"GROUP BY a.USER_ID, a.WORK_DTIM", memberResultSumRowMapper(), eNum);

		return result.stream().findAny();
	}

	@Override
	public Optional<MemberResultSum> findByEmail(String email) {
		List<MemberResultSum> result = jdbcTemplate.query("SELECT a.USER_ID        AS userId" +
				", a.WORK_DTIM      AS workDtim" +
				", SUM(CASE WHEN a.CATEGORY = 's' THEN a.SCORE ELSE 0 END) AS speechResult" +
				", SUM(CASE WHEN a.CATEGORY = 'p' THEN a.SCORE ELSE 0 END) AS presentationResult" +
				", SUM(CASE WHEN a.CATEGORY = 'u' THEN a.SCORE ELSE 0 END) AS unrestResult" +
				", SUM(CASE WHEN a.CATEGORY = 'e' THEN a.SCORE ELSE 0 END) AS evaluationResult" +
				"  FROM RESULT a" +
				"  AND a.EMAIL = ?" +
				"GROUP BY a.USER_ID, a.WORK_DTIM", memberResultSumRowMapper(), email);

		return result.stream().findAny();
	}

	@Override
	public List<MemberResultSum> findAll() {
		List<MemberResultSum> results = jdbcTemplate.query("SELECT a.USER_ID        AS userId" +
				", a.WORK_DTIM      AS workDtim" +
				", SUM(CASE WHEN a.CATEGORY = 's' THEN a.SCORE ELSE 0 END) AS speechResult" +
				", SUM(CASE WHEN a.CATEGORY = 'p' THEN a.SCORE ELSE 0 END) AS presentationResult" +
				", SUM(CASE WHEN a.CATEGORY = 'u' THEN a.SCORE ELSE 0 END) AS unrestResult" +
				", SUM(CASE WHEN a.CATEGORY = 'e' THEN a.SCORE ELSE 0 END) AS evaluationResult" +
				"  FROM RESULT a" +
				" GROUP BY a.USER_ID, a.WORK_DTIM", memberResultSumRowMapper());

		return results;
	}

	@Override
	public int saveResult(MemberResult result) {
		int rslt = jdbcTemplate.update("Insert into RESULT (USER_ID, CATEGORY, SCORE) VALUES (?,?,?) ",result.getUser_id(), result.getCategory(), result.getScore());
		return rslt;
	}
	
	private RowMapper<MemberResultSum> memberResultSumRowMapper() {
		return (rs, rowNum) -> {
			MemberResultSum item = new MemberResultSum();

			item.setUser_id(rs.getString("userId"));
			item.setWorkDtim(rs.getString("workDtim"));
			item.setSpeechResult(rs.getInt("speechResult"));
			item.setPresentationResult(rs.getInt("presentationResult"));
			item.setUnrestResult(rs.getInt("unrestResult"));
			item.setEvaluationResult(rs.getInt("evaluationResult"));

			return item;
		};
	}
	
	
	
}

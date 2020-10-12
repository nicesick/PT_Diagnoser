package com.example.demo.repository;

import com.example.demo.dto.MemberResult;
import com.example.demo.dto.MemberResultSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

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

	/**
	 * makeScoreMap
	 *
	 * 조회한 결과를 가공합니다. 만약, 결과가 아무것도 없다면 workDtim 에 "" 값을 넣어 return 합니다.
	 *
	 * empty example
	 *
	 * result = [{
	 *     user_id : park,
	 *     data : [{
	 *     		workDtim : "",
	 *     		title : [],
	 *     		score : []
	 *     }]
	 * }]
	 *
	 * before example
	 *
	 * result = [
	 * 	{
	 * 	    user_id : park,
	 * 	    workDtim : 20201012112830,
	 * 	    title : 스피치진단,
	 * 	    score : 28
	 * 	},
	 * 	...
	 * ]
	 *
	 * after example
	 *
	 * result = [
	 * {
	 *     user_id : park,
	 *     data : [
	 *     {
	 *     		workDtim : 20201012112830,
	 *     		title : [스피치진단, 프레젠테이션진단, 발표불안진단, ...],
	 *     		score : [28, 49, 55, ...]
	 *     },
	 *     ...
	 *     ]
	 * },
	 * ...
	 * ]
	 *
	 * variables for result
	 *
	 * results
	 * userScores
	 * datas
	 * data
	 *
	 * results = [
	 * 		userScores = {
	 * 		 	user_id : 'park',
	 * 		 	datas : [
	 * 		 		data = {
	 * 		 		    workDtim : '20201012135319',
	 * 		 		    title : [...],
	 * 		 		    score : [...],
	 * 		 		}, ...
	 * 		 	]
	 * 		}, ...
	 * ]
	 *
	 * @param origResults
	 * @param queryParam
	 *
	 * @return 가공된 결과 List
	 */
	private List<Map<String, Object>> makeScoreMap(List<MemberResultSum> origResults, String queryParam) {
		Map<String, Map<String, Object>> 	userScores 	= new HashMap<>();

		/*
		 * origResult 값이 없다면 workDtim 에 빈값 입력
		 */
		if (origResults.size() == 0) {
			Map<String, Object> 	userScore	= new HashMap<>();

			List<Map<String, Object>> 	datas 	= new ArrayList<>();
			Map<String, Object> 		data 	= new HashMap<>();

			data.put("workDtim", "");
			datas.add(data);

			userScore.put("user_id", queryParam);
			userScore.put("data", datas);

			userScores.put(queryParam, userScore);
		} else {
			/*
			 * 있다면, userScore 생성
			 *
			 * data > datas > userScore > userScores 순서로 입력
			 */
			for (MemberResultSum origResult : origResults) {
				Map<String, Object> 		userScore 	= userScores.getOrDefault(origResult.getUser_id(), new HashMap<>());

				if (userScore.containsValue(origResult.getUser_id())) {
					List<Map<String, Object>> 	datas 	= (List<Map<String, Object>>) userScore.get("data");
					boolean 					isFind 	= false;

					for (Map<String, Object> data : datas) {
						if (data.containsValue(origResult.getWorkDtim())) {
							List<String> 		titles 	= (List<String>) data.get("title");
							List<Integer> 		scores 	= (List<Integer>) data.get("score");

							titles.add(origResult.getTitle());
							scores.add(origResult.getScore());

							isFind = true;
							break;
						}
					}

					if (!isFind) {
						Map<String, Object> 	data 	= makeNewData(origResult);
						datas.add(data);
					}
				} else {
					List<Map<String, Object>> 	datas 	= new ArrayList<>();

					Map<String, Object> 		data 	= makeNewData(origResult);
					datas.add(data);

					userScore.put("user_id", origResult.getUser_id());
					userScore.put("data", datas);

					userScores.put(origResult.getUser_id(), userScore);
				}
			}
		}

		List<Map<String, Object>> results = new ArrayList<>(userScores.values());
		return results;
	}

	private Map<String, Object> makeNewData(MemberResultSum origResult) {
		Map<String, Object> data 	= new HashMap<>();

		List<String> 		titles 	= new ArrayList<>();
		List<Integer> 		scores 	= new ArrayList<>();

		titles.add(origResult.getTitle());
		scores.add(origResult.getScore());

		data.put("workDtim", origResult.getWorkDtim());
		data.put("title", titles);
		data.put("score", scores);

		return data;
	}

	/*
	 * TODO
	 * CATEGORY 테이블 INSERT 가 안되는 이유로 CATEGORY b 제거, 이후에 INSERT 되면 추가필요
	 */
	@Override
	public List<Map<String, Object>> findById(String id) {
		List<MemberResultSum> origResult = jdbcTemplate.query("SELECT a.USER_ID        AS userId" +
				"      , a.WORK_DTIM     AS workDtim" +
				"      , a.SCORE         AS score" +
				"      , a.CATEGORY         AS title" +
				"  FROM RESULT a" +
				"  WHERE a.USER_ID = ?" +
				"ORDER BY a.WORK_DTIM, a.CATEGORY", memberResultSumRowMapper(), id);

		List<Map<String, Object>> result = makeScoreMap(origResult, id);
		return result;
	}

	@Override
	public List<Map<String, Object>> findByENum(String eNum) {
		List<MemberResultSum> origResult = jdbcTemplate.query("SELECT a.USER_ID        AS userId" +
				"      , a.WORK_DTIM     AS workDtim" +
				"      , a.SCORE         AS score" +
				"      , b.TITLE         AS title" +
				"  FROM RESULT a, CATEGORY b" +
				" WHERE a.CATEGORY = b.ID" +
				"   AND a.E_NUM = ?" +
				"ORDER BY a.WORK_DTIM, a.CATEGORY", memberResultSumRowMapper(), eNum);

		List<Map<String, Object>> result = makeScoreMap(origResult, eNum);
		return result;
	}

	@Override
	public List<Map<String, Object>> findByEmail(String email) {
		List<MemberResultSum> origResult = jdbcTemplate.query("SELECT a.USER_ID        AS userId" +
				"      , a.WORK_DTIM     AS workDtim" +
				"      , a.SCORE         AS score" +
				"      , b.TITLE         AS title" +
				"  FROM RESULT a, CATEGORY b" +
				" WHERE a.CATEGORY = b.ID" +
				"   AND a.EMAIL = ?" +
				"ORDER BY a.WORK_DTIM, a.CATEGORY", memberResultSumRowMapper(), email);

		List<Map<String, Object>> result = makeScoreMap(origResult, email);
		return result;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		List<MemberResultSum> origResult = jdbcTemplate.query("SELECT a.USER_ID        AS userId" +
				"      , a.WORK_DTIM     AS workDtim" +
				"      , b.TITLE         AS title" +
				"      , a.SCORE         AS score" +
				"  FROM RESULT a, CATEGORY b" +
				" WHERE a.CATEGORY = b.ID" +
				"ORDER BY a.WORK_DTIM, a.CATEGORY", memberResultSumRowMapper());

		List<Map<String, Object>> result = makeScoreMap(origResult, "");
		return result;
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
			item.setTitle(rs.getString("title"));
			item.setScore(rs.getInt("score"));

			return item;
		};
	}
	
	
	
}

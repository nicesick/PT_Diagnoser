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
	 * 조회한 결과를 가공합니다.
	 * 만약, 결과가 아무것도 없다면 workDtim 에 "" 값을 넣어 return 합니다.
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
	 * 	    workDtim : 20201012,
	 * 	    title : 스피치진단,
	 * 	    score : 28,
	 * 	    dedail : 훈련필요,
	 * 	    description : ~~
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
	 *     		workDtim : 20201012,
	 *     		title : [스피치진단, 프레젠테이션진단, 발표불안진단, ...],
	 *     		score : [28, 49, 55, ...],
	 *     		detail : [훈련필요, 보통, 보통, ...],
	 *     		description : [~~, ~~, ~~, ...]
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
	 * 		 	datas 	: [
	 * 		 		data = {
	 * 		 		    workDtim : '20201012',
	 * 		 		    title : [...],
	 * 		 		    score : [...],
	 * 		 		    detail : [...],
	 * 		 		    description : [...],
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
							List<String> 	   details 	= (List<String>) data.get("detail");
							List<String>  descriptions 	= (List<String>) data.get("description");

							titles.add(origResult.getTitle());
							scores.add(origResult.getScore());
							details.add(origResult.getDetail());
							descriptions.add(origResult.getDescription());

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
		List<String>		details	= new ArrayList<>();
		List<String>   descriptions	= new ArrayList<>();

		titles.add(origResult.getTitle());
		scores.add(origResult.getScore());
		details.add(origResult.getDetail());
		descriptions.add(origResult.getDescription());

		data.put("workDtim", origResult.getWorkDtim());
		data.put("title", titles);
		data.put("score", scores);
		data.put("detail", details);
		data.put("description", descriptions);

		return data;
	}

	@Override
	public List<Map<String, Object>> findById(String id) {
		List<MemberResultSum> origResult = jdbcTemplate.query("SELECT userId" +
				"       , workDtim" +
				"       , score" +
				"       , title" +
				"       , detail" +
				"       , description" +
				" FROM" +
				"(SELECT a.USER_ID        AS userId" +
				"       , TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'), 'yyyy-MM-dd')     AS workDtim" +
				"       , ROUND(a.SCORE_100)                                                  AS score" +
				"       , b.TITLE         AS title" +
				"       , c.DETAIL        AS detail" +
				"       , c.DESCRIPTION   AS description" +
				"       , ROW_NUMBER() OVER(PARTITION BY a.USER_ID, TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'),'yyyy-MM-dd'), a.CATEGORY ORDER BY a.SCORE DESC) AS row_num" +
				"  FROM RESULT a" +
				"       , CATEGORY b" +
				"       , SCORE_DETAIL c" +
				"  WHERE a.CATEGORY = b.ID" +
				"    AND a.CATEGORY = c.ID" +
				"    AND a.USER_ID = ?" +
				"    AND a.SCORE BETWEEN c.FROM_SCORE AND c.TO_SCORE" +
				" ORDER BY TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'), 'yyyy-MM-dd') DESC, b.SEQ" +
				")" +
				"  WHERE row_num = 1;", memberResultSumRowMapper(), id);

		List<Map<String, Object>> result = makeScoreMap(origResult, id);
		return result;
	}

	@Override
	public List<Map<String, Object>> findByENum(String eNum) {
		List<MemberResultSum> origResult = jdbcTemplate.query("SELECT userId" +
				"       , workDtim" +
				"       , score" +
				"       , title" +
				"       , detail" +
				"       , description" +
				" FROM" +
				"(SELECT a.USER_ID        AS userId" +
				"       , TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'), 'yyyy-MM-dd')     AS workDtim" +
				"       , ROUND(a.SCORE_100)                                                  AS score" +
				"       , b.TITLE         AS title" +
				"       , c.DETAIL        AS detail" +
				"       , c.DESCRIPTION   AS description" +
				"       , ROW_NUMBER() OVER(PARTITION BY a.USER_ID, TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'),'yyyy-MM-dd'), a.CATEGORY ORDER BY a.SCORE DESC) AS row_num" +
				"  FROM RESULT a" +
				"       , CATEGORY b" +
				"       , SCORE_DETAIL c" +
				"  WHERE a.CATEGORY = b.ID" +
				"    AND a.CATEGORY = c.ID" +
				"    AND a.E_NUM = ?" +
				"    AND a.SCORE BETWEEN c.FROM_SCORE AND c.TO_SCORE" +
				" ORDER BY TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'), 'yyyy-MM-dd') DESC, b.SEQ" +
				")" +
				"  WHERE row_num = 1;", memberResultSumRowMapper(), eNum);

		List<Map<String, Object>> result = makeScoreMap(origResult, eNum);
		return result;
	}

	@Override
	public List<Map<String, Object>> findByEmail(String email) {
		List<MemberResultSum> origResult = jdbcTemplate.query("SELECT userId" +
				"       , workDtim" +
				"       , score" +
				"       , title" +
				"       , detail" +
				"       , description" +
				" FROM" +
				"(SELECT a.USER_ID        AS userId" +
				"       , TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'), 'yyyy-MM-dd')     AS workDtim" +
				"       , ROUND(a.SCORE_100)                                                  AS score" +
				"       , b.TITLE         AS title" +
				"       , c.DETAIL        AS detail" +
				"       , c.DESCRIPTION   AS description" +
				"       , ROW_NUMBER() OVER(PARTITION BY a.USER_ID, TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'),'yyyy-MM-dd'), a.CATEGORY ORDER BY a.SCORE DESC) AS row_num" +
				"  FROM RESULT a" +
				"       , CATEGORY b" +
				"       , SCORE_DETAIL c" +
				"  WHERE a.CATEGORY = b.ID" +
				"    AND a.CATEGORY = c.ID" +
				"    AND a.EMAIL = ?" +
				"    AND a.SCORE BETWEEN c.FROM_SCORE AND c.TO_SCORE" +
				" ORDER BY TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'), 'yyyy-MM-dd') DESC, b.SEQ" +
				")" +
				"  WHERE row_num = 1;", memberResultSumRowMapper(), email);

		List<Map<String, Object>> result = makeScoreMap(origResult, email);
		return result;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		List<MemberResultSum> origResult = jdbcTemplate.query("SELECT userId" +
				"       , workDtim" +
				"       , score" +
				"       , title" +
				"       , detail" +
				"       , description" +
				" FROM" +
				"(SELECT a.USER_ID        AS userId" +
				"       , TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'), 'yyyy-MM-dd')     AS workDtim" +
				"       , ROUND(a.SCORE_100)                                                  AS score" +
				"       , b.TITLE         AS title" +
				"       , c.DETAIL        AS detail" +
				"       , c.DESCRIPTION   AS description" +
				"       , ROW_NUMBER() OVER(PARTITION BY a.USER_ID, TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'),'yyyy-MM-dd'), a.CATEGORY ORDER BY a.SCORE DESC) AS row_num" +
				"  FROM RESULT a" +
				"       , CATEGORY b" +
				"       , SCORE_DETAIL c" +
				"  WHERE a.CATEGORY = b.ID" +
				"    AND a.CATEGORY = c.ID" +
				"    AND a.SCORE BETWEEN c.FROM_SCORE AND c.TO_SCORE" +
				" ORDER BY TO_CHAR(TO_DATE(a.WORK_DTIM, 'yyyyMMddhh24miss'), 'yyyy-MM-dd') DESC, b.SEQ" +
				")" +
				"  WHERE row_num = 1;", memberResultSumRowMapper());

		List<Map<String, Object>> result = makeScoreMap(origResult, "");
		return result;
	}

	@Override
	public int saveResult(MemberResult result) {
		int rslt = jdbcTemplate.update(
						" MERGE INTO RESULT USING ( SELECT 'X' FROM DUAL) AS X                                " +
						"    ON (WORK_DTIM BETWEEN SUBSTR(?,1,8) || '000000' AND SUBSTR(?,1,8) || '999999'    " +
						"                              AND USER_ID = ?                                        " +
						"                              AND CATEGORY = ?)                                    " +
						" WHEN MATCHED THEN                                                                   " +
						" 		     UPDATE SET SCORE = ?,                                                    " +
						" 		     			SCORE_100 = ?                                                 " +
						" 		     			                                                              " +
						" WHEN NOT MATCHED THEN INSERT (USER_ID, CATEGORY, SCORE, WORK_DTIM, SCORE_100)       " +
						" VALUES (?,?,?,?,?) ;",
						result.getWorkDtim(), result.getWorkDtim(), result.getUser_id(),  result.getCategory(), result.getScore(), result.getScore_100(),
				result.getUser_id(), result.getCategory(), result.getScore(), result.getWorkDtim(), result.getScore_100());
		return rslt;
	}
	
	@Override
	public int saveTotresult(MemberResult result) {
		
		int rslt = jdbcTemplate.update( 
				"   MERGE INTO RESULT USING ( SELECT 'X'FROM DUAL) AS X            " +
						"           ON (WORK_DTIM BETWEEN SUBSTR(?,1,8) || '000000' AND SUBSTR(?,1, 8) || '999999'      " +
						"     AND USER_ID = ?                                                                                                    " +
						"     AND CATEGORY = 't' )                                                                                                    " +
						"     WHEN MATCHED THEN UPDATE SET SCORE = ( SELECT SUM(SCORE)                                                                " +
						"                                             FROM ( SELECT A.USER_ID,                                                        " +
						"                                                           A.CATEGORY,                                                       " +
						"                                                           B.SCORE AS SCORE                                                  " +
						"                                                      FROM RESULT A,                                                         " +
						"                                                           SCORE_DETAIL B,                                                   " +
						"                                                           CATEGORY C                                                        " +
						"                                                     WHERE A.CATEGORY = B.ID                                                 " +
						"                                                       AND A.SCORE BETWEEN B.FROM_SCORE                                      " +
						"                                                       AND B.TO_SCORE                                                        " +
						"                                                       AND B.ID = C.ID                                                       " +
						"                                                       AND C.USE_YN = 'Y'                                                    " +
						"                                                       AND A.USER_ID = ?                                                " +
						"                                                       AND A.WORK_DTIM = (SELECT MAX(WORK_DTIM) FROM RESULT WHERE USER_ID = ? AND CATEGORY = 't')) ),                                " +
						"                                  SCORE_100 = ( SELECT SUM(A.SCORE) / SUM(B.SCORE) * 100                                     " +
						"                                                 FROM ( SELECT A.USER_ID,                                                    " +
						"                                                               A.CATEGORY,                                                   " +
						"                                                               B.SCORE AS SCORE                                              " +
						"                                                          FROM RESULT A,                                                     " +
						"                                                               SCORE_DETAIL B,                                               " +
						"                                                               CATEGORY C                                                    " +
						"                                                         WHERE A.CATEGORY = B.ID                                             " +
						"                                                           AND A.SCORE BETWEEN B.FROM_SCORE                                  " +
						"                                                           AND B.TO_SCORE                                                    " +
						"                                                           AND B.ID = C.ID                                                   " +
						"                                                           AND C.USE_YN = 'Y'                                                " +
						"                                                           AND A.USER_ID = ?                                           " +
						"                                                           AND A.WORK_DTIM =(SELECT MAX(WORK_DTIM) FROM RESULT WHERE USER_ID = ? AND CATEGORY = 't')) A,                            " +
						"                                                      ( SELECT MAX(A.SCORE) AS SCORE,                                        " +
						"                                                                A.ID                                                         " +
						"                                                           FROM SCORE_DETAIL A,                                              " +
						"                                                                CATEGORY B                                                   " +
						"                                                          WHERE A.ID = B.ID                                                  " +
						"                                                            AND B.USE_YN = 'Y'                                               " +
						"                                                          GROUP BY A.ID) B)                                                  " +
						"      WHEN NOT MATCHED THEN INSERT  (USER_ID, CATEGORY, WORK_DTIM, SCORE, SCORE_100)                                         " +
						"                                        VALUES(?,                                                                       " +
						"                                               't',                                                                          " +
						"                                               ?,                                                             " +
						"                                               ( SELECT SUM(SCORE)                                                           " +
						"                                                    FROM ( SELECT A.USER_ID,                                                 " +
						"                                                                  A.CATEGORY,                                                " +
						"                                                                  B.SCORE AS SCORE                                           " +
						"                                                             FROM RESULT A,                                                  " +
						"                                                                  SCORE_DETAIL B,                                            " +
						"                                                                  CATEGORY C                                                 " +
						"                                                            WHERE A.CATEGORY = B.ID                                          " +
						"                                                              AND A.SCORE BETWEEN B.FROM_SCORE                               " +
						"                                                              AND B.TO_SCORE                                                 " +
						"                                                              AND B.ID = C.ID                                                " +
						"                                                              AND C.USE_YN = 'Y'                                             " +
						"                                                              AND A.USER_ID = ?                                         " +
						"                                                              AND A.WORK_DTIM = ?) ),                         " +
						"                                               ( SELECT SUM(A.SCORE) / SUM(B.SCORE) * 100                                    " +
						"                                                    FROM ( SELECT A.USER_ID,                                                 " +
						"                                                                  A.CATEGORY,                                                " +
						"                                                                  B.SCORE AS SCORE                                           " +
						"                                                             FROM RESULT A,                                                  " +
						"                                                                  SCORE_DETAIL B,                                            " +
						"                                                                  CATEGORY C                                                 " +
						"                                                            WHERE A.CATEGORY = B.ID                                          " +
						"                                                              AND A.SCORE BETWEEN B.FROM_SCORE                               " +
						"                                                              AND B.TO_SCORE                                                 " +
						"                                                              AND B.ID = C.ID                                                " +
						"                                                              AND C.USE_YN = 'Y'                                             " +
						"                                                              AND A.USER_ID = ?                                         " +
						"                                                              AND A.WORK_DTIM = ? ) A,                         " +
						"                                                         ( SELECT MAX(A.SCORE) AS SCORE,                                     " +
						"                                                                   A.ID                                                      " +
						"                                                              FROM SCORE_DETAIL A,                                           " +
						"                                                                   CATEGORY B                                                " +
						"                                                             WHERE A.ID = B.ID                                               " +
						"                                                               AND B.USE_YN = 'Y'                                            " +
						"                                                             GROUP BY A.ID) B));                                             " ,
						 result.getWorkDtim(), result.getWorkDtim(), result.getUser_id(),  result.getUser_id(),  result.getUser_id(), 
						 result.getUser_id(),  result.getUser_id(),  result.getUser_id(), result.getWorkDtim(),  result.getUser_id(), 
						 result.getWorkDtim(),  result.getUser_id(), result.getWorkDtim());
				
		
		return rslt;
	}
	private RowMapper<MemberResultSum> memberResultSumRowMapper() {
		return (rs, rowNum) -> {
			MemberResultSum item = new MemberResultSum();

			item.setUser_id(rs.getString("userId"));
			item.setWorkDtim(rs.getString("workDtim"));
			item.setTitle(rs.getString("title"));
			item.setScore(rs.getInt("score"));
			item.setDetail(rs.getString("detail"));
			item.setDescription(rs.getString("description"));

			return item;
		};
	}
	
}

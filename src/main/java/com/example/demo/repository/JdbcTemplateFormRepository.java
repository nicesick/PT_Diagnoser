package com.example.demo.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Category;
import com.example.demo.dto.FormItem;
import com.example.demo.dto.Member;

@Repository
public class JdbcTemplateFormRepository implements FormRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTemplateFormRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public FormItem save(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<FormItem> findById(Long id) {
		List<FormItem> result = jdbcTemplate.query("select * from QUESTION where id = ? and use_yn = 'Y'", formRowMapper(), id);
		return result.stream().findAny();
	}

	@Override
	public List<FormItem> findByCategory(String category) {
		List<FormItem> result = jdbcTemplate.query(""
				+ "select A.* "
				+ "from QUESTION A,"
				+ "     CATEGORY B "
				+ "where A.CATEGORY = B.ID "
				+ "AND A.category = ? "
				+ "AND A.use_yn = 'Y' "
				+ "AND B.USE_YN = 'Y'"
				+ " ORDER BY ID ", formRowMapper(),category);
		return result;
	}

	@Override
	public List<FormItem> findAll() {
		List<FormItem> result = jdbcTemplate.query("select * from QUESTION where use_yn = 'Y'", formRowMapper());
		return result;
	}
	@Override
	public List<FormItem> findByPage(Map<String, Object> param) {
		
		/*int pageNo = Integer.parseInt(param.get("pageNo").toString());
		int pageCnt = Integer.parseInt(param.get("pageCnt").toString());
		int startNo = pageNo*pageCnt+1; 
		int endNo = pageNo*pageCnt +pageCnt+1; 
		System.out.println(pageNo + ", " +pageCnt + ",  " + startNo +",  " +endNo);
		*/
		List<FormItem> result = jdbcTemplate.query(""
				+ "SELECT * FROM QUESTION WHERE USE_YN = 'Y' "
				+ "AND CATEGORY = ? "
				//+ "AND ROWNUM BETWEEN ? AND ?"
				+ "ORDER BY ID",formRowMapper(), param.get("category_id").toString().toLowerCase());
		
		for(int i = 0 ; i <result.size();  i++ ) {
			System.out.println(result.get(i).getContent());
		}
		
		return result;
	}
	
	@Override
	public List<Category> getCategory() {
		List<Category> result = jdbcTemplate.query(""
				+ "SELECT ID,"
				+ " TITLE,"
				+ " SURVEY_TYP AS surveyTyp "
				+ " FROM CATEGORY "
				+ "WHERE USE_YN = 'Y' "
				+ "ORDER BY SEQ ",categoryRowMapper());  
		return result;
	}

	private RowMapper<FormItem> formRowMapper() {
		return (rs, rowNum) -> {
			FormItem item = new FormItem();
			item.setId(rs.getInt("id"));
			item.setCategory(rs.getString("category"));
			item.setContent(rs.getString("content"));
			item.setUseYn(rs.getString("use_yn"));
			return item;
		};
	}
	
	private RowMapper<Category> categoryRowMapper() {
		return (rs, rowNum) -> {
			Category item = new Category();
			item.setId(rs.getString("id"));
			item.setTitle(rs.getString("title"));
			item.setSurveyTyp(rs.getString("surveyTyp"));
			return item;
		};
	}

}

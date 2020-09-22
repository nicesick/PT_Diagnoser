package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
		List<FormItem> result = jdbcTemplate.query("select * from QUESTION where id = ? ", formRowMapper(), id);
		return result.stream().findAny();
	}

	@Override
	public Optional<FormItem> findByCategory(String category) {
		List<FormItem> result = jdbcTemplate.query("select * from QUESTION where category = ? ", formRowMapper(),
				category);
		return result.stream().findAny();
	}

	@Override
	public List<FormItem> findAll() {
		List<FormItem> result = jdbcTemplate.query("select * from QUESTION ", formRowMapper());
		return result;
	}

	private RowMapper<FormItem> formRowMapper() {
		return (rs, rowNum) -> {
			FormItem item = new FormItem();
			item.setId(rs.getInt("id"));
			item.setCategory(rs.getString("category"));
			item.setContent(rs.getString("content"));
			return item;
		};
	}
}

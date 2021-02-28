/**
 * 
 */
package com.teko.cinema.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.teko.cinema.model.Cinema;
import com.teko.cinema.model.Seat;




/**
 * @author To Duc
 *
 * Feb 28, 2021--1:22:51 PM
 */
@Repository
public class CinemaRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	public int update(Cinema cinema) {
		String sql = "UPDATE `tbl_cinemas` SET `rows`=:rows, `columns`=:columns, `min_distance`=:minDistance "
				+ "WHERE `id`=1;\r\n";
		SqlParameterSource params = new BeanPropertySqlParameterSource(cinema);
		return jdbcTemplate.update(sql, params);
	}
	public Cinema findById(int cinemaId) {
		String sql = "SELECT * FROM tbl_cinemas WHERE id=:cinemaId;";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cinemaId", cinemaId);
		return jdbcTemplate.query(sql,paramMap, new BeanPropertyRowMapper<Cinema>(Cinema.class)).get(0);
	}
}

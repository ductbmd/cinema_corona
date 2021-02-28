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

import com.teko.cinema.model.Seat;

/**
 * @author To Duc
 *
 *         Feb 28, 2021--1:56:54 PM
 */
@Repository
public class SeatRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public int[] create(List<Seat> seats) {
		String sql = "INSERT INTO `tbl_seats` (`row`, `col`, `status`, `cinema_id`) VALUES (:row, :col, :status, '1');\r\n";

		SqlParameterSource[] batchArgs = new SqlParameterSource[seats.size()];
		int i = 0;
		for (Seat seat : seats) {
			SqlParameterSource Args = new BeanPropertySqlParameterSource(seat);
			batchArgs[i] = Args;
			i++;
		}
		return jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	public int[] updateStatus(List<Seat> seats) {
		String sql = "UPDATE `tbl_seats` SET `status`=:status " + "WHERE `cinema_id`=1 AND col=:col AND row=:row;";
		SqlParameterSource[] batchArgs = new SqlParameterSource[seats.size()];
		int i = 0;
		for (Seat seat : seats) {
			SqlParameterSource Args = new BeanPropertySqlParameterSource(seat);
			batchArgs[i] = Args;
			i++;
		}
		return jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	public List<Seat> findByCinemaId(int cinemaId) {
		String sql = "SELECT * FROM tbl_seats WHERE cinema_id=:cinemaId;";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cinemaId", cinemaId);
		return jdbcTemplate.query(sql, paramMap,new BeanPropertyRowMapper<Seat>(Seat.class));
	}

	public int delete(int cinemaId) {
		String sql = "DELETE FROM `tbl_seats` WHERE `cinema_id`=:cinemaId;";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cinemaId", cinemaId);
		return jdbcTemplate.update(sql, paramMap);
	}

}

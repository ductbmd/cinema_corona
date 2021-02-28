/**
 * 
 */
package com.teko.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teko.cinema.contract.ResponseContract;
import com.teko.cinema.model.Cinema;
import com.teko.cinema.model.Seat;
import com.teko.cinema.service.ICinemaService;

/**
 * @author To Duc
 *
 *         Feb 28, 2021--1:50:50 PM
 */
@RestController
@RequestMapping("/cinema")

public class CinemaController {
	@Autowired
	ICinemaService cinemaService;
	/**
	 * @author To Duc
	 *	API Cập nhật lại thông tin rạp chiếu phim
	 *  Input: Thông tin rạp (số hàng, cột, khoảng cách tối thiểu)
	 *  Output: thông báo cập nhật rạp thành công, reset tất cả các ghế trong rạp về trạng thái 0 (được phép ngồi)
	 */
	@PutMapping("/update")
	public ResponseContract<?> create(@RequestBody Cinema cinema) {
		return cinemaService.update(cinema);
	}
	
	
	/**
	 * @author To Duc
	 *	Lấy thông tin các ghế trong rạp chiếu phim
	 *  Input: Id rạp 
	 *  Output: Thông tin của rạp (số hàng, cột, độ giãn cách), Thông tin các ghế của rạp( Trạng thái của ghế, vị trí ghế)
	 */
	@GetMapping("/check")
	public ResponseContract<?> check() {
		return cinemaService.getSeatCinema(1);
	}

	
	/**
	 * @author To Duc
	 *	Đặt chỗ cho các ghế ngồi của rạp
	 *  Input: Danh sách các ghế muốn đặt (theo tọa độ)
	 *  Output: Thông tin đặt chỗ (thành công nếu các ghế thỏa mãn điều kiện, thất bại nếu các ghế không thỏa mãn điều kiện)
	 */
	@PostMapping("/book-seat")
	public ResponseContract<?> bookSeat(@RequestBody List<Seat> seats) {
		return cinemaService.bookSeat(seats);
	}

	
	/**
	 * @author To Duc
	 *	Hàm lấy số ghế trống cần thiết khả dụng
	 *  Input: Số ghế cần book
	 *  Output: List tọa độ các ghế khả dụng
	 */
	@GetMapping("/seat-available")
	public ResponseContract<?> getSeatAvailable(@RequestParam int numberOfSeat) {
		return cinemaService.getSeatPurchase(numberOfSeat, 1);
	}
}

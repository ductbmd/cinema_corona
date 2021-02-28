/**
 * 
 */
package com.teko.cinema.service;

import java.util.List;

import com.teko.cinema.contract.ResponseContract;
import com.teko.cinema.model.Cinema;
import com.teko.cinema.model.Seat;

/**
 * @author To Duc
 *
 * Feb 28, 2021--1:34:01 PM
 */
public interface ICinemaService {
	public ResponseContract<?> update(Cinema cinema);
	public ResponseContract<?> getSeatCinema(int cinemaId);
	public ResponseContract<?> bookSeat(List<Seat> seats);
	public ResponseContract<?> getSeatPurchase(int numberSeat,int cinemaId);
}

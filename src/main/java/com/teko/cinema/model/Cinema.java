/**
 * 
 */
package com.teko.cinema.model;

import java.util.List;

/**
 * @author To Duc
 *
 *         Feb 28, 2021--1:10:50 AM
 */
public class Cinema {
	private int rows;
	private int columns;
	private int minDistance;
	private List<Seat> seats;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}

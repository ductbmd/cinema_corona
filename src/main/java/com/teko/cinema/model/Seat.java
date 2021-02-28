/**
 * 
 */
package com.teko.cinema.model;

/**
 * @author To Duc
 *
 *         Feb 28, 2021--1:13:25 AM
 */
public class Seat {
	private int status;//0 là chưa ai ngồi; 1 đã có người ngồi; 2 không được ngồi vì không tuân thủ mindistance
	private int row;
	private int col;
	private int cinemaId;
	
	public Seat() {
		super();
	}

	public Seat(int status, int row, int col, int cinemaId) {
		super();
		this.status = status;
		this.row = row;
		this.col = col;
		this.cinemaId = cinemaId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

}

/**
 * 
 */
package com.teko.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teko.cinema.contract.Response;
import com.teko.cinema.contract.ResponseContract;
import com.teko.cinema.model.Cinema;
import com.teko.cinema.model.Seat;
import com.teko.cinema.repository.CinemaRepository;
import com.teko.cinema.repository.SeatRepository;

/**
 * @author To Duc
 *
 *         Feb 28, 2021--1:38:27 PM
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CinemaService implements ICinemaService {
	@Autowired
	CinemaRepository cinemaRepository;
	@Autowired
	SeatRepository seatRepository;

	/**
	 * @author To Duc
	 *	Cập nhật lại thông tin rạp chiếu phim
	 *  Input: Thông tin rạp (số hàng, cột, khoảng cách tối thiểu)
	 *  Output: thông báo cập nhật rạp thành công, reset tất cả các ghế trong rạp về trạng thái 0 (được phép ngồi)
	 */
	@Override
	public ResponseContract<?> update(Cinema cinema) {
		try {
			seatRepository.delete(1);//Delete old seat of cinema
			List<Seat> seats=new ArrayList<Seat>();//List new seats of cinema
			//-------------Create list seat cinema with status=0
			for (int row = 0; row < cinema.getRows(); row++) {
				for (int col = 0; col < cinema.getColumns(); col++) {
					Seat seatTemp= new Seat(0, row, col, 1);
					seats.add(seatTemp);
				}
			}
			int result=cinemaRepository.update(cinema);//update rows,cols,mindistance cinema
			seatRepository.create(seats);//create list seat of cinema
			if(result>0) {
				return new ResponseContract<String>(Response.SUCCESS, "Update OK");
			}
			return new ResponseContract<String>(Response.DB_ERROR, "No row update");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(Response.SERVER_ERROR, "ERROR EXCEPTION");
		}
	}
	/**
	 * @author To Duc
	 *	Lấy thông tin các ghế trong rạp chiếu phim
	 *  Input: Id rạp 
	 *  Output: Thông tin của rạp (số hàng, cột, độ giãn cách), Thông tin các ghế của rạp( Trạng thái của ghế, vị trí ghế)
	 */
	@Override
	public ResponseContract<?> getSeatCinema(int cinemaId) {
		try {
			Cinema cinema=cinemaRepository.findById(cinemaId);
			List<Seat> seats=seatRepository.findByCinemaId(cinemaId);
			cinema.setSeats(seats);
			int[][] seatArr=new int[cinema.getRows()][cinema.getColumns()];
			for (Seat seat : seats) {
				seatArr[seat.getRow()][seat.getCol()]=seat.getStatus();
			}
			for (int i = 0; i < cinema.getRows(); i++) {
				for (int j = 0; j < cinema.getColumns(); j++) {
					System.out.print(seatArr[i][j] + "\t");
				}
				System.out.println();
			}
			return new ResponseContract<Cinema>(Response.SUCCESS, cinema);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(Response.SERVER_ERROR, "ERROR EXCEPTION");
		}
		
	}
	
	
	/**
	 * @author To Duc
	 *	Đặt chỗ cho các ghế ngồi của rạp
	 *  Input: Danh sách các ghế muốn đặt (theo tọa độ)
	 *  Output: Thông tin đặt chỗ (thành công nếu các ghế thỏa mãn điều kiện, thất bại nếu các ghế không thỏa mãn điều kiện)
	 */
	@Override
	public ResponseContract<?> bookSeat(List<Seat> seats) {
		try {
			Cinema cinema=cinemaRepository.findById(1);//set tam thoi 1 cinema co id=1
			List<Seat> listSeats=seatRepository.findByCinemaId(1);//cinema id=1
			int[][] seatArr=new int[cinema.getRows()][cinema.getColumns()];
			for (Seat seat : listSeats) {
				seatArr[seat.getRow()][seat.getCol()]=seat.getStatus();
			}//Khoi tao array seat danh dau vi tri
			if(!checkSeatOk(seats, seatArr)) return new ResponseContract<String>(Response.FAIL, "Ghế ngồi đã có người đặt hoặc không thể ngồi do không đạt khoảng cách tối thiểu!");
			
			List<Seat> seatNeedUpdate=new ArrayList<Seat>();//Danh sách các ghế ngồi cần cập nhật trạng thái vào database
			for (Seat seat : seats) {//Vòng for đầu tiên đánh dấu các ghế sẽ có người ngồi
				seatArr[seat.getRow()][seat.getCol()]=1;
				seat.setStatus(1);
				seat.setCinemaId(1);
				seatNeedUpdate.add(seat);
			}
			
			for (Seat seat : seats) {//Vòng for thứ 2 thực hiện việc đánh dấu các ghế không được ngồi tuân thủ theo rule dãn cách
				seatNeedUpdate.addAll(updateSeat(seat.getRow(), seat.getCol(), cinema.getMinDistance(), cinema.getRows(), cinema.getColumns(), seatArr));
			}
			seatRepository.updateStatus(seatNeedUpdate);
			inkq(cinema.getRows(), cinema.getColumns(), seatArr);
			return new ResponseContract<List<Seat>>(Response.SUCCESS, seatNeedUpdate);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(Response.SERVER_ERROR, "ERROR EXCEPTION");
		}
		
	}
	
	
	
	/**
	 * @author To Duc
	 *	Hàm lấy số ghế trống cần thiết khả dụng
	 *  Input: Số ghế cần book
	 *  Output: List tọa độ các ghế khả dụng
	 */
	@Override
	public ResponseContract<?> getSeatPurchase(int numberSeat,int cinemaId) {
		try {
			List<Seat> listSeats=seatRepository.findByCinemaId(cinemaId);//cinema id=1
			List<Seat> resultSeat=new ArrayList<Seat>();
			for (Seat seat : listSeats) {
				if(seat.getStatus()==0) {//nếu ghế trống thì thêm vào kết quả
					resultSeat.add(seat);
				}
				if(resultSeat.size()==numberSeat) break;//đủ số ghế ngưng kiểm tra
			}//Khoi tao array seat danh dau vi tri
			if(resultSeat.size()==numberSeat) {
				return new ResponseContract<List<Seat>>(Response.SUCCESS, resultSeat);
			}else {
				return new ResponseContract<String>(Response.FAIL, "không đủ ghế ngồi trống!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(Response.SERVER_ERROR, "ERROR EXCEPTION");
		}
		
	}
	
	
	
	/**
	 * @author To Duc
	 *	Hàm private kiểm tra các ghế có thỏa mãn điều kiện giãn cách hoặc đã có người ngồi chưa
	 *  Input: Danh sách ghế đặt, array Rạp chiếu
	 *  Output: True: các ghế của team này thỏa mãn, False: Không được đặt các ghế này
	 */
	private boolean checkSeatOk(List<Seat> seats,int[][] seatArr) {
		//Kiểm tra bộ ghế được đặt có được phép ngồi không
		for (Seat seat : seats) {
			if(seatArr[seat.getRow()][seat.getCol()]!=0) { //Nếu ghế book có người ngồi (==1) hoặc không tuân thủ giãn cách với người cũ (==2)
				return false;
			}
		}
		return true;
	}
	/**
	 * @author To Duc
	 *	Hàm cập nhật lại trạng thái các ghế bằng 2 khi có người ngồi vào ghế số 1
	 *  Input: vị trí ghế book (x,y), khoảng cách tối thiểu, Thông tin rạp chiếu
	 *  Output: Danh sách các ghế update trạng thái không được ngồi x
	 */
	private List<Seat> updateSeat(int x,int y, int minDis,int soDong,int soCot, int[][]cinema) {
		//Độ phức tạp O(2MinDis^2)
//		cinema[x][y]=1;
		List<Seat> result=new ArrayList<Seat>();
		int gioiHanDuoi =x+minDis-1;
		int gioiHanPhai=y+minDis-1;
		int gioiHanTren=x-minDis+1;
		int gioiHanTrai=y-minDis+1;
		//Khóa các ghế bên góc dưới phải
		for (int i = x; i <=gioiHanDuoi ; i++) { 
			for (int j = y; j <= gioiHanPhai; j++) {
				if (i<soDong&&j<soCot&&cinema[i][j]==0) {
					cinema[i][j]=2;//Đánh dấu ghế này không thể ngồi nữa
					Seat seat=new Seat(2, i, j, 1);
					result.add(seat);//Thêm vào danh sách các ghế cần update trạng thái
				}
			}
			gioiHanPhai--;
		}
		//Khóa các ghế góc dưới trái
		for (int i = x; i <=gioiHanDuoi ; i++) { 
			for (int j = y; j >= gioiHanTrai; j--) {
				if (i<soDong && j>=0&&cinema[i][j]==0) {
					cinema[i][j]=2;//Đánh dấu ghế này không thể ngồi nữa
					Seat seat=new Seat(2, i, j, 1);
					result.add(seat);//Thêm vào danh sách các ghế cần update trạng thái
				}
			}
			gioiHanTrai++;
		}
		gioiHanPhai=y+minDis-1;
		gioiHanTrai=y-minDis+1;
		//Khóa ghế góc trên phải
		for (int i = x; i >=gioiHanTren ; i--) { 
			for (int j = y; j <= gioiHanPhai; j++) {
				if (i>=0 && j<soCot && cinema[i][j]==0) {
					cinema[i][j]=2;//Đánh dấu ghế này không thể ngồi nữa
					Seat seat=new Seat(2, i, j, 1);
					result.add(seat);//Thêm vào danh sách các ghế cần update trạng thái
				}
			}
			gioiHanPhai--;
		}
		//Khóa ghế góc trên trái
		for (int i = x; i >=gioiHanTren ; i--) { 
			for (int j = y; j >= gioiHanTrai; j--) {
				if (i>=0 && j>=0 && cinema[i][j]==0) {
					cinema[i][j]=2;
					Seat seat=new Seat(2, i, j, 1);
					result.add(seat);//Thêm vào danh sách các ghế cần update trạng thái
				}
			}
			gioiHanTrai++;
		}
		return result;
	}
	
	/**
	 * @author To Duc
	 *	Hàm In kết quả ra console thể hiện trạng thái rạp chiếu phim
	 *  Input: thông tin rạp
	 *  Output: In ra màn hình trạng thái rạp chiếu
	 */
	private void inkq(int rows,int cols,int[][] arr) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(arr[i][j]==2) {
					System.out.print("x" + "\t");
				}else {
					System.out.print(arr[i][j] + "\t");
				}
				
			}
			System.out.println();
		}
	}
	
	

}

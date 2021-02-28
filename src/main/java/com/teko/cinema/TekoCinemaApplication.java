package com.teko.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TekoCinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TekoCinemaApplication.class, args);
//		int soDong=10;
//		int soCot=15;
//		int [][] cinema =new int[soDong][soCot];
//		int  minDis=4;
//		
//		System.out.println("hello");
////		updateSeat(0,0,minDis,soDong,soCot,cinema);
////		updateSeat(0,1,minDis,soDong,soCot,cinema);
//		updateSeat(5,7,minDis,soDong,soCot,cinema);
////		update(0,1,minDis,soDong,soCot,cinema);
//		for (int i = 0; i < soDong; i++) {
//			for (int j = 0; j < soCot; j++) {
//				System.out.print(cinema[i][j] + "\t");
//			}
//			System.out.println();
//		}
	}
	/**
	private static void updateSeat(int x,int y, int minDis,int soDong,int soCot, int[][]cinema) {
		//Độ phức tạp O(2MinDis^2)
		cinema[x][y]=1;
		int gioiHanDuoi =x+minDis-1;
		int gioiHanPhai=y+minDis-1;
		int gioiHanTren=x-minDis+1;
		int gioiHanTrai=y-minDis+1;
		//Khóa các ghế bên góc dưới phải
		for (int i = x; i <=gioiHanDuoi ; i++) { 
			for (int j = y; j <= gioiHanPhai; j++) {
				if (i<soDong&&j<soCot&&cinema[i][j]==0) {
					cinema[i][j]=2;
				}
			}
			gioiHanPhai--;
		}
		//Khóa các ghế góc dưới trái
		for (int i = x; i <=gioiHanDuoi ; i++) { 
			for (int j = y; j >= gioiHanTrai; j--) {
				if (i<soDong && j>=0&&cinema[i][j]==0) {
					cinema[i][j]=2;
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
					cinema[i][j]=2;
				}
			}
			gioiHanPhai--;
		}
		//Khóa ghế góc trên trái
		for (int i = x; i >=gioiHanTren ; i--) { 
			for (int j = y; j >= gioiHanTrai; j--) {
				if (i>=0 && j>=0 && cinema[i][j]==0) {
					cinema[i][j]=2;
				}
			}
			gioiHanTrai++;
		}
	}
	*/

}

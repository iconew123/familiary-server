package baby.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;


import util.DBManager;



public class BabyDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// UserDao 객체를 단일 인스턴스로 만들기 위해 
	// Singleton Pattern 적용 
	
	// 1. 생성자를 private으로 
	public BabyDao() {
		
	}
	
	// 2. 단일 인스턴스를 생성 (클래스 내부에서) 
	private static BabyDao instance = new BabyDao();
	
	// 3. 단일 인스턴스에 대한 getter 
	public static BabyDao getInstance() {
		return instance;
	}
	
	public BabyResponseDto createBaby(BabyRequestDto babyDto) {

		try {
			conn = DBManager.getConnection();
			
			String sql = "INSERT INTO baby(nickname, name, gender, expected_date, photo, blood_type) VALUES(?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			 
			pstmt.setString(1, babyDto.getNickname());
			
			String name = babyDto.getName().equals("") ? null : babyDto.getName();
			pstmt.setString(2, name);
			
			String gender = babyDto.getGender().equals("") ? null : babyDto.getGender();
			pstmt.setString(3, gender);
			
			pstmt.setString(4, babyDto.getExpected_date());
			
			Blob photo = null;
			if (babyDto.getPhoto() != null && babyDto.getPhoto().length() > 0) {
			    photo = babyDto.getPhoto();
			}
			pstmt.setBlob(5, photo);
			
			String blood_type = babyDto.getBlood_type().equals("") ? null : babyDto.getBlood_type();
			pstmt.setString(6, blood_type);

			pstmt.execute();
			
			return new BabyResponseDto(babyDto.getNickname(), name, gender, babyDto.getExpected_date(), photo, blood_type);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}
	
}

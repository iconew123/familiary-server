package baby.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import com.mysql.cj.jdbc.Blob;

import util.DBManager;



public class BabyDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// UserDao 객체를 단일 인스턴스로 만들기 위해 
	// Singleton Pattern 적용 
	
	// 1. 생성자를 private으로 
	private BabyDao() {}
	
	// 2. 단일 인스턴스를 생성 (클래스 내부에서) 
	private static BabyDao instance = new BabyDao();
	
	// 3. 단일 인스턴스에 대한 getter 
	public static BabyDao getInstance() {
		return instance;
	}
	
	public BabyResponseDto createBaby(BabyRequestDto babyDto) {
		try {
			conn = DBManager.getConnection();
			
			String sql = "INSERT INTO baby(code, nickname, name, gender, expected_date, photo, blood_type) VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			 
			pstmt.setString(1, babyDto.getCode());
			pstmt.setString(2, babyDto.getNickname());
			
			String name = babyDto.getName().equals("") ? null : babyDto.getName();
			pstmt.setString(3, name);
			
			String gender = babyDto.getGender().equals("") ? null : babyDto.getGender();
			pstmt.setString(4, gender);
			
			pstmt.setString(5, babyDto.getExpected_date());
			
			SerialBlob photo = babyDto.getPhoto().equals("") ? null : babyDto.getPhoto();
			pstmt.setBlob(6, photo);
			
			String blood_type = babyDto.getBlood_type().equals("") ? null : babyDto.getBlood_type();
			pstmt.setString(7, blood_type);

			
			pstmt.execute();
			
			return new BabyResponseDto(babyDto.getCode(), babyDto.getNickname(), name, gender, babyDto.getExpected_date(), photo, blood_type);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}
}

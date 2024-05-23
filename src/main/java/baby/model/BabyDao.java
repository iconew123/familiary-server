package baby.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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

			String sql = "INSERT INTO baby(nickname, name, gender, expected_date, blood_type) VALUES(?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, babyDto.getNickname());

			String name = babyDto.getName().equals("") ? null : babyDto.getName();
			pstmt.setString(2, name);

			String gender = babyDto.getGender().equals("") ? null : babyDto.getGender();
			pstmt.setString(3, gender);

			pstmt.setString(4, babyDto.getExpected_date());

			String blood_type = babyDto.getBlood_type().equals("") ? null : babyDto.getBlood_type();
			pstmt.setString(5, blood_type);

			pstmt.execute();

			return new BabyResponseDto(babyDto.getNickname(), name, gender, babyDto.getExpected_date(), blood_type);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}

	public Baby readLatestBaby() {

		Baby baby = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM baby ORDER BY reg_date DESC LIMIT 1";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String code = rs.getString("code");
				String nickname = rs.getString("nickname");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String expected_date = rs.getString("expected_date");
				String bloodType = rs.getString("blood_type");
				Timestamp regDate = rs.getTimestamp("reg_date");
				Timestamp modDate = rs.getTimestamp("mod_date");

				baby = new Baby(code, nickname, name, gender, expected_date, bloodType, regDate, modDate);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return baby;

	}

	public Baby findBabyByCode(String babyCode) {

		Baby baby = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM baby WHERE code = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, babyCode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String code = rs.getString("code");
				String nickname = rs.getString("nickname");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String expected_date = rs.getString("expected_date");
				String bloodType = rs.getString("blood_type");
				Timestamp regDate = rs.getTimestamp("reg_date");
				Timestamp modDate = rs.getTimestamp("mod_date");

				baby = new Baby(code, nickname, name, gender, expected_date, bloodType, regDate, modDate);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return baby;
	}
	
	public boolean updateBaby(BabyRequestDto babyDto) {
		boolean success = false;
	    
		try {
	        conn = DBManager.getConnection(); // 데이터베이스 연결 가져오기

	        String sql = "UPDATE baby SET nickname=?, name=?, gender=?, expected_date=?, blood_type=? WHERE code=?";

	        pstmt = conn.prepareStatement(sql); 
	        pstmt.setString(1, babyDto.getNickname());
	        pstmt.setString(2, babyDto.getName());
	        pstmt.setString(3, babyDto.getGender());
	        pstmt.setString(4, babyDto.getExpected_date());
	        pstmt.setString(5, babyDto.getBlood_type());
	        pstmt.setString(6, babyDto.getCode());
	        
	        int rowsAffected = pstmt.executeUpdate();
	        
	        success = (rowsAffected > 0);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 리소스 해제
	        DBManager.close(conn, pstmt);
	    }

	    return success;
	}
	
	public boolean deleteBaby(Baby baby) {
		try {
			String sql = "DELETE FROM baby WHERE code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, baby.getCode());
			
			pstmt.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
		return false;
	}

}

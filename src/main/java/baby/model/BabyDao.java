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


	public BabyDao() {

	}

	private static BabyDao instance = new BabyDao();

	public static BabyDao getInstance() {
		return instance;
	}

	public BabyResponseDto createBaby(BabyRequestDto babyDto) {

		try {
			conn = DBManager.getConnection();

			String sql = "INSERT INTO baby(nickname, name, gender, expected_date, blood_type) VALUES(?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, babyDto.getNickname());
			pstmt.setString(2, babyDto.getName());
			pstmt.setString(3, babyDto.getGender());
			pstmt.setString(4, babyDto.getExpected_date());
			pstmt.setString(5, babyDto.getBlood_type());

			pstmt.execute();

			return new BabyResponseDto(babyDto.getNickname(), babyDto.getName(), babyDto.getGender(), babyDto.getExpected_date(), babyDto.getBlood_type());
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
		}finally {
			DBManager.close(conn, pstmt);
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
		}finally {
			DBManager.close(conn, pstmt);
		}

		return baby;
	}
	
	public boolean updateBaby(BabyRequestDto babyDto) {
		boolean success = false;
	    
		try {
			conn = DBManager.getConnection();

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
	        DBManager.close(conn, pstmt);
	    }

	    return success;
	}
	
	public boolean deleteBaby(Baby baby) {
		try {
			conn = DBManager.getConnection();

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

	public void DeleteImageStatus(BabyRequestDto baby){

		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE backup AS b JOIN image AS i ON b.num = i.num SET b.status = 0 WHERE i.code = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, baby.getCode());

			pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

}

package enroll.model;

import java.sql.*;

import baby.model.Baby;
import util.DBManager;

public class EnrollDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// UserDao 객체를 단일 인스턴스로 만들기 위해
	// Singleton Pattern 적용

	// 1. 생성자를 private으로
	public EnrollDao() {

	}

	// 2. 단일 인스턴스를 생성 (클래스 내부에서)
	private static EnrollDao instance = new EnrollDao();

	// 3. 단일 인스턴스에 대한 getter
	public static EnrollDao getInstance() {
		return instance;
	}
	
	public EnrollResponseDto createEnroll(EnrollRequestDto enrollDto) {

		try {
			conn = DBManager.getConnection();

			String sql = "INSERT INTO enroll(user_id, baby_code, position) VALUES(?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, enrollDto.getUser_id());
			pstmt.setString(2, enrollDto.getBaby_code());
			pstmt.setString(3, enrollDto.getPosition());

			pstmt.execute();

			return new EnrollResponseDto(enrollDto.getUser_id(), enrollDto.getBaby_code(), enrollDto.getPosition());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}
	
	public boolean checkMother(String baby_code){
		boolean isExist = false;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT count(*) FROM enroll WHERE baby_code = ? AND position = 'mother'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, baby_code);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					isExist = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		return isExist;
	}


public boolean checkFather(String baby_code){
	boolean isExist = false;

	try {
		conn = DBManager.getConnection();

		String sql = "SELECT count(*) FROM enroll WHERE baby_code = ? AND position = 'father'";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, baby_code);


		rs = pstmt.executeQuery();

		if (rs.next()) {
			int count = rs.getInt(1);
			if (count > 0) {
				isExist = true;
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		DBManager.close(conn, pstmt);
	}

	return isExist;
}

	public boolean checkDupl(String baby_code, String user_id){
		boolean isDupl = false;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT count(*) FROM enroll WHERE baby_code = ? AND user_id= ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, baby_code);
			pstmt.setString(2, user_id);


			rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					isDupl = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		return isDupl;
	}

}


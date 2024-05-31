package enroll.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import baby.model.Baby;
import babyInfo.model.BabyInfoResponseDto;
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

	public List<String> findBabyCodeByNickName(String user_id) {
		List<String> babyNicknames = new ArrayList<>();

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT b.nickname FROM enroll e JOIN baby b ON e.baby_code = b.code WHERE e.user_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			while(rs.next()){
				String nickname = rs.getString("nickname");
				babyNicknames.add(nickname);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return babyNicknames;
	}

	public List<String> findBabyCodeByCode(String user_id) {
		List<String> babyCodes = new ArrayList<>();

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT b.code FROM enroll e JOIN baby b ON e.baby_code = b.code WHERE e.user_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			while(rs.next()){
				String code = rs.getString("code");
				babyCodes.add(code);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return babyCodes;
	}

	public String checkPosition(String baby_code, String user_id){
		String position = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT position FROM enroll WHERE baby_code = ? AND user_id = ?";

			pstmt = conn.prepareStatement(sql);
			System.out.println("baby_code: " + baby_code);
			System.out.println("user_id: " + user_id);
			pstmt.setString(1, baby_code);
			pstmt.setString(2, user_id);

			rs = pstmt.executeQuery();

			if (rs.next()) { // 결과 집합에 데이터가 있는지 확인
				position = rs.getString("position");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		return position;
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


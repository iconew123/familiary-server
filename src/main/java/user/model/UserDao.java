package user.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import util.DBManager;

public class UserDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private UserDao() {

	}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	public UserResponseDto createUser(UserRequestDto userDto) {
		
		try {
			conn = DBManager.getConnection();


			String sql = "INSERT INTO users(id, password,nickname, name, security_number , telecom, phone,  email, adress ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, userDto.getPassword());
			pstmt.setString(3, userDto.getNickname());
			pstmt.setString(4, userDto.getName());
			pstmt.setString(5, userDto.getSecurity_number());
			pstmt.setString(6, userDto.getTelecom());
			pstmt.setString(7, userDto.getPhone());

			String email = userDto.getEmail().equals("") ? null : userDto.getEmail();
			pstmt.setString(8, email);

			String adress = userDto.getAdress().equals("") ? null : userDto.getAdress();
			pstmt.setString(9, adress);

			pstmt.execute();

			return new UserResponseDto(userDto.getId(), userDto.getNickname(), userDto.getName(), userDto.getSecurity_number(), userDto.getTelecom(),  userDto.getTelecom(), userDto.getPhone(), email,
					adress);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}

//	public UserResponseDto findUserByIdAndPassword(String id) {
//		UserResponseDto user = null;
//
//		try {
//			String sql = "SELECT id, nickname, name, security_number, telecom, phone, email, adress, position  FROM users WHERE id=?";
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				String nickname = rs.getString(2);
//
//				String name = rs.getString(3);
//				String security_number = rs.getString(4);
//				String telecom = rs.getString(5);
//				String phone = rs.getString(6);
//				String email = rs.getString(7);
//				String adress = rs.getString(8);
//				String position = rs.getString(9);
//
//				user = new UserResponseDto(id, name, nickname, email, adress, security_number, telecom, phone,
//						position);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//	public UserResponseDto updateUserEmail(UserRequestDto userDto) {
//		UserResponseDto user = null;
//		try {
//			String sql = "UPDATE users SET email=? WHERE id=? AND password=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, userDto.getEmail());
//			pstmt.setString(2, userDto.getId());
//			pstmt.setString(3, userDto.getPassword());
//
//			pstmt.execute();
//
//			user = findUserByIdAndPassword(userDto.getId());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//	public boolean deleteUser(UserRequestDto userDto) {
//		if (findUserByIdAndPassword(userDto.getId()) == null)
//			return false;
//
//		try {
//			String sql = "DELETE FROM users WHERE id=? AND password=?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, userDto.getId());
//			pstmt.setString(2, userDto.getPassword());
//
//			pstmt.execute();
//
//			return true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

}

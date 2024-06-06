package user.model;

import util.DBManager;
import util.PasswordCrypto;

import java.sql.*;

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

			String sql = "INSERT INTO users(id, password,nickname, name, security_number , telecom, phone, address, email ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, PasswordCrypto.encrypt(userDto.getPassword()));
			pstmt.setString(3, userDto.getNickname());
			pstmt.setString(4, userDto.getName());
			pstmt.setString(5, userDto.getSecurityNumber());
			pstmt.setString(6, userDto.getTelecom());
			pstmt.setString(7, userDto.getPhone());
			String address = userDto.getAddress().equals("") ? null : userDto.getAddress();
			pstmt.setString(8, address);
			String email = userDto.getEmail().equals("") ? null : userDto.getEmail();
			pstmt.setString(9, email);

			pstmt.execute();

			return findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}

	public boolean isDuplicate(String field, String value) {

		try  {
			conn = DBManager.getConnection();

			String sql = "SELECT COUNT(*) FROM users WHERE " + field + " = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public UserResponseDto findUserByIdAndPassword(String id, String password) {
		UserResponseDto user = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,password, nickname, name, security_number, telecom, phone, address ,email FROM users WHERE id=? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String encryptedPassword = rs.getString(2);
				String nickname = rs.getString(3);
				String name = rs.getString(4);
				String securityNumber = rs.getString(5);
				String telecom = rs.getString(6);
				String phone = rs.getString(7);
				String address = rs.getString(8);
				String email = rs.getString(9);

				if (PasswordCrypto.decrypt(password, encryptedPassword))
					user = new UserResponseDto(id, nickname, name, securityNumber, telecom, phone, address,  email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	private User findUserById(String id) {
		User user = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM users WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String password = rs.getString(2);
				String nickname = rs.getString(3);
				String name = rs.getString(4);
				String securityNumber = rs.getString(5);
				String telecom = rs.getString(6);
				String phone = rs.getString(7);
				String address = rs.getString(8);
				String email = rs.getString(9);

				user = new User(id, password, nickname, name, securityNumber, telecom, phone, address, email);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public boolean deleteUser(UserRequestDto userDto) {
		if (findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) == null)
			return false;

		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM users WHERE id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getId());

			pstmt.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public UserResponseDto updateUser(UserRequestDto userDto, String newPassword) {
		UserResponseDto user = null;
		user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());

		if (user == null) {
			return null;
		}
		conn = DBManager.getConnection();

		String sql = "UPDATE users SET password = ?, nickname = ? ,telecom = ? , phone = ?, address = ? , email=? WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, PasswordCrypto.encrypt(newPassword));
			pstmt.setString(2, userDto.getNickname());
			pstmt.setString(3, userDto.getTelecom());
			pstmt.setString(4, userDto.getPhone());
			String address = userDto.getAddress().equals("") ? null : userDto.getAddress();
			pstmt.setString(5, address);
			String email = userDto.getEmail().equals("") ? null : userDto.getEmail();
			pstmt.setString(6, email);
			pstmt.setString(7, userDto.getId());

			pstmt.execute();
			User userVo = findUserById(userDto.getId());
			user = new UserResponseDto(userVo);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return user;
	}

}
package user.model;

import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			pstmt.setString(2, userDto.getPassword());
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

	public User findUserById(String id) {
		User user = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id, nickname, name, security_number, telecom, phone,  address , emailFROM users WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String nickname = rs.getString(2);
				String name = rs.getString(3);
				String securityNumber = rs.getString(4);
				String telecom = rs.getString(5);
				String phone = rs.getString(6);
				String address = rs.getString(7);
				String email = rs.getString(8);


				user = new User(id, nickname, name,  securityNumber, telecom, phone, address,  email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findUserByNickname(String nickname) {
		User user = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id, name, security_number, telecom, phone, address, email FROM users WHERE nickname=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString(2);
				String name = rs.getString(3);
				String securityNumber = rs.getString(4);
				String telecom = rs.getString(5);
				String phone = rs.getString(6);
				String address = rs.getString(7);
				String email = rs.getString(8);


				user = new User(id, nickname, name,  securityNumber, telecom, phone, address, email );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findUserByphone(String phone) {
		User user = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,nickname, name, security_number, telecom, address, email FROM users WHERE phone=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString(2);
				String nickname = rs.getString(3);
				String name = rs.getString(4);
				String securityNumber = rs.getString(5);
				String telecom = rs.getString(6);
				String address = rs.getString(7);
				String email = rs.getString(8);


				user = new User(id, nickname, name,  securityNumber, telecom, phone, address,  email );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findUserByEamil(String email) {
		User user = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id, nickname, name, security_number, telecom, phone, address FROM users WHERE email=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString(2);
				String nickname = rs.getString(3);
				String name = rs.getString(4);
				String securityNumber = rs.getString(5);
				String telecom = rs.getString(6);
				String phone = rs.getString(7);
				String address = rs.getString(8);

				user = new User(id, nickname, name,  securityNumber, telecom, phone, address,  email );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public UserResponseDto findUserByIdAndPassword(String id, String password) {
		UserResponseDto user = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id, nickname, name, security_number, telecom, phone, address ,email FROM users WHERE id=? AND password=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String nickname = rs.getString(2);
				String name = rs.getString(3);
				String securityNumber = rs.getString(4);
				String telecom = rs.getString(5);
				String phone = rs.getString(6);
				String address = rs.getString(7);
				String email = rs.getString(8);


				user = new UserResponseDto(id, nickname, name, securityNumber, telecom, phone, address,  email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public UserResponseDto findUserByPassword(String id, String password) {
		UserResponseDto user = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id, nickname, name, security_number, telecom, phone, address , email FROM users WHERE id=? AND password=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String nickname = rs.getString(2);
				String name = rs.getString(3);
				String securityNumber = rs.getString(4);
				String telecom = rs.getString(5);
				String phone = rs.getString(6);
				String address = rs.getString(7);
				String email = rs.getString(8);

				user = new UserResponseDto(id, nickname, name, securityNumber, telecom, phone, address, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}



	public boolean deleteUser(UserRequestDto userDto) {
		if (findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) == null)
			return false;

		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM users WHERE id=? AND password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, userDto.getPassword());

			pstmt.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public UserResponseDto updateUserPassword(UserRequestDto userDto, String newPassword) {
		UserResponseDto user = null;

		if(newPassword == null || newPassword.equals("")) {
			return user;
		}

		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE users SET password=? WHERE id=? AND password=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, userDto.getId());
			pstmt.setString(3, userDto.getPassword());

			pstmt.execute();

			User userVo = findUserById(userDto.getId());
			user = new UserResponseDto(userVo);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public UserResponseDto updateUserNickname(UserRequestDto userDto) {
		UserResponseDto user = null;
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE users SET nickname=? WHERE id=? AND password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getNickname());
			pstmt.setString(2, userDto.getId());
			pstmt.setString(3, userDto.getPassword());

			pstmt.execute();
			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
			System.out.println(user.getNickname());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	public UserResponseDto updateUserPhone(UserRequestDto userDto) {
		UserResponseDto user = null;
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE users SET telecom=?, phone=? WHERE id=? AND password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getTelecom());
			pstmt.setString(2, userDto.getPhone());
			pstmt.setString(3, userDto.getId());
			pstmt.setString(4, userDto.getPassword());

			pstmt.execute();

			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public UserResponseDto updateUserAddress(UserRequestDto userDto) {
		UserResponseDto user = null;
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE users SET address=? WHERE id=? AND password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getAddress());
			pstmt.setString(2, userDto.getId());
			pstmt.setString(3, userDto.getPassword());

			pstmt.execute();

			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	public UserResponseDto updateUserEmail(UserRequestDto userDto) {
		UserResponseDto user = null;
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE users SET email=? WHERE id=? AND password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getEmail());
			pstmt.setString(2, userDto.getId());
			pstmt.setString(3, userDto.getPassword());

			pstmt.execute();

			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
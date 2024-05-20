package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private UserDao() {
		setConnection();
	}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	private void setConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://database-familiary.cn02w04k0uua.ap-northeast-2.rds.amazonaws.com:3306/familiary-db";
			String user = "lr2l";
			String password = "nb93Vs3MSjxqUCx";

			this.conn = DriverManager.getConnection(url, user, password);

			System.out.println("[DB 연동 성공]");
		} catch (Exception e) {
			System.err.println("[DB 연동 실패]");
			e.printStackTrace();
		}
	}

	public UserResponseDto findUserByIdAndPassword(String id) {
		UserResponseDto user = null;

		try {
			String sql = "SELECT id, nickname, name, security_number, telecom, phone, email, adress, position  FROM users WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String nickname = rs.getString(2);

				String name = rs.getString(3);
				String security_number = rs.getString(4);
				String telecom = rs.getString(5);
				String phone = rs.getString(6);
				String email = rs.getString(7);
				String adress = rs.getString(8);
				String position = rs.getString(9);

				user = new UserResponseDto(id, name, nickname, email, adress, security_number, telecom, phone,
						position);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public UserResponseDto createUser(UserRequestDto userDto) {

		try {
			String sql = "INSERT INTO users(id, password,nickname, name, security_number , telecom, phone,  email, adress ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

			pstmt.setString(11, userDto.getPosition());

			pstmt.execute();

			return findUserByIdAndPassword(userDto.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

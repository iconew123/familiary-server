package community.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class CommunityDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CommunityDao() {

	}

	private static CommunityDao instance = new CommunityDao();

	public static CommunityDao getInstance() {
		return instance;
	}

	// 잡담 게시판용 게시글 전체 보기
	public List<CommunityResponseDto> findCommunityAll() {
		List<CommunityResponseDto> list = new ArrayList<CommunityResponseDto>();
		conn = DBManager.getConnection();
		String sql = "SELECT code, user_id, user_nickname, title, content, category, reg_date FROM community WHERE category='잡담' order by reg_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int code = rs.getInt(1);
				String userId = rs.getString(2);
				String userNickName = rs.getString(3);
				String title = rs.getString(4);
				String content = rs.getString(5);
				String category = rs.getString(6);
				Timestamp regDate = rs.getTimestamp(7);
				CommunityResponseDto community = new CommunityResponseDto(code, userId, userNickName, title, content,
						category, regDate);
				list.add(community);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public CommunityResponseDto createBoard(CommunityRequestDto CommunityRequestDto) {
		conn = DBManager.getConnection();

		CommunityResponseDto community = null;
		String userId = CommunityRequestDto.getUserId();
		String userNickname = CommunityRequestDto.getUserNickname();
		String title = CommunityRequestDto.getTitle();
		String content = CommunityRequestDto.getContent();
		String category = CommunityRequestDto.getCategory();

		String sql = "INSERT INTO community (user_id, user_nickname, title, content, category) VALUES (?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, userId);
			pstmt.setString(2, userNickname);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, category);

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int code = rs.getInt(1);
				community = findCommunityByCode(code);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return community;
	}

	public CommunityResponseDto findCommunityByCode(int code) {
		conn = DBManager.getConnection();
		CommunityResponseDto community = null;
		String sql = "SELECT code, user_nickname, title, content FROM community WHERE code = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String userNickname = rs.getString(2);
				String title = rs.getString(3);
				String content = rs.getString(4);

				community = new CommunityResponseDto(code, userNickname, title, content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return community;
	}

}

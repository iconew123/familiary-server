package communityComment.model;

import community.model.CommunityRequestDto;
import community.model.CommunityResponseDto;
import util.DBManager;

import java.sql.*;

public class CommunityCommentDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CommunityCommentDao() {

	}

	private static CommunityCommentDao instance = new CommunityCommentDao();

	public static CommunityCommentDao getInstance() {
		return instance;
	}

	public CommunityCommentResponseDto createCommunityComment (CommunityCommentRequestDto communityCommentRequestDto) {
		conn = DBManager.getConnection();

		CommunityCommentResponseDto communityComment = null;
		int communityCode = communityCommentRequestDto.getCommunityCode();
		String userId = communityCommentRequestDto.getUserId();
		String userNickname = communityCommentRequestDto.getUserNickname();
		String content = communityCommentRequestDto.getContent();

		String sql = "INSERT INTO community_comment (community_code, user_id, user_nickname, content) VALUES (?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, communityCode);
			pstmt.setString(2, userId);
			pstmt.setString(3, userNickname);
			pstmt.setString(4, content);

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				String code = rs.getString(1);
				communityComment = findCommunityCommentByCode(code);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return communityComment;
	}

	public CommunityCommentResponseDto findCommunityCommentByCode(String code) {
		conn = DBManager.getConnection();
		CommunityCommentResponseDto communityComment = null;
		String sql = "SELECT code, user_nickname, content FROM community_comment WHERE code = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String userNickname = rs.getString(2);
				String content = rs.getString(3);

				communityComment = new CommunityCommentResponseDto(code, userNickname, content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return communityComment;
	}

	public boolean deleteCommunityComment(CommunityCommentRequestDto communityCommentRequestDto) {
		if (findCommunityCommentByCode(communityCommentRequestDto.getCode()) == null) {
			return false;
		}

		try {
			conn = DBManager.getConnection();

			String sql = "DELETE FROM community_comment WHERE code=? AND user_id=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, communityCommentRequestDto.getCode());
			pstmt.setString(2, communityCommentRequestDto.getUserId());

			pstmt.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return false;
	}
}

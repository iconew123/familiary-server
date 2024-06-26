package communityComment.model;

import community.model.CommunityRequestDto;
import community.model.CommunityResponseDto;
import util.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

	public List<CommunityCommentResponseDto> readAllCommunityComment(int communityCode) {
		List<CommunityCommentResponseDto> list = new ArrayList<CommunityCommentResponseDto>();
		conn = DBManager.getConnection();
		String sql = "SELECT code, community_code, user_id, user_nickname, content, reg_date FROM community_comment WHERE community_code=? order by reg_date asc";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, communityCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String code = rs.getString(1);
				int communityCodeFromDB = rs.getInt(2);
				String userId = rs.getString(3);
				String userNickName = rs.getString(4);
				String content = rs.getString(5);
				Timestamp regDate = rs.getTimestamp(6);
				CommunityCommentResponseDto communityComment = new CommunityCommentResponseDto(code, communityCodeFromDB, userId, userNickName, content, regDate);
				list.add(communityComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
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

			String sql = "DELETE FROM community_comment WHERE code=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, communityCommentRequestDto.getCode());

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

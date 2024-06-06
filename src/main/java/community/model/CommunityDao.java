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

	// 메인 게시판 커뮤니티 (글 최신순)
	public List<CommunityResponseDto> findCommunityAll() {
		List<CommunityResponseDto> list = new ArrayList<CommunityResponseDto>();
		conn = DBManager.getConnection();
		String sql = "SELECT code, user_id, user_nickname, title, content, category, reg_date FROM community order by reg_date desc";

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
		}
		return list;
	}

	// 잡담 게시판용 게시글 전체 보기
	public List<CommunityResponseDto> findCommunityAllByTalk() {
		List<CommunityResponseDto> list = new ArrayList<CommunityResponseDto>();
		conn = DBManager.getConnection();
		String sql = "SELECT code, user_id, user_nickname, title, content, category, reg_date FROM community WHERE category='chat' order by reg_date desc";

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
		}
		return list;
	}

	// 공지 게시판용 게시글 전체 보기
	public List<CommunityResponseDto> findCommunityAllByNotice() {
		List<CommunityResponseDto> list = new ArrayList<CommunityResponseDto>();
		conn = DBManager.getConnection();
		String sql = "SELECT code, user_id, user_nickname, title, content, category, reg_date FROM community WHERE category='notice' order by reg_date desc";

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
		}
		return list;
	}

	// 추천 게시판용 게시글 전체 보기
	public List<CommunityResponseDto> findCommunityAllByRecommend() {
		List<CommunityResponseDto> list = new ArrayList<CommunityResponseDto>();
		conn = DBManager.getConnection();
		String sql = "SELECT code, user_id, user_nickname, title, content, category, reg_date FROM community WHERE category='recommend' order by reg_date desc";

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
		}
		return list;
	}

	public CommunityResponseDto createCommunity(CommunityRequestDto CommunityRequestDto) {
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

	public CommunityResponseDto updateCommunity(CommunityRequestDto communityDto) {
		CommunityResponseDto community = null;

		if (findCommunityByCode(communityDto.getCode()) == null) {
			return community;
		}

		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE community SET title=?, content=?, category=? WHERE code=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, communityDto.getTitle());
			pstmt.setString(2, communityDto.getContent());
			pstmt.setString(3, communityDto.getCategory());
			pstmt.setInt(4, communityDto.getCode());

			pstmt.execute();

			community = findCommunityByCode(communityDto.getCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return community;
	}

	public boolean deleteCommunity(CommunityRequestDto communityRequestDto) {
		if (findCommunityByCode(communityRequestDto.getCode()) == null) {
			return false;
		}

		try {
			conn = DBManager.getConnection();
			
			String sql = "DELETE FROM community WHERE code=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, communityRequestDto.getCode());

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

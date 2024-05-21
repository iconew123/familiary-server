package community.model;

import java.sql.Blob;

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
		String sql = "SELECT code, user_id, user_nickname, title, content, photo, category, reg_date FROM community WHERE category='잡담' order by reg_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int code = rs.getInt(1);
				String userId = rs.getString(2);
				String userNickName = rs.getString(3);
				String title = rs.getString(4);
				String content = rs.getString(5);
				Blob photo = rs.getBlob(6);
				String tag = rs.getString(7);
				String comment = rs.getString(8);
				Timestamp regDate = rs.getTimestamp(9);
				CommunityResponseDto community = new CommunityResponseDto(code, userId, userNickName, title, content, photo, tag, comment, regDate);
				list.add(community);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	// 잡담용 크리에이트 보드
	public CommunityResponseDto createBoard(CommunityRequestDto CommunityRequestDto) {
		conn = DBManager.getConnection();

		CommunityResponseDto community = null;
		String userNickname = CommunityRequestDto.getUserNickname();
		String title = CommunityRequestDto.getTitle();
		String content = CommunityRequestDto.getContent();
		
		Blob photo = CommunityRequestDto.getPhoto();
		if(photo == null) {
			String sql = "INSERT INTO community (user_nickname, title, content, tag) VALUES (?, ?, ?,'잡담')";

			try {
				pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, userNickname);
				pstmt.setString(2, title);
				pstmt.setString(3, content);

				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int code = rs.getInt(1);
					community = findCommunity(code);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return community;
		} else {
			String sql = "INSERT INTO community (user_nickname, title, content, tag, photo) VALUES (?, ?, ?, '잡담', ?)";

			try {
				pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, userNickname);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				pstmt.setBlob(4, photo);

				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int code = rs.getInt(1);
					community = findCommunity(code);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return community;
		}
	}
	
	//
	public CommunityResponseDto findCommunity(int code) {
		conn = DBManager.getConnection();
		CommunityResponseDto community = null;
		String sql = "SELECT code, user_nickname, title, content, photo FROM community WHERE code = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int communityCode = rs.getInt(1);
				String userNickname = rs.getString(2);
				String title = rs.getString(3);
				String content = rs.getString(4);
				Blob photo = rs.getBlob(5);

				community = new CommunityResponseDto(communityCode, userNickname, title, content, photo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return community;
	}
	
}

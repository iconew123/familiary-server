package diaryComment.model;

import util.DBManager;

import java.sql.*;

public class DiaryCommentDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private DiaryCommentDao() {

	}

	private static DiaryCommentDao instance = new DiaryCommentDao();

	public static DiaryCommentDao getInstance() {
		return instance;
	}

	public boolean createComment(DiaryCommentRequestDto comment){

        try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO diary_comment(diary_code, user_id, user_nickname, content) VALUES (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			System.out.println("diarycode : " + comment.getDiaryCode());
			System.out.println("userid : " + comment.getUserId());
			System.out.println("username : " + comment.getNickName());
			System.out.println("content : " + comment.getContent());

			pstmt.setInt(1, comment.getDiaryCode());
			pstmt.setString(2, comment.getUserId());
			pstmt.setString(3, comment.getNickName());
			pstmt.setString(4, comment.getContent());

			int result = pstmt.executeUpdate();
			System.out.println(result);
			if(result == 1)
				return true;
			else
				return false;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			DBManager.close(conn, pstmt);
		}

		return false;
	}

	public DiaryCommentResponseDto findComment(String code){
		DiaryCommentResponseDto comment = null;

        try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM diary_comment WHERE code = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, code);

			rs = pstmt.executeQuery();
			while(rs.next()){
				code = rs.getString(1);
				int diaryCode = rs.getInt(2);
				String userId = rs.getString(3);
				String nickName = rs.getString(4);
				String content = rs.getString(5);
				Timestamp regDate = rs.getTimestamp(6);
				Timestamp updateDate = rs.getTimestamp(7);

				comment = new DiaryCommentResponseDto(code, diaryCode, userId , nickName, content , regDate , updateDate);

			}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comment;
	}

	public boolean updateComment(String code , String comment){
        try {
			conn = DBManager.getConnection();
			String sql = "UPDATE diary_comment SET content = ? WHERE code = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, comment);
			pstmt.setString(2, code);

			int result = pstmt.executeUpdate();

			if (result == 1)
				return true;
			else
				return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

package diaryComment.model;

import util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
